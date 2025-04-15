package br.com.surm.core.instruction;

public class TransferInstruction extends Instruction {

  private final int firstData;
  private final int secondData;

  public TransferInstruction(String code, int firstData, int secondData) {
    super(code);
    this.firstData = firstData;
    this.secondData = secondData;
  }


  public int getFirstData() {
    return firstData;
  }

  public int getSecondData() {
    return secondData;
  }

  @Override
  public String getCode() {
    return super.getCode();
  }

  @Override
  public String toString() {
    return getCode() + "(" + this.firstData + "," + this.secondData + ")";
  }
  
}
