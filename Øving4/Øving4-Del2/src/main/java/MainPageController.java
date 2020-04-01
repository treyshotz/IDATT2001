import com.sun.javafx.scene.control.IntegerField;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class MainPageController {

  MemberArchive memberArchive;
  @FXML
  private ListView listView;
  ObservableList observableList = FXCollections.observableArrayList();
  public IntegerField deleteUser;



  @FXML
  void initialize() {
    memberArchive = new MemberArchive();

    Personals ole = new Personals("Olsen", "Ole", "ole.olsen@dot.com", "ole");
    Personals tove = new Personals("Hansen", "Tove", "tove.hansen@dot.com", "tove");
    LocalDate testdato = LocalDate.of(2008, 2, 10);

    memberArchive.newMember(ole, testdato);
    memberArchive.newMember(tove, testdato);
    listall();
  }

  public void listall() {


    ArrayList<BonusMember> bonusMembers =  memberArchive.listAll();

    observableList.setAll(bonusMembers);
    listView.setItems(observableList);

  }

  public void deleteMember() {
    int userID = deleteUser.getValue();
    if(memberArchive.deleteMember(userID)) {
      listall();
    }
  }
}
