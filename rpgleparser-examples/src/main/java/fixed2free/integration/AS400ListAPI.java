package fixed2free.integration;

import java.beans.PropertyVetoException;
import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Bin4;
import com.ibm.as400.access.AS400DataType;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.AS400Structure;
import com.ibm.as400.access.AS400Text;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.ProgramCall;
import com.ibm.as400.access.ProgramParameter;

public abstract class AS400ListAPI {
	// Format 0100 is for the list APIs that are called as programs (*PGMs).
	//
	// Offset Type Field
	// Dec Hex
	// 0 0 CHAR(64) User area
	protected AS400Text userArea = new AS400Text(64);
	// 64 40 BINARY(4) Size of generic header
	protected AS400Bin4 sizeOfGenericHeader = new AS400Bin4();
	// 68 44 CHAR(4) Structure's release and level
	protected AS400Text structureReleaseAndLevel = new AS400Text(4);
	// 72 48 CHAR(8) Format name
	protected AS400Text formatName = new AS400Text(8);
	// 80 50 CHAR(10) API used
	protected AS400Text apiUsed = new AS400Text(10);
	// 90 5A CHAR(13) Date and time created
	protected AS400Text dateAndTimeCreated = new AS400Text(13);
	// 103 67 CHAR(1) Information status
	protected AS400Text informationStatus = new AS400Text(1);
	// 104 68 BINARY(4) Size of user space used
	protected AS400Bin4 sizeOfUserSpaceUsed = new AS400Bin4();
	// 108 6C BINARY(4) Offset to input parameter section
	protected AS400Bin4 offsetToInputParameterSection = new AS400Bin4();
	// 112 70 BINARY(4) Size of input parameter section
	protected AS400Bin4 sizeOfInputParameterSection = new AS400Bin4();
	// 116 74 BINARY(4) Offset to header section
	protected AS400Bin4 offsetToHeaderSection = new AS400Bin4();
	// 120 78 BINARY(4) Size of header section
	protected AS400Bin4 sizeOfHeaderSection = new AS400Bin4();
	// 124 7C BINARY(4) Offset to list data section
	protected AS400Bin4 offsetToListDataSection = new AS400Bin4();
	// 128 80 BINARY(4) Size of list data section
	protected AS400Bin4 sizeOfListDataSection = new AS400Bin4();
	// 132 84 BINARY(4) Number of list entries
	protected AS400Bin4 numberOfListEntries = new AS400Bin4();
	// 136 88 BINARY(4) Size of each entry
	protected AS400Bin4 sizeOfEachEntry = new AS400Bin4();
	// 140 8C BINARY(4) CCSID of data in the list entries
	protected AS400Bin4 CCSIDofDataInTheListEntries = new AS400Bin4();
	// 144 90 CHAR(2) Country or region ID
	protected AS400Text countryOrRegionID = new AS400Text(2);
	// 146 92 CHAR(3) Language ID
	protected AS400Text languageID = new AS400Text(3);
	// 149 95 CHAR(1) Subsetted list indicator
	protected AS400Text subsettedListIndicator = new AS400Text(1);
	// 150 96 CHAR(42) Reserved
	protected AS400Text reservedLFMT0100 = new AS400Text(42);

	protected AS400DataType[] LFMT0100a = new AS400DataType[] { userArea,
			sizeOfGenericHeader, structureReleaseAndLevel, formatName, apiUsed,
			dateAndTimeCreated, informationStatus, sizeOfUserSpaceUsed,
			offsetToInputParameterSection, sizeOfInputParameterSection,
			offsetToHeaderSection, sizeOfHeaderSection,
			offsetToListDataSection, sizeOfListDataSection,
			numberOfListEntries, sizeOfEachEntry, CCSIDofDataInTheListEntries,
			countryOrRegionID, languageID, subsettedListIndicator,
			reservedLFMT0100 };

