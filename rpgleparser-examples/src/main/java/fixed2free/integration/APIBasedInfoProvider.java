package fixed2free.integration;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

public class APIBasedInfoProvider implements IFileInfoProvider, ListApiCallback {
	private AS400 theAS400;
	private LibraryList liblist = new LibraryList();
	private QUSLOBJ objList;
	private QUSLRCD recList;
	private QUSLFLD fldList;
	private ProgramCall jobRelated;
	private String system;
	private String userID;
	private String password;
	private static final String STATE_OBJECT_RESOLUTION = "Object resolution";
	private static final String STATE_RECORD_RESOLUTION = "Record resolution";
	private static final String STATE_FIELD_RESOLUTION = "Field resolution";
	private FileObject theFile;

	private RecordFormat theRec;
	
	private String currentState = STATE_OBJECT_RESOLUTION;

	private String fileName;

	private String libraryName;

	private String recordFormatName;
	private int fieldNumber;
	
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

	public APIBasedInfoProvider() {
		theAS400 = new AS400();
		jobRelated = new ProgramCall(theAS400);
		liblist.setAS400(theAS400);
		objList = new QUSLOBJ(theAS400);
		fldList = new QUSLFLD(theAS400);
		recList = new QUSLRCD(theAS400);

	}

	public APIBasedInfoProvider(AS400 as400) {
		theAS400 = as400;
		jobRelated = new ProgramCall(theAS400);
		liblist.setAS400(as400);
		objList = new QUSLOBJ(as400);
		fldList = new QUSLFLD(as400);
		recList = new QUSLRCD(as400);
	}

	public void connect() throws AS400SecurityException, IOException {
		if (!theAS400.isConnected()) {
			theAS400.connectService(AS400.COMMAND);
		}
	}

	public List<ColumnInfo> getColumns(String fileName, String recordFormatName) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ColumnInfo> getColumns(String fileName,
			String recordFormatName, String libraryName) {
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
			fieldNumber = 1;
			fldList.dowork();
		}
		fieldNumber = 0;

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

	public boolean processEntry(byte[] listEntry) {
		if (currentState == STATE_OBJECT_RESOLUTION) {
			OBJL0200 myObj = new OBJL0200(listEntry);
			theFile = new FileObject();
			theFile.setFileName(myObj.getObjectNameUsed());
			theFile.setLibraryName(myObj.getObjectLibraryNameUsed());
			theFile.setFileDescription(myObj.getTextDescription());
			// We want to stop at the first file found in the library list
			return false;
		} else if (currentState == STATE_RECORD_RESOLUTION) {
			RCDL0200 myObj = new RCDL0200(listEntry);
			RecordFormat aRecordFormat = new RecordFormat();
			aRecordFormat.setName(myObj.getRecordFormatName());
			aRecordFormat.setRecordFormatID(myObj.getRecordFormatID());
			aRecordFormat.setDescription(myObj.getRecordTextDescription());
			theFile.addRecordFormat(aRecordFormat);
			return true;
		} else if (currentState == STATE_FIELD_RESOLUTION) {
			FLDL0100 myObj = new FLDL0100(listEntry);
			ColumnInfo ci = new ColumnInfo();
			ci.setCharacterOctetLength(null);
			if (myObj.getUcs2DisplayedFieldLength().intValue() != 0) {
				ci.setCharacterMaximumLength(myObj
						.getUcs2DisplayedFieldLength());
			} else if (myObj.getNumberOfDBCSCharacters() != 0) {
				ci.setCharacterMaximumLength(myObj.getNumberOfDBCSCharacters());
			} else {
				ci.setCharacterMaximumLength(myObj.getFieldLengthInBytes());
			}
			ci.setCCSID(myObj.getFieldDataCCSID());
			ci.setColumnDefault(null);
			ci.setColumnExpression(null);
			ci.setColumnHeading(
					StringUtils.rightPad(myObj.getColumnHeading1(),20)
					+ StringUtils.rightPad(myObj.getColumnHeading2(), 20)
					+ StringUtils.rightPad(myObj.getColumnHeading3(), 20)
					);
	
			if (myObj.getAlternativeFieldName() != null && myObj.getAlternativeFieldName().trim().length() > 0){
				ci.setColumnName(myObj.getAlternativeFieldName());
			} else {
				ci.setColumnName(myObj.getFieldName());
			}
			ci.setColumnText(myObj.getFieldTextDescription());
			ci.setDataType(myObj.getDataType());
			//ci.setDateTimePrecision(null);
			//ci.setHasDefault('0');
			//ci.setHasFieldProc('0');
			//ci.setHidden('0');
			ci.setIdentityCache(myObj.getIdentityColumnCache());
			ci.setIdentityCycle(myObj.getIdentityColumnCycle());
			ci.setIdentityIncrement(new BigDecimal(myObj.getIdentityColumnIncrementBy().intValue()));
			ci.setIdentityMaximum(myObj.getIdentityColumnMaxValue());
			ci.setIdentityMinimum(myObj.getIdentityColumnMinValue());
			ci.setIdentityOrder(myObj.getIdentityColumnOrder());
			ci.setIdentityStart(myObj.getIdentityColumnCurrentStartWith());
			//ci.setIsIdentity("0");
			ci.setIsNullable(myObj.getNullValuesAllowed().charAt(0));
			//ci.setIsUpdateable(null);
			ci.setLength(myObj.getFieldLengthInBytes());
			//ci.setLongComment(null);
			ci.setNumericPrecision(myObj.getDecimalPositions());
			ci.setNumericPrecisionRadix(new Integer(10));
			ci.setNumericScale(myObj.getDigits());
			ci.setOrdinalPosition(fieldNumber++);
			ci.setStorage(myObj.getFieldLengthInBytes());
			ci.setSystemColumnName(myObj.getInternalFieldName());
			ci.setSystemSchemaName(theFile.getLibraryName());
			ci.setSystemTableName(theFile.getFileName());
			ci.setTableName(theFile.getFileName());
			//ci.setTableOwner(null);
			ci.setTableSchema(theFile.getLibraryName());
			ci.setUserDefinedTypeName(myObj.getUserDefinedTypeName());
			ci.setUserDefinedTypeSchema(myObj.getUserDefinedTypeLibraryName());
			theRec.getFields().add(ci);
		}
		return true;
	}

}
