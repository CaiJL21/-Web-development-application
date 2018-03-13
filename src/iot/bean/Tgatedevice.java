package iot.bean;

import java.sql.Timestamp;

public class Tgatedevice {
	private long id;
	private long gid;
	private long did;
	private String clientdevicename;
	private String clientdeviceid;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getGid() {
		return gid;
	}
	public void setGid(long gid) {
		this.gid = gid;
	}
	public long getDid() {
		return did;
	}
	public void setDid(long did) {
		this.did = did;
	}
	public String getClientdevicename() {
		return clientdevicename;
	}
	public void setClientdevicename(String clientdevicename) {
		this.clientdevicename = clientdevicename;
	}
	public String getClientdeviceid() {
		return clientdeviceid;
	}
	public void setClientdeviceid(String clientdeviceid) {
		this.clientdeviceid = clientdeviceid;
	}
	public String getClientdeviceconfig() {
		return clientdeviceconfig;
	}
	public void setClientdeviceconfig(String clientdeviceconfig) {
		this.clientdeviceconfig = clientdeviceconfig;
	}
	public String getClientdevicestate() {
		return clientdevicestate;
	}
	public void setClientdevicestate(String clientdevicestate) {
		this.clientdevicestate = clientdevicestate;
	}
	public String getClientdeviceenabled() {
		return clientdeviceenabled;
	}
	public void setClientdeviceenabled(String clientdeviceenabled) {
		this.clientdeviceenabled = clientdeviceenabled;
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
	private String clientdeviceconfig;
	private String clientdevicestate;
	private String clientdeviceenabled;
	private String remark;
	private Timestamp addtime;
}
