package fixed2free;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import fixed2free.integration.IFileInfoProvider;
import fixed2free.integration.ColumnInfo;

/**
 * A simple representation of a compiler symbol, be that variables, constants, pre-processor symbols, 
 * or other constructs that have a name and optional datatype and value. Some key attributes are 
 * predefined, and there is a mechanism to support arbitrary others.<br>
 * I am using static strings rather than enums because I want the user to be able to 
 * extend what is held in the list of symbol attributes.
 * The list of symbols that are predefined start with CAT_, DF_, DT_, SO_
 * @author Eric N. Wilson
 *
 */
public class Symbol implements Comparator<Symbol>{
	public static final String CAT_ARRAY_ELEMENT_COUNT = "ARRAY_ELEMS";

	/** I am using static strings rather than enums because I want the user to be able to 
	 * extend what is held in the list of symbol attributes.
	 * The list of symbols that are predefined start with DT_
	 * 
	 */
	private static final String CAT_CCSID = "CCSID";
	public static final String CAT_DATA_TYPE="DATA TYPE";
	/**
	 * CAT_DECIMAL_POSITIONS Is an optional attribute that pertains only to Packed and Zoned fields
	 */
	public static final String CAT_DECIMAL_POSITIONS = "DEC_POS";
	/**
	 * Definition type is Marked by the DS, S, ' ' normally on a D-Spec. 
	 * I have moved the array, table stuff and the multiple occurrence datastructure here too 
	 * since my intent is to be able to tell if the variable is the right type for an operation
	 * The first level of checking is to see if this is an array or data structure or just a field.
	 */
	public static final String CAT_DEFINITION_TYPE = "DEFINITION_TYPE";
	/**
	 * CAT_LENGTH Is an optional attribute that records the length of a field
	 */
	public static final String CAT_LENGTH = "LENGTH";
	public static final String CAT_RECORD_FORMAT = "RECORD_FORMAT";
	public static final String CAT_SYMBOL_ORIGIN = "SYMBOL_ORIGIN";
	public static final String CAT_TABLE_NAME = "TABLE_NAME";
	
	public static final String CAT_VARYING_LENGTH = "VARYING";
	public static final String DF_ARRAY="ARRAY";
	public static final String DF_CONSTANT="CONSTANT";
	public static final String DF_DATA_STRUCTURE = "DATA STRUCTURE";
	public static final String DF_KLIST="KLIST";
	public static final String DF_MULTIPLE_OCCURANCE_DATA_STRUCTURE = "MULTIPLE_OCCUR";
	public static final String DF_PARAMETER_INTERFACE = "PARAMETER INTERFACE";
	public static final String DF_PLIST="PLIST";
	public static final String DF_PRECOMPILER_SYMBOL="PRECOMPILER SYMBOL";
	
	public static final String DF_PROTOTYPE = "PROTOTYPE";
	public static final String DF_STANDALONE = "STAND-ALONE";
	public static final String DF_SUBFIELD = "SUBFIELD";
	public static final String DF_TABLE="TABLE";
	public static final String DT_ALPHANUM="ALPHANUM";
	public static final String DT_BINARY = "BINARY";
	public static final String DT_DATE="DATE";
	public static final String DT_FLOAT="FLOAT";
	public static final String DT_GRAPHIC="GRAPHIC";
	public static final String DT_INDICATOR="INDICATOR";
	public static final String DT_INTEGER="INTEGER";
	public static final String DT_LEFTSIGN="LEFTSIGN";
	public static final String DT_OBJECT = "OBJECT";
	public static final String DT_PACKED="PACKED";
	public static final String DT_POINTER="POINTER";
	public static final String DT_PROC_POINTER= "PROC_PTR";
	public static final String DT_RIGHTSIGN="RIGHTSIGN";
	public static final String DT_TIME="TIME";
	public static final String DT_TIMESTAMP="TIMESTAMP";
	public static final String DT_UCS2="UCS2";
	public static final String DT_UNSIGNED="UNSIGNED";
	
	public static final String DT_ZONED="ZONED";
	public static final String SO_C_SPECS = "C-SPECS";
	public static final String SO_D_SPECS = "D-SPECS";
	public static final String SO_EXTERNAL_FILE_DESCRIPTION = "EXTERNAL_FILE";
	public static final String SO_I_SPECS = "I-SPECS";

	public static final String SO_O_SPECS = "O-SPECS";

	public static final String DT_PREPROCESSOR_SYMBOL = "PREPROCESSOR_SYMBOL";

	public static final String SO_DEFINE = "/DEFINE";

