package app.web.rentalservice.rental.domain.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class DataToSearchForOffersDto {

    @JsonProperty("city")
    private final String city;

    @JsonProperty("startDate")
    private final Date startDate;

    @JsonProperty("endDate")
    private final Date endDate;

    @JsonProperty("numberOfGuest")
    private final int numberOfGuest;

    @JsonProperty("numberOfRoom")
    private final int numberOfRoom;

    @JsonCreator
    public DataToSearchForOffersDto(@JsonProperty("city") String city,
                                    @JsonProperty("startDate") Date startDate,
                                    @JsonProperty("endDate") Date endDate,
                                    @JsonProperty("numberOfGuest") int numberOfGuest,
                                    @JsonProperty("numberOfRoom") int numberOfRoom) {
        this.city = city;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfGuest = numberOfGuest;
        this.numberOfRoom = numberOfRoom;
    }

    public String getCity() {
        return city;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getNumberOfGuest() {
        return numberOfGuest;
    }

    public int getNumberOfRoom() {
        return numberOfRoom;
    }
}
