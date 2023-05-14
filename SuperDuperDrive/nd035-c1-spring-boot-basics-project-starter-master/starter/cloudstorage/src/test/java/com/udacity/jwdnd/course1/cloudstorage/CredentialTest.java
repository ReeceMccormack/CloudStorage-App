package com.udacity.jwdnd.course1.cloudstorage;


import com.udacity.jwdnd.course1.cloudstorage.Entity.Credential;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CredentialTest {


    @LocalServerPort
    private int port;

    private WebDriver webDriver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.webDriver = new ChromeDriver();

    }

    @AfterEach
    public void afterEach() {
        webDriver.quit();

    }

    @Test
    public void createCredential() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        //Signing up
        webDriver.get("http://localhost:" + this.port + "/signup");
        WebElement FirstName = webDriver.findElement(By.id("inputFirstName"));
        FirstName.sendKeys("John");
        WebElement LastName = webDriver.findElement(By.id("inputLastName"));
        LastName.sendKeys("Doe");
        WebElement Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        WebElement Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement SignupButton = webDriver.findElement(By.id("submit-button"));
        SignupButton.click();

        //Logging in
        webDriver.get("http://localhost:" + this.port + "/login");
        Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement LoginButton = webDriver.findElement(By.id("submit-button"));
        LoginButton.click();

        //Creating Credential
        WebElement CredentialsTab = webDriver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        WebElement NewCredential = webDriver.findElement(By.id("add-credential-button"));
        Thread.sleep(3000);
        NewCredential.click();
        Thread.sleep(3000);
        WebElement CredentialURL = webDriver.findElement(By.id("cred-url"));
        CredentialURL.sendKeys("http//:example.com");
        WebElement CredentialUsername = webDriver.findElement(By.id("cred-username"));
        CredentialUsername.sendKeys("JohnDoe44");
        WebElement CredentialPassword = webDriver.findElement(By.id("cred-password"));
        CredentialPassword.sendKeys("123456");
        WebElement SaveChanges = webDriver.findElement(By.id("credential-save-button"));
        SaveChanges.click();
        Thread.sleep(3000);
    }

    @Test
        public void editCredential () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        //Signing up
        webDriver.get("http://localhost:" + this.port + "/signup");
        WebElement FirstName = webDriver.findElement(By.id("inputFirstName"));
        FirstName.sendKeys("John");
        WebElement LastName = webDriver.findElement(By.id("inputLastName"));
        LastName.sendKeys("Doe");
        WebElement Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        WebElement Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement SignupButton = webDriver.findElement(By.id("submit-button"));
        SignupButton.click();
        //Logging in
        webDriver.get("http://localhost:" + this.port + "/login");
        Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement LoginButton = webDriver.findElement(By.id("submit-button"));
        LoginButton.click();

        //Creating Credential
        WebElement CredentialsTab = webDriver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        WebElement NewCredential = webDriver.findElement(By.id("add-credential-button"));
        Thread.sleep(3000);
        NewCredential.click();
        Thread.sleep(3000);
        WebElement CredentialURL = webDriver.findElement(By.id("cred-url"));
        CredentialURL.sendKeys("http//:example.com");
        WebElement CredentialUsername = webDriver.findElement(By.id("cred-username"));
        CredentialUsername.sendKeys("JohnDoe44");
        WebElement CredentialPassword = webDriver.findElement(By.id("cred-password"));
        CredentialPassword.sendKeys("123456");
        WebElement SaveChanges = webDriver.findElement(By.id("credential-save-button"));
        SaveChanges.click();
        Thread.sleep(3000);

        //Editing Credential
        webDriver.get("http://localhost:" + this.port + "/home");
        CredentialsTab = webDriver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        Thread.sleep(3000);
        WebElement EditNote = webDriver.findElement(By.id("edit-credential-button"));
        EditNote.click();
        Thread.sleep(3000);
        CredentialURL = webDriver.findElement(By.id("cred-url"));
        CredentialURL.clear();
        CredentialURL.sendKeys("http//:edited.com");
        CredentialUsername = webDriver.findElement(By.id("cred-username"));
        CredentialUsername.clear();
        CredentialUsername.sendKeys("JoeySwashbuckle");
        CredentialPassword = webDriver.findElement(By.id("cred-password"));
        CredentialPassword.clear();
        CredentialPassword.sendKeys("654321");
        WebElement SaveNewChanges = webDriver.findElement(By.id("credential-save-button"));
        SaveNewChanges.click();
        Thread.sleep(3000);
    }

    @Test
        public void deleteCredential () throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;

        //Signing up
        webDriver.get("http://localhost:" + this.port + "/signup");
        WebElement FirstName = webDriver.findElement(By.id("inputFirstName"));
        FirstName.sendKeys("John");
        WebElement LastName = webDriver.findElement(By.id("inputLastName"));
        LastName.sendKeys("Doe");
        WebElement Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        WebElement Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement SignupButton = webDriver.findElement(By.id("submit-button"));
        SignupButton.click();

        //Logging in
        webDriver.get("http://localhost:" + this.port + "/login");
        Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnDoe");
        Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement LoginButton = webDriver.findElement(By.id("submit-button"));
        LoginButton.click();

        //Creating Credential
        WebElement CredentialsTab = webDriver.findElement(By.id("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        WebElement NewCredential = webDriver.findElement(By.id("add-credential-button"));
        Thread.sleep(3000);
        NewCredential.click();
        Thread.sleep(3000);
        WebElement CredentialURL = webDriver.findElement(By.id("cred-url"));
        CredentialURL.sendKeys("http//:example.com");
        WebElement CredentialUsername = webDriver.findElement(By.id("cred-username"));
        CredentialUsername.sendKeys("JohnDoe44");
        WebElement CredentialPassword = webDriver.findElement(By.id("cred-password"));
        CredentialPassword.sendKeys("123456");
        WebElement SaveChanges = webDriver.findElement(By.id("credential-save-button"));
        SaveChanges.click();
        Thread.sleep(3000);

        //Deleting Credential
        webDriver.get("http://localhost:" + this.port + "/home");
        CredentialsTab = webDriver.findElement(By.id ("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        Thread.sleep(3000);
        WebElement DeleteCredential = webDriver.findElement(By.id("delete-credential-button"));
        DeleteCredential.click();
        Thread.sleep(3000);
        webDriver.get("http://localhost:" + this.port + "/home");
        CredentialsTab = webDriver.findElement(By.id ("nav-credentials-tab"));
        jse.executeScript("arguments[0].click()", CredentialsTab);
        Thread.sleep(3000);
    }
}







