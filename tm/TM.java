package tm;

import java.util.Arrays;

public class TM {
    private TMState[] states;
    private int currentStateIndex;
    private int tapeHeadPosition;
    private int[] tape;

    //Constructor
    public TM(TMState[] states, int tapeLength) {
        this.states = states;
        this.currentStateIndex = 0;
        this.tapeHeadPosition = tapeLength / 2;
        this.tape = new int[tapeLength];
        initializeTape();
    }

    //Initialize the tape with blanks (0s)
    private void initializeTape() {
        Arrays.fill(tape, 0);
    }

    //Getter and Setter methods
    public TMState getCurrentState() {
        return states[currentStateIndex];
    }

    public int getCurrentTapeSymbol() {
        return tape[tapeHeadPosition];
    }

    public void setCurrentTapeSymbol(int symbol) {
        tape[tapeHeadPosition] = symbol;
    }

    public int getTapeHeadPosition() {
        return tapeHeadPosition;
    }

    public void setTapeHeadPosition(int position) {
        tapeHeadPosition = position;
    }

    //Tape movement methods
    public void moveTapeLeft() {
        tapeHeadPosition--;
    }

    public void moveTapeRight() {
        tapeHeadPosition++;
    }

    //State transition method
    public void transitionToState(int nextStateIndex) {
        currentStateIndex = nextStateIndex;
    }

    //Check if the machine is halted
    public boolean isHalted() {
        return getCurrentState().isFinal();
    }

    //Main 
    public void simulate(String inputString) {
        //Iterate through the input string and simulate the Turing Machine
        for (int i = 0; i < inputString.length(); i++) {
            int currentSymbol = Character.getNumericValue(inputString.charAt(i));
    
            //Set the current tape symbol
            setCurrentTapeSymbol(currentSymbol);
    
            //Get the current transition for the current state and symbol
            TMState currentState = getCurrentState();
    
            // Ensure currentState is not null before trying to get the transition
            if (currentState == null) {
                System.out.println("currentState is null!");
                break;
            }
    
            Transition currentTransition = currentState.getTransitionForSymbol(currentSymbol);
    
            //Check if there's a transition for the given symbol
            if (currentTransition == null) {
                System.out.println("No transition found for symbol " + currentSymbol + " in state " + currentState);
                break;
            }
    
            //Update the machine state, write symbol, and move tape
            transitionToState(currentTransition.getNextState());
            setCurrentTapeSymbol(currentTransition.getWriteSymbol());
    
            //Move the tape head
            if (currentTransition.getMove() == 'L') {
                moveTapeLeft();
            } else if (currentTransition.getMove() == 'R') {
                moveTapeRight();
            }
    
            //Check if the machine is halted
            if (isHalted()) {
                break;
            }
        }
        printTapeContent();
    }
    
    private void printTapeContent() {
        for (int symbol : tape) {
            System.out.print(symbol);
        }
        System.out.println();
    }
}