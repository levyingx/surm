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

  public String getCode(){
    return this.code;
  }

}
