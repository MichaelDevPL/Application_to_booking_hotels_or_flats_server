package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalOfferImage;

public interface RentalImageRepository {

    void createRentalOfferImage(RentalOfferImage rentalOfferImage);

    void updateRentalOfferImage(RentalOfferImage rentalOfferImage);

    void deleteRentalOfferImage(RentalOfferImage rentalOfferImage);

}
