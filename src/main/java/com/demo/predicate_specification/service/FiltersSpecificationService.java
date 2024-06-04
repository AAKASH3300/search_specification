package com.demo.predicate_specification.service;

import com.demo.predicate_specification.dto.SearchRequestDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltersSpecificationService<T> {

    public Specification<T> getSearchSpecification(SearchRequestDto searchRequestDto) {

        return new Specification<T>() {
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.get(searchRequestDto.getColumnName()), searchRequestDto.getValue());
            }
        };

    }

    public Specification<T> getSearchSpecification(List<SearchRequestDto> searchRequestDtos) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            for (SearchRequestDto searchRequestDto : searchRequestDtos) {
                Predicate equal = criteriaBuilder.equal(root.get(searchRequestDto.getColumnName()), searchRequestDto.getValue());
                predicates.add(equal);
            }
            return criteriaBuilder.and(predicates.toArray((new Predicate[0])));
        };

    }

}
