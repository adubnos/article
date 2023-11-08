package com.example.article.Service;

import com.example.article.DTO.CommentDTO;
import com.example.article.Entity.Article;
import com.example.article.Entity.Comment;
import com.example.article.Repository.ArticleRepository;
import com.example.article.Repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper=new ModelMapper();

    public List<CommentDTO> commentList(Integer article) {
        List<Comment> comments=commentRepository.findByArticle(article);

        return Arrays.asList(modelMapper.map(comments, CommentDTO[].class));
    }

    public void commentRegister(CommentDTO commentDTO, Integer no) {
        Article article=articleRepository.findById(no).orElseThrow(null);
        Comment comment=Comment.builder()
                .article(article)
                .content(commentDTO.getContent())
                .writer(commentDTO.getWriter())
                .build();
        commentRepository.save(comment);
    }

    public void commentModify(CommentDTO commentDTO, Integer commentNo, Integer no) {
        Article article=articleRepository.findById(no).orElseThrow(null);
        Optional<Comment> comment=commentRepository.findById(commentNo);
        Comment data=comment.orElseThrow();

        Comment modify=modelMapper.map(commentDTO,Comment.class);
        modify.setNo(data.getNo());
        modify.setArticle(article);
        commentRepository.save(modify);
    }

    public void delete(Integer no) {
        commentRepository.deleteById(no);
    }
}
