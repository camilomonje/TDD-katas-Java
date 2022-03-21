//import org.omg.CORBA.SystemException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StringCalculator {
    public int add(String values) throws Exception { //"1,1"
        if (values.length() > 0) {
            int temp;
            String delimiter = null;
            String[] delimitersArray = new String[1];
            try {
                temp = Integer.parseInt("" + values.charAt(0));
                delimitersArray[0] = null;
            } catch (Exception e) {
                if (("" + values.charAt(0)) == "-") {
                    delimiter = null;
                    delimitersArray[0] = null;
                } else if (values.charAt(0) == '[') {
                    delimiter = values.substring(1, values.indexOf(']'));
                    String subStringValues = values.substring(0, values.lastIndexOf(']'));
                    delimitersArray = subStringValues
                            .replace('[', ' ')
                            .replace(']', ' ')
                            .trim()
                            .replaceAll("\\s+", ";").split(";");
                    Arrays.sort(delimitersArray, Collections.reverseOrder());
                } else {
                    delimiter = "" + values.charAt(0);
                    delimitersArray[0] = "" + values.charAt(0);
                }
            }

            String[] splittedList;
            if ((delimiter != null && delimiter.length() == 1) && (delimitersArray.length == 1 && delimitersArray[0] != null)) {
                splittedList = values.substring(1).split(delimitersArray[0]);
            } else if ((delimiter != null && delimiter.length() > 1) || (delimitersArray[0] != null && delimitersArray.length > 1)) {
                List<String> delimiterList = Arrays.asList(delimitersArray);
                for (String del : delimiterList) {
                    values = values.replaceAll(del, "_");
                }
                splittedList = values.substring(values.lastIndexOf(']') + 1, values.length()).split("_");
            } else {
                splittedList = values.split("[,|\n]");
            }

            ArrayList<Integer> numberList = new ArrayList<>();
            int accumulator = 0;
            for (String element : splittedList) {
                int tempValue = Integer.parseInt(element);
                if (tempValue < 0) {
                    throw new Exception("NegativeNumberException");
                }
                if (tempValue > 1000) {
                    continue;
                }
                numberList.add(tempValue);
            }
            for (Integer number : numberList) {
                accumulator += number;
            }
            return accumulator;
        }
        return 0;
    }
}