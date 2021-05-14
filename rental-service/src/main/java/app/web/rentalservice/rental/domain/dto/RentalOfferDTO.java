package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalImage;

import java.util.Set;

public class RentalOfferDTO {

    private long id;
    private String title;
    private String description;
    private Set<RentalImage> rentalImages;
    private double dailyRate;
    private double clientAverageGrades;

    public RentalOfferDTO(long id, String title, String description, Set<RentalImage> rentalImages, double dailyRate,
                          double clientAverageGrades) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rentalImages = rentalImages;
        this.dailyRate = dailyRate;
        this.clientAverageGrades = clientAverageGrades;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RentalImage> getRentalImages() {
        return rentalImages;
    }

    public void setRentalImages(Set<RentalImage> rentalImages) {
        this.rentalImages = rentalImages;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public double getClientAverageGrades() {
        return clientAverageGrades;
    }

    public void setClientAverageGrades(double clientAverageGrades) {
        this.clientAverageGrades = clientAverageGrades;
    }
}
