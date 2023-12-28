package com.mjc.school.service.util;

import com.mjc.school.repository.interfaces.NewsRepository;
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
    private static final String CONTEXT_GENERATOR = "context_generate";
    private static final String AUTHORS_FILE_NAME = "authors";

    @Autowired
    public DbLoader(NewsRepository newsRepository){
        this.newsRepository = newsRepository;
    }

    @Transactional
    public void saveData(int count){
        if (newsRepository.count() >= count){
            return;
        }
        List<String> authorsList = DataReader.readData(AUTHORS_FILE_NAME);

        Random rnd = new Random();
        String[] context = DataReader.readData(CONTEXT_GENERATOR).get(0).split(" ");
        for (long i = 0; i < count; i++){
            NewsModel newsModel = new NewsModel();

            int commentNumber = rnd.nextInt(4, 10);
            List<Comment> comments = new ArrayList<>();
            for (int j = 0; j < commentNumber; j++) {
                String commentResult = generateSentence(commentNumber, context, rnd);
                Comment comment = new Comment();
                comment.setContent(commentResult);
                comment.setNewsModel(newsModel);
                comments.add(comment);
            }
            newsModel.setComments(new ArrayList<>());
            newsModel.getComments().addAll(comments);

            int tagNumber = rnd.nextInt(1, 3);
            List<TagModel> tags = new ArrayList<>();
            for (int j = 0; j < tagNumber; j++){
                TagModel tag = new TagModel();
                tag.setName(context[rnd.nextInt(1000)]);
                tags.add(tag);
            }
            newsModel.setTagModels(new ArrayList<>());
            newsModel.getTagModels().addAll(tags);

            int titleNumber = rnd.nextInt(1, 4);
            String titleResult = generateSentence(titleNumber, context, rnd);

            int contentNumber = rnd.nextInt(1, 12);
            String contentResult = generateSentence(contentNumber, context, rnd) + ".";

            AuthorModel authorModel = new AuthorModel();
            authorModel.setName(authorsList.get((int) i));
            newsModel.setAuthorModel(authorModel);

            newsModel.setTitle(titleResult);
            newsModel.setContent(contentResult);
            newsRepository.save(newsModel);
        }
    }

    private String generateSentence(int i, String[] context, Random rnd){
        StringBuilder result = new StringBuilder();
        for (int j = 0; j < i; j++){
            result.append(context[rnd.nextInt(context.length - 1)]).append(" ");
        }
        return result.substring(0, 1).toUpperCase() + result.substring(1).toLowerCase();
    }
}
