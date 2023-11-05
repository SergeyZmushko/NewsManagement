package com.mjc.school.repository.repoV2Implementation;

import com.mjc.school.repository.model.impl.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepositoryNew extends JpaRepository<AuthorModel, Long> {
}
