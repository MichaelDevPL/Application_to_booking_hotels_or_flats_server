package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalSchedule;

public interface RentalScheduleRepository {

    boolean checkAvailableOfRentalOfferInPeriod();

    void createRentalOfferInSchedule(RentalSchedule rentalSchedule);

    void updateRentalOfferSchedule(RentalSchedule rentalSchedule);

    void deleteRentalOfferSchedule(RentalSchedule rentalSchedule);

}
