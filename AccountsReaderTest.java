//Author: Carter Morgan
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AccountsReaderTest {
	
	private HashMap<String, Account> accounts;
	
	@BeforeEach
	void setUp() throws Exception {
		CustomerAccount customer = new CustomerAccount(9, "username", "password", "profile");
		AdminAccount admin = new AdminAccount(2, "admin", "admin");
		CustomerAccount customer2 = new CustomerAccount(3, "customer", "1234", "I am a customer");
		AdminAccount admin2 = new AdminAccount(4, "boss", "mypassword");
		accounts = new HashMap<String, Account>();
		accounts.put(customer.getID(), customer);
		accounts.put(customer2.getID(), customer2);
		accounts.put(admin.getID(), admin);
		accounts.put(admin2.getID(), admin2);
	}

	@Test
	void test() {
		HashMap<String, Account> res = AccountsReader.readFile("accounts.xml");
		System.out.println("BEFORE");
		System.out.println(res.size());
		for (String key : res.keySet()) {
			System.out.println(key);
			Account resultAccount = res.get(key);
			Account expectedAccount = accounts.get(key);
			System.out.println("result " + resultAccount.toString());
			System.out.println("expected " + expectedAccount.toString());
			assert resultAccount.toString().equals(expectedAccount.toString()) : "accounts were not the same";
		}
		System.out.println("AFTER");
	}
}
