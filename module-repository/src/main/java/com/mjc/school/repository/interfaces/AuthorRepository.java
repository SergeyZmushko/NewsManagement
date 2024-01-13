package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.interfaces.impl.CustomAuthorRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long>, CustomAuthorRepository, JpaSpecificationExecutor<AuthorModel> {


}
