package ru.skubatko.dev.skillsmart.hard.work.task25.case1.refactored

import jdk.internal.net.http.common.Log.logTrace
import jdk.nashorn.internal.runtime.logging.Loggable
import org.intellij.lang.annotations.Language
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import ru.sokomishalov.commons.core.log.Loggable
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class BatchCleanUpService(
    private val jdbcTemplate: JdbcTemplate,
    private val appBatchProperties: AppBatchProperties,
) {

    @Transactional
    fun cleanup() {
        kotlin.runCatching {
            logDebug { "cleanup() - start" }
            val deleteBy: LocalDateTime = LocalDateTime.now().minusDays(appBatchProperties.retentionDepth.toDays())
            val limit = appBatchProperties.bulkSize
            logTrace { "cleanup() - trace: deleteBy=$deleteBy, limit=$limit" }

            jdbcTemplate.update(BATCH_STEP_EXECUTION_CONTEXT_CLEANUP_QUERY, deleteBy, limit).also {
                logTrace { "cleanup() - trace: BATCH_STEP_EXECUTION_CONTEXT cleaned up rows=$it" }
            }
            jdbcTemplate.update(BATCH_STEP_EXECUTION_CLEANUP_QUERY, deleteBy, limit).also {
                logTrace { "cleanup() - trace: BATCH_STEP_EXECUTION cleaned up rows=$it" }
            }
            jdbcTemplate.update(BATCH_JOB_EXECUTION_CONTEXT_CLEANUP_QUERY, deleteBy, limit).also {
                logTrace { "cleanup() - trace: BATCH_JOB_EXECUTION_CONTEXT cleaned up rows=$it" }
            }
            jdbcTemplate.update(BATCH_JOB_EXECUTION_PARAMS_CLEANUP_QUERY, deleteBy, limit).also {
                logTrace { "cleanup() - trace: BATCH_JOB_EXECUTION_PARAMS cleaned up rows=$it" }
            }
            jdbcTemplate.update(BATCH_JOB_EXECUTION_CLEANUP_QUERY, deleteBy, limit).also {
                logTrace { "cleanup() - trace: BATCH_JOB_EXECUTION cleaned up rows=$it" }
            }
            jdbcTemplate.update(BATCH_JOB_INSTANCE_CLEANUP_QUERY).also {
                logTrace { "cleanup() - trace: BATCH_JOB_INSTANCE cleaned up rows=$it" }
            }

            logDebug { "cleanup() - end" }
        }.onFailure { logError(it) { "cleanup() - verdict: failed" } }
    }

    private companion object : Loggable {
        @Language("PostgreSQL")
        private val BATCH_STEP_EXECUTION_CONTEXT_CLEANUP_QUERY = """
            |DELETE FROM batch_step_execution_context
            |WHERE step_execution_id IN (
            |    SELECT step_execution_id FROM batch_step_execution WHERE job_execution_id IN (
            |        SELECT job_execution_id 
            |        FROM batch_job_execution 
            |        WHERE end_time < ? 
            |        ORDER BY job_execution_id
            |        FOR UPDATE SKIP LOCKED 
            |        LIMIT ?
            |    )
            |)
            """.trimMargin()
        @Language("PostgreSQL")
        private val BATCH_STEP_EXECUTION_CLEANUP_QUERY = """
            |DELETE FROM batch_step_execution 
            |WHERE job_execution_id IN (
            |        SELECT job_execution_id 
            |        FROM batch_job_execution 
            |        WHERE end_time < ? 
            |        ORDER BY job_execution_id
            |        FOR UPDATE SKIP LOCKED 
            |        LIMIT ?
            |)
            """.trimMargin()
        @Language("PostgreSQL")
        private val BATCH_JOB_EXECUTION_CONTEXT_CLEANUP_QUERY = """
            |DELETE FROM batch_job_execution_context 
            |WHERE job_execution_id IN (
            |        SELECT job_execution_id 
            |        FROM batch_job_execution 
            |        WHERE end_time < ? 
            |        ORDER BY job_execution_id
            |        FOR UPDATE SKIP LOCKED 
            |        LIMIT ?
            |)
            |""".trimMargin()
        @Language("PostgreSQL")
        private val BATCH_JOB_EXECUTION_PARAMS_CLEANUP_QUERY = """
            |DELETE FROM batch_job_execution_params 
            |WHERE job_execution_id IN (
            |        SELECT job_execution_id 
            |        FROM batch_job_execution 
            |        WHERE end_time < ? 
            |        ORDER BY job_execution_id
            |        FOR UPDATE SKIP LOCKED 
            |        LIMIT ?
            |)
            |""".trimMargin()
        @Language("PostgreSQL")
        private val BATCH_JOB_EXECUTION_CLEANUP_QUERY = """
            |DELETE FROM batch_job_execution 
            |WHERE job_execution_id IN (
            |        SELECT job_execution_id 
            |        FROM batch_job_execution 
            |        WHERE end_time < ? 
            |        ORDER BY job_execution_id
            |        FOR UPDATE SKIP LOCKED 
            |        LIMIT ?
            |)
            |""".trimMargin()
        @Language("PostgreSQL")
        private val BATCH_JOB_INSTANCE_CLEANUP_QUERY = """
            |DELETE FROM batch_job_instance WHERE job_instance_id IN (
            |    SELECT bji.job_instance_id FROM batch_job_instance bji
            |    LEFT JOIN batch_job_execution bje ON bji.job_instance_id = bje.job_instance_id
            |    WHERE bje.job_execution_id IS NULL 
            |)
            |""".trimMargin()
    }
}
