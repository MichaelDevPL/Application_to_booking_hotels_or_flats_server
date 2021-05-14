package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewReservationDTO {

    @JsonProperty("startDate")
    private final Date startDate;

    @JsonProperty("endDate")
    private final Date endDate;

    @JsonProperty("price")
    private final float price;

    @JsonProperty("clientId")
    private final long clientId;

    @JsonProperty("offerId")
    private final long offerId;

    @JsonCreator
    public NewReservationDTO(@JsonProperty("startDate") Date startDate,
                             @JsonProperty("endDate") Date endDate,
                             @JsonProperty("price") float price,
                             @JsonProperty("clientId") long clientId,
                             @JsonProperty("offerId") long offerId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.clientId = clientId;
        this.offerId = offerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public float getPrice() {
        return price;
    }

    public long getClientId() {
        return clientId;
    }

    public long getofferId() {
        return offerId;
    }
}
