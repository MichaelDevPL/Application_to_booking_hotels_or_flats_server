package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public class RentalOfferRepositoryImpl extends SimpleJpaRepository<RentalOffer, Long> implements RentalOfferRepository {

    private EntityManager em;

    public RentalOfferRepositoryImpl(EntityManager em){
        super(RentalOffer.class, em);
        this.em = em;
    }

    @Override
    public Optional<List<RentalOffer>> getRentalOfferByClientPreferences() {
        return Optional.empty();
    }

    @Override
    public Optional<List<RentalOffer>> getAllRentalOfferByUserId(long userId) {
        return Optional.empty();
    }

    @Override
    public void createRentalOffer(RentalOffer rentalOffer) {
        save(rentalOffer);
    }

    @Override
    public void updateRentalOffer(RentalOffer rentalOffer) {
        save(rentalOffer);
    }

    @Override
    public void deleteRentalOffer(RentalOffer rentalOffer) {
        delete(rentalOffer);
    }

    /*_____________________________________________________________________________________________________________________________*/
    @Override
    public Optional<List<RentalOffer>> getAllTestValium(){
        String sqlQuery= "SELECT r FROM RentalOffer r JOIN FETCH r.rentalOfferImages JOIN FETCH r.rentalSchedule";
        Query query = em.createQuery(sqlQuery);

        List<RentalOffer> rentalOffers = query.getResultList();

        if (rentalOffers.isEmpty()) {
            throw new IllegalArgumentException("No account with such id: ");
        }

        return Optional.of(rentalOffers);
    }
}