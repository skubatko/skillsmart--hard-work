package ru.skubatko.dev.skillsmart.hard.work.task29.case3.refactored;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final DictionaryMapperService dictionaryMapperService;

    public CommentDTO findByNumber(String number) {
        return CommentRepository.findByNumber(number)
            .map(app -> CommentMapper.INSTANCE.toDto(app))
            .orElseThrow(() -> new CommentNotFoundException());
    }

    public CommentDTO findByNumberEnhanced(String number) {
        return CommentRepository.findByNumber(number)
            .map(app -> CommentMapper.INSTANCE.toMappedDto(app, dictionaryMapperService))
            .orElseThrow(() -> new CommentNotFoundException());
    }
}
