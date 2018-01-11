-- This is the WIP script to create db tables 
-- Once we have baselined it, we can move it to baseline script which could be run with flyway

-- drop table user_distribution_zoom1
-- drop table user_distribution_subject
-- drop table user_distribution_zoom1_subject
-- drop table active_user_stats
-- drop table user_profile_master
-- drop table user_grade_map

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
    id bigint PRIMARY KEY,
    user_id text NOT NULL UNIQUE,
    username text,
    reference_id text,
    email_id text,
    client_id text,
    login_type text,
    provision_type text,
    firstname text,
    lastname text,
    user_category text, 
    created_at timestamp without time zone NOT NULL,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    birth_date date, 
    thumbnail text, 
    gender text,
    about_me text,
    school text,
    school_district text,
    country text,
    state text,
    metadata text,
    roster_id text,
    roster_global_userid text
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
