package org.gooru.ds.user.processor.userstats.timespent;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 12/1/18.
 */
public class UserStatsTimespentModelMapper implements ResultSetMapper<UserStatsTimespentModel> {

    @Override
    public UserStatsTimespentModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserStatsTimespentModel model = new UserStatsTimespentModel();
        model.setAudio(r.getLong(MapperFields.AUDIO));
        model.setInteractive(r.getLong(MapperFields.INTERACTIVE));
        model.setText(r.getLong(MapperFields.TEXT));
        model.setVideo(r.getLong(MapperFields.VIDEO));
        model.setWebpage(r.getLong(MapperFields.WEBPAGE));
        model.setImage(r.getLong(MapperFields.IMAGE));
        return model;
    }

    private static final class MapperFields {

        private MapperFields() {
            throw new AssertionError();
        }

        private static final String INTERACTIVE = "interactive";
        private static final String AUDIO = "audio";
        private static final String WEBPAGE = "webpage";
        private static final String VIDEO = "video";
        private static final String TEXT = "text";
        private static final String IMAGE = "image";
    }

}
