package pageobjects;

import commonlibrary.SeleniumCommon;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class InboxPage {

    protected  WebDriver driver;

    @FindBy(xpath = "//div[@class='nM']//div[@role='button']")
    protected WebElement composeButton;

    @FindBy(name = "to")
    protected WebElement toField;

    @FindBy(name = "subjectbox")
    protected WebElement subjectField;

    @FindBy(xpath = "//div[contains(@aria-label,'Send')]")
    protected WebElement sendButton;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    protected WebElement bodyField;

    @FindBy(xpath = "//div[@aria-label='More options']//div[2]")
    protected WebElement mailMenue;

    @FindBy(xpath = "//div[@class='J-N-Jz'][contains(text(),'Label')]")
    protected WebElement labelLink;

    @FindBy(xpath = "//div[@title='Social']/div/div")
    protected WebElement socialLink;

    @FindBy(xpath = "//div[@class='xT']//span[@class='bqe']")
    protected WebElement subjectInbox;

    @FindBy(xpath = "//a[@title='Categories']")
    protected WebElement categories;

    @FindBy(xpath = "//a[@title='Social']")
    protected WebElement socialLable;

    @FindBy(xpath = "//span[@class='bog']//span[@class='bqe']")
    protected WebElement subjectSocial;

    @FindBy(xpath = "//div[@data-hovercard-id='rsk.connect@gmail.com']")
    protected WebElement emailToSelect;

    @FindBy(xpath = "//div[contains(text(),'Apply')]")
    protected WebElement applyBtn;

    @FindBy(xpath = "//span[@role='button']/span[contains(text(),'More')]")
    protected WebElement moreBtn;



    public InboxPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void composeEmail(String emailId, String subject,String mailContent) throws InterruptedException {
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(composeButton,driver,15,composeButton.getText()));
        SeleniumCommon.pause(driver,10);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(toField,driver,15,"To filed"));
        SeleniumCommon.pause(driver,10);
        Assert.assertTrue(SeleniumCommon.clearAndSetText(driver,toField,emailId,"To"));
        SeleniumCommon.pause(driver,20);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(bodyField,driver,15,"Email Body"));
        SeleniumCommon.pause(driver,20);
        Assert.assertTrue(SeleniumCommon.clearAndSetText(driver,subjectField,subject,"Subject"));
        SeleniumCommon.pause(driver,20);
        Assert.assertTrue(SeleniumCommon.clearAndSetText(driver,bodyField,mailContent,"Body"));
        SeleniumCommon.pause(driver,20);
    }

    public void addLabelSocial() throws InterruptedException {
        SeleniumCommon.pause(driver,60);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(mailMenue,driver,15,mailMenue.getText()));
        SeleniumCommon.pause(driver,60);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(labelLink,driver,15,labelLink.getText()));
        SeleniumCommon.pause(driver,60);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(socialLink,driver,15,socialLink.getText()));
        SeleniumCommon.pause(driver,60);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(applyBtn,driver,15,applyBtn.getText()));
        Thread.sleep(3000);
    }

    public void sendEmail() throws InterruptedException {
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(sendButton,driver,15,sendButton.getText()));
        SeleniumCommon.pause(driver,30);
        Thread.sleep(5000);
        driver.navigate().refresh();
        Thread.sleep(10000);
    }


    public void checkVerifyEmailRecieved(String emailId,String subjectEmail)
    {
        List<WebElement> allEmailsByMe = driver.findElements(By.xpath("//div[@class='yW']//span[@name='me'][contains(text(),'me')]"));
        WebElement subEmailInbox = driver.findElement(By.xpath("//span[@class='bog']//span[contains(text(),'"+subjectEmail.trim()+"')]"));
        if(allEmailsByMe.size()>0)
        {
            WebElement thisEmail = allEmailsByMe.get(0);
            Assert.assertEquals(thisEmail.getAttribute("email"),emailId);
            Assert.assertEquals(subEmailInbox.getText(),subjectEmail);
        }
    }

    public void checkEmailContent(String emailBody) throws InterruptedException {
        SeleniumCommon.pause(driver,60);
        List<WebElement> allEmailsByMe = driver.findElements(By.xpath("//div[@class='yW']//span[@name='me'][contains(text(),'me')]"));
        WebElement firstEmailRow = allEmailsByMe.get(0);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(firstEmailRow,driver,15,firstEmailRow.getText()));
        Thread.sleep(5000);
        WebElement emailContent = driver.findElement(By.xpath("//div[contains(text(),'"+emailBody.trim()+"')]"));
        SeleniumCommon.verifyIfElementExistVisible(driver,emailContent,"Email Body",15);
        Assert.assertEquals(emailContent.getText(),emailBody);
        Thread.sleep(5000);
    }

    public void checkVerifyEmailRecievedLabel(String emailId,String subjectEmail, String emailBody) throws InterruptedException {
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(moreBtn,driver,15,moreBtn.getText()));
        Thread.sleep(3000);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(categories,driver,15,categories.getText()));
        Thread.sleep(3000);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(categories,driver,15,categories.getText()));
        Thread.sleep(3000);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(socialLable,driver,15,socialLable.getText()));
        Thread.sleep(5000);
        List<WebElement> starsAll = driver.findElements(By.xpath("//span[@title='Not starred']"));
        WebElement thisStar = starsAll.get(1);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(thisStar,driver,15,"Star"));
        Thread.sleep(3000);
        List<WebElement> allEmailsByMe = driver.findElements(By.xpath("//div[@class='yW']//span[@name='me'][contains(text(),'me')]"));
        WebElement firstEmailRow = allEmailsByMe.get(0);
        Assert.assertEquals(firstEmailRow.getAttribute("email"),emailId);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(firstEmailRow,driver,15,"Email Recieved"));
        Thread.sleep(5000);
        WebElement emailContent = driver.findElement(By.xpath("//div[contains(text(),'"+emailBody.trim()+"')]"));
        SeleniumCommon.verifyIfElementExistVisible(driver,emailContent,"Email Body",15);
        Assert.assertEquals(emailContent.getText(),emailBody);
        Thread.sleep(5000);
        WebElement subjElement = driver.findElement(By.xpath("//h2[contains(text(),'"+subjectEmail+"')]"));
        Assert.assertEquals(subjElement.getText(),subjectEmail);
    }
}
