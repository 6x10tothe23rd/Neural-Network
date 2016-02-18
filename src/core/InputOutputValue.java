package core;

import java.util.ArrayList;

public class InputOutputValue {
   private double value;

   public InputOutputValue(double value){
      setValue(value);
   }
   
   public double getValue() {
      return value;
   }

   public void setValue(double value) {
      this.value = value;
   }
   
   public static ArrayList<InputOutputValue> emptyTemplate(int size){
      ArrayList<InputOutputValue> result = new ArrayList<InputOutputValue>();
      
      for(int i=0;i<size;i++){
         result.add(new InputOutputValue(0));
      }
      
      return result;
   }
   
   public String toString(){
      return String.valueOf(value);
   }
}
