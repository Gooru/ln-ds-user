create table grade_master (
	id bigserial PRIMARY KEY,
	grade text NOT NULL,
	tx_subject_code text NOT NULL,
	grade_seq int NOT NULL,
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	UNIQUE (tx_subject_code, grade)
);

INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade K', 'K12.MA', 1);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 1', 'K12.MA', 2);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 2', 'K12.MA', 3);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 3', 'K12.MA', 4);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 4', 'K12.MA', 5);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 5', 'K12.MA', 6);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 6', 'K12.MA', 7);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 7', 'K12.MA', 8);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Grade 8', 'K12.MA', 9);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Algebra 1', 'K12.MA', 10);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Geometry', 'K12.MA', 11);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Algebra 2', 'K12.MA', 12);
INSERT INTO grade_master(grade, tx_subject_code, grade_seq) VALUES ('Pre-calculus', 'K12.MA', 13);


create table grade_competency_bound (
	id bigserial PRIMARY KEY,
	grade_id int references grade_master(id),
	tx_subject_code text NOT NULL,
	tx_domain_code text NOT NULL,
	highline_tx_comp_code text,
	lowline_tx_comp_code text,
	created_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	updated_at timestamp NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC'),
	UNIQUE (tx_subject_code, tx_domain_code, grade_id)
);

INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'CC', 'K12.MA-MAK-CC-C.02', 'K12.MA-MAK-CC-C.02');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'OAT', 'K12.MA-MAK-OAT-A.05', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'OAT', 'K12.MA-MA1-OAT-D.02', 'K12.MA-MAK-OAT-A.05');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'OAT', 'K12.MA-MA2-OAT-C.02', 'K12.MA-MA1-OAT-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'OAT', 'K12.MA-MA3-OAT-D.02', 'K12.MA-MA2-OAT-C.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'OAT', 'K12.MA-MA4-OAT-C.01', 'K12.MA-MA3-OAT-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA4-OAT-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'OAT', 'K12.MA-MA5-OAT-B.01', 'K12.MA-MA5-OAT-B.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'NOBT', 'K12.MA-MAK-NOBT-A.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'NOBT', 'K12.MA-MA1-NOBT-C.03', 'K12.MA-MAK-NOBT-A.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'NOBT', 'K12.MA-MA2-NOBT-B.05', 'K12.MA-MA1-NOBT-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'NOBT', 'K12.MA-MA3-NOBT-A.03', 'K12.MA-MA2-NOBT-B.05');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'NOBT', 'K12.MA-MA4-NOBT-B.03', 'K12.MA-MA3-NOBT-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA4-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NOBT', 'K12.MA-MA5-NOBT-B.03', 'K12.MA-MA5-NOBT-B.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'NOF', 'K12.MA-MA3-NOF-A.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'NOF', 'K12.MA-MA4-NOF-C.03', 'K12.MA-MA3-NOF-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA4-NOF-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NOF', 'K12.MA-MA5-NOF-B.05.03', 'K12.MA-MA5-NOF-B.05.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'MD', 'K12.MA-MAK-MD-B.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'MD', 'K12.MA-MA1-MD-C.01', 'K12.MA-MAK-MD-B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'MD', 'K12.MA-MA2-MD-D.02', 'K12.MA-MA1-MD-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'MD', 'K12.MA-MA3-MD-D.01', 'K12.MA-MA2-MD-D.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'MD', 'K12.MA-MA4-MD-C.03', 'K12.MA-MA3-MD-D.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA4-MD-C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'MD', 'K12.MA-MA5-MD-C.03.03', 'K12.MA-MA5-MD-C.03.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (1, 'K12.MA', 'G', 'K12.MA-MAK-G-B.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (2, 'K12.MA', 'G', 'K12.MA-MA1-G-A.03', 'K12.MA-MAK-G-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (3, 'K12.MA', 'G', 'K12.MA-MA2-G-A.03', 'K12.MA-MA1-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (4, 'K12.MA', 'G', 'K12.MA-MA3-G-A.02', 'K12.MA-MA2-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (5, 'K12.MA', 'G', 'K12.MA-MA4-G-A.03', 'K12.MA-MA3-G-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (6, 'K12.MA', 'G', 'K12.MA-MA5-G-B.02', 'K12.MA-MA4-G-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'G', 'K12.MA-MA6-G-A.04', 'K12.MA-MA5-G-B.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'G', 'K12.MA-MA7-G-B.03', 'K12.MA-MA6-G-A.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'G', 'K12.MA-MA8-G-C.01', 'K12.MA-MA7-G-B.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'G', 'K12.MA-MA8-G-C.01', 'K12.MA-MA8-G-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'G', 'K12.MA-GEO-G-MG.A.03', 'K12.MA-MA8-G-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'G', 'K12.MA-GEO-G-MG.A.03', 'K12.MA-GEO-G-MG.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'G', 'K12.MA-PC-G-GMD.A.02', 'K12.MA-GEO-G-MG.A.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'RPS', 'K12.MA-MA6-RPS-A.03.04', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA6-RPS-A.03.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'RPS', 'K12.MA-MA7-RPS-A.03', 'K12.MA-MA7-RPS-A.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'TNS', 'K12.MA-MA6-TNS-C.04', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'TNS', 'K12.MA-MA7-TNS-A.03', 'K12.MA-MA6-TNS-C.04');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA7-TNS-A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'TNS', 'K12.MA-MA8-TNS-A.02', 'K12.MA-MA8-TNS-A.02');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (7, 'K12.MA', 'EE', 'K12.MA-MA6-EE-C.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (8, 'K12.MA', 'EE', 'K12.MA-MA7-EE-B.02.02', 'K12.MA-MA6-EE-C.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (9, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA7-EE-B.02.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'EE', 'K12.MA-MA8-EE-C.02.03', 'K12.MA-MA8-EE-C.02.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'NQ', 'K12.MA-A1-NQ-Q.A.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'NQ', 'K12.MA-A1-NQ-Q.A.03', 'K12.MA-A1-NQ-Q.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'NQ', 'K12.MA-A2-NQ-CN.C.03', 'K12.MA-A1-NQ-Q.A.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'NQ', 'K12.MA-PC-NQ-VM.C.12', 'K12.MA-A2-NQ-CN.C.03');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'A', 'K12.MA-A1-A-REI.D.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'A', 'K12.MA-A1-A-REI.D.03', 'K12.MA-A1-A-REI.D.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'A', 'K12.MA-A2-A-REI.D.01', 'K12.MA-A1-A-REI.D.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'A', 'K12.MA-PC-A-REI.C.09', 'K12.MA-A2-A-REI.D.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'F', 'K12.MA-A1-F-LE.B.01', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'F', 'K12.MA-A1-F-LE.B.01', 'K12.MA-A1-F-LE.B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'F', 'K12.MA-A2-F-TF.C.01', 'K12.MA-A1-F-LE.B.01');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'F', 'K12.MA-PC-F-TF.B.07', 'K12.MA-A2-F-TF.C.01');


INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (10, 'K12.MA', 'SP', 'K12.MA-A1-SP-ID.C.03', null);
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (11, 'K12.MA', 'SP', 'K12.MA-GEO-SP-MD.B.02', 'K12.MA-A1-SP-ID.C.03');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (12, 'K12.MA', 'SP', 'K12.MA-A2-SP-MD.B.02', 'K12.MA-GEO-SP-MD.B.02');
INSERT INTO grade_competency_bound (grade_id, tx_subject_code, tx_domain_code, highline_tx_comp_code, lowline_tx_comp_code) VALUES (13, 'K12.MA', 'SP', 'K12.MA-PC-SP-MD.B.05', 'K12.MA-A2-SP-MD.B.02');
