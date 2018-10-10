package org.gooru.ds.user.processor.grade.master;

import java.util.List;

import org.gooru.ds.user.processor.grade.master.GradeMasterCommand.GradeMasterCommandBean;
import org.skife.jdbi.v2.DBI;

/**
 * @author szgooru on 21-Sep-2018
 */
public class GradeMasterService {

	private final GradeMasterDao dao;

	public GradeMasterService(DBI dbi) {
		this.dao = dbi.onDemand(GradeMasterDao.class);
	}

	public GradeMasterModelResponse fetchGrades(GradeMasterCommand command) {
		GradeMasterModelResponse response = new GradeMasterModelResponse();
		GradeMasterCommandBean bean = command.asBean();

		List<GradeMasterModel> grades = this.dao.fetchGrades(bean.getSubject());
		response.setGrades(grades);
		return response;
	}
}
