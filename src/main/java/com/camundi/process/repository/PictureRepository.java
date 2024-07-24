package com.camundi.process.repository;


import com.camundi.process.model.Picture;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
	Optional<Picture> findByProcessInstanceKey(Long processInstanceKey);
}
