package org.gooru.ds.user.processor;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.activeuserlist.ActiveUserListProcessor;
import org.gooru.ds.user.processor.user.distribution.UserDistributionProcessor;
import org.gooru.ds.user.processor.user.journey.UserJourneyProcessor;
import org.gooru.ds.user.processor.userperf.assessments.UserPerfAssessmentsProcessor;
import org.gooru.ds.user.processor.userperf.collections.UserPerfCollectionsProcessor;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseProcessor;
import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonProcessor;
import org.gooru.ds.user.processor.userperf.summary.assessment.UserPerfAsmtSummaryProcessor;
import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryProcessor;
import org.gooru.ds.user.processor.userprefs.content.UserPrefsContentProcessor;
import org.gooru.ds.user.processor.userprefs.curators.UserPrefsCuratorProcessor;
import org.gooru.ds.user.processor.userprefs.providers.UserPrefsProviderProcessor;
import org.gooru.ds.user.processor.userstats.competency.UserStatsCompetencyProcessor;
import org.gooru.ds.user.processor.userstats.content.UserStatsContentsProcessor;
import org.gooru.ds.user.processor.userstats.courses.UserStatsCoursesProcessor;
import org.gooru.ds.user.processor.userstats.curator.UserStatsCuratorProcessor;
import org.gooru.ds.user.processor.userstats.journeys.UserStatsJourneysProcessor;
import org.gooru.ds.user.processor.userstats.provider.UserStatsProviderProcessor;
import org.gooru.ds.user.processor.userstats.timespent.UserStatsTimespentProcessor;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18.
 * updated by mukul@gooru
 */
public final class MessageProcessorBuilder {
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
        case Constants.Message.MSG_OP_USER_PERF_LESSONS:
            return new UserPerfLessonProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PERF_ASSESSMENTS:
            return new UserPerfAssessmentsProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PERF_COLLECTIONS:
            return new UserPerfCollectionsProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_SUMMARY_ASSESSMENT:
            return new UserPerfAsmtSummaryProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_SUMMARY_COLLECTION:
            return new UserPerfCollSummaryProcessor(vertx, message);

        case Constants.Message.MSG_OP_USER_STATS_CONTENTS:
            return new UserStatsContentsProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_PROVIDERS:
            return new UserStatsProviderProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_CURATORS:
            return new UserStatsCuratorProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_STATS_COURSES:
            return new UserStatsCoursesProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PREFS_CONTENT:
            return new UserPrefsContentProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PREFS_CURATORS:
            return new UserPrefsCuratorProcessor(vertx, message);
        case Constants.Message.MSG_OP_USER_PREFS_PROVIDERS:
            return new UserPrefsProviderProcessor(vertx, message);
        case Constants.Message.MSG_OP_COMPETENCY_MATRIX:
            return new StubbedMessageProcessor(vertx, message);
        default:
            return null;
        }

    }

}
