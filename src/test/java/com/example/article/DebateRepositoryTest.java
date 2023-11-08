package com.example.article;

import com.example.article.Entity.Debate;
import com.example.article.Entity.Opinion;
import com.example.article.Repository.DebateRepository;
import com.example.article.Repository.OpinionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
public class DebateRepositoryTest {
    @Autowired
    private DebateRepository debateRepository;
    @Autowired
    private OpinionRepository opinionRepository;

    @Test
    public void debateRegisterTest() {
        for(int i=1;i<=50;i++) {
            Debate debate=Debate.builder()
                    .subject("subject"+i)
                    .build();
            debateRepository.save(debate);
        }
    }

    @Test
    public void opinionRegisterTest() {
        for(int i=1;i<=50;i++) {
            Opinion opinion=Opinion.builder()
                    .comment("comment"+i)
                    .debate(debateRepository.findById(i).orElse(null))
                    .build();
            opinionRepository.save(opinion);
        }
    }

    @Test
    @Transactional
    public void findByDebateTest() {
        Integer id=3;

        List<Opinion> opinions=opinionRepository.findByDebate(id);

        System.out.println(opinions.toString());
    }
}
