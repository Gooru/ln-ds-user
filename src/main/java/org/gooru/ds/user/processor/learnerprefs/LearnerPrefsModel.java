package org.gooru.ds.user.processor.learnerprefs;


class LearnerPrefsModel {

  private Float webpage = 0F;
  private Float interactive = 0F;
  private Float text = 0F;
  private Float audio = 0F;
  private Float video = 0F;
  private Float image = 0F;

  public Float getWebpage() {
    return webpage;
  }

  public void setWebpage(Float webpage) {
    this.webpage = webpage;
  }

  public Float getInteractive() {
    return interactive;
  }

  public void setInteractive(Float interactive) {
    this.interactive = interactive;
  }

  public Float getText() {
    return text;
  }

  public void setText(Float text) {
    this.text = text;
  }

  public Float getAudio() {
    return audio;
  }

  public void setAudio(Float audio) {
    this.audio = audio;
  }

  public Float getVideo() {
    return video;
  }

  public void setVideo(Float video) {
    this.video = video;
  }

  public Float getImage() {
    return image;
  }

  public void setImage(Float image) {
    this.image = image;
  }

  @Override
  public String toString() {
    return "LearnerPrefsModel{" + "webpage=" + webpage + ", interactive=" + interactive
        + ", text=" + text + ", audio=" + audio + ", video=" + video + ", image=" + image + '}';
  }
}
