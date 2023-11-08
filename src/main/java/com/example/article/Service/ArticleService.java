package com.example.article.Service;

import com.example.article.DTO.ArticleDTO;
import com.example.article.Entity.Article;
import com.example.article.Repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper=new ModelMapper();

    public Page<ArticleDTO> list(Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int pageLimit=10;

        Page<Article> paging=articleRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC,"no")));

        return paging.map(article->ArticleDTO.builder()
                .no(article.getNo())
                .title(article.getTitle())
                .viewcnt(article.getViewcnt())
                .createDate(article.getCreateDate())
                .modifyDate(article.getModifyDate())
                .build());
    }

    public void register(ArticleDTO articleDTO) {
        Article article=modelMapper.map(articleDTO, Article.class);
        articleRepository.save(article);
    }

    public ArticleDTO listOne(Integer no) {
        Optional<Article> article=articleRepository.findById(no);
        return modelMapper.map(article,ArticleDTO.class);
    }

    public void modify(ArticleDTO articleDTO) {
        Integer no=articleDTO.getNo();
        Optional<Article> article=articleRepository.findById(no);
        Article data=article.orElseThrow();

        Article modify=modelMapper.map(articleDTO,Article.class);
        modify.setNo(data.getNo());
        articleRepository.save(modify);
    }

    public void delete(Integer no) {
        articleRepository.deleteById(no);
    }
}
