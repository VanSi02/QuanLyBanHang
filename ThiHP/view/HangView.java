package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bean.HoaDonBean;
import bean.KhachHangbean;
import bo.HangBo;
import bo.HoaDonBo;
import bo.KhacHangBo;
import dao.Coso;
import dao.HangDao;
import dao.HoaDonDao;
import dao.KhachHangDao;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JEditorPane;
import javax.swing.JToggleButton;
import java.awt.Button;
import javax.swing.JButton;
import java.awt.Choice;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.TextField;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.Color;

public class HangView extends JFrame {
	private JPanel contentPane;
	private JTextField txtHD;
	public JTextField txttenkh;
	private JTextField txtmakh;
	private JTextField txtdiachi;
	private JTable table;
	public ArrayList<HangBean> dsHD=new ArrayList<HangBean>();
	/**
	 * Launch the application.
	 */
	//cac nut va textfiled
	KhacHangBo khbo=new KhacHangBo();
	Choice CLoaiHang = new Choice();
	Choice CChonhang = new Choice();
	Choice Csdt = new Choice();
	
	//goi class bo, dao
	HoaDonBo hdbo=new HoaDonBo();
	HangBo hbo=new HangBo();
	HangDao hdao=new HangDao();
	
	//cac bien can sd	
	double thanhtien=0;
	public static String maHD;
	ArrayList<HangBean> ds=new ArrayList<HangBean>();
	 
	int mahoadon=1;
	
