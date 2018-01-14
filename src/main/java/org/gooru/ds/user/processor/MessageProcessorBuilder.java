package org.gooru.ds.user.processor;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.activeuserlist.ActiveUserListProcessor;
import org.gooru.ds.user.processor.user.distribution.UserDistributionProcessor;
import org.gooru.ds.user.processor.user.journey.UserJourneyProcessor;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseProcessor;
import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonProcessor;
import org.gooru.ds.user.processor.userstats.competency.UserStatsCompetencyProcessor;
import org.gooru.ds.user.processor.userstats.journeys.UserStatsJourneysProcessor;
import org.gooru.ds.user.processor.userstats.timespent.UserStatsTimespentProcessor;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 * updated by mukul@gooru
 */
public class MessageProcessorBuilder {
    private MessageProcessorBuilder() {
        throw new AssertionError();
    }

    public static MessageProcessor buildProcessor(Vertx vertx, Message<JsonObject> message, String op) {
        switch (op) {
        case Constants.Message.MSG_OP_USER_DISTRIBUTION:
            return new UserDistributionProcessor(vertx, message);
        case Constants.Message.MSG_OP_ACTIVE_USER_LIST:
            return new ActiveUserListProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_COMPETENCY:
            return new UserStatsCompetencyProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_JOURNEYS:
            return new UserStatsJourneysProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_TIMESPENT:
            return new UserStatsTimespentProcessor(vertx, message);
          
        case Constants.Message.MSG_OP_USER_JOURNEY:
            return new UserJourneyProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PERF_COURSE:
            return new UserPerfCourseProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PERF_LESSON:
            return new UserPerfLessonProcessor(vertx, message);

            
        default:
            return null;
        }

    }

}
