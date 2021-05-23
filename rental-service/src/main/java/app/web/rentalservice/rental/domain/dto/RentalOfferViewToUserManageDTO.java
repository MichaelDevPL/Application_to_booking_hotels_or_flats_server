package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.enums.RentalCategory;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RentalOfferViewToUserManageDTO {

    @JsonProperty("id")
    private final long id;

    @JsonProperty("title")
    private final String title;

    @JsonProperty("category")
    private final RentalCategory category;

    @JsonProperty("address")
    private final String address;

    @JsonProperty("city")
    private final String city;

    public RentalOfferViewToUserManageDTO(@JsonProperty("id") long id,
                                          @JsonProperty("title") String title,
                                          @JsonProperty("category") RentalCategory category,
                                          @JsonProperty("address") String address,
                                          @JsonProperty("city") String city) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.address = address;
        this.city = city;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public RentalCategory getCategory() {
        return category;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
