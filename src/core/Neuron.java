package core;

public interface Neuron {
    boolean isInput();
    void preStep();
    void postStep();
    void process(double input);
    double getCurrentState();
}