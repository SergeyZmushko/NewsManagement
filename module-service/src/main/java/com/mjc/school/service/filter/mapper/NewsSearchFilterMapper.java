package com.mjc.school.service.filter.mapper;

import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import com.mjc.school.service.filter.ResourceSearchFilter;
import org.springframework.stereotype.Component;

@Component
public class NewsSearchFilterMapper extends BaseSearchFilterMapper<ResourceSearchFilterRequestDTO> {
    private static final String TITLE_FIELD_TO_SORT_BY = "title";

    private static final String TITLE_FIELD_SORT_ORDER = "ASC";

    private static final String CREATED_DATE_FIELD_TO_SORT_BY = "createDate";

    private static final String CREATED_DATE_FIELD_SORT_ORDER = "ASC";

    @Override
    public ResourceSearchFilter map(ResourceSearchFilterRequestDTO searchFilterRequest) {
        DEFAULT_SORTING_MAP.clear();
        DEFAULT_SORTING_MAP.put(TITLE_FIELD_TO_SORT_BY, TITLE_FIELD_SORT_ORDER);
        DEFAULT_SORTING_MAP.put(CREATED_DATE_FIELD_TO_SORT_BY, CREATED_DATE_FIELD_SORT_ORDER);
        return createResourceSearchFilter(searchFilterRequest.getPage(), searchFilterRequest.getPageSize(), searchFilterRequest.getSortByAndOrder(), searchFilterRequest.getSearchFilter());
    }

}
