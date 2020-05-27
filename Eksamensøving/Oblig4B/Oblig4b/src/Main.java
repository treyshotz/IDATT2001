import com.sun.tools.javac.util.Name;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Main extends Application {
	
	ArrayList<BonusMember> members = new ArrayList<>();
	ObservableList<BonusMember> bonusMembers;
	MemberArchive memberArchive;
	TableView tableView;
	Stage stage;
	Scene home;
	VBox vBox;
	VBox vBox2;
	Button newMember;
	Button deleteMember;
	Button checkUpgrade;
	
	@Override
	public void start(Stage stage) {
		this.stage = stage;
		stage.setTitle("FUCK JAVAFX");
		tableView = new TableView();
		vBox = new VBox(tableView);
		home = new Scene(vBox);
		stage.setScene(home);
		
		member();
		addColumns();
		addButtons();
		
		stage.show();
		
		newMember.setOnAction(p -> {
			newMemberPage();
		});
		
		deleteMember.setOnAction(p -> {
			deleteMember();
		});
		
		checkUpgrade.setOnAction(p -> {
			memberArchive.checkMembers(LocalDate.now());
			addColumns();
			stage.setScene(home);
		});
	}
	
	public void member() {
		Personals ole = new Personals("Ole", "Olsen", "ole.olsen@dot.com", "ole");
		Personals tove = new Personals("Tove", "Hanse", "tove.hansen@dot.com", "tove");
		Personals per = new Personals("Per", "Persen", "per.persen@dot.com", "per");
		Personals fride = new Personals("Fride", "Fridsen", "fride.fridsen@dot.com", "fride");
		
		BonusMember oleM = new BasicMember(123, ole, LocalDate.now());
		BonusMember toveM = new BasicMember(321, tove, LocalDate.now());
		this.members.add(oleM);
		this.members.add(toveM);
		
		memberArchive = new MemberArchive(this.members);
		
		memberArchive.newMember(per, LocalDate.now());
		memberArchive.newMember(fride, LocalDate.now());
		
		memberArchive.registerPoints(123, 50000);
		memberArchive.registerPoints(321, 100000);
	}
	
	public void deleteMember() {
		Dialog dialog = new TextInputDialog();
		dialog.setTitle("Delete a member");
		dialog.setContentText("Enter a userNo to delte");
		TextField textField = new TextField();
		textField.setPromptText("UserNo");
		
		Optional<String> result = dialog.showAndWait();
		result.ifPresent(name -> {
			int no = Integer.parseInt(name);
			if (memberArchive.deleteMember(no)) {
				addColumns();
				stage.setScene(home);
			} else
				System.out.println("Funket ikke");
		});
	}
	
	public void addColumns() {
		tableView.getColumns().clear();
		TableColumn<BonusMember, Integer> memberNo = new TableColumn<>("Member no. ");
		memberNo.setCellValueFactory(new PropertyValueFactory<>("memberNo"));
		
		TableColumn<BonusMember, Personals> personals = new TableColumn<>("Personals");
		personals.setCellValueFactory(new PropertyValueFactory<>("personals"));
		
		TableColumn<BonusMember, LocalDate> enrolledDate = new TableColumn<>("Enrolled date");
		enrolledDate.setCellValueFactory(new PropertyValueFactory<>("enrolledDate"));
		
		TableColumn<BonusMember, Integer> points = new TableColumn<>("Points");
		points.setCellValueFactory(new PropertyValueFactory<>("points"));
		
		TableColumn<BonusMember, BonusMember> memberClass = new TableColumn<>("Class");
		memberClass.setCellValueFactory(new PropertyValueFactory<>("class"));
		
		tableView.refresh();
		tableView.setItems(FXCollections.observableArrayList(memberArchive.listAll()));
		
		tableView.getColumns().addAll(memberNo, personals, enrolledDate, points, memberClass);
	}
	
	public void addButtons() {
		newMember = new Button();
		newMember.setText("Add new member");
		vBox.getChildren().add(newMember);
		
		deleteMember = new Button();
		deleteMember.setText("Delete a member");
		vBox.getChildren().add(deleteMember);
		
		checkUpgrade = new Button();
		checkUpgrade.setText("Check for upgrades");
		vBox.getChildren().add(checkUpgrade);
	}
	
	public void newMemberPage() {
		vBox2 = new VBox();
		TextField surName = new TextField();
		surName.setPromptText("Surname");
		
		TextField firstName = new TextField();
		firstName.setPromptText("Firstname");
		
		TextField email = new TextField();
		email.setPromptText("E-mail");
		
		PasswordField password = new PasswordField();
		password.setPromptText("Password");
		
		Button signUp = new Button();
		signUp.setText("Sign Up");
		
		Button cancel = new Button();
		cancel.setText("Cancel");
		
		vBox2.getChildren().addAll(surName, firstName, email, password, signUp, cancel);
		
		Scene scene = new Scene(vBox2);
		stage.setScene(scene);
		stage.show();
		
		signUp.setOnAction(p -> {
			String surNameString = surName.getText();
			String firstNameString = firstName.getText();
			String emailString = email.getText();
			String passwordString = password.getText();
			LocalDate date = LocalDate.now();
			memberArchive.newMember(new Personals(surNameString, firstNameString, emailString, passwordString), date);
			addColumns();
			stage.setScene(home);
		});
		
		cancel.setOnAction(p -> {
			stage.setScene(scene);
		});
	}
	
	public static void main(String[] args) {
		launch();
	}
	
}
