package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class Menu extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		setTitle("CHAO MUNG DEN VOI HE THONG ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 384);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("TRANG CHU");
		lblNewLabel.setFont(new Font("Lucida Console", Font.BOLD, 44));
		lblNewLabel.setBounds(61, 10, 290, 36);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("BAN HANG");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				HangView hv= new HangView();
//				hv.setVisible(true);
				hv.setVisible(rootPaneCheckingEnabled);

			}
		});
		btnNewButton.setBackground(SystemColor.controlHighlight);
		btnNewButton.setBounds(97, 70, 190, 36);
		contentPane.add(btnNewButton);
		
		JButton btnQuanLyKhach = new JButton("QUAN LY KHACH HANG");
		btnQuanLyKhach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				KhachHangView khview= new KhachHangView();
				khview.setVisible(true);
			}
		});
		btnQuanLyKhach.setBackground(SystemColor.controlHighlight);
		btnQuanLyKhach.setBounds(97, 123, 190, 36);
		contentPane.add(btnQuanLyKhach);
		
		JButton btnQuanLyKhoa = new JButton("QUAN LY KHO");
		btnQuanLyKhoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QuanLyKhoHang qlkho= new QuanLyKhoHang();
				qlkho.setVisible(true);
			}
		});
		btnQuanLyKhoa.setBackground(SystemColor.controlHighlight);
		btnQuanLyKhoa.setBounds(97, 177, 190, 36);
		contentPane.add(btnQuanLyKhoa);
		
		JButton btnThongKe = new JButton("THONG KE");
		btnThongKe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThongKe tk = new ThongKe();
				tk.setVisible(true);
			}
		});
		btnThongKe.setBackground(SystemColor.controlHighlight);
		btnThongKe.setBounds(97, 235, 190, 36);
		contentPane.add(btnThongKe);
		
		JButton btnThoat = new JButton("THOAT");
		btnThoat.setBackground(SystemColor.controlHighlight);
		btnThoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);;
			}
		});
		btnThoat.setBounds(97, 293, 190, 36);
		contentPane.add(btnThoat);
	}
}
