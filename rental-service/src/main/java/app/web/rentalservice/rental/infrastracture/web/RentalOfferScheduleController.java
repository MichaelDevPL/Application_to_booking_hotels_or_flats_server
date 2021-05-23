package app.web.rentalservice.rental.infrastracture.web;

import app.web.rentalservice.rental.domain.RentalSchedule;
import app.web.rentalservice.rental.domain.dto.ReservationAssembler;
import app.web.rentalservice.rental.domain.dto.ReservationDTO;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/schedule")
public class RentalOfferScheduleController {

    private final RentalOfferScheduleRepository rentalOfferScheduleRepository;
    private final ReservationAssembler reservationAssembler;

    @Autowired
    public RentalOfferScheduleController(RentalOfferScheduleRepository rentalOfferScheduleRepository, ReservationAssembler reservationAssembler) {
        this.rentalOfferScheduleRepository = rentalOfferScheduleRepository;
        this.reservationAssembler = reservationAssembler;
    }

    @GetMapping("/user-reservations/{clientId}")
    public ResponseEntity<List<ReservationDTO>> getAllUserReservations(@PathVariable long clientId) {

        return new ResponseEntity<List<ReservationDTO>>(
                this.rentalOfferScheduleRepository.getReservationsByClientId(clientId)
                .stream()
                .map(reservationAssembler::assemble)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping("/{rentalOfferId}")
    public ResponseEntity<List<RentalSchedule>> getOfferScheduleByRentalOfferId(@PathVariable long rentalOfferId) {

        return new ResponseEntity<List<RentalSchedule>>(
                this.rentalOfferScheduleRepository.getReservationsByRentalOfferId(rentalOfferId)
                , HttpStatus.OK);
    }

    @GetMapping("/history-user-reservations/{clientId}")
    public ResponseEntity<List<ReservationDTO>> getHistoryReservations(@PathVariable long clientId) {

        return new ResponseEntity<List<ReservationDTO>>(
                this.rentalOfferScheduleRepository.getHistoryReservationsByClientId(clientId)
                .stream()
                .map(reservationAssembler::assemble)
                .collect(Collectors.toList()), HttpStatus.OK);
    }

    @DeleteMapping("/delete-reservation/{reservationId}")
    public void deleteReservation(@PathVariable long reservationId) {
        this.rentalOfferScheduleRepository.deleteReservation(reservationId);
    }
}
