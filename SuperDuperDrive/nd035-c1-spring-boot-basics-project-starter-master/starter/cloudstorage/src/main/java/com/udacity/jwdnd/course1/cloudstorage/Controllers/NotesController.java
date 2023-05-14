package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Entity.User;
import com.udacity.jwdnd.course1.cloudstorage.Repo.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.Services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.Services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.Services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.Services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class NotesController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;

    private UserMapper userMapper;

    public NotesController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService, UserMapper userMapper) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping("/add-note")
    public String addNote(@ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential, Model model, Authentication authentication){
        User user = userService.getUser(authentication.getPrincipal().toString());
        note.setUserId(user.getUserId());

        if (note.getNoteId() !=null){
            noteService.updateNote(note);
            model.addAttribute("successMessage", "Note edited successfully!");
            model.addAttribute("tab", "nav-notes-tab");
            model.addAttribute("notes", noteService.getUserNotes(user.getUserId()));
            model.addAttribute("files", fileService.getFilesByUserId(user.getUserId()));
            model.addAttribute("credentials", credentialService.getUserCredentials(user.getUserId()));
            return "result";
        } else {
            try {
                Note noteObject = new Note(note.getNoteTitle(), note.getNoteDescription(), user.getUserId());
                noteService.createNote(noteObject);
                model.addAttribute("successMessage", "Note created successfully!");
                model.addAttribute("tab", "nav-notes-tab");
                model.addAttribute("notes", noteService.getUserNotes(user.getUserId()));
                model.addAttribute("files", fileService.getFilesByUserId(user.getUserId()));
                model.addAttribute("credentials", credentialService.getUserCredentials(user.getUserId()));
                return "result";
            } catch (Exception e){
                model.addAttribute("errorMessage", "Unable to add file");
                return "result";
            }

        }

    }

    @GetMapping("/note-delete/{noteId}")
        public String deleteNote(@PathVariable Integer noteId, @ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential, Model model, Authentication authentication){

        User user = userService.getUser(authentication.getPrincipal().toString());

        try {
            noteService.deleteNote(noteId);
            List<Note> noteList = noteService.getUserNotes(user.getUserId());
            model.addAttribute("successMessage", "Note deleted successfully!");
        } catch (Exception e){
            model.addAttribute("errorMessage", "Unable to delete note.");
        }
        return "result";
    }
}
