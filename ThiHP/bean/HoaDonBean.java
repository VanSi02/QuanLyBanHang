package bean;

import java.util.Date;

public class HoaDonBean {
	public HoaDonBean(String maHD, String maKH) {
		super();
		MaHD = maHD;
		MaKH = maKH;
	}
	private String MaHD;
	private String MaKH;
	private Date NgayLapHD;
	public HoaDonBean() {
		super();
	}
	public HoaDonBean(String maHD, String maKH, Date ngayLapHD) {
		super();
		MaHD = maHD;
		MaKH = maKH;
		NgayLapHD = ngayLapHD;
	}
	public String getMaHD() {
		return MaHD;
	}
	public void setMaHD(String maHD) {
		MaHD = maHD;
	}
	public String getMaKH() {
		return MaKH;
	}
	public void setMaKH(String maKH) {
		MaKH = maKH;
	}
	public Date getNgayLapHD() {
		return NgayLapHD;
	}
	public void setNgayLapHD(Date ngayLapHD) {
		NgayLapHD = ngayLapHD;
	}
}
