package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalSchedule;

import java.util.List;
import java.util.Optional;

public interface RentalOfferScheduleRepository {

    public List<RentalSchedule> getReservationsByClientId(long clientId);

    public List<RentalSchedule> getReservationsByRentalOfferId(long rentalOfferId);

    public List<RentalSchedule> getHistoryReservationsByClientId(long clientId);

    void createReservation(RentalSchedule rentalSchedule);

    void deleteReservation(long reservationId);
}
