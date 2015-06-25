package fixed2free.integration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.List;

import org.rpgleparser.api.LibraryList;
import org.rpgleparser.api.ListApiCallback;
import org.rpgleparser.api.LFLD.FLDL0100;
import org.rpgleparser.api.LFLD.QUSLFLD;
import org.rpgleparser.api.LRCD.QUSLRCD;
import org.rpgleparser.api.LRCD.RCDL0200;
import org.rpgleparser.api.OBJL.OBJL0200;
import org.rpgleparser.api.OBJL.QUSLOBJ;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.JobDescription;
import com.ibm.as400.access.ProgramCall;

/**
 * Uses the AS400 APIS (QUSLOBJ, QUSLRCD, QUSLFLD) to return information about a given file
 * be it a display file, printer file, or database file
 * @author Eric N. Wilson
 *
 */
public class APIBasedInfoProvider implements IFileInfoProvider, ListApiCallback {
	private static final String STATE_OBJECT_RESOLUTION = "Object resolution";
	private static final String STATE_RECORD_RESOLUTION = "Record resolution";
	private static final String STATE_FIELD_RESOLUTION = "Field resolution";
	
	public static void main(String[] args){
		APIBasedInfoProvider myObj = new APIBasedInfoProvider();
		myObj.setSystem("DEV400");
		myObj.setUserID("EWILSON");
		myObj.setFileName("INWCTLP");
		myObj.setLibraryName("*LIBL");
		myObj.resolveObject();
		try {
			myObj.liblist.setManagedJob(myObj.jobRelated.getServerJob());
		} catch (AS400SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorCompletingRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private AS400 theAS400;
	private LibraryList liblist = new LibraryList();
	private QUSLOBJ objList;
	private QUSLRCD recList;
	private QUSLFLD fldList;
	private ProgramCall jobRelated;
	private String system;
	private String userID;
	private String password;

	private FileObject theFile;
	
	private RecordFormat theRec;

	private String currentState = STATE_OBJECT_RESOLUTION;

	private String fileName;

	private String libraryName;
	private String recordFormatName;
	
	private int fieldNumber;

	/**
	 * Default constructor, creates an AS400 object and associates it with the objects needed
	 * for this class to function
	 */
	public APIBasedInfoProvider() {
		theAS400 = new AS400();
		jobRelated = new ProgramCall(theAS400);
		liblist.setAS400(theAS400);
		objList = new QUSLOBJ(theAS400);
		fldList = new QUSLFLD(theAS400);
		recList = new QUSLRCD(theAS400);

	}

	/**
	 * Constructor taking an AS400 instance as input. All classes will use the provided AS400 object
	 * @param as400
	 */
	public APIBasedInfoProvider(AS400 as400) {
		theAS400 = as400;
		jobRelated = new ProgramCall(theAS400);
		liblist.setAS400(as400);
		objList = new QUSLOBJ(as400);
		fldList = new QUSLFLD(as400);
		recList = new QUSLRCD(as400);
	}

	/**
	 * Try to connect to the AS400 command service
	 * @throws AS400SecurityException
	 * @throws IOException
	 */
	public void connect() throws AS400SecurityException, IOException {
		if (!theAS400.isConnected()) {
			theAS400.connectService(AS400.COMMAND);
		}
	}

	/**
	 * Creates a ColumnInfo object based on the data contained in the FLDL0100 object
	 * @param listEntry
	 */
	private void doFields(byte[] listEntry) {
		FLDL0100 myObj = new FLDL0100(listEntry);
		ColumnInfo ci = new ColumnInfo(myObj);
		theRec.getFields().add(ci);
	}

	/**
	 * Creates a File Object based on the data contained in the listEntry
	 * @param listEntry
	 */
	private void doObject(byte[] listEntry) {
		OBJL0200 myObj = new OBJL0200(listEntry);
		theFile = new FileObject();
		theFile.setFileName(myObj.getObjectNameUsed());
		theFile.setLibraryName(myObj.getObjectLibraryNameUsed());
		theFile.setFileDescription(myObj.getTextDescription());
	}

	/**
	 * Creates a record format based on the data contained in the list entry
	 * @param listEntry
	 */
	private void doRecord(byte[] listEntry) {
		RCDL0200 myObj = new RCDL0200(listEntry);
		RecordFormat aRecordFormat = new RecordFormat();
		aRecordFormat.setName(myObj.getRecordFormatName());
		aRecordFormat.setRecordFormatID(myObj.getRecordFormatID());
		aRecordFormat.setDescription(myObj.getRecordTextDescription());
		theFile.addRecordFormat(aRecordFormat);
	}

	public FileObject getColumns() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getFileName() {
		return fileName;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public String getPassword() {
		return password;
	}

	public String getRecordFormatName() {
		return recordFormatName;
	}

	public String getSystem() {
		return system;
	}

	public String getUserID() {
		return userID;
	}

	public void populateData(String tableName) {

	}

	public void populateData(String fileName, String recordFormatName) {
		// TODO Auto-generated method stub

	}

	public void populateData(String fileName, String recordFormatName,
			String libraryName) {
		// TODO Auto-generated method stub

	}

	public boolean processEntry(byte[] listEntry) {
		if (currentState == STATE_OBJECT_RESOLUTION) {
			doObject(listEntry);
			// We want to stop at the first file found in the library list
			return false;
		} else if (currentState == STATE_RECORD_RESOLUTION) {
			doRecord(listEntry);
		} else if (currentState == STATE_FIELD_RESOLUTION) {
			doFields(listEntry);
		}
		return true;
	}

	public void resolveObject() {
		currentState = STATE_OBJECT_RESOLUTION;

		objList.setDesiredFormat(QUSLOBJ.OBJ0200_FORMAT);
		objList.setSearchObjectName(fileName);
		if (libraryName == null) {
			libraryName = "*LIBL";
		}
		objList.setSearchObjectLibrary(libraryName);
		objList.setSearchObjectType("*FILE");
		objList.setUserSpaceName("OBJLSTSPC");
		objList.setUserSpaceLibrary("QTEMP");
		objList.setUserSpaceSize(1024);
		objList.setUserSpaceDescription("Object List API user space");
		objList.getTheListHandler().registerCallback(this);
		objList.dowork();
		
		currentState = STATE_RECORD_RESOLUTION;
		recList.setRequestOverrideProcessing(false);
		recList.setRequestedFormat(QUSLRCD.RCDL0200_FORMAT);
		recList.setSearchFileName(theFile.getFileName());
		recList.setSearchLibrayName(theFile.getLibraryName());
		recList.setUserSpaceDescription("Record List API user space");
		recList.setUserSpaceLibrary("QTEMP");
		recList.setUserSpaceName("RECLSTSPC");
		recList.setUserSpaceSize(1024);
		recList.getTheListHandler().registerCallback(this);
		recList.dowork();
		
		currentState = STATE_FIELD_RESOLUTION;
		for (RecordFormat r : theFile.getRecordFormats().values()){
			theRec = r;
			fldList.setDesiredFormat(QUSLFLD.FLDL0100_FORMAT);
			fldList.setFileLib(theFile.getLibraryName());
			fldList.setFileName(theFile.getFileName());
			fldList.setPerformOverrides(false);
			fldList.setRecordFormat(r.getName());
			fldList.setUserSpaceDescription("Field List API User Space");
			fldList.setUserSpaceLib("QTEMP");
			fldList.setUserSpaceName("FLDLSTSPC");
			fldList.setUserSpaceInitialSize(1024);
			fldList.getTheListHandler().registerCallback(this);
			fieldNumber += 1;
			fldList.dowork();
		}
		fieldNumber = 0;
		currentState = STATE_OBJECT_RESOLUTION;

	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setLibraryList(JobDescription jobd)
			throws PropertyVetoException, AS400SecurityException,
			ErrorCompletingRequestException, IOException, InterruptedException {
		liblist.setLibraryList(jobd);
		liblist.commit();
	}

	public void setLibraryList(List<String> libl) throws PropertyVetoException,
			AS400SecurityException, ErrorCompletingRequestException,
			IOException, InterruptedException {
		liblist.setLibraryList(libl);
		liblist.commit();

	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public void setPassword(String password) {
		this.password = password;
		if (! theAS400.isConnected()){
			theAS400.setPassword(password);
		}
	}

	public void setRecordFormatName(String recordFormatName) {
		this.recordFormatName = recordFormatName;
	}

	public void setSystem(String system) {
		this.system = system;
		if (! theAS400.isConnected()){
			try {
				theAS400.setSystemName(system);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

	public void setUserID(String userID) {
		this.userID = userID;
		if (! theAS400.isConnected()){
			try {
				theAS400.setUserId(userID);
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}
		}
	}

}
