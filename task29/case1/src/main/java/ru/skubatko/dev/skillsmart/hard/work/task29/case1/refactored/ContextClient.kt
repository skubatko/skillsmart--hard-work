package ru.skubatko.dev.skillsmart.hard.work.task29.case1.refactored

import java.net.http.HttpHeaders

class ContextClient(
    private val objectMapper: ObjectMapper,
    private val sessionExpirationDuration: String,
    private val webClient: WebClient,
    val serviceId: String
) {

    /**
     * POST /session/{sessionId}/{serviceId} depend on [replace]
     *
     * @param sessionId Идентификатор сессии
     * @param serviceId Идентификатор сервиса
     * @param context Данные сохраняемые в сессионных данных
     * @param token JWT-токен
     *
     * @throws ContextException при получении ответа с ошибкой и успешной его обработкой, содержит тело ответа в виде [ContextError]
     * @throws BadResponseException при получении ответа с ошибкой и неудачной обработкой, содержит тело ответа в виде строки
     */
    fun setContext(
        sessionId: String,
        serviceId: String = this.serviceId,
        context: Any,
        token: String,
    ) {
        try {
            webClient.post()
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

    /**
     * PUT /session/{sessionId}/{serviceId} depend on [replace]
     *
     * @param sessionId Идентификатор сессии
     * @param serviceId Идентификатор сервиса
     * @param context Данные сохраняемые в сессионных данных
     * @param token JWT-токен
     *
     * @throws ContextException при получении ответа с ошибкой и успешной его обработкой, содержит тело ответа в виде [ContextError]
     * @throws BadResponseException при получении ответа с ошибкой и неудачной обработкой, содержит тело ответа в виде строки
     */
    fun updateContext(
        sessionId: String,
        serviceId: String = this.serviceId,
        context: Any,
        token: String,
    ) {
        try {
            webClient.put()
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
}
