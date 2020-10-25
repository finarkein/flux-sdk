package io.finarkein.flux.fiber.hiu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * A class representing data received.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "content",
        "media",
        "careContextReference"
})
public class Data {

    /**
     * This variable stores the received data.
     */
    @JsonProperty("content")
    private String content;

    /**
     * This variable stores the data type.
     */
    @JsonProperty("media")
    private String media;

    /**
     * This variable stores the care context Id of the data.
     */
    @JsonProperty("careContextReference")
    private Integer careContextReference;

    /**
     * @return A string representing content.
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * @param content A String containing content.
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return A string representing media.
     */
    @JsonProperty("media")
    public String getMedia() {
        return media;
    }

    /**
     * @param media A String containing media.
     */
    @JsonProperty("media")
    public void setMedia(String media) {
        this.media = media;
    }

    /**
     * @return A integer representing care context reference.
     */
    @JsonProperty("careContextReference")
    public Integer getCareContextReference() {
        return careContextReference;
    }

    /**
     * @param careContextReference A integer containing care context reference.
     */
    @JsonProperty("careContextReference")
    public void setCareContextReference(Integer careContextReference) {
        this.careContextReference = careContextReference;
    }

}