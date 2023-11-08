package com.example.article.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="article")
@SequenceGenerator(name="article_SEQ", allocationSize = 1)
public class Article extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_SEQ")
    private Integer no;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 200)
    private String content;

    @Column(columnDefinition = "Integer default 0")
    private Integer viewcnt;

    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @OrderBy("no asc")
    private List<Comment> comments;
}
