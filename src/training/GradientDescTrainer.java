package training;

import java.util.ArrayList;
import java.util.Arrays;

import core.*;

public class GradientDescTrainer extends Trainer {
    protected double trainRate;
    protected ArrayList<InputOutputValue> expectedOutputs;
    
    private static final double derivAccuracy = 1e-5;
    
    public GradientDescTrainer(ArrayList<InputOutputValue> inputs, ArrayList<InputOutputValue> outputs, ArrayList<InputOutputValue> expectedOutputs, Network net, double trainRate) {
        super(inputs, outputs, net);
        this.expectedOutputs = expectedOutputs;
        this.trainRate = trainRate;
    }
    
    public double computeError(){
        double error = 0;
                
        ArrayList<Double> errors = new ArrayList<Double>();
        
        for(int i = 0; i < expectedOutputs.size(); i++){
            double expected = expectedOutputs.get(i).getValue();
            double actual = outputs.get(i).getValue();
            
            double thisError = Math.pow((expected - actual), 2);
            errors.add(thisError);
        }
        
        for(double e : errors){
            error += e;
        }
        
        error = error / errors.size();
        
        return error;
    }
    
    public void train(){
        /*for(NetworkList<Neuron>.Connection c : net.neurons.conns){
            double original = c.strength;
            double lowerTest = c.strength - derivAccuracy;
            double upperTest = c.strength + derivAccuracy;
            
            c.strength = lowerTest;
            net.step();
            double lowError = computeError();
            
            c.strength = upperTest;
            net.step();
            double highError = computeError();
            
            double deriv = (highError - lowError) / (2 * derivAccuracy);
            c.strength = original - (trainRate * deriv);
        }*/
        
        ArrayList<Double> postTrain = new ArrayList<Double>();
        
        for(int i = 0; i < net.neurons.conns.size(); i++){
            NetworkList<Neuron>.Connection c = net.neurons.conns.get(i);
            double original = c.strength;
            double lowerTest = c.strength - derivAccuracy;
            double upperTest = c.strength + derivAccuracy;
            
            c.strength = lowerTest;
            net.step();
            double lowError = computeError();
            
            c.strength = upperTest;
            net.step();
            double highError = computeError();
            
            double deriv = (highError - lowError) / (2 * derivAccuracy);
            postTrain.add(original - (trainRate * deriv));
        }
        
        for(int i = 0; i < net.neurons.conns.size(); i++){
            net.neurons.conns.get(i).strength = postTrain.get(i);
        }
    }
}
