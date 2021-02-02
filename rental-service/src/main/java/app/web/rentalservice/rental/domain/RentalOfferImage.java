package app.web.rentalservice.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "rental_offer_image")
public class RentalOfferImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "image_path")
    private String imagePath;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_rental_offer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RentalOffer rentalOffer;

    public RentalOfferImage() {
    }

    public RentalOfferImage(String imagePath, RentalOffer rentalOffer) {
        this.imagePath = imagePath;
        this.rentalOffer = rentalOffer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public RentalOffer getRentalOffer() {
        return rentalOffer;
    }

    public void setRentalOffer(RentalOffer rentalOffer) {
        this.rentalOffer = rentalOffer;
    }

    @Override
    public String toString() {
        return "RentalOfferImage{" +
                "id=" + id +
                ", imagePath='" + imagePath + '\'' +
                ", rentalOffer=" + rentalOffer +
                '}';
    }
}
