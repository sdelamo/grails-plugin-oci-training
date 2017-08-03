package com.objectcomputing.geb

import com.objectcomputing.model.Offering
import com.objectcomputing.model.Track
import geb.Page

class TrainingSchedulePage extends Page {

    public static final String INTERNAL_LINK = '#schedule-offering-'
    public static final String WINDOW_LOCATION = "window.location = '$INTERNAL_LINK"

    static url = '/training/schedule'

    String convertToPath(Object[] args) {
        if ( args.size() > 0 ) {
            return "${TrainingSchedulePage.INTERNAL_LINK}${args[0]}"
        }
    }

    static content = {
        trackSelector {  $("select", name: 'track') }
        trackOptions {  trackSelector.$('option') }
        offeringRows { $('table.offerings tbody tr') }
        modalWindow(required: false) { $('.ws-modal-dialog', 0) }
    }

    void filterByTrack(String trackName) {
        trackSelector = trackName
    }

    Set<Track> tracks() {
        trackOptions.collect {
            Long id
            try {
                id = it.getAttribute('value') as Long
            } catch(NumberFormatException e) {

            }
            new Track(id: id, name: it.text())
        } as Set<Track>
    }

    Set<Offering> offerings() {
        Set<Offering> offerings = []
        for ( int i = 0;  i < offeringRows.size();  i++ ) {
            Offering offering = new Offering()
            offering.with {
                id = offeringRows.getAt(i).getAttribute('onclick').replaceAll(WINDOW_LOCATION, '').replaceAll('\';', '') as Long
                course = offeringRows.getAt(i).$('td', 0).text()
                dates =  offeringRows.getAt(i).$('td', 1).text()
                time = offeringRows.getAt(i).$('td', 2).text()
                instructors = offeringRows.getAt(i).$('td', 3).text()
                hours =  offeringRows.getAt(i).$('td', 4).text()
            }
            offerings << offering
        }
        offerings
    }

    boolean isSoldOut() {
        if ( !modalWindow.empty ) {
            return modalWindow.text().contains('Sold Out')
        }
        false
    }
}
