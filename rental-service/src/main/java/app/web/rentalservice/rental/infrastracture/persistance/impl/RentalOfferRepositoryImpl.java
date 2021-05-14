package app.web.rentalservice.rental.infrastracture.persistance.impl;

import app.web.rentalservice.rental.domain.RentalOffer;
import app.web.rentalservice.rental.domain.dto.DataToSearchForOffersDto;
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
    public List<RentalOffer> getOffersMatchingTheParameters(DataToSearchForOffersDto dataToSearchForOffersDto) {
        String sqlQuery = "SELECT r FROM RentalOffer r" +
                " WHERE r.city=:city" +
                " and (r.bedrooms=:bedrooms or r.guests=:guests)" +
                " and not EXISTS (" +
                " SELECT rs FROM RentalSchedule rs" +
                " WHERE rs.startRentDate BETWEEN :startDate and :endDate" +
                " and rs.endRentDate BETWEEN :startDate and :endDate and rs.rentalOffer.id = r.id )";

        Query query = em.createQuery(sqlQuery);

        query.setParameter("city", dataToSearchForOffersDto.getCity());
        query.setParameter("bedrooms", dataToSearchForOffersDto.getNumberOfRoom());
        query.setParameter("guests", dataToSearchForOffersDto.getNumberOfGuest());
        query.setParameter("startDate", dataToSearchForOffersDto.getStartDate());
        query.setParameter("endDate", dataToSearchForOffersDto.getEndDate());

        return (List<RentalOffer>) query.getResultList();
    }

    @Override
    public List<RentalOffer> getOfferByOfferOwnerId(long userId) {
        return null;
    }

    @Override
    public RentalOffer getRentalOfferById(long id) {
        return em.find(RentalOffer.class, id);
    }

    @Override
    public boolean createRentalOffer(RentalOffer newRentalOffer) {
        RentalOffer savedRentalOffer = save(newRentalOffer);

        return existsById(savedRentalOffer.getId());
    }

    @Override
    public void updateRentalOffer(RentalOffer rentalOffer) {
        save(rentalOffer);
    }

    @Override
    public void deleteRentalOffer(RentalOffer rentalOffer) {
        delete(rentalOffer);
    }

}