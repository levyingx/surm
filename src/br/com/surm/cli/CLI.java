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

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import br.com.surm.core.Program;
import br.com.surm.utils.URMCompile;


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
      boolean hasH = false;
      boolean hasV = false;
      boolean hasR = false;
      boolean hasP = false;

      int indexV = -1;
      int indexR = -1;
      int indexP = -1;

      // get options and values
      for (int i = 0; i < args.length; i++) {
        if(args[i].equals("--H")){
          hasH = true;
        } else {
          if(args[i].equals("--V")){
            hasV = true;
            indexV = i;
          } else {
            if(args[i].equals("--R")){
              hasR = true;
              indexR = i;
            } else {
              if(args[i].equals("--P")){
                hasP = true;
                indexP = i;
              }
            }
          }
        }
      }

      if (hasH) {
        if (hasV || hasR || hasP) {
          throw new IllegalArgumentException("The --H argument must be used alone!");
        } else {
          runHelp();
        }
      }

      ArrayList<Integer> values = new ArrayList<>();

      if (hasV) {
        if (hasR) {
          throw new IllegalArgumentException("The --R and --V arguments should not be used together!");
        } else {
          if (hasP) {
            for (int i = indexV + 1; i < indexP; i++) {
              values.add(Integer.parseInt(args[i]));
            }
          } else {
            throw new IllegalArgumentException("The --V argument must be used with the --P argument!");
          }
        }
      }

      if (hasV && values.isEmpty()) {
        throw new IllegalArgumentException("The --V argument must be followed by n non-negative integer values.");
      }

      if (hasR) {
        if (hasP) {
          for (int i = indexR + 1; i < indexP; i++) {
            values.add(Integer.parseInt(args[i]));
          }
        } else {
          throw new IllegalArgumentException("The --R argument must be used with the --P argument!");
        }
      }

      if (hasR && (values.isEmpty() || values.size() % 2 == 1)) {
        throw new IllegalArgumentException("The --V argument must be followed by 2*n non-negative integer values.");
      }

      for(int i = 0; i < values.size(); i++){
        if(values.get(i) < 0){
          throw new IllegalArgumentException("The value " + values.get(i) + " is less than zero, cannot be accepted!");
        }
      }

      String filePath = args[indexP + 1];
      
      BufferedReader reader = new BufferedReader(new FileReader(filePath));
      ArrayList<String> lines = new ArrayList<>();
      String line;
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }

      URMCompile urmc = new URMCompile();
      Program program = urmc.compile(lines);

    

    } catch (Exception e) {
      System.err.println("Error: " + e.getMessage());
    }
  }

  private static String runHelp() {
    return null;
  }
  
}
