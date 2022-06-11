public class Exceptions {

    public static class noDecimalsException extends Exception {
        public noDecimalsException () {
            System.out.println("Не-не-не, Дэвид Блэйн, никаких десятичных!");
        }
    }

    public static class chooseYourCultureException extends Exception {
        public chooseYourCultureException () {
            System.out.println("Не, ну вы определитесь, пожалуйста: вы к арабам или к римлянам?");
        }
    }
    public static class whatOperationIsException extends Exception {
        public whatOperationIsException () {
            System.out.println("Ммм... А что мне делать с этим? Я калькулятор, но могу подсказать хорошего психотерапевта, если хочется просто поговорить.");
        }
    }
    public static class noviceCalculatorException extends Exception {
        public noviceCalculatorException () {
            System.out.println("Вообще, я по образованию хирург, поэтому не могу делать больше одной операции одновременно.");
        }
    }
    public static class smthUnknownException extends Exception {
        public smthUnknownException () {
            System.out.println("Я не понимаю эти ваши знаки - прояснитесь!");
        }
    }
    public static class smthRomanUnknownException extends Exception {
        public smthRomanUnknownException () {
            System.out.println("Простите, но я по-римски только с числами до 10 включительно умею обращаться.");
        }
    }
    public static class moreThan10LessThan0Exception extends Exception {
        public moreThan10LessThan0Exception () {
            System.out.println("Простите, но я вообще только с числами до 10 умею обращаться.");
        }
    }
    public static class romanNegativeResultException extends Exception {
        public romanNegativeResultException () {
            System.out.println("Пардонус: нетус романус негативус намберус ор нулевус ин природус!");
        }
    }
    public static class noDivideBy0 extends Exception {
        public noDivideBy0 () {
            System.out.println("Ну серьёзно?! Давайте всё делить на мою текущую зарплату!");
        }
    }


    public static class finalException extends Exception {
        public finalException () {}
    }



}
