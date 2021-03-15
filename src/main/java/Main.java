import java.util.*;
import java.util.Stack;

public class Main {

    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        Stack<Integer> countStack = new Stack<>();
        Stack<String> resStack = new Stack<>();
        int idx = 0;
        while (idx < s.length()) {
            if (Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while (Character.isDigit(s.charAt(idx))) {
                    count = 10 * count + (s.charAt(idx) - '0');
                    idx++;
                }
                countStack.push(count);
            } else if (s.charAt(idx) == '[') {
                resStack.push(res.toString());
                res = new StringBuilder();
                idx++;
            } else if (s.charAt(idx) == ']') {
                StringBuilder temp = new StringBuilder(resStack.pop());
                int repeatTimes = countStack.pop();
                temp.append(res.toString().repeat(Math.max(0, repeatTimes)));
                res = new StringBuilder(temp.toString());
                idx++;
            } else {
                res.append(s.charAt(idx));
                idx++;
            }
        }
        return res.toString();
    }

    public static boolean isValid(String s) {
        String pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSUVWXYZ0123456789[]";
        for (int i = 0; i < s.length(); i++) {
            if (pattern.indexOf(s.charAt(i)) == -1)
                return false;
            if (Character.isDigit(s.charAt(i)) && s.charAt(i + 1) != '[')
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the string: ");
        String input = in.next();
        if (isValid(input))
            System.out.println(decodeString(input));
        else
            System.out.println("Input string is not valid!");
    }
}
