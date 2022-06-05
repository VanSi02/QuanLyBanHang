package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.ChiTietHD;
import bean.HangBean;
import bean.HoaDonBean;
import bo.ChiTietHDBo;
import bo.HangBo;
import bo.HoaDonBo;
import bo.KhacHangBo;
import dao.Coso;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.TextField;
import java.awt.Label;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Choice;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ThongKe extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 */
	HoaDonBo hdbo= new HoaDonBo();
	HangBo hbo= new HangBo();
	ChiTietHDBo ctHDbo = new ChiTietHDBo();
	KhacHangBo khbo = new KhacHangBo();
	ArrayList<HangBean> dsHang = new ArrayList<HangBean>();
	ArrayList<HoaDonBean> dsHD = new ArrayList<HoaDonBean>();
	ArrayList<ChiTietHD> dsCTHD = new ArrayList<ChiTietHD>();
	Label lbTongDoangthu = new Label("");
	Label lbTenhang = new Label("");
	TextField txtThanhTien = new TextField();
	Label lbTKnoibat = new Label("");
	Label lbBestHang = new Label("");
	Label lbTenKH = new Label("");
	Label lbBestKH = new Label("");
	Choice chonHD = new Choice();
	Choice chonPhanLoai = new Choice();
	Choice chonTenhang = new Choice();
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThongKe frame = new ThongKe();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	void NapBang(ArrayList<HangBean> ds) {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= 
			{"STT","Ma hang","Ten hang","Phan loai","Gia tien","So luong"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
		table.removeAll();
		int i=1;
		double tongtien=0;
		for(HangBean hn: ds) {
			Object[] t= new Object[6];
			t[0]=i++;
			t[1]=hn.getMaHang();
			t[2]=hn.getTenHang();
			t[3]=hn.getPhanLoai();
			t[4]=hn.getGiaBan();
			t[5]=hn.getSoluongban();
			mh.addRow(t);
			tongtien = hn.getGiaBan() * hn.getSoluongban();
		}
		txtThanhTien.setText("Tong tien : "+String.valueOf(tongtien));
	}
	void NapBangMatHang(ArrayList<String > dshd , String mahang) {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= 
			{"STT","Ngay ban","So luong","Gia tien","Thanh tien"};
		mh.setColumnIdentifiers(tieude);
		table_1.setModel(mh);
		table_1.removeAll();
		int i=1;
		Object[] t= new Object[5];
		double thanhtien=0;
		int soluong=0;
		double tongtien=0;
		txtThanhTien.setText("");
		for(String HD: dshd) {
			t[0]=i++;
		for(ChiTietHD cthd : dsCTHD) {
			if(cthd.getMaHD().equalsIgnoreCase(HD)) {
				if(cthd.getMaHang().equalsIgnoreCase(mahang))
				t[2]=cthd.getSoLuongBan();
				soluong=cthd.getSoLuongBan();
			}
		}
		for(HoaDonBean hdbean : dsHD) {
			if(hdbean.getMaHD().equalsIgnoreCase(HD)) {
				t[1]=hdbean.getNgayLapHD();
			}
		}
		for(HangBean hn: dsHang) {
			if(hn.getMaHang().equalsIgnoreCase(mahang)) {
			t[3]=hn.getGiaBan();
			thanhtien=soluong*hn.getGiaBan();
			t[4]=thanhtien;
			tongtien+=thanhtien;
			}
		}
		
		mh.addRow(t);
		
		txtThanhTien.setText("Tong tien : "+String.valueOf(tongtien));
		}
	}
	public ThongKe() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Coso cs=new Coso();
				cs.KetNoi();
				try {
					dsCTHD = ctHDbo.getChiTietHoaDon();
					dsHD=hdbo.getHD();
					dsHang= hbo.getHang();
					khbo.getKH();
					lbBestHang.setText("Mat hang ban chay nhat :");
					lbTenhang.setText(""+hbo.getTenHanguMaHang(ctHDbo.getbestHang()));
					lbBestKH.setText("Khach Hang mua nhieu nhat :");
					lbTenKH.setText(""+khbo.getTenKHtuMaKH(ctHDbo.getbestKH()).toUpperCase());
					for(String hb: hbo.getPhanLoai()) {
						chonPhanLoai.add(hb);
					}
					double tongdoanhthu=0;
					for(ChiTietHD cthd : dsCTHD) {
						for(HangBean hb : dsHang) {
							if(hb.getMaHang().equalsIgnoreCase(cthd.getMaHang())) {
								tongdoanhthu+=cthd.getSoLuongBan()*hb.getGiaBan();
							}
						}
					}
					lbTongDoangthu.setText(String.valueOf("Tong doanh thu : "+tongdoanhthu));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				for(HoaDonBean hdbean : dsHD) {
					chonHD.add(hdbean.getMaHD());
				}
				try {
//					NapBang(hbo.getHangForThongKe("BK01", 1));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 678, 525);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(41, 198, 586, 140);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Hoa Don", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
			}
		});
		tabbedPane.addTab("Mat Hang", null, scrollPane_1, null);
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		scrollPane_1.setViewportView(table_1);
		
		TextField txtMAKH = new TextField();
		txtMAKH.setBounds(48, 116, 81, 21);
		contentPane.add(txtMAKH);
		
		TextField txtNgayLapHD = new TextField();
		txtNgayLapHD.setBounds(48, 154, 108, 21);
		contentPane.add(txtNgayLapHD);
		chonHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<HoaDonBean> tam= new ArrayList<HoaDonBean>();
				ArrayList<ChiTietHD> tamcthd= new ArrayList<ChiTietHD>();
				ArrayList<HangBean> tamhang= new ArrayList<HangBean>();
				String key = chonHD.getSelectedItem();
				tam=hdbo.get1HoaDon(key);
				tamcthd=ctHDbo.getAllTuMaHD(key);
				for(HoaDonBean hdbean : tam) {
					txtMAKH.setText(hdbean.getMaKH());
					SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
					String ngaylapHD=dd.format(hdbean.getNgayLapHD());
					txtNgayLapHD.setText(ngaylapHD);
					for(ChiTietHD cthdbean : tamcthd) {
						try {
							tamhang.addAll(hbo.getHangForThongKe(cthdbean.getMaHang(), cthdbean.getSoLuongBan()));
						}catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				NapBang(tamhang);
				tamhang.removeAll(tamhang);
			}
		});
		
		
		chonHD.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ArrayList<HoaDonBean> tam= new ArrayList<HoaDonBean>();
				ArrayList<ChiTietHD> tamcthd= new ArrayList<ChiTietHD>();
				ArrayList<HangBean> tamhang= new ArrayList<HangBean>();
				String key = chonHD.getSelectedItem();
				tam=hdbo.get1HoaDon(key);
				tamcthd=ctHDbo.getAllTuMaHD(key);
				for(HoaDonBean hdbean : tam) {
					txtMAKH.setText(hdbean.getMaKH());
					SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
					String ngaylapHD=dd.format(hdbean.getNgayLapHD());
					txtNgayLapHD.setText(ngaylapHD);
					for(ChiTietHD cthdbean : tamcthd) {
						try {
							tamhang.addAll(hbo.getHangForThongKe(cthdbean.getMaHang(), cthdbean.getSoLuongBan()));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					
					}
				NapBang(tamhang);
				tamhang.removeAll(tamhang);
			}
		});
		chonHD.setBounds(48, 77, 95, 18);
		contentPane.add(chonHD);
		
		Label label_3 = new Label("THONG KE HOA DON");
		label_3.setBackground(SystemColor.menu);
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3.setBounds(66, 37, 160, 21);
		contentPane.add(label_3);
		
		Label label = new Label("");
		label.setBackground(SystemColor.menu);
		label.setBounds(41, 30, 185, 157);
		contentPane.add(label);
		
		
		txtThanhTien.setBackground(Color.LIGHT_GRAY);
		txtThanhTien.setBounds(423, 367, 185, 21);
		contentPane.add(txtThanhTien);
		
		Label label_3_1 = new Label("THONG KE MAT HANG");
		label_3_1.setForeground(Color.RED);
		label_3_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3_1.setBackground(SystemColor.menu);
		label_3_1.setBounds(247, 37, 169, 21);
		contentPane.add(label_3_1);
		chonTenhang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				ArrayList<String> tam= new ArrayList<String>();
				String tenhang = chonTenhang.getSelectedItem();
				try {
					String mahang = hbo.getMaHangTuTenHang(tenhang);
					tam = ctHDbo.getMaHDforThongKe(mahang);
					System.out.println(mahang);
					NapBangMatHang(tam, mahang);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		chonTenhang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<String> tam= new ArrayList<String>();
				String tenhang = chonTenhang.getSelectedItem();
				try {
					String mahang = hbo.getMaHangTuTenHang(tenhang);
					tam = ctHDbo.getMaHDforThongKe(mahang);
					System.out.println(mahang);
					NapBangMatHang(tam, mahang);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
			
			
		});
		chonPhanLoai.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				chonTenhang.removeAll();
				String chon=chonPhanLoai.getSelectedItem();
				for(HangBean hb:dsHang) {
					if(hb.getPhanLoai().equals(chon)) {
						chonTenhang.add(hb.getTenHang());
					}
				}
			}
		});
		chonPhanLoai.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				
				chonTenhang.removeAll();
				String chon=chonPhanLoai.getSelectedItem();
				for(HangBean hb:dsHang) {
					if(hb.getPhanLoai().equals(chon)) {
						chonTenhang.add(hb.getTenHang());
					}
				}
			
			}});
		
		
		chonPhanLoai.setBounds(247, 116, 135, 18);
		contentPane.add(chonPhanLoai);
	
		chonTenhang.setBounds(247, 154, 135, 18);
		contentPane.add(chonTenhang);
		
		Label label_4 = new Label("Chon hang de thao tac");
		label_4.setBackground(SystemColor.menu);
		label_4.setBounds(247, 74, 149, 21);
		contentPane.add(label_4);
		
		Label label_1 = new Label("");
		label_1.setBackground(SystemColor.menu);
		label_1.setBounds(232, 30, 185, 157);
		contentPane.add(label_1);
		
		Label label_3_1_1 = new Label("THONG KE NOI BAT");
		label_3_1_1.setForeground(Color.RED);
		label_3_1_1.setFont(new Font("Dialog", Font.BOLD, 12));
		label_3_1_1.setBackground(SystemColor.info);
		label_3_1_1.setBounds(439, 37, 169, 21);
		contentPane.add(label_3_1_1);
		lbBestKH.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		lbBestKH.setBackground(SystemColor.info);
		lbBestKH.setBounds(423, 74, 204, 21);
		contentPane.add(lbBestKH);
		lbBestHang.setFont(new Font("Dialog", Font.BOLD, 12));
		
		lbBestHang.setBackground(SystemColor.info);
		lbBestHang.setBounds(423, 116, 204, 21);
		contentPane.add(lbBestHang);
		lbTenKH.setFont(new Font("Dialog", Font.BOLD, 12));
		
		
		lbTenKH.setBackground(SystemColor.info);
		lbTenKH.setBounds(423, 89, 204, 21);
		contentPane.add(lbTenKH);
		
		lbTenhang.setFont(new Font("Dialog", Font.BOLD, 12));
		lbTenhang.setBackground(SystemColor.info);
		lbTenhang.setBounds(423, 138, 204, 22);
		contentPane.add(lbTenhang);
		
		
		lbTongDoangthu.setFont(new Font("Dialog", Font.BOLD, 12));
		lbTongDoangthu.setBackground(SystemColor.info);
		lbTongDoangthu.setBounds(423, 166, 204, 21);
		contentPane.add(lbTongDoangthu);
		
		
		lbTKnoibat.setBackground(SystemColor.info);
		lbTKnoibat.setBounds(423, 30, 204, 157);
		contentPane.add(lbTKnoibat);
	}
}
