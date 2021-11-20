//Author: Carter Morgan
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AccountListScene extends SceneBasic {
	private VBox listBox;
	
	//account list scene constructor
	public AccountListScene() {
		super("Account List");
		Label topTitle = new Label("Username : Account Type");
		root.getChildren().add(topTitle);	
		listBox = new VBox(20);
		listBox.setAlignment(Pos.CENTER);
		
		Button adminMenu = new Button("Admin Menu");
		adminMenu.setOnAction(e -> SceneManager.setAdminScene());
		
		Button logout = new Button("Logout");
		logout.setOnAction(e -> logout());

		root.getChildren().addAll(listBox,adminMenu,logout);
	}
	
	//recieves a message for each account in the server and adds labels for each
	public void getAccountList() {
		listBox.getChildren().clear();
		try {
			Socket conn = SceneManager.getSocket();
			PrintWriter outgoing = new PrintWriter(conn.getOutputStream());
			BufferedReader incoming = new BufferedReader(
					new InputStreamReader(conn.getInputStream()));
			outgoing.println("LIST");
			outgoing.flush();
			String lineFromServer = "";
			while(true) {
				lineFromServer = incoming.readLine();
				if(lineFromServer.equals("STOP")) break;
				System.out.println(lineFromServer);
				Label account = new Label(lineFromServer);
				listBox.getChildren().add(account);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
