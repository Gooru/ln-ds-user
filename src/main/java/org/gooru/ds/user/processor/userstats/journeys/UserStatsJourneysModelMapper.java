package org.gooru.ds.user.processor.userstats.journeys;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 12/1/18.
 */
public class UserStatsJourneysModelMapper implements ResultSetMapper<UserStatsJourneysModel> {

    @Override
    public UserStatsJourneysModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserStatsJourneysModel model = new UserStatsJourneysModel();
        model.setClassJourneys(r.getInt(MapperFields.CLASS_JOURNEYS));
        model.setIndependentJourneys(r.getInt(MapperFields.INDEPENDENT_JOURNEYS));
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String INDEPENDENT_JOURNEYS = "independent_journeys";
        private static final String CLASS_JOURNEYS = "class_journeys";
    }

}
