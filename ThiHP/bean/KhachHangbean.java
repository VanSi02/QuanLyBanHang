package bean;

public class KhachHangbean {
	private String MaKH;
	private String HoTenKH;
	private String DiaChi;
	private String SDT;
	public KhachHangbean() {
		super();
	}
	public KhachHangbean(String maKH, String hoTenKH, String diaChi,String sDT) {
		super();
		MaKH = maKH;
		HoTenKH = hoTenKH;
		DiaChi = diaChi;
		SDT = sDT;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public String getHoTenKH() {
		return HoTenKH;
	}
	public void setHoTenKH(String hoTenKH) {
		HoTenKH = hoTenKH;
	}
	public String getDiaChi() {
		return DiaChi;
	}
	public void setDiaChi(String diaChi) {
		DiaChi = diaChi;
	}
	public String getSDT() {
		return SDT;
	}
	public void setSDT(String sDT) {
		SDT = sDT;
	}
	
}
