package iot.bean;

import java.sql.Timestamp;

public class tdevicetype implements java.io.Serializable{
	private Long id;
	
	private String devicetypecode;
	private String devicetypename;
	
	private String devicetypeconfig;
	
	private String remark;
	private Timestamp addtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDevicetypecode() {
		return devicetypecode;
	}
	public void setDevicetypecode(String devicetypecode) {
		this.devicetypecode = devicetypecode;
	}
	public String getDevicetypename() {
		return devicetypename;
	}
	public void setDevicetypename(String devicetypename) {
		this.devicetypename = devicetypename;
	}
	public String getDevicetypeconfig() {
		return devicetypeconfig;
	}
	public void setDevicetypeconfig(String devicetypeconfig) {
		this.devicetypeconfig = devicetypeconfig;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getAddtime() {
		return addtime;
	}
	public void setAddtime(Timestamp addtime) {
		this.addtime = addtime;
	}
	
}