package project.java.cosmetic;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.driver.OracleDriver;

public class CosmeticDAOImple implements CosmeticDAO, OracleQuary {

	//singletone 디자인 패턴 적용
	private static CosmeticDAOImple instance = null;
	private CosmeticDAOImple() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static CosmeticDAOImple getInstance(){
		if(instance == null){
			instance = new CosmeticDAOImple();
		}
		return instance;
	}
	
	//메소드 overloading
		private void closeResources(Connection conn, Statement stmt){
			try {
				stmt.close();
				conn.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		private void closeResources(Connection conn, Statement stmt, ResultSet rs){
			try {
				rs.close();
				closeResources(conn, stmt);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	@Override
	public CosmeticVO select_Cos(String name) {
		return null;
	}
	
	@Override
	public ImageVO select_image(String name) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public IngredientsVO select_Ingre(String ingre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert_Cos(CosmeticVO cvo, ImageVO civo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int insert_Ingre(CosmeticVO cvo, IngredientsVO ivo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
