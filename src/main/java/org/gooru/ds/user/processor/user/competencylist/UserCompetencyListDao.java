package org.gooru.ds.user.processor.user.competencylist;

import java.util.List;
import org.gooru.ds.user.processor.user.competencylist.UserCompetencyListCommand;
import org.gooru.ds.user.processor.user.competencylist.UserCompetencyListModel;
import org.gooru.ds.user.processor.user.competencylist.UserCompetencyListModelMapper;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

interface UserCompetencyListDao {

  @Mapper(UserCompetencyListModelMapper.class)
  @SqlQuery("select competency_code, competency_display_code, competency_title, framework_code, status from "
      + " competency_status where user_id = :user and duration = :activeDuration"
      + " order by updated_at offset :offset limit :limit")
  List<UserCompetencyListModel> fetchUserCompetencyList(
      @BindBean UserCompetencyListCommand.UserCompetencyListCommandBean bean);

}
