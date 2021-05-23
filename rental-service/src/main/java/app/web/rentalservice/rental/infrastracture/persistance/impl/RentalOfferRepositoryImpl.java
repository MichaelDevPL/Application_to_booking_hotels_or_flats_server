package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.dto.CreateOfferResponseDTO;
import app.web.rentalservice.rental.domain.dto.DataToSearchForOffersDTO;
import app.web.rentalservice.rental.infrastracture.persistance.RentalOfferRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Transactional
@Repository
public class RentalOfferRepositoryImpl extends SimpleJpaRepository<RentalOffer, Long> implements RentalOfferRepository {

    private EntityManager em;

    public RentalOfferRepositoryImpl(EntityManager em){
        super(RentalOffer.class, em);
        this.em = em;
    }

    @Override
    public List<RentalOffer> getOffersMatchingTheParameters(DataToSearchForOffersDTO dataToSearchForOffersDto) {
        String sqlQuery = "select r from RentalOffer r" +
                " where r.city=:city" +
                " and (r.bedrooms=:bedrooms or r.guests=:guests)" +
                " and not exists (" +
                " select rs from RentalSchedule rs" +
                " where rs.startRentDate between :startDate and :endDate" +
                " and rs.endRentDate between :startDate and :endDate and rs.rentalOffer.id = r.id )";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("city", dataToSearchForOffersDto.getCity());
        query.setParameter("bedrooms", dataToSearchForOffersDto.getNumberOfRoom());
        query.setParameter("guests", dataToSearchForOffersDto.getNumberOfGuest());
        query.setParameter("startDate", dataToSearchForOffersDto.getStartDate());
        query.setParameter("endDate", dataToSearchForOffersDto.getEndDate());

        return (List<RentalOffer>) query.getResultList();
    }

    @Override
    public List<RentalOffer> getOfferByOfferOwnerId(long OfferOwnerId) {
        String sqlQuery = "select r from RentalOffer r" +
                " where r.offerOwnerId=:offerOwnerId";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("offerOwnerId", OfferOwnerId);

        return (List<RentalOffer>) query.getResultList();
    }

    @Override
    public RentalOffer getRentalOfferById(long id) {
        return em.find(RentalOffer.class, id);
    }

    @Override
    public CreateOfferResponseDTO createRentalOffer(RentalOffer newRentalOffer) {
        RentalOffer savedRentalOffer = save(newRentalOffer);

        return new CreateOfferResponseDTO(existsById(savedRentalOffer.getId()), savedRentalOffer.getId());
    }

    @Override
    public void updateRentalOffer(RentalOffer rentalOffer) {
        save(rentalOffer);
    }

    @Override
    public void deleteRentalOffer(long offerId) {
        RentalOffer rentalOffer = em.find(RentalOffer.class, offerId);
        delete(rentalOffer);
    }

}