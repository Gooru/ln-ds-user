-- This is the WIP script to create db tables 
-- Once we have baselined it, we can move it to baseline script which could be run with flyway

-- drop table user_distribution_zoom1
-- drop table user_distribution_subject
-- drop table user_distribution_zoom1_subject

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
