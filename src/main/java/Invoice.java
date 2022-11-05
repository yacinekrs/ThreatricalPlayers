import java.util.*;
import java.text.NumberFormat;

public class Invoice {
  private static int CREDIT_THRESHOLD = 150;
  private static int REDUCTION = 15;
  
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

  private void generate() {
    this.totalAmount = 0;
    this.invoiceItemsList = new ArrayList<>();

    for (Performance perf : performances) {
      Play play = playsMap.get(perf.playID);
      double amount = play.calculeAmount(perf.audience);

      this.invoiceItemsList.add(new InvoiceItem(play.getName(), perf.audience, amount));
      this.totalAmount += amount;
      this.customer.soldePointFid += play.calculeCredit(perf.audience);
    }

    if (this.customer.soldePointFid >= CREDIT_THRESHOLD) {
      this.totalAmount -= REDUCTION;
      this.customer.soldePointFid -= CREDIT_THRESHOLD;
    }
  }

  public String toText() {
    this.generate();
    
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", this.customer.name));

    for (InvoiceItem invoiceItem : this.invoiceItemsList) {
      result.append(
          String.format("  %s: %s (%s seats)\n", invoiceItem.playName, frmt.format(invoiceItem.amount),invoiceItem.audience));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(this.totalAmount)));
    result.append(String.format("You earned %s credits\n", this.customer.soldePointFid));
    return result.toString();
  }

  public String toHTML() {
    this.generate();

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer result = new StringBuffer(String.format("""
      <!DOCTYPE html>
      <html>
        <style>
          table,th,td {
            border: 1px solid black;
          }
        </style>
        <body>
          <h1>Invoice</h1>
          <ul>
            <li><strong>Client : </strong>%s</li>
          </ul>
          <table>
            <tr>
              <th>Piece</th>
              <th>Seats sold</th>
              <th>Price</th>
            </tr>
              """,this.customer.name));

    for (InvoiceItem invoiceItem : this.invoiceItemsList) {
      result.append(String.format("""
                <tr>
                  <td>%s</td>
                  <td>%s</td>
                  <td>%s</td>
                </tr>
          """, invoiceItem.playName, invoiceItem.audience, frmt.format(invoiceItem.amount)));
    }
    result.append(String.format("""
                  <tr>
                    <th colspan="2" align="right">Total owned:</th>
                    <td>%s</td>
                  </tr>
                  <tr>
                    <th colspan="2" align="right">Fidelity point earned:</th>
                    <td>%s</td>
                  </tr>
                </table>
                <p>
                  <i>Payment is required under 30 days. We can break your knees if you don\'t do so</i>
                </p>
              </body>
            </html>
            """, frmt.format(this.totalAmount), this.customer.soldePointFid));

    return result.toString();
  }
}
