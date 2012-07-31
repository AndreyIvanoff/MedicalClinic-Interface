package ru.hospital.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ru.hospital.model.base.EntityExtension;

public class Event extends EntityExtension{

	public static final String TYPE_ADMIN_CREATED = "New Administrator";
	public static final String TYPE_ADMIN_UPDATED = "Update Administrator";
	public static final String TYPE_ADMIN_REMOVED = "Remove Administrator";

	public static final String TYPE_DOCTOR_CREATED = "New Doctor";
	public static final String TYPE_DOCTOR_UPDATED = "Update Doctor";
	public static final String TYPE_DOCTOR_REMOVED = "Remove Doctor";

	public static final String TYPE_PACIENT_CREATED = "New Client";
	public static final String TYPE_PACIENT_UPDATED = "Update Client";
	public static final String TYPE_PACIENT_REMOVED = "Remove Client";

	public static final String TYPE_VISIT_CREATED = "New Visit";
	public static final String TYPE_VISIT_UPDATED = "Update Visit";
	public static final String TYPE_VISIT_REMOVED = "Remove Visit";

	public static final String TAG_FAILED = "Failed";

	public static final String PARAM_TYPE = "type";
	public static final String PARAM_TAGS = "tags";
	public static final String PARAM_DATE = "date";
	public static final String PARAM_INITIATOR_ID = "initiatorId";

	private Date date;
	private String type;
	private Long initiatorId;
	private String initiatorName;
	
	private List<String> tags;

	public Event(){
		
	}

	public Event(String type, Administrator admin, String additional, String... tags){
		super();
		this.type = type;
		this.initiatorId = admin.getId();
		this.initiatorName = admin.getLastName() + " "
							+ admin.getFirstName().substring(0, 1).toUpperCase() + "."
							+ admin.getFatherName().substring(0, 1).toUpperCase() + ".";

		this.date = new Date();
		this.additional = additional;

		this.tags = new ArrayList<>();

		if  (tags != null){
			for (String tag: tags){
				this.tags.add(tag.toLowerCase());
			}
		}
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getInitiatorId() {
		return initiatorId;
	}

	public void setInitiatorId(Long initiatorId) {
		this.initiatorId = initiatorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInitiatorName() {
		return initiatorName;
	}

	public void setInitiatorName(String initiatorName) {
		this.initiatorName = initiatorName;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}
}
