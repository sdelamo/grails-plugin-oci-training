package com.objectcomputing.training

import com.objectcomputing.model.Offering
import groovy.transform.CompileStatic

@CompileStatic
class TrainingOfferings {

    private static Set<Offering> offerings = []

    static Set<Offering> get() {
        offerings
    }

    static Set<Offering> findAllByTrack(Long trackId) {
        offerings.findAll { it.track?.id == trackId }
    }

    static Set<String> findAllTracks() {
        offerings.collect { it.track } as Set<String>
    }

    static void set(Set<Offering> offerings) {
        this.offerings = offerings
    }

}
