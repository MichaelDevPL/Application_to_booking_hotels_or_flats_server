package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.dto.DataToSearchForOffersDto;

import java.util.List;
import java.util.Optional;

public interface RentalOfferRepository {

    List<RentalOffer> getOffersMatchingTheParameters(DataToSearchForOffersDto dataToSearchForOffersDto);
    List<RentalOffer> getOfferByOfferOwnerId(long userId);

    boolean createRentalOffer(RentalOffer newRentalOffer);
    void updateRentalOffer(RentalOffer rentalOffer);
    void deleteRentalOffer(RentalOffer rentalOffer);

    /*_____________________________________________________________________________________________________________________________*/
//    Optional<List<RentalOffer>> getAllTestValium();
}
