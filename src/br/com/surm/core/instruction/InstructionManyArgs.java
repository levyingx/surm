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
package br.com.surm.core.instruction;

import java.util.ArrayList;

/**
 * 
 * This class implements the definition of the transfer and Jump instructions used on Unlimited Register Machine, as presented in the book,
 * Computability: An Introduction to Recursive Function Theory by Nigel Cutland. 
 * 
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @version 1.0
 */
public class InstructionManyArgs extends Instruction {

  private int[] data;

  public InstructionManyArgs(String code, ArrayList<Integer> data) {
    super(code);
    this.data = new int[data.size()];
    for (int i = 0; i < data.size(); i++) {
      this.data[i] = data.get(i);
    }
  }

  /**
   * 
   * Returns a specific argument present in the instruction.
   * 
   * @param argNumber The argument number
   * @return
   */
  public int getSpecificArg(int argNumber) {
    return this.data[argNumber];
  }

  @Override
  public String getCode() {
    return super.getCode();
  }

  @Override
  public String toString() {
    String output = getCode() + "(";
    for(int i = 0; i < this.data.length; i++) {
      if(i < this.data.length - 1){
        output = output + this.data[i] + ", ";
      } else {
        output = output + this.data[i];
      }
    }
    return  output + ")";
  }
  
}
