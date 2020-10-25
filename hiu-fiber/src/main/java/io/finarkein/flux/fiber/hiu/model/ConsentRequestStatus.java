package io.finarkein.flux.fiber.hiu.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


/**
 * A class that represents consent status.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "requestId",
        "timestamp",
        "consentRequestId"
})
public class ConsentRequestStatus {

    /**
     * This variable stores the request id generated during consent init.
     */
    @JsonProperty("requestId")
    private String requestId;

    /**
     * This variable stores the timestamp during consent initialization.
     */
    @JsonProperty("timestamp")
    private String timestamp;

    /**
     * This variable stores the consent request Id received from the gateway.
     */
    @JsonProperty("consentRequestId")
    private String consentRequestId;

    /**
     * @return A string representing requestID.
     */
    @JsonProperty("requestId")
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId A string containing requestID.
     */
    @JsonProperty("requestId")
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    /**
     * @return A string representing timestamp.
     */
    @JsonProperty("timestamp")
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp A string containing timestamp.
     */
    @JsonProperty("timestamp")
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return A string representing consentRequestId.
     */
    @JsonProperty("consentRequestId")
    public String getConsentRequestId() {
        return consentRequestId;
    }

    /**
     * @param consentRequestId A string containing consentRequestId.
     */
    @JsonProperty("consentRequestId")
    public void setConsentRequestId(String consentRequestId) {
        this.consentRequestId = consentRequestId;
    }

}