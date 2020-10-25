package io.finarkein.flux.fiber.hiu.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.NonNull;

/**
 * Represents a data range.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "from",
        "to"
})
@Builder
public class DateRange {

    /**
     * The value is used to store the start date of the data range
     */
    @JsonProperty("from")
    @NonNull
    private String from;
    /**
     * The value is used to store the end date of the data range.
     */
    @JsonProperty("to")
    @NonNull
    private String to;

    /**
     * @return A string representing start date.
     */
    @JsonProperty("from")
    public String getFrom() {
        return from;
    }

    /**
     * @param from A string containing start date.
     */
    @JsonProperty("from")
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return A string representing end date.
     */
    @JsonProperty("to")
    public String getTo() {
        return to;
    }

    /**
     * @param to A string containing end date.
     */
    @JsonProperty("to")
    public void setTo(String to) {
        this.to = to;
    }

}