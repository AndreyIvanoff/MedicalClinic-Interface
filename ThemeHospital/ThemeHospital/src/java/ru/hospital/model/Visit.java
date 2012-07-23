/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.hospital.model;

import java.util.Date;
import ru.hospital.model.base.EntityExtension;
import ru.hospital.model.base.TextField;

public class Visit extends EntityExtension{

	@TextField
	private String doctorName;
	private Long doctorId;

	@TextField
	private String pacientName;
	private Long pacientId;

	@TextField
	private String serviceName;
	private Long serviceId;

	private Date start;
	private Date end;

	private String cost;

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Long getPacientId() {
		return pacientId;
	}

	public void setPacientId(Long pacientId) {
		this.pacientId = pacientId;
	}

	public String getPacientName() {
		return pacientName;
	}

	public void setPacientName(String pacientName) {
		this.pacientName = pacientName;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}
}
