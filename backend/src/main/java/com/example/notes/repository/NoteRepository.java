package com.example.notes.repository;

import com.example.notes.models.Note;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByArchived(boolean archived);

}