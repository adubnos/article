package com.example.article;

import com.example.article.Entity.Article;
import com.example.article.Entity.Comment;
import com.example.article.Repository.ArticleRepository;
import com.example.article.Repository.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    CommentRepository commentRepository;

    @Test
    public void articleRegisterTest() {
        for(int i=1;i<=50;i++) {
            Article article=Article.builder()
                    .title("title"+i)
                    .content("content"+i)
                    .build();
            articleRepository.save(article);
        }
    }

    @Test
    public void commentRegisterTest() {
        for(int i=1; i<=50; i++) {
            Comment comment=Comment.builder()
                    .content("content"+i)
                    .writer("writer"+i)
                    .article(articleRepository.findById(i).orElse(null))
                    .build();
            commentRepository.save(comment);
        }
    }

    @Test
    @Transactional
    public void findByArticleTest() {
        Integer no=3;

        List<Comment> comments=commentRepository.findByArticle(no);

        System.out.println(comments.toString());
    }
}
