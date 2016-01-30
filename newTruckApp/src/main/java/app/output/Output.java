package app.output;

import app.shipper.CompositeShipper;
import app.shipper.Shipper;

import java.util.Observer;

public interface Output extends Observer{
    void set(Shipper shipper);
    void set(CompositeShipper shipper);
}