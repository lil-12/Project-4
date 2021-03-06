import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;

public class InventoryReader 
{	
	public static void main(String []args)
	{
		HashMap<String,String> inventory = readFile("inventory.txt");
		for (String key : inventory.keySet()) 
		{
			System.out.print("Key = " + key + ", ");
			System.out.println("Data = " + inventory.get(key));
		}
		
	}
	
	public static HashMap<String,String> readFile(String filename)
	{
		HashMap <String,String> inventory = new HashMap <String,String>();
		try 
		{
			File file = new File(filename);
			BufferedReader in = new BufferedReader(new FileReader(file));
			String thisLine = removeTab(in.readLine());
			while(thisLine!=null)
			{
				if(removeTab(thisLine).equals("<PRODUCT>"))
				{
					thisLine = removeTab(in.readLine());
					String stockNumber= "";
					String description="";
					
					while(!thisLine.equals("</PRODUCT>"))
					{
						if(thisLine.equals("<stockNumber>"))
						{
							thisLine = removeTab(in.readLine());
							while(!thisLine.equals("</stockNumber>"))
							{
								stockNumber = stockNumber.concat(thisLine);
								thisLine = removeTab(in.readLine());
							}
						}
						else if(thisLine.equals("<description>"))
						{
							thisLine = removeTab(in.readLine());
							while(!thisLine.equals("</description>"))
							{
								description=description.concat(thisLine);
								thisLine = removeTab(in.readLine());
							}
						}
						thisLine = removeTab(in.readLine());
					}
					if(!description.equals("")&&!stockNumber.equals(""))
					{
						inventory.put(stockNumber, description);
					}	
				}	
				thisLine = removeTab(in.readLine());
			}
			in.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return inventory;	
	}
	
	private static String removeTab(String string)
	{
		if(string == null)
			return string;
		else return string.substring(string.lastIndexOf("\t")+1);
	}
		
}
