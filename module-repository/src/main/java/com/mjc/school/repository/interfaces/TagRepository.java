package com.mjc.school.repository.interfaces;

import com.mjc.school.repository.model.impl.TagModel;
import com.mjc.school.repository.interfaces.impl.CustomTagRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Long>, CustomTagRepository, JpaSpecificationExecutor<TagModel> {

}
