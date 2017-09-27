package com.objectcomputing.geb

import com.objectcomputing.model.Offering
import geb.Page

class TrainingSchedulePage extends Page {
    public static final String INTERNAL_LINK = '#schedule-offering-'
    public static final String WINDOW_LOCATION = "window.location = '$INTERNAL_LINK"

    static url = '/training/schedule'

    @Override
    String convertToPath(Object[] args) {
        if ( args.size() > 0 ) {
            return "?track=${args[0]}"
        }
    }

    static content = {
        offeringRows { $('table.offerings tbody tr') }
    }

    Set<Offering> offerings() {
        Set<Offering> offerings = []
        for ( int i = 0;  i < offeringRows.size();  i++ ) {
            def offeringRow = offeringRows.getAt(i)
            def offeringId = offeringRow.getAttribute('onclick')
                    .replaceAll(WINDOW_LOCATION, '')
                    .replaceAll('\';', '') as Long

            Offering offering = new Offering()
            offering.with {
                id = offeringId
                course = offeringRow.$('td', 0).text()
                dates =  offeringRow.$('td', 1).text()
                time = offeringRow.$('td', 2).text()
                instructors = offeringRow.$('td', 3).text()
                hours =  offeringRow.$('td', 4).text()
            }
            offerings << offering
        }
        offerings
    }
}
