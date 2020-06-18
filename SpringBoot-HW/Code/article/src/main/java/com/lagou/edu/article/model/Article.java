package com.lagou.edu.article.model;


import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "t_article")
@Data
public class Article implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String content;

    private Date created;

    private Date modified;

    private String categories;

    private String tags;

    private Boolean allowComment;

    private String thumbnail;
}
