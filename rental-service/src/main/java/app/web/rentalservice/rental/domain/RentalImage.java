package app.web.rentalservice.rental.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "rental_image")
public class RentalImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "image_path")
    private String path;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_rental_offer_id", referencedColumnName = "id",nullable = false)
    @JsonIgnore
    private RentalOffer rentalOffer;

    public RentalImage() {
    }

    public RentalImage(long id, String path, RentalOffer rentalOffer) {
        this.id = id;
        this.path = path;
        this.rentalOffer = rentalOffer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public RentalOffer getRentalOffer() {
        return rentalOffer;
    }

    public void setRentalOffer(RentalOffer rentalOffer) {
        this.rentalOffer = rentalOffer;
    }
}
