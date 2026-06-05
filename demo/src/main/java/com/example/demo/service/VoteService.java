package com.example.demo.service;

import com.example.demo.models.Picture;
import com.example.demo.models.PictureDTO;
import com.example.demo.models.Vote;
import com.example.demo.repository.PictureRepository;
import com.example.demo.repository.UsersRepository;
import com.example.demo.repository.VoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final PictureRepository pictureRepository;
    private final UsersRepository usersRepository;
    private final static Logger logger = LoggerFactory.getLogger(VoteService.class);

    public VoteService(VoteRepository voteRepository, PictureRepository pictureRepository, UsersRepository usersRepository) {
        this.voteRepository = voteRepository;
        this.pictureRepository = pictureRepository;
        this.usersRepository = usersRepository;
    }

    public Vote addVote(Integer voterId, Integer pictureId, Integer voteNumber){
        logger.info("AddVote started in VoteService.");
        Picture picture = pictureRepository.findById(pictureId).orElse(null);
        if(picture.getAuthor().getId().equals(voterId)){
            logger.warn("You can't vote your own picture.");
            return null;
        }
        if (voteNumber == null || voteNumber <= 0){
            logger.warn("Invalid voteNumber in VoteService.");
            return null;
        }
        if (voteRepository.existsByVoterIdAndPictureId(voterId, pictureId)){
            logger.warn("Already voted.");
            return null;
        }
        Vote vote = new Vote();
        vote.setPicture(picture);
        vote.setVoter(usersRepository.getReferenceById(voterId));
        vote.setValue(voteNumber);
        return voteRepository.save(vote);
    }

    public Map<Integer, Integer> getTotalVotes(){
        logger.info("SumVotes started in VoteService.");
        Map<Integer, Integer> votes = new HashMap<>();
        for(Picture p : pictureRepository.findAll()){
            Integer pid = p.getId();
            Integer total = voteRepository.sumValueByPictureId(pid);
            if (total == null)
                total = 0;
            votes.put(pid, total);
        }
        return votes;
    }

    public List<PictureDTO> getTopN(Integer n){
        logger.info("GetTopN started in the VoteRepository.");
        List<PictureDTO> list = voteRepository.findTopPictures(PageRequest.of(0, n));
        return list;
    }
}