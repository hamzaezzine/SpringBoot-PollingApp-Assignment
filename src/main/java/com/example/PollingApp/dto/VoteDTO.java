package com.example.PollingApp.dto;

import lombok.Data;


public class VoteDTO {

    private Long pollId;
    private Long optionId;

    public VoteDTO() {}

    public VoteDTO(Long pollId, Long optionId) {
        this.pollId = pollId;
        this.optionId = optionId;
    }

    public Long getPollId() {
        return pollId;
    }

    public void setPollId(Long pollId) {
        this.pollId = pollId;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }
}
