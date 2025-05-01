package gluon.projects.cryptobinanceservice.impl;

import gluon.projects.cryptobinanceservice.BinanceDataProcessService;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;

public class WebSocketExchange extends WebSocketClient {

    private Logger logger = LoggerFactory.getLogger(WebSocketExchange.class);

    private BinanceDataProcessService orderFlowDataProcessService;

    public WebSocketExchange(URI serverUri) {
        super(serverUri);
    }

    public void setOrderFlowDataProcessServic(BinanceDataProcessService orderFlowDataProcessServic) {
        this.orderFlowDataProcessService = orderFlowDataProcessServic;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
        logger.info("------ CONNECTION OPEN ------");
    }

    @Override
    public void onMessage(String stringResponse) {
        JSONObject exchangeResponseData = new JSONObject(stringResponse);
        String dataResponseType = (String) exchangeResponseData.get("stream");

        if(dataResponseType.contains("trade")) {
            this.orderFlowDataProcessService.process((JSONObject) exchangeResponseData.get("data"));
        }

    }

    @Override
    public void onClose(int i, String s, boolean b) {
        logger.info("------ CONNECTION CLOSED ------");
    }

    @Override
    public void onError(Exception e) {
        logger.info("------ CONNECTION ERROR ------");
    }
}
