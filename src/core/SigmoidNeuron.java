package core;

public class SigmoidNeuron implements Neuron {
   protected double activation;
   public double bias;
   
   public SigmoidNeuron(double bias){
      this.bias = bias;
   }
   
   public SigmoidNeuron(){
      this.bias = 0;
   }
   
   public void preStep() {
      activation = 0;
   }

   public void postStep(){}

   public void process(double input) {
      activation += input;
   }

   private double sigmoid(double z){
      return 1 / (1 + Math.exp(-z));
   }
   
   public double getCurrentState() {
      return sigmoid(activation + bias);
   }
    
    public boolean isInput() {
        return false;
    }
}
