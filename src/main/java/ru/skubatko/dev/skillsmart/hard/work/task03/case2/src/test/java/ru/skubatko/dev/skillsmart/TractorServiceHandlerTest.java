package ru.skubatko.dev.skillsmart;

import static org.assertj.core.api.Assertions.assertThat;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

@DisplayName("Обработчик команд трактора")
class TractorServiceHandlerTest {

    @DisplayName("должен ожидаемо перемещать трактор")
    @SneakyThrows
    @Test
    void shouldExpectedMoveTractor() {
        // given
        Tractor tractor = new Tractor();
        TractorServiceMoveForward serviceMoveForward = new TractorServiceMoveForward(tractor);
        TractorServiceTurnClockwise serviceTurnClockwise = new TractorServiceTurnClockwise(tractor);
        TractorServiceHandler handler = new TractorServiceHandler(List.of(serviceTurnClockwise, serviceMoveForward));

        // when
        handler.handle(TractorCommand.FORWARD);
        handler.handle(TractorCommand.FORWARD);
        handler.handle(TractorCommand.TURN);
        handler.handle(TractorCommand.FORWARD);
        handler.handle(TractorCommand.FORWARD);
        handler.handle(TractorCommand.TURN);
        handler.handle(TractorCommand.TURN);
        handler.handle(TractorCommand.TURN);
        handler.handle(TractorCommand.FORWARD);

        // then
        assertThat(tractor.position()).isEqualTo(new int[]{2, 3});
        assertThat(tractor.orientation()).isEqualTo(Orientation.NORTH.getValue());
    }
}
