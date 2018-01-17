package org.gooru.ds.user.processor.usergrades;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 17/1/18.
 */
interface UserGradesDao {

    @Mapper(UserGradesModelMapper.class)
    @SqlQuery("select subject_code, subject_name, grade from user_grade_map where user_id = :user")
    List<UserGradesModel> fetchUserGrades(@BindBean UserGradesCommand.UserGradesCommandBean userGradesCommandBean);

}
