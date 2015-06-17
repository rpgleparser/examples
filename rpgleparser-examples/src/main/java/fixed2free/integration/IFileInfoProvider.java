package fixed2free.integration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.List;

import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.JobDescription;

public interface IFileInfoProvider {
	// DB2i SQL Datatypes
	public static final String SQL_BIGINT = "BIGINT";
	public static final String SQL_INTEGER = "INTEGER";
	public static final String SQL_SMALLINT = "SMALLINT";
	public static final String SQL_DECIMAL = "DECIMAL";
	public static final String SQL_NUMERIC = "NUMERIC";
	public static final String SQL_FLOAT = "FLOAT";
	public static final String SQL_DECFLOAT = "DECFLOAT";
	public static final String SQL_CHAR = "CHAR";
	public static final String SQL_VARCHAR = "VARCHAR";
	public static final String SQL_CLOB = "CLOB";
	public static final String SQL_GRAPHIC = "GRAPHIC";
	public static final String SQL_VARG = "VARG";
	public static final String SQL_DBCLOB = "DBCLOB";
	public static final String SQL_BINARY = "BINARY";
	public static final String SQL_VARBIN = "VARBIN";
	public static final String SQL_BLOB = "BLOB";
	public static final String SQL_DATE = "DATE";
	public static final String SQL_TIME = "TIME";
	public static final String SQL_TIMESTMP = "TIMESTMP";
	public static final String SQL_DATALINK = "DATALINK";
	public static final String SQL_ROWID = "ROWID";
	public static final String SQL_DOUBLE = "DOUBLE PRECISION";
	public static final String SQL_REAL = "REAL";
	
	public static final String AS400_ALPHA = "A";
	public static final String AS400_BINARY = "B";
	public static final String AS400_DIGITS_ONLY = "D";
	public static final String AS400_EITHER_DBCS_OR_ALPHANUM = "E";
	public static final String AS400_FLOATING_POINT = "F";
	public static final String AS400_GRAPHIC = "G";
	public static final String AS400_HEXADECIMAL = "H";
	public static final String AS400_INHIBIT_INPUT = "I";
	public static final String AS400_DBCS_ONLY = "J";
	public static final String AS400_DATE = "L";
	public static final String AS400_NUMERIC_ONLY = "M";
	public static final String AS400_NUMERIC_SHIFT = "N";
	public static final String AS400_OPEN_BOTH_DBCS_ALPHANUM = "O";
	public static final String AS400_PACKED_DECIMAL = "P";
	public static final String AS400_ZONED_DECIMAL = "S";
	public static final String AS400_TIME = "T";
	public static final String AS400_KATAJANA = "W";
	public static final String AS400_ALPHA_ONLY = "X";
	public static final String AS400_NUMERIC_ONLY_2 = "Y";
	public static final String AS400_TIMESTAMP = "Z";
	public static final String AS400_BLOB = "1";
	public static final String AS400_CLOB = "2";
	public static final String AS400_DBCLOB = "3";
	public static final String AS400_DATALINK = "4";
	public static final String AS400_BINARY_CHARACTER = "5";
	public static final String AS400_DECIMAL_FLOATING_POINT = "6";
	public static final String AS400_XML = "7";
	public FileObject getColumns();

	public void populateData(String fileName);

	public void populateData(String fileName, String recordFormatName);

	public void populateData(String fileName, String recordFormatName,
			String libraryName);

	public void setLibraryList(JobDescription jobd)
			throws PropertyVetoException, AS400SecurityException,
			ErrorCompletingRequestException, IOException, InterruptedException;

	public void setLibraryList(List<String> libl) throws PropertyVetoException,
			AS400SecurityException, ErrorCompletingRequestException,
			IOException, InterruptedException;
}
