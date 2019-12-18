package org.gooru.ds.user.constants;

import java.util.Arrays;
import java.util.List;

/**
 * @author ashish on 10/1/18. updated by mukul@gooru
 */
public final class Constants {

  public static final class EventBus {

    public static final String MBEP_AUTH = "org.gooru.suggestions.eventbus.auth";
    public static final String MBUS_TIMEOUT = "event.bus.send.timeout.seconds";
    public static final String MBEP_DISPATCHER = "org.gooru.ds.users.dispatcher";

    private EventBus() {
      throw new AssertionError();
    }
  }

  public static final class Message {

    public static final String MSG_OP = "mb.op";
    public static final String MSG_OP_AUTH = "auth";
    public static final String MSG_OP_STATUS = "mb.op.status";
    public static final String MSG_OP_STATUS_SUCCESS = "mb.op.status.success";
    public static final String MSG_OP_STATUS_FAIL = "mb.op.status.fail";

    public static final String MSG_OP_USER_DISTRIBUTION = "mb.op.user.distribution";
    public static final String MSG_OP_ACTIVE_USER_LIST = "mb.op.active.user.list";
    public static final String MSG_OP_USER_STATS_JOURNEYS = "mb.op.user.stats.journeys";
    public static final String MSG_OP_USER_STATS_TIMESPENT = "mb.op.user.stats.timespent";
    public static final String MSG_OP_USER_STATS_COMPETENCY = "mb.op.user.stats.competency";
    public static final String MSG_OP_USER_STATS_COURSES = "mb.op.user.stats.courses";
    public static final String MSG_OP_USER_STATS_CONTENTS = "mb.op.user.stats.contents";
    public static final String MSG_OP_USER_STATS_PROVIDERS = "mb.op.user.stats.providers";
    public static final String MSG_OP_USER_STATS_CURATORS = "mb.op.user.stats.curators";

    public static final String MSG_OP_USER_STATS_RESOURCES = "mb.op.user.stats.resources";
    public static final String MSG_OP_USER_PREFS_CONTENT = "mb.op.user.prefs.content";
    public static final String MSG_OP_USER_PREFS_CURATORS = "mb.op.user.prefs.curators";
    public static final String MSG_OP_USER_PREFS_PROVIDERS = "mb.op.user.prefs.providers";
    public static final String MSG_OP_USER_PROFILE = "mb.op.user.profile";
    public static final String MSG_OP_USER_GRADES = "mb.op.user.grades";
    public static final String MSG_OP_COMPETENCY_MATRIX = "mb.op.competency.matrix";
    public static final String MSG_OP_COMPETENCY_MATRIX_COURSE = "mb.op.competency.matrix.course";
    public static final String MSG_OP_COMPETENCY_MATRIX_DOMAIN = "mb.op.competency.matrix.domain";

    public static final String MSG_OP_SKYLINE_COMPETENCY_NEXT = "mb.op.skyline.competency.next";
    public static final String MSG_OP_COMPETENCY_MATRIX_COORDS = "mb.op.competency.matrix.coords";
    public static final String MSG_OP_COMPETENCY_SUBJECTS = "mb.op.competency.subjects";
    public static final String MSG_OP_GRADES = "mb.op.grades";
    public static final String MSG_OP_GRADE_BOUNDARY = "mb.op.grade.boundary";
    public static final String MSG_OP_GRADE_COMPETENCIES = "mb.op.grade.competencies";

    public static final String MSG_OP_USER_JOURNEY = "mb.op.user.journey";
    public static final String MSG_OP_USER_PERF_COURSE = "mb.op.user.performance.course";
    public static final String MSG_OP_USER_PERF_LESSONS = "mb.op.user.performance.lessons";
    public static final String MSG_OP_USER_PERF_COLLECTIONS = "mb.op.user.performance.collections";
    public static final String MSG_OP_USER_COMPETENCY = "mb.op.user.competency";
    public static final String MSG_OP_USER_PERF_COMPETENCY_COLLECTIONS =
        "mb.op.user.performance.competency.collections";
    public static final String MSG_OP_USER_SUMMARY_ASSESSMENT = "mb.op.user.summary.assessment";
    public static final String MSG_OP_USER_SUMMARY_COLLECTION = "mb.op.user.summary.collection";

    // User course competency report - domain level summary
    public static final String MSG_OP_USER_COURSE_COMPETENCY_REPORT =
        "mb.op.user.course.competency.report";

    // Domain reports
    public static final String MSG_OP_DOMAIN_REPORT = "mb.op.domain.report";
    public static final String MSG_OP_DOMAIN_COMPETENCY_PERF_REPORT =
        "mb.op.domain.competency.perf.report";

