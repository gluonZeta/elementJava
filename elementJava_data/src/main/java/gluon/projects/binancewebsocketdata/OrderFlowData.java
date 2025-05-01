package gluon.projects.binancewebsocketdata;

import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
public class OrderFlowData {

    private boolean isSellerAttack;

    private float quantity;

    private float price;

    private Date tradingTime;

    public float getTotal() {
        return quantity * price;
    }

    public void fillOrderFlowData(JSONObject orderFlowBinanceData) {
        this.isSellerAttack = (boolean) orderFlowBinanceData.get("m");
        this.quantity = Float.parseFloat((String) orderFlowBinanceData.get("q"));
        this.price = Float.parseFloat((String) orderFlowBinanceData.get("p"));
        long tradingTimeLong = (long) orderFlowBinanceData.get("E");
        this.tradingTime = new Date(tradingTimeLong);
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(tradingTime != null) {
            return this.isSellerAttack + ";" +
                    this.quantity + ";" +
                    this.price + ";" +
                    this.getTotal() + ";" +
                    sdf.format(this.tradingTime);
        } else {
            return this.isSellerAttack + ";" +
                    this.quantity + ";" +
                    this.price + ";" +
                    this.getTotal() + ";";
        }
    }

}
