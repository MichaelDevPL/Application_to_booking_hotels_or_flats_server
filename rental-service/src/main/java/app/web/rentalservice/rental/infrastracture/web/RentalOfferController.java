package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.domain.dto.*;
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
    private final RentalOfferImageRepository rentalOfferImageRepository;
    private final RentalOfferAssembler offerAssembler;
    private final RentalOfferViewToUserManageAssembler offerViewToUserManageAssembler;
    private final RentalOfferScheduleRepository rentalOfferScheduleRepository;

    @Autowired
    public RentalOfferController(RentalOfferRepository rentalOfferRepository,
                                 RentalOfferImageRepository rentalOfferImageRepository, RentalOfferAssembler offerAssembler,
                                 RentalOfferViewToUserManageAssembler offerViewToUserManageAssembler,
                                 RentalOfferScheduleRepository rentalOfferScheduleRepository) {
        this.rentalOfferRepository = rentalOfferRepository;
        this.rentalOfferImageRepository = rentalOfferImageRepository;
        this.offerAssembler = offerAssembler;
        this.offerViewToUserManageAssembler = offerViewToUserManageAssembler;
        this.rentalOfferScheduleRepository = rentalOfferScheduleRepository;

    }

    @PostMapping("/create")
    public ResponseEntity<CreateOfferResponseDTO> createNewRentalOffer(@RequestBody RentalOffer newRentalOffer) {

        newRentalOffer.getRentalImages().forEach(rentalImage -> {
            rentalImage.setRentalOffer(newRentalOffer);
        });

        return new ResponseEntity<CreateOfferResponseDTO>(rentalOfferRepository.createRentalOffer(newRentalOffer), HttpStatus.OK);
    }

    @PutMapping("/update")
    public void updateRentalOffer(@RequestBody RentalOffer rentalOffer){

        rentalOffer.getRentalImages().forEach( rentalImage -> {
            rentalImage.setRentalOffer(rentalOffer);
        });

        rentalOffer.getRentalSchedule().forEach( rentalSchedule -> {
            rentalSchedule.setRentalOffer(rentalOffer);
        });

        rentalOffer.getClientReviews().forEach(clientReview -> {
            clientReview.setRentalOffer(rentalOffer);
        });

        this.rentalOfferRepository.updateRentalOffer(rentalOffer);
    }

    @PostMapping("/search")
    public ResponseEntity<List<RentalOfferDTO>> searchOfferByData(@RequestBody DataToSearchForOffersDTO dataToSearchForOffersDto) {

        List<RentalOfferDTO> rentalOfferList = this.rentalOfferRepository.getOffersMatchingTheParameters(dataToSearchForOffersDto)
                .stream().map(offerAssembler::assemble).collect(Collectors.toList());

        return new ResponseEntity<List<RentalOfferDTO>>(rentalOfferList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalOffer> findRentalOfferById(@PathVariable long id) {
        RentalOffer rentalOffer = this.rentalOfferRepository.getRentalOfferById(id);

        return new ResponseEntity<RentalOffer>(rentalOffer, HttpStatus.OK);
    }

    @GetMapping("user/{userId}")
    public ResponseEntity<List<RentalOfferViewToUserManageDTO>> getRentalOfferByUserId(@PathVariable long userId) {
        List<RentalOfferViewToUserManageDTO> rentalOffersViewToUserManage = this.rentalOfferRepository.getOfferByOfferOwnerId(userId)
                .stream().map(offerViewToUserManageAssembler::assemble)
                .collect(Collectors.toList());

        return new ResponseEntity<List<RentalOfferViewToUserManageDTO>>(rentalOffersViewToUserManage , HttpStatus.OK);
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

    @DeleteMapping("/delete/{offerId}")
    public void deleteRentalOffer(@PathVariable long offerId) {
        this.rentalOfferRepository.deleteRentalOffer(offerId);
    }

    @DeleteMapping("/deleteImage/{imageId}")
    public void deleteRentalOfferImage(@PathVariable long imageId) {
        this.rentalOfferImageRepository.deleteRentalOfferImage(imageId);
    }

}
