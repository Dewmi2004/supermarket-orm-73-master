package lk.ijse.supermarketfx.dao;

import lk.ijse.supermarketfx.dao.custom.impl.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 7/1/2025 1:34 PM
 * Project: Supermarket-layered
 * --------------------------------------------
 **/

// DAOFactory (Interface with getDAO method)
//     <- DAOFactoryImpl (concentrate implication)

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? (daoFactory = new DAOFactory()) : daoFactory;
    }

    @SuppressWarnings("unchecked")
    // prevent compiler warning about unchecked type casting
    public <T extends SuperDAO> T getDAO(DAOTypes daoType) {
        return switch (daoType) {
            case CUSTOMER -> (T) new CustomerDAOImpl();
            case ITEM -> (T) new ItemDAOImpl();
            case ORDER -> (T) new OrderDAOImpl();
            case ORDER_DETAILS -> (T) new OrderDetailDAOImpl();
            case QUERY -> (T) new QueryDAOImpl();
        };
//        switch (daoType) {
//            case CUSTOMER:
//                return (T) new CustomerDAOImpl();
//            case ITEM:
//                return (T) new ItemDAOImpl();
//            case ORDER:
//                return (T) new OrderDAOImpl();
//            case ORDER_DETAILS:
//                return (T) new OrderDetailDAOImpl();
//            case QUERY:
//                return (T) new QueryDAOImpl();
//            default:
//                return null;
//        }
    }
}