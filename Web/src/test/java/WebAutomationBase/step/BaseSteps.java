package WebAutomationBase.step;

import WebAutomationBase.base.BaseTest;
import WebAutomationBase.helper.ElementHelper;
import WebAutomationBase.helper.StoreHelper;
import WebAutomationBase.model.ElementInfo;
import WebAutomationBase.step.helper.HelperUtils;
import com.thoughtworks.gauge.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.KeyInput;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.Log4jLoggerAdapter;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.*;

import static WebAutomationBase.step.constant.Constant.*;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;

public class BaseSteps<text1> extends BaseTest {

  public static int DEFAULT_MAX_ITERATION_COUNT = 150;
  public static int DEFAULT_MILLISECOND_WAIT_AMOUNT = 100;

  public static Log4jLoggerAdapter logger = (Log4jLoggerAdapter) LoggerFactory
          .getLogger(BaseSteps.class);

  private static String SAVED_ATTRIBUTE;

  private Actions actions = new Actions(driver);
  private String compareText;
  private static final DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


  private ApiTestingPost apiTestingpost = new ApiTestingPost();

  public BaseSteps() {

    PropertyConfigurator
            .configure(BaseSteps.class.getClassLoader().getResource("log4j.properties"));
  }

  public WebElement findElement(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
    WebElement webElement = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(infoParam));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})", webElement);
    return webElement;
  }

  private List<WebElement> findElements(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By infoParam = ElementHelper.getElementInfoToBy(elementInfo);
    return driver.findElements(infoParam);
  }

  private void clickElement(WebElement element) {
    element.click();
  }

  private void clickElementBy(String key) {
    findElement(key).click();
  }

  private void hoverElement(WebElement element) {
    actions.moveToElement(element).build().perform();
  }

  private void hoverElementBy(String key) {
    WebElement webElement = findElement(key);
    actions.moveToElement(webElement).build().perform();
  }

  private void sendKeyESC(String key) {
    findElement(key).sendKeys(Keys.ESCAPE);

  }

  private boolean isDisplayed(WebElement element) {
    return element.isDisplayed();
  }

  private boolean isDisplayedBy(By by) {
    return driver.findElement(by).isDisplayed();
  }

  private String getPageSource() {
    return driver.switchTo().alert().getText();
  }

  public static String getSavedAttribute() {
    return SAVED_ATTRIBUTE;
  }

  public String randomString(int stringLength) {

    Random random = new Random();
    char[] chars = "ABCDEFGHIJKLMNOPQRSTUWVXYZabcdefghijklmnopqrstuwvxyz0123456789".toCharArray();
    String stringRandom = "";
    for (int i = 0; i < stringLength; i++) {

      stringRandom = stringRandom + chars[random.nextInt(chars.length)];
    }

    return stringRandom;
  }

  @Step("Genarete random number for Withdrawal <key> and <keys>, and saved the number <saveKey>. And write the saved key to the <keyy> element")
  public void picksave(String key, String keys, String saveKey, String keyy) throws Exception {
    int randomNumber = randomIntGenerateNumber(key, keys);
    //webElement.sendKeys(String.valueOf(randomNumber));
    StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(randomNumber));
    logger.info("saveKey for genareted random number: " + saveKey);
    StoreHelper.INSTANCE.getValue(saveKey);
    WebElement element = findElement(keyy);
    element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
    //webElement.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
  }

  @Step("Generate Monnet WithDrawal Random Number <key> and <keys>,and saved the number <randomNumber>. And write the saved key to the <transactionAmount> element")
  public void pickSaveRandom(String key, String keys, String saveKey, String keyy) throws Exception {
    int randomNumber = randomIntGenerateNumberFordollar(key, keys);
    //webElement.sendKeys(String.valueOf(randomNumber));
    StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(randomNumber));
    logger.info("saveKey for genareted random number: " + saveKey);
    StoreHelper.INSTANCE.getValue(saveKey);
    WebElement element = findElement(keyy);
    element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
  }


  @Step("Genarete random number for Deposit <keys> and <keys>, and saved the number <saveKey>. And write the saved key to the <keyy> element")
  public void pickSaveRandomValue(String key, String keys, String saveKey, String keyy) throws Exception {
    int randomNumber = randomIntGenerateNumberForCLP(key, keys);
    //webElement.sendKeys(String.valueOf(randomNumber));
    StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(randomNumber));
    logger.info("saveKey for genareted random number: " + saveKey);
    StoreHelper.INSTANCE.getValue(saveKey);
    WebElement element = findElement(keyy);
    element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
  }

  @Step("Save Low amount <key> and Write Amount <key>")
  public void getAmountLowandWrite(String lowAmount, String writeAmount) {
    WebElement low = findElement(lowAmount);
    String minValue = low.getText().replace("Min. amount: ", "");
    logger.info("String Min Value: " + minValue);
    String minValuewithoutCLP = minValue.replace("CLP", "");
    logger.info("String Min Value Without CLP: " + minValuewithoutCLP);
    String minValueWithoutComma = minValuewithoutCLP.replace(",", "");
    logger.info("String Min Value: " + minValueWithoutComma);
    int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
    logger.info("String Current Value is: " + minValueWithoutDecimal);

    StoreHelper.INSTANCE.saveValue(lowAmount, String.valueOf(minValueWithoutDecimal));
    logger.info("saveKey is: " + lowAmount);
    waitBySeconds(2);
    StoreHelper.INSTANCE.getValue(lowAmount);
    WebElement element = findElement(writeAmount);
    element.sendKeys(StoreHelper.INSTANCE.getValue(lowAmount));

  }


  @Step("Save Low dollar amount <key> and Write Amount <key>")
  public void getdollarAmountLowandWrite(String LowAmount, String writeAmount) {
    WebElement Low = findElement(LowAmount);
    String minValue = Low.getText().replace("Min. amount: ", "");
    logger.info("String Min Value: " + minValue);
    String minValuewithout$ = minValue.replace("$", "");
    logger.info("String Min Value Without dollar: " + minValuewithout$);
    String minValueWithoutComma = minValuewithout$.replace(",", "");
    logger.info("String Min Value: " + minValueWithoutComma);
    int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
    logger.info("String Current Value is: " + minValueWithoutDecimal);

    StoreHelper.INSTANCE.saveValue(LowAmount, String.valueOf(minValueWithoutDecimal));
    logger.info("saveKey is: " + LowAmount);
    waitBySeconds(2);
    StoreHelper.INSTANCE.getValue(LowAmount);
    WebElement element = findElement(writeAmount);
    element.sendKeys(StoreHelper.INSTANCE.getValue(LowAmount));

  }


  @Step({"<key> li elementi bul, temizle ve <text> de??erini yaz",
          "Find element by <key> clear and send keys <text>"})
  public void sendKeysByKey(String key, String text) {
    //WebElement webElement = findElement(key);
    WebElement webElement = getElementWithKeyIfExists(key);
    webElement.clear();
    webElement.sendKeys(text);
    logger.info("the text is written: " + "'" + text + "'");
  }


  public String randomNumber(int stringLength) {

    Random random = new Random();
    char[] chars = "0123456789".toCharArray();
    String stringRandom = "";
    for (int i = 0; i < stringLength; i++) {

      stringRandom = stringRandom + chars[random.nextInt(chars.length)];
    }

    return stringRandom;
  }

  public WebElement findElementWithKey(String key) {
    return findElement(key);
  }

  public String getElementText(String key) {
    return findElement(key).getText();
  }

  public String getElementAttributeValue(String key, String attribute) {
    return findElement(key).getAttribute(attribute);
  }

  @Step("Print page source")
  public void printPageSource() {
    System.out.println(getPageSource());
  }

  public void javaScriptClicker(WebDriver driver, WebElement element) {

    JavascriptExecutor jse = ((JavascriptExecutor) driver);
    jse.executeScript("var evt = document.createEvent('MouseEvents');"
            + "evt.initMouseEvent('click',true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0,null);"
            + "arguments[0].dispatchEvent(evt);", element);
  }

  public void javascriptclicker(WebElement element) {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);
  }

  @Step("Select Customer type")
  public void jsclicker() {
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("document.getElementsByClassName('ant-select-item-option-content').click();");
  }

  @Step({"Wait <value> seconds",
          "<int> saniye bekle"})
  public void waitBySeconds(int seconds) {
    try {
      logger.info(seconds + " saniye bekleniyor.");
      Thread.sleep(seconds * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Step({"Wait <value> milliseconds",
          "<long> milisaniye bekle"})
  public void waitByMilliSeconds(long milliseconds) {
    try {
      logger.info(milliseconds + " milisaniye bekleniyor.");
      Thread.sleep(milliseconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Step("Debug Step")
  public void debug() throws InterruptedException {
    Thread.sleep(1000);
  }

  @Step({"Wait for element then click <key>",
          "Elementi bekle ve sonra t??kla <key>"})
  public void checkElementExistsThenClick(String key) {
    getElementWithKeyIfExists(key);
    clickElement(key);
    logger.info("Clicked to the " + key);
  }

  @Step({"Click to element <key>",
          "Elementine t??kla <key>"})
  public void clickElement(String key) {
    if (!key.isEmpty()) {
      hoverElement(findElement(key));
      clickElement(findElement(key));
      logger.info("Clicked to the element " + key);
    }
  }

  @Step({"Get text by <key> and replace the symbols than save the text <saveKeyy>"})
  public void replaceSomeValue(String key, String saveKeyy) {
    WebElement element = getElementWithKeyIfExists(key);
    String replacedFrom$ = element.getText().replace("$", "");
    logger.info("Replaced element: " + replacedFrom$);
    String replacedElement = replacedFrom$.replace(".", "");
    logger.info("Replaced element: " + replacedElement);
    StoreHelper.INSTANCE.saveValue(saveKeyy, replacedElement);
    logger.info("saveKey for genareted random number: " + saveKeyy);
  }


  @Step({"Click to element <key> with focus",
          "<key> elementine focus ile t??kla"})
  public void clickElementWithFocus(String key) {
    actions.moveToElement(findElement(key));
    actions.click();
    actions.build().perform();
    logger.info(key + " elementine focus ile t??kland??.");
  }

  @Step({"Check if element <key> exists",
          "Wait for element to load with key <key>",
          "Element var m?? kontrol et <key>",
          "Elementin y??klenmesini bekle <key>"})
  public WebElement getElementWithKeyIfExists(String key) {
    WebElement webElement;
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      try {
        webElement = findElementWithKey(key);
        logger.info(key + " elementi bulundu.");
        return webElement;
      } catch (WebDriverException e) {
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element: '" + key + "' doesn't exist.");
    return null;
  }

  @Step({"Go to <url> address",
          "<url> adresine git"})
  public void goToUrl(String url) {
    driver.get(url);
    logger.info(url + " adresine gidiliyor.");
  }

  @Step({"Wait for element to load with css <css>",
          "Elementin y??klenmesini bekle css <css>"})
  public void waitElementLoadWithCss(String css) {
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (driver.findElements(By.cssSelector(css)).size() > 0) {
        logger.info(css + " elementi bulundu.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element: '" + css + "' doesn't exist.");
  }

  @Step({"Wait for element to load with xpath <xpath>",
          "Elementinin y??klenmesini bekle xpath <xpath>"})
  public void waitElementLoadWithXpath(String xpath) {
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (driver.findElements(By.xpath(xpath)).size() > 0) {
        logger.info(xpath + " elementi bulundu.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element: '" + xpath + "' doesn't exist.");
  }

  @Step({"Check if element <key> exists else print message <message>",
          "Element <key> var m?? kontrol et yoksa hata mesaj?? ver <message>"})
  public void getElementWithKeyIfExistsWithMessage(String key, String message) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By by = ElementHelper.getElementInfoToBy(elementInfo);

    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (driver.findElements(by).size() > 0) {
        logger.info(key + " elementi bulundu.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail(message);
  }

  @Step("<key> elementin ??st??nde bekle")
  public void hover(String key) {
    hoverElement(findElement(key));
  }

  @Step({"Check if element <key> not exists",
          "Element yok mu kontrol et <key>"})
  public void checkElementNotExists(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    By by = ElementHelper.getElementInfoToBy(elementInfo);

    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (driver.findElements(by).size() == 0) {
        logger.info(key + " elementinin olmad?????? kontrol edildi.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element '" + key + "' still exist.");
  }

  @Step({"Upload file in project <path> to element <key>",
          "Proje i??indeki <path> dosyay?? <key> elemente upload et"})
  public void uploadFile(String path, String key) {
    String pathString = System.getProperty("user.dir") + "/";
    pathString = pathString + path;
    findElement(key).sendKeys(pathString);
    logger.info(path + " dosyas?? " + key + " elementine y??klendi.");
  }

  @Step({"Write values <text> to element <key>",
          "<text> textini <key> elemente yaz"})
  public void sendKeys(String text, String key) {
    if (!key.equals("")) {
      findElement(key).click();
      findElement(key).sendKeys(text);
      logger.info(key + " elementine " + text + " texti yaz??ld??.");
    }
  }
 /* @Step({"Write values dropdown <text> to element <key>",
          "<text> textini <key> elemente yaz"})
  public void sendKeysDropdown(String text, String key) {
    Select select1= new Select(driver.findElement(By.cssSelector("div[class='a-dropdownbox a-form-datefield-day   yui3-widget a-dropdownbox-content']")));
    select1.selectByValue(text);
   /* if (!key.equals("")) {
      findElement(key).click();
      findElement(key).sendKeys(text,Keys.ENTER);
      logger.info(key + " elementine " + text + " texti yaz??ld??.");
    }
    */



 /* @Step({"Write value <text> to element <key>",
          "<text> textini <key> elemente yaz"})
  public void sendKeys(String text, String key) {
    findElement(key).sendKeys(text);
    logger.info("'" +text+ "' text is written to the '" +key + "' element.");
    */


  @Step({"Write random Alpha value to element <key> starting with <text>"})
  public void writeRandomAlphaValueToElement(String key, String startingText) {
    String value = RandomStringUtils.randomAlphabetic(5);
    findElement(key).sendKeys(startingText + value);
    logger.info("The text was written to the field as: " + startingText + value);
  }

  @Step({"Click with javascript to css <css>",
          "Javascript ile css t??kla <css>"})
  public void javascriptClickerWithCss(String css) {
    assertTrue("Element bulunamad??", isDisplayedBy(By.cssSelector(css)));
    javaScriptClicker(driver, driver.findElement(By.cssSelector(css)));
    logger.info("Javascript ile " + css + " t??kland??.");
  }

  @Step({"Click with javascript to xpath <xpath>",
          "Javascript ile xpath t??kla <xpath>"})
  public void javascriptClickerWithXpath(String xpath) {
    assertTrue("Element bulunamad??", isDisplayedBy(By.xpath(xpath)));
    javaScriptClicker(driver, driver.findElement(By.xpath(xpath)));
    logger.info("Javascript ile " + xpath + " t??kland??.");
  }

  @Step({"Check if current URL contains the value <expectedURL>",
          "??uanki URL <url> de??erini i??eriyor mu kontrol et"})
  public void checkURLContainsRepeat(String expectedURL) {
    int loopCount = 0;
    String actualURL = "";
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      actualURL = driver.getCurrentUrl();

      if (actualURL != null && actualURL.contains(expectedURL)) {
        logger.info("??uanki URL" + expectedURL + " de??erini i??eriyor.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail(
            "Actual URL doesn't match the expected." + "Expected: " + expectedURL + ", Actual: "
                    + actualURL);
  }

  @Step({"Send TAB key to element <key>",
          "Elemente TAB keyi yolla <key>"})
  public void sendKeyToElementTAB(String key) {
    findElement(key).sendKeys(Keys.TAB);
    logger.info(key + " elementine TAB keyi yolland??.");
  }

  @Step({"Send BACKSPACE key to element <key>",
          "Elemente BACKSPACE keyi yolla <key>"})
  public void sendKeyToElementBACKSPACE(String key) {
    findElement(key).sendKeys(Keys.BACK_SPACE);
    logger.info(key + " elementine BACKSPACE keyi yolland??.");
  }

  @Step({"Send ESCAPE key to element <key>",
          "Elemente ESCAPE keyi yolla <key>"})
  public void sendKeyToElementESCAPE(String key) {
    findElement(key).sendKeys(Keys.ESCAPE);
    logger.info(key + " elementine ESCAPE keyi yolland??.");
  }

  @Step({"Check if element <key> has attribute <attribute>",
          "<key> elementi <attribute> niteli??ine sahip mi"})
  public void checkElementAttributeExists(String key, String attribute) {
    WebElement element = findElement(key);
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (element.getAttribute(attribute) != null) {
        logger.info(key + " elementi " + attribute + " niteli??ine sahip.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element DOESN't have the attribute: '" + attribute + "'");
  }

  @Step({"Check if element <key> not have attribute <attribute>",
          "<key> elementi <attribute> niteli??ine sahip de??il mi"})
  public void checkElementAttributeNotExists(String key, String attribute) {
    WebElement element = findElement(key);

    int loopCount = 0;

    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      if (element.getAttribute(attribute) == null) {
        logger.info(key + " elementi " + attribute + " niteli??ine sahip olmad?????? kontrol edildi.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element STILL have the attribute: '" + attribute + "'");
  }

  @Step({"Check if <key> element's attribute <attribute> equals to the value <expectedValue>",
          "<key> elementinin <attribute> niteli??i <value> de??erine sahip mi"})
  public void checkElementAttributeEquals(String key, String attribute, String expectedValue) {
    WebElement element = findElement(key);

    String actualValue;
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      actualValue = element.getAttribute(attribute).trim();
      if (actualValue.equals(expectedValue)) {
        logger.info(
                key + " elementinin " + attribute + " niteli??i " + expectedValue + " de??erine sahip.");
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element's attribute value doesn't match expected value");
  }

  @Step({"Check if <key> element's attribute <attribute> contains the value <expectedValue>",
          "<key> elementinin <attribute> niteli??i <value> de??erini i??eriyor mu"})
  public void checkElementAttributeContains(String key, String attribute, String expectedValue) {
    WebElement element = findElement(key);

    String actualValue;
    int loopCount = 0;
    while (loopCount < DEFAULT_MAX_ITERATION_COUNT) {
      actualValue = element.getAttribute(attribute).trim();
      if (actualValue.contains(expectedValue)) {
        return;
      }
      loopCount++;
      waitByMilliSeconds(DEFAULT_MILLISECOND_WAIT_AMOUNT);
    }
    Assert.fail("Element's attribute value doesn't contain expected value");
  }

  @Step({"Write <value> to <attributeName> of element <key>",
          "<value> de??erini <attribute> niteli??ine <key> elementi i??in yaz"})
  public void setElementAttribute(String value, String attributeName, String key) {
    String attributeValue = findElement(key).getAttribute(attributeName);
    findElement(key).sendKeys(attributeValue, value);
  }

  @Step({"Write <value> to <attributeName> of element <key> with Js",
          "<value> de??erini <attribute> niteli??ine <key> elementi i??in JS ile yaz"})
  public void setElementAttributeWithJs(String value, String attributeName, String key) {
    WebElement webElement = findElement(key);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].setAttribute('" + attributeName + "', '" + value + "')",
            webElement);
  }

  @Step({"Clear text of element <key>",
          "<key> elementinin text alan??n?? temizle"})
  public void clearInputArea(String key) {
    findElement(key).clear();
  }

  @Step({"Clear text of element <key> with BACKSPACE",
          "<key> elementinin text alan??n?? BACKSPACE ile temizle"})
  public void clearInputAreaWithBackspace(String key) {
    WebElement element = findElement(key);
    element.clear();
    element.sendKeys("a");
    actions.sendKeys(Keys.BACK_SPACE).build().perform();
  }

  @Step({"Save attribute <attribute> value of element <key>",
          "<attribute> niteli??ini sakla <key> elementi i??in"})
  public void saveAttributeValueOfElement(String attribute, String key) {
    SAVED_ATTRIBUTE = findElement(key).getAttribute(attribute);
    System.out.println("Saved attribute value is: " + SAVED_ATTRIBUTE);
  }


  @Step({"Write saved attribute value to element <key>",
          "Kaydedilmi?? niteli??i <key> elementine yaz"})
  public void writeSavedAttributeToElement(String key) {
    findElement(key).sendKeys(SAVED_ATTRIBUTE);
  }

  @Step({"Check if element <key> contains text <expectedText>",
          "<key> elementi <text> de??erini i??eriyor mu kontrol et"})
  public void checkElementContainsText(String key, String expectedText) {
    Boolean containsText = findElement(key).getText().contains(expectedText);
    assertTrue("Expected text is not contained", containsText);
    logger.info(key + " elementi" + expectedText + "de??erini i??eriyor.");
  }

  @Step({"Write random value to element <key>",
          "<key> elementine random de??er yaz"})
  public void writeRandomValueToElement(String key) {
    findElement(key).sendKeys(randomString(15));
  }

  @Step({"Write random value to element <key> starting with <text>",
          "<key> elementine <text> de??eri ile ba??layan random de??er yaz"})
  public void writeRandomValueToElement(String key, String startingText) {
    String randomText = startingText + randomString(15);
    findElement(key).sendKeys(randomText);
  }

  @Step({"Write random Int value to element <key>",
          "<key> elementine random de??er yaz"})
  public void writeRandomIntValueToElement(String key) {
    findElement(key).sendKeys(randomNumber(9));
  }

  @Step({"Print element text by css <css>",
          "Elementin text de??erini yazd??r css <css>"})
  public void printElementText(String css) {
    System.out.println(driver.findElement(By.cssSelector(css)).getText());
  }

  @Step({"Write value <string> to element <key> with focus",
          "<string> de??erini <key> elementine focus ile yaz"})
  public void sendKeysWithFocus(String text, String key) {
    actions.moveToElement(findElement(key));
    actions.click();
    actions.sendKeys(text);
    actions.build().perform();
    logger.info(key + " elementine " + text + " de??eri focus ile yaz??ld??.");
  }

  @Step({"Refresh page",
          "Sayfay?? yenile"})
  public void refreshPage() {
    driver.navigate().refresh();
  }


  @Step({"Change page zoom to <value>%",
          "Sayfan??n zoom de??erini de??i??tir <value>%"})
  public void chromeZoomOut(String value) {
    JavascriptExecutor jsExec = (JavascriptExecutor) driver;
    jsExec.executeScript("document.body.style.zoom = '" + value + "%'");
  }

  @Step({"Open new tab",
          "Yeni sekme a??"})
  public void chromeOpenNewTab() {
    ((JavascriptExecutor) driver).executeScript("window.open()");
  }

  @Step({"Focus on tab number <number>",
          "<number> numaral?? sekmeye odaklan"})//Starting from 1
  public void chromeFocusTabWithNumber(int number) {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(number - 1));
  }

  @Step({"Focus on last tab",
          "Son sekmeye odaklan"})
  public void chromeFocusLastTab() {
    ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
    driver.switchTo().window(tabs.get(tabs.size() - 1));
  }

  @Step({"Focus on frame with <key>",
          "Frame'e odaklan <key>"})
  public void chromeFocusFrameWithNumber(String key) {
    WebElement webElement = findElement(key);
    driver.switchTo().frame(webElement);
  }

  @Step({"Accept Chrome alert popup",
          "Chrome uyar?? popup'??n?? kabul et"})
  public void acceptChromeAlertPopup() {
    driver.switchTo().alert().accept();
  }


  //----------------------SONRADAN YAZILANLAR-----------------------------------\\


  // Key de??eri al??nan listeden rasgele element se??me amac??yla yaz??lm????t??r. @Mehmet??nan
  public void randomPick(String key) {
    List<WebElement> elements = findElements(key);
    Random random = new Random();
    int index = random.nextInt(elements.size());
    logger.info("index number" + index);
    elements.get(index).click();
  }

  //Javascript driver??n ba??lat??lmas??
  private JavascriptExecutor getJSExecutor() {
    return (JavascriptExecutor) driver;
  }

  //Javascript scriptlerinin ??al????mas?? i??in gerekli fonksiyon
  private Object executeJS(String script, boolean wait) {
    return wait ? getJSExecutor().executeScript(script, "") : getJSExecutor().executeAsyncScript(script, "");
  }

  //Belirli bir locasyona sayfan??n kayd??r??lmas??
  private void scrollTo(int x, int y) {
    String script = String.format("window.scrollTo(%d, %d);", x, y);
    executeJS(script, true);
  }

  //Belirli bir elementin oldu??u locasyona websayfas??n??n kayd??r??lmas??
  public WebElement scrollToElementToBeVisible(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    WebElement webElement = driver.findElement(ElementHelper.getElementInfoToBy(elementInfo));
    if (webElement != null) {
      scrollTo(webElement.getLocation().getX(), webElement.getLocation().getY() - 100);
    }
    return webElement;
  }


  @Step({"<key> alan??na kayd??r"})
  public void scrollTooElement(String key) {
    scrollToElementToBeVisible(key);
    logger.info(key + " elementinin oldu??u alana kayd??r??ld??");

  }

  @Step("Enter phone number <key> and <key>")
  public void jsValue(String key, String value) {
    new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOf(findElement(key))).sendKeys("123456789");


  }


  @Step({"<key> alan??na js ile kayd??r"})
  public void scrollToElementWithJs(String key) {
    ElementInfo elementInfo = StoreHelper.INSTANCE.findElementInfoByKey(key);
    WebElement element = driver.findElement(ElementHelper.getElementInfoToBy(elementInfo));
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
  }


  @Step({"<length> uzunlugunda random bir kelime ??ret ve <saveKey> olarak sakla"})
  public void createRandomString(int length, String saveKey) {
    StoreHelper.INSTANCE.saveValue(saveKey, randomString(length));

  }

  @Step({"<key> li elementi bul ve de??erini <saveKey> saklanan degeri yazdir",
          "Find element by <key> and compare saved key <saveKey>"})
  public void equalsSendTextByKey(String key, String saveKey) throws InterruptedException {
    WebElement element;
    int waitVar = 0;
    element = findElementWithKey(key);
    while (true) {
      if (element.isDisplayed()) {
        logger.info("WebElement is found at: " + waitVar + " second.");
        element.clear();
        StoreHelper.INSTANCE.getValue(saveKey);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));

        break;
      } else {
        waitVar = waitVar + 1;
        Thread.sleep(1000);
        if (waitVar == 20) {
          throw new NullPointerException(String.format("by = %s Web element list not found"));
        } else {
        }
      }
    }
  }

  //Zaman bilgisinin al??nmas??
  private Long getTimestamp() {
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
    return (timestamp.getTime());
  }

  @Step({"<key> li elementi bul, temizle ve rasgele  email de??erini yaz",
          "Find element by <key> clear and send keys  random email"})
  public void RandomMail(String key) {
    Long timestamp = getTimestamp();
    WebElement webElement = findElementWithKey(key);
    webElement.clear();
    webElement.sendKeys("test" + timestamp + "@testinium.com");

  }

  @Step("<key> olarak <text> se??ersem")
  public void choosingTextFromList(String key, String text) throws InterruptedException {
    List<WebElement> comboBoxElement = findElements(key);
    for (int i = 0; i < comboBoxElement.size(); i++) {
      String texts = comboBoxElement.get(i).getText();
      String textim = text;
      if (texts.contains(textim)) {
        comboBoxElement.get(i).click();
        break;
      }
    }
    logger.info(key + " combobox??ndan " + text + " de??eri se??ildi");


  }

  @Step("Deposit payments get from list then click <key>")
  public void comboBoxRandom(String key) throws InterruptedException {


    List<WebElement> comboBoxElement = findElements(key);
    int randomIndex = new Random().nextInt(comboBoxElement.size());
    Thread.sleep(2000);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", comboBoxElement.get(randomIndex));
    logger.info(key + " combobox??ndan herhangi bir de??er se??ildi");

  }

  @Step("Click Deposit Monnet Bank <key>")
  public void openPaymentCard(String key) {
    List<WebElement> options = getlist(key);

    options.get(0).click();
    logger.info("Clicked to the element " + key);
  }

  @Step("Deposit payment get from list then click <key>")
  public List<WebElement> getlist(String key) {
    waitByMilliSeconds(3000);
    List<WebElement> options = findElements(key);
    return options;
//    options.get(0).click();

//    for(int i =0 ; i < options.size(); i++){
//      System.out.println(options.get(i).getText());
//      options.get(i).findElement(By.xpath("//*[@id=\"yui_3_5_0_2_1666018840401_214\"]"));
//      if(options.get(i).equals(3)){
//        options.get(i).findElement(By.xpath("//*[@id=\"yui_3_5_0_2_1666018840401_214\"]")).click();
//        System.out.println("condition true");
//      }
//      else{
//        System.out.println("condition false");
//      }
//    }
  }

  @Step("Get Amount from list <key>")
  public List<WebElement> getAmountFromlist(String key) {
    waitByMilliSeconds(3000);
    List<WebElement> amount = findElements(key);
    List<String> lowHighAmount = new ArrayList<String>();

    for (int i = 0; i < amount.size(); i++) {
      logger.info("idex ==> " + "" + i);

      logger.info(amount.get(i).getText());
      lowHighAmount.add(amount.get(i).getText());
    }
    logger.info("lowHighAmount list==>" + " " + lowHighAmount.get(0));
    logger.info("size==>" + "" + amount.size() + "");
//    logger.info("low value: "+lowHighAmount.get(0)+" High value: "+" "+ lowHighAmount.get(1));

    return amount;
  }


  @Step("Deposit paymen23t get from list then click <key>")
  public void getlist1(String key) {
    List<WebElement> allProduct = driver.findElements(By.className(key));

    for (WebElement option : allProduct) {
      if (option.getText().equalsIgnoreCase("Accent In")) {
        option.click();
      } else {
        System.out.println("List not ound");
      }
    }

  }


  @Step("Url bilgisi <url> ve <path> bilgilerini gir ve  get  iste??i yap")
  public void SendGetRequest(String path, String url) {
    RestAssured.baseURI = url;
    Response response = given().log().all().
            get(path).prettyPeek().then().statusCode(200).extract().response();
  }


  //Sipari?? numaras??n?? substring methoduyla elde etmek i??in yaz??lm????t??r.
  public String choosePatternNo(String key) {
    WebElement webElement = findElement(key);
    String demandNo = webElement.getText();
    String quoteNumber = demandNo.substring(demandNo.lastIndexOf(" ") + 1);
    return quoteNumber;
  }


  @Step("<key> element click")
  public void clickToElementWithJavaScript(String key) {
    WebElement element = findElement(key);
    javascriptclicker(element);
    logger.info(key + " elementine javascript ile t??kland??");
  }


  // Belirli bir key de??erinin oldu??u locasyona websayfas??n??n kayd??r??lmas??
  public void scrollToElementToBeVisiblest(WebElement webElement) {
    if (webElement != null) {
      scrollTo(webElement.getLocation().getX(), webElement.getLocation().getY() - 100);
    }
  }

  @Step({"<key> alan??na kayd??r",
          "scroll to the element <key> be visible"})
  public void scrollToElement(String key) {
    scrollToElementToBeVisible(key);
  }


  //??ift t??klama fonksiyonu
  public void doubleclick(WebElement elementLocator) {
    Actions actions = new Actions(driver);
    actions.doubleClick(elementLocator).perform();
  }

  @Step("Select Customer Type <key>")
  public void hoverClick(String key) {
    Actions actions = new Actions(driver);
    WebElement menuOption = findElement(key);
    actions.moveToElement(menuOption).perform();
    menuOption.isEnabled();
    menuOption.click();
    logger.info("Customer type is Selected");
  }

  @Step("<key> alan??n?? javascript ile temizle")
  public void clearWithJS(String key) {
    WebElement element = findElement(key);
    ((JavascriptExecutor) driver).executeScript("arguments[0].value ='';", element);
  }


  @Step("<key> elementleri aras??ndan <text> kay??tl?? de??i??kene t??kla")
  public void clickParticularElement(String key, String text) {
    List<WebElement> anchors = findElements(key);
    Iterator<WebElement> i = anchors.iterator();
    while (i.hasNext()) {
      WebElement anchor = i.next();
      if (anchor.getText().contains(StoreHelper.INSTANCE.getValue(text))) {
        scrollToElementToBeVisiblest(anchor);
        doubleclick(anchor);
        break;
      }
    }
  }

  @Step("<key> menu listesinden rasgele se??")
  public void chooseRandomElementFromList(String key) {
    randomPick(key);
  }


  @Step("<key> olarak <index> indexi se??ersem")
  public void choosingIndexFromDemandNo(String key, String index) {

    List<WebElement> anchors = findElements(key);
    WebElement anchor = anchors.get(Integer.parseInt(index));
    anchor.click();
  }


  @Step("Siparis durmununu <kartDurumu> elementinden bul")
  public void findOrderStatus(String kartDurumu) throws InterruptedException {
    WebElement webElement = findElement(kartDurumu);
    logger.info(" webelement bulundu");
    compareText = webElement.getText();
    logger.info(compareText + " texti bulundu");
  }

  @Step("<key> elementiyle karsilastir")
  public void compareOrderStatus(String key) throws InterruptedException {
    WebElement cardDetail = findElement(key);
    String supplyDetailStatus = cardDetail.getText();
    logger.info(supplyDetailStatus + " texti bulundu");
    Assert.assertTrue(compareText.equals(supplyDetailStatus));
    logger.info(compareText + " textiyle " + supplyDetailStatus + " texti kar????la??t??r??ld??.");
  }

  @Step("<text> textini <key> elemente tek tek yaz")
  public void sendKeyOneByOne(String text, String key) throws InterruptedException {

    WebElement field = findElement(key);
    field.clear();
    if (!key.equals("")) {
      for (char ch : text.toCharArray())
        findElement(key).sendKeys(Character.toString(ch));
      Thread.sleep(10);
      logger.info(key + " elementine " + text + " texti karakterler tek tek girlilerek yaz??ld??.");
    }
  }


  @Step("<key> elementine <text> de??erini js ile yaz")
  public void writeToKeyWithJavaScript(String key, String text) {
    WebElement element = findElement(key);
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].value=arguments[1]", element, text);
    logger.info(key + " elementine " + text + " de??eri js ile yaz??ld??.");
  }


  //Bug??n??n Tarihinin se??ilmesi
  public String chooseDate() {
    Calendar now = Calendar.getInstance();
    int tarih = now.get(Calendar.DATE) + 2;
    return String.valueOf(tarih);
  }


  @Step("<key> tarihinden 2 g??n sonraya al")
  public void chooseTwoDaysFromNow(String key) {
    List<WebElement> elements = findElements(key);
    for (int i = 0; i < elements.size(); i++) {
      if (elements.get(i).getText().equals(chooseDate())) {
        elements.get(i).click();
      }
    }
  }

  @Step("<variable> de??i??kenini <key> elementine yaz")
  public void sendKeysVariable(String variable, String key) {
    if (!key.equals("")) {
      clearInputArea(key);
      findElement(key).sendKeys(StoreHelper.INSTANCE.getValue(variable));
      logger.info(key + " elementine " + StoreHelper.INSTANCE.getValue(variable) + " texti yaz??ld??.");
    }
  }


  @Step("<key> olarak comboboxtan <text> se??imini yap")
  public void selectDropDown(String key, String text) {
    Select drpCountry = new Select(findElement(key));
    drpCountry.selectByVisibleText(text);
  }

  @Step("Batuhan testinium")
  public void batuhanTestinium() throws InterruptedException {
    Thread.sleep(1000);
  }

  //------------------------------------------------------------------

  @Step({"<keys> elementlerinden birini random olarak se??",
          "Pick the one of elements <keys> randomly"})
  public String pickTheElementRandom(String keys) {
    List<WebElement> elements = findElements(keys); //Get all options
    int index = 0; //if list contains only one element it will take that element
    Random rand = new Random();
    index = rand.nextInt(elements.size() - 1);
    String value = elements.get(index).getText();
    if (elements.size() > 1) {
      //Get a random number between 1, size of elements
      elements.get(index).click();
      logger.info("value: " + value);
      logger.info(value + " is selected");
    } else if (elements.size() < 1) {
      //print error message
      logger.info("there isn't any value on the list");
    }
    if (index >= 0) {
      elements.get(index).click();
    }
    return value;
  }

  public int randomIntGenerateNumber(String key, String keys) throws Exception {
    WebElement low = findElement(key);
    WebElement high = findElement(keys);
    String minValue = low.getText().replace("Min. amount: ", "");
    logger.info("String Min Value: " + minValue);
    String minValueWithoutComma = minValue.replace(",", "");
    logger.info("String Min Value: " + minValueWithoutComma);
    int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
    logger.info("String Current Value with Comma: " + minValueWithoutDecimal);
    String highValueWithComma = high.getText().replaceAll("\\s", "");
    logger.info("String High Value with Comma: " + highValueWithComma);
    String highValueWithoutCLP = high.getText().replace("CLP", "");
    logger.info("String High Value without CLP: " + highValueWithoutCLP);
    String highValueWithoutComma = highValueWithoutCLP.replace(",", "");
    logger.info("String High Value with Comma: " + highValueWithoutComma);
    int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValueWithoutComma);
    logger.info("String Current Value with Comma: " + maxValueWithoutDecimal);


    Random random = new Random();
    if (maxValueWithoutDecimal > minValueWithoutDecimal) {
      int result = random.nextInt(maxValueWithoutDecimal - minValueWithoutDecimal) + minValueWithoutDecimal;
      logger.info("random number: " + result);
      return result;
    } else {
      throw new Exception("There isn't enough balance in account");
    }
  }


  public int randomIntGenerateNumberFordollar(String key, String keys) throws Exception {
    WebElement low = findElement(key);
    WebElement high = findElement(keys);
    String minValue = low.getText().replace("Min. amount: ", "");
    logger.info("String Min Value: " + minValue);
    String minValueWithout$ = minValue.replace("$", "");
    logger.info("String Min Value: " + minValueWithout$);
    logger.info("String Min Value new : " + minValueWithout$);

    int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithout$);
    logger.info("String Current Value with Comma: " + minValueWithoutDecimal);
    String highValueWithComma = high.getText().replaceAll("\\s", "");
    logger.info("String High Value with Comma: " + highValueWithComma);
    String removeStringHighValue;
    if (high.getText().contains("CLP")) {
      removeStringHighValue = high.getText().replace("CLP", "");
      logger.info("String High Value without CLP: " + removeStringHighValue);
    } else {
      removeStringHighValue = high.getText().replace("$", "");
    }
    String highValueWithoutComma = removeStringHighValue.replace(",", "");
    logger.info("String High Value with Comma: " + highValueWithoutComma);
    int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValueWithoutComma.replace("Max. amount:", ""));
    logger.info("String Current Value with Comma: " + maxValueWithoutDecimal);


    Random random = new Random();
    if (maxValueWithoutDecimal > minValueWithoutDecimal) {
      int result = random.nextInt(maxValueWithoutDecimal - minValueWithoutDecimal) + minValueWithoutDecimal;
      logger.info("random number: " + result);
      return result;
    } else {
      throw new Exception("There isn't enough balance in account");
    }
  }


  public int randomIntGenerateNumberForCLP(String key, String keys) throws Exception {
    WebElement low = findElement(key);
    WebElement high = findElement(keys);
    String minValue = low.getText().replace("Min. amount: ", "");
    logger.info("String Min Value: " + minValue);
    String minValuewithoutCLP = minValue.replace("CLP", "");
    logger.info("String Min Value Without CLP: " + minValuewithoutCLP);
    String minValueWithoutComma = minValuewithoutCLP.replace(",", "");
    logger.info("String Min Value: " + minValueWithoutComma);
    int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
    logger.info("String Current Value is: " + minValueWithoutDecimal);
    String highValueWithComma = high.getText().replaceAll("\\s", "");
    logger.info("String High Value with Comma: " + highValueWithComma);

    String highValue = high.getText().replace("Max. amount: ", "");
    logger.info("String Max Value: " + highValue);
    String highValueWithoutCLP = highValue.replace("CLP", "");
    logger.info("String High Value without CLP: " + highValueWithoutCLP);
    String highValueWithoutComma = highValueWithoutCLP.replace(",", "");
    logger.info("String High Value with Comma: " + highValueWithoutComma);
    int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValueWithoutComma);
    logger.info("String Current Value is: " + maxValueWithoutDecimal);


    Random random = new Random();
    if (maxValueWithoutDecimal > minValueWithoutDecimal) {
      int result = random.nextInt(maxValueWithoutDecimal - minValueWithoutDecimal) + minValueWithoutDecimal;
      logger.info("random number: " + result);
      return result;
    } else {
      throw new Exception("There isn't enough balance in account");
    }
  }


  @Step("select month from list <key>")
  public void monthPicker(String key) {
    Select month = new Select(findElement(key));
    month.selectByValue("5");
    logger.info("Selected month is : ");

  }

  @Step("select year from list <key>")
  public void yearPicker(String key) {
    Select month = new Select(findElement(key));
    month.selectByValue("2024");
    logger.info("Selected year is : ");

  }

  public String convertDecimalToStringWithoutComma(String key) {
    Float floatValue = Float.parseFloat(key);
    DecimalFormat df = new DecimalFormat("0");
    df.setMaximumFractionDigits(0);
    String formatedStringValue = df.format(floatValue);
    logger.info("Converted Value without Comma: " + formatedStringValue);
    return formatedStringValue;
  }

  public int convertDecimalStringValueToInt(String key) {
    logger.info("Value ==> " + key);

    String formatedStringValue = convertDecimalToStringWithoutComma(key);
    int ??ntValueWithoutDecimal = (int) Math.floor(Double.parseDouble(formatedStringValue));
    logger.info("Integar formated Value without Comma: " + ??ntValueWithoutDecimal);
    return ??ntValueWithoutDecimal;
  }

  @Step("Genarete random number and saved the number <saveKey>. And write the saveKey to the <keyy> element")
  public void saveTheGenaretedValueAndWrite(String saveRandomAmount, String writeAmount) throws Exception {
//    int randomNumber = randomIntGenerateNumber(key, keys);
    //webElement.sendKeys(String.valueOf(randomNumber));
//    StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(randomNumber));
//    logger.info("saveKey for genareted random number: " + saveKey);
//    waitBySeconds(2);
//    StoreHelper.INSTANCE.getValue(saveKey);
//    WebElement element = findElement(keyy);
//    element.clear();
//    element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
    //webElement.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));

    Random randomNumber = new Random();
    int minRange = 4000, maxRange = 7000;
    int number = randomNumber.nextInt(maxRange - minRange) + minRange;
    logger.info(number + " ");
    StoreHelper.INSTANCE.saveValue(saveRandomAmount, String.valueOf(number));
    logger.info("saveKey for genareted random number: " + saveRandomAmount);
    waitBySeconds(2);
    StoreHelper.INSTANCE.getValue(saveRandomAmount);
    WebElement element = findElement(writeAmount);
    element.sendKeys(StoreHelper.INSTANCE.getValue(saveRandomAmount));


//    logger.info("This is minimum value:"+ lowValue);
//    logger.info("This is maximum value:"+ HighValue);
//    int randomNumber = randomIntGenerateNumber(lowValue, HighValue);
//    StoreHelper.INSTANCE.saveValue(saveRandomAmount, String.valueOf(randomNumber));
//    logger.info("saveKey for genareted random number: " + saveRandomAmount);
//    waitBySeconds(2);
//    StoreHelper.INSTANCE.getValue(saveRandomAmount);
//    WebElement element = findElement(writeAmount);
////    element.clear();
//    element.sendKeys(StoreHelper.INSTANCE.getValue(saveRandomAmount));

  }

  @Step({"<keys> elementlerinden ilk se??enek hari?? birini random olarak se??",
          "Pick the one of elements <keys> randomly excluding first option"})
  public void pickTheElementRandomExcludingFirstOption(String keys) {
    List<WebElement> elements = findElements(keys); //Get all options
    Random randomOption = new Random();
    int startOption = 1; //assuming "--your choice--" is index "0"
    int endOption = elements.size(); // end of range
    int number = startOption + randomOption.nextInt(endOption - startOption);
    String value = elements.get(number).getText();
    elements.get(number).click();
    logger.info("value: " + value);
    logger.info("The element is selected");

  }

  @Step({"Pick the one of elements <keys> randomly excluding first option For Condition and write identity number <keys>"})
  public void pickTheElementRandomExcludingFirstOptionForCondition(String type, String num) {
    List<WebElement> elements = findElements(type); //Get all options
    Random randomOption = new Random();
    int startOption = 1; //assuming "--your choice--" is index "0"
    int endOption = elements.size(); // end of range
    int number = startOption + randomOption.nextInt(endOption - startOption);
    String value = elements.get(number).getText();
    elements.get(number).click();
    logger.info("value: " + value);
    logger.info("The element is selected");

    if (Objects.equals(value.trim(), IDENTITY_TYPE)) {
      findElement(num).sendKeys(IDENTITY_NUMBER);
      logger.info("' text is written to the '" + num + "' element.");
    } else {
      findElement(num).sendKeys(IDENTITY_OTHER_NUMBER);
      logger.info("' text is written to the '" + num + "' element.");

    }


  }

  @Step({"<key> li elementi bul ve de??erini <saveKey> olarak sakla",
          "Find element by <key> and save text <saveKey>"})
  public void saveTextByKey(String key, String saveKey) throws InterruptedException {
    Thread.sleep(1000);
    String text = getElementText(key);
    StoreHelper.INSTANCE.saveValue(saveKey, text);
    logger.info("Saved text: " + text);
    Thread.sleep(2000);

  }

  @Step({"<key> li elementi bul ve de??erini <saveKey> olarak sakla",
          "Find element by <key> and converted the value than save the converted text <saveKey>"})
  public void saveTextByKey2(String key, String saveKeyy) throws InterruptedException {
    Thread.sleep(1000);
    StoreHelper.INSTANCE.saveValue(saveKeyy, getTextWithoutComma(key));
    Thread.sleep(2000);

  }

  public String getTextWithoutComma(String key) {
    String element = getElementText(key).replace(CLP, EMPTY);

    logger.info(element);
    if (element.contains(COMMA) || element.contains(DOLLAR)) {
      String elementWithoutComma = element.replace(COMMA, EMPTY);
      logger.info("Text without comma: " + elementWithoutComma);

      String elementWithoutDecimal = "";
      if (elementWithoutComma.contains(DOLLAR)) {
        convertDecimalToStringWithoutComma(elementWithoutComma.replace(DOLLAR, EMPTY));
      } else {
        convertDecimalToStringWithoutComma(elementWithoutComma);
      }
      logger.info("Text: " + elementWithoutDecimal);
      return elementWithoutDecimal;
    } else {
      logger.info("Text: " + convertDecimalToStringWithoutComma(element));
      return convertDecimalToStringWithoutComma(element);
    }
  }


  @Step({"<saveKey> de??eri <saveKeyy> saklanan de??erini i??eriyor mu kontrol et",
          "Compare saved key <saveKey> contains the other saved key <saveKeyy> of element"})
  public void equalsSaveTextByOtherSaveTextContain(String saveKey, String saveKeyy) {
    String savedRandom = StoreHelper.INSTANCE.getValue(saveKey);
    logger.info("saved random: " + savedRandom);
    String paymentValueInThirdPart = StoreHelper.INSTANCE.getValue(saveKeyy);
    logger.info("payment value: " + paymentValueInThirdPart);
    Assert.assertTrue(savedRandom.contains(paymentValueInThirdPart));
  }

  @Step({"<key> li elementi bul ve de??erini <saveKey> saklanan de??eri i??eriyor mu kontrol et",
          "Find element by <key> and compare saved key <saveKey> contains the text of element"})
  public void equalsSaveTextByKeyContain(String key, String saveKey) throws IOException {
    Assert.assertTrue(getElementText(key).contains(StoreHelper.INSTANCE.getValue(saveKey)));
  }

  @Step("Select the option by value <keyy> from the list <key>")
  public void selectValue(String key, String keyy) {
    WebElement headingOfDropdown = findElement(key);
    Select drpList = new Select(headingOfDropdown);
// Select the option with value "keyy"
    drpList.selectByValue("keyy");
  }

  @Step({"<keys> elementlerinden birini adetine g??re random olarak dropdown listesinden se??",
          "Select the one of elements randomly regarding <keys> size in the dropdown list"})
  public void selectTheValueRandomExcludingSecondOption(String keys) {
    List<WebElement> elements = findElements(keys); //Get all options
    Random randomOption = new Random();
    int startOption = 2; //assuming "--your choice--" is index "0"
    int endOption = elements.size(); // end of range
    int number = startOption + randomOption.nextInt(endOption - startOption);
    String value = elements.get(number).getText();
    elements.get(number).click();
    logger.info("value: " + value);
    logger.info("The element is selected");
  }

  public void getYear() {
    //Getting the current date value of the system
    LocalDate current_date = LocalDate.now();
    logger.info("Current date: " + current_date);
    //getting the current year from the current_date
    int current_Year = current_date.getYear();
    logger.info("Year: " + current_Year);
  }

  @Step({"<key> ile tan??mlanan elemente t??kla",
          "Click to the defined element with <key>"})
  public void clickValue(String key) {
    WebElement element = findElement(key);
    JavascriptExecutor executor = (JavascriptExecutor) driver;
    executor.executeScript("arguments[0].click();", element);
  }

  @Step({"<key> ile tan??mlanan Aktivasyon Kodu alan??na Js ile de??er yazd??r",
          "Send the text to the defined Activation Code field with <key>"})
  public void sendTextWithJs2(String key) {
    WebElement userNameTxt = findElement(key);
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    // set the text
    jsExecutor.executeScript("arguments[0].value='12345678'", userNameTxt);
  }

  @Step({"<key> ile tan??mlanan elemente Js ile de??er yazd??r",
          "Send the text to the password field with <key>"})
  public void sendTextWithJs(String key) {
    WebElement pass = findElement(key);
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    // set the text
    jsExecutor.executeScript("arguments[0].value='123'", pass);
  }

  @Step({"Send the phone number <key>"})
  public void sendPhoneNumber(String key) {
    WebElement pass = findElement(key);
    JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
    // set the text
    jsExecutor.executeScript("arguments[0].value='123456789'", pass);
  }


  @Step({"<key> ile tan??mlanan Aktivasyon Kodu alan??na Js ile de??er yazd??r",
          "Send the text to the defined Activation Code field with wrapper <key>"})
  public void sendTextWithwrapper(String key) {
    findElement(key).sendKeys("12345678");

  }

  @Step({"Send the text to the card Code <A1>,<B1>,and <C1>"})
  public void sendTextToCardCode(String A1, String B1, String C1) {
    findElement(A1).sendKeys("12");
    findElement(B1).sendKeys("34");
    findElement(C1).sendKeys("56");

  }


  @Step({"Write value <text> to element <key>, if the element exists"})
  public void sendKeysToExistElement(String text, String key) {
    WebElement element = getElementWithKeyIfExists(key);
    if (element.getText().equals("RUT de la cuenta bancaria con que pagar??s")) {
      findElement(key).sendKeys(text);
      logger.info("'" + text + "' text is written to the '" + key + "' element.");
    }
  }

  @Step("Caculate the balance after Withdrawal by <key> and <saveKey> and save the value <saveKeys>")
  public void calculate(String saveKey, String key, String saveKeys) {
    String savedAmount = StoreHelper.INSTANCE.getValue(saveKey);
    int savedIntAmount = Integer.parseInt(savedAmount);
    String balance = getTextWithoutComma(key);
    int savedIntBalance = Integer.parseInt(balance);
    int calculate = (savedIntBalance - savedIntAmount);
    logger.info("Left Balance after withdrawal: " + calculate);
    String calculatedBalanceString = Integer.toString(calculate);
    String calculatedBalance = StoreHelper.INSTANCE.saveValue(saveKeys, calculatedBalanceString);
    logger.info(calculatedBalanceString);
  }


  @Step("Write values dropdown <text> to element <key>")
  public void sendDropDown(String text, String key) {

    Select select1 = new Select(driver.findElement(By.cssSelector("div[class='a-dropdownbox a-form-datefield-day   yui3-widget a-dropdownbox-content']")));
    select1.selectByValue(text);

  }

 @Step({"Write date values  <text1> to element <key1>"})
  public void birthDate( Integer text1, String key1) {
    if (!key1.equals("")) {
      findElement(key1).click();

    List<WebElement> birthDay = findElements(key1);
    System.out.println(birthDay.size());

    for(int i = 0; i<=birthDay.size()-1; i++) {

      if(birthDay.get(i).equals(text1)) {

        birthDay.get(i).click();
        break;

      }

  }
}
}
}









