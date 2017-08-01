package util;

import java.text.DecimalFormat;

/**
 * Created by Moein on 7/9/2017.
 */

public class Decimalformat {
    public static String foramtdeciaml(int value){
        DecimalFormat decimalformat=new DecimalFormat("#,###,###");
        String formatted=decimalformat.format(value);
        return formatted;
    }
}
