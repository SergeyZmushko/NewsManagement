package com.mjc.school.repository.interfaces.impl;

import com.mjc.school.repository.model.impl.TagModel;

import java.util.List;
import java.util.Optional;

public interface CustomTagRepository {
    List<TagModel> findByNewsModelsId(Long newsId);
    Optional<TagModel> findByName(String name);
}
