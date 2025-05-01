package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.binancewebsocketdata.OrderFlowData;
import gluon.projects.cryptobinanceservice.BinanceDataProcessService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderFlowDataProcessServiceImpl implements BinanceDataProcessService {

    private Logger logger = LoggerFactory.getLogger(OrderFlowDataProcessServiceImpl.class);

    private String symbol;

    public OrderFlowDataProcessServiceImpl() {}

    public OrderFlowDataProcessServiceImpl(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public void process(JSONObject tradeDataValues) {
        OrderFlowData orderFlowData = new OrderFlowData(this.symbol);
        orderFlowData.fillOrderFlowData(tradeDataValues);
        logger.info(orderFlowData.toString());
    }
}
