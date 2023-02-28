package movie;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController implements Initializable {
	@FXML private Button btnLogin;
	@FXML private Button btnJoin;
	@FXML private TextField tfId;
	@FXML private PasswordField pfPw;
	@FXML private CheckBox checkpass;

	private Stage primaryStage;	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		btnLogin.setOnAction(event->handleBtnLoginAction(event));
		btnJoin.setOnAction(event->handleBtnJoinAction(event));
	}
	

	public void handleBtnLoginAction(ActionEvent event) { 
		
		if(tfId.getText().equals("")) {
			dlgMsg("아이디를 입력하세요.");
			return;
		} else if(pfPw.getText().equals("")) {
			dlgMsg("패스워드를 입력하세요.");
			return;
		}
	
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("member_main.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("메인화면");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		Stage stage11 = (Stage) btnLogin.getScene().getWindow();
		Platform.runLater(() -> {
			stage11.close();
		});
	}

	public void handleBtnJoinAction(ActionEvent event) { 
		Stage primaryStage = new Stage();
		Parent root;
		try {
			root = FXMLLoader.load(getClass().getResource("member_join.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("회원가입화면");
			primaryStage.setScene(scene);
			primaryStage.show();
			primaryStage.setResizable(false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void dlgMsg(String msg){
		Stage dialog = new Stage(StageStyle.UTILITY);
		dialog.initModality(Modality.WINDOW_MODAL);
		dialog.initOwner(primaryStage);
		dialog.setTitle("로그인 알림");
		
		Parent parent;
		try {
			parent = FXMLLoader.load(getClass().getResource("dialog.fxml"));
			Label txtTitle = (Label) parent.lookup("#txtTitle");
			txtTitle.setText(msg);
			Button btnOk = (Button) parent.lookup("#btnOk");
			btnOk.setOnAction(event->dialog.close());	
			Scene scene = new Scene(parent);
			dialog.setScene(scene);
			dialog.setResizable(false);
			dialog.show();
		} catch (IOException e) {
		}		

	}
	
}
