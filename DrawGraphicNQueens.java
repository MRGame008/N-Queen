package AlgorithimDesign;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DrawGraphicNQueens extends JPanel {
    private static final int SIZE = 600; // size of the panel in pixels
    private int n; // number of queens
    private int[] queens; // queens[i] is the column of the queen at row i
    private BufferedImage queenImage; // the image to use for the queens

    public DrawGraphicNQueens(int n, int[] queens) {
        this.n = n;
        this.queens = queens;
        setPreferredSize(new Dimension(SIZE, SIZE));
        try {
            queenImage = ImageIO.read(new File("queen1.png")); // load the image file
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = SIZE / n;
        g.setColor(Color.BLACK);
        for (int i = 0; i <= n; i++) {
            g.drawLine(0, i * cellSize, SIZE, i * cellSize); // horizontal lines
            g.drawLine(i * cellSize, 0, i * cellSize, SIZE); // vertical lines
        }
        if (queenImage != null) {
            for (int i = 0; i < n; i++) {
                g.drawImage(queenImage, queens[i] * cellSize, i * cellSize, cellSize, cellSize, null); // queens
            }
        } else {
            g.setColor(Color.RED);
            for (int i = 0; i < n; i++) {
                g.drawString("Q", queens[i] * cellSize + cellSize / 2, i * cellSize + cellSize / 2); // queens
            }
        }
    }


    public static void main(String[] args) {
        System.out.println("Enter Number of Queens");
        Scanner input = new Scanner(System.in);

        int n = input.nextInt(); // number of queens
        QueensPlace solver = new QueensPlace(n);
        int[] solution = solver.solve().get(0);
        //int[] solution = solutions[0]; // use the first solution
        JFrame frame = new JFrame("N-Queens Solution");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new DrawGraphicNQueens(n, solution));
        frame.pack();
        frame.setVisible(true);
    }
}