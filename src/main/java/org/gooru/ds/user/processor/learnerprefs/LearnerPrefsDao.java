package org.gooru.ds.user.processor.learnerprefs;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerPrefsDao {

  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select avg(audio_pref/1000::real) as audio_pref, avg(interactive_pref/1000::real) as interactive_pref, avg(text_pref/1000::real) as text_pref, avg(video_pref/1000::real) as video_pref, avg(webpage_pref/1000::real) as webpage_pref, "
      + "avg(image_pref/1000::real) as image_pref, avg(project_pref/1000::real) as project_pref  from learner_prefs where user_id = :user and updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefs(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);

  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select avg(audio_pref/1000::real) as audio_pref, avg(interactive_pref/1000::real) as interactive_pref, avg(text_pref/1000::real) as text_pref, avg(video_pref/1000::real) as video_pref, "
      + "avg(webpage_pref/1000::real) as webpage_pref, avg(image_pref/1000::real) as image_pref, avg(project_pref/1000::real) as project_pref from learner_prefs where user_id = :user and tx_subject_code = :subject and updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefsBySubject(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);

  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select avg(audio_pref/1000::real) as audio_pref, avg(interactive_pref/1000::real) as interactive_pref, avg(text_pref/1000::real) as text_pref, avg(video_pref/1000::real) as video_pref, "
      + "avg(webpage_pref/1000::real) as webpage_pref, avg(image_pref/1000::real) as image_pref, avg(project_pref/1000::real) as project_pref from learner_prefs where user_id = :user and tx_subject_code = :subject and "
      + "tx_domain_code = :domain and  updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefsBySubjectDomain(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);
  
  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select avg(audio_pref/1000::real) as audio_pref, avg(interactive_pref/1000::real) as interactive_pref, avg(text_pref/1000::real) as text_pref, avg(video_pref/1000::real) as video_pref, "
      + "avg(webpage_pref/1000::real) as webpage_pref, avg(image_pref/1000::real) as image_pref, avg(project_pref/1000::real) as project_pref from learner_prefs where user_id = :user and tx_subject_code = :subject and "
      + "tx_domain_code = :domain and tx_comp_code = :compCode and  updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefsBySubjectDomainComp(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);
  
}