	protected AS400Structure LFMT0100 = new AS400Structure(LFMT0100a);

	// Generic header format 0300
	// Format 0300 is for the list APIs that are called as procedures exported
	// from ILE service programs (*SRVPGM).
	//
	// Offset Type Field
	// Dec Hex
	// 0 0 Everything from the 0100 format
	// 192 C0 CHAR(256) API entry point name
	protected AS400Text apiEntryPointName = new AS400Text(256);
	// 448 1C0 CHAR(128) Reserved
	protected AS400Text reservedLFMT0300 = new AS400Text(128);

	protected AS400DataType[] LFMT0300a = new AS400DataType[] { userArea,
			sizeOfGenericHeader, structureReleaseAndLevel, formatName, apiUsed,
			dateAndTimeCreated, informationStatus, sizeOfUserSpaceUsed,
			offsetToInputParameterSection, sizeOfInputParameterSection,
			offsetToHeaderSection, sizeOfHeaderSection,
			offsetToListDataSection, sizeOfListDataSection,
			numberOfListEntries, sizeOfEachEntry, CCSIDofDataInTheListEntries,
			countryOrRegionID, languageID, subsettedListIndicator,
			reservedLFMT0100, apiEntryPointName, reservedLFMT0300 };
	protected AS400Structure LFMT0300 = new AS400Structure(LFMT0300a);

	// Format ERRC0100
	// Table 1. Format ERRC0100 for the error code parameter
	// Offset Use Type Field
	// Dec Hex
	// 0 0 INPUT BINARY(4) Bytes provided
	protected AS400Bin4 bytesProvided = new AS400Bin4();
	// 4 4 OUTPUT BINARY(4) Bytes available
	protected AS400Bin4 bytesAvailable = new AS400Bin4();
	// 8 8 OUTPUT CHAR(7) Exception ID
	protected AS400Text exceptionID = new AS400Text(7);
	// 15 F OUTPUT CHAR(1) Reserved
	protected AS400Text ERRC0100Reserved = new AS400Text(1);
	// 16 10 OUTPUT CHAR(*) Exception data

	protected AS400DataType[] ERRC0100a = new AS400DataType[] { bytesProvided,
			bytesAvailable, exceptionID, ERRC0100Reserved };
	protected AS400Structure ERRC0100 = new AS400Structure(ERRC0100a);

	protected Object[] ERRC0100j = new Object[] { new Integer(16),
			new Integer(0), " ", "" };

	// Format ERRC0200
	// Table 2. Format ERRC0200 for the error code parameter
	// Offset Use Type Field
	// Dec Hex
	// 0 0 INPUT BINARY(4) Key
	protected AS400Bin4 key = new AS400Bin4();
	// 4 4 INPUT BINARY(4) Bytes provided
	protected AS400Bin4 bytesProvided0200 = new AS400Bin4();
	// 8 8 OUTPUT BINARY(4) Bytes available
	protected AS400Bin4 bytesAvailable0200 = new AS400Bin4();
	// 12 C OUTPUT CHAR(7) Exception ID
	protected AS400Text exceptionID0200 = new AS400Text(7);
	// 19 13 OUTPUT CHAR(1) Reserved
	protected AS400Text reserved0200 = new AS400Text(1);
	// 20 14 OUTPUT BINARY(4) CCSID of the CCHAR data
	protected AS400Bin4 CCSIDoftheCharData = new AS400Bin4();
	// 24 18 OUTPUT BINARY(4) Offset to the exception data
	protected AS400Bin4 offsetToTheExceptionData = new AS400Bin4();
	// 28 1C OUTPUT BINARY(4) Length of the exception data
	protected AS400Bin4 lengthOfExceptionData = new AS400Bin4();
	// OUTPUT CHAR(*) Exception data

	protected AS400DataType[] ERRC0200a = new AS400DataType[] { key,
			bytesProvided0200, bytesAvailable0200, exceptionID0200,
			reserved0200, CCSIDoftheCharData, offsetToTheExceptionData,
			lengthOfExceptionData };

