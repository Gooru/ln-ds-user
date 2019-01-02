package org.gooru.ds.user.processor.userprofile;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

/**
 * @author ashish on 17/1/18.
 */
public class UserProfileModelMapper implements ResultSetMapper<UserProfileModel> {
  @Override
  public UserProfileModel map(int index, ResultSet r, StatementContext ctx) throws SQLException {
    UserProfileModel result = new UserProfileModel();
    result.setUserId(r.getString(MapperFields.USER_ID));
    result.setUsername(r.getString(MapperFields.USERNAME));
    result.setEmail(r.getString(MapperFields.EMAIL));
    result.setFirstName(r.getString(MapperFields.FIRST_NAME));
    result.setLastName(r.getString(MapperFields.LAST_NAME));
    result.setUserCategory(r.getString(MapperFields.USER_CATEGORY));
    result.setTenantId(r.getString(MapperFields.TENANT_ID));
    result.setThumbnail(r.getString(MapperFields.THUMBNAIL));
    result.setGender(r.getString(MapperFields.GENDER));
    result.setAbout(r.getString(MapperFields.ABOUT));
    result.setSchool(r.getString(MapperFields.SCHOOL));
    result.setSchoolDistrict(r.getString(MapperFields.SCHOOL_DISTRICT));
    result.setCountry(r.getString(MapperFields.COUNTRY));
    result.setState(r.getString(MapperFields.STATE));
    result.setAuthority(r.getFloat(MapperFields.AUTHORITY));
    result.setCitizenship(r.getFloat(MapperFields.CITIZENSHIP));
    result.setReputation(r.getFloat(MapperFields.REPUTATION));
    return result;
  }

  private static final class MapperFields {

    private MapperFields() {
      throw new AssertionError();
    }

    private static final String USER_ID = "user_id";
    private static final String USERNAME = "username";
    private static final String EMAIL = "email";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String USER_CATEGORY = "user_category";
    private static final String TENANT_ID = "tenant_id";
    private static final String THUMBNAIL = "thumbnail";
    private static final String GENDER = "gender";
    private static final String ABOUT = "about";
    private static final String SCHOOL = "school";
    private static final String SCHOOL_DISTRICT = "school_district";
    private static final String COUNTRY = "country";
    private static final String STATE = "state";
    private static final String AUTHORITY = "authority";
    private static final String CITIZENSHIP = "citizenship";
    private static final String REPUTATION = "reputation";
  }

}
