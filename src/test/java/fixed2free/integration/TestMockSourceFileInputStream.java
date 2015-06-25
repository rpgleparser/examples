package fixed2free.integration;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.junit.Test;

import com.ibm.as400.access.AS400;

public class TestMockSourceFileInputStream {

	@Test
	public void testReadQSYSLIBFile() {
		MockSourceFileInputStream testobj = new MockSourceFileInputStream();
		AS400 dummy = new AS400();
		try {
			ANTLRInputStream x = testobj.readQSYSLIBFile(dummy, "*LIBL", "QRPGLESRC", "AIS001PR");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testReadIFSFile() {
		MockSourceFileInputStream testobj = new MockSourceFileInputStream();
		AS400 dummy = new AS400();
		try {
			ANTLRInputStream x = testobj.readIFSFile(dummy, "/QSYS.LIB/QGPL.LIB/QRPGLESRC.FILE/AIS001PR.MBR");
		} catch (IOException e) {
			fail("Unexpected I/O exception\n" + e.toString() );
		}
	}

	@Test
	public void testParseQSYSFilePath01() {
		MockSourceFileInputStream testobj = new MockSourceFileInputStream();
		Map<String, String> result = testobj.parseQSYSFilePath("AIS001PR");
		assertTrue("QSYSFilePath was bad", result.containsKey(ISourceFileInputStream.MAP_KEY_MEMBER));
		String member = result.get(ISourceFileInputStream.MAP_KEY_MEMBER);
		assertTrue("Wrong text "+ member +" stored in Member", member.equalsIgnoreCase("AIS001PR"));
		String file = result.get(ISourceFileInputStream.MAP_KEY_FILE);
		assertTrue("Wrong text stored in File", file.equalsIgnoreCase("QRPGLESRC"));
		String library = result.get(ISourceFileInputStream.MAP_KEY_LIBRARY);
		assertTrue("Wrong text stored in Library", library.equalsIgnoreCase("*LIBL"));
	}
	
	@Test
	public void testParseQSYSFilePath02() {
		MockSourceFileInputStream testobj = new MockSourceFileInputStream();
		Map<String, String> result = testobj.parseQSYSFilePath("QRPGLESRC,AIS001PR");
		assertTrue("QSYSFilePath was bad", result.containsKey(ISourceFileInputStream.MAP_KEY_MEMBER));
		String member = result.get(ISourceFileInputStream.MAP_KEY_MEMBER);
		assertTrue("Wrong text "+ member +" stored in Member", member.equalsIgnoreCase("AIS001PR"));
		String file = result.get(ISourceFileInputStream.MAP_KEY_FILE);
		assertTrue("Wrong text stored in File", file.equalsIgnoreCase("QRPGLESRC"));
		String library = result.get(ISourceFileInputStream.MAP_KEY_LIBRARY);
		assertTrue("Wrong text stored in Library", library.equalsIgnoreCase("*LIBL"));
	}
	@Test
	public void testParseQSYSFilePath03() {
		MockSourceFileInputStream testobj = new MockSourceFileInputStream();
		Map<String, String> result = testobj.parseQSYSFilePath("QGPL/QRPGLESRC,AIS001PR");
		assertTrue("QSYSFilePath was bad", result.containsKey(ISourceFileInputStream.MAP_KEY_MEMBER));
		String member = result.get(ISourceFileInputStream.MAP_KEY_MEMBER);
		assertTrue("Wrong text "+ member +" stored in Member", member.equalsIgnoreCase("AIS001PR"));
		String file = result.get(ISourceFileInputStream.MAP_KEY_FILE);
		assertTrue("Wrong text stored in File", file.equalsIgnoreCase("QRPGLESRC"));
		String library = result.get(ISourceFileInputStream.MAP_KEY_LIBRARY);
		assertTrue("Wrong text stored in Library", library.equalsIgnoreCase("QGPL"));
	}

}
