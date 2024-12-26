package com.example.PollingApp.repository;

import com.example.PollingApp.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}