public class Comedie extends Play {

  public Comedie (String name){
    super(name);
  }
  public int CalculeAmount(Performance perf){
  int thisAmount = 300 + 3 * perf.audience;;
  if (perf.audience > 20) {
    thisAmount += 100 + 5 * (perf.audience - 20);
   
  }
  return thisAmount;
}

  public int CalculeBonus(Performance perf){
    int volumeCredits = 0;
    volumeCredits += Math.max(perf.audience - 30, 0);
    volumeCredits += Math.floor(perf.audience / 5);
    return volumeCredits;
  }
  
  
}
