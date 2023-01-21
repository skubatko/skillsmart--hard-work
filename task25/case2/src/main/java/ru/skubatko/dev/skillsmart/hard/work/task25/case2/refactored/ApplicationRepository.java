package ru.skubatko.dev.skillsmart.hard.work.task25.case2.refactored;

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

    @Modifying
    @Transactional
    @Query(value = "UPDATE DOC_DOCUMENT d set d.statuscode = ?2 where d.applicationid = ?1", nativeQuery = true)
    void updateS1tate(@Param("APPLICATIONID") Long applicationId, @Param("statusCode") String statusCode);

    @Query(value = "" +
            "SELECT DISTINCT statusCode " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.statusCode IS NOT NULL AND doc.statusCode <> '' " +
            "ORDER BY doc.statusCode")
    List<String> findAllDistinctStates();//NotAnsIsNotNull

    @Query(value = "" +
            "SELECT DISTINCT purchaseRegion " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.purchaseRegion IS NOT NULL AND doc.purchaseRegion <> '' " +
            "ORDER BY doc.purchaseRegion")
    List<String> findAllDistinctRegions();

    @Query(value = "" +
            "SELECT DISTINCT createdChannel " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.createdChannel IS NOT NULL AND doc.createdChannel <> '' " +
            "ORDER BY createdChannel")
    List<String> findAllDistinctCreatedChannels();

    @Query(value = "" +
            "SELECT DISTINCT saleChannel " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.saleChannel IS NOT NULL AND doc.saleChannel <> '' " +
            "ORDER BY saleChannel")
    List<String> findAllSaleChannels();

    @Query(value = "" +
            "SELECT DISTINCT saleChannelSecondLevel " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.saleChannelSecondLevel IS NOT NULL AND doc.saleChannelSecondLevel <> '' " +
            "ORDER BY saleChannelSecondLevel")
    List<String> findAllDistinctSaleChannelsSecondLevel();

    @Query(value = "" +
            "SELECT DISTINCT issueBranch " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.issueBranch IS NOT NULL AND doc.issueBranch <> '' " +
            "ORDER BY issueBranch")
    List<String> findAllDistinctIssueBranches();

    @Query(value = "" +
            "SELECT DISTINCT liabilityType " +
            "FROM ApplicationEntity doc " +
            "WHERE doc.liabilityType IS NOT NULL AND doc.liabilityType <> '' " +
            "ORDER BY liabilityType")
    List<String> findAllDistinctLiabilityTypes();
}
