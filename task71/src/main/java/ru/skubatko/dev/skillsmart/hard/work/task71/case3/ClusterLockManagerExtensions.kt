package ru.skubatko.dev.skillsmart.hard.work.task71.case3

import java.time.Duration

suspend inline fun <T> ClusterLockManager.withClusterLock(
    name: String = "lock",
    atLeastFor: Duration = ZERO,
    atMostFor: Duration = atLeastFor,
    ifLocked: () -> T? = { null },
    action: () -> T,
): T? {
    require(name.isNotEmpty()) { "Lock name must not be empty" }

    val now = now()

    val lockInfo = ClusterLockInfo(
        name = name,
        at = now,
        atLeastUntil = now + atLeastFor,
        atMostUntil = now + atMostFor.coerceAtLeast(atLeastFor)
    )

    return try {
        tryLock(lockInfo)
        action()
    } catch (e: ClusterLockExistsException) {
        ifLocked()
    } catch (e: Throwable) {
        throw e
    } finally {
        release(lockInfo)
    }
}
