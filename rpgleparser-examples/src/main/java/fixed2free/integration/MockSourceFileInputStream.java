package fixed2free.integration;

import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.ibm.as400.access.AS400;

public class MockSourceFileInputStream extends AbstractSourceFileInputStream
		implements ISourceFileInputStream {

	public ANTLRInputStream readQSYSLIBFile(AS400 as400, String library,
			String file, String member) throws IOException {
		ANTLRInputStream result = null;
		FileReader fr = null;
		if (member.equalsIgnoreCase("AIS001PR")) {
			fr = new FileReader(
					"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/AIS001PR.RPGLE");
		} else if (member.equalsIgnoreCase("INS002PR")) {
			fr = new FileReader(
					"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/INS002PR.RPGLE");
		} else if (member.equalsIgnoreCase("UTRCPY004")) {
			fr = new FileReader(
					"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/UTRCPY004.RPGLE");
		} else {
			return null;
		}
		result = new ANTLRInputStream(fr);
		return result;
	}

	public ANTLRInputStream readIFSFile(AS400 as400, String ifsPath)
			throws IOException {
		ANTLRInputStream result = null;
		FileReader fr = null;
		String[] temp = ifsPath.split("/");
		int lastIndex = temp.length - 1;
		if (lastIndex < 0) {
			return null;
		}
		if (temp[lastIndex].toLowerCase().matches(".*\\.mbr")) {
			String[] temp2 = temp[lastIndex].split("\\.");
			String member = temp2[0];
			if (member.equalsIgnoreCase("AIS001PR")) {
				fr = new FileReader(
						"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/AIS001PR.RPGLE");
			} else if (member.equalsIgnoreCase("INS002PR")) {
				fr = new FileReader(
						"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/INS002PR.RPGLE");
			} else if (member.equalsIgnoreCase("UTRCPY004")) {
				fr = new FileReader(
						"/Users/doulos1_2000/Documents/rpgleparser/src/test/resources/org/rpgleparser/integration/UTRCPY004.RPGLE");
			} else {
				return null;
			}
			result = new ANTLRInputStream(fr);

		}
		return result;
	}

}