	protected AS400Structure ERRC0200 = new AS400Structure(ERRC0200a);

	protected AS400 theSystem;
	protected ProgramCall pc = new ProgramCall(theSystem);

	public void readUserSpace(String library, String name, AS400DataType[] conversionObject){
		Object result= null;
		
		return;
	}
	public void deleteUserSpace(String library, String name)
			throws PropertyVetoException, AS400SecurityException,
			ErrorCompletingRequestException, IOException, InterruptedException,
			ObjectDoesNotExistException {
		// Initialize the name of the program to run.
		String programName = "/QSYS.LIB/QSYS.LIB/QUSDLTUS.PGM";
		ProgramParameter[] parameterList = new ProgramParameter[2];
		AS400Text spaceName = new AS400Text(20);

		String uspName = StringUtils.rightPad(name, 10)
				+ StringUtils.rightPad(library, 10);

		parameterList[0] = new ProgramParameter(spaceName.toBytes(uspName));
		parameterList[1] = new ProgramParameter(ERRC0100.toBytes(ERRC0100j));

		pc.setProgram(programName, parameterList);
		// Run the program.
		if (pc.run() != true) {
			// Report failure.
			System.out.println("Program failed!");
			// Show the messages.
			AS400Message[] messagelist = pc.getMessageList();
			for (int i = 0; i < messagelist.length; ++i) {
				// Show each message.
				System.out.println(messagelist[i]);
			}
		}

	}

	public void createUserSpace(String library, String name, int size,
			String description) throws PropertyVetoException,
			AS400SecurityException, ErrorCompletingRequestException,
			IOException, InterruptedException, ObjectDoesNotExistException {

		// Initialize the name of the program to run.
		String programName = "/QSYS.LIB/QSYS.LIB/QUSCRTUS.PGM";
		String uspName = StringUtils.rightPad(name, 10)
				+ StringUtils.rightPad(library, 10);

		ProgramParameter[] parameterList = new ProgramParameter[9];
		AS400Text spaceName = new AS400Text(20);
		AS400Text extendedAttribute = new AS400Text(10);
		AS400Bin4 initialSize = new AS400Bin4();
		AS400Text initialValue = new AS400Text(1);
		AS400Text publicAuth = new AS400Text(10);
		AS400Text theDescription = new AS400Text(50);
		AS400Text replace = new AS400Text(10);
		AS400Text domain = new AS400Text(10);

		parameterList[0] = new ProgramParameter(spaceName.toBytes(uspName));
		parameterList[1] = new ProgramParameter(
				extendedAttribute.toBytes("CONTROL"));
		parameterList[2] = new ProgramParameter(initialSize.toBytes(size));
		parameterList[3] = new ProgramParameter(initialValue.toBytes(0x00));
		parameterList[4] = new ProgramParameter(publicAuth.toBytes("*ALL"));
		parameterList[5] = new ProgramParameter(
				theDescription.toBytes(description.trim()));
		parameterList[6] = new ProgramParameter(replace.toBytes("*YES"));
		parameterList[7] = new ProgramParameter(ERRC0100.toBytes(ERRC0100j));
		parameterList[8] = new ProgramParameter(domain.toBytes("*USER"));
		// byte[] quantity = new byte[2];
		// quantity[0] = 1; quantity[1] = 44;
		// parameterList[2] = new ProgramParameter(quantity, 30);
		// Set the program name and parameter list.
		pc.setProgram(programName, parameterList);
		// Run the program.
		if (pc.run() != true) {
			// Report failure.
			System.out.println("Program failed!");
			// Show the messages.
			AS400Message[] messagelist = pc.getMessageList();
			for (int i = 0; i < messagelist.length; ++i) {
				// Show each message.
				System.out.println(messagelist[i]);
			}
		}

	}

}
