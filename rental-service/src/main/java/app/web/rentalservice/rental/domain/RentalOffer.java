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

    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Enumerated(EnumType.STRING)
    private RentalCategory category;

    @OneToMany(
            mappedBy = "rentalOffer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<RentalImage> rentalImages = new HashSet<RentalImage>();

    @Column(name = "daily_rate")
    private float dailyRate;

    @Column(name = "bedrooms")
    private int bedrooms;

    @Column(name = "quests")
    private int quests;

    @Column(name = "offer_owner_id")
    private long offerOwnerId;

    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @OneToMany(
            mappedBy = "rentalOffer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private Set<RentalSchedule> rentalSchedule = new HashSet<RentalSchedule>();

    @OneToMany(
            mappedBy = "rentalOffer",
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    @OrderBy("create_at desc")
    private Set<ClientReview> clientReviews = new HashSet<ClientReview>();

    public RentalOffer() {
    }

    public RentalOffer(long id, String title, String description, String city,
                       String address, RentalCategory category, Set<RentalImage> rentalImages,
                       float dailyRate, int bedrooms, int quests, long offerOwnerId,
                       Date createdAt, Set<RentalSchedule> rentalSchedule, Set<ClientReview> clientReviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.city = city;
        this.address = address;
        this.category = category;
        this.rentalImages = rentalImages;
        this.dailyRate = dailyRate;
        this.bedrooms = bedrooms;
        this.quests = quests;
        this.offerOwnerId = offerOwnerId;
        this.createdAt = createdAt;
        this.rentalSchedule = rentalSchedule;
        this.clientReviews = clientReviews;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public RentalCategory getCategory() {
        return category;
    }

    public void setCategory(RentalCategory category) {
        this.category = category;
    }

    public Set<RentalImage> getRentalImages() {
        return rentalImages;
    }

    public void setRentalImages(Set<RentalImage> rentalImages) {
        this.rentalImages = rentalImages;
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

    public int getQuests() {
        return quests;
    }

    public void setQuests(int quests) {
        this.quests = quests;
    }

    public long getOfferOwnerId() {
        return offerOwnerId;
    }

    public void setOfferOwnerId(long offerOwnerId) {
        this.offerOwnerId = offerOwnerId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Set<RentalSchedule> getRentalSchedule() {
        return rentalSchedule;
    }

    public void setRentalSchedule(Set<RentalSchedule> rentalSchedule) {
        this.rentalSchedule = rentalSchedule;
    }

    public Set<ClientReview> getClientReviews() {
        return clientReviews;
    }

    public void setClientReviews(Set<ClientReview> clientReviews) {
        this.clientReviews = clientReviews;
    }
}