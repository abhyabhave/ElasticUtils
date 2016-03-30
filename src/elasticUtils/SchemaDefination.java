package elasticUtils;

public class SchemaDefination {

	public SchemaDefination() {
		// TODO Auto-generated constructor stub
	}

	String State; 
	public String getState() {
		return State;
	}
	public void setState(String state) {
		this.State = state;
	}
	public String getCloudClass() {
		return CloudClass;
	}
	public void setCloudClass(String cloudClass) {
		this.CloudClass = cloudClass;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public String getSubmitter() {
		return Submitter;
	}
	public void setSubmitter(String submitter) {
		this.Submitter = submitter;
	}
	public String getCreateDate() {
		return CreateDate;
	}
	public void setCreateDate(String createDate) {
		this.CreateDate = createDate;
	}

	String CloudClass; 
	String Name; 
	String Submitter; 
	String CreateDate;
}
