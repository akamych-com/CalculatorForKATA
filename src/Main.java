import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {

        System.out.println("Что вам посчитать, центурион?");

        Scanner input = new Scanner(System.in);
        String inputValue;

        while(true) {
            try {
                inputValue = input.nextLine();
                Main.checkBasicTests(inputValue);
            } catch (Exceptions.finalException e) {
                System.out.println("Это был твой последний шанс... ну... в этом запуске...");
                break;
            }
        }

    }

    public static void checkBasicTests(String input) throws Exceptions.finalException {

        try {

            if ( (input.contains(".")) || (input.contains(",")) ) {
                throw new Exceptions.noDecimalsException();
            }

            boolean romansInTown = false;

            String [] romanNumbersAllowed = {"I", "V", "X"};

            for (String romanNumber: romanNumbersAllowed) {
                if ( (input.contains(romanNumber)) || (input.contains(romanNumber.toLowerCase()))) {
                    romansInTown = true;
                    break;
                }
            }

            boolean arabsInTown = false;

            for(int i=0; i<10; i++) {
                if (input.contains(""+i+"")) {
                    arabsInTown = true;
                    break;
                }
            }

            if ( (romansInTown) && (arabsInTown) ) {
                throw new Exceptions.chooseYourCultureException();
            }

            if ( (!romansInTown) && (!arabsInTown) ) {
                throw new Exceptions.whatOperationIsException();
            }

            Main.calculate(input, arabsInTown);

        } catch (Exceptions.noDecimalsException|Exceptions.chooseYourCultureException|Exceptions.whatOperationIsException e) {
            throw new Exceptions.finalException();
        }

    }


    public static void calculate(String input, boolean arabic) throws Exceptions.finalException {

        String[] operations = {"+", "-", "*", "/"};
        String [] foundNumbers = null;
        String chosenOperator = null;

        for (String operator: operations) {
            if(input.contains(operator)) {
                foundNumbers = input.split("["+operator+"]");
                chosenOperator = operator;
                break;
            }
        }

        try {

            if (foundNumbers == null) {
                throw new Exceptions.whatOperationIsException();
            }

            if (foundNumbers.length != 2) {
                throw new Exceptions.noviceCalculatorException();
            }

            for (String operator: operations) {
                if ( (foundNumbers[0].contains(operator)) || (foundNumbers[1].contains(operator)) ) {
                    throw new Exceptions.noviceCalculatorException();
                }
            }

            Pattern onlyArabicAndSomeRomans = Pattern.compile("[^\\diIvVxX+*/-]");
            Matcher matcher1 = onlyArabicAndSomeRomans.matcher(foundNumbers[0]);
            Matcher matcher2 = onlyArabicAndSomeRomans.matcher(foundNumbers[1]);

            if ( matcher1.find() || matcher2.find()) {
                throw new Exceptions.smthUnknownException();
            }

            int number1, number2;

            if (arabic) {

                number1 = Integer.parseInt(foundNumbers[0]);
                number2 = Integer.parseInt(foundNumbers[1]);

            } else {

                try {
                    number1 = Main.convert2Arabic(foundNumbers[0]);
                    number2 = Main.convert2Arabic(foundNumbers[1]);
                } catch (Exceptions.smthRomanUnknownException e) {
                    throw new Exceptions.finalException();
                }

            }

            try {

                if( (number1 < 0) || (number1 > 10) || (number2 < 0) || (number2 > 10) ) {
                    throw new Exceptions.moreThan10LessThan0Exception();
                }

                if( (number2 == 0) && (chosenOperator.equals("/")) ) {
                    throw new Exceptions.noDivideBy0();
                }

                int result = switch (chosenOperator) {
                    case "+" -> number1 + number2;
                    case "-" -> number1 - number2;
                    case "*" -> number1 * number2;
                    case "/" -> number1 / number2;
                    default -> throw new Exceptions.whatOperationIsException();
                };

                if ( (!arabic) && (result <= 0) ) {
                    throw new Exceptions.romanNegativeResultException();
                }

                if (arabic) {
                    String hooray = "Результат вычисления: ";
                    System.out.println(hooray + result);
                } else {
                    String hooray = "Resultatus vychislenius: ";
                    System.out.println(hooray + Main.convert2Roman(result));
                }

            } catch (Exceptions.moreThan10LessThan0Exception|Exceptions.whatOperationIsException|Exceptions.romanNegativeResultException|Exceptions.noDivideBy0 e) {
                throw new Exceptions.finalException();
            }
            

        } catch (Exceptions.whatOperationIsException|Exceptions.noviceCalculatorException|Exceptions.smthUnknownException e) {
            throw new Exceptions.finalException();
        }



    }

    public static int convert2Arabic(String romanNumber) throws Exceptions.smthRomanUnknownException {

        return switch (romanNumber.toLowerCase()) {
            case "i" -> 1;
            case "ii" -> 2;
            case "iii" -> 3;
            case "iv" -> 4;
            case "v" -> 5;
            case "vi" -> 6;
            case "vii" -> 7;
            case "viii" -> 8;
            case "ix" -> 9;
            case "x" -> 10;
            default -> throw new Exceptions.smthRomanUnknownException();
        };

    }

    public static String convert2Roman(int arabicNumber) {

        String returnString;

        if (arabicNumber == 100) {
            returnString = "C";
        } else {

            int tens = arabicNumber/10;
            int left = arabicNumber - tens*10;

            returnString = Main.convert2RomanTens(tens) + Main.convert2RomanLeft(left);

        }

        return returnString;

    }

    public static String convert2RomanLeft(int arabicNumber) {

        return switch (arabicNumber) {
            case 1 -> "I";
            case 2 -> "II";
            case 3 -> "III";
            case 4 -> "IV";
            case 5 -> "V";
            case 6 -> "VI";
            case 7 -> "VII";
            case 8 -> "VIII";
            case 9 -> "IX";
            default -> "";
        };

    }

    public static String convert2RomanTens(int arabicNumber) {

        return switch (arabicNumber) {
            case 1 -> "X";
            case 2 -> "XX";
            case 3 -> "XXX";
            case 4 -> "XL";
            case 5 -> "L";
            case 6 -> "LX";
            case 7 -> "LXX";
            case 8 -> "LXXX";
            case 9 -> "XC";
            default -> "";
        };

    }


}
