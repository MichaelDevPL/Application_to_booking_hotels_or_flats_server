package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.RentalOfferImage;
import app.web.rentalservice.rental.infrastracture.persistance.RentalScheduleRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalImageRepository;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/rent")
public class RentalOfferController {

    private final RentalOfferRepository rentalOfferRepository;
    private final RentalImageRepository rentalImageRepository;
    private final RentalScheduleRepository rentalScheduleRepository;

    public RentalOfferController(RentalOfferRepository rentalOfferRepository,
                                 RentalImageRepository rentalImageRepository,
                                 RentalScheduleRepository rentalScheduleRepository) {

        this.rentalOfferRepository = rentalOfferRepository;
        this.rentalImageRepository = rentalImageRepository;
        this.rentalScheduleRepository = rentalScheduleRepository;
    }

    @PostMapping("/test1")
    public void test(){
        RentalOffer rentalOffer = new RentalOffer();
        RentalSchedule rentalSchedule = new RentalSchedule(new Date(), new Date(), 1, rentalOffer);
        RentalOfferImage rentalOfferImage = new RentalOfferImage("adasdad", rentalOffer);
        rentalOffer.setCreateAt( new Date());
        Set<RentalSchedule> rentalSchedules = new HashSet<>();
        rentalSchedules.add(rentalSchedule);
        rentalOffer.setRentalSchedule(rentalSchedules);
        Set<RentalOfferImage> offerImages = new HashSet<>();
        offerImages.add(rentalOfferImage);
        rentalOffer.setRentalOfferImages(offerImages);
        rentalOfferRepository.createRentalOffer(rentalOffer);
    }

    @PostMapping("/getTest")
    public Optional<List<RentalOffer>> test1(){
        return rentalOfferRepository.getAllTestValium();
    }

}
