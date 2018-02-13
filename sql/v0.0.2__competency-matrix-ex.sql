-- drop table domain_competency_matrix
-- drop table course_competency_matrix

CREATE TABLE domain_competency_matrix (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    tx_domain_code text NOT NULL,
    tx_comp_code text NOT NULL,
    tx_comp_name text NOT NULL,
    tx_comp_desc text,
    tx_comp_student_desc text,
    tx_comp_seq smallint NOT NULL DEFAULT 0,
    UNIQUE(tx_subject_code, tx_domain_code, tx_comp_code)
);

CREATE TABLE course_competency_matrix (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    tx_course_code text NOT NULL,
    tx_comp_code text NOT NULL,
    tx_comp_name text NOT NULL,
    tx_comp_desc text,
    tx_comp_student_desc text,
    tx_comp_seq smallint NOT NULL DEFAULT 0,
    UNIQUE(tx_subject_code, tx_course_code, tx_comp_code)
);
