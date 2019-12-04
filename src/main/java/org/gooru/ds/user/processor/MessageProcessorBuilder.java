package org.gooru.ds.user.processor;

import org.gooru.ds.user.constants.Constants;
import org.gooru.ds.user.processor.activeuserlist.ActiveUserListProcessor;
import org.gooru.ds.user.processor.activity.feebacks.create.CreateUserActivityFeedbackProcessor;
import org.gooru.ds.user.processor.activity.feedbacks.fetch.FetchUserActivityFeedbacksProcessor;
import org.gooru.ds.user.processor.atc.recompute.ATCCompetencyStatsProcessor;
import org.gooru.ds.user.processor.baselearnerprofile.LearnerProfileBaselineUpdateProcessor;
import org.gooru.ds.user.processor.baselearnerprofile.read.ReadBaselineLearnerProfileProcessor;
import org.gooru.ds.user.processor.competency.subjects.CompetencySubjectListProcessor;
import org.gooru.ds.user.processor.competencycompletion.classes.AllClassesCompetencyCompletionStatsProcessor;
import org.gooru.ds.user.processor.competencymatrixcoordinates.CompetencyMatrixCoordinatesProcessor;
import org.gooru.ds.user.processor.competencysummary.UserCompetencySummaryProcessor;
import org.gooru.ds.user.processor.domain.competency.perf.report.DomainCompetencyCompletionReportProcessor;
import org.gooru.ds.user.processor.domain.report.DomainReportProcessor;
import org.gooru.ds.user.processor.grade.boundary.GradeBoundaryListProcessor;
import org.gooru.ds.user.processor.grade.competency.GradeCompetencyProcessor;
import org.gooru.ds.user.processor.grade.master.GradeMasterProcessor;
import org.gooru.ds.user.processor.struggling.competencies.StrugglingCompetenciesProcessor;
import org.gooru.ds.user.processor.struggling.competencies.perf.StrugglingCompetencyPerformanceProcessor;
import org.gooru.ds.user.processor.subjectcompetencymatrix.UserSubjectCompetencyMatrixProcessor;
import org.gooru.ds.user.processor.user.competencylist.UserCompetencyListProcessor;
import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportProcessor;
import org.gooru.ds.user.processor.user.distribution.UserDistributionProcessor;
import org.gooru.ds.user.processor.user.journey.UserJourneyProcessor;
import org.gooru.ds.user.processor.user.portfolio.competency.UserCompetencyPortfolioProcessor;
import org.gooru.ds.user.processor.user.portfolio.content.item.UserPortfolioItemPerfProcessor;
import org.gooru.ds.user.processor.user.portfolio.content.items.UserPortfolioUniqueItemPerfProcessor;
import org.gooru.ds.user.processor.user.portfolio.content.summary.assessment.UserPortfolioAsmtSummaryProcessor;
import org.gooru.ds.user.processor.user.portfolio.content.summary.collection.UserPortfolioCollSummaryProcessor;
import org.gooru.ds.user.processor.user.portfolio.domain.UserDomainPortfolioProcessor;
import org.gooru.ds.user.processor.user.portfolio.subject.UserSubjectPortfolioProcessor;
import org.gooru.ds.user.processor.user.skylinecompetency.next.UserSkylineCompetencyNextProcessor;
import org.gooru.ds.user.processor.usercompetencymatrix.UserCompetencyMatrixProcessor;
import org.gooru.ds.user.processor.usercoursecompetencymatrix.UserCourseCompetencyMatrixProcessor;
import org.gooru.ds.user.processor.userdomaincompetencymatrix.UserDomainCompetencyMatrixProcessor;
import org.gooru.ds.user.processor.usergrades.UserGradesProcessor;
import org.gooru.ds.user.processor.userperf.collections.UserPerfCollectionsProcessor;
import org.gooru.ds.user.processor.userperf.competency.collections.UserPerfCompetencyCollectionsProcessor;
import org.gooru.ds.user.processor.userperf.course.UserPerfCourseProcessor;
import org.gooru.ds.user.processor.userperf.lesson.UserPerfLessonProcessor;
import org.gooru.ds.user.processor.userperf.summary.assessment.UserPerfAsmtSummaryProcessor;
import org.gooru.ds.user.processor.userperf.summary.collection.UserPerfCollSummaryProcessor;
import org.gooru.ds.user.processor.userprefs.content.UserPrefsContentProcessor;
import org.gooru.ds.user.processor.userprefs.curators.UserPrefsCuratorProcessor;
import org.gooru.ds.user.processor.userprefs.providers.UserPrefsProviderProcessor;
import org.gooru.ds.user.processor.userprofile.UserProfileProcessor;
import org.gooru.ds.user.processor.userstats.competency.UserStatsCompetencyProcessor;
import org.gooru.ds.user.processor.userstats.content.UserStatsContentsProcessor;
import org.gooru.ds.user.processor.userstats.courses.UserStatsCoursesProcessor;
import org.gooru.ds.user.processor.userstats.curator.UserStatsCuratorProcessor;
import org.gooru.ds.user.processor.userstats.journeys.UserStatsJourneysProcessor;
import org.gooru.ds.user.processor.userstats.provider.UserStatsProviderProcessor;
import org.gooru.ds.user.processor.userstats.resources.UserStatsResourcesProcessor;
import org.gooru.ds.user.processor.userstats.timespent.UserStatsTimespentProcessor;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.Message;
import io.vertx.core.json.JsonObject;

