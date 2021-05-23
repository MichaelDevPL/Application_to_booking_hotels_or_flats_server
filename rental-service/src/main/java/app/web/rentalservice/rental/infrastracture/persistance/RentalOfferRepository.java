package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.dto.CreateOfferResponseDTO;
import app.web.rentalservice.rental.domain.dto.DataToSearchForOffersDTO;

import java.util.List;

public interface RentalOfferRepository {

    List<RentalOffer> getOffersMatchingTheParameters(DataToSearchForOffersDTO dataToSearchForOffersDto);
    List<RentalOffer> getOfferByOfferOwnerId(long userId);
    RentalOffer getRentalOfferById (long id);

    CreateOfferResponseDTO createRentalOffer(RentalOffer newRentalOffer);
    void updateRentalOffer(RentalOffer rentalOffer);
    void deleteRentalOffer(long offerId);

}
