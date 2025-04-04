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
   * Constructor of the URM class, which loads the program and Initializes n specific registers with predefined values
   * 
   * @param program The program loaded into the URM.
   * @param registers The “addresses” of the registers that should be initialized.
   * @param values The values to set on registers.
   */
  public URM(Program program, ArrayList<Integer> registers, int[] values){
    this.countProgram = 0;
    int max = 0;
    for(int i = 0; i < registers.size(); i++){
      if(max < registers.get(i)){
        max = registers.get(i);
      }
    }
    if(max == 0) {
      this.registers = new ArrayList<>(1);
    } else {
      this.registers = new ArrayList<>(max);
    }
    for(int i = 0; i < registers.size(); i++){
      if(registers.contains(i)){
        this.registers.set(i, values[i]);
      }else {
        this.registers.set(i, 0);
      }
    }
  }

  /**
   * 
   * Constructor of the URM class, which loads the program and Initializes n specific registers with a Vector of predefined values
   * 
   * @param program The program loaded into the URM.
   * @param registers The “addresses” of the registers that should be initialized.
   * @param values The values to set on registers.
   */
  public URM(Program program, Vector<Integer> values, Vector<Integer> coordinates) {

    this.countProgram = 0;
    this.program = program;
    this.registers = new ArrayList<>(coordinates.size());
    int j = 0;
    for (int i : values) {
      if (coordinates.get(j) >= this.registers.size()) { 
        this.ensureSize(coordinates.get(j) + 1);
      }
      this.registers.set(registers.get(j), i);
      j++;
    }
  }

  /**
   * 
   * Method that executes only one instruction of the program loaded in the URM, this instruction being the one pointed to by the URM program counter.
   * 
   */
  public void runPointedInstruction() {
    if(this.countProgram < this.program.getSize()) {
      Instruction instruction = this.program.getInstruction(countProgram);
      String code = instruction.getCode();
      switch(code) {
        case "Z":
          this.registers.set(instruction.getData().get(0), 0);
          this.countProgram++;
          break;
        case "S":
          int s = this.registers.get(instruction.getData().get(0));
          this.registers.set(instruction.getData().get(0), s+1);
          this.countProgram++;
          break;
        case "T":
          int copy = this.registers.get(instruction.getData().get(0));
          this.registers.set(instruction.getData().get(1), copy);
          this.countProgram++;
          break;
        default:
          int m = instruction.getData().get(0);
          int n = instruction.getData().get(1);
          if(this.registers.get(m) == this.registers.get(n)){
            this.countProgram = instruction.getData().get(2);
          } else {
            this.countProgram++; 
          }
          break;
      }
    } else {
    }
  }

  /**
   * 
   * Method that executes the program loaded in the URM without interruptions.
   * 
   */
  public void runCompleteProgram(){
    while(this.countProgram < this.program.getSize()){
      Instruction instruction = this.program.getInstruction(countProgram);
      String code = instruction.getCode();
      switch(code) {
        case "Z":
          this.registers.set(instruction.getData().get(0), 0);
          this.countProgram++;
          break;
        case "S":
          int s = this.registers.get(instruction.getData().get(0));
          this.registers.set(instruction.getData().get(0), s+1);
          this.countProgram++;
          break;
        case "T":
          int copy = this.registers.get(instruction.getData().get(0));
          this.registers.set(instruction.getData().get(1), copy);
          this.countProgram++;
          break;
        default:
          int m = instruction.getData().get(0);
          int n = instruction.getData().get(1);
          if(this.registers.get(m) == this.registers.get(n)){
            this.countProgram = instruction.getData().get(2);
          } else {
            this.countProgram++; 
          }
          break;
      }
    }
  }

  @Override
  public String toString() {
    String output = "";
    for(int i = 0; i < this.registers.size(); i++){
      output = output + "R" + i + ": " + this.registers.get(i) + ", ";
    }
    output = output + "\n" + "count program: " + this.countProgram;
    return output;
  }

  /**
   * 
   * Prevents index out of bounds exceptions
   * 
   * @param size New size for the registers
   */
  private void ensureSize(int size) {
    this.registers.ensureCapacity(size);
    while (this.registers.size() < size) {
      this.registers.add(0);
    }
  }

}