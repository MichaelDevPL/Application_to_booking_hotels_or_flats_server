package app.web.rentalservice.rental.domain.enums;

public enum RentalCategory {
    HOUSE, APARTMENT, CONDO;

    public String getKindOfRoom() {
        return name();
    }
}
