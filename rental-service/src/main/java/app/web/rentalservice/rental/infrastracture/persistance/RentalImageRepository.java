package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalImage;

public interface RentalImageRepository {

    void createRentalOfferImage(RentalImage rentalImage);

    void updateRentalOfferImage(RentalImage rentalImage);

    void deleteRentalOfferImage(RentalImage rentalImage);

}
