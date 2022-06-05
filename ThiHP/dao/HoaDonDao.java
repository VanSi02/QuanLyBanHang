package dao;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.HoaDonBean;
import bean.KhachHangbean;

public class HoaDonDao {
	public ArrayList<HoaDonBean> hdbean=new ArrayList<HoaDonBean>();
	public ArrayList<HoaDonBean> getHD() throws Exception{

		String sql="Select * from HoaDon";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		
		while(rs.next()) {
			String maHD=rs.getString("MaHD");
			String MaKH=rs.getString("MaKH"); 
			Date NgayLapHD=rs.getDate("NgayLapHD");
			HoaDonBean hbean=new HoaDonBean(maHD, MaKH, NgayLapHD);
			
			hdbean.add(hbean);
		}
		rs.close();
		return hdbean;
	}
	public ArrayList<String> getmaHD() throws Exception{

		String sql="Select MaHD from HoaDon";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		ArrayList< String> tam=new ArrayList<String>();
		while(rs.next()) {
			String MaHD=rs.getString("MaHD");
			System.out.println(MaHD);
			tam.add(MaHD);
		}
		rs.close();
		return tam;
	}
	public int Them(String MaHD, String MaKH,Date ngayban ) throws Exception{
		String sql="insert into HoaDon(MaHD, MaKH, NgayLapHD) values (?,?,?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, MaHD);
		 cmd.setString(2,MaKH);
		 cmd.setDate(3,new java.sql.Date (ngayban.getTime()));
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int ThemMAHD(String MaHD) throws Exception{
		String sql="insert into HoaDon(MaHD) values (?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, MaHD);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int Thanhtoan(String MaHD, String MaKH,Date ngayban) throws Exception{
		String sql="update HoaDon set  MaKH=?,NgayLapHD=? where MaHD=?";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(3, MaHD);
		 cmd.setString(1, MaKH);
		 cmd.setDate(2,new java.sql.Date (ngayban.getTime()));
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
}
