package io.finarkein.flux.fiber.hiu.model;

import java.util.List;

/**
 * A class representing the data block received on data fetch.
 */
@lombok.Data
public class FetchResponse {

    /**
     * This variable stores the list of data.
     *
     * @see Data
     */
    private List<Data> dataEntry;

    /**
     * This variable stores the block page number.
     */
    private int pageNumber;

    /**
     * This variable stores the block page count.
     */
    private int pageCount;

    /**
     * This variable stores the status of the data fetch.
     */
    private FetchStatus fetchStatus;

}
