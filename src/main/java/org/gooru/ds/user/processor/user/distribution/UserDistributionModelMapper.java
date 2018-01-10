package org.gooru.ds.user.processor.user.distribution;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 10/1/18.
 */
public class UserDistributionModelMapper implements ResultSetMapper<UserDistributionModel> {

    @Override
    public UserDistributionModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
        UserDistributionModel model = new UserDistributionModel();
        model.setCode(r.getString(MapperFields.CODE));
        model.setName(r.getString(MapperFields.NAME));
        model.setTotal(r.getLong(MapperFields.TOTAL));
        model.setActive(r.getLong(MapperFields.ACTIVE));
        return model;
    }

    private static final class MapperFields {
        private MapperFields() {
            throw new AssertionError();
        }

        private static final String NAME = "name";
        private static final String CODE = "code";
        private static final String ACTIVE = "active";
        private static final String TOTAL = "total";
    }
}
