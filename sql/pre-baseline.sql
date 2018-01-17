-- This is the WIP script to create db tables 
-- Once we have baselined it, we can move it to baseline script which could be run with flyway

-- drop table user_distribution_zoom1
-- drop table user_distribution_subject
-- drop table user_distribution_zoom1_subject
-- drop table active_user_stats
-- drop table user_profile_master
-- drop table user_grade_map
-- drop table ds_master
-- drop table resource_timespent
-- drop table course_collection_performance
-- drop table overall_course_performance
-- drop table user_courses
-- drop table course_performance
-- drop table user_stats_journeys
-- drop table user_stats_competency
-- drop table user_stats_timespent
-- drop table user_prefs_content
-- drop table user_prefs_provider
-- drop table user_prefs_curator
-- drop table user_stats_content
-- drop table user_stats_provider
-- drop table user_stats_curator

CREATE TABLE user_distribution_zoom1 (
    id bigserial PRIMARY KEY,
    code character varying(512) NOT NULL,
    duration character varying(512) NOT NULL,
    name character varying(512) NOT NULL,
    total bigint NOT NULL DEFAULT 0,
    active bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE (code, duration)
);

CREATE TABLE user_distribution_subject (
    id bigserial PRIMARY KEY,
    code character varying(512) NOT NULL UNIQUE,
    duration character varying(512) NOT NULL,
    name character varying(512) NOT NULL,
    total bigint NOT NULL DEFAULT 0,
    active bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE (code, duration)
);

CREATE TABLE user_distribution_zoom1_subject (
    id bigserial PRIMARY KEY,
    code character varying(512) NOT NULL,
    duration character varying(512) NOT NULL,
    name character varying(512) NOT NULL,
    subject_code character varying(512) NOT NULL,
    subject_name character varying(512) NOT NULL,
    total bigint NOT NULL DEFAULT 0,
    active bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE (code, subject_code, duration)
);

CREATE TABLE active_user_stats (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    duration character varying(512) NOT NULL,
    subject_code character varying(512) NOT NULL DEFAULT 'NONE',
    subject_name character varying(512) NOT NULL DEFAULT 'NONE',
    last_activity_date timestamp without time zone NOT NULL,
    activity_count bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE (user_id, subject_code, duration)
);

CREATE TABLE users_profile_master (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL UNIQUE,
    username text,
    reference_id text,
    email text,
    login_type text,
    first_name text,
    last_name text,
    parent_user_id text,
    user_category text,
    roles text,
    birth_date date,
    grade text,
    course text,
    thumbnail text,
    gender text,
    about text,
    school_id text,
    school text,
    school_district_id text,
    school_district text,
    country_id text,
    country text,
    state_id text,
    state text,
    metadata text,
    roster_id text,
    roster_global_userid text,
    tenant_root text,
    tenant_id text,
    partner_id text,
    display_name text,
    is_deleted boolean,
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL
);

CREATE TABLE user_grade_map (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    subject_code text NOT NULL,
    subject_name text NOT NULL,
    grade text,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,    
    UNIQUE (user_id, subject_code)
);


-- NOTE : the vector values are stored as integer and not real or decimal because of they being inexact
-- We need to pick up a integer spread and then scale it down when it is returned by any API
-- We shall take default spread as 1000 that is to say, table will store values from 0 to 1000
CREATE TABLE user_vectors (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL UNIQUE,
    authority integer,
    citizenship integer,
    reputation integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL
);


CREATE TABLE user_stats_competency (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    duration character varying(512) NOT NULL,
    in_progress integer,
    completed integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, duration)
);

CREATE TABLE user_stats_journeys (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    duration character varying(512) NOT NULL,
    independent_journeys integer,
    class_journeys integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, duration)
);

CREATE TABLE user_stats_timespent (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    duration character varying(512) NOT NULL,
    audio bigint,
    interactive bigint,
    webpage bigint,
    text bigint,
    video bigint,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, duration)
);

CREATE TABLE user_stats_courses (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    duration character varying(512) NOT NULL,
    course_id character varying(512) NOT NULL,
    class_id character varying(512),
    completion  numeric(5,2),
    performance numeric(5,2) ,
    timespent bigint,
    started_in_duration boolean,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL
);

CREATE UNIQUE INDEX on user_stats_courses (user_id, duration, course_id, coalesce(class_id, 'NONE'));

-- Note that prefs values are stored as INT which are scaled to 1000
-- API should scale it down between 0 and 1 before returning it
CREATE TABLE user_prefs_content (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL UNIQUE,
    audio integer,
    interactive integer,
    webpage integer,
    text integer,
    video integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL
);

-- Note that prefs values are stored as INT which are scaled to 1000
-- API should scale it down between 0 and 1 before returning it
CREATE TABLE user_prefs_curator (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    curator_id bigint NOT NULL,
    pref integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, curator_id)
);

