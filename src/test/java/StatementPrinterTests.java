import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.approvaltests.Approvals.verify;

public class StatementPrinterTests {

    @Test
    void exampleStatement() {
        Map<String, Play> plays = Map.of(
                "hamlet",  new Tragedie("Hamlet"),
                "as-like", new Comedie("As You Like It"),
                "othello", new Tragedie("Othello"));

        Invoice invoice = new Invoice("BigCo", List.of(
                new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        verify(result);
    }

    // @Test
    // void statementWithNewPlayTypes() {
    //     Map<String, Play> plays = Map.of(
    //             "henry-v",  new Play("Henry V", "history"),
    //             "as-like", new Play("As You Like It", "pastoral"));

    //     Invoice invoice = new Invoice("BigCo", List.of(
    //             new Performance("henry-v", 53),
    //             new Performance("as-like", 55)));

    //     StatementPrinter statementPrinter = new StatementPrinter();
    //     Assertions.assertThrows(Error.class, () -> {
    //         statementPrinter.print(invoice, plays);
    //     });
    // }
}
