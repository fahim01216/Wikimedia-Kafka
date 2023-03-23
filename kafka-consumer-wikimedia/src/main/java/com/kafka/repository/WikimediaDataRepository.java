package com.kafka.repository;

import com.kafka.entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaDataRepository extends JpaRepository<Wikimedia, Long> {
}
