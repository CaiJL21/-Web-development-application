package iot.bean;

import java.sql.Timestamp;

public class Gate {
	private Long id;
	 private Long pid;
	 private String gatename;
	 private String gateid;
	 private String gateenabled;
	 private String remark;
	 private Timestamp addtime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPid() {
		return pid;
	}
	public void setPid(Long pid) {
		this.pid = pid;
	}
	public String getGatename() {
		return gatename;
	}
	public void setGatename(String gatename) {
		this.gatename = gatename;
	}
	public String getGateid() {
		return gateid;
	}
	public void setGateid(String gateid) {
		this.gateid = gateid;
	}
	public String getGateenabled() {
		return gateenabled;
	}
	public void setGateenabled(String gateenabled) {
		this.gateenabled = gateenabled;
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
