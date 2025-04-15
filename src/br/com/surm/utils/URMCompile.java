package br.com.surm.utils;

import java.util.ArrayList;

import br.com.surm.core.Instruction;
import br.com.surm.core.Program;

public class URMCompile {
  
  private String regexZS;
  private String regexT;
  private String regexJ;

  public URMCompile(){
    this.regexZS = "(0|[1-9][0-9]*)";
    this.regexT = "(0|[1-9][0-9]*),(0|[1-9][0-9]*)";
    this.regexJ = "(0|[1-9][0-9]*),(0|[1-9][0-9]*),([1-9][0-9]*)";
  }

  public Program compile(ArrayList<String> inputs) {
    ArrayList<Instruction> instructions = new ArrayList<>();
    for (int i = 0; i < inputs.size(); i++) {
      String tmp = inputs.get(i);
      char C = tmp.charAt(0);
      switch (C) {
        case 'Z':
          if(this.isZeroOrSuccInstruction(tmp)){
            instructions.add(this.createZeroInstruction(tmp));
          } else {
            throw new InstructionsException("Error: The line " + (i+1) + " is not a well-defined Z instruction");
          }
          break;
        case 'S':
          if(this.isZeroOrSuccInstruction(tmp)){
            instructions.add(this.createSuccInstruction(tmp));
          } else {
            throw new InstructionsException("Error: The line " + (i+1) + " is not a well-defined S instruction");
          }
          break;
        case 'T':
          if(this.isTransferInstruction(tmp)){
            instructions.add(this.createTransferInstruction(tmp));
          } else {
            throw new InstructionsException("Error: The line " + (i+1) + " is not a well-defined J instruction");
          }
          break;
        case 'J':
          if(this.isJumpInstruction(tmp)){
            instructions.add(this.createJumpInstruction(tmp));
          } else {
            throw new InstructionsException("Error: The line " + (i+1) + " is not a well-defined J instruction");
          }
          break;
        default:
          break;
      }
    }
    return new Program(instructions);
  }

  private boolean isZeroOrSuccInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    return nStr.matches(this.regexZS);
  }

  private boolean isTransferInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    return nStr.matches(this.regexT);
  }

  private boolean isJumpInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    return nStr.matches(this.regexJ);
  }

  private Instruction createZeroInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    ArrayList<Integer> values = new ArrayList<>();
    values.add(Integer.parseInt(nStr));
    return new Instruction("Z", values);
  }

  private Instruction createSuccInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    ArrayList<Integer> values = new ArrayList<>();
    values.add(Integer.parseInt(nStr));
    return new Instruction("S", values);
  }

  private Instruction createTransferInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    String[] vStr = nStr.split(",");
    ArrayList<Integer> values = new ArrayList<>();
    for (String value : vStr) {
      values.add(Integer.parseInt(value));
    }
    return new Instruction("T", values);
  }

  private Instruction createJumpInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    String[] vStr = nStr.split(",");
    ArrayList<Integer> values = new ArrayList<>();
    for (String value : vStr) {
      values.add(Integer.parseInt(value));
    }
    return new Instruction("J", values);
  }
}
