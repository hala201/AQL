package controller.helper;

public class StringParser {

    public static String stripQuotes(String text) {
        if ((text.startsWith("\"") && text.endsWith("\"")) || (text.startsWith("\'") && text.endsWith("\'"))) {
            return text.substring(1, text.length() - 1);
        }
        return text;
    }

    // NOTE: we might need to deal with more data type (boolean, etc.)
    public static Object parseValue(String text) {
        try {
            if (text.matches("-?\\d+")) {
                return Double.parseDouble(text);
            } else if (text.matches("-?\\d+\\.\\d+")) {
                return Double.parseDouble(text);
            }
        } catch (NumberFormatException e) {
            // if not a number, just return text
        }
        return text;
    }
}
