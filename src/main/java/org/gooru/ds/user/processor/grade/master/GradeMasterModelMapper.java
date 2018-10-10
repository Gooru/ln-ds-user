package org.gooru.ds.user.processor.grade.master;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru on 21-Sep-2018
 */
public class GradeMasterModelMapper implements ResultSetMapper<GradeMasterModel> {

	@Override
	public GradeMasterModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		GradeMasterModel model = new GradeMasterModel();
		model.setId(r.getInt("id"));
		model.setGrade(r.getString("grade"));
		model.setDescription(r.getString("description"));
		model.setThumbnail(r.getString("thumbnail"));
		model.setSequence(r.getInt("grade_seq"));
		return model;
	}

}
