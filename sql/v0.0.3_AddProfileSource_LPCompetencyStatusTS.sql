alter table learner_profile_competency_status_ts add column profile_source text;

update learner_profile_competency_status_ts set profile_source = lp.profile_source from learner_profile_competency_status lp where learner_profile_competency_status_ts.user_id = lp.user_id and learner_profile_competency_status_ts.gut_code = lp.gut_code and lp.profile_source is not null;
