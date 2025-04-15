package br.com.surm.utils;

import java.util.ArrayList;

import br.com.surm.core.Program;
import br.com.surm.core.instruction.Instruction;
import br.com.surm.core.instruction.JumpInstruction;
import br.com.surm.core.instruction.SuccInstruction;
import br.com.surm.core.instruction.TransferInstruction;
import br.com.surm.core.instruction.ZeroInstruction;

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

  private ZeroInstruction createZeroInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    int n = Integer.parseInt(nStr);
    return new ZeroInstruction("Z", n);
  }

  private SuccInstruction createSuccInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    int n = Integer.parseInt(nStr);
    return new SuccInstruction("S", n);
  }

  private TransferInstruction createTransferInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    String[] vStr = nStr.split(",");
    int valueA = Integer.parseInt(vStr[0]);
    int valueB = Integer.parseInt(vStr[1]);
    return new TransferInstruction("T", valueA, valueB);
  }

  private JumpInstruction createJumpInstruction(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    String[] vStr = nStr.split(",");
    int valueA = Integer.parseInt(vStr[0]);
    int valueB = Integer.parseInt(vStr[1]);
    int valueC = Integer.parseInt(vStr[2]);
    return new JumpInstruction("J", valueA, valueB, valueC);
  }
}
