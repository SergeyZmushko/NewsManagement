package com.mjc.school.interfaces.impl;
import com.mjc.school.model.impl.AuthorModel;

import java.util.Optional;

public interface CustomAuthorRepository {
    Optional<AuthorModel> findByName(String name);

    Optional<AuthorModel> readByNewsId(Long newsId);

}
