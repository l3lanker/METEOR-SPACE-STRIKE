package gamem;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

class mode extends JFrame {
    int mode = 0, p = 5, score = 0, l = 4;
    int time = 60;
    int po = 75, speed = 16;
    int[] y = {600, 600, 600, 600, 600, 600, 600, 600, 600, 600, 600, 600};
    int[] lhp = {600, 650, 600, 700, 650};
    Timer t = new Timer(1000, new time());
    Timer speedam = new Timer(speed, new rand());
    Thread plusspeed = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(9999);
                } catch (InterruptedException e) {
                }
                p++;
                score++;
            }
        }
    });

    class time implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            time--;
            if (time <= 60 && time > 35) { // Initial period, use a1 and b1
                mode = 2;
            } else if (time <= 35) { // Change to a2 and b2, increase speed by 1
                mode = 2;
                speedam.setDelay(speed - 1);
            }
            if (time == 0) {
                t.stop();
                speedam.stop();
                plusspeed.interrupt();
                mode = 3;
            }
            repaint();
        }
    }

    class rand implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (po) {
                case 75:
                    for (int i = 0; i < 3; i++) {
                        if (y[i] < 350 && y[i] > 335) {
                            y[i] = (int) (Math.random() * -1 * 800 % 800);
                            lhp[l] = -30;
                            l--;
                        }
                    }
                    break;
                case 225:
                    for (int i = 3; i < 6; i++) {
                        if (y[i] < 350 && y[i] > 335) {
                            y[i] = (int) (Math.random() * -1 * 800 % 800);
                            lhp[l] = -30;
                            l--;
                        }
                    }
                    break;
                case 375:
                    for (int i = 6; i < 9; i++) {
                        if (y[i] < 350 && y[i] > 335) {
                            y[i] = (int) (Math.random() * -1 * 800 % 800);
                            lhp[l] = -30;
                            l--;
                        }
                    }
                    break;
                case 525:
                    for (int i = 9; i < 12; i++) {
                        if (y[i] < 350 && y[i] > 335) {
                            y[i] = (int) (Math.random() * -1 * 800 % 800);
                            lhp[l] = -30;
                            l--;
                        }
                    }
                    break;
                default:
                    break;
            }
            for (int i = 0; i < y.length; i++) {
                y[i] += p;
                if (y[i] > 750) {
                    y[i] = (int) (Math.random() * -1 * 600 % 600);
                }
            }
            if (l == -1) {
                mode = 3;
                time--;
                t.stop();
                speedam.stop();
                plusspeed.interrupt();
            }
            repaint();
        }
    }

    class gameplay extends JPanel {
        ImageIcon a1 = new ImageIcon(this.getClass().getResource("/images/meteor.png"));
        ImageIcon a2 = new ImageIcon(this.getClass().getResource("/images/meteor_hard.png"));
        ImageIcon b1 = new ImageIcon(this.getClass().getResource("/images/bg.png"));
        ImageIcon b2 = new ImageIcon(this.getClass().getResource("/images/bg_hard.png"));
        ImageIcon start = new ImageIcon(this.getClass().getResource("/images/start.png"));
        ImageIcon player = new ImageIcon(this.getClass().getResource("/images/ship.png"));
        ImageIcon HP = new ImageIcon(this.getClass().getResource("/images/hearts.png"));
        ImageIcon n = new ImageIcon(this.getClass().getResource("/images/end.png"));
        ImageIcon A = new ImageIcon(this.getClass().getResource("/images/a.png"));
        ImageIcon B = new ImageIcon(this.getClass().getResource("/images/b.png"));
        ImageIcon C = new ImageIcon(this.getClass().getResource("/images/c.png"));
        ImageIcon D = new ImageIcon(this.getClass().getResource("/images/d.png"));
        ImageIcon E = new ImageIcon(this.getClass().getResource("/images/e.png"));
        ImageIcon F = new ImageIcon(this.getClass().getResource("/images/f.png"));
        ImageIcon[] ls = {F, E, D, C, B, A, A};
        Font f = new Font("Arial", Font.BOLD, 25);
        JButton b = new JButton("Start");

        Label l = new Label();

        gameplay() {
            setLayout(null);
            add(b);
            b.setFont(f);
            b.setBounds(307, 300, 120, 80);
            setSize(601, 500);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    remove(b);
                    l.setFont(f);
                    l.setBounds(635, 280, 30, 50);
                    add(l);
                    mode = 2;
                    t.start();
                    speedam.start();
                    plusspeed.start();
                    setKeyListener();
                }
            });

            setFocusable(true);
        }

        private void setKeyListener() {
            addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    if (mode == 2) {
                        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                            if (po < 450) {
                                po += 150;
                                repaint();
                            }
                        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                            if (po != 75) {
                                po -= 150;
                                repaint();
                            }
                        }
                    }
                }
            });
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mode == 2) {
                l.setText(String.valueOf(time));
                if (time <= 35) { // Use b2 image and a2 image after 30 seconds
                    g.drawImage(b2.getImage(), 0, 0, this);
                    g.drawImage(a2.getImage(), 25, y[0], 70, 70, this);
                    g.drawImage(a2.getImage(), 25, y[1], 70, 70, this);
                    g.drawImage(a2.getImage(), 25, y[2], 70, 70, this);
                    g.drawImage(a2.getImage(), 200, y[3], 70, 70, this);
                    g.drawImage(a2.getImage(), 200, y[4], 70, 70, this);
                    g.drawImage(a2.getImage(), 200, y[5], 70, 70, this);
                    g.drawImage(a2.getImage(), 350, y[6], 70, 70, this);
                    g.drawImage(a2.getImage(), 350, y[7], 70, 70, this);
                    g.drawImage(a2.getImage(), 350, y[8], 70, 70, this);
                    g.drawImage(a2.getImage(), 500, y[9], 70, 70, this);
                    g.drawImage(a2.getImage(), 500, y[10], 70, 70, this);
                    g.drawImage(a2.getImage(), 500, y[11], 70, 70, this);
                } else { // Use b1 and a1 image before 30 seconds
                    g.drawImage(b1.getImage(), 0, 0, this);
                    g.drawImage(a1.getImage(), 25, y[0], 70, 70, this);
                    g.drawImage(a1.getImage(), 25, y[1], 70, 70, this);
                    g.drawImage(a1.getImage(), 25, y[2], 70, 70, this);
                    g.drawImage(a1.getImage(), 200, y[3], 70, 70, this);
                    g.drawImage(a1.getImage(), 200, y[4], 70, 70, this);
                    g.drawImage(a1.getImage(), 200, y[5], 70, 70, this);
                    g.drawImage(a1.getImage(), 350, y[6], 70, 70, this);
                    g.drawImage(a1.getImage(), 350, y[7], 70, 70, this);
                    g.drawImage(a1.getImage(), 350, y[8], 70, 70, this);
                    g.drawImage(a1.getImage(), 500, y[9], 70, 70, this);
                    g.drawImage(a1.getImage(), 500, y[10], 70, 70, this);
                    g.drawImage(a1.getImage(), 500, y[11], 70, 70, this);
                }
                g.drawImage(player.getImage(), po - 40, 350, 100, 100, this);
                g.drawImage(HP.getImage(), lhp[1], 70, 30, 30, this);
                g.drawImage(HP.getImage(), lhp[2], 100, 30, 30, this);
                g.drawImage(HP.getImage(), lhp[3], 100, 30, 30, this);
                g.drawImage(HP.getImage(), lhp[4], 130, 30, 30, this);
                g.drawImage(ls[score].getImage(), 635, 380, 50, 50, Color.yellow, this);
            } else if (mode == 0) {
                g.drawImage(start.getImage(), 0, 0, this);
            } else if (mode == 3) {
                g.drawImage(n.getImage(), 0, 0, this);
                g.drawImage(ls[score - 1].getImage(), 240, 280, 50, 50, Color.yellow, this);
            }
        }
    }

    mode() {
        add(new gameplay());
    }
}

public class GameM {

    public static void main(String[] args) {
        mode m = new mode();
        m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        m.setSize(750, 500);
        m.setTitle("Meteorite Dodge Game");
        m.setVisible(true);
    }
}




















