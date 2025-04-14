package br.com.surm.cli;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.surm.core.Instruction;
import br.com.surm.core.Program;

public class Lexer {

  public final Program compile(String[] inputs) {
    try {
      int c = 0;
      Pattern regexS = Pattern.compile("S\\((\\d+)\\)");
      Pattern regexT = Pattern.compile("T\\((\\\\d+),\\\\s*(\\\\d+)\\)");
      Pattern regexJ = Pattern.compile("J\\\\((\\\\d+),\\\\s*(\\\\d+),\\\\s*(\\\\d+)\\\\)");

      ArrayList<String> lexems = new ArrayList<>();
      ArrayList<Instruction> instructions = new ArrayList<>();
      for (String string : inputs) {
        char firstChar = string.charAt(0);
        switch (firstChar) {
          case 'Z':
                if(isZeroInstruction(c, string)){
                  instructions.add(this.convertStringToInstructionZ(string));
                } else {
                  throw new IllegalArgumentException(string + "  not is an instruction valid!");
                }
                break;
            case 'S':
                if(isSuccInstruction(c, string)){
                  instructions.add(this.convertStringToInstructionS(string));
                } else {
                  throw new IllegalArgumentException(string + "  not is an instruction valid!");
                }
                break;
            case 'T':
                System.out.println("É um T!");
                break;
            case 'J':
                System.out.println("É um J!");
                break;
            default:
            throw new IllegalArgumentException("The line " + (c +1) + " not start with Z, S, T or J.");
        }
        c++;
      }
    } catch (Exception e) {

    }
    return null;
  }

  private final static boolean isZeroInstruction(int line, String input) {
    try {
      Pattern regexZ = Pattern.compile("Z\\((\\d+)\\)");
      Matcher matcher = regexZ.matcher(input);
      if (matcher.matches()) {
        int n = Integer.parseInt(matcher.group(1));
        if (n < 0) {
          throw new IllegalArgumentException("In line " + (line +1) + " the instruction Z is receiving the value " + n);
        } else {
          return true;
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  private final static boolean isSuccInstruction(int line, String input) {
    try {
      Pattern regex = Pattern.compile("S\\((\\d+)\\)");
      Matcher matcher = regex.matcher(input);
      if (matcher.matches()) {
        int n = Integer.parseInt(matcher.group(1));
        if (n < 0) {
          throw new IllegalArgumentException("In line " + (line +1) + " the instruction S is receiving the value " + n);
        } else {
          return true;
        }
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return false;
  }

  private Instruction convertStringToInstructionZ(String input) {
    try {
      Pattern regex = Pattern.compile("Z\\((\\d+)\\)");
      Matcher matcher = regex.matcher(input);
      if (matcher.matches()) {
        String code = matcher.group(0);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(Integer.parseInt(matcher.group(1)));
        Instruction instruction = new Instruction(code, data);
        return instruction;
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return null;
  }

  private Instruction convertStringToInstructionS(String input) {
    try {
      Pattern regex = Pattern.compile("S\\((\\d+)\\)");
      Matcher matcher = regex.matcher(input);
      if (matcher.matches()) {
        String code = matcher.group(0);
        ArrayList<Integer> data = new ArrayList<>();
        data.add(Integer.parseInt(matcher.group(1)));
        Instruction instruction = new Instruction(code, data);
        return instruction;
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return null;
  }
}
