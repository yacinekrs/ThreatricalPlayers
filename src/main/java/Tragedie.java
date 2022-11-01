public class Tragedie extends Play {

  public Tragedie(String name) {
    super(name);
  }

  @Override
  public int calculeAmount(int audience) {
    int thisAmount = 400;
    if (audience > 30) {
      thisAmount += 10 * (audience - 30);
    }
    return thisAmount;
  }

}
