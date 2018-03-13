package iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import iot.bean.Tgatedevice;
import iot.utils.ConnDb;

public class TgatedeviceDao {

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
	public ArrayList<Tgatedevice> query(String conStr) {
		ArrayList<Tgatedevice> retlist = new ArrayList<Tgatedevice>();
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "select  * from tgatedevice  ";
			if (conStr != "") {
				sqlstr = "select  * from tgatedevice where " + conStr + " ";
			}
			System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				Tgatedevice temp = new Tgatedevice();
				temp.setId(rs.getLong("id"));
				temp.setDid(rs.getLong("did"));
				temp.setGid(rs.getLong("gid"));
				temp.setClientdevicename(rs.getString("clientdevicename"));
				temp.setClientdeviceid(rs.getString("clientdeviceid"));
				temp.setClientdeviceconfig(rs.getString("clientdeviceconfig"));
				temp.setClientdevicestate(rs.getString("clientdevicestate"));
				temp.setClientdeviceenabled(rs.getString("clientdeviceenabled"));
				temp.setRemark(rs.getString("remark"));
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
	public ArrayList<Tgatedevice> queryPage(String conStr, int page) {
		ArrayList<Tgatedevice> retlist = new ArrayList<Tgatedevice>();
		try {
			cn = new ConnDb().getcon();

			int begin = (page - 1) * PAGE_LENGTH;
			String sqllimit = "  order by id desc limit " + begin + ","
					+ PAGE_LENGTH;

			String sqlstr = "select  * from tgatedevice " + sqllimit;
			if (conStr != "") {
				sqlstr = "select  * from tgatedevice where " + conStr + sqllimit;
			}
			// System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				Tgatedevice temp = new Tgatedevice();
				temp.setId(rs.getLong("id"));
				temp.setDid(rs.getLong("did"));
				temp.setGid(rs.getLong("gid"));
				temp.setClientdevicename(rs.getString("clientdevicename"));
				temp.setClientdeviceid(rs.getString("clientdeviceid"));
				temp.setClientdeviceconfig(rs.getString("clientdeviceconfig"));
				temp.setClientdevicestate(rs.getString("clientdevicestate"));
				temp.setClientdeviceenabled(rs.getString("clientdeviceenabled"));
				temp.setRemark(rs.getString("remark"));
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
			String sqlstr = "select  count(*) from tgatedevice ";
			if (conStr != "") {
				sqlstr = "select  count(*) from tgatedevice where " + conStr;
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
	public boolean add(Tgatedevice user) {
		boolean addFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "insert into tgatedevice(gid,did,clientdevicename,clientdeviceid,clientdeviceenabled,addtime) values(?,?,?,?,?,?) ";

			ps = cn.prepareStatement(sqlstr);
			// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
			ps.setLong(1, user.getGid());
			ps.setLong(2, user.getDid());
			ps.setString(3, user.getClientdevicename());
			ps.setString(4, user.getClientdeviceid());
			ps.setString(5, user.getClientdeviceenabled());
			ps.setTimestamp(6, user.getAddtime());
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
	public boolean update(Tgatedevice user) {
		boolean altFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "update tgatedevice set gid=?,did=?,clientdevicename=?,clientdeviceid=?,clientdeviceenabled where id=?";

			ps = cn.prepareStatement(sqlstr);
			// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
			ps.setLong(1, user.getGid());
			ps.setLong(2, user.getDid());
			ps.setString(3, user.getClientdevicename());
			ps.setString(4, user.getClientdeviceid());
			ps.setString(5, user.getClientdeviceenabled());
			ps.setLong(6, user.getId());
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

			String sqlstr = "delete from  tgatedevice  where id=" + number + "  ";
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
