package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    private Integer no;
    @NotEmpty(message = "제목은 필수 입력 사항입니다.")
    private String title;
    private String content;
    private Integer viewcnt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
