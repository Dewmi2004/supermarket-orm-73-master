package lk.ijse.supermarketfx.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.supermarketfx.bo.BOFactory;
import lk.ijse.supermarketfx.bo.BOTypes;
import lk.ijse.supermarketfx.bo.custom.PlaceOrderBO;
import lk.ijse.supermarketfx.dto.ItemDTO;
import lk.ijse.supermarketfx.dto.OrderDTO;
import lk.ijse.supermarketfx.dto.OrderDetailsDTO;
import lk.ijse.supermarketfx.dto.tm.CartTM;
import lk.ijse.supermarketfx.model.CustomerModel;
import lk.ijse.supermarketfx.model.ItemModel;
import lk.ijse.supermarketfx.model.OrderModel;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/3/2025 10:30 AM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

public class OrderPageController implements Initializable {

    @FXML
    private ComboBox<String> cmbCustomerId;

    @FXML
    private ComboBox<String> cmbItemId;

    @FXML
    private TableView<CartTM> tblCart;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<CartTM, String> colItemId;

    @FXML
    private TableColumn<CartTM, String> colName;

    @FXML
    private TableColumn<CartTM, Double> colPrice;

    @FXML
    private TableColumn<CartTM, Integer> colQuantity;

    @FXML
    private TableColumn<CartTM, Double> colTotal;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblItemName;

    @FXML
    private Label lblItemPrice;

    @FXML
    private Label lblItemQty;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label orderDate;


    @FXML
    private TextField txtAddToCartQty;

    private final OrderModel orderModel = new OrderModel();

    private final PlaceOrderBO placeOrderBO = BOFactory.getInstance().getBO(BOTypes.PLACE_ORDER);

    private final CustomerModel customerModel = new CustomerModel();
    private final ItemModel itemModel = new ItemModel();

    private final ObservableList<CartTM> cartData = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("cartQty"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));

        tblCart.setItems(cartData);

        try {
            resetPage();
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(
                    Alert.AlertType.ERROR, "Fail to load data..!"
            ).show();
        }
    }

    private void resetPage() throws SQLException {
        lblOrderId.setText(orderModel.getNextOrderId());

//        orderDate.setText(String.valueOf(LocalDate.now()));
        orderDate.setText(LocalDate.now().toString());

        loadCustomerIds();
        loadItemIds();
    }

    private void loadItemIds() throws SQLException {
//        cmbItemId
        cmbItemId.setItems(
                FXCollections.observableArrayList(
                        itemModel.getAllItemIds()
                )
        );
    }

    private void loadCustomerIds() throws SQLException {
        ArrayList<String> customerIdsList = customerModel.getAllCustomerIds();
//        call bo layer and request getAllCustomerIds()
        ObservableList<String> customerIds = FXCollections.observableArrayList();
        customerIds.addAll(customerIdsList);
        cmbCustomerId.setItems(customerIds);

//        cmbCustomerId.setItems(
//                FXCollections.observableArrayList(
//                        customerModel.getAllCustomerIds()
//                )
//        );
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
//      String selectedItemId = cmbItemId.getSelectionModel().getSelectedItem();
        String selectedItemId = cmbItemId.getValue();
        String cartQtyString = txtAddToCartQty.getText();

        if (selectedItemId == null) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please select item..!"
            ).show();
            return;
        }

        if (!cartQtyString.matches("^[0-9]+$")) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please enter valid quantity..!"
            ).show();
            return;
        }

        int cartQty = Integer.parseInt(cartQtyString);
        int itemStockQty = Integer.parseInt(lblItemQty.getText());

        // 10 < 15
        if (itemStockQty < cartQty) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Not enough item quantity..!"
            ).show();
            return;
        }

        String itemName = lblItemName.getText();
        double itemUnitPrice = Double.parseDouble(lblItemPrice.getText());
        double total = itemUnitPrice * cartQty;

        for (CartTM cartTM : cartData) {
            if (cartTM.getItemId().equals(selectedItemId)) {
                // 20 + 10
                int newQty = cartTM.getCartQty() + cartQty;

                if (itemStockQty < newQty) {
                    new Alert(
                            Alert.AlertType.WARNING,
                            "Not enough item quantity..!"
                    ).show();
                    return;
                }

                cartTM.setCartQty(newQty);
                cartTM.setTotal(newQty * itemUnitPrice);

                txtAddToCartQty.setText("");
                tblCart.refresh();

                return;
            }
        }

        Button removeBtn = new Button("Remove");
        CartTM cartTM = new CartTM(
                selectedItemId,
                itemName,
                cartQty,
                itemUnitPrice,
                total,
                removeBtn
        );

        removeBtn.setOnAction(action -> {
            cartData.remove(cartTM);
            tblCart.refresh();
        });

        txtAddToCartQty.setText("");
        cartData.add(cartTM);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        if (tblCart.getItems().isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please add items to cart..!"
            ).show();
            return;
        }

        if (cmbCustomerId.getValue().isEmpty()) {
            new Alert(
                    Alert.AlertType.WARNING,
                    "Please select customer for place order..!"
            ).show();
            return;
        }

        String selectedCustomerId = cmbCustomerId.getValue();
        String orderId = lblOrderId.getText();
        Date date = Date.valueOf(orderDate.getText());

        ArrayList<OrderDetailsDTO> cartList = new ArrayList<>();

        for (CartTM cartTM : cartData) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    orderId,
                    cartTM.getItemId(),
                    cartTM.getCartQty(),
                    cartTM.getUnitPrice()
            );
            cartList.add(orderDetailsDTO);
        }

        OrderDTO orderDTO = new OrderDTO(
                orderId,
                selectedCustomerId,
                date,
                cartList
        );

        try {
//            boolean isPlaced = orderModel.placeOrder(orderDTO);
            boolean isPlaced = placeOrderBO.placeOrder(orderDTO);
            // call bo and place order

            if (isPlaced) {
                resetPage();
            } else {
                new Alert(Alert.AlertType.ERROR, "Fail to place order..!").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "Fail to place order..!").show();
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {

    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) throws SQLException {
        String selectedId = cmbCustomerId.getSelectionModel().getSelectedItem();
        String name = customerModel.findNameById(selectedId);
        lblCustomerName.setText(name);
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) throws SQLException {
        String selectedId = cmbItemId.getSelectionModel().getSelectedItem();
        ItemDTO itemDTO = itemModel.findById(selectedId);

        if (itemDTO != null) {
            lblItemName.setText(itemDTO.getName());
            lblItemQty.setText(String.valueOf(itemDTO.getQuantity()));
            lblItemPrice.setText(String.valueOf(itemDTO.getUnitPrice()));
        } else {
            lblItemName.setText("");
            lblItemQty.setText("");
            lblItemPrice.setText("");
        }
    }
}
