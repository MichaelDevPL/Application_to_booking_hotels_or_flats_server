package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class NewOfferReviewDTO {

    @JsonProperty("starRating")
    private final int starRating;

    @JsonProperty("comment")
    private final String comment;

    @JsonProperty("createdAt")
    private final Date createdAt;

    @JsonProperty("accountNick")
    private final String accountNick;

    @JsonProperty("rentalOfferId")
    private final long rentalOfferId;

    @JsonCreator
    public NewOfferReviewDTO(@JsonProperty("starRating") int starRating,
                             @JsonProperty("comment") String comment,
                             @JsonProperty("createdAt") Date createdAt,
                             @JsonProperty("accountNick") String accountNick,
                             @JsonProperty("rentalOfferId") long rentalOfferId) {
        this.starRating = starRating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.accountNick = accountNick;
        this.rentalOfferId = rentalOfferId;
    }

    public int getStarRating() {
        return starRating;
    }

    public String getComment() {
        return comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getAccountNick() {
        return accountNick;
    }

    public long getRentalOfferId() {
        return rentalOfferId;
    }
}
