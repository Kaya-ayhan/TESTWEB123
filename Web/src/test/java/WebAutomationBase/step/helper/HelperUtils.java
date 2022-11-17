package WebAutomationBase.step.helper;

public class HelperUtils {
    // SingleTon  implemented.
    private static HelperUtils helperUtils = null;

    public static HelperUtils getInstance()
    {
        if (helperUtils == null)
            helperUtils = new HelperUtils();
        return helperUtils;
    }

    public String removeString(String key,String value ){
       return value.replace(key,"");
    }
}
