package org.gooru.ds.user.processor.activeuserlist;

/**
 * @author ashish on 11/1/18.
 */
class ActiveUserListModel {
  private String userId;
  private String username;
  private String firstName;
  private String lastName;
  private String thumbnail;
  private String grade;
  private float authority;
  private float citizenship;
  private float reputation;

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public String getGrade() {
    return grade;
  }

  public void setGrade(String grade) {
    this.grade = grade;
  }

  public float getAuthority() {
    return authority;
  }

  public void setAuthority(float authority) {
    this.authority = authority;
  }

  public float getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(float citizenship) {
    this.citizenship = citizenship;
  }

  public float getReputation() {
    return reputation;
  }

  public void setReputation(float reputation) {
    this.reputation = reputation;
  }
}
