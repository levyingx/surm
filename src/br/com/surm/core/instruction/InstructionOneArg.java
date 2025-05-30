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

/**
 * 
 * This class implements the definition of the zero and sucessor instructions used on Unlimited Register Machine, as presented in the book,
 * Computability: An Introduction to Recursive Function Theory by Nigel Cutland. 
 * 
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @version 1.0
 */
public class InstructionOneArg extends Instruction {

  private final int data;

  /**
   * 
   * Constructor of the ZeroInstruction class, using a code value and a data..
   * 
   * @param code The instruction code (Z or S).
   * @param data The data for the instruction.
   */
  public InstructionOneArg(String code, int data) {
    super(code);
    this.data = data;
  }

  /**
   * 
   * Returns the register number (register address) used in the instruction.
   * 
   * @return 
   */
  public int getData() {
    return data;
  }
  
  @Override
  public String toString() {
    return super.toString() + "(" + this.data + ")";
  }
  
}
