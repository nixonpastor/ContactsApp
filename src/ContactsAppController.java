import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ContactsAppController {

    private final ObservableList<Contact> contacts = FXCollections.observableArrayList();

    @FXML
    private ListView<Contact> contactListView;

    @FXML
    private TextField firstNameTextField;

    @FXML
    private TextField lastNameTextField;

    @FXML
    private TextField phoneNumberTextField;

    @FXML
    private TextField emailTextField;

    public void initialize(){
        contacts.add(new Contact("Nixon", "Pastor", "npastor@email.com",
                "602-123-4567"));
        contacts.add(new Contact("John", "Smith", "jsmith@email.com",
                "456-789-1010"));
        contacts.add(new Contact("Sam", "Taylor", "staylor@email.com",
                "589-333-1111"));

        contactListView.setItems(contacts);

        contactListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>() {
            @Override
            public void changed(ObservableValue<? extends Contact> observableValue, Contact oldContact,
                                Contact newContact) {
                firstNameTextField.setText(newContact.getFirstName());
                lastNameTextField.setText(newContact.getLastName());
                emailTextField.setText(newContact.getEmail());
                phoneNumberTextField.setText(newContact.getPhoneNumber());

            }
        });
    }

    public void updateContactButton(ActionEvent actionEvent) {
        Contact currentContact = contactListView.getSelectionModel().selectedItemProperty().get();
        Contact updatedContact = new Contact(firstNameTextField.getText(), lastNameTextField.getText(),
                emailTextField.getText(), phoneNumberTextField.getText());
        contacts.add(updatedContact);
        contacts.remove(currentContact);
        contactListView.setItems(contacts);
    }

    public void addContactButton(ActionEvent actionEvent) {
        Contact updatedContact = new Contact(firstNameTextField.getText(), lastNameTextField.getText(),
                emailTextField.getText(), phoneNumberTextField.getText());
        contacts.add(updatedContact);
        contactListView.setItems(contacts);
    }

    public void deleteContactButton(ActionEvent actionEvent) {
        Contact deleteContact = contactListView.getSelectionModel().selectedItemProperty().get();
        contacts.remove(deleteContact);
        contactListView.setItems(contacts);
    }
}
