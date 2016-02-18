package core;

public class InputNeuron implements Neuron {
    private double latestInput;
    private InputOutputValue source;

    public InputNeuron(InputOutputValue source){
        this.source = source;
    }

    public void preStep() {
        latestInput = source.getValue();
    }

    public void postStep(){}

    public void process(double input){}

    public double getCurrentState() {
        return latestInput;
    }

    public boolean isInput() {
        return true;
    }
}
