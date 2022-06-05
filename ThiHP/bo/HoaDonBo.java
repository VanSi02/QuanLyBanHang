package bo;

import java.lang.reflect.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import bean.HoaDonBean;
import bean.KhachHangbean;
import dao.Coso;
import dao.HoaDonDao;

public class HoaDonBo {
	HoaDonDao hddao=new HoaDonDao();
	public static ArrayList<HoaDonBean> dsHD=new ArrayList<HoaDonBean>();
	public  ArrayList<HoaDonBean> ds=new ArrayList<HoaDonBean>();
	public ArrayList<HoaDonBean> getHD() throws Exception{

		ds=hddao.getHD();
		return ds;
	}
	public String getHoaDonThem() throws Exception {
		String mahd="";
		if(hddao.getmaHD().size()==0) {
			return "HD01"; 
		}
		else {
		for(String hd: hddao.getmaHD()) {
			mahd=hd;
		}
		String MaHD=TangMaHD(mahd);
		return MaHD;
		}
	}
	public String getLastHoaDon() throws Exception {
		String mahd="";
		for(String hd: hddao.getmaHD()) {
			mahd=hd;
		}
		return mahd;
		}
	public String TangMaHD(String mahd) throws Exception{
		//HD12
		String[] abc=mahd.split("D");
		String tenhd=abc[0];
		String masohd=abc[1];
		int sohd=Integer.parseInt(masohd);
		sohd++;
		String MaHoaDon=String.valueOf(sohd);
		if(MaHoaDon.length()<2) {
			
			return "HD0"+MaHoaDon;
		}
		else return "HD"+MaHoaDon;
	}
	public ArrayList<HoaDonBean> Them(String MaHD, String MaKH,Date ngayban) throws Exception{
//		SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
//		String nb=dd.format(ngayban);
//		java.sql.Date ngaybansql=java.sql.Date.valueOf(nb) ;
		dsHD.add(new HoaDonBean(MaHD, MaKH));
		hddao.Them(MaHD, MaKH,ngayban);
		return dsHD;

}
	public void Thanhtoan(String MaHD, String MaKH,Date ngayban) throws Exception {
//		ds.add(new HoaDonBean(MaHD, MaKH, ngayban));
		for(HoaDonBean hdb : ds ) {
			if(hdb.getMaHD().equalsIgnoreCase(MaHD)) {
				hdb.setMaHD(MaHD);
				hdb.setMaKH(MaKH);
				hdb.setNgayLapHD(ngayban);
				hddao.Thanhtoan(MaHD, MaKH, ngayban);
				break;
			}
		}
		
	}
	public void themMaHD(String mahd) throws Exception {
		hddao.ThemMAHD(mahd);
	}
	public ArrayList<HoaDonBean> get1HoaDon(String MaHd){
		ArrayList<HoaDonBean> tam= new ArrayList<HoaDonBean>();
		for(HoaDonBean hdbean:ds) {
			if(hdbean.getMaHD().equalsIgnoreCase(MaHd)) {
				tam.add(new HoaDonBean(hdbean.getMaHD(),hdbean.getMaKH(),hdbean.getNgayLapHD()));
				return tam;
			}
		}
		return tam;
		}
}
