package ru.skubatko.dev.skillsmart.hard.work.task25.case3.initial;

import com.querydsl.core.types.dsl.SimpleExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.PagingAndSortingRepository;

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

    List<ReferenceGroupEntity> findAllByArchive(Boolean archive);

    List<ReferenceGroupEntity> findChildrenGroupsByParentGroupIds(Collection<Long> refGroupIds);

    void deleteAllByIdIn(Iterable<Long> ids);
}
