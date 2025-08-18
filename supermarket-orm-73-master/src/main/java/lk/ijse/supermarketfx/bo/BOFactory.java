package lk.ijse.supermarketfx.bo;

import lk.ijse.supermarketfx.bo.custom.impl.CustomerBOImpl;
import lk.ijse.supermarketfx.bo.custom.impl.ItemBOImpl;
import lk.ijse.supermarketfx.bo.custom.impl.PlaceOrderBOImpl;



public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? (boFactory = new BOFactory()) : boFactory;
    }

    @SuppressWarnings("unchecked")
    public <Hello extends SuperBO> Hello getBO(BOTypes boType) {
        return switch (boType) {
            case CUSTOMER -> (Hello) new CustomerBOImpl();
            case ITEM -> (Hello) new ItemBOImpl();
            case PLACE_ORDER -> (Hello) new PlaceOrderBOImpl();
        };
    }
//    public SuperBO getBO(BOTypes boType) {
//        return switch (boType) {
//            case CUSTOMER ->  new CustomerBOImpl();
//            case ITEM -> new ItemBOImpl();
//            case PLACE_ORDER -> new PlaceOrderBOImpl();
//        };
//    }
}


