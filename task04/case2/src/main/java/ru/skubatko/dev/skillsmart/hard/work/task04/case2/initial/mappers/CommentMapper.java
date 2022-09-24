package ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.mappers;

import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.domain.CommentEntity;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.dto.CommentDTO;
import ru.skubatko.dev.skillsmart.hard.work.task04.case2.initial.dto.CommentOutputDTO;

public class CommentMapper {
    public static final CommentMapper INSTANCE = new CommentMapper();

    public CommentOutputDTO toOutputDto(CommentEntity commentVal) {
        return null;
    }

    public CommentDTO toDto(CommentEntity commentEntity) {
        return null;
    }
}
