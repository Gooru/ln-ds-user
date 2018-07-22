CREATE TABLE grade_competency_profile (
	id bigserial PRIMARY KEY,
	tx_subject_code text NOT NULL,
	tx_grade text NOT NULL,
	gut_code text NOT NULL,
	status smallint NOT NULL DEFAULT 0 CHECK(status::smallint = ANY(ARRAY[0, 1, 2, 3, 4, 5])),
	profile_source text,
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC')	
);

CREATE TABLE baseline_learner_profile (
	id bigserial PRIMARY KEY,
	tx_subject_code text NOT NULL,
	class_id text,
	course_id text NOT NULL,
	user_id text NOT NULL,
	gut_code text NOT NULL,
	status smallint NOT NULL DEFAULT 0 CHECK(status::smallint = ANY(ARRAY[0, 1, 2, 3, 4, 5])),
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	UNIQUE(tx_subject_code, course_id, user_id, gut_code));

ALTER TABLE learner_profile_competency_status ADD Column profile_source text;

ALTER TABLE learner_profile_competency_status_ts ADD Column profile_source text;

_______________________________________________________________________________________________________________________


CREATE TABLE grade_competency_matrix (
    id bigserial PRIMARY KEY,
    tx_subject_code text NOT NULL,
    grade text NOT NULL,
    tx_domain_code text,
    tx_comp_code text NOT NULL,
    tx_comp_name text NOT NULL,
    tx_comp_desc text,
    tx_comp_seq smallint NOT NULL DEFAULT 0,
    grade_seq smallint NOT NULL DEFAULT 0,
    UNIQUE(tx_subject_code, grade, tx_comp_code)
);
