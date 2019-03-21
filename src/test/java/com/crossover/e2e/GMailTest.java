package com.crossover.e2e;

import commonlibrary.SeleniumCommon;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageobjects.InboxPage;
import pageobjects.LoginPage;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class GMailTest  {
    private WebDriver driver;
    private Properties properties = new Properties();
    private LoginPage loginPage;
    private InboxPage inboxPage;

    @BeforeTest
    public void setUp() throws Exception {
        
        properties.load(new FileReader(new File("src/test/resources/test.properties")));
        //Dont Change below line. Set this value in test.properties file incase you need to change it..
        System.setProperty("webdriver.chrome.driver",properties.getProperty("webdriver.chrome.driver") );
        driver = new ChromeDriver();
        driver.get("https://www.google.com/intl/en-GB/gmail/about/#");
        SeleniumCommon.pause(driver,3);
        driver.manage().window().maximize();
        SeleniumCommon.pause(driver,3);
        loginPage = new LoginPage(driver);
        inboxPage = new InboxPage(driver);
    }



    @Test(priority = 0)
    public void verifyLoginGmail() throws InterruptedException {

        String userName = properties.getProperty("username");
        String password = properties.getProperty("password");
        loginPage.goToLoginPage();
        loginPage.dologin(userName,password);
        loginPage.VerifyLogin();
    }

    @Test(priority = 1)
    public void verifySendEmail() throws InterruptedException {
        String userName = properties.getProperty("username");
        String emailSubject = properties.getProperty("email.subject");
        String emailBody = properties.getProperty("email.body");
        inboxPage.composeEmail(userName,emailSubject,emailBody);
        inboxPage.addLabelSocial();
        inboxPage.sendEmail();
       // inboxPage.checkVerifyEmailRecieved(userName,emailSubject);
        //inboxPage.checkEmailContent(emailBody);
    }

    @Test(priority = 2, enabled = true)
    public void verifyEmailMovedLabel() throws InterruptedException {
        String userName = properties.getProperty("username");
        String emailBody = properties.getProperty("email.body");
        String emailSubject = properties.getProperty("email.subject");
        inboxPage.checkVerifyEmailRecievedLabel(userName,emailSubject,emailBody);
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
