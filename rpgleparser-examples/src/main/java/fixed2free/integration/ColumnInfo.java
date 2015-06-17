package fixed2free.integration;

import java.math.BigDecimal;

import org.rpgleparser.api.LFLD.FLDL0100;

/**
 * This class serves to "decouple" the internal representation of the field meta
 * data from the FLDL0100 format to a separate class without the AS400 classes
 * in it. Right now the field data is the same as the java representation of the
 * FLDL0100 format (but it does not have to remain so)
 * 
 * @author Eric N. Wilson
 *
 */
public class ColumnInfo {
	private String fieldName;
	private String dataType;
	private String use;
	private Integer outputBufferPosition;
	private Integer inputBufferPosition;
	private Integer fieldLengthInBytes;
	private Integer digits;
	private Integer decimalPositions;
	private String fieldTextDescription;
	private String editCode;
	private Integer editWordLength;
	private String editWord;
	private String columnHeading1;
	private String columnHeading2;
	private String columnHeading3;
	private String internalFieldName;
	private String alternativeFieldName;
	private Integer lengthOfAlternativeFieldName;
	private Integer numberOfDBCSCharacters;
	private String nullValuesAllowed;
	private String hostVariableIndicator;
	private String dateAndTimeFormat;
	private String dateAndTimeSeparator;
	private String variableLengthFieldIndicator;
	private Integer fieldTextDescriptionCCSID;
	private Integer fieldDataCCSID;
	private Integer fieldColumnHeadingsCCSID;
	private Integer fieldEditWordsCCSID;
	private Integer ucs2DisplayedFieldLength;
	private Integer fieldDataEncodingScheme;
	private Integer maximumLargeObjectFieldLength;
	private Integer padLengthForLargeObject;
	private Integer lengthOfUserDefinedTypeName;
	private String userDefinedTypeName;
	private String userDefinedTypeLibraryName;
	private String datalinkLinkControl;
	private String datalinkIntegrity;
	private String datalinkReadPermission;
	private String datalinkWritePermission;
	private String datalinkRecovery;
	private String datalinkUnlinkControl;
	private Integer displayOrPrintRowNumber;
	private Integer displayOrPrintColumnNumber;
	private String rowidColumn;
	private String identityColumn;
	private String generatedBy;
	private String identityColumnCycle;
	private BigDecimal identityColumnOriginalStartWith;
	private BigDecimal identityColumnCurrentStartWith;
	private Integer identityColumnIncrementBy;
	private BigDecimal identityColumnMinValue;
	private BigDecimal identityColumnMaxValue;
	private Integer identityColumnCache;
	private String identityColumnOrder;
	private String implicitlyHidden;
	private String rowChangeTimestampGenerated;
	private String fieldProcedureProgramName;
	private String fieldProcedureLibraryName;

