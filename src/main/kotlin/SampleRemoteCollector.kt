package org.example

import base.boudicca.SemanticKeys
import base.boudicca.model.Event
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import remotecollector.RemoteCollectorApi
import remotecollector.model.EventCollection
import remotecollector.model.HttpCall
import java.time.Instant
import java.time.OffsetDateTime
import java.time.temporal.ChronoUnit

@RestController
class SampleRemoteCollector : RemoteCollectorApi {

    private var nextCollectionTime: Instant? = null
    private var cache: EventCollection? = null

    @GetMapping("/collectEvents")
    @ResponseBody
    override fun collectEvents(): EventCollection {
        return getFromCacheOrCollect()
    }

    /**
     * please always make sure to cache your event collection for an appropriate amount of time to
     * avoid too much load on servers your scrape.
     * !!! this is especially important if you scrape third party sites !!!
     */
    private fun getFromCacheOrCollect(): EventCollection {
        synchronized(this) {
            if (shouldCollect()) {
                cache = doCollect()
                // we cache for 12 hours
                nextCollectionTime = Instant.now().plus(12, ChronoUnit.HOURS)
            }
            return cache!!
        }
    }

    /**
     * returns if a new collection should be executed or not
     */
    private fun shouldCollect(): Boolean {
        val nextCollectionTime = this.nextCollectionTime
        // if null there was no collection before, so we return true, otherwise check if the nextCollectionTime is already in the past
        return nextCollectionTime == null || nextCollectionTime.isBefore(Instant.now())
    }

    /**
     * actually do a collection, this is called after checking for caches
     */
    private fun doCollect(): EventCollection {
        return EventCollection(
            createSampleEventsList(),
            createSampleHttpCalls(),
            createSampleLogLines(),
            2,
            0
        )
    }

    /**
     * creates some sample events
     */
    private fun createSampleEventsList(): List<Event> {
        return listOf(
            Event(
                "event 1", OffsetDateTime.now(), mapOf(
                    SemanticKeys.DESCRIPTION to "fancy awesome event!",
                    SemanticKeys.SOURCES to "sample remote collector"
                )
            ), Event(
                "event 2", OffsetDateTime.now().plusDays(1), mapOf(
                    SemanticKeys.LOCATION_NAME to "fancy awesome location",
                    SemanticKeys.SOURCES to "sample remote collector"
                )
            )
        )
    }

    /**
     * create some sample HttpCalls
     */
    private fun createSampleHttpCalls(): List<HttpCall> {
        return listOf(
            HttpCall(
                "http://example.org/event",
                200,
                OffsetDateTime.now().minusSeconds(2),
                OffsetDateTime.now().minusSeconds(1),
                null
            )
        )
    }

    /**
     * create some sample loglines
     */
    private fun createSampleLogLines(): List<String> {
        return listOf(
            "logging something",
            "logging another thing"
        )
    }
}