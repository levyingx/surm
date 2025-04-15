package br.com.surm.core;

import java.util.ArrayList;

public class Z extends Instruction {

    public Z(String code, ArrayList<Integer> data) {
        super(code, data);

    }

    @Override
    public void method(String code, ArrayList<Integer> data) {
        // Implement Z exclusive method  
        
    }

    public int getData(int index) {
        return this.data.get(index);
    } 
    
}
