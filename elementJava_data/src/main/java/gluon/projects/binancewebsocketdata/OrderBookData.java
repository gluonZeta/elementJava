package gluon.projects.binancewebsocketdata;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class OrderBookData {

    private List<OrderBookOrderInformation> bids;

    private List<OrderBookOrderInformation> asks;

    public void fillBidsAndAsks(JSONArray bids, JSONArray asks) {
        List<OrderBookOrderInformation> bidsElements = new ArrayList<>();
        List<OrderBookOrderInformation> asksElements = new ArrayList<>();
        JSONArray arrayElement;
        float price;
        float quantity;

        for(int i = 0; i < bids.length(); i++) {
            arrayElement = (JSONArray) bids.get(i);
            price = Float.parseFloat((String) arrayElement.get(0));
            quantity = Float.parseFloat((String) arrayElement.get(1));
            bidsElements.add(new OrderBookOrderInformation(price, quantity));
        }
        this.bids = bidsElements;

        for(int i = 0; i < asks.length(); i++) {
            arrayElement = (JSONArray) asks.get(i);
            price = Float.parseFloat((String) arrayElement.get(0));
            quantity = Float.parseFloat((String) arrayElement.get(1));
            asksElements.add(new OrderBookOrderInformation(price, quantity));
        }
        this.asks = asksElements;
    }

    @Override
    public String toString() {
        StringBuilder bidsAsks = new StringBuilder();
        for(OrderBookOrderInformation bid: bids) {
            bidsAsks.append(bid.getPrice()).append(";");
        }
        bidsAsks.deleteCharAt(bidsAsks.length() - 1);
        bidsAsks.append("\n");

        for(OrderBookOrderInformation ask: asks) {
            bidsAsks.append(ask.getPrice()).append(";");
        }
        bidsAsks.deleteCharAt(bidsAsks.length() - 1);
        return bidsAsks.toString();
    }

}
