package com.mjc.school.interfaces;

import com.mjc.school.interfaces.impl.CustomTagRepository;
import com.mjc.school.model.impl.TagModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<TagModel, Long>, CustomTagRepository, JpaSpecificationExecutor<TagModel> {

}
