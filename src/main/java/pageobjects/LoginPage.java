package pageobjects;

import commonlibrary.SeleniumCommon;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

public class LoginPage {
    protected  WebDriver driver;

    @FindBy(linkText = "Sign in")
    protected WebElement signInLink;

    @FindBy(name = "identifier")
    protected WebElement emailfield;

    @FindBy(name = "password")
    protected WebElement passwordFld;

    @FindBy(id = "identifierNext")
    protected WebElement nextButton;

    @FindBy(xpath = "//span[contains(text(),'Next')]")
    protected WebElement nextPassword;

    @FindBy(xpath = "//div[@class='nM']//div[@role='button']")
    protected WebElement composeButton;


    public LoginPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    public void goToLoginPage()
    {
        System.out.println("Function call started:-goToLoginPage");
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(signInLink,driver,15,signInLink.getText()));
        SeleniumCommon.pause(driver,5);
        System.out.println("Function call end:-goToLoginPage");
        Set<String> windowHandels = driver.getWindowHandles();
        ArrayList<String> winHandels = new ArrayList<String>(windowHandels);
        driver.switchTo().window(winHandels.get(1));
    }

    public void dologin (String userid, String password) throws InterruptedException {
        Assert.assertTrue(SeleniumCommon.clearAndSetText(driver,emailfield,userid,emailfield.getAttribute("placeholder")));
        SeleniumCommon.pause(driver,3);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(nextButton,driver,15,nextButton.getText()));
        SeleniumCommon.pause(driver,15);
        Assert.assertTrue(SeleniumCommon.clearAndSetText(driver,passwordFld,password,passwordFld.getAttribute("name")));
        SeleniumCommon.pause(driver,5);
        Assert.assertTrue(SeleniumCommon.checkElementPresentReadyAndClick(nextPassword,driver,15,nextPassword.getText()));
        SeleniumCommon.pause(driver,15);
    }

    public void VerifyLogin()
    {
        SeleniumCommon.verifyIfElementExistVisible(driver,composeButton,"Compose Button",16);
    }

}
