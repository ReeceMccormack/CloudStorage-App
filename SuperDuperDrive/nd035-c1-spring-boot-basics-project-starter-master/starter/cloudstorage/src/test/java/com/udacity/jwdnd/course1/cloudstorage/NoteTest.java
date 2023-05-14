package com.udacity.jwdnd.course1.cloudstorage;


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
public class NoteTest {


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
    public void createNote() throws InterruptedException {
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

        //Adding Note
        WebElement NoteTab = webDriver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        WebElement NewNoteButton = webDriver.findElement(By.id("add-note-button"));
        Thread.sleep(3000);
        NewNoteButton.click();
        Thread.sleep(3000);
        WebElement NoteTitle = webDriver.findElement(By.id("note-title"));
        NoteTitle.sendKeys("Example Title");
        WebElement NoteDescription = webDriver.findElement(By.id("note-description"));
        NoteDescription.sendKeys("Example Description");
        WebElement SaveChanges = webDriver.findElement(By.id("save-note-button"));
        SaveChanges.click();
        Thread.sleep(3000);
    }

    @Test
    public void editNote() throws InterruptedException {
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

        //Adding Note
        WebElement NoteTab = webDriver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        WebElement NewNoteButton = webDriver.findElement(By.id("add-note-button"));
        Thread.sleep(3000);
        NewNoteButton.click();
        Thread.sleep(3000);
        WebElement NoteTitle = webDriver.findElement(By.id("note-title"));
        NoteTitle.sendKeys("Example Title");
        WebElement NoteDescription = webDriver.findElement(By.id("note-description"));
        NoteDescription.sendKeys("Example Description");
        WebElement SaveChanges = webDriver.findElement(By.id("save-note-button"));
        SaveChanges.click();
        Thread.sleep(3000);

        //Editing Note
        webDriver.get("http://localhost:" + this.port + "/home");
        NoteTab = webDriver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        Thread.sleep(3000);
        WebElement EditNote = webDriver.findElement(By.id("edit-note-button"));
        EditNote.click();
        Thread.sleep(3000);
        NoteTitle = webDriver.findElement(By.id("note-title"));
        NoteTitle.clear();
        NoteTitle.sendKeys("Edited Title");
        NoteDescription = webDriver.findElement(By.id("note-description"));
        NoteDescription.clear();
        NoteDescription.sendKeys("Edited  Description");
        WebElement SaveNewChanges = webDriver.findElement(By.id("save-note-button"));
        SaveNewChanges.click();
        Thread.sleep(3000);


    }

    @Test
    public void deleteNote() throws InterruptedException {
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

        //Adding Note
        WebElement NoteTab = webDriver.findElement(By.id("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        WebElement NewNoteButton = webDriver.findElement(By.id("add-note-button"));
        Thread.sleep(3000);
        NewNoteButton.click();
        Thread.sleep(3000);
        WebElement NoteTitle = webDriver.findElement(By.id("note-title"));
        NoteTitle.sendKeys("Example Title");
        WebElement NoteDescription = webDriver.findElement(By.id("note-description"));
        NoteDescription.sendKeys("Example Description");
        WebElement SaveChanges = webDriver.findElement(By.id("save-note-button"));
        SaveChanges.click();
        Thread.sleep(3000);

        //Deleting Note
        webDriver.get("http://localhost:" + this.port + "/home");
        NoteTab = webDriver.findElement(By.id ("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        Thread.sleep (3000);
        WebElement DeleteNote = webDriver.findElement(By.id("delete-note-button"));
        DeleteNote.click();
        Thread.sleep(3000);
        webDriver.get("http://localhost:" + this.port + "/home");
        NoteTab = webDriver.findElement(By.id ("nav-notes-tab"));
        jse.executeScript("arguments[0].click()", NoteTab);
        Thread.sleep (3000);

        }

    }

