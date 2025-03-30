package com.example.notes.controller;

import com.example.notes.models.Note;
import com.example.notes.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/notes") // Ensures API is properly structured
@CrossOrigin(origins = "*")  // Allows frontend to access backend
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Get all notes
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    // Get a note by ID
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Optional<Note> note = noteService.getNoteById(id);
        return note.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new note
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note savedNote = noteService.createNote(note);
        return ResponseEntity.ok(savedNote);
    }

    // Update a note
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable("id") Long id, @RequestBody Note newNoteData) {
        return ResponseEntity.ok(noteService.updateNote(id, newNoteData));
    }

    // Delete a note
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable("id") Long id) {
        System.out.println("Deleting note with ID: " + id); // Debug log
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/archived/{status}")
    public ResponseEntity<List<Note>> getNotesByArchivedStatus(@PathVariable("status") boolean status) {
        return ResponseEntity.ok(noteService.getNotesByArchivedStatus(status));
    }
}
