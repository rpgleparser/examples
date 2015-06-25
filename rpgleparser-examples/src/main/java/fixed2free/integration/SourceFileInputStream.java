package fixed2free.integration;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Exception;
import com.ibm.as400.access.AS400File;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.IFSFile;
import com.ibm.as400.access.IFSFileInputStream;
import com.ibm.as400.access.IFSFileReader;
import com.ibm.as400.access.Record;

public class SourceFileInputStream extends AbstractSourceFileInputStream {

	public ANTLRInputStream readQSYSLIBFile(AS400 as400, String library,
			String file, String member) throws IOException, AS400SecurityException {
		IFSFile temp = new IFSFile(as400, "QSYS.LIB/" + library.trim() + ".LIB/" + file.trim() + ".FILE/" + member.trim() + ".MBR" );
		IFSFileReader reader = new IFSFileReader(temp);
		ANTLRInputStream result = new ANTLRInputStream(reader);
		return result;
	}

	public ANTLRInputStream readIFSFile(AS400 as400, String ifsPath)
			throws IOException, AS400SecurityException {
		IFSFile temp = new IFSFile(as400, ifsPath);
		IFSFileReader reader = new IFSFileReader(temp);
		ANTLRInputStream result = new ANTLRInputStream(reader);
		return result;
	}

}
