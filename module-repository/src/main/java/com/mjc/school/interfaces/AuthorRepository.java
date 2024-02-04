package com.mjc.school.interfaces;

import com.mjc.school.interfaces.impl.CustomAuthorRepository;
import com.mjc.school.model.impl.AuthorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
@Repository
public interface AuthorRepository extends JpaRepository<AuthorModel, Long>, CustomAuthorRepository, JpaSpecificationExecutor<AuthorModel> {


}
