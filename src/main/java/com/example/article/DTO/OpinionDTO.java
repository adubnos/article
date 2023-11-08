package com.example.article.DTO;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OpinionDTO {
    private Integer opinionId;
    private String comment;
    private Integer goodcnt;
    private Integer badcnt;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
    private Integer debateId;
}
