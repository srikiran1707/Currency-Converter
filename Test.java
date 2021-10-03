
//author @srikiran1707
// Java program - currency converter
// using Java Swing
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DecimalFormat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Scanner;

public class Test {

//Function to fetch data from fixer.io API    
    public static double getData(String currency) throws Exception {
        var url = "http://data.fixer.io/api/latest?access_key=69cb123726c63fd2a36f2804e73bb0b7";
        var req = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var cli = HttpClient.newBuilder().build();
        var res = cli.send(req, HttpResponse.BodyHandlers.ofString());
        double temp = 0;
        for (String s : res.body().substring(res.body().lastIndexOf("{") + 1, res.body().indexOf("}")).split(",")) {
            if (currency.equals(s.substring(1, 4))) {
                temp = Double.parseDouble(s.substring(6));
            }
        }
        return temp;
    }
    
    
// Function to convert from EUR
// using Java Swing
    
    
    public static void converter() {

        // Creating a new frame using JFrame
        JFrame f = new JFrame("CONVERTER");

        // Creating two labels
        JLabel l1, l2;

        // Creating two text fields.
        // One for rupee and one for
        // the dollar
        JTextField t0, t1, t2;

        // Creating three buttons
        JButton b1, b3;

        // Naming the labels and setting
        // the bounds for the labels
        l1 = new JLabel("EUR");
        l1.setBounds(20, 40, 60, 30);

        t0 = new JTextField("INR");
        t0.setBounds(200, 40, 40, 30);

        // Initializing the text fields with
        // 0 by default and setting the
        // bounds for the text fields
        t1 = new JTextField("0");
        t1.setBounds(80, 40, 50, 30);

        t2 = new JTextField("0");
        t2.setBounds(240, 40, 60, 30);

        // Creating a button for EUR,
        // and one button to close
        // and setting the bounds
        b1 = new JButton("Calculate");
        b1.setBounds(50, 80, 100, 30);

        b3 = new JButton("close");
        b3.setBounds(150, 150, 100, 30);

        // Adding action listener
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Converting to double
                double d = Double.parseDouble(t1.getText());
                DecimalFormat df = new DecimalFormat("###.##");
                t0.setText(t0.getText());
                // Converting EUR to
                double d1 = 0;
                try {
                    d1 = d * getData(t0.getText());
                } catch (Exception e1) {

                    e1.printStackTrace();
                }

                // Getting the string value of the
                // calculated value
                String str1 = String.valueOf(df.format(d1));

                // Placing it in the text box
                t2.setText(str1);

            }
        });

        // Action listener to close the form
        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        // Default method for closing the frame
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        // Adding the created objects
        // to the form
        f.add(l1);
        f.add(t0);
        f.add(t1);
        f.add(t2);
        f.add(b1);
        f.add(b3);

        f.setLayout(null);
        f.setSize(400, 300);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        converter();
    }
}
