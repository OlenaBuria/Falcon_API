package pojos;

import java.util.List;

public class GetSubmissions {

private String courseId;
private String assetId;
private List<ChildGetSubmissions> children = null;

/**
* No args constructor for use in serialization
*
*/
public GetSubmissions() {
}

/**
*
* @param children
* @param assetId
* @param courseId
*/
public GetSubmissions(String courseId, String assetId, List<ChildGetSubmissions> children) {
super();
this.courseId = courseId;
this.assetId = assetId;
this.children = children;
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

public List<ChildGetSubmissions> getChildren() {
return children;
}

public void setChildren(List<ChildGetSubmissions> children) {
this.children = children;
}

}