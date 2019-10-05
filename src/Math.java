
class Math {


    // Gets number of days since January 1st year:1

    static int getNumDaysSinceOne (int month, int year) {
        int total = 0;
        for (int i = 1; i < year; i++)
            if (isLeapYear(i))
                total = total + 366;
            else
                total = total + 365;

        for (int i = 1; i < month; i++)
            total = total + getNumDaysInMonth(i, year);
        return total;

    }

    //Check if the year is a leap year
    private static boolean isLeapYear(int year){
        return (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0));
    }

    //Number od days in a month
    static int getNumDaysInMonth(int month, int year) {
        if (month == 1 || month == 3 || month == 5 || month == 7 ||
        month == 8 || month == 10 || month == 12)
            return 31;

        if (month == 4 || month == 6 || month == 9 || month == 11)
            return 30;

        if (month == 2)
            return isLeapYear(year) ? 29 : 28;

        return 0; // If month not correct
    }

    // Check if a string is numeric
    static boolean isNumeric(String strNum) {
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}


