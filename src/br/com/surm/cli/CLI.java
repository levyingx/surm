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
package br.com.surm.cli;

import java.util.HashMap;
import java.util.Set;

/**
 * 
 * This class is responsible for implementing the notion of a small command line interpreter.
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @author Definir outra hora
 * @version 1.0
 */
public class CLI {

  public static void main(String[] args) throws Exception {
    try {
      if(args.length == 0) {
        throw new IllegalArgumentException("Error: No parameters used!");
      } else {
        HashMap<String, Integer> count = countOccurrences(args);
        if(count.get("H") > 0 && (count.get("F") + count.get("R")) > 0) {
          throw new IllegalArgumentException("Error: The --H parameter must be used alone.");
        } else {

        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
  }

  /**
   * 
   * Counts the number of times arguments were used on the command line.
   * 
   * @param args The arguments
   * @return A table containing the parameters (table keys) and the value of how many times each parameter was used.
   */
  private static HashMap<String, Integer> countOccurrences(String[] args) {
    HashMap<String, Integer> count = new HashMap<>();
    count.put("H", 0);
    count.put("F", 0);
    count.put("R", 0);
    for (String arg : args) {
      if(arg.equals("--H")) {
        count.put("H", count.get("H") + 1);
      } else {
        if(arg.equals("--F")) {
          count.put("F", count.get("F") + 1);
        } else {
          count.put("R", count.get("R") + 1);
        }
      }
    }
    return count;
  }
}
