package esw.ocs.dsl.highlevel

import csw.command.client.models.framework.LockingResponse
import csw.location.api.javadsl.JComponentType.Assembly
import csw.location.api.javadsl.JComponentType.HCD
import csw.params.core.models.Prefix
import esw.ocs.dsl.script.utils.LockUnlockUtil
import io.kotlintest.specs.WordSpec
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.concurrent.CompletableFuture
import kotlin.time.Duration
import kotlin.time.seconds
import kotlin.time.toJavaDuration

class LockUnlockDslTest : WordSpec(), LockUnlockDsl {
    private val componentName = "test-component"
    private val assembly = Assembly
    private val hcd = HCD
    private val prefix = Prefix("esw")
    private val leaseDuration: Duration = 10.seconds
    private val jLeaseDuration = leaseDuration.toJavaDuration()

    private val mockLockUnlockUtil = mockk<LockUnlockUtil>()
    private val lockingResponse = mockk<LockingResponse>()

    override val lockUnlockUtil: LockUnlockUtil = mockLockUnlockUtil

    init {
        "LockUnlockDsl" should {
            "lockAssembly should delegate to LockUnlockUtil.jLock | ESW-126" {
                every {
                    mockLockUnlockUtil.jLock(componentName, assembly, prefix, jLeaseDuration)
                }.returns(CompletableFuture.completedFuture(lockingResponse))

                lockAssembly(componentName, prefix, jLeaseDuration)
                verify { lockUnlockUtil.jLock(componentName, assembly, prefix, jLeaseDuration) }
            }

            "unlockAssembly should delegate to LockUnlockUtil.jUnlock | ESW-126" {
                every {
                    mockLockUnlockUtil.jUnlock(componentName, assembly, prefix)
                }.returns(CompletableFuture.completedFuture(lockingResponse))

                unlockAssembly(componentName, prefix)
                verify { lockUnlockUtil.jUnlock(componentName, assembly, prefix) }
            }

            "lockHcd should delegate to LockUnlockUtil.jLock | ESW-126" {
                every {
                    mockLockUnlockUtil.jLock(componentName, hcd, prefix, jLeaseDuration)
                }.returns(CompletableFuture.completedFuture(lockingResponse))

                lockHcd(componentName, prefix, jLeaseDuration)
                verify { lockUnlockUtil.jLock(componentName, hcd, prefix, jLeaseDuration) }
            }

            "unlockHcd should delegate to LockUnlockUtil.jUnlock | ESW-126" {
                every {
                    mockLockUnlockUtil.jUnlock(componentName, hcd, prefix)
                }.returns(CompletableFuture.completedFuture(lockingResponse))

                unlockHcd(componentName, prefix)
                verify { lockUnlockUtil.jUnlock(componentName, hcd, prefix) }
            }
        }
    }
}
