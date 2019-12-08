package org.gooru.ds.user.processor.learnerprefs;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;


interface LearnerPrefsDao {

  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select sum(audio_pref) as audio_pref, sum(interactive_pref) as interactive_pref, sum(text_pref) as text_pref, sum(video_pref) as video_pref, sum(webpage_pref) as webpage_pref, "
      + "sum(image_pref) as image_pref from learner_prefs where user_id = :user and updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefs(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);

  @Mapper(LearnerPrefsModelMapper.class)
  @SqlQuery("select sum(audio_pref) as audio_pref, sum(interactive_pref) as interactive_pref, sum(text_pref) as text_pref, sum(video_pref) as video_pref, "
      + "sum(webpage_pref) as webpage_pref, sum(image_pref) as image_pref from learner_prefs where user_id = :user and tx_subject_code = :subject and updated_at < :toDate")
  LearnerPrefsModel fetchLearnerPrefsBySubject(
      @BindBean LearnerPrefsCommand.LearnerPrefsCommandBean learnerPrefsCommandBean);

}
