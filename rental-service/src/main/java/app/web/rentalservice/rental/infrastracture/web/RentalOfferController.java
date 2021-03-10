package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.dto.DataToSearchForOffersDto;
import app.web.rentalservice.rental.domain.dto.RentalOfferAssembler;
import app.web.rentalservice.rental.domain.dto.RentalOfferDto;
import app.web.rentalservice.rental.infrastracture.persistance.RentalScheduleRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalImageRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
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
    private final RentalImageRepository rentalImageRepository;
    private final RentalScheduleRepository rentalScheduleRepository;

    @Autowired
    public RentalOfferController(RentalOfferRepository rentalOfferRepository,
                                 RentalOfferAssembler offerAssembler,
                                 RentalImageRepository rentalImageRepository,
                                 RentalScheduleRepository rentalScheduleRepository) {
        this.rentalOfferRepository = rentalOfferRepository;
        this.offerAssembler = offerAssembler;
        this.rentalImageRepository = rentalImageRepository;
        this.rentalScheduleRepository = rentalScheduleRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Boolean> createNewRentalOffer (@RequestBody RentalOffer newRentalOffer){

        newRentalOffer.getRentalImages().forEach(rentalImage -> {
            rentalImage.setRentalOffer(newRentalOffer);
        });

        return new ResponseEntity<Boolean>(rentalOfferRepository.createRentalOffer(newRentalOffer), HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<RentalOfferDto>> searchOfferByData (@RequestBody DataToSearchForOffersDto dataToSearchForOffersDto){

        List<RentalOfferDto> rentalOfferList = this.rentalOfferRepository.getOffersMatchingTheParameters(dataToSearchForOffersDto)
                .stream().map(offerAssembler::assemble).collect(Collectors.toList());

        return new ResponseEntity<List<RentalOfferDto>>(rentalOfferList, HttpStatus.OK);
    }

}
