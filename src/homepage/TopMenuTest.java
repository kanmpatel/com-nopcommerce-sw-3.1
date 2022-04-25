package homepage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        //open the browser
        openBrowser(baseUrl);
    }
    public void selectMenu(String menu){
        //click on the ‘navigation’ link
        clickOnElement(By.linkText(menu));
    }

    @Test
    public void verifyPageNavigation(){
        //find the element of menu tab
        String navigationName = getTextFromElement(By.linkText("Electronics"));
        //call the selectmenu for click on navigation to other page
        selectMenu(navigationName);
        //find the text of navigation page
        String actualName = getTextFromElement(By.xpath("//h1[contains(text(),'Electronics')]"));
        //verify the navigation page text
        verifyTextMessage("Electronics",actualName);
    }


    @After
    public void clearBrowser(){
        //close the browser
        // closeBrowser();
    }
}
