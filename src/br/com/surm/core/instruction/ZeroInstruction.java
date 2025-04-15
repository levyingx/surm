package br.com.surm.core.instruction;

public class ZeroInstruction extends Instruction {

  private final int data;

  public ZeroInstruction(String code, int data) {
    super(code);
    this.data = data;
  }

  public int getData() {
    return data;
  }
  
  @Override
  public String toString() {
    return super.toString() + "(" + this.data + ")";
  }
  
}
