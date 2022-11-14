package ru.skubatko.dev.skillsmart.hard.work.task09.dependency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ReportFile {
    private Report report;
    private Status status;
    private String guid;
}
