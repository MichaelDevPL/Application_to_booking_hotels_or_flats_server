package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalImage;

public interface RentalOfferImageRepository {

    void createRentalOfferImage(RentalImage rentalImage);

    void updateRentalOfferImage(RentalImage rentalImage);

    void deleteRentalOfferImage(long id);

}
