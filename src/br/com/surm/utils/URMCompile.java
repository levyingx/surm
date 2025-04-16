package br.com.surm.utils;

import java.util.ArrayList;

import br.com.surm.core.Program;
import br.com.surm.core.instruction.Instruction;
import br.com.surm.core.instruction.InstructionManyArgs;
import br.com.surm.core.instruction.InstructionOneArg;

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
      if(C == 'S' || C == 'Z') {
        if(this.isInstructionOneArgValid(tmp)){
          instructions.add(this.createInstructionOneArg(tmp));
        } else {
          throw new InstructionsException("The instruction " + tmp + " not is a well-formed instruction!");
        }
      } else {
        if(this.isInstructionManyArgsValid(tmp)){
          instructions.add(this.createInstructionManyArgs(tmp));
        } else {
          throw new InstructionsException("The instruction " + tmp + " not is a well-formed instruction!");
        }
      }
    }
    return new Program(instructions);
  }

  /**
   * 
   * Method that checks whether a word represents a well-formed instruction of an argument, that is, it checks whether the word is of the 
   * form S(n) or Z(n), where n is an integer greater than or equal to 0.
   * 
   * @param input An input word.
   * @return True if is well-formed instruction, false otherwise.
   */
  private boolean isInstructionOneArgValid(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    return nStr.matches(this.regexZS);
  }

  /**
   * 
   * Method that checks whether a word represents a well-formed instruction of many arguments, that is, it checks whether the word is of the 
   * form T(m,n) or J(m,n,p), where m, n is an integer greater or equal than 0 and p is an integer greater than 0.
   * 
   * @param input An input word.
   * @return True if is well-formed instruction, false otherwise.
   */
  private boolean isInstructionManyArgsValid(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String nStr = input.substring(start + 1, end);
    return nStr.matches(this.regexT) || nStr.matches(this.regexJ);
  }

  /**
   * 
   * Method that creates S or Z instructions from an input word.
   * 
   * @param input An input word.
   * @return An instruction S or Z.
   */
  private InstructionOneArg createInstructionOneArg(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String code = input.substring(0, 1);
    String nStr = input.substring(start + 1, end);
    int data = Integer.parseInt(nStr);
    return new InstructionOneArg(code, data);
  }

  /**
   * 
   * Method that creates T or J instructions from an input word.
   * 
   * @param input An input word.
   * @return An instruction T or J.
   */
  private InstructionManyArgs createInstructionManyArgs(String input) {
    int start = input.indexOf('(');
    int end = input.indexOf(')');
    String code = input.substring(0, 1);
    String[] vStr = input.substring(start + 1, end).split(",");
    ArrayList<Integer> values = new ArrayList<>();
    for(int i = 0; i < vStr.length; i++){
      values.add(Integer.parseInt(vStr[i]));
    }
    return new InstructionManyArgs(code, values);
  }

}
