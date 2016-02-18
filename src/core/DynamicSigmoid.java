package core;

public class DynamicSigmoid extends SigmoidNeuron implements DynamicNeuron {
   protected double decayRate;
   
   public void preStep(){
      decay();
   }
   
   public DynamicSigmoid(double bias, double decayRate){
      super(bias);
      this.decayRate = decayRate;
   }
   
   public DynamicSigmoid(double decayRate){
      super();
      this.decayRate = decayRate;
   }
   
   public void decay() {
      activation *= (1 - decayRate);
      activation = activation < 0 ? 0 : activation;
   }
}
