package org.gooru.ds.user.processor.grade.boundary;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author szgooru on 19-Sep-2018
 */
public class GradeBoundaryListModelMapper implements ResultSetMapper<GradeBoundaryListModel> {

	@Override
	public GradeBoundaryListModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
		GradeBoundaryListModel model = new GradeBoundaryListModel();
		model.setDomainCode(r.getString("tx_domain_code"));
		model.setHighline(r.getString("highline_tx_comp_code"));
		return model;
	}
	
}
