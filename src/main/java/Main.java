import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Rika on 2017/7/12.
 */
public class Main {
    public static HashMap<String, String> MorseCode = new HashMap<String, String>();
    public static void main(String[] args) {

        int sult = solution(1000000);
            System.out.print(sult);
    }

    public static int solution(int number) {
        int[] arr = new int[number];
        int i = 3, j = 5, index = 0;
        while(i < number || j < number) {
            if (i < number) {
                arr[index++] = i;
                i +=3;
            }
            if (j < number) {
                arr[index++] = j;
                j +=5;
            }

        }
        int sum = 0;
        Arrays.sort(arr);
        for (int k = 0; k < arr.length; k++) {
            if (k == 0 ) {
                sum += arr[k];
            } else if (arr[k]!= arr[k-1]) {
                sum += arr[k];
            }
        }
        return sum;
    }

    static String toCamelCase(String s){
        String[] arr;
        if (s.contains("-")) {
            arr = s.split("-");
        } else {
            arr = s.split("_");
        }
        String result = "";
        for (int i=0; i < arr.length; i++){
            String t = arr[i];
            if (t.isEmpty()) continue;
            if (i == 0) {
                result += t;
            } else {
                result += t.replaceFirst(t.charAt(0)+"", (t.charAt(0)+"").toUpperCase()) ;
            }
        }
        return result;
    }

    public static String toAlternativeString(String string) {
        char[] arr = string.toCharArray();
        String result = "";
        for (char c : arr) {
            String t = new String(c + "");
            if (t.equals(t.toLowerCase())) {
                result += t.toUpperCase();
            } else {
                result += t.toLowerCase();
            }
        }

        return result;
    }

    public static int zeros(int n) {
        if (n == 0) return 0;
        int num=0;
        int b=1;
        while(true)
        {
            b*=5;
        num+=n/b;
            if(b>n)
                break;
        }
        return num;
    }

    
    public static double[] tribonacci(double[] s, int n) {
        double[] result = new double[n];
        if (n == 0) return new double[]{};
        else if (n==1) {
            result[0]=s[0];
        } else if (n == 2) {
            result[0]=s[0];
            result[1]=s[1];
        }
        else if (n == 3) return s;
        else {
            for (int i=0; i < s.length; i++) {
                result[i] = s[i];
            }
            for (int j=s.length; j < n; j ++) {
                result[j] = result[j-2] + result[j-1] + result[j-3];
            }
        }
        return result;
    }
    

    public static String accum(String s) {
        String result = "";
        char[] array = s.toCharArray();
        for (int i=0; i < array.length ; i++) {
            for (int j =0; j < i +1; j++) {
                if (j == 0) {
                    result += new String(array[i] +"").toUpperCase();
                } else {
                    result += new String(array[i] +"").toLowerCase();
                }
            }
            result += "-";
        }

        return result.substring(0, result.length()-1);
    }

    public static String decode(String morseCode) {
        String result = "";
        String[] arrays = morseCode.split("  ");
        for (String entry : arrays) {
            for (String en : entry.split(" ")) {
                if (MorseCode.get(en) != null)
                    result += MorseCode.get(en);
            }
            result += " ";
        }
        return result.trim();
    }




    public static String even_or_odd(int number) {
        Double newDouble = new Double(number/2);
        return number != newDouble.intValue()*2 ? "odd" : "even";

    }

    public static String HighAndLow(String numbers) {
        String[] array  = numbers.split(" ") ;
        Integer[] intList = new Integer[array.length];


//        Arrays.sort(array);
        for (int i=0; i< array.length; i ++) {
            int a = Integer.parseInt(array[i]);
            intList[i] = a;
        }
        Arrays.sort(intList);

        System.out.println(intList);

        String result;
        result = intList[intList.length-1] + " " + intList[0];
        return result;
    }
}
