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
        def response = restClient.get(path: '/offer/' + createOfferResponse.offerId)

        then:
        response.status == 200
        response.responseData.id == createOfferResponse.offerId

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)

    }

    def 'user should be able to perform post request to create reservation'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def requestBody = [startDate: '2021-03-15', endDate: '2021-03-25',
                           price: '1200', clientId: '1',
                           offerId: createOfferResponse.offerId]

        when:
        def response = restClient.post(path: '/offer/create-reservation',
                body: requestBody, requestContentType: 'application/json')

        then:
        response.status == 200

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

}
