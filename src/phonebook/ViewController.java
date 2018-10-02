package phonebook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ViewController implements Initializable {

    @FXML
    TableView table;
    @FXML
    TextField inputID;
    @FXML
    TextField inputLastname;
    @FXML
    TextField inputFirstName;
    @FXML
    TextField inputEmail;
    @FXML
    TextField inputAnyjaneve;
    @FXML
    TextField inputLakcim;
    @FXML
    TextField inputTajszam;
    @FXML
    TextField inputDate;
    @FXML
    TextField inputTelefon;
    @FXML
    Button addNewContactButton;
    @FXML
    StackPane menuPane;
    @FXML
    Pane contactPane;
    @FXML
    Pane exportPane;
    @FXML
    SplitPane mainSplit;
    @FXML
    AnchorPane anchor;
    @FXML
    TextField inputExportName;
    @FXML
    Button exportButton;

    DB db = new DB();

    private final String MENU_CONTACTS = "Kontaktok";
    private final String MENU_LIST = "Lista";
    private final String MENU_EXPORT = "Exportálás";
    private final String MENU_EXIT = "Kilépés";

    private ObservableList<Person> data = FXCollections.observableArrayList(db.getAllContacts());
    

    @FXML
    private void addContact(ActionEvent event) {
        String email = inputEmail.getText();
        if (email.length() > 3 && email.contains("@") && email.contains(".")) {
            Person newPerson = new Person(inputLastname.getText(), inputFirstName.getText(),
                    inputEmail.getText(), inputAnyjaneve.getText(),
                    inputLakcim.getText(), inputTajszam.getText(),
                    inputDate.getText(), inputTelefon.getText());

            db.addContact(newPerson);
            tableReferesh();
            inputLastname.clear();
            inputFirstName.clear();
            inputEmail.clear();
            inputAnyjaneve.clear();
            inputLakcim.clear();
            inputTajszam.clear();
            inputDate.clear();
            inputTelefon.clear();
        } else {
            alert("Adj meg egy valódi e-mail címet!");
        }
    }
    
    @FXML
    private void updateContact(ActionEvent event) {
        String email = inputEmail.getText();
        if (email.length() > 3 && email.contains("@") && email.contains(".")) {
            Person editPerson = new Person(Integer.parseInt(inputID.getText()),inputLastname.getText(), inputFirstName.getText(),
                    inputEmail.getText(), inputAnyjaneve.getText(),
                    inputLakcim.getText(), inputTajszam.getText(),
                    inputDate.getText(), inputTelefon.getText());

            db.updateContact(editPerson);
            tableReferesh();
            inputLastname.clear();
            inputFirstName.clear();
            inputEmail.clear();
            inputAnyjaneve.clear();
            inputLakcim.clear();
            inputTajszam.clear();
            inputDate.clear();
            inputTelefon.clear();
        } else {
            alert("Adj meg egy valódi e-mail címet!");
        }
    }

    @FXML
    private void exportList(ActionEvent event) {
        String fileName = inputExportName.getText();
        fileName = fileName.replaceAll("\\s+", "");
        if (fileName != null && !fileName.equals("")) {
            PdfGeneration pdfCreator = new PdfGeneration();
            pdfCreator.pdfGeneration(fileName, data);
        } else {
            alert("Adj meg egy fájlnevet!");
        }
    }

    public void setTableData() {
        TableColumn lastNameCol = new TableColumn("Vezetéknév");
        lastNameCol.setMinWidth(120);
        lastNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lastNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));

        lastNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setLastName(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn firstNameCol = new TableColumn("Keresztnév");
        firstNameCol.setMinWidth(90);
        firstNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        firstNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));

        firstNameCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setFirstName(t.getNewValue());
                data.remove(t);
                db.updateContact(actualPerson);

            }
        }
        );

        TableColumn emailCol = new TableColumn("Email cím");
        emailCol.setMinWidth(120);
        emailCol.setCellValueFactory(new PropertyValueFactory<Person, String>("email"));
        emailCol.setCellFactory(TextFieldTableCell.forTableColumn());

        emailCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setEmail(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn anyjaneveNeveCol = new TableColumn("Anyja neve");
        anyjaneveNeveCol.setMinWidth(100);
        anyjaneveNeveCol.setCellValueFactory(new PropertyValueFactory<Person, String>("anyjaNeve"));
        anyjaneveNeveCol.setCellFactory(TextFieldTableCell.forTableColumn());

        anyjaneveNeveCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setAnyjaNeve(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn lakcimCol = new TableColumn("Lakcím");
        lakcimCol.setMinWidth(150);
        lakcimCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lakcim"));
        lakcimCol.setCellFactory(TextFieldTableCell.forTableColumn());

        lakcimCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setLakcim(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn tajszamCol = new TableColumn("Taj szám");
        tajszamCol.setMinWidth(80);
        tajszamCol.setCellValueFactory(new PropertyValueFactory<Person, String>("tajszam"));
        tajszamCol.setCellFactory(TextFieldTableCell.forTableColumn());

        tajszamCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setTajszam(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn szidoCol = new TableColumn("Születési idő");
        szidoCol.setMinWidth(80);
        szidoCol.setCellValueFactory(new PropertyValueFactory<Person, String>("szido"));
        szidoCol.setCellFactory(TextFieldTableCell.forTableColumn());

        szidoCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setSzido(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn telCol = new TableColumn("Telefon");
        telCol.setMinWidth(100);
        telCol.setCellValueFactory(new PropertyValueFactory<Person, String>("telefon"));
        telCol.setCellFactory(TextFieldTableCell.forTableColumn());

        telCol.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> t) {
                Person actualPerson = (Person) t.getTableView().getItems().get(t.getTablePosition().getRow());
                actualPerson.setTelefon(t.getNewValue());
                db.updateContact(actualPerson);
            }
        }
        );

        TableColumn removeCol = new TableColumn("Törlés");
        removeCol.setMinWidth(70);

        Callback<TableColumn<Person, String>, TableCell<Person, String>> cellFactory
                = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell call(final TableColumn<Person, String> param) {
                final TableCell<Person, String> cell = new TableCell<Person, String>() {
                    final Button btn = new Button("Törlés");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction((ActionEvent event)
                                    -> {
                                Person person = getTableView().getItems().get(getIndex());
                                db.removeContact(person);
                                tableReferesh();
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        TableColumn editCol = new TableColumn("Szerkeszt");
        editCol.setMinWidth(70);

        Callback<TableColumn<Person, String>, TableCell<Person, String>> Torol
                = new Callback<TableColumn<Person, String>, TableCell<Person, String>>() {
            @Override
            public TableCell call(final TableColumn<Person, String> param) {
                final TableCell<Person, String> cell = new TableCell<Person, String>() {
                    final Button btn = new Button("Szerkeszt");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction((ActionEvent event)
                                    -> {
                                Person person = getTableView().getItems().get(getIndex());
                                inputAnyjaneve.setText(person.getAnyjaNeve());
                                inputDate.setText(person.getSzido());
                                inputEmail.setText(person.getEmail());
                                inputFirstName.setText(person.getFirstName());
                                inputLastname.setText(person.getLastName());
                                inputLakcim.setText(person.getLakcim());
                                inputTajszam.setText(person.getTajszam());
                                inputTelefon.setText(person.getTelefon());
                                inputID.setText(person.getId());
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                };
                return cell;
            }
        };

        editCol.setCellFactory(Torol);
        removeCol.setCellFactory(cellFactory);

        table.getColumns().addAll(lastNameCol, firstNameCol, emailCol, anyjaneveNeveCol, lakcimCol, tajszamCol, szidoCol, telCol, editCol, removeCol);
        table.setItems(data);
    }

    private void setMenuData() {
        TreeItem<String> treeItemRoot1 = new TreeItem<>("Menü");
        TreeView<String> treeView = new TreeView<>(treeItemRoot1);
        treeView.setShowRoot(false);

        TreeItem<String> nodeItemA = new TreeItem<>(MENU_CONTACTS);
        TreeItem<String> nodeItemB = new TreeItem<>(MENU_EXIT);

        nodeItemA.setExpanded(true);

        Node contactsNode = new ImageView(new Image(getClass().getResourceAsStream("/contacts.png")));
        Node exportNode = new ImageView(new Image(getClass().getResourceAsStream("/export.png")));
        TreeItem<String> nodeItemA1 = new TreeItem<>(MENU_LIST, contactsNode);
        TreeItem<String> nodeItemA2 = new TreeItem<>(MENU_EXPORT, exportNode);

        nodeItemA.getChildren().addAll(nodeItemA1, nodeItemA2);
        treeItemRoot1.getChildren().addAll(nodeItemA, nodeItemB);

        menuPane.getChildren().add(treeView);

        treeView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                TreeItem<String> selectedItem = (TreeItem<String>) newValue;
                String selectedMenu;
                selectedMenu = selectedItem.getValue();

                if (null != selectedMenu) {
                    switch (selectedMenu) {
                        case MENU_CONTACTS:
                            selectedItem.setExpanded(true);
                            break;
                        case MENU_LIST:
                            contactPane.setVisible(true);
                            exportPane.setVisible(false);
                            break;
                        case MENU_EXPORT:
                            contactPane.setVisible(false);
                            exportPane.setVisible(true);
                            break;
                        case MENU_EXIT:
                            System.exit(0);
                            break;
                    }
                }

            }
        });

    }

    private void tableReferesh() {
        table.getColumns().clear();
        data = FXCollections.observableArrayList(db.getAllContacts());
        setTableData();
    }
    
    private void alert(String text) {
        mainSplit.setDisable(true);
        mainSplit.setOpacity(0.4);

        Label label = new Label(text);
        Button alertButton = new Button("OK");
        VBox vbox = new VBox(label, alertButton);
        vbox.setAlignment(Pos.CENTER);

        alertButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                mainSplit.setDisable(false);
                mainSplit.setOpacity(1);
                vbox.setVisible(false);
            }
        });

        anchor.getChildren().add(vbox);
        anchor.setTopAnchor(vbox, 300.0);
        anchor.setLeftAnchor(vbox, 300.0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setTableData();
        setMenuData();
    }

}
