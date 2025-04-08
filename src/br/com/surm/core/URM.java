/*
 * The MIT License
 *
 * Copyright (c) 2025 Valdigleis.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.surm.core;

import java.util.ArrayList;
import java.util.Vector;

/**
 * 
 * This class implements the definition of the Unlimited Register Machine, as presented in the book, 
 * Computability: An Introduction to Recursive Function Theory by Nigel Cutland.
 * 
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @author Filipe Campos (filipe.campos.127@ufrn.edu.br)
 * @version 1.0
 */
public class URM {

  private ArrayList<Integer> registers;
  private int countProgram;
  private Program program;

  /**
   * 
   * Constructor of the URM class, which loads the program and initializes 10 registers with a value of 0.
   * 
   * @param program The program loaded into the URM.
   */
  public URM(Program program) {
    this.program = program;
    this.countProgram = 0;
    this.registers = new ArrayList<>(10);
    for(int i = 0; i < 10; i++) {
      this.registers.set(i, 0);
    }
  }

  /**
   * 
   * Constructor of the URM class, which loads the program and initializes n registers with values on input.
   * 
   * @param program The program loaded into the URM.
   * @param values The n values to set in first n registers.
   */
  public URM(Program program, int[] values){
    this.program = program;
    this.countProgram = 0;
    this.registers = new ArrayList<>(values.length);
    for(int i = 0; i < values.length; i++) {
      this.registers.set(i, values[i]);
    }
  }

  /**
   * 
   * Constructor of the URM class, which loads the program and initializes n registers with a Vector of values on input.
   * 
   * @param program The program loaded into the URM.
   * @param values The n values to set in first n registers.
   */
  public URM(Program program, Vector<Integer> values) {
    this.program = program;
    this.countProgram = 0;
    this.registers = new ArrayList<>(values.size());
    for (int i : values) {
      this.registers.add(i);
    }
  }

  /**
   * 
   * Method that executes only one instruction of the program loaded in the URM, this instruction being the one pointed to by the URM program counter.
   * 
   */
  public void runInstruction() {
    if(this.countProgram < this.program.getSize()) {
      Instruction instruction = this.program.getInstruction(countProgram);
      String code = instruction.getCode();
      switch(code) {
        case "Z":
          try {
            this.registers.set(instruction.getData().get(0), 0); 
          } catch (Exception e) {
            this.addRegisters(instruction.getData().get(0));
          }
          this.countProgram++;
          break;
        case "S":
          try {
            int value = this.registers.get(instruction.getData().get(0));
            this.registers.set(instruction.getData().get(0), value+1); 
          } catch (Exception e) {
            this.addRegisters(instruction.getData().get(0));
            this.registers.set(instruction.getData().get(0), 1);
          }
          this.countProgram++;
          break;
        case "T":
          try {
            int value = this.registers.get(instruction.getData().get(0));
            this.registers.set(instruction.getData().get(1), value);
          } catch (Exception e) {
            int m = instruction.getData().get(0);
            int n = instruction.getData().get(1);
            int maxRegister = Math.max(m, n);
            this.addRegisters(maxRegister);
            this.registers.set(n, this.registers.get(m));
          }
          this.countProgram++;
          break;
        default:
          try {
            if( this.registers.get(instruction.getData().get(0)) == 
                this.registers.get(instruction.getData().get(1))) {
              this.countProgram = instruction.getData().get(2);
            } else {
              this.countProgram++; 
            }
          } catch (Exception e) {
            int maxRegister = Math.max(
              instruction.getData().get(0),
              instruction.getData().get(1));
            this.addRegisters(maxRegister);
            if( this.registers.get(instruction.getData().get(0)) == 
                this.registers.get(instruction.getData().get(1))) {
              this.countProgram = instruction.getData().get(2) - 1;
            } else {
              this.countProgram++; 
            }
          }
          break;
      }
    }
  }

  /**
   * 
   * Method that executes the program loaded in the URM without interruptions.
   * 
   */
  public void runCompleteProgram(){
    while(this.countProgram < this.program.getSize()){
      
    }
  }

  /**
   * 
   * Method to increase the number of working registers in the URM.
   * 
   * @param numberRegister The new number of working registers
   */
  private void addRegisters(int numberRegister) {
    while (this.registers.size() < numberRegister) {
      this.registers.add(0);
    }
  }

  @Override
  public String toString() {
    String output = "";
    for(int i = 0; i < this.registers.size(); i++){
      output = output + "R" + i + ": " + this.registers.get(i) + ", ";
    }
    output = output + "\n" + "count program: " + this.countProgram + "\n";
    return output;
  }

}