import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;


public class Grafik extends Canvas implements Runnable {

    private Thread thread;
    int fps = 30;
    private boolean isRunning;

    private BufferStrategy bs;
    //private BufferedImage image;

    private int Frogx, Frogy;
    private int Frogxv, Frogyv;
    private int Carx[] = new int[54];
    private int Cary[] = new int[54];
    private int Red[] = new int[54];
    private int Green[] = new int[54];
    private int Blue[] = new int[54];
    private int i, n, t;
    private int Score, carV;
    private int sneak;
    private boolean Alive, Wc, Ac, Sc, Dc = true;

    public Grafik() {
        JFrame frame = new JFrame("A new painting");
        this.setSize(800, 600);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.addKeyListener(new KL());
        frame.setVisible(true);

        isRunning = false;

        Frogx = 400;
        Frogy = 550;
        Ac = true;
        Wc = true;
        Dc = true;
        Sc = true;
        t=0;

        sneak=1;
        for (i=0; i<Cary.length; i++) {
            if (i < 6){
                Cary[i]= 94;
            } else if (i < 12){
                Cary[i]= 141;
            } else if (i < 18){
                Cary[i]= 188;
            } else if (i < 24){
                Cary[i]= 235;
            } else if (i < 30){
                Cary[i]= 282;
            } else if (i < 36){
                Cary[i]= 329;
            } else if (i < 42){
                Cary[i]= 376;
            } else if (i < 48){
                Cary[i]= 423;
            } else {
                Cary[i]= 470;
            }
        }
        for (i=0; i<Carx.length; i++) {
                Carx[i]= (int) Math.round(Math.random()*940)-70;
        }
        for (i=0; i<Red.length; i++) {
            Red[i] = (int) Math.round(Math.random()*100)+80;
            Green[i] = (int) Math.round(Math.random()*100)+80;
            Blue[i] = (int) Math.round(Math.random()*100)+80;
        }
        carV = 1; //hasigheten på bilarna

        Score = 0;


    }
    public void update() {
        if (Frogy <= 10) {
            Frogy = 550;
            Score += 1000*carV;
            if (carV<11) {
                carV += 1;
            }
            System.out.println(sneak);
            for (i=0; i<Carx.length; i++) {
                Carx[i]= (int) Math.round(Math.random()*940)-70;
                Red[i] = (int) Math.round(Math.random()*100)+80;
                Green[i] = (int) Math.round(Math.random()*100)+80;
                Blue[i] = (int) Math.round(Math.random()*100)+80;
            }
        }

        if ( 0 < Frogx && Frogxv<0) {
        Frogx += (Frogxv / sneak);
        }
        if (760 > Frogx && Frogxv>0) {
            Frogx += (Frogxv / sneak);
        }
        if ( 550 > Frogy && Frogyv>0) {
        Frogy += (Frogyv / sneak);
        }
        if ( 0 < Frogy && Frogyv<0) {
            Frogy += (Frogyv / sneak);
        }


        n=0;
        if (t==2) {
        while (n<Carx.length) {
            if (Cary[n] == 94 || Cary[n] == 282) {
                Carx[n] -= carV+1;
            } else  if (Cary[n] == 376 || Cary[n] == 188) {
                Carx[n] -= carV + 2;
            } else if (Cary[n] == 470 || Cary[n] == 141) {
                Carx[n] += carV;
            } else if (Cary[n] == 235){
                Carx[n] += carV+1;
            } else {
                Carx[n] -= carV;
            }
            if (Carx[n] <=-70) {
                Carx[n] = 870;
            } else if (Carx[n] >= 871){
                Carx[n] = -70;
            }
            n++;
        }
        t=0;
        }
        t++;

    }


    public void draw() {
        bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();

        update();
        g.setColor(new Color(200,255,255));
        g.setColor(Color.DARK_GRAY);
        g.fillRect(0,0,800,600);
        g.setColor(Color.gray);
        g.fillRect(0,550, 800, 50 );
        g.fillRect(0,0,800,80);
        for (i=0; i<Cary.length; i++) {
            drawCarleft1(g, Carx[i], Cary[i]);
        }
        drawFrog(g, Frogx,Frogy);

        g.dispose();
        bs.show();
    }

    private void drawCarleft1(Graphics g, int x, int y) {
        g.setColor(Color.black);
        g.fillRect(x + 10, y, 15, 34);
        g.fillRect(x + 45, y, 15, 34);
        g.setColor(new Color(Red[i], Green[i], Blue[i]));
        g.fillRect(x, y + 2, 70, 30);
        g.setColor(new Color(0, 160, 255));
        if (y == 470 || y == 141 || y == 235) {
            g.fillRect(x + 50, y + 5, 15, 24);
        } else {
            g.fillRect(x + 8, y + 5, 15, 24);
        }
    }


    private void drawFrog(Graphics g , int x, int y) {
        g.setColor(new Color(0,153,0));
        g.fillOval(x+5,y,8,15);
        g.fillOval(x+28,y,8,15);
        g.setColor(new Color(0,204,0));
        g.fillOval(x+5,y+3,31, 35);

    }


    public static void main(String[] args) {
        Grafik painting = new Grafik();
        painting.start();
    }

    public synchronized void start(){
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        double deltaT = 1000.0/fps;
        long lastTime = System.currentTimeMillis();

        while (isRunning) {
            long now = System.currentTimeMillis();
            if (now-lastTime > deltaT) {
                update();
                draw();
                lastTime=now;
            }
        }
        stop();
    }
    private  class  KL implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
            if (e.getKeyChar()== 'a' || e.getKeyChar()== 'A') {
                if (Ac) {
                    Frogxv += -6;
                    Ac=false;
                }
            }
            if (e.getKeyChar()== 'd' || e.getKeyChar()== 'D') {
                if (Dc) {
                    Frogxv += 6;
                    Dc = false;
                }
            }
            if (e.getKeyChar()== 'w' || e.getKeyChar()== 'W') {
                if (Wc) {
                    Frogyv += -6;
                    Wc = false;
                }
            }
            if (e.getKeyChar()== 's' || e.getKeyChar()== 'S') {
                if (Sc) {
                    Frogyv += 6;
                    Sc = false;
                }
            }
            if (e.getKeyChar()== 'p') {
                sneak = 2;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {
            if (e.getKeyChar()== 'a' || e.getKeyChar()== 'A') {
                    Frogxv += 6;
                    Ac = true;
            }
            if (e.getKeyChar()== 'd' || e.getKeyChar()== 'D') {
                Frogxv += -6;
                Dc=true;
            }
            if (e.getKeyChar()== 'w' || e.getKeyChar()== 'W') {
                Frogyv += 6;
                Wc=true;
            }
            if (e.getKeyChar()== 's' || e.getKeyChar()== 'S') {
                Frogyv += -6;
                Sc=true;
            }
            if (e.getKeyChar()== 'p') {
                sneak = 1;
            }
        }
        }
    }

