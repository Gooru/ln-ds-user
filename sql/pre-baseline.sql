-- This is the WIP script to create db tables 
-- Once we have baselined it, we can move it to baseline script which could be run with flyway

-- drop table user_distribution_zoom1
-- drop table user_distribution_zoom1_subject

CREATE TABLE user_distribution_zoom1 (
    id bigserial PRIMARY KEY,
    code character varying(512) NOT NULL UNIQUE,
    total_count bigint NOT NULL DEFAULT 0,
    active_count bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL
);

CREATE TABLE user_distribution_zoom1_subject (
    id bigserial PRIMARY KEY,
    code character varying(512) NOT NULL,
    subject_code character varying(512) NOT NULL,
    subject_name character varying(512) NOT NULL,
    total_count bigint NOT NULL DEFAULT 0,
    active_count bigint NOT NULL DEFAULT 0,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE (code, subject_code)
);
