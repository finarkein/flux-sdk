package io.finarkein.flux.fiber.hiu.model;

import io.finarkein.flux.fiber.hiu.model.common.Error;
import lombok.Builder;

/**
 * A class representing data fetch token.
 */
@Builder
public class DataRequestToken {

    /**
     * This variable stores the consent Id for the requested data.
     */
    private final String artefactId;

    /**
     * This variable stores the consent request Id.
     */
    private final String consentRequestId;

    /**
     * This variable stores the error if received.
     */
    private final Error error;

    /**
     * @return A string representing consentRequestId.
     */
    String getConsentRequestId() {
        return consentRequestId;
    }

    /**
     * @return A string representing artefactId.
     */
    String getArtefactId() {
        return artefactId;
    }

    /**
     * @return A Error representing error.
     */
    public Error getError() {
        return error;
    }
}
