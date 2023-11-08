package com.example.article.Service;

import com.example.article.DTO.DebateDTO;
import com.example.article.Entity.Debate;
import com.example.article.Repository.DebateRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DebateService {
    private final DebateRepository debateRepository;
    private final ModelMapper modelMapper=new ModelMapper();

    public Page<DebateDTO> debateDTOS(Pageable pageable) {
        int page=pageable.getPageNumber()-1;
        int pageLimit=10;

        Page<Debate> paging=debateRepository.findAll(PageRequest.of(page,pageLimit, Sort.by(Sort.Direction.DESC,"debateId")));

        return paging.map(debate-> DebateDTO.builder()
                .debateId(debate.getDebateId())
                .subject(debate.getSubject())
                .viewcnt(debate.getViewcnt())
                .createDate(debate.getCreateDate())
                .modifyDate(debate.getModifyDate())
                .build());
    }

    public void register(DebateDTO debateDTO) {
        Debate debate=modelMapper.map(debateDTO, Debate.class);
        debateRepository.save(debate);
    }

    public DebateDTO listOne(Integer debateId) {
        Optional<Debate> debate=debateRepository.findById(debateId);
        return modelMapper.map(debate,DebateDTO.class);
    }

    public void modify(DebateDTO debateDTO) {
        Integer id=debateDTO.getDebateId();
        Debate debate=debateRepository.findById(id).orElseThrow();

        Debate modify=modelMapper.map(debateDTO,Debate.class);
        modify.setDebateId(debate.getDebateId());
        debateRepository.save(modify);
    }

    public void viewcntInc(Integer debateId) {
        debateRepository.viewcntInc(debateId);
    }

    public void delete(Integer debateId) {
        debateRepository.deleteById(debateId);
    }
}
