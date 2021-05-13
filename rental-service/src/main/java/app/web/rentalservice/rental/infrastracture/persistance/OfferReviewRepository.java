package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.ClientReview;

import java.util.Optional;

public interface OfferReviewRepository {

    public Optional<ClientReview> findReviewById(long id);

    public void createNewReview (ClientReview clientReview);

    public void updateNewReview (ClientReview clientReview);

    public void deleteNewReview (ClientReview clientReview);

}
