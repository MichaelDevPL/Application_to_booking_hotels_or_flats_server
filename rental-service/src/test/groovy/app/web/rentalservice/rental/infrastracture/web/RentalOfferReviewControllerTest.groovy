package app.web.rentalservice.rental.infrastracture.web

import app.web.rentalservice.rental.infrastracture.web.configuration.TestDataConfig
import groovyx.net.http.RESTClient
import spock.lang.Specification

class RentalOfferReviewControllerTest extends Specification {

    static String testURL = "http://localhost:8770"

    RESTClient restClient
    TestDataConfig testData

    def setup() {
        restClient = new RESTClient(testURL)
        testData = new TestDataConfig()
    }

    def 'Create new rental review and get all reviews belong to test user'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData

        when:
        def response = testData.addNewReview(createOfferResponse)

        then:
        response.status == 200
        !response.responseData.empty

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

    def 'Delete review'() {
        setup:
        def createOfferResponse = testData.createTestOffer().responseData
        def createNewReviewResp = testData.addNewReview(createOfferResponse)

        when:
        restClient.delete(path: '/review/delete/' + createNewReviewResp
                .responseData.first().id.toString())
        def allTestUserReviewsResp = restClient.get(path: '/review/get-all-account-reviews/testUser')

        then:
        allTestUserReviewsResp.status == 200
        !allTestUserReviewsResp.responseData.empty
        allTestUserReviewsResp.responseData.size > 0

        cleanup:
        testData.deleteTestOffer(createOfferResponse.offerId)
    }

}