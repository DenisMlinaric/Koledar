import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;


public class Main {

    static String[] monthNames = {"Izberi mesec","januar", "februar", "marec", "april", "maj", "junij", "julij", "avgust", "september", "oktober", "november", "december"};

    static private Calendar cal = Calendar.getInstance();

    //Current date
    static private int year=cal.get(Calendar.YEAR);
    static private int month=cal.get(Calendar.MONTH) +1 ;


    //Tables for holydays
    static ArrayList<String> days = new ArrayList<>();
    static ArrayList<String> months = new ArrayList<>();
    static ArrayList<String> years = new ArrayList<>();



    public static void main(String[] args) throws IOException {

        FileReader r = new FileReader("prazniki.txt");
        BufferedReader br = new BufferedReader(r);
        int ch;

        int i = 0;
        String temp ="";


        //Read file and fill days, months, years for holidays, Usage at the moment only for this type of file

        while ((ch = br.read()) != -1 ){

            if ((char)ch == ("/").charAt(0)) {
                if (i==0) days.add(temp);
                if (i==1) months.add(temp);
                temp="";
                i++;
            }

            if ((char)ch != ("/").charAt(0) && (char)ch != (",").charAt(0) ) {
                temp = temp + Character.toString((char) ch) ; }

            if ((char)ch == (",").charAt(0)) {
                years.add(temp);
                i=0;
                temp="";
            }
        }
        years.add(temp);   // Last year doesn't get added inside the while loop, but is retained in temp


        Panel panel = new Panel(month, year);


        JComboBox<String> combo = new JComboBox<>(monthNames); // Combo box for selecting Month
        JTextField textBoxYear = new JTextField("leto",5); // Text box for selecting Year
        JTextField textBoxDate = new JTextField("dd/mm/llll", 10); // Text box for selecting Date
        JButton buttonDate=new JButton("Izberi točen datum"); // Button on first screen option
        JButton buttonMonthYear=new JButton("Izberi mesec in leto"); // Button on first screen option

        JButton buttonSubmitDate = new JButton("Izberi");   // Submit buttons on second screen for each option
        JButton buttonSubmitMonthYear = new JButton("Izberi");

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());



        frame.setVisible(true);
        frame.setSize(400, 400);
        frame.setResizable(false);

        frame.add(buttonDate);
        frame.add(buttonMonthYear);
        frame.add(panel);


        // Error frame in case of false user input
        JFrame napaka = new JFrame();
        napaka.setSize(150,100);
        JPanel panelNapaka = new JPanel();
        JLabel textNapaka = new JLabel("Napačen vnos");
        napaka.setLocation(125, 100);
        panelNapaka.add(textNapaka);
        napaka.add(panelNapaka);


        buttonMonthYear.addActionListener(arg0 -> {  // Button "Izberi mesec in leto"

            frame.getContentPane().removeAll();
            napaka.setVisible(false);

            Panel panel2 = new Panel(month, year);

            frame.add(combo);
            frame.add(textBoxYear);
            frame.add(buttonSubmitMonthYear);

            frame.getContentPane().add(panel2);

            frame.revalidate();
            frame.repaint();

        });

        buttonDate.addActionListener(arg0 -> { // Button "Izberi datum"

            frame.getContentPane().removeAll();
            napaka.setVisible(false);

            Panel panel2 = new Panel(month, year);

            frame.add(textBoxDate);
            frame.add(buttonSubmitDate);
            frame.getContentPane().add(panel2);

            frame.revalidate();
            frame.repaint();

        });


