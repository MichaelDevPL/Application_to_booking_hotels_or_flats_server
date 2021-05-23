package app.web.rentalservice.rental.infrastracture.web.configuration

import groovyx.net.http.RESTClient

class TestDataConfig {

    static String testURL = "http://localhost:8770"

    RESTClient restClient = new RESTClient(testURL)

    def createTestOffer() {
        def requestBody = [title      : 'Bed&Bath Luxury Apartments',
                           description: 'Offering an outdoor pool and located just 10 minute' +
                                   ' drive from Walt Disney World, The Grove Resort & Water Park Orlando is located in Kissimmee.' +
                                   ' The resort has an on-site bar where guests can enjoy a drink.',
                           city       : 'Cracow, Poland', address: 'ul. Halicka 1',
                           category   : 'APARTMENT', rentalImages: [[path: '1/test/test.png'], [path: '1/test/test.png']],
                           dailyRate  : '120', bedrooms: '2',
                           guests     : '4', offerOwnerId: '1',
                           createdAt  : '2021-03-15']

        return restClient.post(path: '/offer/create', body: requestBody, requestContentType: 'application/json')
    }

    def deleteTestOffer(def offerId) {
        return restClient.delete(path: '/offer/delete/' + offerId)
    }
}
