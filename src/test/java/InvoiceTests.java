import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import static org.approvaltests.Approvals.verify;

public class InvoiceTests {

    @Test
    void toTextTest() {
        Map<String, Play> playsMap = Map.of(
                "hamlet", new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));

        Invoice invoice = new Invoice(new Customer("BigCo", "1123AB", 150), List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)),playsMap);

        String result = invoice.toText();
        verify(result);
    }
    
    @Test
    void toTextTestComedieInf() {
        Map<String, Play> playsMap = Map.of(
                "hamlet", new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));

        Invoice invoice = new Invoice(new Customer("BigCo", "1123AB", 150), List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 15),
                new Performance("othello", 40)),playsMap);

        String result = invoice.toText();
        verify(result);
    }

    @Test
    void toTextTestTragedieInf() {
        Map<String, Play> playsMap = Map.of(
                "hamlet", new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));

        Invoice invoice = new Invoice(new Customer("BigCo", "1123AB", 150), List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 25)),playsMap);

        String result = invoice.toText();
        verify(result);
    }

    @Test
    void toTextTestSoldPntInf() {
        Map<String, Play> playsMap = Map.of(
                "hamlet", new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));

        Invoice invoice = new Invoice(new Customer("BigCo", "1123AB", 10), List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 25)),playsMap);

        String result = invoice.toText();
        verify(result);
    }
    
    @Test
    void toHTMLTest() {
        Map<String, Play> playsMap = Map.of(
                "hamlet", new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));
       
        Invoice invoice = new Invoice(new Customer("BigCo", "1123AB", 150), List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)),playsMap);

        String result = invoice.toHTML();
        verify(result);
    }
}
