package app.web.rentalservice.rental.infrastracture.persistance;

import app.web.rentalservice.rental.domain.RentalSchedule;

import java.util.List;
import java.util.Optional;

public interface RentalScheduleRepository {

    public List<RentalSchedule> getAllByClientId(long clientId);

    void createReserve(RentalSchedule rentalSchedule);

    void updateReserve(RentalSchedule rentalSchedule);

    void deleteReserve(RentalSchedule rentalSchedule);

}
