package org.gooru.ds.user.processor.userperf.course;

import java.util.ArrayList;
import java.util.List;

import org.gooru.ds.user.app.data.EventBusMessage;
import org.gooru.ds.user.app.jdbi.DBICreator;
import org.gooru.ds.user.processor.MessageProcessor;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseCommand;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseBaseModel;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseService;
import org.gooru.ds.user.responses.MessageResponse;
import org.gooru.ds.user.responses.MessageResponseFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;


/**
 * @author mukul@gooru
 */
public class UserPerfCourseProcessor implements MessageProcessor {
	
	
    private final Vertx vertx;
    private final Message<JsonObject> message;
    private final Future<MessageResponse> result;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfCourseProcessor.class);
    private EventBusMessage eventBusMessage;
    private final UserPerfCourseService UserPerfCourseService =
        new UserPerfCourseService(DBICreator.getDbiForDefaultDS());

    public UserPerfCourseProcessor(Vertx vertx, Message<JsonObject> message) {
        this.vertx = vertx;
        this.message = message;
        this.result = Future.future();
    }

    @Override
    public Future<MessageResponse> process() {
        try {
            this.eventBusMessage = EventBusMessage.eventBusMessageBuilder(message);
            UserPerfCourseCommand command = UserPerfCourseCommand.builder(eventBusMessage.getRequestBody());
            fetchUserCoursePerf(command);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }
        return result;
    }

    private void fetchUserCoursePerf(UserPerfCourseCommand command) {  
    	List<UserPerfCourseBaseModel> models = UserPerfCourseService.fetchUserCoursePerf(command);
        try {
            UserPerfCourseModel outcome = transform(models);
            String resultString = new ObjectMapper().writeValueAsString(outcome);
            result.complete(MessageResponseFactory.createOkayResponse(new JsonObject(resultString)));            
        } catch (JsonProcessingException e) {
            LOGGER.error("Not able to convert data to JSON", e);
            result.fail(e);
        } catch (DecodeException e) {
            LOGGER.warn("Not able to convert data to JSON", e);
            result.fail(e);
        } catch (Throwable throwable) {
            LOGGER.warn("Encountered exception", throwable);
            result.fail(throwable);
        }

    }
    
    private static UserPerfCourseModel transform(List<UserPerfCourseBaseModel> models) {
        boolean initializedCourse = false;
        UserPerfCourseModel courseModel = new UserPerfCourseModel();
        List<UserPerfUnitModel> unitModels = new ArrayList<>();
        for (UserPerfCourseBaseModel model : models) {
            if (!initializedCourse) {
                courseModel.setClassCode(model.getClassCode());
                courseModel.setClassId(model.getClassId());
                courseModel.setClassTitle(model.getClassTitle());
                courseModel.setCourseAsmtScore(model.getCourseAsmtScore());
                courseModel.setCourseAsmtTimeSpent(model.getCourseAsmtTimeSpent());
                courseModel.setCourseAssessmentsComplete(model.getCourseAssessmentsComplete());
                courseModel.setCourseCollTimeSpent(model.getCourseCollTimeSpent());
                courseModel.setCourseId(model.getCourseId());
                courseModel.setCourseTitle(model.getCourseTitle());
                courseModel.setTotalAssessments(model.getTotalAssessments());
                initializedCourse = true;
            }
            UserPerfUnitModel unitModel = new UserPerfUnitModel();
            unitModel.setUnitAsmtScore(model.getUnitAsmtScore());
            unitModel.setUnitAsmtTimeSpent(model.getUnitAsmtTimeSpent());
            unitModel.setUnitCollTimeSpent(model.getUnitCollTimeSpent());
            unitModel.setUnitId(model.getUnitId());
            unitModel.setUnitTitle(model.getUnitTitle());
            unitModels.add(unitModel);
        }
        courseModel.setUnits(unitModels);
        return courseModel;
    }


}
