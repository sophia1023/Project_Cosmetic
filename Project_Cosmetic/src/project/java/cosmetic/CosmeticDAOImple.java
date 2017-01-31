package project.java.cosmetic;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.plaf.synth.SynthSeparatorUI;

import oracle.jdbc.driver.OracleDriver;

public class CosmeticDAOImple implements CosmeticDAO, OracleQuary {

	// singletone 디자인 패턴 적용
	private static CosmeticDAOImple instance = null;
	private static CosmeticVO cvo;
	private static BufferedImage bi;
	private static ArrayList<IngredientsVO> list;

	private CosmeticDAOImple() {
		try {
			DriverManager.registerDriver(new OracleDriver());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static CosmeticDAOImple getInstance() {
		if (instance == null) {
			instance = new CosmeticDAOImple();
		}
		return instance;
	}

	// 메소드 overloading
	private void closeResources(Connection conn, Statement stmt) {
		try {
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void closeResources(Connection conn, Statement stmt, ResultSet rs) {
		try {
			rs.close();
			closeResources(conn, stmt);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public int insert_Cos(CosmeticVO cvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(INSERT_COS);

			pstmt.setString(1, cvo.getCos_Name());
			pstmt.setString(2, cvo.getCos_Com());
			pstmt.setString(3, cvo.getCos_Cate());
			pstmt.setString(4, cvo.getCos_Ingre());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt);
		}
		return result;
	}

	@Override
	public int insert_CosImge(CosmeticVO vo, String imgPath, String fileName) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(INSERT_COSIMG);

			pstmt.setString(1, vo.getCos_Name());
			pstmt.setString(2, fileName);

			System.out.println(imgPath);
			File file = new File(imgPath);
			FileInputStream fin = new FileInputStream(file);
			pstmt.setBinaryStream(3, fin, (int) file.length());

			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt);
		}
		return result;
	}

	@Override
	public int insert_Ingre(CosmeticVO cvo, IngredientsVO ivo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public CosmeticVO select_Cos(String name) {
		cvo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SELECT_COS);

			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String cos_name = rs.getString(1);
				String cos_com = rs.getString(2);
				String cos_cate = rs.getString(3);
				String cos_ingre = rs.getString(4);

				cvo = new CosmeticVO(cos_name, cos_com, cos_cate, cos_ingre);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}

		return cvo;
	}

	@Override
	public BufferedImage select_cosImge(String name) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SELECT_COSIMG);

			pstmt.setString(1, "%" + name + "%");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String cos_name = rs.getString(1);
				InputStream in = rs.getBinaryStream(3);
				bi = ImageIO.read(in);
				in.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		return bi;
	}

	@Override
	public ArrayList<IngredientsVO> select_Ingre(String[] ingre) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			pstmt = conn.prepareStatement(SELECT_INGRE);

			for(int i = 0; i < ingre.length; i++){
				System.out.println(ingre[i]);
				pstmt.setString(1, "%" + ingre[i] + "%");
				rs = pstmt.executeQuery();
			}
			
			while (rs.next()) {
				String name = rs.getString(1);
				int ewg = rs.getInt(2);
				String func = rs.getString(3);
				String use = rs.getString(4);
				String effect = rs.getString(5);
				String type = rs.getString(6);
				int top20 = rs.getInt(7);
				
				IngredientsVO ivo = new IngredientsVO(name,ewg,func,use,effect,type,top20);
				list.add(ivo);
			}		
			
			for(int i  =0 ; i < list.size(); i++){
				System.out.println(list.get(i));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources(conn, pstmt, rs);
		}
		System.out.println(list.size());
		return list;	
	}

}
