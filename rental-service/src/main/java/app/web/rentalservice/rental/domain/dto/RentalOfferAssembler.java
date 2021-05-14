package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class RentalOfferAssembler implements DtoAssembler<RentalOfferDTO, RentalOffer> {

    @Override
    public RentalOfferDTO assemble(RentalOffer entityObject) {
        return new RentalOfferDTO(entityObject.getId(), entityObject.getTitle(), entityObject.getDescription(),
                entityObject.getRentalImages(), entityObject.getDailyRate(),
                entityObject.getClientReviews().stream()
                        .mapToDouble(ClientReview::getStarRating).average().orElse(0.0));
    }
}
