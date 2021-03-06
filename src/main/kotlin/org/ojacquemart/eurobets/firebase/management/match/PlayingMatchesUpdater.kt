package org.ojacquemart.eurobets.firebase.management.match

import org.ojacquemart.eurobets.firebase.Collections
import org.ojacquemart.eurobets.firebase.config.FirebaseRef
import org.ojacquemart.eurobets.firebase.support.Status
import org.ojacquemart.eurobets.lang.loggerFor

class PlayingMatchesUpdater {

    private val log = loggerFor<PlayingMatchesUpdater>()

    fun update(match: Match, ref: FirebaseRef) {
        log.info("Update match ${match.number}")

        ref.firebase.child(Collections.matches).child(match.number.toString())
                .updateChildren(mapOf("status" to Status.PLAYING.id)) { error, ref ->
                    when (error) {
                        null -> log.debug("Match ${match.number} is set to ${Status.PLAYING}")
                        else -> log.error("Error while updating the match ${match.number}")
                    }
                }
    }

}