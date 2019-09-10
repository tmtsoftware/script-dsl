package esw.ocs.dsl.highlevel

import csw.location.models.AkkaLocation
import csw.location.models.ComponentType
import csw.location.models.Location
import csw.params.core.models.Subsystem
import esw.ocs.dsl.nullable
import esw.ocs.impl.dsl.CswServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.future.await

interface LocationServiceKtDsl : CoroutineScope {
    val cswServices: CswServices

    suspend fun listBy(subsystem: Subsystem, componentType: ComponentType): List<AkkaLocation> =
        cswServices.jListBy(subsystem, componentType).await().toList()

    suspend fun listByComponentName(name: String): List<Location> =
        cswServices.jListByComponentName(name).await().toList()

    suspend fun resolveByComponentNameAndType(name: String, componentType: ComponentType): Location? =
        cswServices.jResolveByComponentNameAndType(name, componentType).await().nullable()

    // To be used by Script Writer
    suspend fun resolveSequencer(sequencerId: String, observingMode: String): AkkaLocation =
        cswServices.jResolveSequencer(sequencerId, observingMode).await()

}