package com.example.PollingApp.controller;

import com.example.PollingApp.dto.PollDTO;
import com.example.PollingApp.model.Poll;
import com.example.PollingApp.service.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollService pollService;

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody PollDTO pollDTO) {
        return ResponseEntity.ok(pollService.createPoll(pollDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PollDTO> getPoll(@PathVariable Long id) {
        return ResponseEntity.ok(pollService.getPollWithVotes(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable Long id, @RequestBody PollDTO pollDTO) {
        return ResponseEntity.ok(pollService.updatePoll(id, pollDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable Long id) {
        pollService.deletePoll(id);
        return ResponseEntity.noContent().build();
    }
}
