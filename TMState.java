package tm;

public class TMState {
    private Transition[] transitions;
    private boolean isFinal;

    //Constructors

    public TMState(int numTransitions) {
        if (numTransitions != 0) {
            transitions = new Transition[numTransitions];
            isFinal = false;
        } else {
            isFinal = true;
        }
    }

    public TMState(Transition[] transitionArray) {
        this.transitions = transitionArray;
        isFinal = false;
    }

    //Getter and Setter methods

    public Transition[] getTransitions() {
        return transitions;
    }

    public Transition getTransitionAt(int index) {
        return transitions[index];
    }

    public void setTransitions(Transition[] newTransitions) {
        this.transitions = newTransitions;
    }

    public void setTransitionAt(int index, Transition newTransition) {
        transitions[index] = newTransition;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public void setFinal(boolean finalState) {
        isFinal = finalState;
    }

    //Method to get the transition for a given symbol
    public Transition getTransitionForSymbol(int symbol) {
        for (Transition transition : transitions) {
            if (transition.getReadSymbol() == symbol) {
                return transition;
            }
        }
        return null; 
    }

    //toString method for debugging or output
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (isFinal) {
            result.append("Final state reached. No more transitions\n");
        } else {
            for (int i = 0; i < transitions.length; i++) {
                result.append("Transition ").append(i + 1).append(" is ").append(transitions[i]).append("\n");
            }
        }
        return result.toString();
    }
}