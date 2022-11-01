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
          String.format("  %s: %s (%s seats)\n", invoiceItem.playName, frmt.format(invoiceItem.amount), invoiceItem.audience));
    }
    result.append(String.format("Amount owed is %s\n", frmt.format(invoice.totalAmount)));
    result.append(String.format("You earned %s credits\n", invoice.volumeCredits));
    return result.toString();
  }

}
