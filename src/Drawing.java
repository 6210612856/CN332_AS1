import java.awt.Canvas;
import java.awt.Graphics;
import java.io.*;
import java.util.*;


import javax.swing.JFrame;
import java.util.Scanner;

public class Drawing extends Canvas {

    String[] a = {"1","1","1","square","5","","","","","","red"};
    String[] b = {"2","1","2","circle","10","","","","","","green"};
    String[] c = {"3","100","100","rectangle","40","50","","","","","blue"};
    String[] d = {"4","50","50","triangle","10","10","20","20","10","0","white"};
    String[][] shapeTest = {a,b,c,d};
    

    public static void main(String[] args) {

        JFrame frame = new JFrame("My Drawing");
        Canvas canvas = new Drawing();
        canvas.setSize(1000, 1000);
        frame.add(canvas);
        frame.pack();
        frame.setVisible(true);
        
    }

    public void paint(Graphics g) {

        String fileName= "C:\\Users\\Fille's PC\\Desktop\\Java\\TestShape\\src\\database.csv";
        File file= new File(fileName);

        List<List<String>> lines = new ArrayList<>();
        Scanner inputStream;

        try{
            inputStream = new Scanner(file);

            while(inputStream.hasNext()){
                String line= inputStream.next();
                String[] values = line.split(",");

                lines.add(Arrays.asList(values));
            }

            inputStream.close();
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (List<String> line : lines){
            String[] i = line.toArray(new String[line.size()]);

            if (i[3].equals("square")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillRect(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4]), Integer.parseInt(i[4]));
            }
            else if (i[3].equals("circle")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillOval(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4])/2, Integer.parseInt(i[4])/2);
            }
            else if (i[3].equals("triangle")) {
                int xLoc = Integer.parseInt(i[1]);
                int yLoc = Integer.parseInt(i[2]);
                int[] x = {Integer.parseInt(i[4] + xLoc), Integer.parseInt(i[6] + xLoc), Integer.parseInt(i[8] + xLoc)};
                int[] y = {Integer.parseInt(i[5] + yLoc), Integer.parseInt(i[7] + yLoc), Integer.parseInt(i[9] + yLoc)};
                g.setColor(MyColor.getColor(i[10]));
                g.fillPolygon(x, y, 3);
            }
            else if (i[3].equals("rectangle")) {
                g.setColor(MyColor.getColor(i[10]));
                g.fillRect(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[4]), Integer.parseInt(i[5]));
            }


        }

    }
}
