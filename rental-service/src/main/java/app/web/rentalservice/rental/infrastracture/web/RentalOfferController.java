package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.domain.dto.*;
import app.web.rentalservice.rental.infrastracture.persistance.OfferReviewRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalImageRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/offer")
public class RentalOfferController {

    private final RentalOfferRepository rentalOfferRepository;
    private final RentalOfferAssembler offerAssembler;
    private final BookedOfferAssembler bookedOfferAssembler;
    private final RentalImageRepository rentalImageRepository;
    private final RentalScheduleRepository rentalScheduleRepository;
    private final OfferReviewRepository offerReviewRepository;

    @Autowired
    public RentalOfferController(RentalOfferRepository rentalOfferRepository,
                                 RentalOfferAssembler offerAssembler,
                                 BookedOfferAssembler bookedOfferAssembler,
                                 RentalImageRepository rentalImageRepository,
                                 RentalScheduleRepository rentalScheduleRepository,
                                 OfferReviewRepository offerReviewRepository) {
        this.rentalOfferRepository = rentalOfferRepository;
        this.offerAssembler = offerAssembler;
        this.bookedOfferAssembler = bookedOfferAssembler;
        this.rentalImageRepository = rentalImageRepository;
        this.rentalScheduleRepository = rentalScheduleRepository;
        this.offerReviewRepository = offerReviewRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createNewRentalOffer(@RequestBody RentalOffer newRentalOffer) {

        newRentalOffer.getRentalImages().forEach(rentalImage -> {
            rentalImage.setRentalOffer(newRentalOffer);
        });

        return new ResponseEntity<Boolean>(rentalOfferRepository.createRentalOffer(newRentalOffer), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<RentalOfferDTO>> searchOfferByData(@RequestBody DataToSearchForOffersDto dataToSearchForOffersDto) {

        List<RentalOfferDTO> rentalOfferList = this.rentalOfferRepository.getOffersMatchingTheParameters(dataToSearchForOffersDto)
                .stream().map(offerAssembler::assemble).collect(Collectors.toList());

        return new ResponseEntity<List<RentalOfferDTO>>(rentalOfferList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalOffer> findRentalOfferByID(@PathVariable long id) {
        RentalOffer rentalOffer = this.rentalOfferRepository.getRentalOfferById(id);

        return new ResponseEntity<RentalOffer>(rentalOffer, HttpStatus.OK);
    }

    @PostMapping("/save-reserve")
    public void saveReserve(@RequestBody ReserveOfferDTO reserveOfferDto) {

        this.rentalScheduleRepository.createReserve(new RentalSchedule(
                reserveOfferDto.getStartDate(),
                reserveOfferDto.getEndDate(),
                reserveOfferDto.getClientId(),
                reserveOfferDto.getPrice(),
                rentalOfferRepository.getRentalOfferById(reserveOfferDto.getofferId())
        ));
    }

    @PostMapping("/create-review")
    public void createOfferReview(@RequestBody NewOfferReviewDTO newReview) {

        this.offerReviewRepository.createNewReview(new ClientReview(
                newReview.getId(),
                newReview.getStarRating(),
                newReview.getComment(),
                newReview.getCreatedAt(),
                newReview.getAccountNick(),
                rentalOfferRepository.getRentalOfferById(newReview.getRentalOfferId())
        ));
    }

    @GetMapping("/all-booked-offer-by-user/{clientId}")
    public ResponseEntity<List<BookedOfferDTO>> getAllBookedOfferByUser(@PathVariable long clientId) {

        return new ResponseEntity<List<BookedOfferDTO>>(this.rentalScheduleRepository.getAllByClientId(clientId)
                .stream()
                .map(bookedOfferAssembler::assemble)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

}
