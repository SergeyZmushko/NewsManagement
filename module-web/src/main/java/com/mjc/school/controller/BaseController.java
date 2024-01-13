package com.mjc.school.controller;

import com.mjc.school.service.dto.PageDtoResponse;
import com.mjc.school.service.dto.ResourceSearchFilterRequestDTO;
import org.springframework.data.domain.Pageable;

public interface BaseController <C, R, G, U>{
    PageDtoResponse<R> readAll(ResourceSearchFilterRequestDTO requestDTO, Pageable pageable);

    R readById(G id);

    R create(C createRequest);

    R update(G id, U updateRequest);

    void deleteById(G id);
}
