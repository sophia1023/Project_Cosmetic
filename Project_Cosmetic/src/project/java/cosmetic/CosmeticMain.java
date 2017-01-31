package project.java.cosmetic;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import java.awt.image.*;
import java.io.*;
import java.util.ArrayList;

import javax.imageio.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CosmeticMain {

	private CosmeticDAO dao;
	private CosmeticVO cvo;
	private IngredientsVO ivo;
	private JFrame frame;
	private JPanel selectPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	private ImgPanel select_ImgPanel = new ImgPanel();
	private ImgPanel insert_ImgPanel = new ImgPanel();
	
	private Color c_panel = new Color(200,221,242);
	private JTextField find_Field;
	private JTextField textField;
	private JTextArea result_field;
	//private JTextArea textArea;
	private JTextField cos_Name;
	private JTextField cos_Com;
	private JTextField cos_Cate;
	private JTextField cos_Ingre;
	
	public String path;
	public String fileName;
	public String image;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CosmeticMain window = new CosmeticMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CosmeticMain() {
		dao = CosmeticDAOImple.getInstance();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBackground(Color.PINK);
		frame.setBounds(100, 100, 782, 586);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tPane, BorderLayout.CENTER);
		
		selectPanel.setBackground(c_panel);
		tPane.addTab("유해성분 찾기", null, selectPanel, null);
		selectPanel.setLayout(null);
		
		JTextArea find_Title = new JTextArea();
		find_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		find_Title.setText("화장품 찾기");
		find_Title.setBounds(29, 27, 103, 28);
		find_Title.setBackground(c_panel);
		selectPanel.add(find_Title);	
		
		find_Field = new JTextField();
		find_Field.setBounds(144, 27, 380, 27);
		selectPanel.add(find_Field);
		find_Field.setColumns(10);
		
		JButton find_Btn = new JButton("검 색");
		find_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel();
				showCos();
				showIngre();
			}
		});
		
		find_Btn.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 20));
		find_Btn.setBounds(536, 27, 87, 28);
		selectPanel.add(find_Btn);
		
		resultPanel.setBackground(c_panel);
		resultPanel.setBounds(0, 65, 761, 453);
		selectPanel.add(resultPanel);
		resultPanel.setLayout(null);
		resultPanel.setVisible(false);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(31, 10, 87, 24);
		textArea.setText("검색 결과");
		textArea.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		textArea.setBackground(new Color(200, 221, 242));
		resultPanel.add(textArea);
		
		select_ImgPanel.setBackground(c_panel);
		select_ImgPanel.setBorder(new LineBorder(c_panel));
		select_ImgPanel.setBounds(31, 45, 290, 393);		
		resultPanel.add(select_ImgPanel);
		
		result_field = new JTextArea();
		result_field.setBackground(c_panel);
		
		JScrollPane rPane = new JScrollPane(result_field, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		rPane.setBorder(new LineBorder(c_panel));
		rPane.setBounds(346, 45, 385, 393);
		resultPanel.add(rPane);
		
		JPanel insertPanel = new JPanel();
		insertPanel.setBackground(c_panel);
		tPane.addTab("화장품 등록", null, insertPanel, null);
		insertPanel.setLayout(null);
		
		JTextArea file_Titile = new JTextArea();
		file_Titile.setText("사진 등록");
		file_Titile.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		file_Titile.setBackground(new Color(200, 221, 242));
		file_Titile.setBounds(30, 27, 103, 28);
		insertPanel.add(file_Titile);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(128, 27, 380, 27);
		insertPanel.add(textField);
		
		JButton file_Btn = new JButton("파일검색");
		file_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findImage();
				showImage();
			}
		});
		file_Btn.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 20));
		file_Btn.setBounds(520, 27, 113, 28);
		insertPanel.add(file_Btn);
			
		//insert_ImgPanel.setBackground(c_panel);
		insert_ImgPanel.setBorder(new LineBorder(c_panel));
		insert_ImgPanel.setBounds(30, 88, 290, 393);
		insertPanel.add(insert_ImgPanel);
			
		JTextArea cos_Title = new JTextArea();
		cos_Title.setText("화장품 명");
		cos_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		cos_Title.setBackground(new Color(200, 221, 242));
		cos_Title.setBounds(354, 93, 103, 28);
		insertPanel.add(cos_Title);
		
		cos_Name = new JTextField();
		cos_Name.setColumns(10);
		cos_Name.setBounds(462, 93, 263, 27);
		insertPanel.add(cos_Name);
		
		JTextArea com_Title = new JTextArea();
		com_Title.setText("회사 명");
		com_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		com_Title.setBackground(new Color(200, 221, 242));
		com_Title.setBounds(354, 134, 68, 28);
		insertPanel.add(com_Title);
		
		cos_Com = new JTextField();
		cos_Com.setColumns(10);
		cos_Com.setBounds(462, 130, 264, 27);
		insertPanel.add(cos_Com);
		
		JTextArea cate_Title = new JTextArea();
		cate_Title.setText("카테고리");
		cate_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		cate_Title.setBackground(new Color(200, 221, 242));
		cate_Title.setBounds(353, 177, 78, 28);
		insertPanel.add(cate_Title);
		
		cos_Cate = new JTextField();
		cos_Cate.setColumns(10);
		cos_Cate.setBounds(462, 174, 266, 27);
		insertPanel.add(cos_Cate);
		
		JTextArea ingre_Title = new JTextArea();
		ingre_Title.setText("성 분");
		ingre_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		ingre_Title.setBackground(new Color(200, 221, 242));
		ingre_Title.setBounds(356, 218, 47, 28);
		insertPanel.add(ingre_Title);
		
		JScrollPane iPane = new JScrollPane();
		iPane.setBounds(0, 0, 2, 2);
		insertPanel.add(iPane);
		
		cos_Ingre = new JTextField();
		cos_Ingre.setColumns(500);
		cos_Ingre.setBounds(354, 252, 375, 187);
		insertPanel.add(cos_Ingre);
		
		JButton insert_Btn = new JButton("화장품 등록");
		insert_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertCos();
			}
		});
		insert_Btn.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 20));
		insert_Btn.setBounds(607, 453, 123, 28);
		insertPanel.add(insert_Btn);
		tPane.setBackground(c_panel);
		
		
    }
	
	private String findImage(){
		
		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(frame);
		if(result == JFileChooser.APPROVE_OPTION){
			path = fc.getSelectedFile().getPath();
			fileName = fc.getSelectedFile().getName();
		}
			
		return path;
	}
	
	private void insertCos() {
		String name = cos_Name.getText();
		String company = cos_Com.getText();
		String category = cos_Cate.getText();
		String ingredients = cos_Ingre.getText();
		
		if(!name.equals("") && !company.equals("") && !category.equals("") && !ingredients.equals("")) {
			cvo = new CosmeticVO(name,company,category,ingredients);
			System.out.println(cvo);
		} else {
			JOptionPane.showConfirmDialog(frame, "등록 실패!", "확인메세지", 3);
			System.out.println("등록 실패");
		}

		int result1 = dao.insert_Cos(cvo);
		int result2 = 0 ;
		try {
			result2 = dao.insert_CosImge(cvo,path,fileName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (result1 <= 0 && result2 <= 0) {
			JOptionPane.showConfirmDialog(frame, "등록 실패!", "확인메세지", 3);
			System.out.println("등록 실패");
		} else {
			JOptionPane.showMessageDialog(frame, "등록 성공!", "확인메세지", 1);
			System.out.println("등록 성공!");
		}
		 clearInsertPane();
	}
	
	private void clearInsertPane(){
		cos_Name.setText("");
		cos_Com.setText("");
		cos_Cate.setText("");
		cos_Ingre.setText("");
		  
		insert_ImgPanel.bi = null;
		insert_ImgPanel.reBi = null;
        insert_ImgPanel.repaint();
	}
	
	private void showImage() {
		insert_ImgPanel.setImage(path);
	}
	
	private void showPanel() {
		resultPanel.setVisible(true);
	}
	
	private void showCos() {
		String name = find_Field.getText();
		cvo = dao.select_Cos(name);
		StringBuffer buffer = new StringBuffer();
		buffer.append(cvo);
		result_field.setText(buffer.toString());
		
		try {
			BufferedImage bi = dao.select_cosImge(name);
			select_ImgPanel.bi = bi;
			select_ImgPanel.setImage2(bi);
			select_ImgPanel.repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void showIngre(){
		ArrayList<IngredientsVO> list = new ArrayList<>();
		ArrayList<IngredientsVO> clist = new ArrayList<>();
		ArrayList<IngredientsVO> hlist = new ArrayList<>();
		ArrayList<IngredientsVO> alist = new ArrayList<>();
		StringBuffer buffer = new StringBuffer();
		
		String[] ingre = cvo.getCos_Ingre().split(",");
		list = dao.select_Ingre(ingre);
		//System.out.println(list.size());
	}
	
}



