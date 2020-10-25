package io.finarkein.flux.fiber.hiu.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.NonNull;

/**
 * Represents a patient.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "name",
})
public class Patient {

    /**
     * This variable stores the id of the requested patient.
     */
    @JsonProperty("id")
    @NonNull
    private String id;

    /**
     * This variable stores the name of the requested patient.
     */
    @JsonProperty("name")
    private String name;

    /**
     * @return A string representing patient ID.
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     * @param id A string containing patient ID.
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return A string representing patient name.
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * @param name A string containing patient name.
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

}