package com.example.PollingApp.controller;

import com.example.PollingApp.dto.VoteDTO;
import com.example.PollingApp.model.Vote;
import com.example.PollingApp.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/votes")
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<Vote> castVote(@RequestBody VoteDTO voteDTO) {
        return ResponseEntity.ok(voteService.castVote(voteDTO.getPollId(), voteDTO.getOptionId()));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Vote> editVote(@PathVariable Long id, @RequestBody VoteDTO voteDTO) {
        return ResponseEntity.ok(voteService.editVote(id, voteDTO.getOptionId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        voteService.deleteVote(id);
        return ResponseEntity.noContent().build();
    }
}
