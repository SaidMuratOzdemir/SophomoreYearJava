package Oct31;

public class Main {

    public static void main(String[] args) {

        //Binary Strings
        System.out.println("Binary Strings");
        binaryStrings(3, "");


        System.out.println(occurance("DataStructuresRecursion", 't'));
        System.out.println(occurance("DataStructuresRecursion", 'R'));
    }

    public static void binaryStrings(int k, String str) {
        if (k == 0) {
            System.out.println(str);
        } else {
            binaryStrings(k - 1, str + "0");
            if (str.isEmpty() || str.charAt(str.length() - 1) != '1') {
                binaryStrings(k - 1, str + "1");
            }
        }
    }

    public static int occurance(String s, char c) {
        if (s.isEmpty()) {
            return -1;
        } else {
            if (s.charAt(0) == c) {
                return 0;
            } else {
                return 1 + occurance(s.substring(1), c);
            }
        }
    }
}
