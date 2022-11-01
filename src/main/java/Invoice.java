import java.util.*;

public class Invoice {

  public String customer;
  private List<Performance> performances;
  private Map<String, Play> playsMap;
  public int volumeCredits;
  public double totalAmount;
  public List<InvoiceItem> invoiceItemsList;

  public Invoice(String customer, List<Performance> performances, Map<String, Play> playsMap) {
    this.customer = customer;
    this.performances = performances;
    this.playsMap = playsMap;
  }

  public void generate() {
    this.totalAmount = 0;
    this.volumeCredits = 0;
    this.invoiceItemsList = new ArrayList<>();

    for (Performance perf : performances) {
      Play play = playsMap.get(perf.playID);

      double amount = play.calculeAmount(perf.audience);

      this.invoiceItemsList.add(new InvoiceItem(play.getName(), perf.audience, amount));
      this.totalAmount += amount;
      this.volumeCredits += play.calculeCredit(perf.audience);
    }

  }

}
