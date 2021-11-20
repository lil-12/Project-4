//	NOT PART OF THE PROJECT
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class PasswordHelper {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket conn = new Socket("localhost", 32007);
			BufferedReader incoming = new BufferedReader( 
	                new InputStreamReader(conn.getInputStream()));
			PrintWriter outgoing = new PrintWriter(
					new OutputStreamWriter(conn.getOutputStream()), true);
			outgoing.println("LOGIN");
			outgoing.println("username,password");
			outgoing.println("PASSWORD");
			outgoing.println("password,new");
			
			String line = "";
			while(!line.equals("")) {
				line = incoming.readLine();
				System.out.println("|" + line);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
