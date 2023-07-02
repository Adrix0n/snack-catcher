/*
 This panel shows final score at the end of the game.
*/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreScreen extends JPanel {

    ScoreScreen(int width, int height, int score, float accuracy){
        this.setPreferredSize(new Dimension(width, height));
        this.setBackground(new Color(200,200,255));
        this.setLayout(null);

        // Creating final score label
        JLabel textLabel = new JLabel();
        textLabel.setText("Your final score: "+score);
        textLabel.setBounds(0,height/2-90,400,60);
        textLabel.setHorizontalTextPosition(JLabel.LEFT);
        textLabel.setVerticalTextPosition(JLabel.NORTH);
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setVerticalTextPosition(JLabel.CENTER);
        textLabel.setVisible(true);
        textLabel.setFont(new Font("Calibri",Font.BOLD,30));
        this.add(textLabel);

        // Creating accuracy label
        JLabel accuracyLabel = new JLabel();
        accuracyLabel.setText("Click accuracy: "+accuracy*100+"%");
        accuracyLabel.setBounds(0,height/2-20,400,60);
        accuracyLabel.setHorizontalTextPosition(JLabel.LEFT);
        accuracyLabel.setVerticalTextPosition(JLabel.NORTH);
        accuracyLabel.setHorizontalAlignment(JLabel.CENTER);
        accuracyLabel.setVerticalTextPosition(JLabel.CENTER);
        accuracyLabel.setVisible(true);
        accuracyLabel.setFont(new Font("Calibri",Font.BOLD,20));
        this.add(accuracyLabel);

        JButton exitButton = new JButton();
        exitButton.setText("Exit program");
        exitButton.setBounds(0,height/2+50,400,60);
        exitButton.setFont(new Font("Calibri",Font.BOLD,30));
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.add(exitButton);

    }
}