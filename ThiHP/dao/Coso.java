package dao;

import java.sql.Connection; 
import java.sql.DriverManager;
	public class Coso {
	public static Connection cn;
		public void KetNoi(){

		try{
			Class.forName ("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			System.out.println("OK da nap Driver");
			String st="jdbc:sqlserver://DESKTOP-NQMSLDT\\SQLEXPRESS:1433; databaseName=QuanLyBanHang; user=sa; password=123";
			cn= DriverManager.getConnection (st); 
//			System.out.println("Da ket noi den csdl qlsv");

		}catch(Exception tt){

			tt.printStackTrace();

//			System.out.println(tt.getMessage());

		}

	}

		public static void main(String[] args) { 
			Coso cs= new Coso();
			cs.KetNoi();
		}
}