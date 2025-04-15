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

/**
 * 
 * This class implements the instructions execute on Unlimited Register Machine, as presented in the book 
 * Computability: An Introduction to Recursive Function Theory by Nigel Cutland.
 * 
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @author INSIRA_SEU_NOME (INSIRA_SEU_EMAIL)
 * @version 1.0
 */
public class Instruction {
  
  private String code;
  private ArrayList<Integer> data;

  /**
   * Constructor method of the Instruction class, which converts a String and an ArrayList of integers into 
   * an instruction that can be executed by a URM.
   * 
   * @param code The type of instruction.
   * @param data The infos used by instruction.
   */
  public Instruction(String code, ArrayList<Integer> data) {
    this.code = code;
    this.data = data;
  }

  /**
   * 
   * Method that returns the code of Instruction.
   * 
   * @return The code id: Z, S, T or J.
   */
  public String getCode(){
    return this.code;
  }

  public ArrayList<Integer> getData() {
    return this.data;
  }

  @Override
  public String toString() {
    String output = (this.code + "(" + this.data.toString() + ")").replace("[", "");
    return output.replace("]", "");
  }

}
