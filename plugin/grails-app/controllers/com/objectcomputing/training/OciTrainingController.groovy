package com.objectcomputing.training

import com.objectcomputing.model.Offering
import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.transform.CompileStatic

@CompileStatic
class OciTrainingController implements GrailsConfigurationAware {

    static responseFormats = ['json']

    boolean refreshEnabled

    OciTrainingService ociTrainingService

    def index(Long trackId) {
        Set<Offering> offerings = trackId ? TrainingOfferings.findAllByTrack(trackId) : TrainingOfferings.get()
        respond offerings
    }

    def tracks() {
        Map model = [tracks: TrainingOfferings.findAllTracks()]
        respond model
    }

    def refresh() {
        if ( !refreshEnabled ) {
            render status: 404
            return
        }
        ociTrainingService.refreshTrainingOfferings()

        respond 'OK'
    }

    @Override
    void setConfiguration(Config co) {
        refreshEnabled = co.getProperty('com.objectcomputing.training.refresh.enabled', Boolean)
    }
}