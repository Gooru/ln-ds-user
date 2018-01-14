package org.gooru.ds.user.processor.userprefs.content;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/1/18.
 */
public class UserPrefsContentModelMapper implements ResultSetMapper<UserPrefsContentModel> {

    @Override
    public UserPrefsContentModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPrefsContentModel model = new UserPrefsContentModel();
        model.setAudio(r.getFloat(MapperFields.AUDIO));
        model.setInteractive(r.getFloat(MapperFields.INTERACTIVE));
        model.setText(r.getFloat(MapperFields.TEXT));
        model.setVideo(r.getFloat(MapperFields.VIDEO));
        model.setWebpage(r.getFloat(MapperFields.WEBPAGE));
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
        private static final String TEXT = "textual";
    }

}
