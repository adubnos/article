package com.example.article.Repository;

import com.example.article.Entity.Debate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DebateRepository extends JpaRepository<Debate, Integer> {

    @Modifying
    @Query("UPDATE Debate u set u.viewcnt=u.viewcnt+1 where u.debateId=:debateId")
    void viewcntInc(@Param("debateId") Integer debateId);
}
