public class Comedie extends Play {

  public Comedie(String name) {
    super(name);
  }

  @Override
  public int calculeAmount(int audience) {
    int thisAmount = 300 + 3 * audience;
    if (audience > 20) {
      thisAmount += 100 + 5 * (audience - 20);
    }
    return thisAmount;
  }

  @Override
  public double calculeCredit(int audience) {
    return super.calculeCredit(audience) + Math.floor(audience / 5);
  }

}
