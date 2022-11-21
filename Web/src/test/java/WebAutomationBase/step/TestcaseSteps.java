package WebAutomationBase.step;

import WebAutomationBase.helper.StoreHelper;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebElement;
import static WebAutomationBase.step.constant.Constant.*;

import java.util.Random;

public class TestcaseSteps extends BaseSteps {

    @Step("Check Empty Amount <button> and getting an error <validation>")
    public void emptyAmount(String button,String validation){
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()){
            logger.info("Test Pass - Empty Amount and getting error validation: " + error.getText());
        }
        else {
            logger.info("Test Failed - Empty Amount Shouldn't be accept: ");
        }


    }

    @Step("Enter Special Characters <key> then click <key> and Getting an Error <key>")
    public void enterSpecialChar(String AmountField,String button,String validation){

        WebElement element = findElement(AmountField);
        element.sendKeys(SPECIAL_CHARACTER);
        logger.info("Special Character is: "+SPECIAL_CHARACTER);

        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()){
            logger.info("Test Pass - Enter Special characters and getting error validation: " + error.getText());
        }
        else {
            logger.info("Test Failed - Special Characters Shouldn't accept: ");
        }

    }

    @Step("Enter Amount less than Minimum <key> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterLessAmountForCheckingValidations(String key,String saveKey, String AmountField,String button,String validation) {
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
        if (error.isDisplayed()){
            logger.info("Test Pass - Amount is less than Minimum amount and getting error validation: " + error.getText());
        }
        else {
            logger.info("Test Failed - Amount is not less than Minimum amount: ");
        }

    }

    @Step("Enter Amount Greater than Maximum Amount <keys> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterHighAmountForCheckingValidations(String keys,String saveKey, String AmountField,String button,String validation) {
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
        if (error.isDisplayed()){
            logger.info("Test Pass - Amount is Greater than Maximum amount and getting error validation: " + error.getText());
        }
        else {
            logger.info("Test Failed - Amount is not Greater than Maximum amount: " );
        }
    }

    public int getAmountLow(String key) {
        WebElement low = findElement(key);
        String minValue = low.getText().replace("Min. amount: ","");
        logger.info("String Min Value: " + minValue);
        String minValuewithoutCLP = minValue.replace("CLP","");
        logger.info("String Min Value Without CLP: " + minValuewithoutCLP);
        String minValueWithoutComma = minValuewithoutCLP.replace(",", "");
        logger.info("String Min Value: " + minValueWithoutComma);
        int minValueWithoutDecimal = convertDecimalStringValueToInt(minValueWithoutComma) ;
        logger.info("String Current Value is: " + minValueWithoutDecimal);
        int lessAmount = minValueWithoutDecimal -1 ;
        return lessAmount;



    }

    public int getAmountHigh(String keys)  {

        WebElement high = findElement(keys);
        String highValue = high.getText().replace("Max. amount: ", "").replace("CLP", "").
                replace(",", "");
        logger.info("String Max Value with decimal: " + highValue);
        int maxValueWithoutDecimal = convertDecimalStringValueToInt(highValue);
        logger.info("String Maximum Current Value is: " + maxValueWithoutDecimal);
        int highAmount = maxValueWithoutDecimal +1;

        return highAmount;



    }


}
