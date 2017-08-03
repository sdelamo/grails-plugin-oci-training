package com.objectcomputing.model

import groovy.transform.CompileStatic
import groovy.transform.ToString

@ToString
@CompileStatic
class Offering {
    Long id
    String course
    String dates
    String time
    String instructors
    String hours
    Track track

    String getEnrollmentLink() {
        "https://objectcomputing.com/index.php/training/register/offering/$id"
    }
    boolean soldOut
}
