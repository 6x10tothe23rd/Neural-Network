import java.util.*;

import core.*;
import training.*;

public class Main {
    public static int highest(ArrayList<InputOutputValue> arr){
        int highestIndex = 0;
        for(int i = 1; i < arr.size(); i++){
            if(arr.get(i).getValue() > arr.get(highestIndex).getValue()){
                highestIndex = i;
            }
        }
        return highestIndex;
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        NetworkList<Neuron> structure = new NetworkList<Neuron>();
        
        String choices[] = {
                "One",
                "Two",
                "Three",
                "Four",
                "Five",
                "Six",
                "Seven",
                "Eight"
        };

        ArrayList<InputOutputValue> inputs = InputOutputValue.emptyTemplate(choices.length);
        ArrayList<InputOutputValue> outputs = InputOutputValue.emptyTemplate(choices.length);
        ArrayList<InputOutputValue> expected = InputOutputValue.emptyTemplate(outputs.size());

        for(InputOutputValue d : inputs){
            structure.net.add(new InputNeuron(d));
        }
        for(InputOutputValue d : outputs){
            structure.net.add(new OutputNeuron(d));
        }
        for(int i=0;i<2;i++){
            structure.net.add(new SigmoidNeuron());
        }

        Network x = new Network(structure);
        GradientDescTrainer t = new GradientDescTrainer(inputs, outputs, expected, x, 0.01);
        x.randomizeConnections(60, true);
        double error = 1;
        double besterror = 1000;
        int counter = 1;
        while(error > 0.01){
            int current = (int)(Math.random() * inputs.size());
            for(InputOutputValue io : inputs){
                io.setValue(0);
            }
            inputs.get(current).setValue(1);
            for(InputOutputValue io : expected){
                io.setValue(0);
            }
            expected.get(current).setValue(1);
            t.train();
            error = t.computeError();
            if(error < besterror){
                besterror = error;
            }
            if(counter == 100){
                counter = 1;
                System.out.println("Error: " + besterror);
                besterror = 1000;
            }
            counter++;
        }
        while(true){
            System.out.print("Which:");
            for(InputOutputValue io : inputs){
                io.setValue(0);
            }
            inputs.get((scan.nextInt() - 1)).setValue(1);
            x.step();
            System.out.println("Choice: " + choices[highest(outputs)]);
            System.out.println(outputs.toString());
        }
    }
}
