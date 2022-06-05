package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import bean.HangBean;


public class HangDao {
	public ArrayList<HangBean> ds=new ArrayList<HangBean>();
	public static ArrayList<HangBean> dsHD=new ArrayList<HangBean>();
	public ArrayList<HangBean> getHang() throws Exception{
	
		String sql="Select * from Hang";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		
		while(rs.next()) {
			String maHang=rs.getString("MaHang");
			String tenHang=rs.getString("TenHang");
			String phanLoai=rs.getString("PhanLoai");
			int soLuongHangHienCo=rs.getInt("SoLuongHangHienCo");
			double giaBan=rs.getDouble("GiaBan");
			HangBean Hang=new HangBean(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan);
			ds.add(Hang);
		}
		rs.close();
		return ds;
	}
	public int ThemHang(String maHang, String tenHang, String phanLoai, int soLuongHangHienCo, double giaBan) throws Exception{
		String sql="insert into Hang(MaHang,TenHang,PhanLoai,SoLuongHangHienCo,GiaBan) values (?,?,?,?,?)";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1, maHang);
		 cmd.setString(2,tenHang);
		 cmd.setString(3, phanLoai);
		 cmd.setInt(4, soLuongHangHienCo);
		 cmd.setDouble(5, giaBan);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public ArrayList<String> getPhanLoai() throws Exception{

		String sql="Select Distinct PhanLoai from Hang";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		ResultSet rs= cmd.executeQuery();
		ArrayList< String> tam=new ArrayList<String>();
		while(rs.next()) {
			String phanLoai=rs.getString("PhanLoai");
			tam.add(phanLoai);
		}
		rs.close();
		return tam;
	}
	public ArrayList<String> getLastMaHang(String PhanLoai) throws Exception{
		String sql="Select MaHang from Hang where PhanLoai=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		//HD_01
		cmd.setString(1, PhanLoai);
		ResultSet rs= cmd.executeQuery();
		ArrayList< String> tam=new ArrayList<String>();
		while(rs.next()) {
			String MaHang=rs.getString("MaHang");
			tam.add(MaHang);
		}
		rs.close();
		return tam;
	}
	public ArrayList<HangBean> getHangtutenhang(String tenhang,int soluong) throws Exception{

		String sql="Select * from Hang where TenHang=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1, tenhang);
		ResultSet rs= cmd.executeQuery();
		ArrayList<HangBean> tam=new ArrayList<HangBean>();
		while(rs.next()) {
			String maHang=rs.getString("MaHang");
			String tenHang=rs.getString("TenHang");
			String phanLoai=rs.getString("PhanLoai");
			int soLuongHangHienCo=rs.getInt("SoLuongHangHienCo");
			double giaBan=rs.getDouble("GiaBan");
			HangBean hb=new HangBean(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan, soluong);
			tam.add(hb);
		}
		rs.close();
		return tam;
	}
	public ArrayList<HangBean> getHangThem(String tenhang,int stt,int soluong) throws Exception{

		String sql="Select * from Hang where TenHang=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1,tenhang);
		ResultSet rs= cmd.executeQuery();
		ArrayList<HangBean> tam=new ArrayList<HangBean>();
		while(rs.next()) {
			String maHang=rs.getString("MaHang");
			String tenHang=rs.getString("TenHang");
			String phanLoai=rs.getString("PhanLoai");
			int soLuongHangHienCo=rs.getInt("SoLuongHangHienCo");
			double giaBan=rs.getDouble("GiaBan");
			HangBean Hang=new HangBean(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan, stt, soluong);
			dsHD.add(Hang);
		}
		rs.close();
		return dsHD;
	}
	public ArrayList<HangBean> Them(String tenhang,int soluong) throws Exception{

		String sql="Select * from Hang where TenHang=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1,tenhang);
		ResultSet rs= cmd.executeQuery();
		ArrayList<HangBean> tam=new ArrayList<HangBean>();
		while(rs.next()) {
			String maHang=rs.getString("MaHang");
			String tenHang=rs.getString("TenHang");
			String phanLoai=rs.getString("PhanLoai");
			int soLuongHangHienCo=rs.getInt("SoLuongHangHienCo");
			double giaBan=rs.getDouble("GiaBan");
			HangBean Hang=new HangBean(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan, soluong);
			dsHD.add(Hang);
		}
		rs.close();
		return dsHD;
	}
	public int NhhapHang(String TenHang, int soluong) throws Exception{
		String sql="update Hang set  SoLuongHangHienCo=? where TenHang=?";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(2,TenHang);
		 cmd.setInt(1, soluong);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int SuaHang(String TenHang, int soluong,double giatien,String MaHang) throws Exception{
		String sql="update Hang set TenHang=?,SoLuongHangHienCo=?,GiaBan=? where MaHang=?";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1,TenHang);
		 cmd.setInt(2, soluong);
		 cmd.setDouble(3, giatien);
		 cmd.setString(4, MaHang);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int BanHang( int soluong,String MaHang) throws Exception{
		String sql="update Hang set SoLuongHangHienCo=? where MaHang=?";
		//b2: Tao ra 1 prepareStatement
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setInt(1, soluong);
		 cmd.setString(2, MaHang);
		 //b3: Thuc hien cau lenh
		return cmd.executeUpdate();
	}
	public int XoaHang(String maHang) throws Exception{
		String sql="delete from Hang where MaHang=?";
		 PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		 cmd.setString(1,maHang);
		return cmd.executeUpdate();
	}
	public int getSoluonghang(String tenhang) throws Exception{
		String sql="Select SoLuongHangHienCo from Hang where TenHang=?";
		PreparedStatement cmd= Coso.cn.prepareStatement(sql);
		cmd.setString(1,tenhang);
		ResultSet rs= cmd.executeQuery();
		
		int soluonghienco=0;
		while(rs.next()) {
			String soluong=rs.getString("SoLuongHangHienCo");
			soluonghienco=Integer.parseInt(soluong);
		}
		rs.close();
		return soluonghienco;
	}
	
}
