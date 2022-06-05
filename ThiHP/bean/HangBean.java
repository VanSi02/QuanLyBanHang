package bean;

public class HangBean {
	private String MaHang;
	private String TenHang;
	private String PhanLoai;
	private int SoLuongHangHienCo;
	private double GiaBan;
	private int stt;
	private int soluongban;
	public HangBean() {
		super();
	}
	public HangBean(String maHang, String tenHang, String phanLoai, int soLuongHangHienCo, double giaBan) {
		super();
		MaHang = maHang;
		TenHang = tenHang;
		PhanLoai = phanLoai;
		SoLuongHangHienCo = soLuongHangHienCo;
		GiaBan = giaBan;
	}
	public HangBean(String maHang, String tenHang, String phanLoai, int soLuongHangHienCo, double giaBan,	int soluongban) {
		super();
		MaHang = maHang;
		TenHang = tenHang;
		PhanLoai = phanLoai;
		SoLuongHangHienCo = soLuongHangHienCo;
		GiaBan = giaBan;
		this.soluongban = soluongban;
	}
	public HangBean(String maHang, String tenHang, String phanLoai, double giaBan, int soluongban) {
		super();
		MaHang = maHang;
		TenHang = tenHang;
		PhanLoai = phanLoai;
		GiaBan = giaBan;
		this.soluongban = soluongban;
	}
	public HangBean(String maHang, String tenHang, String phanLoai, int soLuongHangHienCo, double giaBan, int stt,int soluongban) {
		super();
		MaHang = maHang;
		TenHang = tenHang;
		PhanLoai = phanLoai;
		SoLuongHangHienCo = soLuongHangHienCo;
		GiaBan = giaBan;
		this.stt = stt;
		this.soluongban = soluongban;
	}
	public String getMaHang() {
		return MaHang;
	}
	public void setMaHang(String maHang) {
		MaHang = maHang;
	}
	public String getTenHang() {
		return TenHang;
	}
	public void setTenHang(String tenHang) {
		TenHang = tenHang;
	}
	public String getPhanLoai() {
		return PhanLoai;
	}
	public void setPhanLoai(String phanLoai) {
		PhanLoai = phanLoai;
	}
	public int getSoLuongHangHienCo() {
		return SoLuongHangHienCo;
	}
	public void setSoLuongHangHienCo(int soLuongHangHienCo) {
		SoLuongHangHienCo = soLuongHangHienCo;
	}
	public double getGiaBan() {
		return GiaBan;
	}
	public void setGiaBan(double giaBan) {
		GiaBan = giaBan;
	}
	public int getStt() {
		return stt;
	}
	public void setStt(int stt) {
		this.stt = stt;
	}
	public int getSoluongban() {
		return soluongban;
	}
	public void setSoluongban(int soluongban) {
		this.soluongban = soluongban;
	}
	
}
