package core;

public class OutputNeuron implements Neuron {
   private double currentOutput;
   private InputOutputValue dest;
   
   public OutputNeuron(InputOutputValue dest){
      this.dest = dest;
   }
   
   public void preStep() {
      currentOutput = 0;
   }

   public void postStep() {
      dest.setValue(currentOutput);
   }

   public void process(double input) {
      currentOutput += input;
   }

   public double getCurrentState() {
      return currentOutput;
   }

    public boolean isInput() {
        return false;
    }
}
