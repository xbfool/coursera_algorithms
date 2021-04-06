/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

public class Board {
    private int[][] tiles;
    private int size;
    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles){
        size = tiles.length;
        this.tiles = new int[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    // string representation of this board
    public String toString(){
        String s1 = String.format("%d\n", size);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                s1 += String.format("%d ", tiles[i][j]);
            }
            s1 += "\n";
        }
        return s1
    }

    // board dimension n
    public int dimension(){
        return size;
    }

    // number of tiles out of place
    public int hamming(){
        int x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(tiles[i][j] != 0 && tiles[i][j] != i * size + j + 1){
                    x++;
                }
            }
        }
        return x;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan(){
        int x = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int tmp = tiles[i][j];
                if(tmp == 0)
                    continue;
                int tmi = (tmp - 1) / size;
                int tmj = (tmp - 1) % size;
                x += Math.abs(tmi - i) + Math.abs(tmj - j);
            }
        }
        return x;
    }

    // is this board the goal board?
    public boolean isGoal(){
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y){
        Board yy = (Board) y;
        if(yy.size != size){
            return false;
        }else{
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (tiles[i][j] != yy.tiles[i][j]){
                        return false;
                    }
                }
            }
        }
        return false;
    }

    // all neighboring boards
    public Iterable<Board> neighbors(){
        return null;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin(){
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args){

    }
}
