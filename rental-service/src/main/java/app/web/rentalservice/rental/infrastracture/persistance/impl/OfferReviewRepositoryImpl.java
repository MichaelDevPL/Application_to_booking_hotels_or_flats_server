package app.web.rentalservice.rental.infrastracture.persistance.impl;


import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.infrastracture.persistance.OfferReviewRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Optional;

@Transactional
@Repository
public class OfferReviewRepositoryImpl extends SimpleJpaRepository<ClientReview, Long> implements OfferReviewRepository {

    private EntityManager em;

    public OfferReviewRepositoryImpl(EntityManager em){
        super(ClientReview.class, em);
        this.em = em;
    }


    @Override
    public Optional<ClientReview> findReviewById(long id) {
        return findById(id);
    }

    @Override
    public void createNewReview(ClientReview clientReview) {
        save(clientReview);
    }

    @Override
    public void updateNewReview(ClientReview clientReview) {

    }

    @Override
    public void deleteNewReview(ClientReview clientReview) {

    }
}
