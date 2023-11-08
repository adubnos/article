package com.example.article.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SequenceGenerator(name="comment_SEQ", allocationSize = 1)
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_SEQ")
    private int no;

    @Column(nullable = false, length = 100)
    private String content;

    @Column(nullable = false, length = 20)
    private String writer;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ARTICLE_NO", referencedColumnName = "no")
    private Article article;
}
