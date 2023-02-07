package WebAutomationBase.step;

import WebAutomationBase.helper.StoreHelper;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import static WebAutomationBase.step.constant.Constant.*;

import java.util.Random;

public class TestcaseSteps extends BaseSteps {

    @Step("Check Empty Amount <button> and getting an error <validation>")
    public void emptyAmount(String button, String validation) {
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Empty Amount and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Empty Amount Shouldn't be accept: ");
        }


    }

    @Step("Enter Special Characters <key> then click <key> and Getting an Error <key>")
    public void enterSpecialChar(String AmountField, String button, String validation) {

        WebElement element = findElement(AmountField);
        element.sendKeys(SPECIAL_CHARACTER);
        logger.info("Special Character is: " + SPECIAL_CHARACTER);
        waitByMilliSeconds(200);

        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Enter Special characters and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Special Characters Shouldn't accept: ");
        }

    }

    @Step("Enter Amount less than Minimum <key> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterLessAmountForCheckValidations(String key, String saveKey, String AmountField, String button, String validation) {
        int lessValue = getAmountLow(key);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(lessValue));
        logger.info("saveKey for Amount is Less than Minimum amount: " + lessValue);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(AmountField);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Amount is less than Minimum amount and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Amount is not less than Minimum amount: ");
        }

    }

    @Step("Enter Amount Greater than Maximum Amount <keys> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterHighAmountForCheckValidations(String keys, String saveKey, String AmountField, String button, String validation) {
        int highValue = getAmountHigh(keys);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(highValue));
        logger.info("saveKey for Amount is Greater than Maximum amount: " + highValue);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(AmountField);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Amount is Greater than Maximum amount and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Amount is not Greater than Maximum amount: ");
        }
    }

    @Step("Enter Dollar Amount less than Minimum <key> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterLessDollarAmountForCheckValidations(String key, String saveKey, String AmountField, String button, String validation) {
        int lessValue = getDollarAmountLow(key);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(lessValue));
        logger.info("saveKey for Amount is Less than Minimum amount: " + lessValue);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(AmountField);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Amount is less than Minimum amount and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Amount is not less than Minimum amount: ");
        }

    }

    @Step("Enter Dollar Amount Greater than Maximum Amount <keys> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterHighDollarAmountForCheckValidations(String keys, String saveKey, String AmountField, String button, String validation) {
        int highValue = getDollarAmountHigh(keys);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(highValue));
        logger.info("saveKey for Amount is Greater than Maximum amount: " + highValue);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(AmountField);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Amount is Greater than Maximum amount and getting error validation: " + error.getText());
        } else {
            logger.info("Test Failed - Amount is not Greater than Maximum amount: ");
        }
    }

    @Step("Check Empty Fields for document and getting an error <validation>")
    public void emptyDocumentField(String validation) {

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in document field: " + error.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in empty document ");
        }


    }

    @Step("Check Empty Fields for Identity number and getting an error <validation>")
    public void emptyIdentityNumField(String validation) {

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Identity Number field: " + error.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in empty identity number field  ");
        }


    }

    @Step("Enter less value in Identity Number field <key> and click continue button <button> then getting an error <validation>")
    public void checkValidationIdentityLessNum(String valueIdentity, String button, String validation) {

        WebElement element = findElement(valueIdentity);
        element.sendKeys(VALIDATION_IDENTITY_NUMBER);
        logger.info("Enter Less Indentity Number is " + VALIDATION_IDENTITY_NUMBER);
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Getting error validation : " + error.getText());
        } else {
            logger.info("Test Failed - Doesn't getting an error validation ");
        }


    }

    @Step("Check Validations in Empty Fields for Select Card <cardvalidation> and missing card <missingCard> and card expiry <cardExpiry>")
    public void emptyFieldsCheckValidations(String cardvalidation, String missingCard, String cardExpiry) {

        WebElement errorCard = findElement(cardvalidation);
        WebElement errorMissing = findElement(missingCard);
        WebElement errorExpiry = findElement(cardExpiry);

        if (errorCard.isDisplayed() && errorMissing.isDisplayed() && errorExpiry.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Select Card field: " + errorCard.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in empty Select Card field  ");
        }
        if (errorMissing.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Missing Card field: " + errorMissing.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in empty Missing Card field  ");
        }
        if (errorExpiry.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Card Expiration field: " + errorExpiry.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in empty Card Expiration field  ");
        }

    }

    @Step("Check Less Missing Card Number Fields and getting an error <validation>")
    public void checkValidationMissingCardLessNum(String validation) {

        WebElement error = findElement(validation);
        if (error.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Missing Card number field by Putting Less number: " + error.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in Missing Card number field  ");
        }


    }

    @Step("Check Validations For Empty fields <key> and <key>")
    public void checkValidationsBankAccount(String bankCode, String accountNum) {

        WebElement errorBank = findElement(bankCode);
        WebElement errorAccount = findElement(accountNum);
        if (errorBank.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Bank code field: " + errorBank.getText());
        } else {
            logger.info("Test Failed - Error Validations doesn't display in Bank code field  ");
        }
        if (errorAccount.isDisplayed()) {
            logger.info("Test Pass - Getting an error validation in Account number field: " + errorAccount.getText());

        } else {

            logger.info("Test Failed - Error Validations doesn't display in Account number field  ");
        }
    }

    @Step("Clear Text <key>")
    public void deleteText(String key) {
        WebElement toClear = findElement(key);
        toClear.sendKeys(Keys.CONTROL + "a");
        toClear.sendKeys(Keys.DELETE);
    }


    public int getAmountLow(String key) {
        WebElement low = findElement(key);
        String minValue = low.getText().replace("Min. amount: ", "");
        logger.info("String Min Value: " + minValue);
        String minValuewithoutCLP = minValue.replace("CLP", "");
        logger.info("String Min Value Without CLP: " + minValuewithoutCLP);
        String minValueWithoutComma = minValuewithoutCLP.replace(",", "");
        logger.info("String Min Value: " + minValueWithoutComma);
        int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
        logger.info("String Current Value is: " + minValueWithoutDecimal);
        int lessAmount = minValueWithoutDecimal - 1;
        return lessAmount;
    }


    public int getAmountHigh(String keys) {

        WebElement high = findElement(keys);
        String highValue = high.getText().replace("Max. amount: ", "").replace("CLP", "").
                replace(",", "");
        logger.info("String Max Value with decimal: " + highValue);
        int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValue);
        logger.info("String Maximum Current Value is: " + maxValueWithoutDecimal);
        int highAmount = maxValueWithoutDecimal + 1;

        return highAmount;


    }

    public int getDollarAmountLow(String key) {
        WebElement low = findElement(key);
        String minValue = low.getText().replace("Min. amount: ", "");
        logger.info("String Min Value: " + minValue);
        String minValuewithoutdollar = minValue.replace("$", "");
        logger.info("String Min Value Without Dollar: " + minValuewithoutdollar);
        String minValueWithoutComma = minValuewithoutdollar.replace(",", "");
        logger.info("String Min Value: " + minValueWithoutComma);
        int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma);
        logger.info("String Current Value is: " + minValueWithoutDecimal);
        int lessAmount = minValueWithoutDecimal - 1;
        return lessAmount;
    }


    public int getDollarAmountHigh(String keys) {

        WebElement high = findElement(keys);
        String highValue = high.getText().replace("Max. amount: ", "").replace("$", "").
                replace(",", "");
        logger.info("String Max Value with decimal: " + highValue);
        int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValue);
        logger.info("String Maximum Current Value is: " + maxValueWithoutDecimal);
        int highAmount = maxValueWithoutDecimal + 1;

        return highAmount;


    }



}
