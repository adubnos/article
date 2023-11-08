package com.example.article.Service;

import com.example.article.DTO.OpinionDTO;
import com.example.article.Entity.Debate;
import com.example.article.Entity.Opinion;
import com.example.article.Repository.DebateRepository;
import com.example.article.Repository.OpinionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class OpinionService {
    private final OpinionRepository opinionRepository;
    private final DebateRepository debateRepository;
    private final ModelMapper modelMapper=new ModelMapper();

    public List<OpinionDTO> opinionDTOS(Integer debateId) {
        List<Opinion> opinions=opinionRepository.findByDebate(debateId);

        return Arrays.asList(modelMapper.map(opinions, OpinionDTO[].class));
    }

    public void opinionRegister(OpinionDTO opinionDTO, Integer debateId) {
        Debate debate=debateRepository.findById(debateId).orElseThrow(null);
        Opinion opinion=Opinion.builder()
                .comment(opinionDTO.getComment())
                .debate(debate)
                .build();
        opinionRepository.save(opinion);
    }

    public void opinionModify(OpinionDTO opinionDTO, Integer opinionId, Integer debateId) {
        Debate debate=debateRepository.findById(debateId).orElseThrow(null);
        Opinion opinion=opinionRepository.findById(opinionId).orElseThrow();

        Opinion modify=modelMapper.map(opinionDTO, Opinion.class);
        modify.setOpinionId(opinion.getOpinionId());
        modify.setDebate(debate);
        opinionRepository.save(modify);
    }

    public void goodcntInc(Integer opinionId) {
        opinionRepository.goodcntInc(opinionId);
    }

    public void badcntInc(Integer opinionId) {
        opinionRepository.badcntInc(opinionId);
    }

    public void opinionDelete(Integer opinionId) {
        opinionRepository.deleteById(opinionId);
    }
}
