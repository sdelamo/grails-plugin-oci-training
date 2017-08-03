package com.objectcomputing.training

import com.objectcomputing.geb.TrainingScheduleBrowser
import com.objectcomputing.model.Offering
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@CompileStatic
class OciTrainingService {

    void refreshTrainingOfferings() {
        TrainingScheduleBrowser browser = new TrainingScheduleBrowser()
        Set<Offering> offerings = browser.offerings()
        log.info('Fetched #{} training offerings', offerings.size())
        offerings.each {
            log.info '{}', it.toString()
        }
        if ( offerings ) {
            TrainingOfferings.set offerings
        }
    }

}