-- Note that prefs values are stored as INT which are scaled to 1000
-- API should scale it down between 0 and 1 before returning it
CREATE TABLE user_prefs_provider (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    provider_id bigint NOT NULL,
    pref integer,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, provider_id)
);

CREATE TABLE curators (
    id bigserial PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE providers (
    id bigserial PRIMARY KEY,
    name text NOT NULL UNIQUE
);

CREATE TABLE user_stats_content (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL UNIQUE,
    duration character varying(512) NOT NULL,
    audio bigint,
    interactive bigint,
    webpage bigint,
    text bigint,
    video bigint,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, duration)
);

CREATE TABLE user_stats_curator (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    curator_id bigint NOT NULL,
    duration character varying(512) NOT NULL,
    counter bigint,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, curator_id, duration)
);

CREATE TABLE user_stats_provider (
    id bigserial PRIMARY KEY,
    user_id text NOT NULL,
    provider_id bigint NOT NULL,
    duration character varying(512) NOT NULL,
    counter bigint,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(user_id, provider_id, duration)
);

-- This is the master data table that forms the leaf level data housing 
-- Aggregated Tables will be created/derived from this master table.
CREATE TABLE ds_master (
id BIGSERIAL PRIMARY KEY,
event_name varchar(36),
event_type varchar(36),
tenant_id varchar(36),
actor_id varchar(36),
class_id varchar(36),
class_title varchar(100),
class_code varchar(7),
course_id varchar(36),
course_title varchar(100),
unit_id varchar(36),
unit_title varchar(100),
lesson_id varchar(36),
lesson_title varchar(100),
collection_id varchar(36),
collection_title varchar(36),
session_id varchar(36),
question_count smallint,
collection_type varchar(12),
resource_type varchar(12),
resource__content_type varchar(20),
question_type varchar(36),
answer_object text,
resource_id varchar(36),
resource_title varchar(100),
time_spent bigint,
views integer,
reaction smallint,
resource_attempt_status varchar(10),
score numeric(5,2),
max_score numeric(5,2),
grading_type varchar(10),
app_id varchar,
partner_id varchar(36),
collection_sub_type varchar(36),
path_id bigint,
event_id varchar(36),
time_zone varchar(50),
content_source varchar(36),
date_in_time_zone date,
is_graded boolean,
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));


-- Resource TimeSpent based on the resource_content_type
CREATE TABLE resource_timespent (
id SERIAL PRIMARY KEY,
user_id varchar(36),
resource__content_type varchar(20),
resource_id varchar(36),
resource_title varchar(100),
resource_time_spent bigint,
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));


CREATE TABLE overall_course_performance (
id SERIAL PRIMARY KEY,
user_id varchar(36),
class_id varchar(36),
class_title varchar(100),
class_code varchar(7),
course_id varchar(36),
course_title varchar(100),
assessments_completed bigint,
total_assessments bigint,
time_spent bigint,
average_score numeric(5,2),
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));

CREATE TABLE course_performance (
id SERIAL PRIMARY KEY,
user_id varchar(36),
class_id varchar(36),
class_title varchar(100),
class_code varchar(7),
course_id varchar(36),
course_title varchar(100),
course_asmt_time_spent bigint,
course_asmt_score numeric(5,2),
course_coll_time_spent bigint,
course_coll_score numeric(5,2),
unit_id varchar(36),
unit_title varchar(100),
unit_sequence_id smallint,
unit_asmt_time_spent bigint,
unit_asmt_score numeric(5,2),
unit_coll_time_spent bigint,
unit_coll_score numeric(5,2),
lesson_id varchar(36),
lesson_title varchar(100),
lesson_sequence_id smallint,
lesson_asmt_time_spent bigint,
lesson_asmt_score numeric(5,2),
lesson_coll_time_spent bigint,
lesson_coll_score numeric(5,2),
course_assessments_complete smallint,
total_assessments smallint,
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));


CREATE TABLE course_collection_performance (
id SERIAL PRIMARY KEY,
user_id varchar(36),
class_id varchar(36),
class_title varchar(100),
class_code varchar(7),
course_id varchar(36),
course_title varchar(100),
unit_id varchar(36),
unit_title varchar(100),
lesson_id varchar(36),
lesson_title varchar(100),
latest_session_id varchar(36),
collection_id varchar(36),
collection_title varchar(100),
collection_sequence_id smallint,
collection_path_id bigint,
collection_time_spent bigint,
collection_score numeric(5,2),
collection_average_reaction smallint,
collection_num_attempts smallint,
collection_type varchar(12),
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));

CREATE TABLE user_courses (
id BIGSERIAL PRIMARY KEY,
user_id varchar(36),
in_class_course_id varchar(36),
in_class_course_title varchar(100),
class_code varchar(7),
il_course_id varchar(36),
il_course_title varchar(100),
created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'));

