package remotecollector

import remotecollector.model.EventCollection

//TODO remove after real remote-collector-api is published
interface RemoteCollectorApi {
    fun collectEvents(): EventCollection
}