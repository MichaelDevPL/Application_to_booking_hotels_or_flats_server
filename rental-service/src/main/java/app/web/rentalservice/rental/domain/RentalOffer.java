package app.web.rentalservice.rental.domain;

import app.web.rentalservice.rental.domain.enums.RentalCategory;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "rental_offer")
public class RentalOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Enumerated(EnumType.STRING)
    private RentalCategory rentalCategory;

    @Column(name = "main_view_image")
    private String mainViewImage;

    @OneToMany(
            mappedBy = "rentalOffer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<RentalOfferImage> rentalOfferImages = new HashSet<RentalOfferImage>();

    @Column(name = "daily_rate")
    private float dailyRate;

    @Column(name = "bedrooms")
    private int bedrooms;

    @Column(name = "offer_owner_id")
    private long offerOwnerId;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date createAt;

    @OneToMany(
            mappedBy = "rentalOffer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Set<RentalSchedule> rentalSchedule = new HashSet<RentalSchedule>();

    public RentalOffer() {
    }

    public RentalOffer(long id, String title, String description, String city, String street,
                       RentalCategory rentalCategory, String mainViewImage, Set<RentalOfferImage> rentalOfferImages,
                       float dailyRate, int bedrooms, long offerOwnerId, Date createAt, Set<RentalSchedule> rentalSchedule) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.city = city;
        this.street = street;
        this.rentalCategory = rentalCategory;
        this.mainViewImage = mainViewImage;
        this.rentalOfferImages = rentalOfferImages;
        this.dailyRate = dailyRate;
        this.bedrooms = bedrooms;
        this.offerOwnerId = offerOwnerId;
        this.createAt = createAt;
        this.rentalSchedule = rentalSchedule;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public RentalCategory getRentalCategory() {
        return rentalCategory;
    }

    public void setRentalCategory(RentalCategory rentalCategory) {
        this.rentalCategory = rentalCategory;
    }

    public String getMainViewImage() {
        return mainViewImage;
    }

    public void setMainViewImage(String mainViewImage) {
        this.mainViewImage = mainViewImage;
    }

    public Set<RentalOfferImage> getRentalOfferImages() {
        return rentalOfferImages;
    }

    public void setRentalOfferImages(Set<RentalOfferImage> rentalOfferImages) {
        this.rentalOfferImages = rentalOfferImages;
    }

    public float getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(float dailyRate) {
        this.dailyRate = dailyRate;
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        this.bedrooms = bedrooms;
    }

    public long getOfferOwnerId() {
        return offerOwnerId;
    }

    public void setOfferOwnerId(long offerOwnerId) {
        this.offerOwnerId = offerOwnerId;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Set<RentalSchedule> getRentalSchedule() {
        return rentalSchedule;
    }

    public void setRentalSchedule(Set<RentalSchedule> rentalSchedule) {
        this.rentalSchedule = rentalSchedule;
    }

    @Override
    public String toString() {
        return "RentalOffer{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", rentalCategory=" + rentalCategory +
                ", mainViewImage='" + mainViewImage + '\'' +
                ", rentalOfferImages=" + rentalOfferImages +
                ", dailyRate=" + dailyRate +
                ", bedrooms=" + bedrooms +
                ", offerOwnerId=" + offerOwnerId +
                ", createAt=" + createAt +
                ", rentalSchedule=" + rentalSchedule +
                '}';
    }
}