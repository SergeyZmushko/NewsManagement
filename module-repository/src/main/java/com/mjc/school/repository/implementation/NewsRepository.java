package com.mjc.school.repository.implementation;

import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsRepository extends AbstractDbRepository<NewsModel, Long> {

    @Override
    void update(NewsModel prevState, NewsModel nextState) {
        if (nextState.getTitle() != null && !nextState.getTitle().isBlank()){
            prevState.setTitle(nextState.getTitle());
        }
        if (nextState.getContent() != null && !nextState.getContent().isBlank()){
            prevState.setContent(nextState.getContent());
        }
        AuthorModel authorModel = nextState.getAuthorModel();
        if (authorModel != null && !authorModel.getName().isBlank()){
            prevState.setAuthorModel(authorModel);
        }
        List<TagModel> tags = nextState.getTagModels();
        if (tags != null && !tags.isEmpty()){
            prevState.setTagModels(tags);
        }
    }
}
