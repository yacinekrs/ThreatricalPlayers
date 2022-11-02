import java.util.*;

public class Invoice {

  public Customer customer;
  private List<Performance> performances;
  private Map<String, Play> playsMap;
  public double totalAmount;
  public List<InvoiceItem> invoiceItemsList;

  public Invoice(Customer customer, List<Performance> performances, Map<String, Play> playsMap) {
    this.customer = customer;
    this.performances = performances;
    this.playsMap = playsMap;
  }

  public void generate() {
    this.totalAmount = 0;
    this.invoiceItemsList = new ArrayList<>();

    for (Performance perf : performances) {
      Play play = playsMap.get(perf.playID);
      double amount = play.calculeAmount(perf.audience);

      this.invoiceItemsList.add(new InvoiceItem(play.getName(), perf.audience, amount));
      this.totalAmount += amount;
      this.customer.soldePointFid += play.calculeCredit(perf.audience);
    }

    if (this.customer.soldePointFid >= 150) {
      this.totalAmount -= 15;
      this.customer.soldePointFid -= 150;
    }
  }

}
