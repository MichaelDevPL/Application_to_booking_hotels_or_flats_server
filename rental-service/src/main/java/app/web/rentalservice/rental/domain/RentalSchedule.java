package app.web.rentalservice.rental.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "rental_schedule")
public class RentalSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "start_rent_date")
    @Temporal(TemporalType.DATE)
    private Date startRentDate;

    @Column(name = "end_rent_date")
    @Temporal(TemporalType.DATE)
    private Date endRentDate;

    @Column(name = "client_id")
    private long clientId;

    @Column(name = "price")
    private float price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "fk_rental_offer_id", referencedColumnName = "id" , nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RentalOffer rentalOffer;

    public RentalSchedule() {
    }

    public RentalSchedule(Date startRentDate, Date endRentDate, long clientId, float price, RentalOffer rentalOffer) {
        this.startRentDate = startRentDate;
        this.endRentDate = endRentDate;
        this.clientId = clientId;
        this.price = price;
        this.rentalOffer = rentalOffer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(Date startRentDate) {
        this.startRentDate = startRentDate;
    }

    public Date getEndRentDate() {
        return endRentDate;
    }

    public void setEndRentDate(Date endRentDate) {
        this.endRentDate = endRentDate;
    }

    public long getClientId() {
        return clientId;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public RentalOffer getRentalOffer() {
        return rentalOffer;
    }

    public void setRentalOffer(RentalOffer rentalOffer) {
        this.rentalOffer = rentalOffer;
    }
}
