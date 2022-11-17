package WebAutomationBase.step;

import WebAutomationBase.helper.StoreHelper;
import com.thoughtworks.gauge.Step;
import org.openqa.selenium.WebElement;

public class TestcaseSteps extends BaseSteps {

    @Step("Enter Amount less than Minimum <key> and saved the number <saveKey>. And write the saved key to the <keyy> element and click continue button <button> then show validation error <key>")
    public void enterLessAmountForCheckingValidations(String key,String saveKey, String keyy,String button,String validation) {
        int lessValue = getAmountLow(key);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(lessValue));
        logger.info("saveKey for genareted random number: " + saveKey);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(keyy);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
        WebElement continueButton = findElement(button);
        continueButton.click();

        WebElement error = findElement(validation);
        if (error.isDisplayed()){
            logger.info("Test Pass - Amount is less than Minimum amount and getting error validation: " + error.getText());
        }
        else {
            logger.info("Test Failed - Amount is not less than Minimum amount: " + error.getText());
        }

    }

    @Step("Enter amount Greater than Maximum Amount <keys> and saved the number <saveKey>. And write the saved key to the <keyy> element and show validation error <key>")
    public void enterHighAmountForCheckingValidations(String keys,String saveKey, String keyy,String validation) {
        int lessValue = getAmountHigh(keys);
        WebElement error = findElement(validation);
        //webElement.sendKeys(String.valueOf(randomNumber));
        StoreHelper.INSTANCE.saveValue(saveKey, String.valueOf(lessValue));
        logger.info("saveKey for genareted random number: " + saveKey);
        StoreHelper.INSTANCE.getValue(saveKey);
        WebElement element = findElement(keyy);
        element.sendKeys(StoreHelper.INSTANCE.getValue(saveKey));
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

        return maxValueWithoutDecimal +1;



    }


}
