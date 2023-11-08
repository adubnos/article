package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Integer no;
    @NotNull(message = "내용은 필수 입력 사항입니다.")
    private String content;
    @NotNull(message = "작성자는 필수 입력 사항입니다.")
    private String writer;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Integer articleno;
}
