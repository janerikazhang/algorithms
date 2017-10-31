package interview.prepare.DataStructureAndAlg;

/**
 * Created by Rika on 2017/9/12.
 */
public class StringManipulate {
    public static void main(String[] args) {
        StringManipulate stringManipulate = new StringManipulate();
        String result = stringManipulate.strConvert("aabbcca");
        System.out.println("String convert: " + result);

        System.out.println("String to number: " + stringManipulate.atoi("12345"));
    }

    /**
     * convert from aabbcca to a2b2c2a1
     */
    String strConvert(String str) {
        int count = 0;
        String result = "";
        String current = "";
        for (char c : str.toCharArray()) {
            if (current.isEmpty()) {
                current = c + "";
                count++;
            } else if (current.equals(c + ""))
                count++;
            else {
                result += current + count;
                count = 1;
                current = c + "";
            }
        }
        result += current + count;
        return result;
    }

    /**
     * Convert string to number
     * @param numberStr
     * @return
     */
    Integer atoi(String numberStr) {
        Integer result = 0;
        for (char c : numberStr.toCharArray()) {
            if (c >= '0' && c <= '9') {
                 result  = result * 10 + (c - '0');
            } else {
                throw new RuntimeException("Unexpected char found " + c);
            }
        }
        return result;
    }
}
