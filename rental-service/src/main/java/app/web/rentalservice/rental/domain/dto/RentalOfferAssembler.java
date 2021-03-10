package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class RentalOfferAssembler implements DtoAssembler<RentalOfferDto, RentalOffer> {

    @Override
    public RentalOfferDto assemble(RentalOffer entityObject) {
        return new RentalOfferDto(entityObject.getId(), entityObject.getTitle(),
                entityObject.getAddress(), entityObject.getCity(), entityObject.getRentalImages(),
                entityObject.getDailyRate());
    }
}
