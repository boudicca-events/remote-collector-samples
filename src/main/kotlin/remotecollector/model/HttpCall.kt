package remotecollector.model

import java.time.OffsetDateTime

//TODO remove after real remote-collector-api is published
data class HttpCall(
    val url: String,
    val responseCode: Int,
    val startTime: OffsetDateTime,
    val endTime: OffsetDateTime,
    val postParams: String?
)
