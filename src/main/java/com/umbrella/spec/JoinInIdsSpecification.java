package com.umbrella.spec;

import com.umbrella.entity.Event;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class JoinInIdsSpecification implements Specification<Event> {
    private final List<Integer> ids;
    public JoinInIdsSpecification(List<Integer> ids) {
        this.ids = ids;
    }
    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> cquery, CriteriaBuilder cbuilder) {
        return root.get("id").in(ids);
    }
}
