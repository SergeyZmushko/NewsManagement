package com.mjc.school.repository.model.impl;

import com.mjc.school.repository.model.BaseEntity;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "news")
@EntityListeners(AuditingEntityListener.class)
public class NewsModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "news_id")
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate;
    @Column(name = "last_update_date")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private AuthorModel authorModel;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "news_tag",
            joinColumns = @JoinColumn(name = "news_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagModel> tagModels;

    @OneToMany(mappedBy = "newsModel", fetch = FetchType.LAZY)
    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<TagModel> getTagModels() {
        return tagModels;
    }

    public void setTagModels(List<TagModel> tagModels) {
        this.tagModels = tagModels;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public AuthorModel getAuthorModel() {
        return authorModel;
    }

    public void setAuthorModel(AuthorModel authorModel) {
        this.authorModel = authorModel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    @Override
    public String toString() {
        return "NewsModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createDate=" + createDate +
                ", lastUpdateDate=" + lastUpdateDate +
                ", authorModel=" + authorModel +
                ", tags=" + tagModels +
                '}';
    }
}
