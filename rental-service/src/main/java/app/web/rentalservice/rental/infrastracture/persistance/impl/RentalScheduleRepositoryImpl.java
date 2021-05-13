package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.infrastracture.persistance.RentalScheduleRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class RentalScheduleRepositoryImpl extends SimpleJpaRepository<RentalSchedule, Long> implements RentalScheduleRepository {

    private final EntityManager em;

    public RentalScheduleRepositoryImpl(EntityManager em) {
        super(RentalSchedule.class, em);
        this.em = em;
    }

    @Override
    public List<RentalSchedule> getAllByClientId(long clientId) {
        String sqlQuery = " SELECT r FROM RentalSchedule r " +
                " WHERE r.clientId=:clientId " +
                " and r.endRentDate >= CURDATE()";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("clientId", clientId);

        return (List<RentalSchedule>) query.getResultList();
    }

    @Override
    public void createReserve(RentalSchedule rentalSchedule) {
        save(rentalSchedule);
    }

    @Override
    public void updateReserve(RentalSchedule rentalSchedule) {
        save(rentalSchedule);
    }

    @Override
    public void deleteReserve(RentalSchedule rentalSchedule) {
        delete(rentalSchedule);
    }
}
