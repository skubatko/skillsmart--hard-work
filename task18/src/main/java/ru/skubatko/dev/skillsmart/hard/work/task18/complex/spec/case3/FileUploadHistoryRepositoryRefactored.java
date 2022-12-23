package ru.skubatko.dev.skillsmart.hard.work.task18.complex.spec.case3;

import java.util.Optional;

public interface FileUploadHistoryRepositoryRefactored extends PagingAndSortingRepository<FileUploadHistoryEntity, Long> {

    Optional<FileUploadHistoryEntity> find(Long id);

    FileUploadHistoryEntity create(FileUploadHistoryCreateDto dto);

    void update(Long id, FileUploadHistoryUpdateDto dto);

    void delete(Long id);
}
