package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class BookedOfferAssembler implements DtoAssembler<BookedOfferDTO, RentalSchedule> {

    @Override
    public BookedOfferDTO assemble(RentalSchedule entityObject) {
        return new BookedOfferDTO(entityObject.getId(), entityObject.getRentalOffer().getTitle(),
                entityObject.getStartRentDate(), entityObject.getEndRentDate(),
                entityObject.getPrice(), entityObject.getRentalOffer().getId());
    }
}
