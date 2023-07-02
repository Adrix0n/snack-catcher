import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;


public class Main {
    static int MAINFRAME_WIDTH = 1600;
    static int MAINFRAME_HEIGHT = 900;
    // Number of fields in X and Y axis
    static int MAP_X_SIZE = 15;
    static int MAP_Y_SIZE = 10;

    public static void main(String[] args) {
        ImageIcon logo = new ImageIcon("logo.png");

        // Creating the main frame of the program
        JFrame MainFrame = new JFrame();
        MainFrame.setTitle("Snack catcher");
        MainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        MainFrame.setSize(MAINFRAME_WIDTH, MAINFRAME_HEIGHT);
        MainFrame.setResizable(false);
        MainFrame.setIconImage(logo.getImage());
        MainFrame.setLayout(new BorderLayout());

        // Creating map panel and panel with collected snacks
        MapOverview mapOverview = new MapOverview(MAINFRAME_WIDTH - 400, MAINFRAME_HEIGHT, MAP_X_SIZE, MAP_Y_SIZE);
        CatMiniView catMiniview = new CatMiniView(384, MAINFRAME_HEIGHT);

        // Adding both panels to main frame
        MainFrame.add(mapOverview, BorderLayout.WEST);
        MainFrame.add(catMiniview, BorderLayout.EAST);
        MainFrame.setVisible(true);

        // This array is used to send information about amount of collected snacks
        // from mapOverview panel to CatMiniView panel through main class
        ArrayList<Integer> collectedSnacks = new ArrayList<>(8);
        for (int i = 0; i < 8; i++) {
            collectedSnacks.add(0);
        }

        // Initialization
        Random rand = new Random();
        int upperbound = MAP_X_SIZE * MAP_Y_SIZE;
        long startTime = System.currentTimeMillis();
        long finishTime = System.currentTimeMillis();
        long snackTime = System.currentTimeMillis();
        long timeElapsed = finishTime - startTime;
        int timeForNextSnack = rand.nextInt(800) + 200;

        // Main loop which lasts for 60 seconds
        while ((Math.abs(timeElapsed) < 60000)) {
            if ((Math.abs(timeElapsed - snackTime)) > timeForNextSnack) {
                // New snack will appear after 200-999 milliseconds
                timeForNextSnack = rand.nextInt(800) + 200;
                snackTime = timeElapsed;
                // Placing snack in random position of the map
                mapOverview.placeSnack(rand.nextInt(upperbound), rand.nextInt(7));
                // Refreshing the number of collected snacks in right panel
                collectedSnacks = mapOverview.getCollectedSnacks();
                CatMiniView.setCollectedSnacks(collectedSnacks);
            }

            catMiniview.setTimeLeft(60000 - timeElapsed);
            finishTime = System.currentTimeMillis();
            timeElapsed = finishTime - startTime;
        }

        // Calculating the final score
        collectedSnacks = mapOverview.getCollectedSnacks();
        int score =0;
        score += collectedSnacks.get(1) * 700;
        score += collectedSnacks.get(2) * 600;
        score += collectedSnacks.get(3) * 520;
        score += collectedSnacks.get(4) * 450;
        score += collectedSnacks.get(5) * 400;
        score += collectedSnacks.get(6) * 350;
        score += collectedSnacks.get(7) * 300;

        // Calculating accuracy of the player clicks
        int sum=0;
        for(int i=1;i<8;i++)
            sum+=collectedSnacks.get(i);
        float clickAccuracy = (float)sum/(float)collectedSnacks.get(0);
        // Creating score panel containing final score and accuracy
        ScoreScreen scoreScreen = new ScoreScreen(384,MAINFRAME_HEIGHT,score,clickAccuracy);
        // Switching panels visibility to show scorescreen panel
        catMiniview.setVisible(false);
        scoreScreen.setVisible(true);
        MainFrame.add(scoreScreen,BorderLayout.EAST);
    }
}