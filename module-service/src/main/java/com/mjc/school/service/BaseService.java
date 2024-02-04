package com.mjc.school.service;

import com.mjc.school.specification.SearchCriteria;
import com.mjc.school.specification.SearchFilterSpecification;
import com.mjc.school.specification.SearchOperation;
import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;


public interface BaseService<C, R, G, S, U> {
    PageDtoResponse<R> readAll(ResourceSearchFilterRequestDTO searchFilter, Pageable pageable);

    R readById(G id);

    R create(C createRequest);

    R update(G id, U updateRequest);

    void deleteById(G id);

    default Specification<S> getSpecification(ResourceSearchFilterRequestDTO searchFilterRequestDTO) {
        String searchField = searchFilterRequestDTO.getSearchFilter().get(0);
        String[] filterData = searchField.split(":");
        SearchOperation operation = SearchOperation.getSearchOperationByName(filterData[1]);
        return new SearchFilterSpecification<>(new SearchCriteria(filterData[0], operation, filterData[2]));
    }
}