        buttonSubmitDate.addActionListener(arg0 -> {  // Button "izberi" for option date


            // Check if TextBox date is filled correctly, switch to correct date or back to to first screen
            if (!textBoxDate.getText().trim().isEmpty()&& getYearFromDateString(textBoxDate.getText()) != -1
                && getMonthFromDateString(textBoxDate.getText()) < 13 && getMonthFromDateString(textBoxDate.getText()) > 0 &&
                    getYearFromDateString(textBoxDate.getText()) > 0 &&  getDayFromDateString(textBoxDate.getText()) > 0 &&
                    getDayFromDateString(textBoxDate.getText()) <= Math.getNumDaysInMonth(getMonthFromDateString(textBoxDate.getText()),getYearFromDateString(textBoxDate.getText()))
                    )
             {

                year = getYearFromDateString(textBoxDate.getText());
                month = getMonthFromDateString(textBoxDate.getText());

                frame.getContentPane().removeAll();

                Panel panel2 = new Panel(month, year);
                frame.add(buttonDate);
                frame.add(buttonMonthYear);
                frame.getContentPane().add(panel2);
                frame.revalidate();
                frame.repaint();
            }

            else {
                frame.getContentPane().removeAll();

                Panel panel2 = new Panel(cal.get(Calendar.MONTH) +1, cal.get(Calendar.YEAR));

                frame.add(buttonDate);
                frame.add(buttonMonthYear);
                frame.getContentPane().add(panel2);

                frame.revalidate();
                frame.repaint();
                napaka.setVisible(true);
            }
        });

        buttonSubmitMonthYear.addActionListener(arg0 -> {

            frame.getContentPane().removeAll();



            // Check if TextBox year and ComboBox month are selected correctly, switch to correct date or back to first screen
            if (!textBoxYear.getText().trim().isEmpty() && (combo.getSelectedItem()!= "Izberi mesec")){

                if (Objects.equals(combo.getSelectedItem(), "januar")) month = 1;
                if (Objects.equals(combo.getSelectedItem(), "februar")) month = 2;
                if (Objects.equals(combo.getSelectedItem(), "marec")) month = 3;
                if (Objects.equals(combo.getSelectedItem(), "april")) month = 4;
                if (Objects.equals(combo.getSelectedItem(), "maj")) month = 5;
                if (Objects.equals(combo.getSelectedItem(), "junij")) month = 6;
                if (Objects.equals(combo.getSelectedItem(), "julij")) month = 7;
                if (Objects.equals(combo.getSelectedItem(), "avgust")) month = 8;
                if (Objects.equals(combo.getSelectedItem(), "september")) month = 9;
                if (Objects.equals(combo.getSelectedItem(), "oktober")) month = 10;
                if (Objects.equals(combo.getSelectedItem(), "november")) month = 11;
                if (Objects.equals(combo.getSelectedItem(), "december")) month = 12;
                year = Integer.valueOf(textBoxYear.getText());

                Panel panel2 = new Panel(month, year);

                frame.add(buttonDate);
                frame.add(buttonMonthYear);
                frame.getContentPane().add(panel2);
                frame.revalidate();
                frame.repaint();
            }

            else {
                frame.getContentPane().removeAll();
                Panel panel2 = new Panel(cal.get(Calendar.MONTH) +1, cal.get(Calendar.YEAR));
                frame.add(buttonDate);
                frame.add(buttonMonthYear);
                frame.getContentPane().add(panel2);
                frame.revalidate();
                frame.repaint();
                napaka.setVisible(true);
            }
       });
    }


    // Get the month from TextBox Date

    private static int getMonthFromDateString(String textBoxDate) {
        if (!Math.isNumeric(textBoxDate.substring(3,5))) { return -1;}
        else return Integer.valueOf(textBoxDate.substring(3,5));

    }


    // Get the year from TextBox Date
    private static int getYearFromDateString(String textBoxDate) {
        if (!Math.isNumeric(((textBoxDate.substring(6))))) { return -1;}
        else return Integer.valueOf(textBoxDate.substring(6));
    }

    // Get the day from TextBox Date
    private static int getDayFromDateString(String textBoxDate) {
        if (!Math.isNumeric(((textBoxDate.substring(0,2))))) { return -1;}
        else return Integer.valueOf(textBoxDate.substring(0,2));
    }

}
