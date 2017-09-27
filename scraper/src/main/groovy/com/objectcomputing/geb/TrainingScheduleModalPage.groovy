package com.objectcomputing.geb

import geb.Page

class TrainingScheduleModalPage extends Page {

    static url = '/training/schedule'

    static content = {
        modalWindow(required: false) { $('.ws-modal-dialog', 0) }
    }

    @Override
    String convertToPath(Object[] args) {
        if ( args.size() > 1 ) {
            return "?track=${args[0]}#schedule-offering-${args[1]}"
        }
    }

    boolean isSoldOut() {
        if ( !modalWindow.empty ) {
            return modalWindow.text().contains('Sold Out')
        }
        false
    }
}
