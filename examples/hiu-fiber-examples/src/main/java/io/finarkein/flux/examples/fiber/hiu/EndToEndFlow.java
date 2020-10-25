package io.finarkein.flux.examples.fiber.hiu;

import io.finarkein.flux.fiber.hiu.HiuFiber;
import io.finarkein.flux.fiber.hiu.model.ConsentRequest;
import io.finarkein.flux.fiber.hiu.model.ConsentToken;
import io.finarkein.flux.fiber.hiu.model.Data;
import io.finarkein.flux.fiber.hiu.model.DataFetchId;
import io.finarkein.flux.fiber.hiu.model.DataFetchRequest;
import io.finarkein.flux.fiber.hiu.model.DataRequestToken;
import io.finarkein.flux.fiber.hiu.model.FetchResponse;
import io.finarkein.flux.fiber.hiu.model.common.DateRange;
import io.finarkein.flux.fiber.hiu.model.common.Frequency;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This example shows usage of all {@link io.finarkein.flux.fiber.hiu.HiuFiber} features.
 */
public class EndToEndFlow {

    public static void main(String[] args) throws IOException {
        HiuFiber fiber = new HiuFiber();

        // Build a consent request for a patient
        String patientId = "<set-patient-id>";
        ConsentRequest consentRequest = createConsentRequest(patientId);

        // Exchange it for a token from the fiber (to be used for making data request)
        ConsentToken consentToken = fiber.createConsent(consentRequest);

        // Exchange consent token for a data request token to raise data request
        DataRequestToken dataFetchToken = fiber.getDataFetchToken(consentToken, 3);
        // If error is set in data fetch token, either consent request was not granted or was denied!
        if (dataFetchToken.getError() != null) {
            throw new IllegalStateException(dataFetchToken.getError().getMessage());
        }

        // If all good, raise a data request using request token from Fiber.
        DataFetchRequest dataFetchRequest = createDataRequest(dataFetchToken);
        DataFetchId dataFetchId = fiber.requestData(dataFetchRequest);

        // Finally, fetch the data! (lookout for fetch response status - data present only when it's TRANSFERRED)
        FetchResponse fetchResponse = fiber.fetchData(dataFetchId);
        // get decrypted data
        List<Data> dataList = Optional.of(fetchResponse.getDataEntry()).orElse(Collections.emptyList());
        for (Data data : dataList) {
            System.out.println("==========================================================");
            System.out.println("Media type: " + data.getMedia());
            System.out.println("Care context reference: " + data.getCareContextReference());
            System.out.println(data.getContent());
        }
    }

    static ConsentRequest createConsentRequest(String patientId) {
        return ConsentRequest.builder()
                .purposeText("Care Management")
                .purposeCode("CAREMGT")
                .patientId(patientId)
                .hipId("lh-28") // LiveHealth
                // .hiuId("finarkein-001") // default (but configurable)
                .requesterName("Dr. New2")
                .requesterIdentifierType("REGNO")
                .requesterIdentifierValue("MH1001")
                .requesterIdentifierSystem("https://www.mciindia.org")
                .hiTypes(Arrays.asList("DischargeSummary", "DiagnosticReport", "OPConsultation", "Prescription"))
                .permissionAccessModes("VIEW")
                .dateRange(DateRange.builder()
                        .from("2019-10-01T18:17:25.525Z")
                        .to("2021-10-13T18:17:25.525Z")
                        .build())
                .frequency(Frequency.builder()
                        .unit("HOUR")
                        .value(0)
                        .repeats(0)
                        .build())
                .dataEraseAt("2021-10-13T18:17:25.525Z")
                .build();
    }

    static DataFetchRequest createDataRequest(DataRequestToken dataFetchToken) {
        return DataFetchRequest.builder()
                .dataFetchToken(dataFetchToken)
                .dateRange(DateRange.builder()
                        .from("2019-10-01T18:17:25.525Z")
                        .to("2021-10-13T18:17:25.525Z")
                        .build())
                .build();
    }

}
