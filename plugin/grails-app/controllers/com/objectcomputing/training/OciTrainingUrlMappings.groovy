package com.objectcomputing.training

class OciTrainingUrlMappings {

    static mappings = {
        "/api/training/$trackId?"(controller: 'ociTraining', action: 'index', method: "GET")
        "/api/training/tracks"(controller: 'ociTraining', action: 'tracks', method: "GET")
        "/api/training/refresh"(controller: 'ociTraining', action: 'refresh', method: "POST")
    }
}
