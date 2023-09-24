package ru.skubatko.dev.skillsmart.hard.work.task71.case2

import org.graalvm.compiler.nodes.ValueNode

interface AuditService {

    fun <T : Any> send(
        action: String,
        `class`: AuditEventClass,
        message: T,
        dataCustomizer: Function<ValueNode.JsonNode, ValueNode.JsonNode>
    ): Mono<Void>
}
