import javax.swing.*;
import java.awt.*;

public class Grafiksimpel extends Canvas {
    public Grafiksimpel() {
        JFrame frame = new JFrame("A new painting");
        this.setSize(800,600);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void paint(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0,500, 800, 100 );
        drawTree(g, -60, 240);
        drawTree(g, 0, 240);
        drawTree(g, 50, 240);
        drawTree(g, 100, 240);
        drawTree(g, 150, 240);
        drawTree(g, 190, 240);
        drawTree(g, 267, 240);
        drawTree(g, 328, 240);
        drawTree(g, 400, 240);
        drawTree(g, 467, 240);
        drawTree(g, 530, 240);
        drawTree(g, 600, 240);
        drawTree(g, 640, 240);
        drawTree(g, 700, 240);
        drawHomper(g, 500, 200);
    }

    private void drawTree(Graphics g, int x, int y){
        g.setColor(new Color(160, 82, 45));
        g.fillRect(x+55, y+40, 40,220);
        g.setColor(new Color(34, 139, 34));
        g.fillOval(x+20, y+20, 100, 100);
        g.fillOval(x, y+20, 60, 60);
        g.fillOval(x+80, y+80, 60, 60);
        g.fillOval(x+10, y+70, 70, 70);
    }

    private void drawHomper(Graphics g , int x, int y) {
        g.setColor(Color.yellow);
        g.fillRect(x+50, y+50, 150, 400); //Head
        g.fillOval(x+50,y,150,100);          //Head
        g.setColor(Color.white);
        g.fillOval(x, y+100, 80, 80);        //Eyes
        g.fillOval(x+70, y+100, 80, 80);  //Eyes
        g.setColor(new Color(139, 69, 19));
        g.fillOval(x, y+190, 140, 140);      //Beard
        g.setColor(Color.yellow);
        g.fillRect(x+20, y+165, 140, 40); //Nose
        g.fillOval(x, y+165, 40,40);         //Nose
        g.setColor(Color.BLACK);
        g.fillOval(x+20, y+120, 40, 40);  //pupils
        g.fillOval(x+90, y+120, 40, 40);  //pupils
        g.fillRect(x, y+255, 80, 10);
    }

    public static void main(String[] args) {
        Grafiksimpel painting = new Grafiksimpel();
    }
}
