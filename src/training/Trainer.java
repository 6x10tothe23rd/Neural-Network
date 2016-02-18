package training;

import core.*;
import java.util.ArrayList;

public abstract class Trainer {
    protected ArrayList<InputOutputValue> inputs;
    protected ArrayList<InputOutputValue> outputs;
    protected Network net;

    public Trainer(ArrayList<InputOutputValue> inputs, ArrayList<InputOutputValue> outputs, Network net){
        this.inputs = inputs;
        this.outputs = outputs;
        this.net = net;
    }

    public void train(){};
}
