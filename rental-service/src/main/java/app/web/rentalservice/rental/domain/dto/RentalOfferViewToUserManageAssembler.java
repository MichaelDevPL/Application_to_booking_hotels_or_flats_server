package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class RentalOfferViewToUserManageAssembler implements DtoAssembler<RentalOfferViewToUserManageDTO, RentalOffer> {
    @Override
    public RentalOfferViewToUserManageDTO assemble(RentalOffer entityObject) {
        return new RentalOfferViewToUserManageDTO(entityObject.getId(),
                entityObject.getTitle(), entityObject.getCategory(),
                entityObject.getAddress(), entityObject.getCity());
    }
}
