package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalOffer;

import java.util.List;
import java.util.Optional;

public interface RentalOfferRepository {

    Optional<List<RentalOffer>> getRentalOfferByClientPreferences();
    Optional<List<RentalOffer>> getAllRentalOfferByUserId(long userId);

    void createRentalOffer(RentalOffer rentalOffer);
    void updateRentalOffer(RentalOffer rentalOffer);
    void deleteRentalOffer(RentalOffer rentalOffer);

    /*_____________________________________________________________________________________________________________________________*/
    Optional<List<RentalOffer>> getAllTestValium();
}
