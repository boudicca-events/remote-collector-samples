package remotecollector.model

import base.boudicca.model.Event
import remotecollector.model.HttpCall

//TODO remove after real remote-collector-api is published
data class EventCollection(
    val events: List<Event>,
    val httpCalls: List<HttpCall>?,
    val logLines: List<String>?,
    val warningCount: Int?,
    val errorCount: Int?,
)