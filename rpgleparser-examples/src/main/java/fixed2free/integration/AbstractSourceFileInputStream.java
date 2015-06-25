package fixed2free.integration;

import java.util.HashMap;
import java.util.Map;

/**
 * Implements the basic function to parse a QSYS File path as expressed in RPG
 * Descendant classes will implement the rest.
 * @author Eric N. Wilson
 *
 */
public abstract class AbstractSourceFileInputStream implements
		ISourceFileInputStream {

	public  Map<String, String> parseQSYSFilePath(String input) throws IllegalArgumentException{
		HashMap<String, String> result = new HashMap<String, String>();
		String[] temp = input.split("[/,]");
		if (temp.length == 1){
			// User should have only provided a member name
			result.put(MAP_KEY_MEMBER, temp[0]);
			result.put(MAP_KEY_LIBRARY, "*LIBL");
			result.put(MAP_KEY_FILE, "QRPGLESRC");
			
		} else if (temp.length == 2){
			result.put(MAP_KEY_LIBRARY, "*LIBL");
			result.put(MAP_KEY_FILE, temp[0]);
			result.put(MAP_KEY_MEMBER, temp[1]);
		} else if (temp.length == 3){
			result.put(MAP_KEY_LIBRARY, temp[0]);
			result.put(MAP_KEY_FILE, temp[1]);
			result.put(MAP_KEY_MEMBER, temp[2]);
		} else {
			throw new IllegalArgumentException("Too many components in QSYS Filename");
		}
		return result;
	}

}
