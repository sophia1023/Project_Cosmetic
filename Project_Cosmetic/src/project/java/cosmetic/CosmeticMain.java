package project.java.cosmetic;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class CosmeticMain {

	private JFrame frame;
	private JPanel selectPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	
	private Color c_panel = new Color(200,221,242);
	private JTextField textField;
	private JTextField cos_Name;
	private JTextField cos_Com;
	private JTextField cos_Cate;
	private JTextField cos_Ingre;
	
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
		
		JTextField find_Field = new JTextField();
		find_Field.setBounds(144, 27, 380, 27);
		selectPanel.add(find_Field);
		find_Field.setColumns(10);
		
		JButton find_Btn = new JButton("검 색");
		find_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showPanel();
				showImage();
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
		
		ImgPanel select_ImgPanel = new ImgPanel();
		//select_ImgPanel.setBackground(c_panel);
		select_ImgPanel.setBounds(31, 45, 290, 393);
		select_ImgPanel.setBorder(new LineBorder(c_panel));
		resultPanel.add(select_ImgPanel);
		
		JScrollPane rPane = new JScrollPane();
		//rPane.setBorder(new LineBorder(c_panel));
		rPane.setBounds(346, 45, 385, 393);
		resultPanel.add(rPane);
		
		JTextArea result_field = new JTextArea();
		result_field.setBackground(c_panel);
		rPane.setViewportView(result_field);
		
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
		file_Btn.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 20));
		file_Btn.setBounds(520, 27, 113, 28);
		insertPanel.add(file_Btn);
		
		ImgPanel insert_ImgPanel = new ImgPanel();
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
		cos_Ingre.setColumns(10);
		cos_Ingre.setBounds(354, 252, 375, 187);
		insertPanel.add(cos_Ingre);
		
		JButton insert_Btn = new JButton("화장품 등록");
		insert_Btn.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 20));
		insert_Btn.setBounds(607, 453, 123, 28);
		insertPanel.add(insert_Btn);
		tPane.setBackground(c_panel);
		
		
    }
	
	private void showPanel() {
		resultPanel.setVisible(true);
	}
	
	private void showImage() {
	
		
	}
}



