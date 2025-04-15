package br.com.surm.utils;

import java.util.ArrayList;

import br.com.surm.core.Instruction;
import br.com.surm.core.Program;

public class URMCompile {
  
  private String regexS;
  private String regexJ;

  public URMCompile(){
    this.regexS = "s\\((0|[1-9][0-9]*)\\)";
    this.regexJ = "j\\((0|[1-9][0-9]*),(0|[1-9][0-9]*),([1-9][0-9]*)\\)";
  }

  public Program compile(String[] inputs) {
    ArrayList<Instruction> instructions = new ArrayList<>();
    for (int i = 0; i < inputs.length; i++) {
      String tmp = inputs[0];
      char C = tmp.charAt(0);
      switch (C) {
        case 'S':
          if(this.isSuccInstruction(tmp)){
            instructions.add(this.createSuccInstruction(tmp));
          } else {
            throw new InstructionsException("Error: The line " + (i+1) + " is not a well-defined S instruction");
          }
          break;
        case 'J':
          if(this.isJumpInstruction(tmp)){
            instructions.add(this.createSuccInstruction(tmp));
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

  private boolean isSuccInstruction(String input) {
    return input.matches(this.regexS);
  }

  private boolean isJumpInstruction(String input) {
    return input.matches(this.regexJ);
  }

  private Instruction createSuccInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    ArrayList<Integer> values = new ArrayList<>();
    values.add(Integer.parseInt(nStr));
    return new Instruction("S", values);
  }
}
