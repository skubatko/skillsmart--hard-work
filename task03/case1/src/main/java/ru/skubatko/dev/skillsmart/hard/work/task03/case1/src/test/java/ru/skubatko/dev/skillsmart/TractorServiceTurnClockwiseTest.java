package ru.skubatko.dev.skillsmart;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Сервис поворота")
class TractorServiceTurnClockwiseTest {

    @DisplayName("должен ожидаемо поворачивать трактор")
    @SneakyThrows
    @Test
    void shouldExpectedTurnTractor() {
        // given
        Tractor tractor = new Tractor();
        TractorServiceTurnClockwise serviceTurnClockwise = new TractorServiceTurnClockwise(tractor);

        // when
        serviceTurnClockwise.move();

        // then
        assertThat(tractor.orientation()).isEqualTo(Orientation.EAST.getValue());
    }
}
