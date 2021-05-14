package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.ClientReview;

import java.util.List;

public interface RentalOfferReviewRepository {

    public List<ClientReview> getAllReviewByAccountNick(String accountNick);

    public void createReview (ClientReview clientReview);

    public void deleteReview (long reviewId);

}
