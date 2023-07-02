/*
 The most complex panel. It is responsible for creating and placing map buttons,
  moves of the cat, placing and removing snacks
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MapOverview extends JLayeredPane {
    ArrayList<JButton> fields = new ArrayList<>();
    ImageIcon catIcon;
    JLabel player;
    javax.swing.Timer timer;

    // These variables are used to smootly move cat around clicks
    int newXpos = 540;
    int newYpos = 420;
    int currXpos = 540;
    int currYpos = 420;

    // Dimensions of button
    int xSize = 80;
    int ySize = 80;
    int mapXSize;
    int mapYSize;


    ImageIcon snack0Icon;
    ImageIcon snack1Icon;
    ImageIcon snack2Icon;
    ImageIcon snack3Icon;
    ImageIcon snack4Icon;
    ImageIcon snack5Icon;
    ImageIcon snack6Icon;

    ArrayList<Integer> collectedSnacks;

    MapOverview(int width, int height, int mapXsize, int mapYsize) {
        mapXSize = mapXsize;
        mapYSize = mapYsize;

        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(Color.blue);
        this.setLayout(null);

        collectedSnacks = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            collectedSnacks.add(0);
        }

        ImageIcon backgroundIcon = new ImageIcon(new ImageIcon("background.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        snack0Icon = new ImageIcon(new ImageIcon("snacksicons/snack0.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack1Icon = new ImageIcon(new ImageIcon("snacksicons/snack1.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack2Icon = new ImageIcon(new ImageIcon("snacksicons/snack2.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack3Icon = new ImageIcon(new ImageIcon("snacksicons/snack3.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack4Icon = new ImageIcon(new ImageIcon("snacksicons/snack4.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack5Icon = new ImageIcon(new ImageIcon("snacksicons/snack5.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        snack6Icon = new ImageIcon(new ImageIcon("snacksicons/snack6.png").getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));

        JLabel backgroundLabel = new JLabel();

        backgroundLabel.setBounds(0, 0, 1600, 900);
        backgroundLabel.setIcon(backgroundIcon);
        this.add(backgroundLabel, 0);

        // Creating and placing buttons
        for (int i = 0; i < mapYsize; i++) {
            for (int j = 0; j < mapXsize; j++) {
                fields.add(new JButton());
                JButton o = fields.get(fields.size() - 1);
                o.setBounds(j * xSize, i * ySize, xSize, ySize);
                o.setFocusable(false);
                o.setIconTextGap(0);
                o.setHorizontalAlignment(JButton.CENTER);
                o.setVerticalAlignment(JButton.CENTER);
                o.setVerticalTextPosition(JButton.CENTER);
                o.setHorizontalTextPosition(JButton.CENTER);
                o.setContentAreaFilled(false);
                o.setBorderPainted(false);
                int finalI = i;
                int finalJ = j;
                o.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {}
                    @Override
                    public void mousePressed(MouseEvent e) {
                        newXpos = xSize * finalJ-30;
                        newYpos = ySize * finalI-30;
                        removeSnack(finalI, finalJ);
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                this.add(o, 0);
            }
        }

        catIcon = new ImageIcon(new ImageIcon("caticon.png").getImage().getScaledInstance(xSize+60, ySize+60, Image.SCALE_DEFAULT));
        player = new JLabel();
        player.setIcon(catIcon);
        player.setBounds(currXpos, currYpos, xSize+60, ySize+60);
        player.setVisible(true);
        this.add(player, 1);

        // Creating timer for smooth cat movement. Every 15 milliseconds method moved() is called and cat
        // image is moved 1/8 of distance between new position and current position.
        // By doing that this way it is looks like cat movement is smooth.
        timer = new javax.swing.Timer(15, e -> moved((newXpos - currXpos) / 8, (newYpos - currYpos) / 8));
        timer.start();
    }

    public void moved(int x, int y) {
        player.setVisible(false);
        Rectangle t = player.getBounds();
        if (newXpos - currXpos < 10 && newXpos - currXpos > -10) {
            player.setBounds(newXpos, t.y + y, t.width, t.height);
            this.currXpos = newXpos;
            this.currYpos += y;
        } else if (newYpos - currYpos < 10 && newYpos - currYpos > -10) {
            player.setBounds(t.x + x, newYpos, t.width, t.height);
            this.currXpos += x;
            this.currYpos = newYpos;
        } else {
            player.setBounds(t.x + x, t.y + y, t.width, t.height);
            this.currYpos += y;
            this.currXpos += x;
        }
        player.setVisible(true);
    }

    public void placeSnack(int posXY, int snackicon) {
        int time = 600;
        switch (snackicon) {
            case (0) -> time += 100;
            case (1) -> time += 200;
            case (2) -> time += 300;
            case (3) -> time += 400;
            case (4) -> time += 500;
            case (5) -> time += 600;
            case (6) -> time += 700;
            default -> {
            }
        }

        ImageIcon snackIcon = new ImageIcon(new ImageIcon("snacksicons/snack" + (snackicon) + ".png").getImage().getScaledInstance(xSize, ySize, Image.SCALE_DEFAULT));
        JButton tmpButton = fields.get(posXY);
        tmpButton.setIconTextGap(snackicon + 1);
        tmpButton.setIcon(snackIcon);
        tmpButton.setVisible(true);

        Timer testTimer = new Timer();
        testTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                tmpButton.setIconTextGap(0);
                removeSnack(posXY / mapXSize, posXY % mapXSize);
            }
        }, time);

    }

    public void removeSnack(int posY, int posX) {
        JButton tmpButton = fields.get(posY * mapXSize + posX);
        tmpButton.setIcon(null);
        tmpButton.setVisible(true);
        collectedSnacks.set(tmpButton.getIconTextGap(), collectedSnacks.get(tmpButton.getIconTextGap()) + 1);
        tmpButton.setIconTextGap(0);
    }
    public ArrayList<Integer> getCollectedSnacks() {
        return collectedSnacks;
    }
}
