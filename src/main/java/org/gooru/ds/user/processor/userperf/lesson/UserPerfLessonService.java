package org.gooru.ds.user.processor.userperf.lesson;

import java.util.List;

import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonCommand;
import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonDao;
import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonModel;

import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author mukul@gooru
 */
class UserPerfLessonService {

    private final UserPerfLessonDao userPerfLessonDao;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserPerfLessonService.class);

    UserPerfLessonService(DBI dbi) {
        this.userPerfLessonDao = dbi.onDemand(UserPerfLessonDao.class);
    }

    public UserPerfLessonModelResponse fetchUserLessonPerf(UserPerfLessonCommand command) {
        List<UserPerfLessonModel> models = userPerfLessonDao.fetchUserPerfLesson(command.asBean());
        UserPerfLessonModelResponse result = new UserPerfLessonModelResponse();
        result.setLessons(models);
        return result;
    }




}
