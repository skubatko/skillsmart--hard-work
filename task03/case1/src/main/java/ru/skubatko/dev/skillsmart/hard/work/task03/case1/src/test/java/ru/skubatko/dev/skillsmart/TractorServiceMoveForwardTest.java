package ru.skubatko.dev.skillsmart;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Сервис движения вперёд")
class TractorServiceMoveForwardTest {

    @DisplayName("должен ожидаемо двигать трактор")
    @SneakyThrows
    @Test
    void shouldExpectedMoveTractor() {
        // given
        Tractor tractor = new Tractor();
        TractorServiceMoveForward serviceMoveForward = new TractorServiceMoveForward(tractor);

        // when
        serviceMoveForward.move();

        // then
        assertThat(tractor.position()).isEqualTo(new int[]{0, 1});
    }
}
