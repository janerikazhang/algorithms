package interview.prepare;

/**
 * Created by Rika on 2017/9/12.
 */
public class StringManipulate {
    public static void main(String[] args) {
        StringManipulate stringManipulate = new StringManipulate();
        String result = stringManipulate.strConvert("aabbcca");
        System.out.println("lalala: " + result);
    }

    String strConvert(String str) {
        int count = 0;
        String result = "";
        String current = "";
        for (char c : str.toCharArray()) {
            if (current.isEmpty()) {
                current = c +"";
                count ++;
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
}
