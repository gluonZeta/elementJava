package gluon.projects.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
@Table(schema = "cryptodataschema")
public class OrderFlowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String symbol;

    private boolean isBuyerMarketMaker;

    private float quantity;

    private float price;

    private Date tradingTime;
}
