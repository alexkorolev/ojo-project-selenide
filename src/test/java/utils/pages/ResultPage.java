package utils.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ResultPage {

    public static SelenideElement insiderWindowExitBtn = $x("//div[@class='flex-center']//div[@tabindex='-1']/div[1]");
    public static SelenideElement btnShowMore = $x("//button[contains(normalize-space(@class),'qa-moreResultsBtn')]");
    public static ElementsCollection xpathPriceRegularTickets = $$x("//div[contains(normalize-space(@class),'qa-tripOptionPrice')]");
    public static SelenideElement cheapestTab = $x("//div[contains(normalize-space(@class),'qa-searchSortTab0')]");

    public static float getPriceAltTickets(){
        return Float.parseFloat($x("//div[contains(normalize-space(@class),'qa-altDatePQPrice')]")
                .getText().replaceAll("[$]", ""));
    }

    public static ArrayList<Float> getPriceRegularTickets(){
        ElementsCollection xpathPriceRegularTickets = $$x("//div[contains(normalize-space(@class),'qa-tripOptionPrice')]");
        ArrayList<Float> RegularTickets = new ArrayList<>();
        for (int i = 0; i < xpathPriceRegularTickets.size(); i++){
            float x =  Float.parseFloat(xpathPriceRegularTickets.get(i).getText().replaceAll("[$]", ""));
            RegularTickets.add(x);
        }
        return RegularTickets;
    }

    public static void checkPriceRegularTickets(ArrayList<Float> prices){
        for (int i = 0; i < prices.size(); i++){
            Assert.isTrue(prices.get(i) > 0, "Regular ticket have't price");
        }
    }

    public static void compareNewOldListPrices(ArrayList<Float> oldList, ArrayList<Float> newList){
        for (int i = 0; i < oldList.size(); i++){
            for (int y = oldList.size(); y < newList.size(); y++){
                Assert.isTrue(oldList.get(i) != newList.get(y),
                        "priceRegularTickets in old and new list can't be equal!");
            }
        }
    }

    public static void checkSortTickets (ArrayList<Float> oldList, ArrayList<Float> newList){
        for (int i = 0; i < oldList.size(); i++){
            Assert.isTrue(newList.get(i) == oldList.get(i),"Price sorting does't match.");
        }
    }
}
