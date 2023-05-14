package com.udacity.jwdnd.course1.cloudstorage.Services;

import com.udacity.jwdnd.course1.cloudstorage.Entity.File;
import com.udacity.jwdnd.course1.cloudstorage.Repo.FileMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {

    private FileMapper fileMapper;

   public FileService(FileMapper fileMapper) {
        this.fileMapper = fileMapper;
    }

    public File getFileByFileId(int fileId){
       return fileMapper.getFileById(fileId);

    }
    public void addFile(File files){
       fileMapper.insertFiles(files);
    }

    public void deleteFile(int fileId){
        fileMapper.deleteFile(fileId);
    }

    public List<File> getFilesByUserId (int userId){
        return fileMapper.getFiles(userId);
    }

    public boolean duplicateFileCheck(String filename, Integer userId){
       return this.fileMapper.getFilename(filename, userId) == null;
    }



}
