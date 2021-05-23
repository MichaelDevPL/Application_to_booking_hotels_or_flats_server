package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateOfferResponseDTO {

    @JsonProperty("createdSuccessfully")
    private boolean createdSuccessfully;

    @JsonProperty("offerId")
    private long offerId;

    public CreateOfferResponseDTO(@JsonProperty("createdSuccessfully") boolean createdSuccessfully,
                                  @JsonProperty("offerId") long offerId) {
        this.createdSuccessfully = createdSuccessfully;
        this.offerId = offerId;
    }

    public boolean isCreatedSuccessfully() {
        return createdSuccessfully;
    }

    public void setCreatedSuccessfully(boolean createdSuccessfully) {
        this.createdSuccessfully = createdSuccessfully;
    }

    public long getOfferId() {
        return offerId;
    }

    public void setOfferId(long offerId) {
        this.offerId = offerId;
    }
}
