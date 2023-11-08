package com.example.article.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="debate")
@SequenceGenerator(name="debate_SEQ", sequenceName = "debate_SEQ", allocationSize = 1)
public class Debate extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "debate_SEQ")
    private Integer debateId;

    @Column(nullable = false, length = 200)
    private String subject;

    @Column
    private Integer viewcnt;

    @JsonIgnore
    @OneToMany(mappedBy = "debate", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Opinion> opinions;

    @PrePersist
    public void prePersist() {
        if (viewcnt == null || viewcnt == 0) {
            viewcnt = 0;
        }
    }
}

