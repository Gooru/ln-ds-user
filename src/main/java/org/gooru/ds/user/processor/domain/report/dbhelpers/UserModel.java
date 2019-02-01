
package org.gooru.ds.user.processor.domain.report.dbhelpers;

/**
 * @author szgooru Created On 01-Feb-2019
 */
public class UserModel {

  private String id;
  private String firstName;
  private String lastName;
  private String thumbnail;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getThumbnail() {
    return thumbnail;
  }

  public void setThumbnail(String thumbnail) {
    this.thumbnail = thumbnail;
  }

  @Override
  public String toString() {
    return "UserModel [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName
        + ", thumbnail=" + thumbnail + "]";
  }
  
}
