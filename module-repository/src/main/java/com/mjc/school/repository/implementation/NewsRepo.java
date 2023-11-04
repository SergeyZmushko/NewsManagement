package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NewsRepo extends JpaRepository<NewsModel, Long> {
    @Override
    Optional<NewsModel> findById(Long aLong);
}
