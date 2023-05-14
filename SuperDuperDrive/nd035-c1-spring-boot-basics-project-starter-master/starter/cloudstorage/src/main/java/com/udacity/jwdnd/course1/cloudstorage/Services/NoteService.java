package com.udacity.jwdnd.course1.cloudstorage.Services;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Repo.NoteMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private NoteMapper noteMapper;

    public NoteService(NoteMapper noteMapper) {
        this.noteMapper = noteMapper;
    }
    public List<Note> getUserNotes(int userId){
        return noteMapper.getNotesByUserId(userId);
    }
    public void createNote(Note notes){
        noteMapper.insertNotes(notes);
    }
    public void updateNote(Note notes){
        noteMapper.updateNote(notes);
    }
    public void deleteNote(int noteId){
        noteMapper.deleteNote(noteId);
    }

}
