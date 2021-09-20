package app.web.rentalservice.rental.infrastracture.web

import app.web.rentalservice.rental.infrastracture.web.configuration.TestDataConfig
import groovyx.net.http.RESTClient
import spock.lang.Specification

class RentalOfferControllerTest extends Specification {

    static String testURL = "http://localhost:8770"

    RESTClient restClient
    TestDataConfig testData

    def setup() {
        restClient = new RESTClient(testURL)
        testData = new TestDataConfig()
    }

    def 'User should be able to perform post request to create new offer'() {
        when:
        def responseData = testData.createTestOffer()

        then:
        responseData.status == 200
        responseData.responseData.createdSuccessfully == true

        cleanup:
        testData.deleteTestOffer(responseData.responseData.offerId)

    }

    def 'User should be able to perform update request to change offer data'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def getOfferDataResponse = restClient.get(path: '/offer/' + createOfferResponse.offerId.toString())

        when:
        getOfferDataResponse.responseData.guests = 1
        restClient.put(path: '/offer/update', body: getOfferDataResponse.responseData,
                requestContentType: 'application/json')
        def response = restClient.get(path: '/offer/' + createOfferResponse.offerId.toString())

        then:
        response.status == 200
        !response.responseData.empty
        response.responseData.guests == 1

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

    def 'user should be able to perform post request by specify data to get offer list'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def requestBody = [city   : 'Cracow, Poland', startDate: '2021-03-15',
                           endDate: '2021-03-25', numberOfGuest: '4', numberOfRoom: '2']

        when:
        def response = restClient.post(path: '/offer/search',
                body: requestBody, requestContentType: 'application/json')

        then:
        response.status == 200
        !response.responseData.empty

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

    def 'User should be able to perform post request by offerID and get data about offer'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData

        when:
        def response = restClient.get(path: '/offer/' + createOfferResponse.offerId.toString())

        then:
        response.status == 200
        response.responseData.id == createOfferResponse.offerId

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)

    }

    def 'user should be able to perform post request to create reservation'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData

        when:
        def response = testData.addNewOfferReservation(createOfferResponse)

        then:
        response.status == 200
        !response.responseData.empty
        response.responseData.rentalSchedule.size() == 1

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

    def 'Delete select offer image'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def getOfferDataResponse = restClient.get(path: '/offer/' + createOfferResponse.offerId.toString()).responseData

        when:
        restClient.delete(path: '/offer/deleteImage/' + getOfferDataResponse.rentalImages.first().id.toString())
        getOfferDataResponse = restClient.get(path: '/offer/' + createOfferResponse.offerId.toString())

        then:
        getOfferDataResponse.status == 200
        !getOfferDataResponse.responseData.empty
        getOfferDataResponse.responseData.rentalImages.size() == 1

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

}
