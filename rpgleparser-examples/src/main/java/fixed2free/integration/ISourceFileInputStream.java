package fixed2free.integration;

import java.io.IOException;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;

/**
 * An interface describing the methods and constants used to bring a source member into scope
 * @author Eric N. Wilson
 *
 */
public interface ISourceFileInputStream {
	public static final String MAP_KEY_FILE = "FILE";
	public static final String MAP_KEY_LIBRARY = "LIBRARY";
	public static final String MAP_KEY_MEMBER = "MEMBER";
	/**
	 * Reads a file based on the library, File, Member passed in and returns an ANTLRInputStream
	 * Used in the expansion of copy members in RPG code
	 * @param as400 Required AS400 connection
	 * @param library Library the file lives in
	 * @param file Name of the file
	 * @param member The member of the file
	 * @return An ANTLR Input Stream suitable for parsing 
	 * @throws IOException 
	 * @throws AS400SecurityException 
	 */
	public ANTLRInputStream readQSYSLIBFile(AS400 as400, String library, String file, String member) throws IOException, AS400SecurityException;
	/**
	 * Reads a file based on the IFS Path and returns an ANTLRInputStream
	 * @param as400 Required AS400 connection
	 * @param ifsPath Full path to the file member to be read (something like "/QSYS.LIB/QGPL.LIB/QRPGLESRC.FILE/THEMEMBER.MBR")
	 * @return An ANTLR Input Stream suitable for parsing 
	 * @throws IOException 
	 * @throws AS400SecurityException 
	 */
	public ANTLRInputStream readIFSFile(AS400 as400, String ifsPath) throws IOException, AS400SecurityException;
	/**
	 * Returns the parsed QSYS File path from RPG /COPY format to the individual components
	 * @param input Takes the following forms <ul>
	 * <li>libraryname/filename,membername</li>
	 * <li>filename,membername</li>
	 * <li>membername</li>
	 * </ul>
	 * @return The map will have the components of the path in their own slot using the Static Final Strings MAP_KEY_* as the keys
	 */
	public Map<String, String> parseQSYSFilePath(String input) throws IllegalArgumentException;

}
