package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entities.PanDetails;

@Repository
public interface PanRepository extends JpaRepository<PanDetails, Long> {
	PanDetails findByPanNumber(String panNumber);

	void deleteByPanNumber(String panNumber);
}
