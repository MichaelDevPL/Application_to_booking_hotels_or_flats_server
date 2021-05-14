package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferScheduleRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class RentalOfferScheduleRepositoryImpl extends SimpleJpaRepository<RentalSchedule, Long> implements RentalOfferScheduleRepository {

    private final EntityManager em;

    public RentalOfferScheduleRepositoryImpl(EntityManager em) {
        super(RentalSchedule.class, em);
        this.em = em;
    }

    @Override
    public List<RentalSchedule> getReservationsByClientId(long clientId) {
        String sqlQuery = " SELECT r FROM RentalSchedule r " +
                " join fetch r.rentalOffer ro" +
                " WHERE r.clientId=:clientId " +
                " and r.endRentDate >= CURDATE()" +
                " order by r.endRentDate DESC";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("clientId", clientId);

        return (List<RentalSchedule>) query.getResultList();
    }

    @Override
    public List<RentalSchedule> getHistoryReservationsByClientId(long clientId) {
        String sqlQuery = " SELECT r FROM RentalSchedule r " +
                " join fetch r.rentalOffer ro" +
                " WHERE r.clientId=:clientId " +
                " and r.endRentDate < CURDATE()" +
                " order by r.endRentDate DESC";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("clientId", clientId);

        return (List<RentalSchedule>) query.getResultList();
    }

    @Override
    public void createReservation(RentalSchedule rentalSchedule) {
        save(rentalSchedule);
    }

    @Override
    public void deleteReservation(long reservationId) {
        RentalSchedule rentalSchedule = em.find(RentalSchedule.class, reservationId);
        delete(rentalSchedule);
    }
}
