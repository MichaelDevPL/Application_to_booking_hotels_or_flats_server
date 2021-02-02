package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalOfferImage;
import app.web.rentalservice.rental.infrastracture.persistance.RentalImageRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

@Transactional
@Repository
public class RentalImageRepositoryImpl extends SimpleJpaRepository<RentalOfferImage, Long> implements RentalImageRepository {

    private EntityManager em;

    public RentalImageRepositoryImpl(EntityManager em){
        super(RentalOfferImage.class, em);
        this.em = em;
    }

    @Override
    public void createRentalOfferImage(RentalOfferImage rentalOfferImage) {

    }

    @Override
    public void updateRentalOfferImage(RentalOfferImage rentalOfferImage) {

    }

    @Override
    public void deleteRentalOfferImage(RentalOfferImage rentalOfferImage) {

    }
}
