package com.mjc.school.repository.repoV2Implementation;

import com.mjc.school.repository.filter.pagination.Page;
import com.mjc.school.repository.model.BaseEntity;
import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface BaseRepository<T extends BaseEntity<K>, K> extends JpaRepository<T, K> {
    Page<AuthorModel> findAll(Pageable pageable);
}
