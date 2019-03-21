package commonlibrary;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class SeleniumCommon {

    public static  boolean verifyIfElementExistVisible(WebDriver driver, WebElement element, String webElementName, int timeOut)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeOut);
        wait.until(ExpectedConditions.visibilityOf(element));
        if(element.isDisplayed())
        {
            System.out.println("Element lcoated and visible : "+webElementName);
            return true;
        }
        else
        {
            System.out.println("Element could not be located and visible : "+webElementName);
            return false;
        }
    }

    public static boolean clearAndSetText(WebDriver driver, WebElement element, String text,String elementName) throws InterruptedException {
        System.out.println("Function call started :clearAndSetText");
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.visibilityOf(element));
        if(element.isDisplayed())
        {
            if(element.isEnabled())
            {
                Actions navigator = new Actions(driver);
                navigator.click(element).sendKeys(Keys.END).keyDown(Keys.SHIFT).sendKeys(Keys.HOME).keyUp(Keys.SHIFT)
                        .sendKeys(Keys.BACK_SPACE).sendKeys(text).perform();
                System.out.println("Text Value :-"+text+" , entered in text field:-"+elementName);
                return true;
            }
            else
            {
                System.out.println("Element is not enabled");
                System.out.println("Function call ended :clearAndSetText");
                return false;
            }
        }
        else
        {
            System.out.println("Element is not displayed");
            System.out.println("Function call ended :clearAndSetText");
            return false;
        }
    }

    public static boolean checkElementPresentReadyAndClick(WebElement element, WebDriver driver ,int timeout,String elementName) {

        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.visibilityOf(element));
        wait.until(ExpectedConditions.elementToBeClickable(element));
        if(element.isDisplayed())
        {
            element.click();
            System.out.println("Web Element :-"+elementName+" is clicked");
            return true;
        }
        else
        {
            System.out.println("Web Element :-"+elementName+" is not displayed");
            return false;
        }
    }

    public static void pause(WebDriver driver, int time)
    {
        driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
    }
}
