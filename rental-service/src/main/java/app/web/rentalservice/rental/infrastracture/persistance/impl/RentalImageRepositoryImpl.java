package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalImage;
import app.web.rentalservice.rental.infrastracture.persistance.RentalImageRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class RentalImageRepositoryImpl extends SimpleJpaRepository<RentalImage, Long> implements RentalImageRepository {

    private EntityManager em;

    public RentalImageRepositoryImpl(EntityManager em){
        super(RentalImage.class, em);
        this.em = em;
    }

    @Override
    public void createRentalOfferImage(RentalImage rentalImage) {

    }

    @Override
    public void updateRentalOfferImage(RentalImage rentalImage) {

    }

    @Override
    public void deleteRentalOfferImage(RentalImage rentalImage) {

    }
}
