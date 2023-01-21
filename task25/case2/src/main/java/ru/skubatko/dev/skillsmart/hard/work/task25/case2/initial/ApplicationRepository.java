package ru.skubatko.dev.skillsmart.hard.work.task25.case2.initial;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface ApplicationRepository extends PagingAndSortingRepository<ApplicationEntity, Long>, QuerydslPredicateExecutor<ApplicationEntity> {

    Optional<ApplicationEntity> findByExternalId(Long externalId);

    Optional<ApplicationEntity> findByApplicationId(Long applicationId);

    Optional<ApplicationEntity> findByNumber(String number);

    @Transactional
    void updateS1tate(@Param("APPLICATIONID") Long applicationId, @Param("statusCode") String statusCode);

    List<String> findAllDistinctStates();//NotAnsIsNotNull

    List<String> findAllDistinctRegions();

    List<String> findAllDistinctCreatedChannels();

    List<String> findAllSaleChannels();

    List<String> findAllDistinctSaleChannelsSecondLevel();

    List<String> findAllDistinctIssueBranches();

    List<String> findAllDistinctLiabilityTypes();
}
