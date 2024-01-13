package com.mjc.school.service.dto;

import com.mjc.school.service.validator.constraint.SearchCriteria;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ResourceSearchFilterRequestDTO {

    // Value example: name:like:tag
    @SearchCriteria
    public List<String> searchCriteria = new ArrayList<>();

    public ResourceSearchFilterRequestDTO() {

    }

    public ResourceSearchFilterRequestDTO(@Nullable List<String> searchCriteria) {
        if (searchCriteria != null) {
            this.searchCriteria = searchCriteria;
        }
    }

    public List<String> getSearchFilter() {
        return searchCriteria;
    }
}
