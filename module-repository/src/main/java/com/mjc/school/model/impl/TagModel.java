package com.mjc.school.model.impl;

import com.mjc.school.model.BaseEntity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tag")
public class TagModel implements BaseEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long id;
    @Column(name = "name")
    private String name;
    @ManyToMany(mappedBy = "tagModels", fetch = FetchType.LAZY)
    private List<NewsModel> newsModels;

    public List<NewsModel> getNewsModels() {
        return newsModels;
    }

    public void setNewsModels(List<NewsModel> newsModels) {
        this.newsModels = newsModels;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TagModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", newsModels=" + newsModels +
                '}';
    }
}

