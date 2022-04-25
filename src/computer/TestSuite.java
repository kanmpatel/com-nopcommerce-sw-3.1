package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSuite extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        //open the browser
        openBrowser(baseUrl);
    }
    @Test
    public void verifyUserNavigateTheDesktopPageAndSortbyPriceLowToHigh() throws InterruptedException {
        //1.1
        //mouse hover on computer navigation
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]/child::a"));
        //1.2
        //mouse hover on destop bar and click desktop link
        clickOnMouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]/descendant::a[contains(text(),'Desktops')]"));
        //short the actual price list
        List<WebElement> originalPriceList = driver.findElements(By.xpath("//div[@class='item-box']/child::div/descendant::span"));
        //create the list object
        List<String> shortPriceList = new ArrayList();
        //remove $ sign and add the shortpricelist
        //add all web element value to list object
        for (WebElement element : originalPriceList) {
            shortPriceList.add(String.valueOf(element.getText().replace("$", "")));
        }
        //remove , sign add the double type List
        List<Double> shortPriceList1 = new ArrayList();
        for (String element : shortPriceList) {
            shortPriceList1.add(Double.valueOf(element.replace(",", "")));
        }
        //sort the price in asecending order
        Collections.sort(shortPriceList1);
        //1.3
        //click on price low to high filter
        selectByValueFromDropDown(By.id("products-orderby"),"10");
        Thread.sleep(1000);
        //short the actual price list
        List<WebElement> actualPriceListElement = driver.findElements(By.xpath("//div[@class='item-box']/child::div/descendant::span"));
        //create the list object
        List<String> actualPriceList = new ArrayList();
        //remove $ sign and add the shortpricelist
        //add all web element value to list object
        for (WebElement element : actualPriceListElement) {
            actualPriceList.add(String.valueOf(element.getText().replace("$", "")));
        }
        //remove , sign add the double type List
        List<Double> actualPriceList1 = new ArrayList();
        for (String element : actualPriceList) {
            actualPriceList1.add(Double.valueOf(element.replace(",", "")));
        }
        //1.4
        //verify the arrange price in low to high
        Assert.assertEquals(shortPriceList1,actualPriceList1);
    }


    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        //2.1
        //mouse hover on computer navigation
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]/child::a"));
        //2.2
        //mouse hover on destop bar and click desktop link
        clickOnMouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[1]/descendant::a[contains(text(),'Desktops')]"));
        //2.3
        //click on name A to Z filter
        selectByValueFromDropDown(By.id("products-orderby"),"5");
        //2.4
        //click on add to cart
        //clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[3]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[2]/button[1]"));
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//div[@data-productid=\"1\"]/descendant::div[@class=\"details\"]/descendant::button[@class=\"button-2 product-box-add-to-cart-button\"]"));
        //2.5
        //Verify the Text "Build your own computer"
        String actualVerifiedText = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        verifyTextMessage("Build your own computer",actualVerifiedText);
        //2.6
        //select 2.2 GHz Intel Pentium Dual-Core E2200
        selectByValueFromDropDown(By.id("product_attribute_1"),"1");
        //2.7
        //select 8GB [+$60.00]"
        selectByValueFromDropDown(By.id("product_attribute_2"),"5");
        //2.8
        //Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));
        //2.9
        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));
        //2.10
        //Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander [+$5.00]"
        clickOnElement(By.id("product_attribute_5_11"));
        //2.11
        //verify the price "$1475.00
        String actualVerifyPrice = getTextFromElement(By.xpath("//span[@id=\"price-value-1\"]"));
        //verifyTextMessage("$1,475.00",actualVerifyPrice);
        //2.12
        //click on add the card
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.id("add-to-cart-button-1"));
        //2.13
        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String verifyAddToCartMessage = getTextFromElement(By.xpath("//p[contains(text(),'The product has been added to your ')]"));
        verifyTextMessage("The product has been added to your shopping cart",verifyAddToCartMessage);
        //close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class=\"close\"]"));
        //2.14
        //MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//li[@id=\"topcartlink\"]"));
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        //2.15
        //verify text shopping cart
        String actualVerifyShoppingCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        verifyTextMessage("Shopping cart",actualVerifyShoppingCart);
        //2.16
        //Change the Qty to "2" and Click on "Update shopping cart"
        Thread.sleep(2000);
        WebElement Quality= findElementsOnBrowser(By.xpath("//td[@class=\"quantity\"]/child::input"));
        Quality.clear();
        Quality.sendKeys("2");
        Thread.sleep(2000);
        //click on update shopping cart
        clickOnMouseHoverOnElement(By.id("updatecart"));
        //2.17
        //verify the price "$1475.00
        String actualVerifyTotalPrice = getTextFromElement(By.xpath("//td[@class=\"subtotal\"]/descendant::span"));
        //verifyTextMessage("$2,950.00",actualVerifyTotalPrice);
        //2.18
        //click the checkbox of terms & conditions
        clickOnElement(By.id("termsofservice"));
        //2.19
        //click on checkout
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//div[@class=\"checkout-buttons\"]/child::button"));
        //2.20
        // Verify the Text “Welcome, Please Sign In!”
        String verifyCheckOutMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        verifyTextMessage("Welcome, Please Sign In!",verifyCheckOutMessage);
        //2.21
        // Click on “CHECKOUT AS GUEST” Tab
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));
        //2.22
        // Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"),"john");
        sendTextToElement(By.id("BillingNewAddress_LastName"),"sena");
        sendTextToElement(By.id("BillingNewAddress_Email"),"sena111@gmail.com");
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"),"212");
        selectByValueFromDropDown(By.id("BillingNewAddress_StateProvinceId"),"0");
        sendTextToElement(By.id("BillingNewAddress_City"),"nehsan");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"nehsan road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"123 456");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"1234567890");
        //2.23
        // Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.id("shippingoption_1"));
        //2.25 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        //2.26 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        //2.27 Select “Master card” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"),"MasterCard");
        //2.28 Fill all the details
        sendTextToElement(By.id("CardholderName"),"john sena");
        sendTextToElement(By.id("CardNumber"),"5555555555554444");
        selectByValueFromDropDown(By.id("ExpireMonth"),"5");
        selectByValueFromDropDown(By.id("ExpireYear"),"2024");
        sendTextToElement(By.id("CardCode"),"123");
        //2.29 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));
        //2.30 Verify “Payment Method” is “Credit Card”
        String actualPayMethod = getTextFromElement(By.xpath("//li[@class='payment-method']/child::span[@class='value']"));
        verifyTextMessage("Credit Card",actualPayMethod);
        //2.32 Verify “Shipping Method” is “Next Day Air”
        String actualSippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']/child::span[@class='value']"));
        verifyTextMessage("Next Day Air",actualSippingMethod);
        //2.33 Verify $2,950.00
        String actualTotal = getTextFromElement(By.xpath("//span[@class='value-summary']/child::strong"));
        //verifyTextMessage("$2,950.00",actualTotal);
        //2.34 Click on “confirm”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        //2.35 Verify the Text “Thank You”
        String actualThankYouText = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        verifyTextMessage("Thank you",actualThankYouText);
        //	2.36 Verify the message “Your order has been successfully processed!”
        String actualOrderMessageText = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        verifyTextMessage("Your order has been successfully processed!",actualOrderMessageText);
        //	2.37 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        //2.37 Verify the text “Welcome to our store”
        String actualWelComeMessageText = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        verifyTextMessage("Welcome to our store",actualWelComeMessageText);



    }


    @After
    public void clearBrowser(){
        //close the browser
        // closeBrowser();
    }
}
