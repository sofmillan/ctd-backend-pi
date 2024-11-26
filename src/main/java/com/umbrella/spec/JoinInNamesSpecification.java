package com.umbrella.spec;

import com.umbrella.entity.Event;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class JoinInNamesSpecification implements Specification<Event> {
    private final List<String> names;
    public JoinInNamesSpecification(List<String> names) {
        this.names = names;
    }
    @Override
    public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> cquery, CriteriaBuilder cbuilder) {
        return root.join("genre", JoinType.INNER)
                .get("name")
                .in(names);
    }

}
