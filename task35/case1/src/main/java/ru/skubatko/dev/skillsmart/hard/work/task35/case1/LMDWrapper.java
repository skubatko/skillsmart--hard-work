package ru.skubatko.dev.skillsmart.hard.work.task35.case1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

/**
 * Конверт (обертка) для собираемых объектов LMD
 */
@Data
@NoArgsConstructor
public class LMDWrapper<T extends LMDWrappable> {
    private static final String CHANGE_TYPE = "Default";
    private static final String CHANGE_OWNER = "alppstm";
    private static final String CHANGE_SOURCE = "MAIN";

    /**
     * Уникальный идентификатор изменений
     */
    private UUID changeId;

    /**
     * Тип изменений
     */
    private String changeType = CHANGE_TYPE;

    /**
     * Владелец изменения
     */
    private String changeOwner = CHANGE_OWNER;

    /**
     * Режим БД
     */
    private String changeSource = CHANGE_SOURCE;

    /**
     * Идентификатор сущности изменения
     */
    private String changeKey;

    /**
     * Временная метка сообщения
     */
    private String changeTimestamp;

    /**
     * Идентификатор версии сущности
     */
    private Meta meta;

    /**
     * Вложенная в сообщение изменяемая сущность
     */
    private List<T> payload;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor(staticName = "from")
    public static class Meta {

        /**
         * Класс BusinessObject
         */
        private String clazz;

        /**
         * Родительский класс
         */
        private String baseClass;
    }
}
