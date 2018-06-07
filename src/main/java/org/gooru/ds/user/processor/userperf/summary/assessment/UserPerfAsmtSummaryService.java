package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfAsmtSummaryService {

    private final UserPerfAsmtSummaryDao userPerfAsmtSummaryDao;
    private final CoreContentsService coreContentsService;
    private UserPerfAsmtSummaryCommand command;
    private String classId;
    private String courseId;
    private String unitId;
    private String lessonId;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAsmtSummaryService.class);

    UserPerfAsmtSummaryService(DBI dbi, DBI coreDbi) {
        this.userPerfAsmtSummaryDao = dbi.onDemand(UserPerfAsmtSummaryDao.class);
        this.coreContentsService = new CoreContentsService(coreDbi);
    }

    public UserPerfAsmtSummaryModelResponse fetchUserAsmtSummary(UserPerfAsmtSummaryCommand command) {
    
    	//Refactor
        this.command = command;
        classId = this.command.getclassId();
        courseId = this.command.getcourseId();
        lessonId = this.command.getlessonId();
        unitId = this.command.getunitId();
        List<UserPerfAsmtSummaryModel> models = new ArrayList<>();

        if (courseId == null && lessonId == null && unitId == null) {
            models = fetchAsmtSummaryforComp(this.command);
        } else {
            models = fetchAsmtSummary(this.command);
        }

        List<String> contentIds = new ArrayList<>();
        models.forEach(model -> {
        	contentIds.add(model.getId());
        });
        
        Map<String, CoreContentsModel> contentTitles = this.coreContentsService.fetchContentTitles(contentIds);
        models.forEach(model -> {
        	CoreContentsModel coreModel = contentTitles.get(model.getId());
        	
        	model.setTitle(coreModel.getTitle());
        	model.setContentType(coreModel.getContentType());
        });
        
        UserPerfAsmtSummaryModelResponse result = new UserPerfAsmtSummaryModelResponse();
        result.setResources(models);
        return result;

    }
    
    private List<UserPerfAsmtSummaryModel> fetchAsmtSummary(UserPerfAsmtSummaryCommand command) {
    	return userPerfAsmtSummaryDao.fetchUserPerfAsmtSummary(command.asBean());
    }

    private List<UserPerfAsmtSummaryModel> fetchAsmtSummaryforComp(UserPerfAsmtSummaryCommand command) {        
            return userPerfAsmtSummaryDao.fetchUserPerfAsmtSummaryforComp(command.asBean());            
    }

}
