package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookedOfferDTO {

    @JsonProperty("id")
    private long id;

    @JsonProperty("offerTittle")
    private String offerTittle;

    @JsonProperty("startRentDate")
    private Date startRentDate;

    @JsonProperty("endRentDate")
    private Date endRentDate;

    @JsonProperty("price")
    private float price;

    @JsonProperty("rentalOfferID")
    private long rentalOfferID;

    public BookedOfferDTO(@JsonProperty("id") long id,
                          @JsonProperty("offerTittle") String offerTittle,
                          @JsonProperty("startRentDate") Date startRentDate,
                          @JsonProperty("endRentDate") Date endRentDate,
                          @JsonProperty("price") float price,
                          @JsonProperty("rentalOfferID") long rentalOfferID) {
        this.id = id;
        this.offerTittle = offerTittle;
        this.startRentDate = startRentDate;
        this.endRentDate = endRentDate;
        this.price = price;
        this.rentalOfferID = rentalOfferID;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOfferTittle() {
        return offerTittle;
    }

    public void setOfferTittle(String offerTittle) {
        this.offerTittle = offerTittle;
    }

    public Date getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(Date startRentDate) {
        this.startRentDate = startRentDate;
    }

    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public long getRentalOfferID() {
        return rentalOfferID;
    }

    public void setRentalOfferID(long rentalOfferID) {
        this.rentalOfferID = rentalOfferID;
    }
}
