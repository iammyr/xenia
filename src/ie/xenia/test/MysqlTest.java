package ie.xenia.test;

import static org.junit.Assert.assertTrue;
import ie.xenia.database.MysqlManager;
import ie.xenia.resource.user.Person;
import ie.xenia.resource.user.Person.Name;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

public class MysqlTest extends XeniaTestHelper {


	@Before
	public void init(){
		this.mysql = new MysqlManager(this.dbUrl, this.dbUser, this.dbPassw, this.dbName);

	}

	@Test
	public void testMysqlConnection(){
		Connection conn = null;
		try {
			assertTrue((conn=mysql.connect()) != null);
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			assertTrue(MysqlManager.disconnect(conn));
		}
	}

	@Test 
	public void testRegister() throws SQLException{
		Person person = new Person();
		Name name = new Name();
		String uname = (personNick+UUID.randomUUID().toString()).substring(0, 20);
		name.setNick(uname);

		person.setName(name);
		person.setPassword(this.personPassword);		

		assertTrue(mysql.register(person)==1);
		name.setNick(personNick);
		//			person.setName(name);
		assertTrue(mysql.register(person)==2);

	}


}
