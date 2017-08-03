package com.objectcomputing

import com.objectcomputing.geb.TrainingScheduleBrowser
import com.objectcomputing.model.Offering
import spock.lang.Specification

class TrainingScheduleBrowserSpec extends Specification {

    def "fetch offerings"() {

        given:
        TrainingScheduleBrowser browser = new TrainingScheduleBrowser()

        when:
        Set<Offering> offerings = browser.offerings()

        offerings.each { println it }

        then:
        offerings
    }
}
