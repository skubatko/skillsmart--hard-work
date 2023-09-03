package ru.skubatko.dev.skillsmart.hard.work.task68

fun CreateRepairOperationRequestInputParameterTO.toInputParameter() =
    InputParameter(
        attribute = InputParameterAttribute(this.attribute),
        value = InputParameterValue(this.value)
    )

fun InputParameter.toInputParameterAdminConsoleKafkaMessage(): AdminConsoleAlppKafkaMessageInputParameter =
    AdminConsoleAlppKafkaMessageInputParameter(
        attribute = this.attribute.value,
        value = this.value.value
    )
