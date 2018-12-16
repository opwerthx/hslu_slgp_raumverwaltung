package persister;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Timestamp;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import api.User;

class UserLoginTest {

	static User peter = null;
	static User mona = null;
	static User marco = null;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		Date date= new Date();     
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);
		peter = new api.User(null, "peter", "testmUser", "testName", "testPass", "testRole", "testAdd", "testCUser", "testEmail", "testFirstName", ts,ts,ts);
		mona = new api.User(null, "mona", "testmUser2", "testName2", "testPass2", "testRole2", "testAdd2", "testCUser2", "testEmail2", "testFirstName2", ts,ts,ts);
		marco = new api.User(null, "marco", "testmUser3", "testName3", "testPass3", "testRole3", "testAdd3", "testCUser3", "testEmail3", "testFirstName3", ts,ts,ts);
	}

	@Test
	static void testCreateUser() {
		UserLogin logon = new UserLogin();
		boolean plist = logon.createUser(peter.getUsername(),peter.getEmail(),peter.getName(),peter.getPassword(), peter.getRole());
		assertEquals(true, plist);
		
		plist = logon.createUser(mona.getUsername(),mona.getEmail(),mona.getName(),mona.getPassword(), mona.getRole());
		assertEquals(true, plist);
		
		plist = logon.createUser(marco.getUsername(),marco.getEmail(),marco.getName(),marco.getPassword(), marco.getRole());
		assertEquals(true, plist);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		testCreateUser();
	}

	@AfterEach
	void tearDown() throws Exception {
		testDeleteUser();
	}
	public void  testDeleteUser() {
		    UserLogin logon = new UserLogin();
			api.User plist = logon.getUserInfo(peter.getUsername());
			assertEquals(peter.getUsername(), plist.getUsername());
			
			boolean returnvalue = logon.deleteUser(plist.getUserId());
			assertEquals(returnvalue, true);
			
			plist = logon.getUserInfo(mona.getUsername());
			assertEquals(mona.getUsername(), plist.getUsername());
			
			returnvalue = logon.deleteUser(plist.getUserId());
			assertEquals(returnvalue, true);
			
			plist = logon.getUserInfo(marco.getUsername());
			assertEquals(marco.getUsername(), plist.getUsername());
			
			returnvalue = logon.deleteUser(plist.getUserId());
			assertEquals(returnvalue, true);
       }

	@Test
	void testCheckUserLogin() {
		UserLogin logon = new UserLogin();
		boolean auth;
		auth = false;
		
		auth = logon.checkUserLogin(peter.getUsername(), peter.getPassword(), "TEST_SESSION");
		assertEquals(true, auth);
		auth = logon.checkUserLogin(mona.getUsername(), mona.getPassword(), "TEST_SESSION");
		assertEquals(true, auth);
		auth = logon.checkUserLogin(marco.getUsername(), marco.getPassword(), "TEST_SESSION");
		assertEquals(true, auth);	
	}

	@Test
	void testCheckUserLoginNegativ() {
		UserLogin logon = new UserLogin();
		boolean auth;
		auth = false;
		auth = logon.checkUserLogin(peter.getUsername(), "wrongPass", "TEST_SESSION");
		assertEquals(false, auth);
	}
	
	@Test
	void testCheckUserLoginSQLInjection() {
		UserLogin logon = new UserLogin();
		boolean auth;
		auth = false;
		auth = logon.checkUserLogin(peter.getUsername(), "*", "TEST_SESSION");
		assertEquals(false, auth);
	}

	@Test
	void testUpdateUser() {
		UserLogin logon = new UserLogin();
		api.User plist = logon.getUserInfo(peter.getUsername());
		assertEquals(peter.getUsername(), plist.getUsername());
		assertEquals(plist.getPassword(), "testPass");
		assertEquals(plist.getRole(), "testRole");
		User r = plist;		
		r.setRole("testRole");
		r.setPassword("testPass");
		boolean resultvalue = logon.updateUser(r);	

		plist = logon.getUserInfo(peter.getUsername());
		assertEquals(resultvalue, true);
		assertEquals(plist.getPassword(), "testPass");
		assertEquals(plist.getRole(), "testRole");
		
		plist = logon.getUserInfo(mona.getUsername());
		assertEquals(mona.getUsername(), plist.getUsername());
		assertEquals(mona.getUsername(), plist.getUsername());
		assertEquals(plist.getPassword(), "testPass2");
		assertEquals(plist.getRole(), "testRole2");
		 r = plist;		
			r.setRole("testRole2");
			r.setPassword("testPass2");
		 resultvalue = logon.updateUser(r);	

		plist = logon.getUserInfo(mona.getUsername());
		assertEquals(resultvalue, true);
		assertEquals(plist.getPassword(), "testPass2");
		assertEquals(plist.getRole(), "testRole2");
		
		plist = logon.getUserInfo(marco.getUsername());
		assertEquals(marco.getUsername(), plist.getUsername());
		assertEquals(plist.getPassword(), "testPass3");
		assertEquals(plist.getRole(), "testRole3");
		 r = plist;		
			r.setRole("testRole3");
			r.setPassword("testPass3");
		 resultvalue = logon.updateUser(r);	

		plist = logon.getUserInfo(marco.getUsername());
		assertEquals(resultvalue, true);
		assertEquals(plist.getPassword(), "testPass3");
		assertEquals(plist.getRole(), "testRole3");
	}
	
	
	@Test
	void testGetUserInfo() {
		UserLogin logon = new UserLogin();
		api.User plist = logon.getUserInfo(peter.getUsername());
		assertEquals("peter", plist.getUsername());
		assertEquals("testName", plist.getName());
		assertEquals("testPass", plist.getPassword());
		assertEquals("testRole", plist.getRole());
		assertEquals("testEmail", plist.getEmail());

		
		plist = logon.getUserInfo(mona.getUsername());
		assertEquals("mona", plist.getUsername());
		assertEquals("testName2", plist.getName());
		assertEquals("testPass2", plist.getPassword());
		assertEquals("testRole2", plist.getRole());
		assertEquals("testEmail2", plist.getEmail());

		
		plist = logon.getUserInfo(marco.getUsername());
		assertEquals("marco", plist.getUsername());
		assertEquals("testName3", plist.getName());
		assertEquals("testPass3", plist.getPassword());
		assertEquals("testRole3", plist.getRole());
		assertEquals("testEmail3", plist.getEmail());

	}
	


}
