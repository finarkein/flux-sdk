package io.finarkein.flux.fiber.hiu.model;

import io.finarkein.flux.fiber.hiu.model.common.DateRange;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

/**
 * A class that represents data fetch request, used in request data call.
 */
@ToString
@Getter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
public class DataFetchRequest {

    /**
     * This variable stores the consent Id for the requested data.
     */
    private final String artefactId;

    /**
     * This variable stores the consent request Id.
     */
    private final String consentRequestId;

    /**
     * This variable stores the data range of the requested data.
     */
    @NonNull
    private final DateRange dateRange;

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private DataRequestToken fetchToken;
        private DateRange dateRange;

        public Builder dataFetchToken(DataRequestToken dataFetchToken) {
            this.fetchToken = dataFetchToken;
            return this;
        }

        public Builder dateRange(DateRange dateRange) {
            this.dateRange = dateRange;
            return this;
        }

        public DataFetchRequest build() {
            return new DataFetchRequest(fetchToken.getArtefactId(), fetchToken.getConsentRequestId(), dateRange);
        }
    }
}
