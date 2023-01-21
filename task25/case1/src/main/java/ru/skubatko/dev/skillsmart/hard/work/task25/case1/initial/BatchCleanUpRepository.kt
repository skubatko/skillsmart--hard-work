package ru.skubatko.dev.skillsmart.hard.work.task25.case1.initial

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
interface  BatchCleanUpRepository : JpaRepository<BatchEntity, Long> {
}
