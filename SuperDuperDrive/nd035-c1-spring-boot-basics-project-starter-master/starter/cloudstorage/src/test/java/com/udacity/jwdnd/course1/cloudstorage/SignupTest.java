package com.udacity.jwdnd.course1.cloudstorage;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.io.File;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SignupTest {

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
    public void retrieveLoginPage() throws InterruptedException {
        webDriver.get("http://localhost:" + this.port + "/login");
        Thread.sleep(3000);

    }

    @Test
    public void retrieveSignupPage() throws InterruptedException {
        webDriver.get("http://localhost:" + this.port + "/signup");
        Thread.sleep(3000);

    }

    @Test
    public void unauthorisedAccessHomePage(){
        webDriver.get("http://localhost:" + this.port + "/home");
    }

    @Test
    public void newUserTest(){

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

    }
    @Test
    public void testRedirection() throws InterruptedException {

        //Signing up
        webDriver.get("http://localhost:" + this.port + "/signup");
        WebElement FirstName = webDriver.findElement(By.id("inputFirstName"));
        FirstName.sendKeys("John");
        WebElement LastName = webDriver.findElement(By.id("inputLastName"));
        LastName.sendKeys("Tango");
        WebElement Username = webDriver.findElement(By.id("inputUsername"));
        Username.sendKeys("JohnTango");
        WebElement Password = webDriver.findElement(By.id("inputPassword"));
        Password.sendKeys("123456");
        WebElement SignupButton = webDriver.findElement(By.id("submit-button"));
        SignupButton.click();
        Thread.sleep(3000);

        Assertions.assertEquals("http://localhost:" + this.port + "/login", webDriver.getCurrentUrl());

    }

    @Test
    public void testBadUrl() {
        // Create a test account
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


        // Try to access a random made-up URL.
        webDriver.get("http://localhost:" + this.port + "/some-random-page");
        Assertions.assertFalse(webDriver.getPageSource().contains("Whitelabel Error Page"));
    }

    @Test
    public void testLargeUpload() {

        // Create a test account
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

        // Try to upload an arbitrary large file
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, 2);
        String fileName = "upload5m.zip";

        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fileUpload")));
        WebElement fileSelectButton = webDriver.findElement(By.id("fileUpload"));
        fileSelectButton.sendKeys(new File(fileName).getAbsolutePath());

        WebElement uploadButton = webDriver.findElement(By.id("uploadButton"));
        uploadButton.click();
        try {
            webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("success")));
        } catch (org.openqa.selenium.TimeoutException e) {
            System.out.println("Large File upload failed");
        }
        Assertions.assertFalse(webDriver.getPageSource().contains("HTTP Status 403 – Forbidden"));

    }
}