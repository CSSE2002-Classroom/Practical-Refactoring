import java.io.PrintWriter;
import java.util.Scanner;

public class Connect4 {
    /**
     * I store a copy of the board for each player in boardX and boardO.
     * I store the board as a flat array of integers
     * so that a row and column is represented by board[row * 7 + column].
     * If board[row * 7 + column] is 1 then there is a tile at that location for the player.
     * e.g. boardX[3 * 7 + 4] == 1 means that player X has a tile at row=3, column=4
     */
    public static void play(Scanner i, PrintWriter out) {
        int[] boardX = new int[7 * 6];
        int[] boardO = new int[7 * 6];
        boolean turn = false;
        boolean exit=false;
        while (true) {
            // BEGIN PRINT BOARD
            int index = 0;
            while (index < 7) {out.print(index); index++;}
            index = 0;
            while (index < 42) {
                if (boardX[index] == 1) out.print("X");
                if (boardO[index] == 1) out.print("O");
                if (boardX[index] != 1 && boardO[index] != 1) out.print(" ");
                if ((index+1)%7==0) // new line
                    out.print("\n"); index++;
            }
            // END PRINT BOARD
            if (exit) {
                break;
            }
            if (turn) {

                out.print("Player X: ");
            }
            else {
                out.print("Player O: ");
            }
            out.flush();
            int pos = i.nextInt();
            if (pos<0||pos>6) continue; // wrong move buddy
            int firstFreeRow=5; // top
            while (firstFreeRow > 0) {
                if (boardX[firstFreeRow*7+pos] == 0 &&boardO[firstFreeRow*7+pos] == 0) {
                    break;
                }
                firstFreeRow--;
            }
            if (turn) {
                boardX[firstFreeRow*7+pos] = 1;
            }
            if (!turn) {
                boardO[firstFreeRow*7+pos] = 1;
            }
            turn=!turn;
            for (int s=0;s<42;s++){ // check win
                boolean xWins=true;
                boolean oWins=true;
                for(int xOffset=0;xOffset<4;xOffset++){ // search horizontal
                    if ((s+xOffset)/7!=s/7){xWins=false;break;} // ran off the edge
                    if (boardX[s+xOffset]==0)
                        xWins=false;
                }
                if (xWins){
                    out.println("Player X wins");
                    exit=true;
                }
                xWins=true;
                for(int yOffset=0;yOffset<4;yOffset++){ // search vertical
                    if (s+(yOffset*7)>=42){xWins=false;break;} // ran off the edge
                    if (boardX[s+(yOffset*7)]==0)
                        xWins=false;
                }
                if (xWins){
                    out.println("Player X wins");
                    exit=true;
                }
                for(int xOffset=0;xOffset<4;xOffset++){ // search horizontal
                    if ((s+xOffset)/7!=s/7){oWins=false;break;} // ran off the edge
                    if (boardO[s+xOffset]==0)
                        oWins=false;
                }
                if (oWins){
                    out.println("Player O wins");
                    exit=true;
                }
                oWins=true;
                for(int yOffset=0;yOffset<4;yOffset++){ // search vertical
                    if (s+(yOffset*7)>=42){oWins=false;break;} // ran off the edge
                    if (boardO[s+(yOffset*7)]==0)
                        oWins=false;
                }
                if (oWins){
                    out.println("Player O wins");
                    exit=true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner i = new Scanner(System.in);
        play(i, new PrintWriter(System.out));
    }
}
