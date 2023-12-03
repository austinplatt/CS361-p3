import tm.TM;
import tm.TMState;
import tm.Transition;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TMSimulator {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java TMSimulator <input_file>");
            System.exit(1);
        }

        String inputFile = args[0];

        try {
            List<String> lines = readLinesFromFile(inputFile);

            int numStates = Integer.parseInt(lines.get(0));
            int numSymbols = Integer.parseInt(lines.get(1));

            //Parse TMState transitions
            TMState[] states = parseTMStates(lines.subList(2, 2 + (numStates-1) * (numSymbols+1)));

            //Parse input string
            String inputString = lines.get(lines.size() - 1).trim();
            if(lines.size() != (numStates-1) * (numSymbols + 1) + 3) {
            	inputString = "";
            }
            
            int inputlength = inputString.length() * 2;
            if(inputlength == 0) {
            	inputlength = 10;
            }

            //Create Turing Machine
            TM turingMachine = new TM(states, inputlength, inputString); // Adjust tape length as needed

            //Simulate Turing Machine
            turingMachine.simulate();
            
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private static List<String> readLinesFromFile(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    private static TMState[] parseTMStates(List<String> transitionLines) {
        int numTransitionsPerState = transitionLines.size() / (transitionLines.size() - 1);
        TMState[] states = new TMState[numTransitionsPerState];

        for (int i = 0; i < numTransitionsPerState; i++) {
            String[] parts = transitionLines.get(i).split(",");
            int numTransitions = parts.length / 3; 
            Transition[] transitions = new Transition[numTransitions];

            for (int j = 0; j < numTransitions; j++) {
                int index = i * numTransitions + j;
                String[] transitionParts = transitionLines.get(index).split(",");
                int nextState = Integer.parseInt(transitionParts[0].trim());
                int writeSymbol = Integer.parseInt(transitionParts[1].trim());
                char move = transitionParts[2].trim().charAt(0);

                transitions[j] = new Transition(nextState, writeSymbol, move);
            }

            states[i] = new TMState(transitions);
        }

        //Set the last state as final
        states[numTransitionsPerState - 1].setFinal(true);

        return states;
    }
}