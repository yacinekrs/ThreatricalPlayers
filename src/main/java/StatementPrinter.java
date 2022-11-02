import java.text.NumberFormat;
import java.util.*;

public class StatementPrinter {

  public String print(Invoice invoice) {

    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

    StringBuffer result = new StringBuffer(String.format("Statement for %s\n", invoice.customer));
    invoice.generate();
    // print line for this order
    for (InvoiceItem invoiceItem : invoice.invoiceItemsList) {
      result.append(
          String.format("  %s: %s (%s seats)\n", invoiceItem.playName, frmt.format(invoiceItem.amount),
              invoiceItem.audience));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(invoice.totalAmount)));
    result.append(String.format("You earned %s credits\n", invoice.volumeCredits));
    return result.toString();
  }

  public String toHTML(Invoice invoice) {
    NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);
    StringBuffer result = new StringBuffer(String.format("""
      <!DOCTYPE html><html><style>table,th,td {border: 1px solid black;}</style><body><h1>Invoice</h1><ul><li><strong>Client : </strong>%s</li></ul><table style="width: 100%"><tr><th>Piece</th><th>Seats sold</th><th>Price</th></tr>""",
    invoice.customer));

    for (InvoiceItem invoiceItem : invoice.invoiceItemsList) {
      result.append(String.format("""
          <tr>
            <td>%s</td>
            <td>%s</td>
            <td>%s</td>
          </tr>""", invoiceItem.playName, invoiceItem.audience, frmt.format(invoiceItem.amount)));
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
              <i
                >Payment is required under 30 days. We can break your knees if you don\'t
                do so</i>
            </p>
          </body>
        </html>""", frmt.format(invoice.totalAmount), invoice.volumeCredits));

    return result.toString();
  }
}
