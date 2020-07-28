package pojos;

import java.util.List;

public class GetTaskLists {

private String courseId;
private String assetId;
private String assetType;
private Boolean required;
private String status;
private String title;
private String rubricId;
private String nextAssetId;
private List<ChildGetTaskLists> children;
private String finalAssetId;
private String assessmentId;
private String assessmentItemId;
private String state;

public GetTaskLists() {
}

public String getCourseId() {
return courseId;
}

public void setCourseId(String courseId) {
this.courseId = courseId;
}

public String getAssetId() {
return assetId;
}

public void setAssetId(String assetId) {
this.assetId = assetId;
}

public String getAssetType() {
return assetType;
}

public void setAssetType(String assetType) {
this.assetType = assetType;
}

public Boolean getRequired() {
return required;
}

public void setRequired(Boolean required) {
this.required = required;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getRubricId() {
return rubricId;
}

public void setRubricId(String rubricId) {
this.rubricId = rubricId;
}

public String getNextAssetId() {
return nextAssetId;
}

public void setNextAssetId(String nextAssetId) {
this.nextAssetId = nextAssetId;
}

public List<ChildGetTaskLists> getChildren() {
return children;
}

public void setChildren(List<ChildGetTaskLists> children) {
this.children = children;
}

public String getFinalAssetId() {
	return finalAssetId;
}

public void setFinalAssetId(String finalAssetId) {
	this.finalAssetId = finalAssetId;
}

public String getAssessmentId() {
	return assessmentId;
}

public void setAssessmentId(String assessmentId) {
	this.assessmentId = assessmentId;
}

public String getAssessmentItemId() {
	return assessmentItemId;
}

public void setAssessmentItemId(String assessmentItemId) {
	this.assessmentItemId = assessmentItemId;
}

public String getState() {
	return state;
}

public void setState(String state) {
	this.state = state;
}


}