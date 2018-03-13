package iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import iot.bean.Gate;
import iot.utils.ConnDb;

public class GateDao {
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
	public ArrayList<Gate> query(String conStr) {
		ArrayList<Gate> retlist = new ArrayList<Gate>();
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "select  * from tgate  ";
			if (conStr != "") {
				sqlstr = "select  * from tgate where " + conStr + " ";
			}
			System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				Gate temp = new Gate();
				temp.setId(rs.getLong("id"));
				temp.setPid(rs.getLong("pid"));
				temp.setGatename(rs.getString("gatename"));
				temp.setGateid(rs.getString("gateid"));
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
	public ArrayList<Gate> queryPage(String conStr, int page) {
		ArrayList<Gate> retlist = new ArrayList<Gate>();
		try {
			cn = new ConnDb().getcon();

			int begin = (page - 1) * PAGE_LENGTH;
			String sqllimit = "  order by id desc limit " + begin + ","
					+ PAGE_LENGTH;

			String sqlstr = "select  * from tgate " + sqllimit;
			if (conStr != "") {
				sqlstr = "select  * from tgate where " + conStr + sqllimit;
			}
			// System.out.println(sqlstr);
			ps = cn.prepareStatement(sqlstr);

			rs = ps.executeQuery();
			while (rs.next()) {
				Gate temp = new Gate();
				temp.setId(rs.getLong("id"));
				temp.setPid(rs.getLong("pid"));
				temp.setGatename(rs.getString("gatename"));
				temp.setGateid(rs.getString("gateid"));
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
			String sqlstr = "select  count(*) from tgate ";
			if (conStr != "") {
				sqlstr = "select  count(*) from tgate where " + conStr;
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

	// 监控网关添加
	public boolean add(Gate gate) {
		boolean addFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "insert into tgate(pid,gatename,gateid) values(?,?,?) ";

			ps = cn.prepareStatement(sqlstr);
			// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
			ps.setLong(1, gate.getPid());
			ps.setString(2, gate.getGatename());
			ps.setString(3, gate.getGateid());
			

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
	public boolean update(Gate gate) {
		boolean altFlag = false;
		try {
			cn = new ConnDb().getcon();
			String sqlstr = "update tgate set  pid=?,gatename=?,gateid=? where id=? ";

			ps = cn.prepareStatement(sqlstr);
			
			ps.setLong(1, gate.getPid());
			ps.setString(2, gate.getGatename());
			ps.setString(3, gate.getGateid());
			ps.setLong(4, gate.getId());

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

			String sqlstr = "delete from  tgate  where id=" + number + "  ";
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

