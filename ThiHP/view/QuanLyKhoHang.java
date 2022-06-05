package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bo.HangBo;
import dao.Coso;
import dao.HangDao;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Label;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyKhoHang extends JFrame {

	private JPanel contentPane;

	HangBo hbo=new HangBo();
	HangDao hdao=new HangDao();
	ArrayList<HangBean> ds=new ArrayList<HangBean>();
	/**
	 * Launch the application.
	 */
	Choice chonLoaiHang = new Choice();
	TextField txttenhang = new TextField();
	TextField txtSoLuongThem = new TextField();
	TextField txtDonGiaThem = new TextField();
	TextField txtTenHangThem = new TextField();
	TextField txtMaHangThem = new TextField();
	private JTable table;
	private JTextField txtTenHangSua;
	private JTextField txtGiaSua;
	private JTextField txtSoSua;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyKhoHang frame = new QuanLyKhoHang();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws Exception 
	 */
	void NapBang(String tenhang) throws Exception {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= {"STT","Ma hang","Ten hang","Phan loai","Gia tien","So luong"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
		int i=1;
		for(HangBean hn: ds) {
			
			Object[] t= new Object[6];
			if(hn.getTenHang().toLowerCase().contains(tenhang)) {
			t[0]=i++;
			t[1]=hn.getMaHang();
			t[2]=hn.getTenHang();
			t[3]=hn.getPhanLoai();
			t[4]=hn.getGiaBan();
			t[5]=hn.getSoLuongHangHienCo();
			mh.addRow(t);
		}
		}
		
	}
	
	void resetbang(ArrayList<HangBean> dsmh) throws Exception {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= {"STT","Ma hang","Ten hang","Phan loai","Gia tien","So luong"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
		int i=1;
		ds.removeAll(dsmh);
		System.out.println();
		String s;
		ds=hbo.getHang();
		for(HangBean hn: ds) {
			Object[] t= new Object[6];
			t[0]=i++;
			t[1]=hn.getMaHang();
			t[2]=hn.getTenHang();
			t[3]=hn.getPhanLoai();
			t[4]=hn.getGiaBan();
			t[5]=hn.getSoLuongHangHienCo();
			mh.addRow(t);
		}
		}
		
	
	public QuanLyKhoHang() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				
				try {
					Coso cs=new Coso();
					cs.KetNoi();
					ds=hbo.getHang();
					for(String hb: hbo.getPhanLoai()) {
						chonLoaiHang.add(hb);
				} 
					NapBang("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1065, 531);
		contentPane = new JPanel();
		contentPane.setBackground(Color.LIGHT_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(Color.WHITE);
		tabbedPane.setBounds(500, 231, 525, 127);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Danh sach hang", null, scrollPane, null);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d=table.getSelectedRow();
				String tenhang= table.getValueAt(d, 2).toString();
				int soluong=Integer.parseInt(table.getValueAt(d, 5).toString());
				double giatien=Double.parseDouble(table.getValueAt(d,4).toString());
				String slSua=String.valueOf(soluong);
				String giaSua=String.valueOf(giatien);
				System.out.println(slSua);
				txtTenHangSua.setText(tenhang);
				txtSoSua.setText(slSua);
				txtGiaSua.setText(giaSua);
			}
			
		});
		scrollPane.setViewportView(table);
		
		Button button_1 = new Button("Them Mat Hang");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(hbo.KTMaHang(txtMaHangThem.getText())==0) {
						JOptionPane.showMessageDialog(null, "Ma hang da ton tai");
					}
					else {
					String phanloai=chonLoaiHang.getSelectedItem();
					int soluong=Integer.parseInt(txtSoLuongThem.getText());
					double giaban=Double.parseDouble(txtDonGiaThem.getText());
					hbo.ThemHang(txtMaHangThem.getText(), txtTenHangThem.getText() ,phanloai, soluong,giaban);
//					table.removeAll();
					resetbang(ds);
					JOptionPane.showMessageDialog(null,"da them mat hang "+txtTenHangThem.getText());
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setFont(new Font("Dialog", Font.BOLD, 18));
		button_1.setBounds(138, 420, 220, 30);
		contentPane.add(button_1);
		
		Label label_1 = new Label("Them Mat Hang");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Dialog", Font.BOLD, 21));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(138, 104, 201, 45);
		contentPane.add(label_1);
		
		Label tieude = new Label("Quan Ly Kho");
		tieude.setForeground(Color.BLUE);
		tieude.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		tieude.setAlignment(Label.CENTER);
		tieude.setBounds(232, 10, 525, 45);
		contentPane.add(tieude);
		
		Label label = new Label("Phan Loai");
		label.setBackground(Color.WHITE);
		label.setBounds(55, 160, 70, 21);
		contentPane.add(label);
		
		Label label_2 = new Label("Ten Hang");
		label_2.setBackground(Color.WHITE);
		label_2.setForeground(Color.BLACK);
		label_2.setBounds(55, 266, 70, 21);
		contentPane.add(label_2);
		
		Label label_2_1 = new Label("Don Gia");
		label_2_1.setBackground(Color.WHITE);
		label_2_1.setForeground(Color.BLACK);
		label_2_1.setBounds(55, 322, 70, 21);
		contentPane.add(label_2_1);
		
		Label label_2_2 = new Label("So luong");
		label_2_2.setBackground(Color.WHITE);
		label_2_2.setForeground(Color.BLACK);
		label_2_2.setBounds(55, 380, 70, 21);
		contentPane.add(label_2_2);
		
		Label label_2_3 = new Label("Ma Hang");
		label_2_3.setForeground(Color.BLACK);
		label_2_3.setBackground(Color.WHITE);
		label_2_3.setBounds(55, 215, 70, 21);
		contentPane.add(label_2_3);
		chonLoaiHang.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				try {
					String key = chonLoaiHang.getSelectedItem();
					txtMaHangThem.setText(hbo.tangMaHang(hbo.getLastMaHang(key)));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		
		chonLoaiHang.setBounds(138, 163, 178, 18);
		contentPane.add(chonLoaiHang);
		
		Button button = new Button("Them loai hang");
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=JOptionPane.showInputDialog("Nhap loai hang can them");
				chonLoaiHang.add(key);
			}
		});
		button.setBounds(288, 160, 110, 21);
		contentPane.add(button);
	
		txtMaHangThem.setBounds(138, 215, 143, 21);
		contentPane.add(txtMaHangThem);
	
		txtTenHangThem.setBounds(138, 266, 143, 21);
		contentPane.add(txtTenHangThem);
		
		txtDonGiaThem.setBounds(138, 322, 143, 21);
		contentPane.add(txtDonGiaThem);
		
		txtSoLuongThem.setBounds(138, 380, 143, 21);
		contentPane.add(txtSoLuongThem);
		
		Label label_1_1 = new Label("Quan Ly Mat Hang Hien Co");
		label_1_1.setForeground(Color.RED);
		label_1_1.setFont(new Font("Dialog", Font.BOLD, 21));
		label_1_1.setBackground(Color.WHITE);
		label_1_1.setBounds(591, 104, 351, 45);
		contentPane.add(label_1_1);
		
		Label label_4 = new Label("Nhap Ten Hang");
		label_4.setBackground(Color.WHITE);
		label_4.setBounds(500, 160, 100, 21);
		contentPane.add(label_4);
		txttenhang.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					NapBang(txttenhang.getText().toLowerCase());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		txttenhang.setBounds(648, 160, 153, 21);
		contentPane.add(txttenhang);
		
		JButton btnSuaHang = new JButton("Sua Hang");
		btnSuaHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int d=table.getSelectedRow();
				String mahang= table.getValueAt(d, 1).toString();
				String tenHang=txtTenHangSua.getText();
				double giatien=Double.parseDouble( txtGiaSua.getText());
				int soluong=Integer.parseInt(txtSoSua.getText());
				try {
					ds=hbo.SuaHang(tenHang, soluong, giatien, mahang);
					NapBang("");
					JOptionPane.showMessageDialog(null, "da cap nhat lai mat hang "+tenHang);
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSuaHang.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnSuaHang.setBackground(Color.LIGHT_GRAY);
		btnSuaHang.setBounds(682, 380, 110, 45);
		contentPane.add(btnSuaHang);
		
		JButton btnXoaHang = new JButton("Xoa Hang");
		btnXoaHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int d=table.getSelectedRow();
					String mahang= table.getValueAt(d, 1).toString();
					String tenHang=txtTenHangSua.getText();
					
					int kt=JOptionPane.showConfirmDialog(null,"ban co chac chan muon xoa mat hang "+tenHang+" ?");
					if(kt==0) {
						ds=hbo.XoaHang(mahang);
//						NapBang(tenHang);
						resetbang(ds);
					JOptionPane.showMessageDialog(null, "Da xoa mat hang "+tenHang);
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
			}
		});
		btnXoaHang.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnXoaHang.setBackground(Color.LIGHT_GRAY);
		btnXoaHang.setBounds(861, 380, 110, 45);
		contentPane.add(btnXoaHang);
		
		JButton btnNhapHang = new JButton("Nhap Hang");
		btnNhapHang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					int d=table.getSelectedRow();
					String tenhang= table.getValueAt(d, 2).toString();
					int hangthem=Integer.parseInt(JOptionPane.showInputDialog("nhap so hang can nhap"));
					int soluong=Integer.parseInt(table.getValueAt(d, 5).toString());
					int SoLuongSauThem=hangthem+soluong;
					ds=hbo.NhapHang(tenhang, SoLuongSauThem);
					NapBang("");
					JOptionPane.showMessageDialog(null, "Nhap them hang thanh cong");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNhapHang.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnNhapHang.setBackground(Color.LIGHT_GRAY);
		btnNhapHang.setBounds(513, 380, 110, 45);
		contentPane.add(btnNhapHang);
		
		Label khungthemhang = new Label("");
		khungthemhang.setBackground(Color.WHITE);
		khungthemhang.setBounds(45, 89, 401, 378);
		contentPane.add(khungthemhang);
		
		txtTenHangSua = new JTextField();
		txtTenHangSua.setBounds(527, 204, 96, 19);
		contentPane.add(txtTenHangSua);
		txtTenHangSua.setColumns(10);
		
		txtGiaSua = new JTextField();
		txtGiaSua.setBounds(661, 204, 96, 19);
		contentPane.add(txtGiaSua);
		txtGiaSua.setColumns(10);
		
		txtSoSua = new JTextField();
		txtSoSua.setBounds(808, 202, 96, 19);
		contentPane.add(txtSoSua);
		txtSoSua.setColumns(10);
		
		Label label_3 = new Label("");
		label_3.setBackground(Color.WHITE);
		label_3.setBounds(465, 89, 576, 378);
		contentPane.add(label_3);
	}
}
