package io.finarkein.flux.fiber.hiu.model;

import io.finarkein.flux.fiber.hiu.model.common.DateRange;
import io.finarkein.flux.fiber.hiu.model.common.Frequency;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * A class representing bare minimum data to be provided by the client to initiate the consent flow.
 */
@Getter
@Builder
public class ConsentRequest {

    /**
     * This variable stores the Purpose of Use which defines what is the use of the health information that a HIU is requesting for.
     * <p>
     * Allowed values
     * *  Care Management
     * *  Break the Glass
     * *  Public Health
     * *  Healthcare Payment
     * *  Disease Specific Healthcare Research
     * *  Self Requested
     */
    private String purposeText;

    /**
     * This variable stores the purpose code.
     * <p>
     * Allowed values
     * *  CAREMGT
     * *  BTG
     * *  PUBHLTH
     * *  HPAYMT
     * *  DSRCH
     * *  PATRQT
     */
    private String purposeCode;

    /**
     * This variable stores the id of the requested patient.
     */
    private String patientId;

    /**
     * This variable stores the hiu Id.
     */
    private String hiuId;

    /**
     * This variable stores the hip Id.
     */
    private String hipId;

    /**
     * This variable stores the name of the person requesting the data.
     */
    private String requesterName;

    /**
     * This variable stores the type of the requester identifier.
     */
    private String requesterIdentifierType;

    /**
     * This variable stores the value of requester identifier.
     */
    private String requesterIdentifierValue;

    /**
     * This variable stores the system of requester identifier.
     */
    private String requesterIdentifierSystem;

    /**
     * This variable stores the Health Information (HI) Types - defines what types of information a requester is asking for.
     * <p>
     * Allowed values:
     * *  Prescription
     * *  DiagnosticReport
     * *  OPConsultation
     * *  DischargeSummary
     */
    private List<String> hiTypes;

    /**
     * This variable stores the mode in which the client wants to access the data.
     */
    private String permissionAccessModes;

    /**
     * This variable stores the data range of the data to be requested.
     */
    private DateRange dateRange;

    /**
     * This variable stores the frequency of requests.
     */
    private Frequency frequency;

    /**
     * This variable stores the date at which the data stored should be erased.
     */
    private String dataEraseAt;

}
