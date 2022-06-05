package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.ChiTietHD;
import bean.HoaDonBean;
import dao.ChiTietHDDao;
import dao.Coso;

public class ChiTietHDBo {
	ChiTietHDDao cthdDao=new ChiTietHDDao();
	public ArrayList<ChiTietHD> ds=new ArrayList<ChiTietHD>();
	public ArrayList<ChiTietHD> dshd=new ArrayList<ChiTietHD>();
	public ArrayList<ChiTietHD> getChiTietHoaDon() throws Exception{
		ds=cthdDao.getChiTietHoaDon();
		return ds;
	}
	public ArrayList<ChiTietHD> them(String MaHD, String MaHang, int soLuongban,String MaKH) throws Exception{
		ds.add(new ChiTietHD(MaHD, MaHang, soLuongban, MaKH));
		try {
			cthdDao.ThemCTHD(MaHD, MaHang, soLuongban, MaKH);
		} catch (Exception e) {
				e.printStackTrace();
		}
		return ds;
	}
	public void themMaHD(String mahd) throws Exception {
			cthdDao.ThemMAHD(mahd);
	}
	public ArrayList<ChiTietHD> getAllTuMaHD(String MaHD){
		ArrayList<ChiTietHD> tam= new ArrayList<ChiTietHD>();
		for(ChiTietHD cthd: ds) {
			if(cthd.getMaHD().equalsIgnoreCase(MaHD)) {
				String mahd = cthd.getMaHD();
				String makh = cthd.getMaKH();
				String mahang = cthd.getMaHang();
				int soluongban = cthd.getSoLuongBan();
				tam.add(new ChiTietHD(mahd,mahang, soluongban, makh));
				}
		}
		return tam;
	}
	public ArrayList<String> getMaHDforThongKe(String maHang) throws Exception{
		ArrayList<String> tam= new ArrayList<String>();
		for(ChiTietHD cthd : ds) {
			if(cthd.getMaHang().equalsIgnoreCase(maHang))
				tam.add(cthd.getMaHD());
		}
		return tam;
	}
	public String getbestKH() throws Exception{
		return cthdDao.getbestKH();
	}
	public String getbestHang() throws Exception{
		return cthdDao.getbestHang();
	}
	}
