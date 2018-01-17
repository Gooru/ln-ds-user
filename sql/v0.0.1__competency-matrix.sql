-- drop table competency_matrix
-- drop table user_competency_matrix

CREATE TABLE tx_courses (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    tx_course_code text NOT NULL,
    tx_course_name text NOT NULL,
    tx_course_seq int NOT NULL,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(tx_subject_code, tx_course_code)
);

CREATE TABLE tx_domains (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    tx_domain_code text NOT NULL,
    tx_domain_name text NOT NULL,
    tx_domain_seq int NOT NULL,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(tx_subject_code, tx_domain_code)
);


CREATE TABLE competency_matrix (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    tx_course_code text NOT NULL,
    tx_domain_code text NOT NULL,
    tx_comp_code text NOT NULL,
    tx_comp_name text NOT NULL,
    tx_comp_desc text,
    tx_comp_student_desc text,
    tx_comp_seq int NOT NULL,
    updated_at timestamp without time zone DEFAULT timezone('UTC'::text, now()) NOT NULL,
    UNIQUE(tx_subject_code, tx_course_code, tx_domain_code, tx_comp_code)
);

CREATE TABLE user_competency_matrix (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    user_id text NOT NULL,
    tx_comp_code text NOT NULL,
    status smallint NOT NULL DEFAULT 0 CHECK(status::smallint = ANY(ARRAY[0, 1, 2, 3, 4, 5])),
    UNIQUE(tx_subject_code, user_id, tx_comp_code)
);

