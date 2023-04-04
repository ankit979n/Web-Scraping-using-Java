package org.project;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document; //for storing HTML document
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.util.Locale;
import java.util.Scanner;

public class App {

    public static String getdata(String c)throws Exception {
        StringBuffer br = new StringBuffer();
        br.append("<html>"+"<body style='text-align:center'>");
        br.append(c.toUpperCase()+ "<br>");
        String url = "https://www.worldometers.info/coronavirus//country/"+c+"/";
        Document doc = Jsoup.connect(url).get();   //this will give you HTML document
        //System.out.println(doc.title());
        Elements elements = doc.select("#maincounter-wrap");
        //System.out.println(elements.html());
        //Extracting the 3 required elements:
        elements.forEach((e)->{
            String text = e.select("h1").text(); // for getting the header <h1> text
            String count = e.select(".maincounter-number>span").text(); //for getting the text at "maincounter-number" inside span
            //System.out.println(text+" "+count);
            br.append(text).append(count).append("<br>");
        });
        br.append("</body>" + "</html>");
        return br.toString();// as it's return type is string
    }
    public static void main( String[] args ) throws Exception{
/*
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Country:");
        String co = sc.nextLine();
        System.out.println(getdata(co));
*/
        //size of GUI:
        JFrame root = new JFrame("COVID19 Details of the country");
        root.setSize(500,500);
        Font f = new Font("poppins",Font.BOLD,30);// creating the font
        //textfield: where you write country wise name:
        JTextField field = new JTextField();
        //label:
        JLabel dataL = new JLabel();

        field.setFont(f);  //setting the font
        dataL.setFont(f);  //setting the label
        field.setHorizontalAlignment(SwingConstants.CENTER);//for typing the text at centre
        dataL.setHorizontalAlignment(SwingConstants.CENTER);// for printing the details in centre

        //creating get button:
        JButton button = new JButton("Get detail");
        button.setFont(f);
        //for listner
        button.addActionListener(event->{
            try {
                //code will run after the click on "get detail"
                String maindata = field.getText();
                String result = getdata(maindata); // passing the "maindata" to "getdata" method
                dataL.setText(result);
            }catch (Exception e){
                e.printStackTrace();
            }
        });

        //Adding these 3 in our frame:
         //first set layout:
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);//adding text in the top (NORTH)
        //middle part will be data:
        root.add(dataL);//If you didn't mention position it will be placed at centre
        // root.add(dataL, BorderLayout.CENTER);
        root.add(button, BorderLayout.SOUTH);//Adding button at bottom(SOUTH)

        root.setVisible(true);
    }

}
