package ru.skubatko.dev.skillsmart.hard.work.task68

import java.util.UUID

fun CreateRepairOperationRequestTO.toRepairOperation(): RepairOperation =
    RepairOperation(
        performedBy = PerformedBy(this.performedBy),
        repairId = RepairId(UUID.randomUUID()),
        commands = RepairOperationCommandSet(this.commands.map { it.toRepairOperationCommand() }.toSet())
    )

fun CreateRepairOperationRequestCommandTO.toRepairOperationCommand(): RepairOperationCommand =
    RepairOperationCommand(
        applicationId = ApplicationId(this.applicationId),
        repairCode = RepairCode(this.repairCode),
        problemSource = ProblemSource(this.problemSource),
        inputParameters = InputParameterSet(this.inputParameters?.map { it.toInputParameter() }?.toSet())
    )
