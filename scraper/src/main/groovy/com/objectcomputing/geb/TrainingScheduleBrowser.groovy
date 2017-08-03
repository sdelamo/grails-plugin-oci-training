package com.objectcomputing.geb

import com.objectcomputing.ObjectComputingWebsite
import com.objectcomputing.model.Offering
import com.objectcomputing.model.Track
import geb.Browser

class TrainingScheduleBrowser {

    public static final String ALL_TRACKS = 'All Tracks'

    Set<Offering> offerings() {
        def browser = new Browser()
        browser.baseUrl = ObjectComputingWebsite.BASE_URL
        TrainingSchedulePage page = browser.to TrainingSchedulePage

        Set<Offering> offerings = []

        Set<Track> tracks = page.tracks().findAll { it.name != 'All Tracks' }
        for (Track track : tracks ) {
            browser.go "${ObjectComputingWebsite.BASE_URL}${TrainingSchedulePage.url}?track=${track.id}"
            page = browser.page TrainingSchedulePage
            Set<Offering> trackOfferings = page.offerings()
            trackOfferings.each {
                it.track = track
            }
            offerings += trackOfferings
        }

        for ( int i = 0; i < offerings.size(); i++ ) {
            Offering offering = offerings[i]
            browser.to TrainingSchedulePage, offering.id
            offering.soldOut = page.isSoldOut()
        }

        offerings
    }
}
