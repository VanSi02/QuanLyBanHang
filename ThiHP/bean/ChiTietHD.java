package bean;

public class ChiTietHD {
	private String MaHD;
	private String MaHang;
	private int SoLuongBan;
	private String MaKH;
	public ChiTietHD() {
		super();
	}
	public ChiTietHD(String maHD, String maHang, int soLuongBan,String MaKh) {
		super();
		MaHD = maHD;
		MaHang = maHang;
		SoLuongBan = soLuongBan;
		MaKH=MaKh;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getMaHang() {
		return MaHang;
	}
	public void setMaHang(String maHang) {
		MaHang = maHang;
	}
	public int getSoLuongBan() {
		return SoLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		SoLuongBan = soLuongBan;
	}
	
}
