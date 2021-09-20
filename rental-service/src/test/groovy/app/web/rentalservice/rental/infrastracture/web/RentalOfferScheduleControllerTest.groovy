package app.web.rentalservice.rental.infrastracture.web

import app.web.rentalservice.rental.infrastracture.web.configuration.TestDataConfig
import groovyx.net.http.RESTClient
import spock.lang.Specification

class RentalOfferScheduleControllerTest extends Specification {

    static String testURL = "http://localhost:8770"

    RESTClient restClient
    TestDataConfig testData

    def setup() {
        restClient = new RESTClient(testURL)
        testData = new TestDataConfig()
    }

    def 'Get all reservation belonging to specify user'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def createReservationResponse = testData.addNewOfferReservation(createOfferResponse)

        when:
        def getReservationsByUserId = restClient.get(path: '/schedule/user-reservations/1')

        then:
        getReservationsByUserId.status == 200
        !getReservationsByUserId.responseData.empty

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)

    }

    def 'Get all bookings made on the offer'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def createReservationResponse = testData.addNewOfferReservation(createOfferResponse)

        when:
        def getReservations = restClient.get(path: '/schedule/' + createOfferResponse.offerId.toString())

        then:
        getReservations.status == 200
        !getReservations.responseData.empty

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)

    }

    def 'Get history user reservation'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def createExpiredReservationResponse = testData.addExpired0fferReservation(createOfferResponse)

        when:
        def getReservationsHistory = restClient.get(path: '/schedule/history-user-reservations/1')

        then:
        getReservationsHistory.status == 200
        !getReservationsHistory.responseData.empty

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

    def 'Delete reservation'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def createReservationResponse = testData.addNewOfferReservation(createOfferResponse).responseData

        when:
        restClient.delete(path: '/schedule/delete-reservation/' + createReservationResponse.rentalSchedule.first().id.toString())
        def getReservationsByUserId = restClient.get(path: '/schedule/user-reservations/1')

        then:
        getReservationsByUserId.status == 200
        !getReservationsByUserId.responseData.empty
        getReservationsByUserId.responseData.size() == 1

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }
}