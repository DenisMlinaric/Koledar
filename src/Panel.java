import javax.swing.*;
import java.awt.*;

import static java.awt.Color.gray;
import static java.awt.Color.lightGray;
import static java.awt.Color.red;

class Panel extends JPanel {
    private int month, year;
    private String[] dayNames = {"P", "T", "S", "Č", "P", "S", "N"};


    // Main calendar Panel
    Panel(int month, int year) {
        this.month = month;
        this.year = year;
        JPanel Panel = new JPanel();
        Panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();


        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 0.5;
        c.weighty = 0.5;
        Panel.add(makeTitle(),c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.5;
        c.weighty = 0.5;

        Panel.add(makeDays(),c);


        this.add(Panel,c);

    }

    // Title Panel
    private JPanel makeTitle() {

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel label = new JLabel(getMonthName(month) + " " + year);
        label.setForeground(SystemColor.activeCaption);
        titlePanel.add(label, BorderLayout.CENTER);
        return titlePanel;

    }

    //Get the name of the month e.g. 1 -> "Januar"
    private String getMonthName(int month) {
        return Main.monthNames[month];
    }



    private JPanel makeDays() {
        JPanel dayPanel = new JPanel();

        // Makes the first row of te calendar the day names

        for (String dayName : dayNames) {
            JPanel dPanel = new JPanel(true);
            //dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dLabel = new JLabel(dayName);
            dPanel.add(dLabel);
            dayPanel.add(dPanel);
        }



        dayPanel.setLayout(new GridLayout(0, 7, 2, 2));

        int count = (Math.getNumDaysSinceOne(month, year) % 7) -1;

        // Makes the day numbers grid and colors and numbers

        for (int i = -count; i < maxLabels(Math.getNumDaysInMonth(month, year), count) -count; i++) {
            JPanel dPanel = new JPanel(true);
            dPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            JLabel dayLabel = new JLabel();

            // Default Panel and Text Style
            dPanel.setBackground(Color.white);
            dayLabel.setForeground(Color.black);


            // Sundays have background color gray
            if ((i  + 1 + count) % 7 == 0) {
                dPanel.setBackground(gray);
                dayLabel.setForeground(red);
            }

            // Saturdays have background color light gray
            if (((i  + 1 + count) % 7) -6 == 0) {
                dPanel.setBackground(lightGray);
            }

            // Check if the day is a holiday adn set the color to gray
            for (int j= 0; j<Main.years.size(); j++) {
                if (  i == Integer.valueOf(Main.days.get(j)) && Integer.valueOf(Main.months.get(j)) == month &&
                        (Main.years.get(j).equals("P") || Integer.valueOf(Main.years.get(j)) == year)
                ) {dPanel.setBackground(gray); dayLabel.setForeground(red);}
            }


            // Sets day numbers or empty spaces

            dayLabel.setText("");

            if (i>0){
                dayLabel.setText(Integer.toString(i));
            }

            if(i>Math.getNumDaysInMonth(month, year)){
                dayLabel.setText("");
            }

            dPanel.add(dayLabel);
            dayPanel.add(dPanel);
        }

        return dayPanel;
    }

    private int maxLabels(int days, int count) {
        if (days == 30 && count > 4 || days == 31 && count > 3) return 42;
        else if (days == 30 || days == 31 || days == 29 || days == 28 && count > -1) return 35;
        else return 28;
    }


}
