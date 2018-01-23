package org.gooru.ds.user.processor.userstats.content;

/**
 * @author ashish on 12/1/18.
 */
class UserStatsContentsModel {
    private Long webpage;
    private Long interactive;
    private Long text;
    private Long audio;
    private Long video;
    private Long image;

	public Long getWebpage() {
        return webpage;
    }

    public void setWebpage(Long webpage) {
        this.webpage = webpage;
    }

    public Long getInteractive() {
        return interactive;
    }

    public void setInteractive(Long interactive) {
        this.interactive = interactive;
    }

    public Long getText() {
        return text;
    }

    public void setText(Long text) {
        this.text = text;
    }

    public Long getAudio() {
        return audio;
    }

    public void setAudio(Long audio) {
        this.audio = audio;
    }

    public Long getVideo() {
        return video;
    }

    public void setVideo(Long video) {
        this.video = video;
    }
    
    public Long getImage() {
		return image;
	}

	public void setImage(Long image) {
		this.image = image;
	}

}
