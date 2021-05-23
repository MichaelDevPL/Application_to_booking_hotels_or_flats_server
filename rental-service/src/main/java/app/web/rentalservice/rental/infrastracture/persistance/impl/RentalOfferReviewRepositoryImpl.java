package app.web.rentalservice.rental.infrastracture.persistance.impl;


import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferReviewRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class RentalOfferReviewRepositoryImpl extends SimpleJpaRepository<ClientReview, Long> implements RentalOfferReviewRepository {

    private EntityManager em;

    public RentalOfferReviewRepositoryImpl(EntityManager em){
        super(ClientReview.class, em);
        this.em = em;
    }

    @Override
    public List<ClientReview> getAllReviewByAccountNick(String accountNick) {
        String sqlQuery = " select cr from ClientReview cr " +
                " join fetch cr.rentalOffer r" +
                " where cr.accountNick=:accountNick" +
                " order by cr.createdAt desc";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("accountNick", accountNick);

        return (List<ClientReview>) query.getResultList();
    }

    @Override
    public void createReview(ClientReview clientReview) {
        save(clientReview);
    }

    @Override
    public void deleteReview(long reviewId) {
        ClientReview clientReview = em.find(ClientReview.class, reviewId);
        delete(clientReview);
    }
}
