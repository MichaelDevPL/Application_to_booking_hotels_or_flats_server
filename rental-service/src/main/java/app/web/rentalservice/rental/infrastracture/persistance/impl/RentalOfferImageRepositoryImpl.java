package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalImage;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferImageRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class RentalOfferImageRepositoryImpl extends SimpleJpaRepository<RentalImage, Long> implements RentalOfferImageRepository {

    private EntityManager em;

    public RentalOfferImageRepositoryImpl(EntityManager em){
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
