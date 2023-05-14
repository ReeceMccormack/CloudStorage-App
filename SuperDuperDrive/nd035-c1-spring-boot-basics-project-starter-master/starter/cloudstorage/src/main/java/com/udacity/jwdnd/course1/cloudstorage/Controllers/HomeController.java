package com.udacity.jwdnd.course1.cloudstorage.Controllers;

import com.udacity.jwdnd.course1.cloudstorage.Entity.*;
import com.udacity.jwdnd.course1.cloudstorage.Services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;

    private EncryptionService encryptionService;

    public HomeController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService, EncryptionService encryptionService) {
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }


    @GetMapping
    public String homeView(Authentication authentication, Model model, @ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential) {
        User user = userService.getUser(authentication.getPrincipal().toString());
        int userId = user.getUserId();
        model.addAttribute("files", this.fileService.getFilesByUserId(userId));
        model.addAttribute("notes", this.noteService.getUserNotes(userId));
        model.addAttribute("credentials", this.credentialService.getUserCredentials(userId));
        model.addAttribute("encryptionService", encryptionService);


        return "home";
    }
}