package ru.skubatko.dev.skillsmart.hard.work.task68

import java.time.LocalDateTime
import java.time.ZoneId

fun RepairCommandSet.toCommandEntityList(): List<RepairCommandEntity> =
    this.value.map { it.toCommandEntity() }

fun RepairCommand.toCommandEntity(): RepairCommandEntity =
    RepairCommandEntity(
        repairId = this.repairId,
        performedBy = this.performedBy,
        applicationId = this.applicationId,
        repairCode = this.repairCode,
        problemSource = this.problemSource,
        inputParameters = this.inputParameters?.toInputParameterList(),
    )

fun RepairCommand.toAdminConsoleKafkaMessageContainer(): AdminConsoleAlppKafkaMessageContainer =
    AdminConsoleAlppKafkaMessageContainer(
        headers = AdminConsoleAlppKafkaMessageContainer.AdminConsoleAlppKafkaHeaders(
            messageKey = this.id?.value.toString(),
            problemSource = this.problemSource.value,
            repairCode = this.repairCode.value
        ),
        message = AdminConsoleAlppKafkaMessageContainer.AdminConsoleAlppKafkaMessage(
            repairId = this.repairId.value,
            performedBy = this.performedBy.value,
            applicationId = this.applicationId?.value ?: EMPTY,
            repairCode = this.repairCode.value,
            inputParameters = this.inputParameters?.value?.map { it.toInputParameterAdminConsoleKafkaMessage() })
    )

fun RepairCommand.toDataHistoryKafkaRequest(repairStatus: RepairStatus): DataHistoryKafkaRequest =
    DataHistoryKafkaRequest()
        .entityType(ADMIN_CONSOLE_DATA_HISTORY_MESSAGE_ENTITY_TYPE)
        .entityId(this.repairId.value.toString())
        .relatedEntityId(this.applicationId?.value ?: EMPTY)
        .operationDttm(LocalDateTime.ofInstant(this.dateTime!!.value, ZoneId.systemDefault()))
        .operation(repairStatus.name)
        .performedByType(ADMIN_CONSOLE_DATA_HISTORY_MESSAGE_PERFORMED_BY_TYPE)
        .performedBy(this.performedBy.value)
        .reason(this.repairCode.value)
        .addChangedDataItem(
            ChangedDataDTO()
                .key(ADMIN_CONSOLE_DATA_HISTORY_MESSAGE_CHANGED_DATA_PROBLEM_SOURCE_KEY)
                .title(ADMIN_CONSOLE_DATA_HISTORY_MESSAGE_CHANGED_DATA_PROBLEM_SOURCE_TITLE)
                .valueOld(this.problemSource.value)
                .valueNew(this.problemSource.value)
        )
        .also { request ->
            this.inputParameters?.value?.forEach { inputParameter ->
                request.addChangedDataItem(
                    ChangedDataDTO()
                        .key(inputParameter.attribute.value)
                        .title(ADMIN_CONSOLE_DATA_HISTORY_MESSAGE_CHANGED_DATA_INPUT_PARAMETER_TITLE)
                        .valueOld(inputParameter.value.value)
                        .valueNew(inputParameter.value.value)
                )
            }
        }
