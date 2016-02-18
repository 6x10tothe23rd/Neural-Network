package core;

import java.util.*;

public class Network {
    public NetworkList<Neuron> neurons;

    public Network(){
        neurons = new NetworkList<Neuron>();
    }

    public Network(NetworkList<Neuron> existing){
        neurons = existing;
    }

    public void step(){
        for(Neuron n: neurons.net){
            n.preStep();
        }
        for(Neuron n : neurons.net){
            for(Neuron p : neurons.getParents(n)){
                double weight = neurons.getWeight(p, n);
                n.process(p.getCurrentState() * weight);
            }
        }
        for(Neuron n: neurons.net){
            n.postStep();
        }
    }

    private Neuron getRandomNeuron(){
        Random rand = new Random();
        int choice = rand.nextInt(neurons.net.size());
        return neurons.net.get(choice);
    }
    
    public ArrayList<Neuron> getChildless(){
        ArrayList<Neuron> results = new ArrayList<Neuron>();
        for(Neuron n : neurons.net){
            if(neurons.getChildren(n).size() == 0){
                results.add(n);
            }
        }
        return results;
    }
    
    public ArrayList<Neuron> getParentless(){
        ArrayList<Neuron> results = new ArrayList<Neuron>();
        for(Neuron n : neurons.net){
            if(neurons.getParents(n).size() == 0){
                results.add(n);
            }
        }
        return results;
    }

    public void randomizeConnections(int min, boolean overwrite){
        Random rand = new Random();

        if(overwrite){
            neurons.resetConnections();
        }
        
        ArrayList<Neuron> parentless = getParentless();
        for(Neuron n : parentless){
            double strength = 0.1 + 0.9 * rand.nextFloat();
            neurons.connect(getRandomNeuron(), n, strength);
        }
        
        ArrayList<Neuron> childless = getChildless();
        for(Neuron n : childless){
            double strength = 0.1 + 0.9 * rand.nextFloat();
            neurons.connect(n, getRandomNeuron(), strength);
        }
        
        int total = childless.size() + parentless.size();
        int remaining = min - total;
        while(remaining > 0){
            if(neurons.connect(getRandomNeuron(), getRandomNeuron())){
                remaining--;
            }
        }
    }
}
