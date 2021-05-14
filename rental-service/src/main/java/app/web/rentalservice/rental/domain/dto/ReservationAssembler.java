package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReservationAssembler implements DtoAssembler<ReservationDTO, RentalSchedule> {

    @Override
    public ReservationDTO assemble(RentalSchedule entityObject) {
        return new ReservationDTO(entityObject.getId(), entityObject.getRentalOffer().getTitle(),
                entityObject.getStartRentDate(), entityObject.getEndRentDate(),
                entityObject.getPrice(), entityObject.getRentalOffer().getId());
    }
}
