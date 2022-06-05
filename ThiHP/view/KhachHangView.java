package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import bean.HangBean;
import bean.KhachHangbean;
import bo.KhacHangBo;
import dao.Coso;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.SystemColor;
import javax.swing.JTabbedPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class KhachHangView extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */KhacHangBo khbo=new KhacHangBo();
	TextField txtsdt = new TextField();
	TextField txtdiachi = new TextField();
	TextField txthovaten = new TextField();
	TextField txtmakh = new TextField();
	public ArrayList<KhachHangbean> ds=new ArrayList<KhachHangbean>();
	private JTable table;
	void NapBang(String tenKH) throws Exception {
		DefaultTableModel mh= new DefaultTableModel();
		String[] tieude= {"STT","Ma KH","Ten Khach Hang","SDT","Dia Chi"};
		mh.setColumnIdentifiers(tieude);
		table.setModel(mh);
		int i=1;
		for(KhachHangbean kh: ds) {
			
			Object[] t= new Object[5];
			if(kh.getHoTenKH().toLowerCase().contains(tenKH)) {
			t[0]=i++;
			t[1]=kh.getMaKH();
			t[2]=kh.getHoTenKH();
			t[3]=kh.getSDT();
			t[4]=kh.getDiaChi();
			mh.addRow(t);
		}
		}
		
	}
	void resetKH() {
		try {
			ds=khbo.getKH();
			ArrayList<KhachHangbean>  tam= new ArrayList<KhachHangbean>();
			tam=khbo.lastkh();
			for(KhachHangbean kh : tam) {
				txtmakh.setText(kh.getMaKH());
				txthovaten.setText(kh.getHoTenKH());
				txtdiachi.setText(kh.getDiaChi());
				txtsdt.setText(kh.getSDT());
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	void resetKH(ArrayList<KhachHangbean> ds) {
		try {
			
			for(KhachHangbean kh : ds) {
				txtmakh.setText(kh.getMaKH());
				txthovaten.setText(kh.getHoTenKH());
				txtdiachi.setText(kh.getDiaChi());
				txtsdt.setText(kh.getSDT());
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KhachHangView frame = new KhachHangView();
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
	public KhachHangView() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				Coso cs=new Coso();
				cs.KetNoi();
				resetKH();
				try {
					NapBang("");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 684, 457);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Label label = new Label("QUAN LY THONG TIN KHACH HANG");
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		label.setBounds(64, 10, 535, 40);
		contentPane.add(label);
		
		Label label_1 = new Label("Ma Khach Hang ");
		label_1.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_1.setBounds(10, 74, 132, 21);
		contentPane.add(label_1);
		
		txtmakh.setBounds(159, 74, 142, 21);
		contentPane.add(txtmakh);
		
		Label label_2 = new Label("Ho Va Ten ");
		label_2.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_2.setBounds(10, 111, 113, 21);
		contentPane.add(label_2);
		txthovaten.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					NapBang(txthovaten.getText().toLowerCase());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		txthovaten.setBounds(159, 111, 244, 21);
		contentPane.add(txthovaten);
		
		Label label_3 = new Label("Dia Chi ");
		label_3.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_3.setBounds(10, 146, 59, 21);
		contentPane.add(label_3);
		
		txtdiachi.setBounds(159, 146, 244, 21);
		contentPane.add(txtdiachi);
		
		Label label_4 = new Label("So Dien Thoai");
		label_4.setFont(new Font("Dialog", Font.PLAIN, 12));
		label_4.setBounds(10, 183, 113, 21);
		contentPane.add(label_4);
		
		
		txtsdt.setBounds(159, 183, 142, 21);
		contentPane.add(txtsdt);
		
		Button button = new Button("Them");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					if(khbo.timMaKH(txtmakh.getText())==true) {
						JOptionPane.showMessageDialog(null, "Ma khach hang da ton tai");
					}
					else {khbo.Them(txtmakh.getText(), txthovaten.getText(),txtdiachi.getText(), txtsdt.getText());
					JOptionPane.showMessageDialog(null, "Them khach hang thanh cong");
					NapBang("");
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button.setBackground(SystemColor.activeCaptionBorder);
		button.setForeground(SystemColor.inactiveCaptionText);
		button.setBounds(481, 74, 72, 21);
		contentPane.add(button);
		
		Button button_1 = new Button("Sua");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					khbo.Sua(txtmakh.getText(), txthovaten.getText(), txtdiachi.getText(), txtsdt.getText());
					JOptionPane.showMessageDialog(null, "Sua thong tin thanh cong");
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			
		});
		button_1.setForeground(SystemColor.inactiveCaptionText);
		button_1.setBackground(SystemColor.activeCaptionBorder);
		button_1.setBounds(481, 111, 72, 21);
		contentPane.add(button_1);
		
		Button button_2 = new Button("Xoa");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int kt = JOptionPane.showConfirmDialog(null, "ban con muon xoa khach hang nay?");
					if(kt==0) {
					
					khbo.Xoa(txtmakh.getText());
					JOptionPane.showMessageDialog(null, "Xoa khach hang thanh cong");
					resetKH();
					}
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_2.setForeground(SystemColor.inactiveCaptionText);
		button_2.setBackground(SystemColor.activeCaptionBorder);
		button_2.setBounds(481, 146, 72, 21);
		contentPane.add(button_2);
		
		Button button_3 = new Button("Tim Kiem");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String key=JOptionPane.showInputDialog("Nhap ma khach hang");
				try {
					if(khbo.TimKiem(key)==null) {
						JOptionPane.showMessageDialog(null, "Ma khach hang khong ton tai");
					}
					else resetKH(khbo.TimKiem(key));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		button_3.setForeground(SystemColor.inactiveCaptionText);
		button_3.setBackground(SystemColor.activeCaptionBorder);
		button_3.setBounds(481, 183, 72, 21);
		contentPane.add(button_3);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(34, 257, 506, 128);
		contentPane.add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("DS Khach Hang", null, scrollPane, null);
		
		table = new JTable();
		table.setBackground(SystemColor.activeCaptionBorder);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int d=table.getSelectedRow();
				String maKH=table.getValueAt(d, 1).toString();
				String TenKH= table.getValueAt(d, 2).toString();
				int sodt=Integer.parseInt(table.getValueAt(d, 3).toString());
				String diachi=table.getValueAt(d, 4).toString();
				txtmakh.setText(maKH);
				txthovaten.setText(TenKH);
				txtsdt.setText(String.valueOf(sodt));
				txtdiachi.setText(diachi);
			}
		});
		scrollPane.setViewportView(table);
	}
}
