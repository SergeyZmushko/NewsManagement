package com.mjc.school.service.dto;

import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ResourceSearchFilterRequestDTO {

    // Value example: name:like:tag

    public List<String> searchCriteria = new ArrayList<>();

    public ResourceSearchFilterRequestDTO() {

    }

    public void setSearchCriteria(List<String> searchCriteria) {
        this.searchCriteria = searchCriteria;
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
