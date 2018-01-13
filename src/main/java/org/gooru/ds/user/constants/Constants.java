package org.gooru.ds.user.constants;

/**
 * @author ashish on 10/1/18.
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
        public static final String MSG_OP_USER_PREFS_CONTENT = "mb.op.user.prefs.content";
        public static final String MSG_OP_USER_PREFS_CURATORS = "mb.op.user.prefs.curators";
        public static final String MSG_OP_USER_PREFS_PROVIDERS = "mb.op.user.prefs.providers";

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

        private Params() {
            throw new AssertionError();
        }
    }

    public static final class Route {

        public static final String API_AUTH_ROUTE = "/api/ds/users/*";
        public static final String API_INTERNAL_BANNER = "/api/internal/banner";
        public static final String API_INTERNAL_METRICS = "/api/internal/metrics";
        private static final String API_BASE_ROUTE = "/api/ds/users/:version/";
        public static final String API_USER_DISTRIBUTION = API_BASE_ROUTE + "user/distribution";
        public static final String API_ACTIVE_USER_LIST = API_BASE_ROUTE + "user/distribution/active";
        public static final String API_USER_STATS_JOURNEYS = API_BASE_ROUTE + "user/stats/journeys";
        public static final String API_USER_STATS_TIMESPENT = API_BASE_ROUTE + "user/stats/timespent";
        public static final String API_USER_STATS_COMPETENCY = API_BASE_ROUTE + "user/stats/competency";
        public static final String API_USER_STATS_COURSES = API_BASE_ROUTE + "user/stats/courses";
        public static final String API_USER_PREFS_CONTENT = API_BASE_ROUTE + "user/prefs/content";
        public static final String API_USER_PREFS_CURATORS = API_BASE_ROUTE + "user/prefs/curators";
        public static final String API_USER_PREFS_PROVIDERS = API_BASE_ROUTE + "user/prefs/providers";

        private Route() {
            throw new AssertionError();
        }
    }

    private Constants() {
        throw new AssertionError();
    }
}
