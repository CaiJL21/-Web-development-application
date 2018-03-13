package iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import iot.bean.tdevice;
import iot.utils.ConnDb;
public class DeviceDao {
	private Connection cn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	public static final int PAGE_LENGTH = 5;
	public void closedb() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (cn != null) {
				cn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 查询,未分页
	public ArrayList<tdevice> query(String conStr) {
		ArrayList<tdevice> retlist = new ArrayList<tdevice>();
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "select  * from tdevice  ";
			if (conStr != "") {
				sqlstr = "select  * from tdevice where " + conStr + " ";
			}
			System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				tdevice temp = new tdevice();
				temp.setId(rs.getLong("id"));
				temp.setDtid(rs.getLong("dtid"));
				temp.setDevicecode(rs.getString("devicecode"));
				temp.setDevicename(rs.getString("devicename"));
				temp.setDevicephoto(rs.getString("devicephoto"));
				temp.setDeviceconfig(rs.getString("deviceconfig"));
				temp.setRemark(rs.getString("remark"));
				temp.setDeviceenabled(rs.getInt("deviceenabled"));
				temp.setAddtime(rs.getTimestamp("addtime"));
				retlist.add(temp);
			}

		} catch (Exception e) {
			retlist = null;
			e.printStackTrace();
		} finally {
			this.closedb();
		}

		return retlist;
	}

	// 分页查询
	public ArrayList<tdevice> queryPage(String conStr, int page) {
		ArrayList<tdevice> retlist = new ArrayList<tdevice>();
		try {
			cn = new ConnDb().getcon();

			int begin = (page - 1) * PAGE_LENGTH;
			String sqllimit = "  order by id desc limit " + begin + ","
					+ PAGE_LENGTH;

			String sqlstr = "select  * from tdevice " + sqllimit;
			if (conStr != "") {
				sqlstr = "select  * from tdevice where " + conStr + sqllimit;
			}
			// System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				tdevice temp = new tdevice();
				temp.setId(rs.getLong("id"));
				temp.setDtid(rs.getLong("dtid"));
				temp.setDevicecode(rs.getString("devicecode"));
				temp.setDevicename(rs.getString("devicename"));
				temp.setDevicephoto(rs.getString("devicephoto"));
				temp.setDeviceconfig(rs.getString("deviceconfig"));
				temp.setRemark(rs.getString("remark"));
				temp.setDeviceenabled(rs.getInt("deviceenabled"));
				temp.setAddtime(rs.getTimestamp("addtime"));
				retlist.add(temp);
			}

		} catch (Exception e) {
			retlist = null;
			e.printStackTrace();
		} finally {
			this.closedb();
		}

		return retlist;
	}

	public int count(String conStr) {

		int n = 0;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "select  count(*) from tdevice ";
			if (conStr != "") {
				sqlstr = "select  count(*) from tdevice where " + conStr;
			}
			// System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			if (rs.next()) {

				n = rs.getInt(1);

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closedb();
		}
		return n;
	}

	// 用户添加
	public boolean add(tdevice device) {
		boolean addFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "insert into tdevice(dtid,devicecode,devicename,devicephoto) values(?,?,?,?) ";

			ps = cn.prepareStatement(sqlstr);
			// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
			ps.setLong(1, device.getDtid());
			ps.setString(2, device.getDevicecode());
			ps.setString(3, device.getDevicename());
			ps.setString(4, device.getDevicephoto());
			int rows = ps.executeUpdate();
			if (rows > 0) {
				addFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closedb();
		}
		return addFlag;
	}

	// 修改
	public boolean update(tdevice device) {
		boolean altFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "update tdevice set devicecode=?,devicename=?,devicephoto=? where id=? ";

			ps = cn.prepareStatement(sqlstr);
			// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
			
			ps.setString(1, device.getDevicecode());
			ps.setString(2, device.getDevicename());
			ps.setString(3, device.getDevicephoto());
			ps.setLong(4, device.getId());

			int rows = ps.executeUpdate();
			if (rows > 0) {
				altFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closedb();
		}
		return altFlag;
	}

	// 删除记录
	public boolean delete(String number) {
		boolean delFlag = false;
		try {
			cn = new ConnDb().getcon();

			String sqlstr = "delete from  tdevice  where id=" + number + "  ";
			ps = cn.prepareStatement(sqlstr);

			int rows = ps.executeUpdate();
			if (rows > 0) {
				delFlag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closedb();
		}
		return delFlag;
	}

}


