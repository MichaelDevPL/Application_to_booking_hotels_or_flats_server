package app.web.rentalservice.rental.domain.dto;

import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.util.DtoAssembler;
import org.springframework.stereotype.Service;

@Service
public class ReviewAssembler implements DtoAssembler<ReviewDTO, ClientReview> {

    @Override
    public ReviewDTO assemble(ClientReview entityObject) {
        return new ReviewDTO(entityObject.getId(), entityObject.getStarRating(),
                entityObject.getComment(), entityObject.getCreatedAt(),
                entityObject.getAccountNick(), entityObject.getRentalOffer().getId());
    }
}