	int stt=0;
	private JTextField txtthanhtien;
	//cac ham 
	void tangmahodon() {
		String mahd="HD0";
		mahoadon++;
		String sohd=Integer.toString(mahoadon);
		mahd=mahd.concat(sohd);
		txtHD.setText(mahd);
	}
	void NapBang(ArrayList<HangBean> dsHD) {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= 
			{"STT","Ma hang","Ten hang","Phan loai","Gia tien","So luong"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
		int i=1;
		for(HangBean hn: dsHD) {
			Object[] t= new Object[6];
			t[0]=i++;
//			t[0]=hn.getStt();
			t[1]=hn.getMaHang();
			t[2]=hn.getTenHang();
			t[3]=hn.getPhanLoai();
			t[4]=hn.getGiaBan();
			t[5]=hn.getSoluongban();
			mh.addRow(t);
		}
		
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HangView frame = new HangView();
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
	public HangView() {
		setBackground(SystemColor.info);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				try {
					Coso cs=new Coso();
					cs.KetNoi();
					ds=hbo.getHang();
					for(String hb: hbo.getPhanLoai()) {
						CLoaiHang.add(hb);
				}
					//tao ma hoa don
//					String mahd="HD0";
//					String sohd=Integer.toString(mahoadon);
//					mahd=mahd.concat(sohd);
					String mahd=hdbo.getHoaDonThem();
					txtHD.setText(mahd);
				}
				catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
			
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 807, 583);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(153, 204, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtHD = new JTextField();
		txtHD.setBounds(146, 72, 260, 19);
		contentPane.add(txtHD);
		txtHD.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("H\u1EC7 Th\u1ED1ng B\u00E1n H\u00E0ng");
		lblNewLabel.setBackground(new Color(95, 158, 160));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(234, 10, 415, 52);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ma Hoa Don");
		lblNewLabel_1.setBounds(20, 72, 79, 19);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nhap ten khach hang");
		lblNewLabel_2.setBounds(20, 114, 124, 13);
		contentPane.add(lblNewLabel_2);
		
		txttenkh = new JTextField();
		txttenkh.setBounds(143, 111, 263, 19);
		contentPane.add(txttenkh);
		txttenkh.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Ma khach hang");
		lblNewLabel_3.setBounds(20, 150, 113, 13);
		contentPane.add(lblNewLabel_3);
		
		txtmakh = new JTextField();
		txtmakh.setBounds(143, 147, 263, 19);
		contentPane.add(txtmakh);
		txtmakh.setColumns(10);
		
		JSpinner ssoluong = new JSpinner();
		ssoluong.setBounds(476, 238, 92, 31);
		contentPane.add(ssoluong);
		
		JButton btnNewButton = new JButton("Check");
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String hotenkh=txttenkh.getText();
					try {
						Csdt.removeAll();
						khbo.getKH();
						if(khbo.timtenKH(hotenkh)==false) {
							int kt=JOptionPane.showConfirmDialog(null, "khach hang chua dang ky , ban co muon them ?");
							if (kt == 0) {
								KhachHangView khview = new KhachHangView();
								khview.setVisible(true);
							}
						}
						if(khbo.timtenKH(hotenkh)==true) {
						for(String kh: khbo.getSdt(hotenkh)) {
							Csdt.add(kh);
						}
						}
//						else
//							JOptionPane.showMessageDialog(null,"Khach hang chua dang ky");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} 
					
			
		});
		btnNewButton.setBounds(428, 110, 85, 21);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_4 = new JLabel("Dia chi");
		lblNewLabel_4.setBounds(20, 193, 45, 13);
		contentPane.add(lblNewLabel_4);
		
		txtdiachi = new JTextField();
		txtdiachi.setBounds(144, 190, 262, 19);
		contentPane.add(txtdiachi);
		txtdiachi.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("SDT");
		lblNewLabel_5.setBounds(428, 150, 30, 13);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Loai Hang ");
		lblNewLabel_6.setBounds(20, 238, 98, 13);
		contentPane.add(lblNewLabel_6);
		CLoaiHang.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CChonhang.removeAll();
				String chon=CLoaiHang.getSelectedItem();
				for(HangBean hb:ds) {
					if(hb.getPhanLoai().equals(chon)) {
						CChonhang.add(hb.getTenHang());
					}
				}
			}
		});
		CLoaiHang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				CChonhang.removeAll();
				String chon=CLoaiHang.getSelectedItem();
				for(HangBean hb:ds) {
					if(hb.getPhanLoai().equals(chon)) {
						CChonhang.add(hb.getTenHang());
					}
				}
			}
		});
		
		
		CLoaiHang.setBounds(144, 238, 262, 18);
		contentPane.add(CLoaiHang);
		
		JLabel lblNewLabel_7 = new JLabel("Chon hang");
		lblNewLabel_7.setBounds(20, 278, 124, 13);
		contentPane.add(lblNewLabel_7);
		
		
		CChonhang.setBounds(146, 273, 260, 18);
		contentPane.add(CChonhang);
		
		JLabel lblNewLabel_8 = new JLabel("So luong");
		lblNewLabel_8.setBounds(407, 228, 77, 50);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("Them hang");
		btnNewButton_1.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
						String soluong=ssoluong.getValue().toString();
						int sl=Integer.parseInt(soluong);
						String tenhang=CChonhang.getSelectedItem();
						int soluonghienco=hbo.getSoluonghang(tenhang);
						if(sl <= soluonghienco) {
							stt=stt+1;		
							int so=(int) ssoluong.getValue();
							String ten=CChonhang.getSelectedItem();
							ArrayList<HangBean> tam=new ArrayList<HangBean>();
							tam.addAll(tam=hbo.Them(ten,so));
							thanhtien=0;
							for(HangBean hb:tam) {
								double gia=hb.getGiaBan();
								double soluongban=hb.getSoluongban();
								thanhtien=thanhtien+gia*soluongban;
								String tt=String.valueOf(thanhtien);
								txtthanhtien.setText(tt);
							}
							
							NapBang(tam);
						}
						else JOptionPane.showMessageDialog(null,"mat hang nay hien khong co du "+sl+" san pham");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
		btnNewButton_1.setBounds(625, 71, 157, 38);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_1_1 = new JButton("Sua Hang");
		btnNewButton_1_1.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d=table.getSelectedRow();
				String tenhang= table.getValueAt(d, 2).toString();
				 double giatien=(double) table.getValueAt(d, 4);
				 int soluong=(int) table.getValueAt(d, 5);
				try {
					thanhtien=thanhtien-giatien*soluong;
					String soluongban=ssoluong.getValue().toString();
					int sl=Integer.parseInt(soluongban);
					thanhtien=thanhtien+giatien*sl;
					String tt=String.valueOf(thanhtien);
					txtthanhtien.setText(tt);
					NapBang(hbo.Sua(tenhang,sl));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_1.setBounds(625, 129, 157, 37);
		contentPane.add(btnNewButton_1_1);
		
		JButton btnNewButton_1_2 = new JButton("Xoa hang");
		btnNewButton_1_2.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_1_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d=table.getSelectedRow();
				 String mahang=table.getValueAt(d, 1).toString();
				 double giatien=(double) table.getValueAt(d, 4);
				 int soluong=(int) table.getValueAt(d, 5);
				try {
					int i=JOptionPane.showConfirmDialog(null,"ban co chac chan muon xoa?");
					if(i==0){
					thanhtien=thanhtien-giatien*soluong;
					String tt=String.valueOf(thanhtien);
					txtthanhtien.setText(tt);
					NapBang(hbo.Xoa(mahang));
					}
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1_2.setBounds(625, 189, 157, 38);
		contentPane.add(btnNewButton_1_2);
		
		JButton btnNewButton_1_3 = new JButton("Thanh toan");
		btnNewButton_1_3.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_1_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				
				try {
				
//					SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
//					java.util.Date nb=  dd.parse(txtngayban.getText());
//					hdbo.Them(txtHD.getText(), txtmakh.getText());
					maHD=txtHD.getText();
					String tenkh=txtmakh.getText();
//					hdbo.themMaHD(txtHD.getText());
					
					HoaDonView hdview=new HoaDonView(maHD,tenkh,hbo.dsHD);
					hdview.setVisible(true);
					String mahd=hdbo.getHoaDonThem();
					txtHD.setText(mahd);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnNewButton_1_3.setBounds(625, 251, 157, 40);
		contentPane.add(btnNewButton_1_3);
		
		JButton btnNewButton_2 = new JButton("Kiem tra so luong");
		btnNewButton_2.setBackground(SystemColor.activeCaptionBorder);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String soluong=ssoluong.getValue().toString();
				int sl=Integer.parseInt(soluong);
				String tenhang=CChonhang.getSelectedItem();
				
				try {
					int soluonghienco=hbo.getSoluonghang(tenhang);
					if(sl <= soluonghienco) {
						JOptionPane.showMessageDialog(null,"okay");
					}
					else JOptionPane.showMessageDialog(null,"mat hang nay chi con "+soluonghienco+" san pham");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		btnNewButton_2.setBounds(417, 274, 151, 21);
		contentPane.add(btnNewButton_2);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(20, 332, 760, 204);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach hang", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		Csdt.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//			}
			@Override
			public void mousePressed(MouseEvent e) {
	try {
					
					String sdt=Csdt.getSelectedItem();
					String makh=khbo.getMatuSdt(sdt);
					txtmakh.setText(makh);
					String diachi=khbo.getDiachituSdt(sdt);
					txtdiachi.setText(diachi);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			
		});
		
		
			
		Csdt.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
					
					try {
						
						String sdt=Csdt.getSelectedItem();
						String makh=khbo.getMatuSdt(sdt);
						txtmakh.setText(makh);
						String diachi=khbo.getDiachituSdt(sdt);
						txtdiachi.setText(diachi);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
			}
		});
		
		
		Csdt.setBounds(462, 147, 127, 18);
		contentPane.add(Csdt);
		
		JLabel lblNewLabel_9 = new JLabel("thanh tien");
		lblNewLabel_9.setBounds(407, 313, 77, 21);
		contentPane.add(lblNewLabel_9);
		
		txtthanhtien = new JTextField();
		txtthanhtien.setBounds(472, 315, 96, 19);
		contentPane.add(txtthanhtien);
		txtthanhtien.setColumns(10);
	}
}
