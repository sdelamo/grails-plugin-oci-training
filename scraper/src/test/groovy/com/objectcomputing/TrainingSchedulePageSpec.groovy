package com.objectcomputing

import com.objectcomputing.geb.TrainingScheduleBrowser
import com.objectcomputing.geb.TrainingSchedulePage
import com.objectcomputing.model.Offering
import geb.Browser
import spock.lang.Specification

class TrainingSchedulePageSpec extends Specification {

    def "retrieve tracks"() {
        given:

        def browser = new Browser()
        browser.baseUrl = ObjectComputingWebsite.BASE_URL

        when:
        TrainingSchedulePage page = browser.to TrainingSchedulePage

        then:
        page.tracks().contains 'Groovy and Grails Programming'

        when:
        Set<Offering> offerings = page.offerings()

        then:
        offerings

        when:


        then:
        offerings
        offerings.each { Offering offering ->
            println offering
            assert offering.id
            assert offering.course
            assert offering.dates
            assert offering.time
            assert offering.instructors
            assert offering.hours
        }

    }
}
