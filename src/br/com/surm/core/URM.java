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

  // IMPLEMENTE OUTRO CONSTRUTOR PARA A CLASSE URM QUE SATISFAZ DUAS CONDIÇÕES:
  // (1) O CONSTRUTOR DEVE GARANTIR QUE O PROGRAMA SERÁ CARREGADO.
  // (2) QUE UMA QUANTIDADE n DE REGISTRADORES SEJA INICIALIZADOS COM VALORES ESPECIFICOS (NÃO NECESSARIAMENTE SENDO 0).

}