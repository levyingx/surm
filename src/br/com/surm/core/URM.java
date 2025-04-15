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
import java.util.Collections;

import br.com.surm.core.instruction.Instruction;
import br.com.surm.core.instruction.JumpInstruction;
import br.com.surm.core.instruction.SuccInstruction;
import br.com.surm.core.instruction.TransferInstruction;
import br.com.surm.core.instruction.ZeroInstruction;

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
    this.countProgram = 1;
    this.registers = new ArrayList<>();
    this.addRegisters(10);
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
    this.countProgram = 1;
    this.registers = new ArrayList<>();
    for(int i = 0; i < values.length; i++) {
      this.registers.add(values[i]);
    }
  }

  /**
   * 
   * Constructor of the URM class, which loads the program and initializes n registers with a Vector of values on input.
   * 
   * @param program The program loaded into the URM.
   * @param values The n values to set in first n registers.
   */
  public URM(Program program, ArrayList<Integer> values) {
    this.program = program;
    this.countProgram = 1;
    this.registers = new ArrayList<>();
    for (int i : values) {
      this.registers.add(i);
    }
  }

  /**
   * 
   * The number of registers and values ​​are distinct.
   * 
   * @param program The program loaded into the URM.
   * @param registers The addresses of the registers.
   * @param values The n values to set in first on registers.
   */
  public URM(Program program, ArrayList<Integer> registers, ArrayList<Integer> values) {
    try {
      if(registers.size() != values.size()) {
        throw new IllegalArgumentException("The number of registers is different from the number of values.");
      } else {
        this.registers = new ArrayList<>();
        ArrayList<Integer> secureCopy = new ArrayList<>(registers);
        Collections.sort(secureCopy);
        int max = secureCopy.removeLast();
        this.addRegisters(max+1);
        for(int i = 0; i < registers.size(); i++) {
          int m = registers.get(i);
          int n = values.get(i);
          this.registers.set(m, n);
        }
      }
    } finally {
      this.program = program;
      this.countProgram = 1;
    }
  }

  /**
   * 
   * Method that executes only one instruction of the program loaded in the URM, this instruction being the one pointed to by the URM program counter.
   * 
   */
  public void runInstruction() {
    if(this.countProgram <= this.program.getSize()) {
      Instruction instruction = this.program.getInstruction(countProgram - 1);
      if(instruction instanceof ZeroInstruction) {
        ZeroInstruction tmp = (ZeroInstruction) instruction;
        int n = tmp.getData();
        this.registers.set(n, 0);
        this.countProgram++;
      } else {
        if(instruction instanceof SuccInstruction) {
          SuccInstruction tmp = (SuccInstruction) instruction;
          int n = tmp.getData();
          int v = this.registers.get(n) + 1;
          this.registers.set(n, v);
          this.countProgram++;
        } else {
          if(instruction instanceof TransferInstruction) {
            TransferInstruction tmp = (TransferInstruction) instruction;
            int m = tmp.getFirstData();
            int n = tmp.getSecondData();
            int v = this.registers.get(m);
            this.registers.set(n, v);
            this.countProgram++;
          } else {
            JumpInstruction tmp = (JumpInstruction) instruction;
            int m = tmp.getFirstData();
            int n = tmp.getSecondData();
            int p = tmp.getThirdData();
            if(this.registers.get(m) == this.registers.get(n)) {
              this.countProgram = p;
            } else {
              this.countProgram++;
            }
          }
        }
      }
    }
  }

  /**
   * 
   * Returns the instruction number pointed to by the URM's program counter.
   * 
   * @return the value on count of the program.
   */
  public int getCountProgram() {
    return this.countProgram;
  }

  /**
   * 
   * Returns the program that was loaded by the URM.
   * 
   * @return the program.
   */
  public Program getProgram() {
    return this.program;
  }

  /**
   * 
   * Return to the next instruction that is loaded to be executed.
   * 
   * @return the next instruction.
   */
  public Instruction getNextInstruction() {
    return this.program.getInstruction(this.countProgram - 1);
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
    String output = "Registers:\n";
    for(int i = 0; i < this.registers.size(); i++){
      if(i % 2 == 0) {
        if(i < this.registers.size() - 1) {
          output = output + "R" + i + ": " + this.registers.get(i) + ", ";
        } else {
          output = output + "R" + i + ": " + this.registers.get(i);
        }
      } else {
        output = output + "R" + i + ": " + this.registers.get(i) + "\n";
      }
    }
    output = output + "\n" + "count program: " + this.countProgram + "\n";
    return output;
  }

}