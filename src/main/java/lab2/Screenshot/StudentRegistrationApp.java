package lab2.Screenshot;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class StudentRegistrationApp extends Application {

    @Override
    public void start(Stage stage) {

        // ---------- Welcome ----------
        Label welcome = new Label("Student Registration System - Bwalya Musonda (202503581)");
        welcome.setId("welcome");

        // ---------- Inputs ----------
        TextField nameField = new TextField();
        nameField.setPromptText("e.g. Bwalya Musonda");

        TextField studentNumberField = new TextField();
        studentNumberField.setPromptText("e.g. 202503581");

        ComboBox<String> yearBox = new ComboBox<>();
        yearBox.getItems().addAll("1", "2", "3", "4", "5");
        yearBox.setPromptText("Select year");

        ComboBox<String> provinceBox = new ComboBox<>();
        provinceBox.getItems().addAll(
                "Central", "Copperbelt", "Eastern", "Luapula",
                "Lusaka", "Muchinga", "Northern", "North-Western",
                "Southern", "Western"
        );
        provinceBox.setPromptText("Select province");

        ComboBox<String> programmeBox = new ComboBox<>();
        programmeBox.getItems().addAll(
                "ICT", "Engineering", "Business", "Education",
                "Health Sciences", "Others"
        );
        programmeBox.setPromptText("Select programme");

        ComboBox<String> genderBox = new ComboBox<>();
        genderBox.getItems().addAll("Male", "Female");
        genderBox.setPromptText("Select gender");

        TextField emailField = new TextField();
        emailField.setPromptText("e.g. bwalya@example.com");

        TextField phoneField = new TextField();
        phoneField.setPromptText("e.g. 0971234567");

        DatePicker dobPicker = new DatePicker();
        dobPicker.setPromptText("Pick your date of birth");

        // ---------- Buttons ----------
        Button registerBtn = new Button("Register Student");
        registerBtn.setId("registerBtn");

        Button clearBtn = new Button("Clear Form");
        clearBtn.setId("clearBtn");

        Button deleteBtn = new Button("Delete Student");
        deleteBtn.setId("deleteBtn");

        Label status = new Label("Ready");

        // ---------- Table ----------
        TableView<Student> table = new TableView<>();
        ObservableList<Student> students = FXCollections.observableArrayList();
        table.setItems(students);

        TableColumn<Student, String> c1 = new TableColumn<>("Student No");
        c1.setCellValueFactory(new PropertyValueFactory<>("studentNumber"));
        c1.setPrefWidth(110);

        TableColumn<Student, String> c2 = new TableColumn<>("Name");
        c2.setCellValueFactory(new PropertyValueFactory<>("name"));
        c2.setPrefWidth(150);

        TableColumn<Student, String> c3 = new TableColumn<>("Year");
        c3.setCellValueFactory(new PropertyValueFactory<>("year"));
        c3.setPrefWidth(60);

        TableColumn<Student, String> c4 = new TableColumn<>("Province");
        c4.setCellValueFactory(new PropertyValueFactory<>("province"));
        c4.setPrefWidth(110);

        TableColumn<Student, String> c5 = new TableColumn<>("Programme");
        c5.setCellValueFactory(new PropertyValueFactory<>("programme"));
        c5.setPrefWidth(120);

        TableColumn<Student, String> c6 = new TableColumn<>("Gender");
        c6.setCellValueFactory(new PropertyValueFactory<>("gender"));
        c6.setPrefWidth(80);

        TableColumn<Student, String> c7 = new TableColumn<>("Email");
        c7.setCellValueFactory(new PropertyValueFactory<>("email"));
        c7.setPrefWidth(220);

        TableColumn<Student, String> c8 = new TableColumn<>("Phone");
        c8.setCellValueFactory(new PropertyValueFactory<>("phone"));
        c8.setPrefWidth(120);

        TableColumn<Student, String> c9 = new TableColumn<>("Date of Birth");
        c9.setCellValueFactory(new PropertyValueFactory<>("dob"));
        c9.setPrefWidth(120);

        table.getColumns().addAll(c1, c2, c3, c4, c5, c6, c7, c8, c9);

        // ---------- Register button logic ----------
        registerBtn.setOnAction(e -> {
            String name = nameField.getText().trim();
            String studentNumber = studentNumberField.getText().trim();
            String year = yearBox.getValue();
            String province = provinceBox.getValue();
            String programme = programmeBox.getValue();
            String gender = genderBox.getValue();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            LocalDate dob = dobPicker.getValue();

            if (name.isEmpty()) {
                status.setText("Enter the student name.");
                nameField.requestFocus();
                return;
            }
            if (studentNumber.isEmpty()) {
                status.setText("Enter the student number.");
                studentNumberField.requestFocus();
                return;
            }
            if (year == null) {
                status.setText("Select the year of study.");
                yearBox.requestFocus();
                return;
            }
            if (province == null) {
                status.setText("Select the province.");
                provinceBox.requestFocus();
                return;
            }
            if (programme == null) {
                status.setText("Select the programme.");
                programmeBox.requestFocus();
                return;
            }
            if (gender == null) {
                status.setText("Select the gender.");
                genderBox.requestFocus();
                return;
            }
            if (email.isEmpty() || !email.contains("@")) {
                status.setText("Enter a valid email (must contain @).");
                emailField.requestFocus();
                return;
            }
            if (phone.isEmpty()) {
                status.setText("Enter the phone number.");
                phoneField.requestFocus();
                return;
            }
            if (dob == null) {
                status.setText("Pick the date of birth.");
                dobPicker.requestFocus();
                return;
            }

            students.add(new Student(name, studentNumber, year, province,
                    programme, gender, email, phone, dob.toString()));
            status.setText("Student registered successfully.");

            nameField.clear();
            studentNumberField.clear();
            yearBox.setValue(null);
            provinceBox.setValue(null);
            programmeBox.setValue(null);
            genderBox.setValue(null);
            emailField.clear();
            phoneField.clear();
            dobPicker.setValue(null);
            nameField.requestFocus();
        });

        // ---------- Clear button logic ----------
        clearBtn.setOnAction(e -> {
            nameField.clear();
            studentNumberField.clear();
            yearBox.setValue(null);
            provinceBox.setValue(null);
            programmeBox.setValue(null);
            genderBox.setValue(null);
            emailField.clear();
            phoneField.clear();
            dobPicker.setValue(null);
            status.setText("Form cleared.");
            nameField.requestFocus();
        });

        // ---------- Delete button logic ----------
        deleteBtn.setOnAction(e -> {
            Student selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                status.setText("Select a student to delete first.");
                return;
            }

            Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                    "Delete student " + selected.getName() + "?",
                    ButtonType.OK, ButtonType.CANCEL);
            confirm.setHeaderText("Confirm deletion");

            if (confirm.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK) {
                students.remove(selected);
                status.setText("Student deleted.");
            }
        });

        // ---------- Layout with GridPane ----------
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);

        form.add(new Label("Student Name:"), 0, 0);
        form.add(nameField, 1, 0);
        form.add(new Label("Student Number:"), 0, 1);
        form.add(studentNumberField, 1, 1);
        form.add(new Label("Year of Study:"), 0, 2);
        form.add(yearBox, 1, 2);
        form.add(new Label("Province:"), 0, 3);
        form.add(provinceBox, 1, 3);
        form.add(new Label("Programme:"), 0, 4);
        form.add(programmeBox, 1, 4);

        form.add(new Label("Gender:"), 2, 0);
        form.add(genderBox, 3, 0);
        form.add(new Label("Email:"), 2, 1);
        form.add(emailField, 3, 1);
        form.add(new Label("Phone:"), 2, 2);
        form.add(phoneField, 3, 2);
        form.add(new Label("Date of Birth:"), 2, 3);
        form.add(dobPicker, 3, 3);

        HBox buttons = new HBox(10, registerBtn, clearBtn, deleteBtn);

        VBox root = new VBox(15, welcome, form, buttons, status, table);
        root.setPadding(new Insets(15));

        Scene scene = new Scene(root, 1200, 650);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        stage.setTitle("Student Registration System - Bwalya Musonda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}