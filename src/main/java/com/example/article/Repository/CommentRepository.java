package com.example.article.Repository;

import com.example.article.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(value = "SELECT * From Comment WHERE article_no = :article", nativeQuery = true)
    List<Comment> findByArticle(Integer article);
}
