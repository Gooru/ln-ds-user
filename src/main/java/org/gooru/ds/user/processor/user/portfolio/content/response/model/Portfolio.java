
package org.gooru.ds.user.processor.user.portfolio.content.response.model;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "content"
})
public class Portfolio implements Serializable
{

    @JsonProperty("content")
    private List<Content> content = null;
    private final static long serialVersionUID = 1653691258758639878L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Portfolio() {
    }

    /**
     * 
     * @param content
     */
    public Portfolio(List<Content> content) {
        super();
        this.content = content;
    }

    @JsonProperty("content")
    public List<Content> getContent() {
        return content;
    }

    @JsonProperty("content")
    public void setContent(List<Content> content) {
        this.content = content;
    }

    public Portfolio withContent(List<Content> content) {
        this.content = content;
        return this;
    }

}