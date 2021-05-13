package app.web.rentalservice.rental.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "client_review")
public class ClientReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private long id;

    @Column(name = "star_rating")
    private double starRating;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "create_at")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    @Column(name = "account_nick")
    private String accountNick;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_rental_offer_id", referencedColumnName = "id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RentalOffer rentalOffer;

    public ClientReview() {
    }

    public ClientReview(long id, double starRating, String comment, Date createdAt,
                        String accountNick, RentalOffer rentalOffer) {
        this.id = id;
        this.starRating = starRating;
        this.comment = comment;
        this.createdAt = createdAt;
        this.accountNick = accountNick;
        this.rentalOffer = rentalOffer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getStarRating() {
        return starRating;
    }

    public void setStarRating(double starRating) {
        this.starRating = starRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getAccountNick() {
        return accountNick;
    }

    public void setAccountNick(String accountNick) {
        this.accountNick = accountNick;
    }

    public RentalOffer getRentalOffer() {
        return rentalOffer;
    }

    public void setRentalOffer(RentalOffer rentalOffer) {
        this.rentalOffer = rentalOffer;
    }
}
