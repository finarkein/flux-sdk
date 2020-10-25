package io.finarkein.flux.fiber.hiu.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.NonNull;

/**
 * Represents a frequency.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "unit",
        "value",
        "repeats"
})
@Builder
public class Frequency {

    /**
     * This variable stores time unit for the consent.
     */
    @JsonProperty("unit")
    @NonNull
    private String unit;

    /**
     * This variable stores the number of unit values.
     */
    @JsonProperty("value")
    @NonNull
    private Integer value;

    /**
     * This variable stores the number of times you can request data.
     */
    @JsonProperty("repeats")
    @NonNull
    private Integer repeats;

    /**
     * @return A string representing unit.
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit A string containing unit.
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return A integer representing value.
     */
    @JsonProperty("value")
    public Integer getValue() {
        return value;
    }

    /**
     * @param value A integer containing value.
     */
    @JsonProperty("value")
    public void setValue(Integer value) {
        this.value = value;
    }

    /**
     * @return A integer representing repeats.
     */
    @JsonProperty("repeats")
    public Integer getRepeats() {
        return repeats;
    }

    /**
     * @param repeats A integer containing repeats.
     */
    @JsonProperty("repeats")
    public void setRepeats(Integer repeats) {
        this.repeats = repeats;
    }

}