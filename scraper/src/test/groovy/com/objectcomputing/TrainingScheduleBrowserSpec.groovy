package com.objectcomputing

import com.objectcomputing.geb.TrainingScheduleBrowser
import com.objectcomputing.model.Offering
import spock.lang.Specification

class TrainingScheduleBrowserSpec extends Specification {

    def "fetch offerings"() {

        when:
        Set<Offering> offerings = TrainingScheduleBrowser.offerings()

        offerings.each { Offering offering ->
            println "${offering.soldOut ? 'SOLD OUT': 'AVAILABLE'} ${offering.course} ${offering.track.name} "
        }

        then:
        offerings
    }
}
