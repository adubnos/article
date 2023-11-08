package com.example.article.Repository;

import com.example.article.Entity.Debate;
import com.example.article.Entity.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public interface OpinionRepository extends JpaRepository<Opinion, Integer> {

    @Modifying
    @Query("UPDATE Opinion u set u.goodcnt=u.goodcnt+1 where u.opinionId=:opinionId")
    void goodcntInc(@Param("opinionId") Integer opinionId);

    @Modifying
    @Query("UPDATE Opinion u set u.badcnt=u.badcnt+1 where u.opinionId=:opinionId")
    void badcntInc(@Param("opinionId") Integer opinionId);

    @Query("SELECT u From Opinion u WHERE u.debate.debateId = :debateId")
    List<Opinion> findByDebate(@Param("debateId") Integer debateId);
}
