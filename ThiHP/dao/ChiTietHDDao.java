package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ChiTietHD;
import bean.HangBean;

public class ChiTietHDDao {
	public ArrayList<ChiTietHD> ds=new ArrayList<ChiTietHD>();
	public ArrayList<ChiTietHD> getChiTietHoaDon() throws Exception{
		
		String sql="Select * from ChiTietHD";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		
		while(rs.next()) {
			String MaHD=rs.getString("MaHD");
			String MaHang=rs.getString("MaHang");
			int soLuongban=rs.getInt("SoLuongBan");
			String MaKH=rs.getString("MaKH");
			ChiTietHD cthdbean=new ChiTietHD(MaHD, MaHang, soLuongban,MaKH);
			ds.add(cthdbean);
		}
		rs.close();
		return ds;
	}
	public int ThemCTHD(String MaHD, String MaHang, int soLuongban,String MaKH) throws Exception{
		String sql="insert into ChiTietHD(MaHD, MaHang, SoLuongBan, MaKH) values (?,?,?,?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, MaHD);
		 cmd.setString(2,MaHang);
		 cmd.setInt(3, soLuongban);
		 cmd.setString(4, MaKH);
		 //b3: Thuc hien cau lenh
		 System.out.println("thuc hien dc cau lenh");
		return cmd.executeUpdate();
	}
	public int ThemMAHD(String MaHD) throws Exception{
		String sql="insert into ChiTietHD(MaHD) values (?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, MaHD);
		 //b3: Thuc hien cau lenh
		 System.out.println("thuc hien dc cau lenh");
		return cmd.executeUpdate();
		
	}
	public String  getbestKH() throws Exception{
		
		String sql="SELECT top(1) MaKH, COUNT(MaKH) \r\n"
				+ "  FROM ChiTietHD\r\n"
				+ "  GROUP BY MaKH \r\n"
				+ "  order by COUNT(MaKH) desc";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		String MaKH="";
		while(rs.next()) {
			MaKH=rs.getString("MaKH");
		}
		rs.close();
		return MaKH;
	}
	public String  getbestHang() throws Exception{
		
		String sql=" SELECT TOP(1) MaHang, COUNT(MaHang) \r\n"
				+ "  FROM ChiTietHD\r\n"
				+ "  GROUP BY MaHang \r\n"
				+ "  order by COUNT(MaHang) desc";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		String MaHang="";
		while(rs.next()) {
			MaHang=rs.getString("MaHang");
		}
		rs.close();
		return MaHang;
	}
}
