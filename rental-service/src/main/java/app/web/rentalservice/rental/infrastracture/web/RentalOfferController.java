package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.ClientReview;
import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.domain.dto.*;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferReviewRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferImageRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferScheduleRepository;
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
    private final RentalOfferScheduleRepository rentalOfferScheduleRepository;

    @Autowired
    public RentalOfferController(RentalOfferRepository rentalOfferRepository,
                                 RentalOfferAssembler offerAssembler,
                                 RentalOfferScheduleRepository rentalOfferScheduleRepository) {
        this.rentalOfferRepository = rentalOfferRepository;
        this.offerAssembler = offerAssembler;
        this.rentalOfferScheduleRepository = rentalOfferScheduleRepository;

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

    @PostMapping("/create-reservation")
    public void saveReservation(@RequestBody NewReservationDTO newReservationDto) {

        this.rentalOfferScheduleRepository.createReservation(new RentalSchedule(
                newReservationDto.getStartDate(),
                newReservationDto.getEndDate(),
                newReservationDto.getClientId(),
                newReservationDto.getPrice(),
                rentalOfferRepository.getRentalOfferById(newReservationDto.getofferId())
        ));
    }



}
