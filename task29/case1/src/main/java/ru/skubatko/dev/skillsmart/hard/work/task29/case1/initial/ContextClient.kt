package ru.skubatko.dev.skillsmart.hard.work.task29.case1.initial

import java.net.http.HttpHeaders

class ContextClient(
    private val objectMapper: ObjectMapper,
    private val sessionExpirationDuration: String,
    private val webClient: WebClient,
    val serviceId: String
) {

    /**
     * POST or PUT /session/{sessionId}/{serviceId} depend on [replace]
     *
     * @param sessionId Идентификатор сессии
     * @param serviceId Идентификатор сервиса
     * @param context Данные сохраняемые в сессионных данных
     * @param token JWT-токен
     * @param replace true - создание нового бакета данных (с перезаписью прошлого), false - обновление существующего
     *
     * @throws ContextException при получении ответа с ошибкой и успешной его обработкой, содержит тело ответа в виде [ContextError]
     * @throws BadResponseException при получении ответа с ошибкой и неудачной обработкой, содержит тело ответа в виде строки
     */
    fun setContext(
        sessionId: String,
        serviceId: String = this.serviceId,
        context: Any,
        token: String,
        replace: Boolean = false,
    ) {
        try {
            webClient.postOrPutDependOnFlag(replace)
                .uri("/session/$sessionId/$serviceId")
                .header(HttpHeaders.AUTHORIZATION, token)
                .bodyValue(context)
                .retrieve()
                .toBodilessEntity()
                .block()
        } catch (ex: WebClientResponseException) {
            processErrorWithLog(ex)
        }
    }

    private fun WebClient.postOrPutDependOnFlag(create: Boolean) = if (create) post() else put()
}
