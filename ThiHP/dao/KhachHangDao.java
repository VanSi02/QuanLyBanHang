package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.KhachHangbean;


public class KhachHangDao {
	public ArrayList<KhachHangbean> ds=new ArrayList<KhachHangbean>();
	public ArrayList<KhachHangbean> getKH() throws Exception{

		String sql="Select * from KhachHang";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		
		while(rs.next()) {
			String maKH=rs.getString("MaKH");
			String hoTenKH=rs.getString("HoTenKH"); 
			String diaChi=rs.getString("DiaChi");
			String sDT=rs.getString("SDT");
			KhachHangbean kh=new KhachHangbean(maKH, hoTenKH, diaChi, sDT);
			ds.add(kh);
		}
		rs.close();
		return ds;
	}
	public ArrayList<String> getSdt(String hoten) throws Exception{

		String sql="Select Distinct SDT from KhachHang where HoTenKH=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1, hoten);

		
		ResultSet rs= cmd.executeQuery();
		ArrayList< String> tam=new ArrayList<String>();
		while(rs.next()) {
			String SDT=rs.getString("SDT");
			tam.add(SDT);
		}
		rs.close();
		return tam;
	}
	public String getMatuSdt(String sdt) throws Exception{

		String sql="Select  MaKH from KhachHang where SDT=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1, sdt);

		
		ResultSet rs= cmd.executeQuery();
		String MaKH = null;
		while(rs.next()) {
			 MaKH=rs.getString("MaKH");
		}
		rs.close();
		return MaKH;
	}
	public String getDiachituSdt(String sdt) throws Exception{

		String sql="Select  DiaChi from KhachHang where SDT=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1, sdt);
		ResultSet rs= cmd.executeQuery();
		String DiaChi = null;
		while(rs.next()) {
			 DiaChi=rs.getString("DiaChi");
		}
		rs.close();
		return DiaChi;
	}
	public int Them(String maKH, String hoTenKH, String diaChi,String sDT ) throws Exception{
		String sql="insert into KhachHang(MaKH, HoTenKH, DiaChi, SDT) values (?,?,?,?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, maKH);
		 cmd.setString(2,hoTenKH);
		 cmd.setString(3, diaChi);
		 cmd.setString(4, sDT);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int Sua(String maKH, String hoTenKH, String diaChi,String sDT ) throws Exception{
		String sql="update KhachHang set  HoTenKH=?, DiaChi=?, SDT=? where MaKH=?";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(4, maKH);
		 cmd.setString(1,hoTenKH);
		 cmd.setString(2, diaChi);
		 cmd.setString(3, sDT);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int Xoa(String maKH) throws Exception{
		String sql="delete from KhachHang where MaKH=?";
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1,maKH);
		return cmd.executeUpdate();
	}

	
}
