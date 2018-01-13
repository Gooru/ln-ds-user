package org.gooru.ds.user.processor.userprefs.curators;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 13/1/18.
 */
public class UserPrefsCuratorModelMapper implements ResultSetMapper<UserPrefsCuratorModel> {

    @Override
    public UserPrefsCuratorModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserPrefsCuratorModel model = new UserPrefsCuratorModel();
        model.setCuratorId(r.getLong(MapperFields.ID));
        model.setCuratorName(r.getString(MapperFields.CURATOR_NAME));
        model.setPref(r.getFloat(MapperFields.PREF));
        return model;
    }

    private static final class MapperFields {

        private MapperFields() {
            throw new AssertionError();
        }

        private static final String ID = "id";
        private static final String CURATOR_NAME = "curator_name";
        private static final String PREF = "pref";
    }

}
