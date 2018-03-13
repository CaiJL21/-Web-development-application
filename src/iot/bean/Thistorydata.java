package iot.bean;

import java.sql.Timestamp;

public class Thistorydata {
	private Long id;
	 private Long gdid;
	 private Timestamp recordtime;
	 private String recorddata;
	 private String remark;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getGdid() {
		return gdid;
	}
	public void setGdid(Long gdid) {
		this.gdid = gdid;
	}
	
	
	public Timestamp getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(Timestamp recordtime) {
		this.recordtime = recordtime;
	}
	public String getRecorddata() {
		return recorddata;
	}
	public void setRecorddata(String recorddata) {
		this.recorddata = recorddata;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
}
