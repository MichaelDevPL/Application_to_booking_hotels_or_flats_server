package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.domain.dto.NewOfferReviewDTO;
import app.web.rentalservice.rental.domain.dto.ReviewAssembler;
import app.web.rentalservice.rental.domain.dto.ReviewDTO;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/review")
public class RentalOfferReviewController {

    private final RentalOfferReviewRepository rentalOfferReviewRepository;
    private final RentalOfferRepository rentalOfferRepository;
    private final ReviewAssembler reviewAssembler;

    public RentalOfferReviewController(RentalOfferReviewRepository rentalOfferReviewRepository,
                                       RentalOfferRepository rentalOfferRepository,
                                       ReviewAssembler reviewAssembler) {
        this.rentalOfferReviewRepository = rentalOfferReviewRepository;
        this.rentalOfferRepository = rentalOfferRepository;
        this.reviewAssembler = reviewAssembler;
    }

    @PostMapping("/new-review")
    public void createOfferReview(@RequestBody NewOfferReviewDTO newReview) {

        this.rentalOfferReviewRepository.createReview(new ClientReview(
                newReview.getId(),
                newReview.getStarRating(),
                newReview.getComment(),
                newReview.getCreatedAt(),
                newReview.getAccountNick(),
                rentalOfferRepository.getRentalOfferById(newReview.getRentalOfferId())
        ));
    }

    @GetMapping("/get-all-account-reviews/{accountNick}")
    public ResponseEntity<List<ReviewDTO>> getReviewsBelongToAccount(@PathVariable String accountNick) {

        return new ResponseEntity<List<ReviewDTO>>(this.rentalOfferReviewRepository.getAllReviewByAccountNick(accountNick)
                .stream()
                .map(reviewAssembler::assemble)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{reviewId}")
    public void deleteReview(@PathVariable long reviewId) {
        this.rentalOfferReviewRepository.deleteReview(reviewId);
    }
}
