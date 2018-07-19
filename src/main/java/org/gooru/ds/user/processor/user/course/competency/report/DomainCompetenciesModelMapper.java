package org.gooru.ds.user.processor.user.course.competency.report;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.gooru.ds.user.processor.user.course.competency.report.UserCourseCompetencyReportModelMapper.MapperFields;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru on 18-Jul-2018
 */
public class DomainCompetenciesModelMapper implements ResultSetMapper<DomainCompetenciesModel> {

	@Override
	public DomainCompetenciesModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		DomainCompetenciesModel model = new DomainCompetenciesModel();
		model.setDomainCode(r.getString(MapperFields.DOMAIN_CODE));
		model.setDomainName(r.getString(MapperFields.DOMAIN_NAME));
		model.setDomainSeq(r.getInt(MapperFields.DOMAIN_SEQ));
        model.setCompetencyCode(r.getString(MapperFields.COMPETENCY_CODE));
        model.setCompetencyName(r.getString(MapperFields.COMPETENCY_NAME));
        model.setCompetencyDesc(r.getString(MapperFields.COMPETENCY_DESC));
        model.setCompetencyStudentDesc(r.getString(MapperFields.COMPETENCY_STUDENT_DESC));
        model.setCompetencySeq(r.getInt(MapperFields.COMPETENCY_SEQ));
		return model;
	}

}
