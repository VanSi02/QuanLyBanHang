package bo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import bean.KhachHangbean;
import dao.Coso;
import dao.KhachHangDao;

public class KhacHangBo {
		KhachHangDao khdao=new KhachHangDao();
		ArrayList<KhachHangbean> ds=new ArrayList<KhachHangbean>();
		public ArrayList<KhachHangbean> getKH() throws Exception{
			ds=khdao.getKH();
			return ds;
			
		}
		public ArrayList<String> getSdt(String hoten) throws Exception{
			ArrayList<String>  tam= new ArrayList<String>();
			tam=khdao.getSdt(hoten);
			return tam;
		}
		public String getMatuSdt(String sdt) throws Exception{
			String tam=null;
			tam=khdao.getMatuSdt(sdt);
			return tam;
		}
		public String getDiachituSdt(String sdt) throws Exception{
			String tam=null;
			tam=khdao.getDiachituSdt(sdt);
			return tam;
		}
		public ArrayList<KhachHangbean> Them(String maKH, String hoTenKH, String diaChi, String sDT) throws Exception{
			
					ds.add(new KhachHangbean(maKH, hoTenKH, diaChi, sDT));
					khdao.Them(maKH, hoTenKH, diaChi, sDT);
					return ds;
			
		}
		public ArrayList<KhachHangbean> lastkh() throws Exception{
			ArrayList<KhachHangbean>  tam= new ArrayList<KhachHangbean>();
			int so=ds.size();
			int i=0;
			for(KhachHangbean kh:ds) {
				if(i==so-1) {
					tam.add(kh);
				}
				i++;
			}
			return tam;
			
		}
		public boolean timMaKH(String maKH) {
			
			for(KhachHangbean kh :ds) {
				if(kh.getMaKH().equalsIgnoreCase(maKH)) {
					return true;
				}	
		}
			return false;
		}
		public boolean timtenKH(String tenKH) {
			
			for(KhachHangbean kh :ds) {
				if(kh.getHoTenKH().equalsIgnoreCase(tenKH)) {
					return true;
				}	
		}
			return false;
		}
		public boolean timSDT(String sdt) {
			
			for(KhachHangbean kh :ds) {
				if(kh.getSDT().equalsIgnoreCase(sdt)) {
					return true;
				}	
		}
			return false;
		}
		public ArrayList<KhachHangbean> Sua(String maKH, String hoTenKH, String diaChi, String sDT) throws Exception{
			for(KhachHangbean kh :ds) {
				if(kh.getMaKH().equalsIgnoreCase(maKH)) {
					kh.setHoTenKH(hoTenKH);
					kh.setDiaChi(diaChi);
					kh.setSDT(sDT);
					khdao.Sua(maKH, hoTenKH, diaChi, sDT);
				}
			}
			return ds;
			
		}
		public ArrayList<KhachHangbean> Xoa(String maKH) throws Exception{
			for(KhachHangbean kh :ds) {
				if(kh.getMaKH().equalsIgnoreCase(maKH)) {
					ds.remove(kh);
					khdao.Xoa(maKH);
					break;
				}
			}
			return ds;
			
		}
		public ArrayList<KhachHangbean>TimKiem(String maKH) throws Exception{
			ArrayList<KhachHangbean>  tam= new ArrayList<KhachHangbean>();
			for(KhachHangbean kh :ds) {
				System.out.println(kh.getMaKH());
				if(kh.getMaKH().equalsIgnoreCase(maKH)) {
					tam.add(kh);
					return tam;
				}
				
			}
			return null;
			
		}
		public String getTenKHtuMaKH(String makh) throws Exception{
			for(KhachHangbean kh : ds) {
				if(kh.getMaKH().equalsIgnoreCase(makh))
					return kh.getHoTenKH();
			}
			return null;
		}
		
}
