package game;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;

public class Game {

	private static final String EMPTY_CELL = "⬜";
    private static final String CAR = "🚙";
    private static final String PLAYER_CAR = "🚗";
    private static final String OBSTACLE_CAR = "⬛";

    private int player = 0;
    private String[][] board = {
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, OBSTACLE_CAR, OBSTACLE_CAR, EMPTY_CELL, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, EMPTY_CELL, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, EMPTY_CELL},
        {EMPTY_CELL, EMPTY_CELL, EMPTY_CELL, OBSTACLE_CAR, OBSTACLE_CAR, OBSTACLE_CAR, EMPTY_CELL, EMPTY_CELL}
    };

    public void play() {
        boolean playing = true;
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (playing) {
                displayBoard();
                moveCarsDown();

                // Generate a new car at a random position in the top row
                int newCar = new Random().nextInt(8);
                board[0][newCar] = CAR;
                board[7][player] = PLAYER_CAR;

                // Input
                String key = br.readLine();

                if (key.equals("q")) {
                    playing = false;
                } else if (key.equals("a") && player > 0) {
                    movePlayerLeft();
                } else if (key.equals("d") && player < 7) {
                    movePlayerRight();
                }

                if (board[7][player].equals(CAR) || board[6][player].equals(CAR)) {
                    playing = false;
                    System.out.println("Perdiste!");

                    // Reset the player position and clear the top row
                    player = 0;
                    for (int i = 0; i < board[0].length; i++) {
                        board[0][i] = EMPTY_CELL;
                    }
                }

                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void displayBoard() {
        for (String[] row : board) {
            for (String elem : row) {
                System.out.print(" " + elem + " ");
            }
            System.out.println("");
        }
    }

    private void moveCarsDown() {
        for (int i = 0; i < 7; i++) {
            board[7 - i] = board[6 - i].clone();
        }
    }

    private void movePlayerLeft() {
        board[7][player] = EMPTY_CELL;
        player -= 1;
    }

    private void movePlayerRight() {
        board[7][player] = EMPTY_CELL;
        player += 1;
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.play();
    }
}
