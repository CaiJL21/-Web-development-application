package iot.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import iot.bean.Thistorydata;
import iot.utils.ConnDb;

public class RecorddataDao {
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
		public ArrayList<Thistorydata> query(String conStr) {
			ArrayList<Thistorydata> retlist = new ArrayList<Thistorydata>();
			try {
				cn = new ConnDb().getcon();
				String sqlstr = "select  * from thistorydata  ";
				if (conStr != "") {
					sqlstr = "select  * from thistorydata where " + conStr + " ";
				}
				System.out.println(sqlstr);
				ps = cn.prepareStatement(sqlstr);

				rs = ps.executeQuery();
				while (rs.next()) {
					Thistorydata temp = new Thistorydata();
					temp.setId(rs.getLong("id"));
					temp.setGdid(rs.getLong("gdid"));
					temp.setRecorddata(rs.getString("recorddata"));
					temp.setRecordtime(rs.getTimestamp("recordtime"));
					temp.setRemark(rs.getString("remark"));
					
					
					
					
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
		public ArrayList<Thistorydata> queryPage(String conStr, int page) {
			ArrayList<Thistorydata> retlist = new ArrayList<Thistorydata>();
			try {
				cn = new ConnDb().getcon();

				int begin = (page - 1) * PAGE_LENGTH;
				String sqllimit = "  order by id desc limit " + begin + ","
						+ PAGE_LENGTH;

				String sqlstr = "select  * from thistorydata " + sqllimit;
				if (conStr != "") {
					sqlstr = "select  * from thistorydata where " + conStr + sqllimit;
				}
				// System.out.println(sqlstr);
				ps = cn.prepareStatement(sqlstr);

				rs = ps.executeQuery();
				while (rs.next()) {
					Thistorydata temp = new Thistorydata();
					temp.setId(rs.getLong("id"));
					temp.setGdid(rs.getLong("gdid"));
					temp.setRecorddata(rs.getString("recorddata"));
					temp.setRecordtime(rs.getTimestamp("recordtime"));
					temp.setRemark(rs.getString("remark"));

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
	
		
		// 添加
		public boolean add(Thistorydata history) {
			boolean addFlag = false;
			try {
				cn = new ConnDb().getcon();
				String sqlstr = "insert into thistorydata(gdid,recordtime,recorddata) values(?,?,?) ";

				ps = cn.prepareStatement(sqlstr);
				// 采用参数构造法，避免用拼接方法带来的特殊字符冲突
				
				ps.setLong(1, history.getGdid());
				ps.setTimestamp(2, history.getRecordtime());
				ps.setString(3, history.getRecorddata());
				

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
}
