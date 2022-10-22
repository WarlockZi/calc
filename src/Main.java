import java.util.*;

public class Main {
    public static String[] parseExpression(String expression) {
        return expression.split(" ");
    }
    public static HashMap getRomanHashMap() {
        HashMap<String, Integer> roman = new HashMap<>();
        roman.put("I", 1);
        roman.put("II", 2);
        roman.put("III", 3);
        roman.put("IV", 4);
        roman.put("V", 5);
        roman.put("VI", 6);
        roman.put("VII", 7);
        roman.put("VIII", 8);
        roman.put("IX", 9);
        roman.put("X", 10);
        roman.put("XX", 20);
        roman.put("XXX", 30);
        roman.put("XL", 40);
        roman.put("L", 50);
        roman.put("LX", 60);
        roman.put("LXX", 70);
        roman.put("LXXX", 80);
        roman.put("XC", 90);
        roman.put("C", 100);
        return roman;
    }
    public static void validateIntegers(int a, int b) throws Exception {
        if (a > 10 || b > 10) {
            throw new Exception("Should be less than 10");
        }
        if (a < 1 || b < 1) {
            throw new Exception("Should be more than 0");
        }
    }
    public static int calc(String operator, int a, int b) {

        if (operator.equals("+")) {
            return a + b;
        } else if (operator.equals("-")) {
            return a - b;
        } else if (operator.equals("*")) {
            return a * b;
        } else if (operator.equals("/")) {
            return a / b;
        }
        return 0;
    }
    static int romanToArabic(String str, HashMap roman) {
        return Integer.parseInt(roman.get(str).toString());
    }
    public static String arabToRoman(int arab, HashMap roman) {

        int arabt = arab;
        ArrayList<Integer> result = new ArrayList<Integer>();
        while (arabt > 0) {
            result.add(arabt % 10);
            arabt = arabt / 10;
        }
        Collections.reverse(result);

        StringBuilder digit = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            int integ = result.get(i);
            if (arab < 10) {
                digit = new StringBuilder(Objects.requireNonNull(getKey(roman, integ)).toString());
            } else {
                if (i == 0) {
                    digit = new StringBuilder(Objects.requireNonNull(getKey(roman, integ * 10)).toString());
                } else {
                    if (integ != 0) {
                        digit.append(Objects.requireNonNull(getKey(roman, integ)).toString());
                    }
                }
            }
        }
        return digit.toString();
    }
    public static <K, V> K getKey(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (value.equals(entry.getValue())) {
                return entry.getKey();
            }
        }
        return null;
    }

    static boolean operandIsArabic(String ch) {
        int intValue;
        try {
            intValue = Integer.parseInt(ch);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    static boolean operandIsRoman(String ch) {
        try {
            Roman roman = Roman.valueOf(ch);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

   public static void parseOperator(String element) throws Exception {
        if (!element.equals("+") &&
                !element.equals("-") &&
                !element.equals("*") &&
                !element.equals("/"))
        {
            throw new Exception("Operator should be one of theese '+','-','*','/'");
        }
    }
    public static void parseOperands(String a, String b) throws Exception {
        if (!Objects.equals(parseOperand(a), parseOperand(b))) {
            throw new Exception("Operands of different types");
        }
    }
    static String parseOperand(String element) throws Exception {
        if (operandIsArabic(element)) {
            return "arab";
        } else if (operandIsRoman(element)) {
            return "roman";
        }else {
            throw new Exception("Opernads should be either arab or roman");
        }
    }


    public static void main(String[] args) throws Exception {

        int a = 0, b = 0;
        String inputType = "";

        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();
        scanner.close();
//        String expression = "IX / IV";

        String[] array = parseExpression(expression);
        HashMap roman = getRomanHashMap();

        if (array.length > 3) {
            throw new Exception("Format of operation doesn't meet requirements: two operand and one operator (+, -, /, *)");
        }

        parseOperator(array[1]);
        parseOperands(array[0],array[2]);

        if (operandIsArabic(array[0])) {
            a = Integer.parseInt(array[0]);
            b = Integer.parseInt(array[2]);
            inputType = "arab";
            validateIntegers(a, b);
        } else if (operandIsRoman(array[0])) {
            a = romanToArabic(array[0], roman);
            b = romanToArabic(array[2], roman);
            inputType = "roman";
            validateIntegers(a, b);
        } else {
            throw new Exception("Operands should be arabic or roman");
        }

        int result = calc(array[1], a, b);

        if (inputType.equals("roman")) {
            if (result == 0) throw new Exception("<=0");
            String romanResult = arabToRoman(result, roman);
            System.out.println(romanResult);
        } else {
            System.out.println(result);
        }
    }

}
