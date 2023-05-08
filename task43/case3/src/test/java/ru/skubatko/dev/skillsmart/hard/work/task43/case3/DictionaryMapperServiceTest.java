package ru.skubatko.dev.skillsmart.hard.work.task43.case3;

import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@DisplayName("Сервис отображения значений из словарей")
@ExtendWith(MockitoExtension.class)
class DictionaryMapperServiceTest {
    @Mock
    private DictionaryCacheService dictionaryCacheService;

    @InjectMocks
    private DictionaryMapperService dictionaryMapperService;

    @DisplayName("должен ожидаемо обрабатывать параметры поиска заявок, содержащие несколько значений, разделённых запятой")
    @SneakyThrows
    @Test
    void shouldExpectedlyProcessApplicationSearchParametersWhenContainsSeveralValuesSplitByComma() {
        // given
        ApplicationParametersDTO parametersDTO = new ApplicationParametersDTO();
        parametersDTO.setProductType("cm.LoanProductType.AutoLite,cm.LoanProductType.AutoPostPledge");

        // when
        dictionaryMapperService.processApplicationSearchParameters(parametersDTO);

        // then
        assertThat(parametersDTO.getProductType().split("\\s*,\\s*")).hasSize(2);
    }
}
