package io.finarkein.flux.fiber.hiu.model;

import lombok.Data;

/**
 * This class represents the current status of the consent request.
 */
@Data
public class StatusResponse {

    /**
     * This variable stores status of your consent.
     */
    private String status;

    /**
     * This variable stores the consent request Id given by the gateway.
     */
    private String consentRequestId;

    /**
     * This variable stores the error if received.
     */
    private Error error;
}
