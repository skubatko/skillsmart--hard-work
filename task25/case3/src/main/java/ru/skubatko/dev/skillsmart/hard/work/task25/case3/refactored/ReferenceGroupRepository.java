package ru.skubatko.dev.skillsmart.hard.work.task25.case3.refactored;

import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ReferenceGroupRepository extends PagingAndSortingRepository<ReferenceGroupEntity, Long>, QuerydslPredicateExecutor<ReferenceGroupEntity>,
        QuerydslBinderCustomizer<QReferenceGroupEntity> {

    @Override
    default void customize(QuerydslBindings bindings, QReferenceGroupEntity qEntity) {
        bindings.bind(qEntity.parentId)
                .first(SimpleExpression::eq);
        bindings.bind(qEntity.name)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
        bindings.bind(qEntity.sysname)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

    Optional<ReferenceGroupEntity> findByParentIdEqualsAndSysnameEquals(Long parentId, String sysname);

    List<ReferenceGroupEntity> findByParentIdEquals(Long parentId);

    List<ReferenceGroupEntity> findAll();

    @Modifying
    @Query("update ReferenceGroupEntity r set r.archive = true")
    void pushAllIntoArchive();

    @Query("SELECT r FROM ReferenceGroupEntity r WHERE (:archive is null or r.archive = :archive)")
    List<ReferenceGroupEntity> findAllByArchive(@Param("archive") Boolean archive);

    @Query("SELECT r FROM ReferenceGroupEntity r inner join ReferenceGroupEntity c on c.parentId = r.id where r.id in :refGroupIds group by r")
    List<ReferenceGroupEntity> findChildrenGroupsByParentGroupIds(@Param(value = "refGroupIds") Collection<Long> refGroupIds);

    void deleteAllByIdIn(Iterable<Long> ids);
}
