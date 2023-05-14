package com.udacity.jwdnd.course1.cloudstorage.Services;

import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.Repo.CredentialMapper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialService {


   private CredentialMapper credentialMapper;
   private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    private String generateKey(){
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    private String encryptPassword(Credential credential){
        return this.encryptionService.encryptValue(credential.getPassword(), credential.getKey());
    }

    private String decryptPassword(Credential credential){
        return this.encryptionService.decryptValue(credential.getPassword(), credential.getKey());
    }

    public void createCredential(Credential credential){
        credential.setKey(generateKey());
        credential.setPassword(encryptPassword(credential));
         this.credentialMapper.insertCredential(credential);
    }

    public void updateCredential(Credential credential){

        String key = generateKey();
        credential.setKey(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(),key);
        credential.setPassword(encryptedPassword);
        this.credentialMapper.updateCredential(credential);
    }

    public List<Credential> getUserCredentials(Integer userId){
        return credentialMapper.getUserCredentials(userId);
    }

    public Credential getCredentialsById(int credentialId){
       return credentialMapper.getCredentialById(credentialId);
    }

    public void deleteCredentials(Integer credentialId){
        this.credentialMapper.deleteCredential(credentialId);
    }


}
