import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice, Map<String, Play> plays) {
    float totalAmount = 0;
    int volumeCredits = 0;
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    
    StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.customer));

    for (Performance perf : invoice.performances) {
      Play play = plays.get(perf.playID);
      int thisAmount = play.CalculeAmount(perf);
     
      // print line for this order
      result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount), perf.audience));
     
      totalAmount += thisAmount;
      //volumeCredits += Math.max(perf.audience - 30, 0);
      volumeCredits += play.CalculeBonus(perf);
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(totalAmount)));
    result.append(String.format("You earned %s credits\n", volumeCredits)) ;
    String fullMessage =result.toString();
    return fullMessage;
  }

}
