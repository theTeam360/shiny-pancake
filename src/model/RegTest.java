package model;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.Test;

public class RegTest {

	@Test
	public void userExists_test() throws IOException {
		//Known user: Bill, pw: asdas
		boolean res = Registration.userExists("Bill", null);
		assertTrue(res);
		res = Registration.userExists("Bill", "asdas");
		assertTrue(res);
		
		//Wrong password
		res = Registration.userExists("Bill", "fafdsa");
		assertFalse(res);
		
		//Wrong user
		res = Registration.userExists("test5", "asdas");
		assertFalse(res);
	}
	
	@Test
	public void findEmail_test() throws IOException {
		
		String email = Registration.findEmail("Bill");
		assertTrue("asfdsadfa".equals(email));
		email = Registration.findEmail("robert");
		assertTrue("-1".equals(email));
		email = Registration.findEmail("test");
		assertTrue("0".equals(email));
	}
	
	
	@Test
	public void add_test() throws IOException {
		
		int res = Registration.add("Bill","","","","","","","");
		assertSame(-1, res);
		res = Registration.add("teset","a","b","c","d","e","f","g");
		assertSame(0, res);
		
		//Erase test save
		String filename= "./src/model/RegData.txt";
		File myFile = new File(filename);
		List<String> lines = Files.readAllLines(myFile.toPath());
		if (lines.size() == 2) {
			lines.remove(1);
			lines.set(0, "");
		} else {
			lines.remove(lines.size()-1);
		}
		
		Files.write(myFile.toPath(), lines);

	}
}
