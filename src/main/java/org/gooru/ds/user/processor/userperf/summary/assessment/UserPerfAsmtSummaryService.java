package org.gooru.ds.user.processor.userperf.summary.assessment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.gooru.ds.user.app.jdbi.PGArrayUtils;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author mukul@gooru
 */
class UserPerfAsmtSummaryService {

    private final UserPerfAsmtSummaryDao userPerfAsmtSummaryDao;
    private UserPerfAsmtSummaryCommand command;
    private String classId;
    private String courseId;
    private String unitId;
    private String lessonId;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfAsmtSummaryService.class);

    UserPerfAsmtSummaryService(DBI dbi) {
        this.userPerfAsmtSummaryDao = dbi.onDemand(UserPerfAsmtSummaryDao.class);
    }

    public UserPerfAsmtSummaryModelResponse fetchUserAsmtSummary(UserPerfAsmtSummaryCommand command) {
    
    	//Refactor
        this.command = command;
        classId = this.command.getclassId();
        courseId = this.command.getcourseId();
        lessonId = this.command.getlessonId();
        unitId = this.command.getunitId();
        List<UserPerfAsmtSummaryModel> model = new ArrayList<>();

        if (courseId == null && lessonId == null && unitId == null) {
            model = fetchAsmtSummaryforComp(this.command);
        } else {
            model = fetchAsmtSummary(this.command);
        }

        UserPerfAsmtSummaryModelResponse result = new UserPerfAsmtSummaryModelResponse();
        result.setResources(model);
        return result;

    }
    
    private List<UserPerfAsmtSummaryModel> fetchAsmtSummary(UserPerfAsmtSummaryCommand command) {
    	return userPerfAsmtSummaryDao.fetchUserPerfAsmtSummary(command.asBean());
    }

    private List<UserPerfAsmtSummaryModel> fetchAsmtSummaryforComp(UserPerfAsmtSummaryCommand command) {        
            return userPerfAsmtSummaryDao.fetchUserPerfAsmtSummaryforComp(command.asBean());            
    }

}
