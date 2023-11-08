package com.example.article.DTO;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DebateDTO {
    private Integer debateId;
    @NotEmpty(message = "토론 주제는 필수 입력 사항입니다.")
    private String subject;
    private Integer viewcnt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
