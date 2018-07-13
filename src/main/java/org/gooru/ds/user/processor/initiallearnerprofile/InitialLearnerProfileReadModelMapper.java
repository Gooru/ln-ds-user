package org.gooru.ds.user.processor.initiallearnerprofile;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;


/**
 * @author mukul@gooru
 * 
 */
public class InitialLearnerProfileReadModelMapper implements ResultSetMapper<InitialLearnerProfileReadModel> {
	
    @Override
    public InitialLearnerProfileReadModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    	InitialLearnerProfileReadModel model = new InitialLearnerProfileReadModel();
        model.setSubjectCode(r.getString(MapperFields.TX_SUBJECT_CODE));
        model.setGrade(r.getString(MapperFields.TX_GRADE));
        model.setStatus(r.getInt(MapperFields.STATUS));
        model.setCompetencyCode(r.getString(MapperFields.GUT_CODE));
        model.setProfileSource(r.getString(MapperFields.PROFILE_SOURCE));
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String TX_SUBJECT_CODE = "tx_subject_code";
        private static final String TX_GRADE = "tx_grade";
        private static final String STATUS = "status";

        private static final String GUT_CODE = "gut_code";
        private static final String PROFILE_SOURCE= "profile_source";
        
    }



}
