package com.example.article.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="opinion")
@SequenceGenerator(name="opinion_SEQ", sequenceName = "opinion_SEQ", allocationSize = 1)
public class Opinion extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "opinion_SEQ")
    private Integer opinionId;

    @Column(nullable = false, length = 200)
    private String comment;

    @Column(columnDefinition = "Integer default 0")
    private Integer goodcnt;

    @Column(columnDefinition = "Integer default 0")
    private Integer badcnt;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="DEBATE_ID")
    private Debate debate;

    @PrePersist
    public void prePersist() {
        if (goodcnt == null || goodcnt == 0) {
            goodcnt = 0;
        }
        if (badcnt == null || badcnt == 0) {
            badcnt = 0;
        }
    }

}
