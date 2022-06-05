

package bo;

import java.util.ArrayList;

import bean.HangBean;
import bean.KhachHangbean;
import dao.HangDao;

public class HangBo {
	HangDao hdao=new HangDao();
	ArrayList<HangBean> ds=new ArrayList<HangBean>();
	public static ArrayList<HangBean> dsHD=new ArrayList<HangBean>();
	ArrayList<HangBean> dsTK=new ArrayList<HangBean>();
	public ArrayList<HangBean> getHang() throws Exception{
		ds=hdao.getHang();
		return ds;
		}
	public int KTMaHang(String Mahang) {
		for(HangBean hb:ds) {
			if(hb.getMaHang().equalsIgnoreCase(Mahang)) {
				return 0;
			}
				
		}
		return 1;
	}
	public String getLastMaHang(String PhanLoai) throws Exception{
		ArrayList< String> tam=new ArrayList<String>();
		tam=hdao.getLastMaHang(PhanLoai);
		
		String lastMahang="";
		if(tam.size()==0) {
			return "Dat Ma Hang";
		}
		else {
		for(String mahang: tam ) {
			lastMahang=mahang;
		}
		
		return lastMahang;
		}
	}
	public String tangMaHang(String lastMaHang ) throws Exception{
//		lastMaHang
		String MaSo = lastMaHang.toLowerCase().replaceAll("[a-z]*", "");
		String TenMa = lastMaHang.substring(0,lastMaHang.length()-MaSo.length());
		int soMaHang = Integer.parseInt(MaSo);
		soMaHang++;
		if(soMaHang < 10)
			return TenMa + "0"+String.valueOf(soMaHang);
		return TenMa + String.valueOf(soMaHang);
	}
	public ArrayList<HangBean> NhapHang(String TenHang,int soluong) throws Exception{
		for(HangBean hb : ds) {
			if(hb.getTenHang().equalsIgnoreCase(TenHang)) {
				hb.setSoLuongHangHienCo(soluong);
				hdao.NhhapHang(TenHang, soluong);
				return ds;
			}
		}
		return ds;
	}
	public ArrayList<HangBean> XoaHang(String maHang) throws Exception{
		for(HangBean hb :ds) {
			if(hb.getMaHang().equalsIgnoreCase(maHang)) {
				ds.remove(hb);
				hdao.XoaHang(maHang);
				return ds;
			}
		}
		return ds;
		
	}
	public ArrayList<HangBean> SuaHang(String TenHang,int soluong,double giatien,String mahang) throws Exception{
		for(HangBean hb : ds) {
			if(hb.getMaHang().equalsIgnoreCase(mahang)) {
				hb.setTenHang(TenHang);
				hb.setGiaBan(giatien);
				hb.setSoLuongHangHienCo(soluong);
				hdao.SuaHang(TenHang, soluong, giatien, mahang);
				return ds;
			}
		}
		return ds;
	}
	public ArrayList<HangBean> ThemHang(String maHang, String tenHang, String phanLoai, int soLuongHangHienCo, double giaBan) throws Exception{
		ds.add(new HangBean(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan));
		hdao.ThemHang(maHang, tenHang, phanLoai, soLuongHangHienCo, giaBan);
		return ds;
}
	public ArrayList<String> getPhanLoai() throws Exception{
		ArrayList< String> tam=new ArrayList<String>();
		tam=hdao.getPhanLoai();
		
		return tam;
		}
	public int getSoluonghang(String tenhang) throws Exception{
		int soluonghienco=hdao.getSoluonghang(tenhang);
		return soluonghienco;
	}
	public ArrayList<HangBean> getHangThem(String tenhang,int stt,int soluong) throws Exception{

		dsHD=hdao.getHangThem(tenhang, stt,soluong);
		return dsHD;
	}
	public ArrayList<HangBean> Them(String tenhang,int soluong) throws Exception{

		dsHD=hdao.Them(tenhang,soluong);
		return dsHD;
	}
	public ArrayList<HangBean> Sua(String tenhang,int soluong) throws Exception{
		for(HangBean hb:dsHD) {
			if(hb.getTenHang().equalsIgnoreCase(tenhang)) {
//				for(HangBean hb2:hdao.getHangtutenhang(tenhang,soluong)) {
//					hb.setStt(hb2.getStt());
//					hb.setMaHang(hb2.getMaHang());
//					hb.setTenHang(hb2.getTenHang());
//					hb.setPhanLoai(hb2.getPhanLoai());
//					hb.setGiaBan(hb2.getGiaBan());
//					hb.setSoLuongHangHienCo(hb2.getSoLuongHangHienCo());
//					hb.setSoluongban(hb2.getSoluongban());
//				}
				hb.setSoluongban(soluong);
				break;
			}
		}
		return dsHD;
	}
	public ArrayList<HangBean> Xoa(String maHang) throws Exception{
		for(HangBean hb:dsHD) {
			if(hb.getMaHang().equalsIgnoreCase(maHang)) {
				dsHD.remove(hb);
				break;
				}
		}
		return dsHD;
	}
	//nguyen van minh
	//2022-05-20
	public void BanHang(int soluong,String MaHang) throws Exception{
		for(HangBean hb:dsHD) {
			if(hb.getMaHang().equalsIgnoreCase(MaHang)) {
				hb.setSoLuongHangHienCo(soluong);
				hdao.BanHang(soluong, MaHang);
				break;
				}
		}
	}
	public ArrayList<HangBean> getHangForThongKe(String maHang,int soluongban) throws Exception{
		ArrayList< HangBean> tam=new ArrayList<HangBean>();
		for(HangBean hb:ds) {
			if(hb.getMaHang().equalsIgnoreCase(maHang)) {
				String MaHang=hb.getMaHang();
				String tenHang=hb.getTenHang();
				String phanLoai= hb.getPhanLoai();
				double giaBan=hb.getGiaBan();
				 hb.setSoluongban(soluongban);
				 tam.add(new HangBean(MaHang, tenHang, phanLoai, giaBan, soluongban));
			
				}
		}
		return tam;
	}
	public String getMaHangTuTenHang(String tenHang) throws Exception{
		for(HangBean hb : ds) {
			if(hb.getTenHang().equalsIgnoreCase(tenHang)) {
				return hb.getMaHang();
			}
		}
		return null;
	}
	public String getTenHanguMaHang(String mahang) throws Exception{
		for(HangBean hb : ds) {
			if(hb.getMaHang().equalsIgnoreCase(mahang)) {
				return hb.getTenHang();
			}
		}
		return null;
	}
}
