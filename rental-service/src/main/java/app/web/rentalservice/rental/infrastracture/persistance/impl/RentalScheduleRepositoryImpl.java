package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.infrastracture.persistance.RentalScheduleRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class RentalScheduleRepositoryImpl extends SimpleJpaRepository<RentalSchedule, Long> implements RentalScheduleRepository {

    private EntityManager em;

    public RentalScheduleRepositoryImpl(EntityManager em){
        super(RentalSchedule.class, em);
        this.em = em;
    }

    @Override
    public boolean checkAvailableOfRentalOfferInPeriod() {
        return false;
    }

    @Override
    public void createRentalOfferInSchedule(RentalSchedule rentalSchedule) {

    }

    @Override
    public void updateRentalOfferSchedule(RentalSchedule rentalSchedule) {

    }

    @Override
    public void deleteRentalOfferSchedule(RentalSchedule rentalSchedule) {

    }
}