    // Baseline Learner Profile
    public static final String MSG_OP_BASE_LEARNER_PROFILE = "mb.op.base.learner.profile";
    // Navigator Course ATC
    public static final String MSG_OP_USERS_PERF_VS_COMPLETION =
        "mb.op.users.performance.vs.completion";
    public static final String MSG_OP_STATS_COMPETENCY_COMPLETION =
        "mb.op.competency.completion.stats";
    // Read baseline learner profile
    public static final String MSG_OP_READ_BASELINE_LEARNER_PROFILE =
        "mb.op.base.learner.profile.read";

    // Content-Portfolio
    public static final String MSG_OP_USER_PORTFOLIO_CONTENT_ITEMS_PERF =
        "mb.op.user.portfolio.content.items.perf";
    public static final String MSG_OP_USER_PORTFOLIO_CONTENT_ITEM_PERF =
        "mb.op.user.portfolio.content.item.perf";
    public static final String MSG_OP_USER_PORTFOLIO_CONTENT_ASMT_SUMMARY =
        "mb.op.user.portfolio.content.asmt.summary";
    public static final String MSG_OP_USER_PORTFOLIO_CONTENT_COLL_SUMMARY =
        "mb.op.user.portfolio.content.coll.summary";
    public static final String MSG_OP_USER_PORTFOLIO_CONTENT_OA_SUMMARY =
        "mb.op.user.portfolio.content.oa.summary";
    public static final String MSG_OP_USER_PORTFOLIO_COMPETENCY =
        "mb.op.user.portfolio.competency.items.perf";
    public static final String MSG_OP_USER_PORTFOLIO_DOMAIN =
        "mb.op.user.portfolio.domain.items.perf";
    public static final String MSG_OP_USER_PORTFOLIO_SUBJECT =
        "mb.op.user.portfolio.subject.items.perf";

    // Struggling competencies
    public static final String MSG_OP_STRUGGLING_COMPETENCIES = "mb.op.struggling.competencies";
    public static final String MSG_OP_STRUGGLING_COMPETENCY_PERF =
        "mb.op.struggling.competency.performance";

    // User Competency Summary
    public static final String MSG_OP_USER_COMPETENCY_SUMMARY = "mb.op.user.competency.summary";

    // User Activity Feedback
    public static final String MSG_OP_USER_ACTIVITY_FEEDBACK_CREATE =
        "mb.op.user.activity.feedback.create";
    public static final String MSG_OP_USER_ACTIVITY_FEEDBACK_FETCH =
        "mb.op.user.activity.feedback.fetch";

    // User Subject Competency Matrix
    public static final String MSG_OP_USER_SUBJECT_COMPETENCY_MATRIX =
        "mb.op.user.subject.competency.matrix";
    // Learner Vectors
    public static final String MSG_OP_LEARNER_VECTORS = "mb.op.user.vectors";
    // Learner Preferences
    public static final String MSG_OP_LEARNER_PREFS = "mb.op.user.prefs";
    // Learner Portfolio  across subject stats
    public static final String MSG_OP_LEARNER_PORTFOLIO_STATS = "mb.op.user.portfolio.stats";
    // Learner Portfolio  by  subject stats
    public static final String MSG_OP_LEARNER_PORTFOLIO_SUBJECT_STATS = "mb.op.user.portfolio.subject.stats";
    // Learner Portfolio by subject domain stats
    public static final String MSG_OP_LEARNER_PORTFOLIO_SUBJECT_DOMAIN_STATS = "mb.op.user.portfolio.subject.domain.stats";

    public static final String MSG_API_VERSION = "api.version";
    public static final String MSG_SESSION_TOKEN = "session.token";
    public static final String MSG_KEY_SESSION = "session";
    public static final String MSG_USER_ANONYMOUS = "anonymous";
    public static final String MSG_USER_ID = "user_id";
    public static final String MSG_HTTP_STATUS = "http.status";
    public static final String MSG_HTTP_BODY = "http.body";
    public static final String MSG_HTTP_HEADERS = "http.headers";

    public static final String MSG_MESSAGE = "message";
    public static final String PROCESSING_AUTH_TIME = "auth.processing.time";
    public static final String PROCESSING_HANDLER_START_TIME = "handler.start.time";

    private Message() {
      throw new AssertionError();
    }
  }

  public static final class Response {

    private Response() {
      throw new AssertionError();
    }
  }

  public static final class Params {

    public static final String AGENT_MOBILE = "mobile";
    public static final String AGENT_DESKTOP = "desktop";
    public static final String AGENT_DEFAULT = AGENT_DESKTOP;
    public static final List<String> SUPPORTED_AGENTS = Arrays.asList(AGENT_MOBILE, AGENT_DESKTOP);

    private Params() {
      throw new AssertionError();
    }
  }

