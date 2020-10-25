package io.finarkein.flux.fiber.hiu.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;

/**
 * Represents a error.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "code",
        "message"
})
@Builder
public class Error {

    /**
     * The value is used to store error code.
     */
    @JsonProperty("code")
    private Integer code;
    /**
     * The value is used to store error message.
     */
    @JsonProperty("message")
    private String message;

    /**
     * @return A integer representing error code.
     */
    @JsonProperty("code")
    public Integer getCode() {
        return code;
    }

    /**
     * @param code A integer containing error code.
     */
    @JsonProperty("code")
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * @return A string representing error message.
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * @param message A string containing error message.
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

}