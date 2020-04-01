import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;



public class AlertBox {

  public void display(BonusMember member){

    Stage window = new Stage();

    window.initModality(Modality.APPLICATION_MODAL);
    window.setTitle(member.getClass().toString());
    window.setMinWidth(400);
    window.setMinWidth(400);

    Label label = new Label();
    label.setText("Member no: " + member.getMemberNo() + "\nPersoanls: " + member.getPersonals() + "\nEnrolled date: " + member.getEnrolledDate() + "\nPoints: " + member.getPoints() + "\nClass: " + member.getClass().getName());
    Button closebutton = new Button("Close");
    closebutton.setOnAction(e -> window.close());

    VBox layout = new VBox();
    layout.getChildren().addAll(label, closebutton);
    layout.setAlignment(Pos.CENTER);

    Scene alertBoxScene = new Scene(layout);
    window.setScene(alertBoxScene);
    window.showAndWait();
  }


}