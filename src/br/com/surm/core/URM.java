package br.com.surm.core;

import java.util.ArrayList;

/**
 * 
 * This class implements the definition of the Unlimited Register Machine, as presented in the book, 
 * Computability: An Introduction to Recursive Function Theory by Nigel Cutland.
 * 
 * @author Valdigleis (valdigleis@dimap.ufrn.br)
 * @author INSIRA_SEU_NOME (INSIRA_SEU_EMAIL)
 * @version 1.0
 */
public class URM {

  private ArrayList<Integer> registers;
  private int countProgram;
  private Program program;

  /**
   * Constructor of the URM class, which loads the program and initializes the first 10 registers with a value of 0.
   * 
   * @param program The program loaded into the URM.
   */
  public URM(Program program) {
    this.program = program;
    this.countProgram = 0;
    this.registers = new ArrayList<>(10);
    for(int i = 0; i < 10; i++) {
      this.registers.set(i, 0);
    }
  }

  public URM(Program program, int[] values){
    this.program = program;
    this.countProgram = 0;
    this.registers = new ArrayList<>(values.length);
    for(int i = 0; i < values.length; i++) {
      this.registers.set(i, values[i]);
    }
  }

  public URM(Program program, ArrayList<Integer> registers, int[] values){
    this.countProgram = 0;
    int max = 0;
    for(int i = 0; i < registers.size(); i++){
      if(max < registers.get(i)){
        max = registers.get(i);
      }
    }
    this.registers = new ArrayList<>(max);
    for(int i = 0; i < registers.size(); i++){
      if(registers.contains(i)){
        this.registers.set(i, values[i]);
      }else {
        this.registers.set(i, 0);
      }
    }
  }

  public void execution(){
    while(this.countProgram < this.program.getSize()){
      Instruction instruction = this.program.getInstruction(countProgram);
      String code = instruction.getCode();
      switch(code) {
        case "Z":
          break;
        case "S":
          break;
        case "T":
          break;
        default:
          break;
      }
    }
  }

}