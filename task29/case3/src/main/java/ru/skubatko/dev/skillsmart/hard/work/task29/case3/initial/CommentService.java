package ru.skubatko.dev.skillsmart.hard.work.task29.case3.initial;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class CommentService {
    private final DictionaryMapperService dictionaryMapperService;

    public CommentDTO findByNumber(String number, boolean isEnhanced) {
        return CommentRepository.findByNumber(number)
            .map(app -> isEnhanced
                ? CommentMapper.INSTANCE.toMappedDto(app, dictionaryMapperService)
                : CommentMapper.INSTANCE.toDto(app))
            .orElseThrow(() -> new CommentNotFoundException());
    }
}