/**
 * @author ashish on 10/1/18. updated by mukul@gooru
 */
public final class MessageProcessorBuilder {

  private MessageProcessorBuilder() {
    throw new AssertionError();
  }

  public static MessageProcessor buildProcessor(Vertx vertx, Message<JsonObject> message,
      String op) {
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
      case Constants.Message.MSG_OP_USER_PROFILE:
        return new UserProfileProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_GRADES:
        return new UserGradesProcessor(vertx, message);

      case Constants.Message.MSG_OP_USER_JOURNEY:
        return new UserJourneyProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PERF_COURSE:
        return new UserPerfCourseProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PERF_LESSONS:
        return new UserPerfLessonProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PERF_COLLECTIONS:
        return new UserPerfCollectionsProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_SUMMARY_ASSESSMENT:
        return new UserPerfAsmtSummaryProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_SUMMARY_COLLECTION:
        return new UserPerfCollSummaryProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PERF_COMPETENCY_COLLECTIONS:
        return new UserPerfCompetencyCollectionsProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_STATS_RESOURCES:
        return new UserStatsResourcesProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_COMPETENCY:
        return new UserCompetencyListProcessor(vertx, message);

      case Constants.Message.MSG_OP_USER_COURSE_COMPETENCY_REPORT:
        return new UserCourseCompetencyReportProcessor(vertx, message);

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
        return new UserCompetencyMatrixProcessor(vertx, message);
      case Constants.Message.MSG_OP_COMPETENCY_MATRIX_COURSE:
        return new UserCourseCompetencyMatrixProcessor(vertx, message);
      case Constants.Message.MSG_OP_COMPETENCY_MATRIX_DOMAIN:
        return new UserDomainCompetencyMatrixProcessor(vertx, message);

      case Constants.Message.MSG_OP_SKYLINE_COMPETENCY_NEXT:
        return new UserSkylineCompetencyNextProcessor(vertx, message);
      case Constants.Message.MSG_OP_COMPETENCY_MATRIX_COORDS:
        return new CompetencyMatrixCoordinatesProcessor(vertx, message);
      case Constants.Message.MSG_OP_COMPETENCY_SUBJECTS:
        return new CompetencySubjectListProcessor(vertx, message);

      case Constants.Message.MSG_OP_GRADES:
        return new GradeMasterProcessor(vertx, message);
      case Constants.Message.MSG_OP_GRADE_BOUNDARY:
        return new GradeBoundaryListProcessor(vertx, message);
      case Constants.Message.MSG_OP_GRADE_COMPETENCIES:
        return new GradeCompetencyProcessor(vertx, message);



      case Constants.Message.MSG_OP_BASE_LEARNER_PROFILE:
        return new LearnerProfileBaselineUpdateProcessor(vertx, message);
      case Constants.Message.MSG_OP_USERS_PERF_VS_COMPLETION:
        // return new CompetencyPerfVsCompletionProcessor(vertx, message);
        return new ATCCompetencyStatsProcessor(vertx, message);
      case Constants.Message.MSG_OP_STATS_COMPETENCY_COMPLETION:
        return new AllClassesCompetencyCompletionStatsProcessor(vertx, message);
      case Constants.Message.MSG_OP_READ_BASELINE_LEARNER_PROFILE:
        return new ReadBaselineLearnerProfileProcessor(vertx, message);

      case Constants.Message.MSG_OP_USER_COMPETENCY_SUMMARY:
        return new UserCompetencySummaryProcessor(vertx, message);

      case Constants.Message.MSG_OP_DOMAIN_REPORT:
        return new DomainReportProcessor(vertx, message);
      case Constants.Message.MSG_OP_DOMAIN_COMPETENCY_PERF_REPORT:
        return new DomainCompetencyCompletionReportProcessor(vertx, message);

      case Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ITEMS_PERF:
        return new UserPortfolioUniqueItemPerfProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ITEM_PERF:
        return new UserPortfolioItemPerfProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_ASMT_SUMMARY:
        return new UserPortfolioAsmtSummaryProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PORTFOLIO_CONTENT_COLL_SUMMARY:
        return new UserPortfolioCollSummaryProcessor(vertx, message);

      case Constants.Message.MSG_OP_USER_PORTFOLIO_COMPETENCY:
        return new UserCompetencyPortfolioProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PORTFOLIO_DOMAIN:
        return new UserDomainPortfolioProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_PORTFOLIO_SUBJECT:
        return new UserSubjectPortfolioProcessor(vertx, message);
        
      case Constants.Message.MSG_OP_STRUGGLING_COMPETENCIES:
        return new StrugglingCompetenciesProcessor(vertx, message);
      case Constants.Message.MSG_OP_STRUGGLING_COMPETENCY_PERF:
        return new StrugglingCompetencyPerformanceProcessor(vertx, message);
      
      case Constants.Message.MSG_OP_USER_ACTIVITY_FEEDBACK_CREATE:
        return new CreateUserActivityFeedbackProcessor(vertx, message);
      case Constants.Message.MSG_OP_USER_ACTIVITY_FEEDBACK_FETCH:
        return new FetchUserActivityFeedbacksProcessor(vertx, message);
        
      case Constants.Message.MSG_OP_USER_SUBJECT_COMPETENCY_MATRIX:
      return new UserSubjectCompetencyMatrixProcessor(vertx, message);

      default:
        return null;
    }

  }

}