	public ColumnInfo(String fieldName, String dataType, String use,
			Integer outputBufferPosition, Integer inputBufferPosition,
			Integer fieldLengthInBytes, Integer digits,
			Integer decimalPositions, String fieldTextDescription,
			String editCode, Integer editWordLength, String editWord,
			String columnHeading1, String columnHeading2,
			String columnHeading3, String internalFieldName,
			String alternativeFieldName, Integer lengthOfAlternativeFieldName,
			Integer numberOfDBCSCharacters, String nullValuesAllowed,
			String hostVariableIndicator, String dateAndTimeFormat,
			String dateAndTimeSeparator, String variableLengthFieldIndicator,
			Integer fieldTextDescriptionCCSID, Integer fieldDataCCSID,
			Integer fieldColumnHeadingsCCSID, Integer fieldEditWordsCCSID,
			Integer ucs2DisplayedFieldLength, Integer fieldDataEncodingScheme,
			Integer maximumLargeObjectFieldLength,
			Integer padLengthForLargeObject,
			Integer lengthOfUserDefinedTypeName, String userDefinedTypeName,
			String userDefinedTypeLibraryName, String datalinkLinkControl,
			String datalinkIntegrity, String datalinkReadPermission,
			String datalinkWritePermission, String datalinkRecovery,
			String datalinkUnlinkControl, Integer displayOrPrintRowNumber,
			Integer displayOrPrintColumnNumber, String rowidColumn,
			String identityColumn, String generatedBy,
			String identityColumnCycle,
			BigDecimal identityColumnOriginalStartWith,
			BigDecimal identityColumnCurrentStartWith,
			Integer identityColumnIncrementBy,
			BigDecimal identityColumnMinValue,
			BigDecimal identityColumnMaxValue, Integer identityColumnCache,
			String identityColumnOrder, String implicitlyHidden,
			String rowChangeTimestampGenerated,
			String fieldProcedureProgramName, String fieldProcedureLibraryName) {
		this.fieldName = fieldName;
		this.dataType = dataType;
		this.use = use;
		this.outputBufferPosition = outputBufferPosition;
		this.inputBufferPosition = inputBufferPosition;
		this.fieldLengthInBytes = fieldLengthInBytes;
		this.digits = digits;
		this.decimalPositions = decimalPositions;
		this.fieldTextDescription = fieldTextDescription;
		this.editCode = editCode;
		this.editWordLength = editWordLength;
		this.editWord = editWord;
		this.columnHeading1 = columnHeading1;
		this.columnHeading2 = columnHeading2;
		this.columnHeading3 = columnHeading3;
		this.internalFieldName = internalFieldName;
		this.alternativeFieldName = alternativeFieldName;
		this.lengthOfAlternativeFieldName = lengthOfAlternativeFieldName;
		this.numberOfDBCSCharacters = numberOfDBCSCharacters;
		this.nullValuesAllowed = nullValuesAllowed;
		this.hostVariableIndicator = hostVariableIndicator;
		this.dateAndTimeFormat = dateAndTimeFormat;
		this.dateAndTimeSeparator = dateAndTimeSeparator;
		this.variableLengthFieldIndicator = variableLengthFieldIndicator;
		this.fieldTextDescriptionCCSID = fieldTextDescriptionCCSID;
		this.fieldDataCCSID = fieldDataCCSID;
		this.fieldColumnHeadingsCCSID = fieldColumnHeadingsCCSID;
		this.fieldEditWordsCCSID = fieldEditWordsCCSID;
		this.ucs2DisplayedFieldLength = ucs2DisplayedFieldLength;
		this.fieldDataEncodingScheme = fieldDataEncodingScheme;
		this.maximumLargeObjectFieldLength = maximumLargeObjectFieldLength;
		this.padLengthForLargeObject = padLengthForLargeObject;
		this.lengthOfUserDefinedTypeName = lengthOfUserDefinedTypeName;
		this.userDefinedTypeName = userDefinedTypeName;
		this.userDefinedTypeLibraryName = userDefinedTypeLibraryName;
		this.datalinkLinkControl = datalinkLinkControl;
		this.datalinkIntegrity = datalinkIntegrity;
		this.datalinkReadPermission = datalinkReadPermission;
		this.datalinkWritePermission = datalinkWritePermission;
		this.datalinkRecovery = datalinkRecovery;
		this.datalinkUnlinkControl = datalinkUnlinkControl;
		this.displayOrPrintRowNumber = displayOrPrintRowNumber;
		this.displayOrPrintColumnNumber = displayOrPrintColumnNumber;
		this.rowidColumn = rowidColumn;
		this.identityColumn = identityColumn;
		this.generatedBy = generatedBy;
		this.identityColumnCycle = identityColumnCycle;
		this.identityColumnOriginalStartWith = identityColumnOriginalStartWith;
		this.identityColumnCurrentStartWith = this.identityColumnCurrentStartWith;
		this.identityColumnIncrementBy = identityColumnIncrementBy;
		this.identityColumnMinValue = identityColumnMinValue;
		this.identityColumnMaxValue = identityColumnMaxValue;
		this.identityColumnCache = identityColumnCache;
		this.identityColumnOrder = identityColumnOrder;
		this.implicitlyHidden = implicitlyHidden;
		this.rowChangeTimestampGenerated = rowChangeTimestampGenerated;
		this.fieldProcedureProgramName = fieldProcedureProgramName;
		this.fieldProcedureLibraryName = fieldProcedureLibraryName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getUse() {
		return use;
	}

	public void setUse(String use) {
		this.use = use;
	}

	public Integer getOutputBufferPosition() {
		return outputBufferPosition;
	}

	public void setOutputBufferPosition(Integer outputBufferPosition) {
		this.outputBufferPosition = outputBufferPosition;
	}

	public Integer getInputBufferPosition() {
		return inputBufferPosition;
	}

	public void setInputBufferPosition(Integer inputBufferPosition) {
		this.inputBufferPosition = inputBufferPosition;
	}

	public Integer getFieldLengthInBytes() {
		return fieldLengthInBytes;
	}

	public void setFieldLengthInBytes(Integer fieldLengthInBytes) {
		this.fieldLengthInBytes = fieldLengthInBytes;
	}

	public Integer getDigits() {
		return digits;
	}

	public void setDigits(Integer digits) {
		this.digits = digits;
	}

	public Integer getDecimalPositions() {
		return decimalPositions;
	}

	public void setDecimalPositions(Integer decimalPositions) {
		this.decimalPositions = decimalPositions;
	}

	public String getFieldTextDescription() {
		return fieldTextDescription;
	}

	public void setFieldTextDescription(String fieldTextDescription) {
		this.fieldTextDescription = fieldTextDescription;
	}

	public String getEditCode() {
		return editCode;
	}

	public void setEditCode(String editCode) {
		this.editCode = editCode;
	}

	public Integer getEditWordLength() {
		return editWordLength;
	}

	public void setEditWordLength(Integer editWordLength) {
		this.editWordLength = editWordLength;
	}

	public String getEditWord() {
		return editWord;
	}

	public void setEditWord(String editWord) {
		this.editWord = editWord;
	}

	public String getColumnHeading1() {
		return columnHeading1;
	}

	public void setColumnHeading1(String columnHeading1) {
		this.columnHeading1 = columnHeading1;
	}

	public String getColumnHeading2() {
		return columnHeading2;
	}

	public void setColumnHeading2(String columnHeading2) {
		this.columnHeading2 = columnHeading2;
	}

	public String getColumnHeading3() {
		return columnHeading3;
	}

	public void setColumnHeading3(String columnHeading3) {
		this.columnHeading3 = columnHeading3;
	}

	public String getInternalFieldName() {
		return internalFieldName;
	}

	public void setInternalFieldName(String internalFieldName) {
		this.internalFieldName = internalFieldName;
	}

	public String getAlternativeFieldName() {
		return alternativeFieldName;
	}

	public void setAlternativeFieldName(String alternativeFieldName) {
		this.alternativeFieldName = alternativeFieldName;
	}

	public Integer getLengthOfAlternativeFieldName() {
		return lengthOfAlternativeFieldName;
	}

	public void setLengthOfAlternativeFieldName(
			Integer lengthOfAlternativeFieldName) {
		this.lengthOfAlternativeFieldName = lengthOfAlternativeFieldName;
	}

	public Integer getNumberOfDBCSCharacters() {
		return numberOfDBCSCharacters;
	}

	public void setNumberOfDBCSCharacters(Integer numberOfDBCSCharacters) {
		this.numberOfDBCSCharacters = numberOfDBCSCharacters;
	}

	public String getNullValuesAllowed() {
		return nullValuesAllowed;
	}

	public void setNullValuesAllowed(String nullValuesAllowed) {
		this.nullValuesAllowed = nullValuesAllowed;
	}

	public String getHostVariableIndicator() {
		return hostVariableIndicator;
	}

	public void setHostVariableIndicator(String hostVariableIndicator) {
		this.hostVariableIndicator = hostVariableIndicator;
	}

	public String getDateAndTimeFormat() {
		return dateAndTimeFormat;
	}

	public void setDateAndTimeFormat(String dateAndTimeFormat) {
		this.dateAndTimeFormat = dateAndTimeFormat;
	}

	public String getDateAndTimeSeparator() {
		return dateAndTimeSeparator;
	}

	public void setDateAndTimeSeparator(String dateAndTimeSeparator) {
		this.dateAndTimeSeparator = dateAndTimeSeparator;
	}

	public String getVariableLengthFieldIndicator() {
		return variableLengthFieldIndicator;
	}

	public void setVariableLengthFieldIndicator(
			String variableLengthFieldIndicator) {
		this.variableLengthFieldIndicator = variableLengthFieldIndicator;
	}

	public Integer getFieldTextDescriptionCCSID() {
		return fieldTextDescriptionCCSID;
	}

	public void setFieldTextDescriptionCCSID(Integer fieldTextDescriptionCCSID) {
		this.fieldTextDescriptionCCSID = fieldTextDescriptionCCSID;
	}

	public Integer getFieldDataCCSID() {
		return fieldDataCCSID;
	}

	public void setFieldDataCCSID(Integer fieldDataCCSID) {
		this.fieldDataCCSID = fieldDataCCSID;
	}

	public Integer getFieldColumnHeadingsCCSID() {
		return fieldColumnHeadingsCCSID;
	}

	public void setFieldColumnHeadingsCCSID(Integer fieldColumnHeadingsCCSID) {
		this.fieldColumnHeadingsCCSID = fieldColumnHeadingsCCSID;
	}

	public Integer getFieldEditWordsCCSID() {
		return fieldEditWordsCCSID;
	}

	public void setFieldEditWordsCCSID(Integer fieldEditWordsCCSID) {
		this.fieldEditWordsCCSID = fieldEditWordsCCSID;
	}

	public Integer getUcs2DisplayedFieldLength() {
		return ucs2DisplayedFieldLength;
	}

	public void setUcs2DisplayedFieldLength(Integer ucs2DisplayedFieldLength) {
		this.ucs2DisplayedFieldLength = ucs2DisplayedFieldLength;
	}

	public Integer getFieldDataEncodingScheme() {
		return fieldDataEncodingScheme;
	}

	public void setFieldDataEncodingScheme(Integer fieldDataEncodingScheme) {
		this.fieldDataEncodingScheme = fieldDataEncodingScheme;
	}

	public Integer getMaximumLargeObjectFieldLength() {
		return maximumLargeObjectFieldLength;
	}

	public void setMaximumLargeObjectFieldLength(
			Integer maximumLargeObjectFieldLength) {
		this.maximumLargeObjectFieldLength = maximumLargeObjectFieldLength;
	}

	public Integer getPadLengthForLargeObject() {
		return padLengthForLargeObject;
	}

	public void setPadLengthForLargeObject(Integer padLengthForLargeObject) {
		this.padLengthForLargeObject = padLengthForLargeObject;
	}

	public Integer getLengthOfUserDefinedTypeName() {
		return lengthOfUserDefinedTypeName;
	}

	public void setLengthOfUserDefinedTypeName(
			Integer lengthOfUserDefinedTypeName) {
		this.lengthOfUserDefinedTypeName = lengthOfUserDefinedTypeName;
	}

	public String getUserDefinedTypeName() {
		return userDefinedTypeName;
	}

	public void setUserDefinedTypeName(String userDefinedTypeName) {
		this.userDefinedTypeName = userDefinedTypeName;
	}

	public String getUserDefinedTypeLibraryName() {
		return userDefinedTypeLibraryName;
	}

	public void setUserDefinedTypeLibraryName(String userDefinedTypeLibraryName) {
		this.userDefinedTypeLibraryName = userDefinedTypeLibraryName;
	}

	public String getDatalinkLinkControl() {
		return datalinkLinkControl;
	}

	public void setDatalinkLinkControl(String datalinkLinkControl) {
		this.datalinkLinkControl = datalinkLinkControl;
	}

	public String getDatalinkIntegrity() {
		return datalinkIntegrity;
	}

	public void setDatalinkIntegrity(String datalinkIntegrity) {
		this.datalinkIntegrity = datalinkIntegrity;
	}

	public String getDatalinkReadPermission() {
		return datalinkReadPermission;
	}

	public void setDatalinkReadPermission(String datalinkReadPermission) {
		this.datalinkReadPermission = datalinkReadPermission;
	}

	public String getDatalinkWritePermission() {
		return datalinkWritePermission;
	}

	public void setDatalinkWritePermission(String datalinkWritePermission) {
		this.datalinkWritePermission = datalinkWritePermission;
	}

	public String getDatalinkRecovery() {
		return datalinkRecovery;
	}

	public void setDatalinkRecovery(String datalinkRecovery) {
		this.datalinkRecovery = datalinkRecovery;
	}

	public String getDatalinkUnlinkControl() {
		return datalinkUnlinkControl;
	}

	public void setDatalinkUnlinkControl(String datalinkUnlinkControl) {
		this.datalinkUnlinkControl = datalinkUnlinkControl;
	}

	public Integer getDisplayOrPrintRowNumber() {
		return displayOrPrintRowNumber;
	}

	public void setDisplayOrPrintRowNumber(Integer displayOrPrintRowNumber) {
		this.displayOrPrintRowNumber = displayOrPrintRowNumber;
	}

	public Integer getDisplayOrPrintColumnNumber() {
		return displayOrPrintColumnNumber;
	}

	public void setDisplayOrPrintColumnNumber(Integer displayOrPrintColumnNumber) {
		this.displayOrPrintColumnNumber = displayOrPrintColumnNumber;
	}

	public String getRowidColumn() {
		return rowidColumn;
	}

	public void setRowidColumn(String rowidColumn) {
		this.rowidColumn = rowidColumn;
	}

	public String getIdentityColumn() {
		return identityColumn;
	}

	public void setIdentityColumn(String identityColumn) {
		this.identityColumn = identityColumn;
	}

	public String getGeneratedBy() {
		return generatedBy;
	}

	public void setGeneratedBy(String generatedBy) {
		this.generatedBy = generatedBy;
	}

	public String getIdentityColumnCycle() {
		return identityColumnCycle;
	}

	public void setIdentityColumnCycle(String identityColumnCycle) {
		this.identityColumnCycle = identityColumnCycle;
	}

	public BigDecimal getIdentityColumnOriginalStartWith() {
		return identityColumnOriginalStartWith;
	}

	public void setIdentityColumnOriginalStartWith(
			BigDecimal identityColumnOriginalStartWith) {
		this.identityColumnOriginalStartWith = identityColumnOriginalStartWith;
	}

	public BigDecimal getIdentityColumnCurrentStartWith() {
		return identityColumnCurrentStartWith;
	}

	public void setIdentityColumnCurrentStartWith(
			BigDecimal identityColumnCurrentStartWith) {
		this.identityColumnCurrentStartWith = identityColumnCurrentStartWith;
	}

	public Integer getIdentityColumnIncrementBy() {
		return identityColumnIncrementBy;
	}

	public void setIdentityColumnIncrementBy(Integer identityColumnIncrementBy) {
		this.identityColumnIncrementBy = identityColumnIncrementBy;
	}

	public BigDecimal getIdentityColumnMinValue() {
		return identityColumnMinValue;
	}

	public void setIdentityColumnMinValue(BigDecimal identityColumnMinValue) {
		this.identityColumnMinValue = identityColumnMinValue;
	}

	public BigDecimal getIdentityColumnMaxValue() {
		return identityColumnMaxValue;
	}

	public void setIdentityColumnMaxValue(BigDecimal identityColumnMaxValue) {
		this.identityColumnMaxValue = identityColumnMaxValue;
	}

	public Integer getIdentityColumnCache() {
		return identityColumnCache;
	}

	public void setIdentityColumnCache(Integer identityColumnCache) {
		this.identityColumnCache = identityColumnCache;
	}

	public String getIdentityColumnOrder() {
		return identityColumnOrder;
	}

	public void setIdentityColumnOrder(String identityColumnOrder) {
		this.identityColumnOrder = identityColumnOrder;
	}

	public String getImplicitlyHidden() {
		return implicitlyHidden;
	}

	public void setImplicitlyHidden(String implicitlyHidden) {
		this.implicitlyHidden = implicitlyHidden;
	}

	public String getRowChangeTimestampGenerated() {
		return rowChangeTimestampGenerated;
	}

	public void setRowChangeTimestampGenerated(
			String rowChangeTimestampGenerated) {
		this.rowChangeTimestampGenerated = rowChangeTimestampGenerated;
	}

	public String getFieldProcedureProgramName() {
		return fieldProcedureProgramName;
	}

	public void setFieldProcedureProgramName(String fieldProcedureProgramName) {
		this.fieldProcedureProgramName = fieldProcedureProgramName;
	}

	public String getFieldProcedureLibraryName() {
		return fieldProcedureLibraryName;
	}

	public void setFieldProcedureLibraryName(String fieldProcedureLibraryName) {
		this.fieldProcedureLibraryName = fieldProcedureLibraryName;
	}

	public ColumnInfo() {
		// make default constructor visible
	}

	public ColumnInfo(FLDL0100 input) {
		this.alternativeFieldName = input.getAlternativeFieldName();
		this.columnHeading1 = input.getColumnHeading1();
		this.columnHeading2 = input.getColumnHeading2();
		this.columnHeading3 = input.getColumnHeading3();
		this.datalinkIntegrity = input.getDatalinkIntegrity();
		this.datalinkLinkControl = input.getDatalinkLinkControl();
		this.datalinkReadPermission = input.getDatalinkReadPermission();
		this.datalinkRecovery = input.getDatalinkRecovery();
		this.datalinkUnlinkControl = input.getDatalinkUnlinkControl();
		this.datalinkWritePermission = input.getDatalinkWritePermission();
		this.dataType = input.getDataType();
		this.dateAndTimeFormat = input.getDateAndTimeFormat();
		this.dateAndTimeSeparator = input.getDateAndTimeSeparator();
		this.decimalPositions = input.getDecimalPositions();
		this.digits = input.getDigits();
		this.displayOrPrintColumnNumber = input.getDisplayOrPrintColumnNumber();
		this.displayOrPrintRowNumber = input.getDisplayOrPrintRowNumber();
		this.editCode = input.getEditCode();
		this.editWord = input.getEditWord();
		this.editWordLength = input.getEditWordLength();
		this.fieldColumnHeadingsCCSID = input.getFieldColumnHeadingsCCSID();
		this.fieldDataCCSID = input.getFieldDataCCSID();
		this.fieldDataEncodingScheme = input.getFieldDataEncodingScheme();
		this.fieldEditWordsCCSID = input.getFieldEditWordsCCSID();
		this.fieldLengthInBytes = input.getFieldLengthInBytes();
		this.fieldName = input.getFieldName();
		this.fieldProcedureLibraryName = input.getFieldProcedureLibraryName();
		this.fieldProcedureProgramName = input.getFieldProcedureProgramName();
		this.fieldTextDescription = input.getFieldTextDescription();
		this.fieldTextDescriptionCCSID = input.getFieldTextDescriptionCCSID();
		this.generatedBy = input.getGeneratedBy();
		this.hostVariableIndicator = input.getHostVariableIndicator();
		this.identityColumn = input.getIdentityColumn();
		this.identityColumnCache = input.getIdentityColumnCache();
		this.identityColumnCurrentStartWith = input
				.getIdentityColumnCurrentStartWith();
		this.identityColumnCycle = input.getIdentityColumnCycle();
		this.identityColumnIncrementBy = input.getIdentityColumnIncrementBy();
		this.identityColumnMaxValue = input.getIdentityColumnMaxValue();
		this.identityColumnMinValue = input.getIdentityColumnMinValue();
		this.identityColumnOrder = input.getIdentityColumnOrder();
		this.identityColumnOriginalStartWith = input
				.getIdentityColumnOriginalStartWith();
		this.implicitlyHidden = input.getImplicitlyHidden();
		this.inputBufferPosition = input.getInputBufferPosition();
		this.internalFieldName = input.getInternalFieldName();
		this.lengthOfAlternativeFieldName = input
				.getLengthOfAlternativeFieldName();
		this.lengthOfUserDefinedTypeName = input
				.getLengthOfUserDefinedTypeName();
		this.maximumLargeObjectFieldLength = input
				.getMaximumLargeObjectFieldLength();
		this.nullValuesAllowed = input.getNullValuesAllowed();
		this.numberOfDBCSCharacters = input.getNumberOfDBCSCharacters();
		this.outputBufferPosition = input.getOutputBufferPosition();
		this.padLengthForLargeObject = input.getPadLengthForLargeObject();
		this.rowChangeTimestampGenerated = input
				.getRowChangeTimestampGenerated();
		this.rowidColumn = input.getRowidColumn();
		this.ucs2DisplayedFieldLength = input.getUcs2DisplayedFieldLength();
		this.use = input.getUse();
		this.userDefinedTypeLibraryName = input.getUserDefinedTypeLibraryName();
		this.userDefinedTypeName = input.getUserDefinedTypeName();
		this.variableLengthFieldIndicator = input
				.getVariableLengthFieldIndicator();

	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append(" Field Name: " + fieldName.trim());
		sb.append(" Data Type: " + dataType.trim());
		sb.append(" Use: " + use.trim());
		sb.append(" Output Buffer Position: " + outputBufferPosition.toString());
		sb.append(" Input Buffer Position: " + inputBufferPosition.toString());
		sb.append(" Field length in Bytes: " + fieldLengthInBytes.toString());
		sb.append(" Digits: " + digits.toString());
		sb.append(" Decimal Positions: " + decimalPositions.toString());
		sb.append(" Field Text Description: " + fieldTextDescription.trim());
		sb.append(" Edit Code: " + editCode.trim());
		sb.append(" Edit Word Length: " + editWordLength.toString());
		sb.append(" Edit Word: " + editWord.trim());
		sb.append(" Column Heading 1: " + columnHeading1.trim());
		sb.append(" Column Heading 2: " + columnHeading2.trim());
		sb.append(" Column Heading 3: " + columnHeading3.trim());
		sb.append(" Internal Field Name: " + internalFieldName.trim());
		sb.append(" Alternative Field Name: " + alternativeFieldName.trim());
		sb.append(" Length of Alternative Field Name: "
				+ lengthOfAlternativeFieldName.toString());
		sb.append(" Number of DBCS Characters: "
				+ numberOfDBCSCharacters.toString());
		sb.append(" Null Values Allowed: " + nullValuesAllowed.trim());
		sb.append(" Host Variable Indicator: " + hostVariableIndicator.trim());
		sb.append(" Date And Time Format: " + dateAndTimeFormat.trim());
		sb.append(" Date And Time Separator: " + dateAndTimeSeparator.trim());
		sb.append(" Variable Length Field Indicator: "
				+ variableLengthFieldIndicator.trim());
		sb.append(" Field Text Description CCSID: "
				+ fieldTextDescriptionCCSID.toString());
		;
		sb.append(" Field Data CCSID: " + fieldDataCCSID.toString());
		sb.append(" Field Column Headings CCSID: "
				+ fieldColumnHeadingsCCSID.toString());
		sb.append(" Field Edit Word CCSID: " + fieldEditWordsCCSID.toString());
		sb.append(" UCS2 Displayed Field Length: "
				+ ucs2DisplayedFieldLength.toString());
		sb.append(" Field Data Encoding Scheme: "
				+ fieldDataEncodingScheme.toString());
		sb.append(" Maximum Large Object Field Length: "
				+ maximumLargeObjectFieldLength.toString());
		sb.append(" Pad Length For Large Object: "
				+ padLengthForLargeObject.toString());
		sb.append(" Length of User Defined Type Name: "
				+ lengthOfUserDefinedTypeName.toString());
		sb.append(" User Defined Type Name: " + userDefinedTypeName.trim());
		sb.append(" User Defined Type Library Name: "
				+ userDefinedTypeLibraryName.trim());
		sb.append(" Datalink Link Control: " + datalinkLinkControl.trim());
		sb.append(" Datalink Integrity: " + datalinkIntegrity.trim());
		sb.append(" Datalink Read Permission: " + datalinkReadPermission.trim());
		sb.append(" Datalink Write Permission: "
				+ datalinkWritePermission.trim());
		sb.append(" Datalink Recovery: " + datalinkRecovery.trim());
		sb.append(" Datalink Unlink Control: " + datalinkUnlinkControl.trim());
		sb.append(" Display or Print Row Number: "
				+ displayOrPrintRowNumber.toString());
		sb.append(" Display or Print Column Number: "
				+ displayOrPrintColumnNumber.toString());
		sb.append(" Rowid Column: " + rowidColumn.trim());
		sb.append(" Identity Column: " + identityColumn.trim());
		sb.append(" Generated By: " + generatedBy.trim());
		sb.append(" Identity Column Cycle: " + identityColumnCycle.trim());
		sb.append(" Identity Column Original Start With: "
				+ identityColumnOriginalStartWith.toString());
		sb.append(" Identity Column Current Start With: "
				+ identityColumnCurrentStartWith.toString());
		sb.append(" Identity Column Increment By: "
				+ identityColumnIncrementBy.toString());
		sb.append(" Identity Column Min Value: "
				+ identityColumnMinValue.toString());
		sb.append(" Identity Column Max Value: "
				+ identityColumnMaxValue.toString());
		sb.append(" Identity Column Cache: " + identityColumnCache.toString());
		sb.append(" Identity Column Order: " + identityColumnOrder.trim());
		sb.append(" Implicitly Hidden: " + implicitlyHidden.trim());
		sb.append(" Row Change Timestamp Generated: "
				+ rowChangeTimestampGenerated.trim());
		sb.append(" Field Procedure Program Name: "
				+ fieldProcedureProgramName.trim());
		sb.append(" Field Procedure Library Name: "
				+ fieldProcedureLibraryName.trim());

		return sb.toString();
	}

}
