package br.com.surm.core.instruction;

public class JumpInstruction extends Instruction {

  private final int firstData;
  private final int secondData;
  private final int thirdData;

  public JumpInstruction(String code, int firstData, int secondData, int thirdData) {
    super(code);
    this.firstData = firstData;
    this.secondData = secondData;
    this.thirdData = thirdData;
  }

  public int getFirstData() {
    return firstData;
  }

  public int getSecondData() {
    return secondData;
  }

  public int getThirdData() {
    return thirdData;
  }

  @Override
  public String toString() {
    return super.getCode() + "(" + this.firstData + "," + this.secondData + "," + this.thirdData + ")";
  }

  
}
