/*
    This panel shows the remained time of the game and number of each collected snack.
*/
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CatMiniView extends JPanel {
    static ArrayList<JLabel> snackLabels;
    JLabel timeLeft;

    CatMiniView(int width, int height) {


        // Creating cat portrait label
        ImageIcon catPortraitIcon = new ImageIcon(new ImageIcon("caticon.png").getImage().getScaledInstance(90, 90, Image.SCALE_DEFAULT));


        JLabel catPortrait = new JLabel();
        catPortrait.setBounds(10, 10, 100, 100);
        catPortrait.setIcon(catPortraitIcon);
        catPortrait.setVisible(true);
        catPortrait.setText("Kotek");
        catPortrait.setHorizontalTextPosition(JLabel.CENTER);
        catPortrait.setVerticalTextPosition(JLabel.NORTH);
        catPortrait.setFont(new Font("Calibri", Font.BOLD, 30));
        catPortrait.setVerticalAlignment(JLabel.CENTER);
        catPortrait.setHorizontalAlignment(JLabel.CENTER);
        catPortrait.setForeground(Color.BLACK);

        // Creating label for remained time
        timeLeft = new JLabel();
        timeLeft.setBounds(0, 0, 240, 30);
        timeLeft.setText("60");
        timeLeft.setHorizontalTextPosition(JLabel.CENTER);
        timeLeft.setVerticalTextPosition(JLabel.CENTER);
        timeLeft.setVerticalAlignment(JLabel.CENTER);
        timeLeft.setHorizontalAlignment(JLabel.CENTER);
        timeLeft.setFont(new Font("Calibri", Font.BOLD, 80));
        timeLeft.setForeground(Color.BLACK);


        // Crating cat snacks panel
        JPanel catSnacks = new JPanel();
        catSnacks.setLayout(new BorderLayout());
        catSnacks.setBounds(10, 100, 300, 150);

        this.add(timeLeft);
        this.add(catPortrait);


        snackLabels = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            ImageIcon snackIcon = new ImageIcon(new ImageIcon("snacksicons/snack" + (i) + ".png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            JLabel snackLabel = new JLabel();
            snackLabel.setIcon(snackIcon);
            snackLabel.setText("0");
            snackLabel.setHorizontalTextPosition(JLabel.CENTER);
            snackLabel.setVerticalTextPosition(JLabel.BOTTOM);
            snackLabel.setIconTextGap(10);
            snackLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
            snackLabel.setVerticalAlignment(JLabel.CENTER);
            snackLabel.setHorizontalAlignment(JLabel.CENTER);
            snackLabel.setBounds(0, 0, 30, 30);
            snackLabels.add(snackLabel);
            catSnacks.add(snackLabels.get(snackLabels.size() - 1));
            this.add(snackLabels.get(snackLabels.size() - 1));
        }


        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridLayout(5, 2));
        this.setBackground(new Color(200,200,255));
    }

    public static void setCollectedSnacks(ArrayList<Integer> collectedSnacks) {
        for (int i = 0; i < 7; i++) {
            JLabel tmpLabel = snackLabels.get(i);
            tmpLabel.setText(Integer.toString(collectedSnacks.get(i + 1)));
        }
    }

    public void setTimeLeft(long time) {
        this.timeLeft.setText(Long.toString(time / 1000));
    }
}
