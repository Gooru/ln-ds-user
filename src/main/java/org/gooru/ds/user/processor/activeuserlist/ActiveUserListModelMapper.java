package org.gooru.ds.user.processor.activeuserlist;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 11/1/18.
 */
public class ActiveUserListModelMapper implements ResultSetMapper<ActiveUserListModel> {

    @Override
    public ActiveUserListModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        ActiveUserListModel model = new ActiveUserListModel();
        model.setUserId(r.getString(MapperFields.USER_ID));
        model.setUsername(r.getString(MapperFields.USERNAME));
        model.setFirstname(r.getString(MapperFields.FIRSTNAME));
        model.setLastname(r.getString(MapperFields.LASTNAME));
        model.setThumbnail(r.getString(MapperFields.THUMBNAIL));
        model.setGrade(r.getString(MapperFields.GRADE));
        model.setAuthority(r.getFloat(MapperFields.AUTHORITY));
        model.setCitizenship(r.getFloat(MapperFields.CITIZENSHIP));
        model.setReputation(r.getFloat(MapperFields.REPUTATION));
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String USER_ID = "user_id";
        private static final String USERNAME = "username";
        private static final String FIRSTNAME = "firstname";
        private static final String LASTNAME = "lastname";
        private static final String THUMBNAIL = "thumbnail";
        private static final String GRADE = "grade";
        private static final String AUTHORITY = "authority";
        private static final String CITIZENSHIP = "citizenship";
        private static final String REPUTATION = "reputation";
    }

}
