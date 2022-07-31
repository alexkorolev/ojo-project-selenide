package suites;

import dev.failsafe.internal.util.Assert;
import org.junit.jupiter.api.Test;
import utils.TestInitializer;
import utils.pages.ResultPage;

import java.util.Collections;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertAll;

public class ResultPageTest extends TestInitializer {

    @Test
    void searchResultPriceRegularTicketsTest() {

        float priceAltTickets = ResultPage.getPriceAltTickets();

        ArrayList<Float> priceRegularTickets = ResultPage.getPriceRegularTickets();
        ResultPage.checkPriceRegularTickets(priceRegularTickets);

        assertAll(
                ()-> Assert.isTrue(priceAltTickets > 0, "Alternative ticket have't price!"),
                ()-> Assert.isTrue(priceAltTickets <= priceRegularTickets.get(0),
                        "Alternative ticket should be cheaper than other tickets!")
        );

        ResultPage.xpathPriceRegularTickets.get(ResultPage.xpathPriceRegularTickets.size()-1).scrollTo();
        ResultPage.btnShowMore.click();

        ArrayList<Float> newPriceRegularTickets = ResultPage.getPriceRegularTickets();
        assertAll(
                ()-> Assert.isTrue(priceRegularTickets.size() < newPriceRegularTickets.size() ,
                        "New tickets are't loading!")
        );

        ResultPage.compareNewOldListPrices(priceRegularTickets, newPriceRegularTickets);
    }

    @Test
    void checkSortResultPrice() {

        float priceAltTickets = ResultPage.getPriceAltTickets();

        ResultPage.cheapestTab.click();
        ArrayList<Float> priceRegularTickets = ResultPage.getPriceRegularTickets();

        ArrayList<Float> priceRegularTicketSorted = priceRegularTickets;
        Collections.sort(priceRegularTicketSorted);

        assertAll(
                ()-> Assert.isTrue(priceAltTickets <= priceRegularTickets.get(0),
                        "Alternative ticket should be cheaper than other tickets!")
        );

        ResultPage.checkSortTickets(priceRegularTickets, priceRegularTicketSorted);
        System.out.println(priceRegularTickets);
    }
}

