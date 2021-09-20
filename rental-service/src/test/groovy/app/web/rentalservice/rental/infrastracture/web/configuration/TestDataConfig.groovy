package app.web.rentalservice.rental.infrastracture.web.configuration

import groovy.time.TimeCategory
import groovyx.net.http.RESTClient

import java.text.SimpleDateFormat

class TestDataConfig {

    static String testURL = "http://localhost:8770"

    RESTClient restClient = new RESTClient(testURL)

    def createTestOffer() {
        def requestBody = [title      : 'Bed&Bath Luxury Apartments',
                           description: 'Offering an outdoor pool and located just 10 minute' +
                                   ' drive from Walt Disney World, The Grove Resort & Water Park Orlando is located in Kissimmee.' +
                                   ' The resort has an on-site bar where guests can enjoy a drink.',
                           city       : 'Cracow, Poland', address: 'ul. Hali 1',
                           category   : 'APARTMENT', rentalImages: [[path: '1/test/test.png'], [path: '1/test/test.png']],
                           dailyRate  : '120', bedrooms: '2',
                           guests     : '4', offerOwnerId: '1',
                           createdAt  : '2021-03-15']

        return restClient.post(path: '/offer/create', body: requestBody,
                requestContentType: 'application/json')
    }

    def addNewOfferReservation(def offerData) {

        def dateBeginningPeriod
        def dateEndPeriod

        use( TimeCategory ) {
            def startDate = new Date() + 20.day
            def endDate = new Date() + 30.day
            dateBeginningPeriod = new SimpleDateFormat("yyyy-MM-dd").format(startDate)
            dateEndPeriod = new SimpleDateFormat("yyyy-MM-dd").format(endDate)
        }

        def requestBody = [startDate: dateBeginningPeriod,
                           endDate:  dateEndPeriod,
                           price    : '1200', clientId: '1',
                           offerId  : offerData.offerId]

        restClient.post(path: '/offer/create-reservation', body: requestBody,
                requestContentType: 'application/json')

        restClient.post(path: '/offer/create-reservation', body: requestBody,
                requestContentType: 'application/json')

        return restClient.get(path: '/offer/' + offerData.offerId.toString())
    }

    def addExpired0fferReservation(def offerData) {

        def dateBeginningPeriod
        def dateEndPeriod

        use( TimeCategory ) {
            def startDate = new Date() - 20.day
            def endDate = new Date() - 10.day
            dateBeginningPeriod = new SimpleDateFormat("yyyy-MM-dd").format(startDate)
            dateEndPeriod = new SimpleDateFormat("yyyy-MM-dd").format(endDate)
        }

        def requestBody = [startDate: dateBeginningPeriod,
                           endDate:  dateEndPeriod,
                           price    : '1200', clientId: '1',
                           offerId  : offerData.offerId]

        restClient.post(path: '/offer/create-reservation', body: requestBody,
                requestContentType: 'application/json')

        return restClient.get(path: '/offer/' + offerData.offerId.toString())
    }

    def addNewReview(def offerData) {
        def requestBody = [starRating: '5', comment: 'some text..',
                           createdAt: '2021-03-15', accountNick: 'testUser', rentalOfferId: offerData.offerId]
        def requestBodySecond = [starRating: '2', comment: 'some text second..',
                           createdAt: '2021-03-16', accountNick: 'testUser', rentalOfferId: offerData.offerId]

        restClient.post(path: '/review/new-review', body: requestBody,
                requestContentType: 'application/json')

        restClient.post(path: '/review/new-review', body: requestBodySecond,
                requestContentType: 'application/json')

        return restClient.get(path: '/review/get-all-account-reviews/testUser')
    }

    def deleteTestOffer(def offerId) {
        return restClient.delete(path: '/offer/delete/' + offerId.toString())
    }
}
