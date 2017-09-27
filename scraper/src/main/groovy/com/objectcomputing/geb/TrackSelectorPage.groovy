package com.objectcomputing.geb

import com.objectcomputing.model.Track
import geb.Page

class TrackSelectorPage extends Page {

    static url = '/training/schedule/'

    static content = {
        trackSelector {  $("select", name: 'track') }
        trackOptions {  trackSelector.$('option') }
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

}