  public static final class Route {
    public static final String COLON = ":";
    public static final String SEP = "/";

    public static final String API_AUTH_ROUTE = "/api/ds/users/*";
    public static final String API_INTERNAL_BANNER = "/api/internal/banner";
    public static final String API_INTERNAL_METRICS = "/api/internal/metrics";
    private static final String API_BASE_ROUTE = "/api/ds/users/:version/";
    public static final String API_USER_DISTRIBUTION = API_BASE_ROUTE + "user/distribution";
    public static final String API_ACTIVE_USER_LIST = API_BASE_ROUTE + "user/distribution/active";
    public static final String API_USER_STATS_JOURNEYS = API_BASE_ROUTE + "user/stats/journeys";
    public static final String API_USER_STATS_TIMESPENT = API_BASE_ROUTE + "user/stats/timespent";
    public static final String API_USER_STATS_COMPETENCY = API_BASE_ROUTE + "user/stats/competency";
    public static final String API_USER_STATS_RESOURCES = API_BASE_ROUTE + "user/stats/resources";

    public static final String API_USER_JOURNEY = API_BASE_ROUTE + "user/journey";
    public static final String API_USER_PERF_COURSE = API_BASE_ROUTE + "user/performance/course";
    public static final String API_USER_PERF_LESSONS = API_BASE_ROUTE + "user/performance/lessons";
    public static final String API_USER_PERF_COLLECTIONS =
        API_BASE_ROUTE + "user/performance/collections";
    public static final String API_USER_SUMMARY_ASSESSMENT =
        API_BASE_ROUTE + "user/summary/assessment";
    public static final String API_USER_SUMMARY_COLLECTION =
        API_BASE_ROUTE + "user/summary/collection";
    public static final String API_USER_COMPETENCY = API_BASE_ROUTE + "user/competency";
    public static final String API_USER_PERF_COMPETENCY_COLLECTIONS =
        API_BASE_ROUTE + "user/performance/competency/collections";

    public static final String API_USER_STATS_CONTENT = API_BASE_ROUTE + "user/stats/contents";
    public static final String API_USER_STATS_PROVIDER = API_BASE_ROUTE + "user/stats/providers";
    public static final String API_USER_STATS_CURATOR = API_BASE_ROUTE + "user/stats/curators";
    public static final String API_USER_STATS_COURSES = API_BASE_ROUTE + "user/stats/courses";
    public static final String API_USER_PREFS_CONTENT = API_BASE_ROUTE + "user/prefs/content";
    public static final String API_USER_PREFS_CURATORS = API_BASE_ROUTE + "user/prefs/curators";
    public static final String API_USER_PREFS_PROVIDERS = API_BASE_ROUTE + "user/prefs/providers";
    public static final String API_USER_PROFILE = API_BASE_ROUTE + "user/profile";
    public static final String API_USER_GRADES = API_BASE_ROUTE + "user/grades";

    public static final String API_COMPETENCY_MATRIX = API_BASE_ROUTE + "tx/competency/matrix";
    public static final String API_COMPETENCY_SUBJECTS = API_BASE_ROUTE + "tx/subjects";
    public static final String API_COURSE_COMPETENCY_MATRIX =
        API_BASE_ROUTE + "tx/competency/matrix/course";
    public static final String API_DOMAIN_COMPETENCY_MATRIX =
        API_BASE_ROUTE + "tx/competency/matrix/domain";
    // Mukul
    public static final String API_SKYLINE_COMPETENCY_NEXT = API_BASE_ROUTE + "tx/competency/next";
    public static final String API_COMPETENCY_MATRIX_COORDS =
        API_BASE_ROUTE + "tx/competency/matrix/coordinates";

    public static final String API_GRADES = API_BASE_ROUTE + "tx/grades";
    public static final String API_GRADE_BOUNDARY = API_BASE_ROUTE + "tx/grade/boundary/:gradeId";
    public static final String API_GRADE_COMPETENCIES = API_BASE_ROUTE + "tx/grade/competency";
    public static final String API_USER_SUBJECT_COMPETENCY_MATRIX =
        API_BASE_ROUTE + "tx/subjects/competency/matrix";
    public static final String API_LEARNER_VECTORS = API_BASE_ROUTE + "learner/vectors";
    public static final String API_LEARNER_PREFS = API_BASE_ROUTE + "learner/prefs";
    public static final String API_LEARNER_PORTFOLIO_STATS = API_BASE_ROUTE + "learner/portfolio/stats";
    public static final String API_LEARNER_PORTFOLIO_STATS_SUBJECT = API_BASE_ROUTE + "learner/portfolio/stats/subject";
    public static final String API_LEARNER_PORTFOLIO_STATS_SUBJECT_DOMAIN = API_BASE_ROUTE + "learner/portfolio/stats/subject/domain";

