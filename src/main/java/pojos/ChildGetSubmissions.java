package pojos;

import java.util.List;

public class ChildGetSubmissions {

private Integer sequence;
private String title;
private String description;
private List<String> fileTypes = null;
private String type;
private String documentId;
private String documentName;
private Integer documentSize;
/**
* No args constructor for use in serialization
*
*/
public ChildGetSubmissions() {
}

/**
*
* @param sequence
* @param description
* @param title
* @param fileTypes
*/
public ChildGetSubmissions(Integer sequence, String title, String description, List<String> fileTypes, String type, String documentId, String documentName, Integer documentSize) {
super();
this.sequence = sequence;
this.title = title;
this.description = description;
this.fileTypes = fileTypes;
this.type = type;
this.documentId = documentId;
this.documentName = documentName;
this.documentSize = documentSize;
}

public Integer getSequence() {
return sequence;
}

public void setSequence(Integer sequence) {
this.sequence = sequence;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public List<String> getFileTypes() {
return fileTypes;
}

public void setFileTypes(List<String> fileTypes) {
this.fileTypes = fileTypes;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public String getDocumentId() {
	return documentId;
}

public void setDocumentId(String documentId) {
	this.documentId = documentId;
}

public String getDocumentName() {
	return documentName;
}

public void setDocumentName(String documentName) {
	this.documentName = documentName;
}

public Integer getDocumentSize() {
	return documentSize;
}

public void setDocumentSize(Integer documentSize) {
	this.documentSize = documentSize;
}
}
