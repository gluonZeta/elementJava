package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.Utils;
import gluon.projects.cryptobinanceservice.BinanceDataProcessService;
import gluon.projects.cryptobinanceservice.BinanceWebsocketService;
import gluon.projects.exceptions.ElementProjectException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

public class BinanceWebsocketServiceImpl implements BinanceWebsocketService {

    private final String symbol;

    private final String websocketBinanceUrl;

    public BinanceWebsocketServiceImpl(String symbol) {
        Properties properties = Utils.getPropertiesByFileName("application.properties");
        this.websocketBinanceUrl = properties.getProperty("streambinancesocket");
        this.symbol = symbol;
    }

    @Override
    public void launchExchangeData() {
        String urlWebsocketExchange = this.createUrlWebsocketExchange();
        BinanceDataProcessService orderFlowDataProcessService = new OrderFlowDataProcessServiceImpl(this.symbol);
        try {
            WebSocketExchange webSocketExchange = new WebSocketExchange(new URI(urlWebsocketExchange));
            webSocketExchange.setOrderFlowDataProcessServic(orderFlowDataProcessService);
            webSocketExchange.connect();
        } catch (URISyntaxException e) {
            throw new ElementProjectException(e);
        }
    }

    private String createUrlWebsocketExchange() {
        return this.websocketBinanceUrl
                + "/stream?streams="
                + this.symbol.toLowerCase()
                + "@depth20/"
                + this.symbol.toLowerCase()
                + "@trade";
    }
}
