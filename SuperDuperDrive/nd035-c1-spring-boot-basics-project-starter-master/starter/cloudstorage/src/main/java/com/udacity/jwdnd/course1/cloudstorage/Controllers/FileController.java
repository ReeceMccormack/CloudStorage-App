package com.udacity.jwdnd.course1.cloudstorage.Controllers;


import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Entity.File;
import com.udacity.jwdnd.course1.cloudstorage.Entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.Entity.User;
import com.udacity.jwdnd.course1.cloudstorage.Services.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
public class FileController {

    private FileService fileService;
    private UserService userService;
    private NoteService noteService;
    private CredentialService credentialService;


    public FileController(FileService fileService, UserService userService, NoteService noteService, CredentialService credentialService){
        this.noteService = noteService;
        this.fileService = fileService;
        this.credentialService = credentialService;
        this.userService = userService;
    }

@PostMapping("/files")
    public String uploadFiles(@RequestParam("fileUpload")MultipartFile multipartFile, Authentication authentication, Model model,@ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential){
    User user = userService.getUser(authentication.getPrincipal().toString());
    model.addAttribute("successMessage", false);

    if(multipartFile.getOriginalFilename().isEmpty()){
        model.addAttribute("errorMessage", "File is empty.");
        return "result";}

        if (!fileService.duplicateFileCheck(multipartFile.getOriginalFilename(), user.getUserId())){
            model.addAttribute("errorMessage", "Filename already exists.");
            return "result";}
     try {
        File fileObject = new File(multipartFile.getOriginalFilename(), multipartFile.getContentType(), multipartFile.getSize() + "", user.getUserId(), multipartFile.getBytes());
        fileService.addFile(fileObject);
        List<File> fileList = fileService.getFilesByUserId(user.getUserId());

        model.addAttribute("files", fileList);
        model.addAttribute("successMessage", "File Saved!");

    } catch (IOException e) {
         e.printStackTrace();
         model.addAttribute("errorMessage", e.getMessage());
     }



    return "result";
}

@GetMapping("files/delete/{fileId}")
    public String deleteFile(@PathVariable Integer fileId, Authentication authentication, Model model, @ModelAttribute("note") Note note, @ModelAttribute("credential") Credential credential){
        User user = userService.getUser(authentication.getPrincipal().toString());

        try {
            fileService.deleteFile(fileId);
            List<File> fileList = fileService.getFilesByUserId(user.getUserId());
            model.addAttribute("successMessage", "File deleted successfully!");
        }catch(Exception e){
            model.addAttribute("errorMessage", "Unable to delete file");
        }
        return "result";
}

@GetMapping("files/{fileId}")
 public ResponseEntity<byte[]> downloadFile(@PathVariable Integer fileId){
        File file = fileService.getFileByFileId(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        byte[] fileContents = file.getFiledata();
        httpHeaders.setContentType(MediaType.parseMediaType(file.getContenttype()));
        String fileName = file.getFilename();
        httpHeaders.setContentDispositionFormData(fileName,fileName);

        ResponseEntity<byte[]> responseEntity = new ResponseEntity<byte[]>(fileContents, httpHeaders, HttpStatus.OK);
        return responseEntity;
}
}
