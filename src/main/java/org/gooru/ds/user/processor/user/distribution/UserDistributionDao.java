package org.gooru.ds.user.processor.user.distribution;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 10/1/18.
 */
interface UserDistributionDao {

    @Mapper(UserDistributionModelMapper.class)
    @SqlQuery("select code, name, total, active from user_distribution_zoom1 where duration = :activeDuration")
    List<UserDistributionModel> fetchUserDistributionAcrossGeoLocations(
        @BindBean UserDistributionCommand.UserDistributionCommandBean userDistributionCommandBean);

    @Mapper(UserDistributionModelMapper.class)
    @SqlQuery("select code, name, total, active from user_distribution_subject where duration = :activeDuration")
    List<UserDistributionModel> fetchUserDistributionAcrossSubjects(
        @BindBean UserDistributionCommand.UserDistributionCommandBean userDistributionCommandBean);

    @Mapper(UserDistributionModelMapper.class)
    @SqlQuery("select code, name, active, total from user_distribution_zoom1_subject where subject_code = :subject "
                  + " and duration = :activeDuration")
    List<UserDistributionModel> fetchUserDistributionBySubjectAcrossGeoLocations(
        @BindBean UserDistributionCommand.UserDistributionCommandBean userDistributionCommandBean);

    @Mapper(UserDistributionModelMapper.class)
    @SqlQuery("select subject_code as code, subject_name as name, active, total from user_distribution_zoom1_subject "
                  + "where code = :geoLocation and duration = :activeDuration")
    List<UserDistributionModel> fetchUserDistributionByGeoLocationAcrossSubjects(
        @BindBean UserDistributionCommand.UserDistributionCommandBean userDistributionCommandBean);
}
