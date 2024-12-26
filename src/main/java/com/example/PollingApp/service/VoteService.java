package com.example.PollingApp.service;

import com.example.PollingApp.model.Option;
import com.example.PollingApp.model.Poll;
import com.example.PollingApp.model.Vote;
import com.example.PollingApp.repository.OptionRepository;
import com.example.PollingApp.repository.PollRepository;
import com.example.PollingApp.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private PollRepository pollRepository;

    public Vote castVote(Long pollId, Long optionId) {
        Poll poll = pollRepository.findById(pollId).orElseThrow(() -> new RuntimeException("Poll not found"));
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Option not found"));

        Vote vote = new Vote();
        vote.setOption(option);
        voteRepository.save(vote);

        option.setVoteCount(option.getVoteCount() + 1);
        optionRepository.save(option);

        return vote;
    }

    public Vote editVote(Long voteId, Long optionId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        Option newOption = optionRepository.findById(optionId).orElseThrow(() -> new RuntimeException("Option not found"));

        Option oldOption = vote.getOption();
        oldOption.setVoteCount(oldOption.getVoteCount() - 1);
        optionRepository.save(oldOption);

        vote.setOption(newOption);

        newOption.setVoteCount(newOption.getVoteCount() + 1);
        optionRepository.save(newOption);

        return voteRepository.save(vote);
    }

    public void deleteVote(Long voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() -> new RuntimeException("Vote not found"));
        Option option = vote.getOption();

        option.setVoteCount(option.getVoteCount() - 1);
        optionRepository.save(option);

        voteRepository.delete(vote);
    }
}
