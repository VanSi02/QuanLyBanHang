package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.ChiTietHD;
import bean.HangBean;
import bo.ChiTietHDBo;
import bo.HangBo;
import bo.HoaDonBo;
import dao.Coso;
import dao.HangDao;
import dao.HoaDonDao;

import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Font;
import java.awt.Color;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HoaDonView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	HangView hang=new HangView();
	HoaDonBo hdbo= new HoaDonBo();
	HangBo hangbo=new HangBo();
	ChiTietHDBo cthdBo=new ChiTietHDBo();
	JLabel lbTongtien = new JLabel("");
	public static ArrayList<HangBean> ds=new ArrayList<HangBean>();
	void NapBang(ArrayList<HangBean> ds) {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= 
			{"STT","Ma hang","Ten hang","Phan loai","Gia tien","So luong"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
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
			tongtien+=hn.getSoluongban()*hn.getGiaBan();
		}
		lbTongtien.setText("Tong tien :"+String.valueOf(tongtien));
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HoaDonView frame = new HoaDonView("mahd","makh",ds);
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
	
	private JTable table;
	private JTextField txtmahd;
	private JTextField txtmakh;
	private JTextField txtngaymua;
	private Label label_2;
	private Label label_3;
	
	public HoaDonView(String maHD, String makh, ArrayList<HangBean> dsHD) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Coso cs=new Coso();
				cs.KetNoi();
				txtmahd.setText(maHD);
				txtmakh.setText(makh);
				try {
					hdbo.getHD();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				for(HangBean hb:dsHD) {
//					System.out.println(hb);
//				}
					NapBang(HangDao.dsHD);
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 734, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(72, 154, 592, 181);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("Chi tiet hoa don", null, scrollPane, null);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		txtmahd = new JTextField();
		txtmahd.setBounds(195, 65, 136, 19);
		contentPane.add(txtmahd);
		txtmahd.setColumns(10);
		
		txtmakh = new JTextField();
		txtmakh.setBounds(195, 96, 136, 19);
		contentPane.add(txtmakh);
		txtmakh.setColumns(10);
		
		txtngaymua = new JTextField();
		txtngaymua.setBounds(195, 125, 236, 19);
		contentPane.add(txtngaymua);
		txtngaymua.setColumns(10);
		
		Label label = new Label("MaHD");
		label.setBounds(81, 65, 59, 21);
		contentPane.add(label);
		
		Label label_1 = new Label("MaKH");
		label_1.setBounds(81, 96, 59, 21);
		contentPane.add(label_1);
		
		label_2 = new Label("Ngay mua");
		label_2.setBounds(81, 125, 71, 21);
		contentPane.add(label_2);
		
		label_3 = new Label("Chi Tiet Hoa Don");
		label_3.setForeground(Color.BLUE);
		label_3.setFont(new Font("Dialog", Font.BOLD, 20));
		label_3.setAlignment(Label.CENTER);
		label_3.setBounds(180, 10, 302, 33);
		contentPane.add(label_3);
		
		Button button = new Button("Xac nhan thanh toan");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
//					int d =table.getSelectedRow();
//					String tenhang=table.getValueAt(d, 2).toString();
//					int soluongmua=(int) table.getValueAt(d, 5);
					SimpleDateFormat dd= new SimpleDateFormat("yyyy-MM-dd");
					java.util.Date nb=  dd.parse(txtngaymua.getText());
					for(HangBean hb: dsHD) {
						System.out.println(hb.getMaHang());
						int soluonghangsaumua=hb.getSoLuongHangHienCo()-hb.getSoluongban();
						hangbo.BanHang(soluonghangsaumua, hb.getMaHang());
//						cthdBo.themMaHD(maHD);
					}
					hdbo.Them(maHD, makh,nb);
//					hdbo.Them(txtmahd.getText(),txtmakh.getText(),nb);
					for(HangBean hb: dsHD) {
						cthdBo.them(maHD,hb.getMaHang(),hb.getSoluongban(),makh);
					}
					JOptionPane.showMessageDialog(null,"thanh toan thanh cong");
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 12));
		button.setBackground(Color.LIGHT_GRAY);
		button.setBounds(212, 341, 289, 45);
		contentPane.add(button);
		
		
		lbTongtien.setForeground(Color.RED);
		lbTongtien.setBounds(528, 341, 136, 26);
		contentPane.add(lbTongtien);
		
	}
}
