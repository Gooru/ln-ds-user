package org.gooru.ds.user.processor.competencymatrixcoordinates;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

/**
 * @author ashish on 17/1/18.
 */
interface CompetencyMatrixCoordinatesDao {

    @Mapper(CompetencyMatrixCoordinatesCourseModelMapper.class)
    @SqlQuery("select tx_course_code, tx_course_name, tx_course_seq from tx_courses where tx_subject_code = :subject "
                  + "order by tx_course_seq asc")
    List<CompetencyMatrixCoordinatesCourseModel> fetchCompetencyMatrixCoordinatesCourses(@BindBean
        CompetencyMatrixCoordinatesCommand.CompetencyMatrixCoordinatesCommandBean
        competencyMatrixCoordinatesCommandBean);

    @Mapper(CompetencyMatrixCoordinatesDomainModelMapper.class)
    @SqlQuery("select tx_domain_code, tx_domain_name, tx_domain_seq from tx_domains where tx_subject_code = :subject "
                  + "order by tx_domain_seq asc")
    List<CompetencyMatrixCoordinatesDomainModel> fetchCompetencyMatrixCoordinatesDomains(@BindBean
        CompetencyMatrixCoordinatesCommand.CompetencyMatrixCoordinatesCommandBean
        competencyMatrixCoordinatesCommandBean);

}