	/**
	 * Sets the Data Type, Length and possibly Decimal positions or CCSID if relevant. The 
	 * ColumnInfo should have been provided by the APIBasedInfoProvider.
	 * @param col A ColumnInfo instance that is used to derive the attributes for the symbol
	 * @param sym The symbol to be modified
	 */
	public static void as400Attr2rpg(ColumnInfo col, Symbol sym) {
		if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_BINARY)){
			sym.addAttribute(CAT_DATA_TYPE, DT_INTEGER);
			sym.addAttribute(CAT_LENGTH, col.getFieldLengthInBytes().toString());
			sym.addAttribute(CAT_DECIMAL_POSITIONS, col.getDecimalPositions().toString());
		}  else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_PACKED_DECIMAL)){
			sym.addAttribute(CAT_DATA_TYPE, DT_PACKED);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getDigits()));
			sym.addAttribute(CAT_DECIMAL_POSITIONS, Integer.toString(col.getDecimalPositions()));
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_ZONED_DECIMAL)){
			sym.addAttribute(CAT_DATA_TYPE, DT_ZONED);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getDigits()));
			sym.addAttribute(CAT_DECIMAL_POSITIONS, Integer.toString(col.getDecimalPositions()));
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_FLOATING_POINT)){
			sym.addAttribute(CAT_DATA_TYPE, DT_FLOAT);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getFieldLengthInBytes()));
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_ALPHA	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_ALPHANUM);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getFieldLengthInBytes()));
			sym.addAttribute(CAT_CCSID, Integer.toString(col.getFieldDataCCSID()));
			if (col.getVariableLengthFieldIndicator().equalsIgnoreCase("1")){
				sym.addAttribute(CAT_VARYING_LENGTH, "TRUE");
			}
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.AS400_GRAPHIC	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_GRAPHIC);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getNumberOfDBCSCharacters()));
			sym.addAttribute(CAT_CCSID, Integer.toString(col.getFieldDataCCSID()));
			if (col.getVariableLengthFieldIndicator().equalsIgnoreCase("1")){
				sym.addAttribute(CAT_VARYING_LENGTH, "TRUE");
			}
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.SQL_DATE	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_DATE);
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.SQL_TIME	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_TIME);
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.SQL_TIMESTMP	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_TIMESTAMP);
		} else if (col.getDataType().equalsIgnoreCase(IFileInfoProvider.SQL_ROWID	)){
			sym.addAttribute(CAT_DATA_TYPE, DT_ALPHANUM);
			sym.addAttribute(CAT_LENGTH, Integer.toString(col.getFieldLengthInBytes()));
			sym.addAttribute(CAT_VARYING_LENGTH, "TRUE");
		}
	}
	
	/**
	 * Returns the RPG data type for a given SQL data type
	 * @param dataType
	 * @return
	 */
	public static String sqlDataType2rpg(String dataType) {
		String result = null;
		if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_BIGINT)){
			result = DT_INTEGER;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_INTEGER)){
			result = DT_INTEGER;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_SMALLINT)){
			result = DT_INTEGER;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_DECIMAL)){
			result = DT_PACKED;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_NUMERIC)){
			result = DT_ZONED;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_DOUBLE)){
			result = DT_FLOAT;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_FLOAT)){
			result = DT_FLOAT;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_REAL	)){
			result = DT_FLOAT;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_CHAR	)){
			result = DT_ALPHANUM;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_VARCHAR	)){
			result = DT_ALPHANUM;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_GRAPHIC	)){
			result = DT_GRAPHIC;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_VARG	)){
			result = DT_GRAPHIC;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_BINARY	)){
			result = DT_BINARY;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_VARBIN	)){
			result = DT_BINARY;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_DATE	)){
			result = DT_DATE;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_TIME	)){
			result = DT_TIME;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_TIMESTMP	)){
			result = DT_TIMESTAMP;
		} else if (dataType.equalsIgnoreCase(IFileInfoProvider.SQL_ROWID	)){
			result = DT_ALPHANUM;
		}
		return result;
	}
	private Map<String, String>attributes = new HashMap<String, String>();
	private String name;

	private boolean active;
	
	/**
	 * Add an attribute (datatype, size, color, what have you) to a symbol.<br> 
	 * <code>Note: Right now we only allow the attribute to be stored once in the map</code>
	 * @param category Predefined categories are the constants prefixed with CAT_
	 * @param value The string representation of the value for the category
	 */
	public void addAttribute(String category, String value) {
		if (attributes.containsKey(category)){
			// Do nothing
		} else {
			attributes.put(category, value);
		}
	}
	
	public int compare(Symbol o1, Symbol o2) {
		return o1.getName().compareTo(o2.getName());
	}
	
	/**
	 * Returns the attribute corresponding the the provided key
	 * @param key Usually one of the predefined constants in this class
	 * @return The string representation of the value
	 */
	public String getAnAttribute(String key) {
		return attributes.get(key);
	}
	
	/**
	 * Get a map of attributes for this symbol. Be aware that I am currently passing back
	 * the internal map, so if you make changes to the data if will affect the symbol
	 * @return A map of attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	/**
	 * @return The name of the symbol
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Does this symbol have an attribute for the provided category
	 * @param category
	 * @return
	 */
	public boolean hasAttribute(String category) {
		return attributes.containsKey(category);
	}
	
	/**
	 * Gives you the opportunity to mess things up by replacing the map that was created by this class
	 * with one of your own. Use at your own risk
	 * @param attributes
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	
	/**
	 * Set the name of the symbol
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		String newline = System.lineSeparator();
		sb.append("Name = " + name + newline);
		for (Map.Entry<String, String> e : attributes.entrySet()){
			sb.append("\t\tKey = " + e.getKey() + "\t Value = " + e.getValue() + newline);
		}
		return sb.toString();
	}
	
	
	/**
	 * Preprocessor symbols can come to be and go out of existence in a program. Right now
	 * I am soft deleting a symbol by setting it to be inactive.
	 * @param b
	 */
	public void setActive(boolean b) {
		this.active = b;
	}

}
