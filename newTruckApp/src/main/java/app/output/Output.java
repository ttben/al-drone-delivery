package app.output;

import app.shipper.*;

import java.util.Observer;

public interface Output extends Observer{
    void register(CompositeShipper shipper);

    void register(BasicShipper shipper);

    void register(Shipper shipper);

}