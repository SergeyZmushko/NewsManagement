package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@EntityListeners(AuditingEntityListener.class)
public class Comment implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "content")
    private String content;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "news_id")
    private NewsModel newsModel;
    @Column(name = "created_date")
    @CreatedDate
    private LocalDateTime created;
    @Column(name = "modified_date")
    @LastModifiedDate
    private LocalDateTime modified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public NewsModel getNewsModel() {
        return newsModel;
    }

    public void setNewsModel(NewsModel newsModel) {
        this.newsModel = newsModel;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getModified() {
        return modified;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }
}


