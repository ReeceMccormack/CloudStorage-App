package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Entity.User;
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

@Controller
public class CredentialController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;


    public CredentialController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

    @PostMapping("/add-credential")
    public String addCredential(@ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential, Model model, Authentication authentication) {

        User user = userService.getUser(authentication.getPrincipal().toString());
        credential.setUserId(user.getUserId());

        if (credential.getCredentialId() != null) {
            try {
                credentialService.updateCredential(credential);
                model.addAttribute("successMessage", "Credential edited successfully!");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Unable to edit credential.");
            }

        } else {
            try {
                credentialService.createCredential(credential);
                model.addAttribute("successMessage", "Credential created successfully!");
            } catch (Exception e) {
                model.addAttribute("errorMessage", "Unable to create credential.");
            }
        }
        model.addAttribute("tab", "nav-credentials-tab");
        model.addAttribute("notes", noteService.getUserNotes(user.getUserId()));
        model.addAttribute("files", fileService.getFilesByUserId(user.getUserId()));
        model.addAttribute("credentials", credentialService.getUserCredentials(user.getUserId()));
        return "result";
    }

    @GetMapping ("/delete-credential/{credentialId}")
        public String deleteCredential (@PathVariable Integer credentialId, @ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential, Authentication authentication, Model model) {
        User user = userService.getUser(authentication.getPrincipal().toString());

        try {
            credentialService.deleteCredentials(credentialId);
            credentialService.getUserCredentials(user.getUserId());
            model.addAttribute("successMessage", "Credential removed successfully!");
            model.addAttribute("tab", "nav-credentials-tab");
            model.addAttribute("notes", noteService.getUserNotes(user.getUserId()));
            model.addAttribute("files", fileService.getFilesByUserId(user.getUserId()));
            model.addAttribute("credentials", credentialService.getUserCredentials(user.getUserId()));
        } catch (Exception e){
            model.addAttribute("errorMessage", "Unable to delete credential.");
        }
        return "result";
    }
}