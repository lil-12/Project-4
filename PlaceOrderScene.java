

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class PlaceOrderScene extends SceneBasic
{

	//VBox root = new VBox();//container holding most elements of the scene
	Label list = new Label("");//list of accounts to be displayed
	TextField stockNumber;
	TextField quantity;
	
	public PlaceOrderScene()
	{
		super("Account List Menu");
		
		Label title = new Label("Account List Scene");//large title
		title.setFont(new Font(40));

		Button customerMenu = new Button("CustomerMenu");//brings you back to adminMenu
		Button logOut = new Button("Log out");//logs you out (but does not disconnect from server)
		Button submit = new Button("Submit order");
		Button quit = new Button("Quit");
		stockNumber = new TextField();
		quantity = new TextField();
		Label stockLabel = new Label("Stock Number:");
		Label quantLabel = new Label("Quantity:");
		
		
		customerMenu.setOnAction(e-> SceneManager.setCustomerScene());
		logOut.setOnAction(e-> SceneManager.setLoginScene());
		submit.setOnAction(e->sendOrder());
		quit.setOnAction(e->super.logout());
		
		GridPane gp = new GridPane();
		gp.setAlignment(Pos.CENTER);
		gp.setVgap(10);
		gp.setHgap(5);
		
		gp.add(customerMenu, 0, 2);
		gp.add(logOut, 1, 2);
		gp.add(quit, 3, 2);//button from SceneBasic that cuts connections and returns to loginScene
		
		
		HBox textfields = new HBox();
		textfields.getChildren().addAll(stockLabel, stockNumber,new Label("\t"),quantLabel,quantity);
		
		
		root = new VBox(30, title,list,textfields, gp);
		root.setAlignment(Pos.CENTER);

		BorderPane bp = new BorderPane(root);
		BorderPane.setAlignment(bp, Pos.CENTER);
		//super.getScene = new Scene(bp, 400, 300);
		
	}
	
	public void sendOrder()
	{
		try 
		{
			Socket socket = SceneManager.getSocket();
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			out.println("GET_ORDER");//signal
			out.flush();
			out.println(stockNumber.getText());
			out.println(quantity.getText());
			out.close();
			stockNumber.setText("");
			quantity.setText("");
		} 
		catch (Exception e) 
		{	
		}
	}
	
	
	public void getInventory() 
	{
		try 
		{
			Socket socket = SceneManager.getSocket();
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(SceneManager.getSocket().getOutputStream());
			out.println("GET_INV");//signal
			out.flush();
			out.close();
			int numOfMessages = Integer.parseInt(in.readLine());
			String listString = new String("Stock Number\tDescription\n" );
			for(int i=0;i<numOfMessages;i++)
			{
				listString = listString.concat(in.readLine()+"\n");
			}
			list = new Label();
			list.setText(listString);
			in.close();
			//root.add(list, 0, 0);		
		} 
		catch (Exception e) 
		{	
		}
	}
}
