package core;

public interface DynamicNeuron extends Neuron {
	void decay();
	default void preStep(){
	   decay();
	}
}
