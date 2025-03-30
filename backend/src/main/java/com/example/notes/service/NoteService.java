package com.example.notes.service;

import com.example.notes.models.Note;
import com.example.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public Note createNote(Note note) {
        return noteRepository.save(note);
    }

    public Note updateNote(Long id, Note newNoteData) {
        return noteRepository.findById(id)
            .map(note -> {
                note.setTitle(newNoteData.getTitle());
                note.setContent(newNoteData.getContent());
                note.setArchived(newNoteData.isArchived());
                note.setUpdatedAt(newNoteData.getUpdatedAt());
                return noteRepository.save(note);
            }).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);
    }
    
    //Get notes by archived status
    public List<Note> getNotesByArchivedStatus(boolean archived) {
        return noteRepository.findByArchived(archived);
    }
}