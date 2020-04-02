import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadListener;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main extends Application implements EventHandler<ActionEvent> {

  private Stage window;
  private Scene scene, scene2, scene3, scene4, scene5;
  private TableView<BonusMember> table, table2;




  private static Logger logger;
  MemberArchive memberArchive;
  ArrayList<BonusMember> members = new ArrayList<>();
  ObservableList<BonusMember> bonusMembers;
  AlertBox alertBox = new AlertBox();
  int infoId;

  @Override
  public void start(Stage primaryStage) throws IOException{


    fillMemberArchive();

    window = primaryStage;

    window.setTitle("Mads Lundegaard hater JavaFX - Oblig 4");
    window.setWidth(1000);
    window.setHeight(600);


    tableWithMembers();

    getScene1();

    getScene2();

    addBasicMember();

    getScene4();





    window.setScene(scene);
    window.show();



  }

  public ObservableList<BonusMember> getValues(){
    bonusMembers = FXCollections.observableArrayList(memberArchive.listAll());
    return bonusMembers;
  }

  @Override
  public void handle(ActionEvent actionEvent) {

  }

  /**
   * Added logger and set level to warning
   * @param args
   */
  public static void main(String[] args) throws IOException{
    logger = LoggerFactory.getLogger("Obliglogger");
    launch(args);
     /*   try {
            Log mylog = new Log("oving4log.txt");
            mylog.logger.setLevel(Level.WARNING);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
  }

  public void fillMemberArchive() throws IOException{
    Personals eksempelpersonals = new Personals("Mads","Lundegaard","@epost.no","Test123");
    Personals eksempelpersonals2 = new Personals("Eirik","Steira","@epost.no","Test123");
    Personals eksempelpersonals3 = new Personals("Simon", "Jensen", "@epost.no", "Test123");
    Personals eksempelpersonals4 = new Personals("Lars", "Brodin", "@epost.no", "Test123");
    BasicMember a = new BasicMember(1, eksempelpersonals, LocalDate.of(2010, 10, 10));
    BasicMember b = new BasicMember(2,eksempelpersonals2,LocalDate.of(2011, 11, 11));
    this.members.add(a);
    this.members.add(b);
    memberArchive = new MemberArchive(members);
    memberArchive.newMember(eksempelpersonals3, LocalDate.of(2010, 10, 10));
    memberArchive.newMember(eksempelpersonals4, LocalDate.of(2011, 11, 11));
    memberArchive.registerPoints(1,1000);
    memberArchive.registerPoints(2,30005);
    memberArchive.registerPoints(memberArchive.findMemberByPersonals(eksempelpersonals3).getMemberNo(), 10000);
    memberArchive.registerPoints(memberArchive.findMemberByPersonals(eksempelpersonals4).getMemberNo(), 150000);
  }

  public void tableWithMembers(){
    TableColumn<BonusMember, Integer> nrColumn =  new TableColumn<>("MemberNo");
    nrColumn.setMinWidth(50);
    nrColumn.setCellValueFactory(new PropertyValueFactory("memberNo"));

    TableColumn<BonusMember, Personals> nameColumn =  new TableColumn<>("Peronals");
    nameColumn.setMinWidth(50);
    nameColumn.setCellValueFactory(new PropertyValueFactory("personals"));

        /*TableColumn<BonusMember, Personals> surnameColumn =  new TableColumn<>(" Last Name");
        surnameColumn.setMinWidth(50);
        surnameColumn.setCellValueFactory(new PropertyValueFactory("surname"));*/

    TableColumn<BonusMember, LocalDate> dateColumn =  new TableColumn<>("Enrolled Date");
    dateColumn.setMinWidth(50);
    dateColumn.setCellValueFactory(new PropertyValueFactory("enrolledDate"));

    TableColumn<BonusMember, Integer> pointColumn =  new TableColumn<>("Points");
    pointColumn.setMinWidth(50);
    pointColumn.setCellValueFactory(new PropertyValueFactory("points"));

    TableColumn<BonusMember, BonusMember> classColumn = new TableColumn<>("Class");
    classColumn.setMinWidth(50);
    classColumn.setCellValueFactory(new PropertyValueFactory("class"));

    table = new TableView<>();
    table.refresh();
    table.setItems(FXCollections.observableArrayList(memberArchive.listAll()));
    table.setItems(getValues());
    table.getColumns().addAll(nrColumn, nameColumn, dateColumn, pointColumn);
  }

  public void getScene1(){

    table.setItems(FXCollections.observableArrayList(memberArchive.listAll()));
    //Button nr 1
    Button menuButton = new Button("Go to menu");
    menuButton.setOnAction(e -> window.setScene(scene4));
    //Scene nr 1
    VBox layout1 = new VBox(20);
    layout1.getChildren().addAll(table, menuButton);
    scene = new Scene(layout1);
  }

  public void getScene2(){

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10));
    grid.setVgap(8);
    grid.setHgap(10);

    //Creates another button
    Button button2 = new Button("Go back");
    button2.setOnAction(e -> window.setScene(scene));
    GridPane.setConstraints(button2,0,1);

    Button button3 = new Button("Add new member");
    button3.setLineSpacing(30);
    button3.setOnAction(e -> window.setScene(scene3));
    GridPane.setConstraints(button3,0,2);

    Button button4 = new Button("Delete member");
    button4.setOnAction(e -> window.setScene(scene4));
    GridPane.setConstraints(button4,0,3);

    //Creates another layout
    StackPane layout2 = new StackPane();
    grid.getChildren().addAll(button3, button2, button4);
    scene2 = new Scene(grid);

  }

  public void addBasicMember() throws IOException{

    GridPane grid = new GridPane();
    grid.setPadding(new Insets(10));
    grid.setVgap(8);
    grid.setHgap(10);

    Label nameLabel = new Label("First name");
    Label nameLabel2 = new Label("Last name");
    Label mailLabel = new Label("email");
    Label passwordLabel = new Label("passwrd");

    GridPane.setConstraints(nameLabel,0,0);
    GridPane.setConstraints(nameLabel2,0,1);
    GridPane.setConstraints(mailLabel,0,2);
    GridPane.setConstraints(passwordLabel,0,3);

    TextField nameInput = new TextField();
    TextField name2Input = new TextField();
    TextField mailInput = new TextField();
    TextField passwordInput = new TextField();

    GridPane.setConstraints(nameInput,1,0);
    GridPane.setConstraints(name2Input,1,1);
    GridPane.setConstraints(mailInput,1,2);
    GridPane.setConstraints(passwordInput,1,3);

    Button addButton = new Button("Register member");
    Button backButton = new Button("Back");
    //Button printButton = new Button("System out print");

    //addbutton creates a new bonus member with innputs, then updates the table contents, with setItem
    addButton.setOnAction(e -> {
      memberArchive.newMember(new Personals(nameInput.getText(),name2Input.getText(),mailInput.getText(),passwordInput.getText()), LocalDate.of(2020,3,26));
      table.setItems(FXCollections.observableArrayList(memberArchive.listAll())); window.setScene(scene4); });


    GridPane.setConstraints(addButton,2,0);
    backButton.setOnAction(e -> window.setScene(scene4));
    GridPane.setConstraints(backButton,2,1);
        /*printButton.setOnAction(e -> System.out.println(memberArchive.getMembersList()));
        GridPane.setConstraints(printButton,2,2);*/

    grid.getChildren().addAll(nameInput,name2Input, mailInput, passwordInput, addButton, nameLabel, nameLabel2, mailLabel, passwordLabel, backButton);

    scene3 = new Scene(grid, 500, 500);
    window.setScene(scene3);
    window.show();

  }


  public void getScene4(){

    tableWithMembers();

    VBox layout2 = new VBox(5);
    TextField idInput = new TextField("Skriv id");

    Button button2 = new Button("Check all members");
    button2.setOnAction(e -> {
      memberArchive.checkMembers(LocalDate.of(2010, 12, 12));
      table.setItems(FXCollections.observableArrayList(memberArchive.listAll()));});

    Button goToAddButton = new Button("Add members");
    goToAddButton.setOnAction(e -> window.setScene(scene3));

    Button deleteButton = new Button("Delete chosen member");
    deleteButton.setOnAction(e -> {
      memberArchive.deleteMember(Integer.parseInt(idInput.getText()));
      table.setItems(FXCollections.observableArrayList(memberArchive.listAll()));
    });

    Button infoButton = new Button("Get info from chosen member");

    infoButton.setOnAction(e -> {
      infoId = Integer.parseInt(idInput.getText());
      alertBox.display(memberArchive.findMember(infoId));
    });

    layout2.getChildren().addAll(table, idInput, button2, deleteButton, goToAddButton, infoButton);
    scene4 = new Scene(layout2);

  }

}
