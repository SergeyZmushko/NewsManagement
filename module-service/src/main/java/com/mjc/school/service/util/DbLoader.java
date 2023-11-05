package com.mjc.school.service.util;

import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.repository.model.impl.AuthorModel;
import com.mjc.school.repository.model.impl.Comment;
import com.mjc.school.repository.model.impl.NewsModel;
import com.mjc.school.repository.model.impl.TagModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;
@Component
public class DbLoader {

    private final NewsRepository newsRepository;
    private static final String AUTHORS_FILE_NAME = "authors";
    private static final String CONTENT_FILE_NAME = "content";
    private static final String COMMENTS_FILE_NAME = "comments";
    private static final String NEWS_FILE_NAME = "news";
    private static final String TAGS_FILE_NAME = "tags";

    @Autowired
    public DbLoader(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @Transactional
    public void loadData() {
        List<String> authors = DataReader.readData(AUTHORS_FILE_NAME);
        List<String> tags = DataReader.readData(TAGS_FILE_NAME);
        List<String> comments = DataReader.readData(COMMENTS_FILE_NAME);
        Set<Integer> prevs = new HashSet<>();
        List<TagModel> tagModels = new LinkedList<>();
        List<AuthorModel> authorModels = new LinkedList<>();
        List<Comment> commentModels = new LinkedList<>();

        Random rnd = new Random();
        for (long i = 1; i <= 20; i++) {
            int index = rnd.nextInt(tags.size());
            if (prevs.contains(index)) {
                i--;
            } else {
                prevs.add(index);
                TagModel tagModel = new TagModel();
                AuthorModel authorModel = new AuthorModel();
                Comment comment = new Comment();
                tagModel.setName(tags.get(index));
                authorModel.setName(authors.get(index));
                comment.setContent(comments.get(index));
                tagModels.add(tagModel);
                authorModels.add(authorModel);
                commentModels.add(comment);
            }
        }
        List<String> content = DataReader.readData(CONTENT_FILE_NAME);
        List<String> titles = DataReader.readData(NEWS_FILE_NAME);
        for (long i = 1; i <= 20; i++) {
            NewsModel newsModel = new NewsModel();
            int tagNumber = rnd.nextInt(2, 6);
            for (int j = 0; j < tagNumber; j++) {
                newsModel.setTagModels(new ArrayList<>());
                newsModel.getTagModels().add(tagModels.get(rnd.nextInt(20)));
            }
            int commentNumber = rnd.nextInt(4, 10);
            for (int j = 0; j < commentNumber; j++) {
                Comment comment = commentModels.get(rnd.nextInt(20));
                comment.setNewsModel(newsModel);
                newsModel.setComments(new ArrayList<>());
                newsModel.getComments().add(comment);
            }
            newsModel.setTitle(titles.get(rnd.nextInt(titles.size())));
            newsModel.setContent(content.get(rnd.nextInt(content.size())));
            newsModel.setAuthorModel(authorModels.get(rnd.nextInt(authorModels.size())));
            newsRepository.create(newsModel);
        }
    }
}
