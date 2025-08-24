package lk.ijse.supermarketfx.dao.custom.impl;

import lk.ijse.supermarketfx.config.FactoryConfiguration;
import lk.ijse.supermarketfx.dao.SQLUtil;
import lk.ijse.supermarketfx.dao.custom.CustomerDAO;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.entity.Customer;
import lk.ijse.supermarketfx.util.CrudUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CustomerDAOImpl implements CustomerDAO {
    private final FactoryConfiguration factoryConfiguration = FactoryConfiguration.getInstance();
    @Override
    public List<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        List<Customer> list = new ArrayList<>();
        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
            list.add(customer);
        }
        return list;
    }

    @Override
    public String getLastId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer ORDER BY customer_id DESC LIMIT 1");
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return null;
    }

    @Override
    public boolean save(Customer customer) throws SQLException {
//        return SQLUtil.execute(
//                "INSERT INTO customer (customer_id, name, nic, email, phone) VALUES (?, ?, ?, ?, ?)",
//                customer.getId(),
//                customer.getName(),
//                customer.getNic(),
//                customer.getEmail(),
//                customer.getPhone()
//        );
        Session sessionFactory = factoryConfiguration.getSession();
        Transaction transaction = sessionFactory.beginTransaction();
        try{
            sessionFactory.persist(customer);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            sessionFactory.close();
        }
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        Session sessionFactory = factoryConfiguration.getSession();
        Transaction transaction = sessionFactory.beginTransaction();
        try{
            sessionFactory.merge(customer);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            sessionFactory.close();
        }
    }

    @Override
    public boolean delete(String id) throws SQLException {
        Session sessionFactory = factoryConfiguration.getSession();
        Transaction transaction = sessionFactory.beginTransaction();
        try{
            sessionFactory.remove(id);
            transaction.commit();
            return true;
        }catch(Exception e){
            transaction.rollback();
            return false;
        }finally {
            sessionFactory.close();
        }
     }

    @Override
    public List<String> getAllIds() throws SQLException {
//        ResultSet resultSet = SQLUtil.execute("SELECT customer_id FROM customer");
//        List<String> ids = new ArrayList<>();
//        while (resultSet.next()) {
//            ids.add(resultSet.getString(1));
//        }
//        return ids;
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try{
            Query<Customer> quary = session.createQuery("from Customer", Customer.class);
              List<Customer> customerList = quary.list();
              customerList.forEach(customer -> System.out.println(customer.toString()));
            transaction.commit();
            return customerList.stream().map(Customer::getId).collect(Collectors.toList());
        }catch(Exception e){
            transaction.rollback();
            return new ArrayList<>();
        }finally {
            session.close();

        }
    }

    @Override
    public Optional<Customer> findById(String id) throws SQLException {
 //       Session session = FactoryConfiguration.getInstance().getSession();
//        Client customer = session.get(Client.class, 2);
//        System.out.println(customer.toString());
        //----------
//        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE customer_id = ?", id);
//        if (resultSet.next()) {
//            return Optional.of(new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5)
//            ));
//        }
//        return Optional.empty();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            Customer customer = session.get(Customer.class, id);
            transaction.commit();
            return Optional.ofNullable(customer);
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return Optional.empty();
        } finally {
            session.close();
        }

    }

    @Override
          public List<Customer> search(String text) throws SQLException {
//        String searchText = "%" + text + "%";
//        ResultSet resultSet = SQLUtil.execute(
//                "SELECT * FROM customer WHERE customer_id LIKE ? OR name LIKE ? OR nic LIKE ? OR email LIKE ? OR phone LIKE ?",
//                searchText, searchText, searchText, searchText, searchText
//        );
//
//        List<Customer> list = new ArrayList<>();
//        while (resultSet.next()) {
//            Customer customer = new Customer(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getString(3),
//                    resultSet.getString(4),
//                    resultSet.getString(5)
//            );
//            list.add(customer);
//        }
//        return list;
//    }
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try {
            String searchText = "%" + text + "%";

            Query<Customer> query = session.createQuery(
                    "FROM Customer c WHERE c.customerId LIKE :text " +
                            "OR c.name LIKE :text " +
                            "OR c.nic LIKE :text " +
                            "OR c.email LIKE :text " +
                            "OR c.phone LIKE :text",
                    Customer.class
            );
            query.setParameter("text", searchText);

            List<Customer> list = query.list();

            transaction.commit();
            return list;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return List.of(); // return empty list on error
        } finally {
            session.close();
        }
    }
    @Override
    public Optional<Customer> findCustomerByNic(String nic) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE nic = ?", nic);
        if (resultSet.next()) {
            return Optional.of(new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }
        return Optional.empty();
    }

    @Override
    public boolean existsCustomerByPhoneNumber(String phoneNumber) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer WHERE phone = ?", phoneNumber);
//        if (resultSet.next()){
//            return true;
//        }
//        return false;

        return resultSet.next();
    }
}
