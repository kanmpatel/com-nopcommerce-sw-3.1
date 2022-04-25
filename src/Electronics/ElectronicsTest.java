package Electronics;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utilities.Utility;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    @Before
    public void setUp(){
        //open the browser
        openBrowser(baseUrl);
    }
    @Test
    public void verified(){
        //1.1 Mouse Hover on “Electronics”Tab
        mouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[2]/child::a"));
        //1.2 Mouse Hover on “Cell phones” and click
        clickOnMouseHoverOnElement(By.xpath("//ul[@class='top-menu notmobile']/child::li[2]/descendant::a[contains(text(),'Cell phones')]"));
        //1.3 Verify the text “Cell phones”
        String verifyCellPhonesText = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        verifyTextMessage("Cell phones",verifyCellPhonesText);

    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {
        //2.1 Mouse Hover on “Electronics” Tab
        //2.2 Mouse Hover on “Cell phones” and click
        //2.3 Verify the text “Cell phones”
        verified();
        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(@class,'viewmode-icon list')]"));
        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(3000);
        clickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));
        //2.6 Verify the text “Nokia Lumia 1020”
        String productText = getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        verifyTextMessage("Nokia Lumia 1020",productText);
        //2.7 Verify the price “$349.00”
        String priceProductText = getTextFromElement(By.xpath("//div[@class='product-price']/child::span[@id='price-value-20']"));
        verifyTextMessage("$349.00",priceProductText);
        //2.8 Change quantity to 2
        Thread.sleep(2000);
        WebElement Quality= findElementsOnBrowser(By.id("product_enteredQuantity_20"));
        Quality.clear();
        Quality.sendKeys("2");
        //2.9 Click on “ADD TO CART” tab
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.id("add-to-cart-button-20"));
        //2.10
        //Verify the Message "The product has been added to your shopping cart" on Top green Bar
        String verifyAddToCartMessage = getTextFromElement(By.xpath("//p[contains(text(),'The product has been added to your ')]"));
        verifyTextMessage("The product has been added to your shopping cart",verifyAddToCartMessage);
        //close the bar clicking on the cross button.
        clickOnElement(By.xpath("//span[@class=\"close\"]"));
        //2.11
        //MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverOnElement(By.xpath("//li[@id=\"topcartlink\"]"));
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        //2.12
        //verify text shopping cart
        String actualVerifyShoppingCart = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        verifyTextMessage("Shopping cart",actualVerifyShoppingCart);
        //2.13 Verify the quantity is 2
        WebElement VerifyQty = findElementsOnBrowser(By.xpath("//td[@class=\"quantity\"]/descendant::input"));
        String qty = VerifyQty.getAttribute("value");
        verifyTextMessage("2",qty);
        //2.14 Verify the Total $698.00
        String actualVerifyPrice = getTextFromElement(By.xpath("//td[@class=\"subtotal\"]/descendant::span"));
        verifyTextMessage("$698.00",actualVerifyPrice);
        //2.15
        //click the checkbox of terms & conditions
        clickOnElement(By.id("termsofservice"));
        //2.16
        //click on checkout
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//div[@class=\"checkout-buttons\"]/child::button"));
        //2.18 Click on “REGISTER” tab
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[contains(text(),'Register')]"));
        //2.19 Verify the text “Register”
        String registrationText = getTextFromElement(By.xpath("//h1[contains(text(),'Register')]"));
        verifyTextMessage("Register",registrationText);
        //2.20 Fill the mandatory fields
        //2.21 Click on “REGISTER” Button
        //2.22 Verify the message “Your registration completed”
        userSholdRegisterAccountSuccessfully();
        //2.23 Click on “CONTINUE” tab
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//div[@class='buttons']/child::a"));
        //2.24
        //verify text shopping cart
        String actualVerifyShoppingCartText = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        verifyTextMessage("Shopping cart",actualVerifyShoppingCartText);
        //2.25
        //click the checkbox of terms & conditions
        clickOnElement(By.id("termsofservice"));
        //2.26
        //click on checkout
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//div[@class=\"checkout-buttons\"]/child::button"));
        //2.27
        // Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"),"john");
        sendTextToElement(By.id("BillingNewAddress_LastName"),"sena");
        selectByValueFromDropDown(By.id("BillingNewAddress_CountryId"),"212");
        selectByValueFromDropDown(By.id("BillingNewAddress_StateProvinceId"),"0");
        sendTextToElement(By.id("BillingNewAddress_City"),"nehsan");
        sendTextToElement(By.id("BillingNewAddress_Address1"),"nehsan road");
        sendTextToElement(By.id("BillingNewAddress_ZipPostalCode"),"123 456");
        sendTextToElement(By.id("BillingNewAddress_PhoneNumber"),"1234567890");
        //2.28
        // Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='Billing.save()']"));
        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));
        //2.30 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.id("paymentmethod_1"));
        //2.31 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='PaymentMethod.save()']"));
        //2.32 Select “visa” From Select credit card dropdown
        selectByValueFromDropDown(By.id("CreditCardType"),"visa");
        //2.33 Fill all the details
        sendTextToElement(By.id("CardholderName"),"john sena");
        sendTextToElement(By.id("CardNumber"),"4444333322221111");
        selectByValueFromDropDown(By.id("ExpireMonth"),"5");
        selectByValueFromDropDown(By.id("ExpireYear"),"2024");
        sendTextToElement(By.id("CardCode"),"123");
        //2.34 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='PaymentInfo.save()']"));
        //2.35 Verify “Payment Method” is “Credit Card”
        String actualPayMethod = getTextFromElement(By.xpath("//li[@class='payment-method']/child::span[@class='value']"));
        verifyTextMessage("Credit Card",actualPayMethod);
        //2.36 Verify “Shipping Method” is “2nd Day Air”
        String actualSippingMethod = getTextFromElement(By.xpath("//li[@class='shipping-method']/child::span[@class='value']"));
        verifyTextMessage("2nd Day Air",actualSippingMethod);
        //2.37 Verify $698.00
        String actualTotal = getTextFromElement(By.xpath("//span[@class='value-summary']/child::strong"));
        verifyTextMessage("$698.00",actualTotal);
        //2.38 Click on “confirm”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='ConfirmOrder.save()']"));
        //2.39 Verify the Text “Thank You”
        String actualThankYouText = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        verifyTextMessage("Thank you",actualThankYouText);
        //	2.40 Verify the message “Your order has been successfully processed!”
        String actualOrderMessageText = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        verifyTextMessage("Your order has been successfully processed!",actualOrderMessageText);
        //	2.41 Click on “CONTINUE”
        Thread.sleep(3000);
        clickOnMouseHoverOnElement(By.xpath("//button[@onclick='setLocation(\"/\")']"));
        //2.42 Verify the text “Welcome to our store”
        String actualWelComeMessageText = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        verifyTextMessage("Welcome to our store",actualWelComeMessageText);
        //2.43 Click on “Logout” link
        clickOnElement(By.linkText("Log out"));
        //2.44 Verify the URL is “https://demo.nopcommerce.com/”
        String actualCurrentUrl = driver.getCurrentUrl();
        verifyTextMessage("https://demo.nopcommerce.com/",actualCurrentUrl);





    }

    public void userSholdRegisterAccountSuccessfully(){
        //Enter First name with name
        sendTextToElement(By.name("FirstName"),"Megha");
        //Enter last name with css selector
        sendTextToElement(By.cssSelector("input#LastName"),"Jadvani");
        //Select Date of birth using select class
        selectByValueFromDropDown(By.name("DateOfBirthDay"),"26");
        //Select month of birth using select class
        selectByValueFromDropDown(By.name("DateOfBirthMonth"),"7");
        //Select year of birth using select class
        selectByValueFromDropDown(By.name("DateOfBirthYear"),"1998");
        // Enter Email address using xpath
        sendTextToElement(By.xpath("//input[@id='Email']"),"dj3544@gmail.com");
        //Enter Password using id
        sendTextToElement(By.id("Password"),"megha1234");
        //Enter confirm Password using id
        sendTextToElement(By.id("ConfirmPassword"),"megha1234");
        //Click on REGISTER button
        clickOnElement(By.id("register-button"));
        String expectedRegister = "Your registration completed";
        String actualregister = getTextFromElement(By.cssSelector("div.result"));

        //variable actual and expected message
        verifyTextMessage(expectedRegister,actualregister);
    }


    @After
    public void clearBrowser(){
        //close the browser
        // closeBrowser();
    }
}
