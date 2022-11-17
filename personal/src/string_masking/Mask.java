package string_masking;

/* Code by harrison twitter : _foreverchild
*/
public class Mask {
    private static String maskPhoneString(String strText) throws Exception {

            if(strText!=null) {

                int start = 3;
                int end = 7;
                int maskLength = 0;
                String maskString;

                if (!(strText.length() == 11)) return "Invalid Phone Number";
                maskLength = end - start;

                maskString = "*".repeat(maskLength);
                return strText.substring(0, start)
                        + "*".repeat(maskLength)
                        + strText.substring(start + maskLength);
            }
            return ("Value cannot be null");
    }

    private static String maskEmailString(String strText) throws Exception {

        try {

            if (strText != null) {
                if (strText.length() <= 2 || (!strText.contains("@"))) return "invalid email";
                int start = 3;

                String[] firstString = strText.split("@");
                int end = firstString[0].length();

                if (firstString[0].length() < 1) return "invalid email";

                int maskLength = end - start;
                // for even condition
                if (firstString[0].length() % 2 == 0) {
                    maskLength = (Math.abs(firstString[0].length() / 2));
                    start = (maskLength / 2);
                    end = firstString[0].length() - (maskLength / 2);
                }
                if (firstString[0].length() % 2 == 1) {
                    maskLength = (Math.abs(firstString[0].length() / 2));
                    int adjuster = maskLength / 2;
                    start = (maskLength - adjuster);
                    end = start + maskLength;
                }

                String sbMaskString = "*".repeat(maskLength);

                return firstString[0].substring(0, start)
                        + sbMaskString
                        + firstString[0].substring(end)
                        + "@" + firstString[1];
            }

//        }return "value cannot be nullll";

        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("wrong mask length");

        }
        return "Incorrect value for email "+ strText;
    }

    public static String maskAll(String data) throws Exception {
        String value, result;
        try {

        value = (data.contains("@")) ? "EMAIL" : "NUMBER";

        switch (value){
            case "EMAIL":
                result = maskEmailString(data);
                break;
            case "NUMBER":
                result = maskPhoneString(data);
                break;
            default:
                return "";
        }}catch (NullPointerException e){
            return new NullPointerException("Value cannot be null").getMessage() ;
        }
        return result;
    }


    public static void main(String[] args) throws Exception {
        System.out.println(maskAll(null));
        System.out.println(maskAll(""));
        System.out.println(maskAll("a"));
        System.out.println(maskAll("ad"));
        System.out.println(maskAll("adr"));
        System.out.println(maskAll("4444"));
        System.out.println(maskPhoneString(""));
        System.out.println(maskPhoneString(null));
        System.out.println(maskPhoneString("a"));
        System.out.println(maskPhoneString("3"));
        System.out.println(maskPhoneString("34343434"));
        System.out.println(maskPhoneString("09078674578"));
        System.out.println(maskEmailString(""));
        System.out.println(maskEmailString("d"));
        System.out.println(maskEmailString("dd"));
        System.out.println(maskEmailString(null));
        System.out.println(maskEmailString("harry@gmail.com"));
        System.out.println(maskAll(""));

    }
}
