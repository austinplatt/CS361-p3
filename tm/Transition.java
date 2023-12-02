package tm;

public class Transition {
    private int nextState;
    private int writeSymbol;
    private char move;

    //Constructor
    public Transition(int nextState, int writeSymbol, char move) {
        this.nextState = nextState;
        this.writeSymbol = writeSymbol;
        this.move = move;
    }

    //Getter methods
    public int getNextState() {
        return nextState;
    }

    public int getWriteSymbol() {
        return writeSymbol;
    }

    public char getMove() {
        return move;
    }

    //Method to read symbol
    public int getReadSymbol() {
        return writeSymbol; 
    }

    @Override
    public String toString() {
        return nextState + "," + writeSymbol + "," + move;
    }
}
