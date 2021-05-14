package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ReviewDTO {

    @JsonProperty("id")
    private final long id;

    @JsonProperty("starRating")
    private final double starRating;

    @JsonProperty("comment")
    private final String comment;

    @JsonProperty("createdAt")
    private final Date createdAt;

    @JsonProperty("accountNick")
    private final String accountNick;

    @JsonProperty("rentalOfferId")
    private final long rentalOfferId;

    public ReviewDTO( @JsonProperty("id") long id,
                      @JsonProperty("starRating") double starRating,
                      @JsonProperty("comment") String comment,
                      @JsonProperty("createdAt") Date createdAt,
                      @JsonProperty("accountNick") String accountNick,
                      @JsonProperty("rentalOfferId") long rentalOfferId) {
        this.id = id;
        this.starRating = starRating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.accountNick = accountNick;
        this.rentalOfferId = rentalOfferId;
    }

    public long getId() {
        return id;
    }

    public double getStarRating() {
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
