package SisforCDGame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SisForCDGameController {

    @FXML
    private Button btnBatal;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnHapus;

    @FXML
    private Button btnKeluar;

    @FXML
    private Button btnSimpan;

    @FXML
    private Button btnTambah;

    @FXML
    private ChoiceBox<String> choiceBoxKategori;

    @FXML
    private Spinner<Integer> spinnerStok;

    @FXML
    private TableView<Order> tableOrder;

    @FXML
    private TextField txtFieldKode;

    @FXML
    private TextField txtFieldNama;

    @FXML
    private TableColumn<Order, Integer> colKodeProduk;

    @FXML
    private TableColumn<Order, String> colNamaGame;

    @FXML
    private TableColumn<Order, String> colKategori;

    @FXML
    private TableColumn<Order, Integer> colJumlahStok;

    private final ObservableList<Order> orderList = FXCollections.observableArrayList();
    private final String DATA_FILE = "orderData.ser";

    @FXML
    private void initialize() {
        // Initialize ChoiceBox with game categories
        choiceBoxKategori.setItems(FXCollections.observableArrayList("Adventure", "Action", "RPG", "Strategy"));
        choiceBoxKategori.getSelectionModel().select(0);

        // Initialize Spinner with stock values (min: 1, max: 100, initial: 1)
        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100, 1);
        spinnerStok.setValueFactory(valueFactory);

        // Initialize table columns
        colKodeProduk.setCellValueFactory(new PropertyValueFactory<>("kodeGame"));
        colNamaGame.setCellValueFactory(new PropertyValueFactory<>("namaGame"));
        colKategori.setCellValueFactory(new PropertyValueFactory<>("kategori"));
        colJumlahStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        // Bind the data to the TableView
        tableOrder.setItems(orderList);

        // Load data from file
        loadData();
    }

    @FXML
    void handleclickbtnTambah(MouseEvent event) {
        // Logic to handle add button
        Order newOrder = new Order(
            Integer.parseInt(txtFieldKode.getText()),
            txtFieldNama.getText(),
            choiceBoxKategori.getValue(),
            spinnerStok.getValue()
        );

        orderList.add(newOrder);
        saveData();
        clearFields();
    }

    @FXML
    private void handleSimpan() {
        // Logic to handle save button (if needed, e.g., to save to a file or database)
        saveData();
    }

    @FXML
    void handleclickbtnEdit(MouseEvent event) {
        // Logic to handle edit button
        Order selectedOrder = tableOrder.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            selectedOrder.setKodeGame(Integer.parseInt(txtFieldKode.getText()));
            selectedOrder.setNamaGame(txtFieldNama.getText());
            selectedOrder.setKategori(choiceBoxKategori.getValue());
            selectedOrder.setStok(spinnerStok.getValue());
            tableOrder.refresh();
            saveData();
        }
    }

    @FXML
    void handleclickbtnHapus(MouseEvent event) {
        // Logic to handle delete button
        Order selectedOrder = tableOrder.getSelectionModel().getSelectedItem();
        if (selectedOrder != null) {
            orderList.remove(selectedOrder);
            saveData();
        }
    }

    @FXML
    void handleclickbtnBatal(MouseEvent event) {
        // Logic to handle cancel button
        clearFields();
    }

    @FXML
    void handleclickbtnKeluar(MouseEvent event) {
        // Logic to handle exit button
        saveData();
        System.exit(0);
    }

    private void clearFields() {
        txtFieldKode.clear();
        txtFieldNama.clear();
        choiceBoxKategori.getSelectionModel().select(0);
        spinnerStok.getValueFactory().setValue(1);
    }

    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(new ArrayList<>(orderList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        File file = new File(DATA_FILE);
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                List<Order> loadedOrders = (List<Order>) in.readObject();
                orderList.addAll(loadedOrders);
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}
