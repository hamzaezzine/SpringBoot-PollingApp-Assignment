package com.example.PollingApp.service;

import com.example.PollingApp.dto.OptionDTO;
import com.example.PollingApp.dto.PollDTO;
import com.example.PollingApp.model.Option;
import com.example.PollingApp.model.Poll;
import com.example.PollingApp.repository.OptionRepository;
import com.example.PollingApp.repository.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private OptionRepository optionRepository;

    public Poll createPoll(PollDTO pollDTO) {
        Poll poll = new Poll();
        poll.setQuestion(pollDTO.getQuestion());

        List<Option> options = pollDTO.getOptions().stream().map(optionDTO -> {
            Option option = new Option();
            option.setOptionText(optionDTO.getOptionText());
            option.setPoll(poll); // Associate with poll
            return option;
        }).collect(Collectors.toList());
        poll.setOptions(options);

        return pollRepository.save(poll);
    }

    public PollDTO getPollWithVotes(Long id) {
        Poll poll = pollRepository.findById(id).orElseThrow(() -> new RuntimeException("Poll not found"));
        PollDTO pollDTO = new PollDTO();
        pollDTO.setId(poll.getId());
        pollDTO.setQuestion(poll.getQuestion());

        List<OptionDTO> optionDTOs = poll.getOptions().stream().map(option -> {
            OptionDTO optionDTO = new OptionDTO();
            optionDTO.setId(option.getId());
            optionDTO.setOptionText(option.getOptionText());
            optionDTO.setVoteCount(option.getVoteCount());
            return optionDTO;
        }).collect(Collectors.toList());

        pollDTO.setOptions(optionDTOs);
        return pollDTO;
    }

    public Poll updatePoll(Long id, PollDTO pollDTO) {
        Poll poll = pollRepository.findById(id).orElseThrow(() -> new RuntimeException("Poll not found"));
        poll.setQuestion(pollDTO.getQuestion());

        // Update options if needed
        if (pollDTO.getOptions() != null) {
            List<Option> options = pollDTO.getOptions().stream().map(optionDTO -> {
                Option option = new Option();
                option.setOptionText(optionDTO.getOptionText());
                option.setPoll(poll);
                return option;
            }).collect(Collectors.toList());
            poll.setOptions(options);
        }

        return pollRepository.save(poll);
    }

    public void deletePoll(Long id) {
        Poll poll = pollRepository.findById(id).orElseThrow(() -> new RuntimeException("Poll not found"));
        pollRepository.delete(poll);
    }
}
