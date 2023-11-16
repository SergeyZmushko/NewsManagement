package com.mjc.school.controller;

import com.mjc.school.service.dto.PageDtoResponse;

public interface BaseController <C, R, G, S, U>{
    PageDtoResponse<R> readAll(S pageNo, S pageSize, String sort);

    R readById(G id);

    R create(C createRequest);

    R update(G id, U updateRequest);

    void deleteById(G id);
}
