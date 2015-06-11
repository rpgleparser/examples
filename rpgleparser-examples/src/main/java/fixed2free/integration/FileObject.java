package fixed2free.integration;

import java.util.HashMap;

public class FileObject {
	private HashMap<String, RecordFormat> recordFormats = new HashMap<String, RecordFormat>();
	private String fileName;
	private String libraryName;
	private String fileDescription;
	
	public void addRecordFormat(RecordFormat inRecordFormat) {
		if (!recordFormats.containsKey(inRecordFormat)) {
			recordFormats.put(inRecordFormat.getName(), inRecordFormat);
		}
	}

	public RecordFormat getRecordFormatByName(String name) {
		return recordFormats.get(name);
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getLibraryName() {
		return libraryName;
	}

	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}

	public String getFileDescription() {
		return fileDescription;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public HashMap<String, RecordFormat> getRecordFormats() {
		return recordFormats;
	}
}
