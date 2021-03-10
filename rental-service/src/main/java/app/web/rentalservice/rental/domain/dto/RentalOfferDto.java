package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalImage;

import java.util.Set;

public class RentalOfferDto {

    private long id;
    private String title;
    private String address;
    private String city;
    private Set<RentalImage> rentalImages;
    private double dailyRate;

    public RentalOfferDto(long id, String title, String address, String city,
                          Set<RentalImage> rentalImages, double dailyRate) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.city = city;
        this.rentalImages = rentalImages;
        this.dailyRate = dailyRate;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    @Override
    public String toString() {
        return "RentalOfferOut{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                ", rentalImages=" + rentalImages +
                ", dailyRate=" + dailyRate +
                '}';
    }
}
