public class Tragedie extends Play {
  
public Tragedie (String name){
  super(name);
}

public int CalculeAmount(Performance perf){
  int thisAmount = 400;
  if (perf.audience > 30) {
    thisAmount += 10 * (perf.audience - 30);
  }
  return thisAmount;
}
  
public int CalculeBonus(Performance perf){
 return Math.max(perf.audience - 30, 0);
}
}
