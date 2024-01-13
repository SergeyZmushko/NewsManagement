package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.impl.NewsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NewsRepository extends JpaRepository<NewsModel, Long>, JpaSpecificationExecutor<NewsModel> {
}
