package com.mjc.school.service;

import com.mjc.school.service.dto.PageDtoResponse;

public interface BaseService<C, R, G, S, U> {
    PageDtoResponse<R> readAll(Integer pageNo, Integer pageSize, String sort);

    R readById(G id);

    R create(C createRequest);

    R update(G id, U updateRequest);

    void deleteById(G id);
}
