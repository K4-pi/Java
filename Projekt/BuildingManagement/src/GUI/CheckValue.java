package GUI;

public class CheckValue {
    public static boolean isNumber(String value) {
        //Checking if String is number
        char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
        char[] chars = value.toCharArray();
        boolean isNumber = false;

        for (char c : chars) {
            System.out.println("c: " + c);
            for (char i : numbers) {
                if (c == i) {
                    isNumber = true;
                    break;
                }
                isNumber = false;
            }
        }
        return isNumber;
    }
}
