package org.gooru.ds.user.processor.learnervectors;


class LearnerVectorsModel {

  private Float authority = 0F;
  private Float citizenship = 0F;
  private Float reputation = 0F;
  private Float grit = 0F;
  private Float perseverance = 0F;
  private Float motivation = 0F;
  private Float selfConfidence = 0F;


  public Float getAuthority() {
    return authority;
  }

  public void setAuthority(Float authority) {
    this.authority = authority;
  }

  public Float getCitizenship() {
    return citizenship;
  }

  public void setCitizenship(Float citizenship) {
    this.citizenship = citizenship;
  }

  public Float getReputation() {
    return reputation;
  }

  public void setReputation(Float reputation) {
    this.reputation = reputation;
  }

  public Float getGrit() {
    return grit;
  }

  public void setGrit(Float grit) {
    this.grit = grit;
  }

  public Float getPerseverance() {
    return perseverance;
  }



  public void setPerseverance(Float perseverance) {
    this.perseverance = perseverance;
  }

  public Float getMotivation() {
    return motivation;
  }

  public void setMotivation(Float motivation) {
    this.motivation = motivation;
  }

  public Float getSelfConfidence() {
    return selfConfidence;
  }

  public void setSelfConfidence(Float selfConfidence) {
    this.selfConfidence = selfConfidence;
  }


  @Override
  public String toString() {
    return "LearnerVectorsModel{" + "authority=" + authority + ", citizenship=" + citizenship
        + ", reputation=" + reputation + ", grit=" + grit + ", perseverance=" + perseverance
        + ", motivation=" + motivation + ", selfConfidence=" + selfConfidence + '}';
  }
}
