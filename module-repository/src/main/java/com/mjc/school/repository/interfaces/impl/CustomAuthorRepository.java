package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.AuthorModel;

import java.util.Optional;

public interface CustomAuthorRepository {
    Optional<AuthorModel> findByName(String name);

    Optional<AuthorModel> readByNewsId(Long newsId);

}