    // Initial Learner Profile Setup
    // public static final String API_INTERNAL_INITIAL_LEARNER_PROFILE = "/api/internal/lp/initial";
    public static final String API_INTERNAL_BASE_LEARNER_PROFILE = "/api/internal/lp/baseline";
    // Navigator Course ATC
    public static final String API_NC_PERF_VS_COMPLETION = API_BASE_ROUTE + "nc/atc/pvc";
    // public static final String API_ATC = API_BASE_ROUTE + "nc/atc/recompute";
    public static final String API_STATS_COMPETENCY_COMPLETION =
        API_BASE_ROUTE + "stats/competency";
    // User course competency report - domain level summary
    // http://staging.gooru.org/api/ds/users/v2/user/competency/report/course
    public static final String API_USER_COURSE_COMPETENCY_REPORT =
        API_BASE_ROUTE + "user/competency/report/course";
    // Baseline learner profile
    // http://staging.gooru.org/api/ds/users/v2/user/baseline/learnerprofile
    public static final String API_USER_BASELINE_LEARNER_PROFILE =
        API_BASE_ROUTE + "user/baseline/learnerprofile";
    // Competency Summary for a Student (inProgress, completed, notStarted)
    public static final String API_USER_COMPETENCY_SUMMARY =
        API_BASE_ROUTE + "user/competency/summary";

    // Domain Reports APIs
    public static final String ID_CLASS = "classId";
    public static final String CODE_DOMAIN = "domainCode";

    // http://{host}/api/ds/users/{version}/classes/reports/domains?agent={agent}
    public static final String API_DOMAIN_REPORT = API_BASE_ROUTE + "classes/reports/domains";

    // http://{host}/api/ds/users/{version}/classes/reports/domains/competencies/performance?tx_code={txCode}&agent={agent}
    public static final String API_DOMAIN_COMPETENCIES_PERFORMANCE_REPORT =
        API_BASE_ROUTE + "classes/reports/domains/competencies/performance";
    // Portfolio
    // http://{host}/api/ds/users/v2/content/portfolio/items
    public static final String API_USER_PORTFOLIO_CONTENT_ITEMS_PERF =
        API_BASE_ROUTE + "content/portfolio/items";
    // http://{host}/api/ds/users/v2/content/portfolio/item
    public static final String API_USER_PORTFOLIO_CONTENT_ITEM_PERF =
        API_BASE_ROUTE + "content/portfolio/item";
    // http://{host}/api/ds/users/v2/content/portfolio/assessment/summary
    public static final String API_USER_PORTFOLIO_CONTENT_ASMT_SUMMARY =
        API_BASE_ROUTE + "content/portfolio/assessment/summary";
    // http://{host}/api/ds/users/v2/content/portfolio/collection/summary
    public static final String API_USER_PORTFOLIO_CONTENT_COLL_SUMMARY =
        API_BASE_ROUTE + "content/portfolio/collection/summary";
    // http://{host}/api/ds/users/v2/content/portfolio/oa/summary
    public static final String API_USER_PORTFOLIO_CONTENT_OA_SUMMARY =
        API_BASE_ROUTE + "content/portfolio/oa/summary";
    // http://{host}/api/ds/users/v2/competency/portfolio/items
    public static final String API_USER_PORTFOLIO_COMPETENCY =
        API_BASE_ROUTE + "competency/portfolio/items";
    // http://{host}/api/ds/users/v2/domain/portfolio/items
    public static final String API_USER_PORTFOLIO_DOMAIN =
        API_BASE_ROUTE + "domain/portfolio/items";
    // http://{host}/api/ds/users/v2/subject/portfolio/items
    public static final String API_USER_PORTFOLIO_SUBJECT =
        API_BASE_ROUTE + "subject/portfolio/items";

    public static final String API_STRUGGLING_COMPETENCIES =
        API_BASE_ROUTE + "competencies/struggling";

    public static final String API_STRUGGLING_COMPETENCY_PERF =
        API_BASE_ROUTE + "competencies/struggling/performance";

    // User Activity Feedbacks
    // http://{host}/api/ds/users/v2/activity/feedbacks
    public static final String API_USER_ACTIVITY_FEEDBACKS_CREATE =
        API_BASE_ROUTE + "activity/feedbacks";
    // http://{host}/api/ds/users/v2/activity/feedbacks?content_id=&user_id=
    public static final String API_USER_ACTIVITY_FEEDBACKS_FETCH =
        API_BASE_ROUTE + "activity/feedbacks";

    private Route() {
      throw new AssertionError();
    }
  }

  private Constants() {
    throw new AssertionError();
  }
}
