package project.java.cosmetic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.border.LineBorder;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;


public class CosmeticMain implements ActionListener {

	private CosmeticDAO dao;
	private CosmeticVO cvo;
	private IngredientsVO ivo;
	private ImageVO imvo;

	private ArrayList<CosmeticVO> cos_list = new ArrayList<>();
	private ArrayList<CosmeticVO> cos_list2 = new ArrayList<>();
	private ArrayList<IngredientsVO> ingre_list = new ArrayList<>();
	private ArrayList<ImageVO> img_list = new ArrayList<>();
	private ArrayList<IngredientsVO> clist = new ArrayList<>();
	private ArrayList<IngredientsVO> hlist = new ArrayList<>();
	private ArrayList<IngredientsVO> alist = new ArrayList<>();
	private ArrayList<BufferedImage> blist = new ArrayList<>();
	private ArrayList<String> cos_names = new ArrayList<>();
	private ArrayList<String> cosmetics = new ArrayList<>();
	private ArrayList<String> cos_coms = new ArrayList<>();
	private ArrayList<String> cos_cates = new ArrayList<>();

	private JFrame frame;
	private Color c_panel = new Color(200, 221, 242);
	private Color info_color = new Color(222, 41, 35);
	private Color c_color = new Color(174, 119, 41);
	private Color h_color = new Color(26, 83, 131);
	private Color a_color = new Color(131, 26, 130);
	private Font font = new Font("210 카툰스토리 L", Font.BOLD, 20);

	private JPanel selectPanel = new JPanel();
	private JPanel listPanel = new JPanel();
	private JPanel resultPanel = new JPanel();
	private JPanel lPane;
	private ImgPanel select_ImgPanel = new ImgPanel();
	private ImgPanel insert_ImgPanel = new ImgPanel();

	private JButton[] l_Button;
	private JRadioButton check_Cos = new JRadioButton("화장품명");
	private JRadioButton check_Com = new JRadioButton("회사명");
	private JRadioButton check_Cate = new JRadioButton("카테고리");

	private JTextField find_Field;
	private JTextField textField;

	private JLabel c_Label = new JLabel();
	private JLabel h_Label = new JLabel();
	private JLabel a_Label = new JLabel();

	private JTextArea result_Cos;
	private JTextArea result_c_ingre;
	private JTextArea result_h_ingre;
	private JTextArea result_a_ingre;

	private JTextField cos_Name;
	private JTextField cos_Com;
	private JTextField cos_Cate;
	private JTextArea cos_Ingre;

	public String path = null;
	public String fileName = null;
	public String image = null;
	private String cosmetic = null;
	private int index = 0;
	private int count = 0;
	private String buttonText = null;
	private BufferedImage bi = null;

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
		frame.setBounds(100, 100, 782, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tPane = new JTabbedPane(JTabbedPane.TOP);
		tPane.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 16));
		frame.getContentPane().add(tPane, BorderLayout.CENTER);

		selectPanel.setBackground(c_panel);
		tPane.addTab("유해성분 찾기", null, selectPanel, null);
		selectPanel.setLayout(null);

		JTextArea find_Title = new JTextArea();
		find_Title.setFont(new Font("210 카툰스토리 L", Font.BOLD, 20));
		find_Title.setText("화장품 찾기");
		find_Title.setBounds(25, 27, 104, 28);
		find_Title.setBackground(c_panel);
		selectPanel.add(find_Title);

		check_Cos.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 12));
		check_Cos.setBounds(137, 27, 73, 23);
		check_Cos.setBackground(c_panel);
		check_Cos.setSelected(true);
		selectPanel.add(check_Cos);
		check_Cos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_Com.setSelected(false);
				check_Cate.setSelected(false);
				listPanel.setVisible(false);
			}
		});

		check_Com.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 12));
		check_Com.setBounds(214, 27, 61, 23);
		check_Com.setBackground(c_panel);
		selectPanel.add(check_Com);
		check_Com.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_Cos.setSelected(false);
				check_Cate.setSelected(false);
				listPanel.setVisible(false);
			}
		});

		check_Cate.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 12));
		check_Cate.setBounds(279, 27, 73, 23);
		check_Cate.setBackground(c_panel);
		selectPanel.add(check_Cate);
		check_Cate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check_Cos.setSelected(false);
				check_Com.setSelected(false);
				listPanel.setVisible(false);
			}
		});

		find_Field = new JTextField();
		find_Field.setBounds(360, 27, 307, 27);
		selectPanel.add(find_Field);
		find_Field.setColumns(10);

		JButton find_Btn = new JButton("검 색");
		find_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				count = dao.count_Cosmetic();
				l_Button = new JButton[count];
				clearSelectPane();

				String findText = find_Field.getText();
				getCosList(findText);

				if (cos_list.size() == 1) {
					cvo = cos_list.get(0);
					showResultPanel();
					showCos();
					showCosImg(cosmetic, index);
					showIngre(cvo);
				} else {
					listPanel.setVisible(true);
					resultPanel.setVisible(false);
					getCos_Set();
					showListPanel();
				}

			}
		});

		find_Btn.setFont(new Font("210 카툰스토리 L", Font.BOLD, 17));
		find_Btn.setBounds(676, 27, 73, 28);
		selectPanel.add(find_Btn);

		listPanel.setBackground(c_panel);
		listPanel.setBounds(0, 65, 761, 545);
		selectPanel.add(listPanel);
		listPanel.setLayout(null);
		listPanel.setVisible(false);

		JTextArea result_set = new JTextArea();
		result_set.setBounds(26, 10, 109, 29);
		listPanel.add(result_set);
		result_set.setText("검색 결과");
		result_set.setFont(new Font("210 카툰스토리 L", Font.BOLD, 25));
		result_set.setBackground(new Color(200, 221, 242));

		JScrollPane scrollBar = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollBar.setBounds(12, 49, 737, 469);
		scrollBar.setBorder(new LineBorder(c_panel));
		listPanel.add(scrollBar);

		lPane = new JPanel();
		lPane.setBounds(12, 49, 760, 544);
		lPane.setBackground(c_panel);
		lPane.setBorder(new LineBorder(c_panel));
		lPane.setLayout(new GridLayout(0, 1));
		scrollBar.setViewportView(lPane);

		resultPanel.setBackground(c_panel);
		resultPanel.setBounds(0, 63, 761, 547);
		selectPanel.add(resultPanel);
		resultPanel.setLayout(null);
		resultPanel.setVisible(false);

		JTextArea textArea = new JTextArea();
		textArea.setBounds(25, 10, 109, 29);
		textArea.setText("검색 결과");
		textArea.setFont(new Font("210 카툰스토리 L", Font.BOLD, 25));
		textArea.setBackground(new Color(200, 221, 242));
		resultPanel.add(textArea);

		select_ImgPanel.setBackground(c_panel);
		select_ImgPanel.setBorder(new LineBorder(c_panel));
		select_ImgPanel.setBounds(25, 77, 300, 393);
		resultPanel.add(select_ImgPanel);

		JScrollPane rPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		rPane.setBorder(new LineBorder(c_panel));
		rPane.setBounds(345, 10, 391, 76);
		resultPanel.add(rPane);

		result_Cos = new JTextArea();
		result_Cos.setBounds(345, 10, 391, 76);
		rPane.setViewportView(result_Cos);
		result_Cos.setBackground(c_panel);
		result_Cos.setBorder(new LineBorder(c_panel));
		result_Cos.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 16));

		ImageIcon icon2 = new ImageIcon("warning.png");
		JLabel info_icon = new JLabel(icon2);
		info_icon.setBounds(342, 102, 20, 20);
		resultPanel.add(info_icon);
		info_icon.setBackground(c_panel);
		info_icon.setBorder(new LineBorder(c_panel));

		JLabel info = new JLabel("20가지 특별히 조심해야 할 성분은 붉은 색으로 표시 됩니다.");
		info.setBounds(368, 93, 361, 39);
		info.setBackground(c_panel);
		info.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 13));
		info.setForeground(info_color);
		resultPanel.add(info);

		JScrollPane c_rPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		c_rPane.setBounds(345, 159, 391, 103);
		resultPanel.add(c_rPane);

		JScrollPane a_rPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		a_rPane.setBounds(345, 299, 391, 103);
		resultPanel.add(a_rPane);

		JScrollPane h_rPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		h_rPane.setBounds(345, 433, 391, 103);
		resultPanel.add(h_rPane);

		result_c_ingre = new JTextArea();
		result_c_ingre.setBounds(345, 154, 385, 274);
		c_rPane.setViewportView(result_c_ingre);
		result_c_ingre.setBackground(c_panel);
		result_c_ingre.setLineWrap(true);

		result_a_ingre = new JTextArea();
		a_rPane.setViewportView(result_a_ingre);
		result_a_ingre.setBounds(345, 294, 385, 103);
		result_a_ingre.setBackground(c_panel);
		result_a_ingre.setLineWrap(true);

		result_h_ingre = new JTextArea();
		result_h_ingre.setBounds(345, 158, 385, 274);
		h_rPane.setViewportView(result_h_ingre);
		result_h_ingre.setBackground(c_panel);
		result_h_ingre.setLineWrap(true);

		////////////////////////////////////////////////////////// 등록 UI /////////////////////////////////////////////////////

		JPanel insertPanel = new JPanel();
		insertPanel.setBackground(c_panel);
		tPane.addTab("화장품 등록", null, insertPanel, null);
		insertPanel.setLayout(null);

		JTextArea file_Titile = new JTextArea();
		file_Titile.setText("사진 등록");
		file_Titile.setFont(font);
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
				path = null;
				findImage();
				showImage();
			}
		});
		file_Btn.setFont(font);
		file_Btn.setBounds(520, 27, 113, 28);
		insertPanel.add(file_Btn);

		insert_ImgPanel.setBackground(c_panel);
		//insert_ImgPanel.setBorder(new LineBorder(c_panel));
		insert_ImgPanel.setBounds(30, 121, 290, 393);
		insertPanel.add(insert_ImgPanel);

		JTextArea cos_Title = new JTextArea();
		cos_Title.setText("화장품 명");
		cos_Title.setFont(font);
		cos_Title.setBackground(new Color(200, 221, 242));
		cos_Title.setBounds(354, 126, 103, 28);
		insertPanel.add(cos_Title);

		cos_Name = new JTextField();
		cos_Name.setColumns(10);
		cos_Name.setBounds(462, 126, 263, 27);
		insertPanel.add(cos_Name);

		JTextArea com_Title = new JTextArea();
		com_Title.setText("회사 명");
		com_Title.setFont(font);
		com_Title.setBackground(new Color(200, 221, 242));
		com_Title.setBounds(354, 167, 68, 28);
		insertPanel.add(com_Title);

		cos_Com = new JTextField();
		cos_Com.setColumns(10);
		cos_Com.setBounds(462, 163, 264, 27);
		insertPanel.add(cos_Com);

		JTextArea cate_Title = new JTextArea();
		cate_Title.setText("카테고리");
		cate_Title.setFont(font);
		cate_Title.setBackground(new Color(200, 221, 242));
		cate_Title.setBounds(353, 210, 78, 28);
		insertPanel.add(cate_Title);

		cos_Cate = new JTextField();
		cos_Cate.setColumns(10);
		cos_Cate.setBounds(462, 207, 266, 27);
		insertPanel.add(cos_Cate);

		JTextArea ingre_Title = new JTextArea();
		ingre_Title.setText("성 분");
		ingre_Title.setFont(font);
		ingre_Title.setBackground(new Color(200, 221, 242));
		ingre_Title.setBounds(356, 251, 47, 28);
		insertPanel.add(ingre_Title);

		JScrollPane iPane = new JScrollPane(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		iPane.setBounds(354, 285, 375, 187);
		iPane.setBorder(new LineBorder(c_panel));
		iPane.setBackground(c_panel);
		insertPanel.add(iPane);

		cos_Ingre = new JTextArea();
		cos_Ingre.setColumns(5);
		cos_Ingre.setBounds(354, 285, 375, 186);
		cos_Ingre.setLineWrap(true);
		iPane.setViewportView(cos_Ingre);

		JButton insert_Btn = new JButton("화장품 등록");
		insert_Btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertCos();
			}
		});
		insert_Btn.setFont(font);
		insert_Btn.setBounds(578, 486, 152, 28);
		insertPanel.add(insert_Btn);
		tPane.setBackground(c_panel);
	}

	private void showResultPanel() {
		resultPanel.setVisible(true);
	}

	private void showListPanel() {
		listPanel.setVisible(true);
	}

	private void getCosList(String findText) {
		if (check_Cos.isSelected() == true) {
			cos_list = dao.select_Cos(findText);
		} else if (check_Com.isSelected() == true) {
			cos_list = dao.select_Com(findText);
		} else if (check_Cate.isSelected() == true) {
			cos_list = dao.select_Cate(findText);
		}

	}

	private void showCos() {
		cosmetic = cvo.getCos_Name();
		StringBuffer buffer = new StringBuffer();
		buffer.append(cvo);
		result_Cos.setText(buffer.toString());
		index = 0;
	}

	private void showCosImg(String cosmetic, int index) {
		try {
			img_list.clear();
			img_list = dao.select_cosImge(cosmetic);
			System.out.println(img_list);
			imvo = img_list.get(index);
			bi = imvo.getImg();
			select_ImgPanel.bi = bi;
			select_ImgPanel.setImage2(bi);
			select_ImgPanel.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getCos_Set() {
		for (int i = 0; i < cos_list.size(); i++) {
			cvo = cos_list.get(i);
			cos_coms.add(cvo.getCos_Com());
			cos_cates.add(cvo.getCos_Cate());
			cos_names.add(cvo.getCos_Name());
		}
		getCosImg_Set(cos_names);
		showCos_set();
	}

	private void getCosImg_Set(ArrayList<String> names) {
		try {
			for (int i = 0; i < names.size(); i++) {
				String name = names.get(i);
				imvo = dao.select_cosImge(name).get(i);
				bi = imvo.getImg();
				blist.add(bi);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showCos_set() {
		lPane.removeAll();
		for (int i = 0; i < cos_list.size(); i++) {
			cvo = cos_list.get(i);
			if (check_Com.isSelected() == true) {
				buttonText = cvo.getCos_Name() + "(" + cvo.getCos_Cate() + ")";
			} else if (check_Cate.isSelected() == true) {
				buttonText = cvo.getCos_Name() + "(" + cvo.getCos_Cate() + ")";
			} else if (check_Cos.isSelected() == true) {
				buttonText = cvo.getCos_Name() + "(" + cvo.getCos_Com() + ", " + cvo.getCos_Cate() + ")";
			}

			l_Button[i] = new JButton();

			BufferedImage icon = blist.get(i);
			ImageIcon img = new ImageIcon(icon);
			Image ori = img.getImage();
			Image resize = ori.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			img = new ImageIcon(resize);
			
			String index = String.valueOf(i);
			l_Button[i].setIcon(img);
			l_Button[i].setName(index);
			l_Button[i].setText(buttonText);
			l_Button[i].setHorizontalAlignment(SwingConstants.LEFT);
			l_Button[i].setBackground(c_panel);
			l_Button[i].setBounds(25, 75 * i, 700, 70);
			l_Button[i].setFont(font);
			lPane.add(l_Button[i]);
			l_Button[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent e) {
		clearSelectPane();
		cos_list2.clear();
		listPanel.setVisible(false);
		JButton b = (JButton) e.getSource();
		String text = b.getText();
		String[] store = text.split("\\(");
		String cos = store[0];
		cos_list2 = dao.select_Cos(cos);
		cvo = cos_list2.get(0);
		showResultPanel();
		showCos();
		index = Integer.valueOf(b.getName());
		showCosImg(cos, index);
		showIngre(cvo);
	}

	private void divideIngres() {
		for (int i = 0; i < ingre_list.size(); i++) {
			ivo = ingre_list.get(i);
			if (ivo.getType().equals("발암")) {
				clist.add(ivo);
			} else if (ivo.getType().equals("환호")) {
				hlist.add(ivo);
			} else if (ivo.getType().equals("알러지")) {
				alist.add(ivo);
			}
		}
	}

	private void showLabel() {
		c_Label.setText("◈ 발암 물질 " + clist.size() + "개");
		c_Label.setBackground(c_panel);
		c_Label.setBounds(345, 124, 385, 29);
		c_Label.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 16));
		c_Label.setForeground(c_color);
		resultPanel.add(c_Label);

		a_Label.setText("◈ 알러지 유발 물질 " + alist.size() + "개");
		a_Label.setBackground(c_panel);
		a_Label.setBounds(345, 266, 385, 29);
		a_Label.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 16));
		a_Label.setForeground(a_color);
		resultPanel.add(a_Label);

		h_Label.setText("◈ 환경 호르몬 물질 " + hlist.size() + "개");
		h_Label.setBackground(c_panel);
		h_Label.setBounds(345, 402, 385, 29);
		h_Label.setFont(new Font("210 카툰스토리 L", Font.PLAIN, 16));
		h_Label.setForeground(h_color);
		resultPanel.add(h_Label);
	}

	private void showIngre(CosmeticVO cvo) {
		StringBuffer c_buffer = new StringBuffer();
		StringBuffer h_buffer = new StringBuffer();
		StringBuffer a_buffer = new StringBuffer();

		String[] ingre = cvo.getCos_Ingre().split(", ");
		ingre_list = dao.select_Ingre(ingre);

		divideIngres();
		showLabel();

		if (clist.size() > 0) {
			for (int i = 0; i < clist.size(); i++) {
				if (clist.get(i).getTop20() != 0) {
					result_c_ingre.setForeground(info_color);
				}
				c_buffer.append(clist.get(i) + "\n\n");
				result_c_ingre.setText(c_buffer.toString());
			}
		} else {
			result_c_ingre.setText("");
		}

		if (alist.size() > 0) {
			for (int i = 0; i < alist.size(); i++) {
				if (alist.get(i).getTop20() != 0) {
					result_a_ingre.setForeground(info_color);
				}
				a_buffer.append(alist.get(i) + "\n\n");
				result_a_ingre.setText(a_buffer.toString());
			}
		} else {
			result_a_ingre.setText("");
		}

		if (hlist.size() > 0) {
			for (int i = 0; i < hlist.size(); i++) {
				if (hlist.get(i).getTop20() != 0) {
					result_h_ingre.setForeground(info_color);
				}
				h_buffer.append(hlist.get(i) + "\n\n");
				result_h_ingre.setText(h_buffer.toString());
			}
		} else {
			result_h_ingre.setText("");
		}
	}
	
	/////////////////////////////////// 등록 메소드////////////////////////////////////////
	
	private String findImage() {

		JFileChooser fc = new JFileChooser();
		int result = fc.showOpenDialog(frame);
		if (result == JFileChooser.APPROVE_OPTION) {
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

		if (!name.equals("") && !company.equals("") && !category.equals("") && !ingredients.equals("")) {
			cvo = new CosmeticVO(name, company, category, ingredients);
			System.out.println(cvo);
		} else {
			JOptionPane.showConfirmDialog(frame, "등록 실패!", "확인메세지", 3);
			System.out.println("등록 실패");
		}

		int result1 = dao.insert_Cos(cvo);
		int result2 = 0;
		try {
			result2 = dao.insert_CosImge(cvo, path, fileName);
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

	private void showImage() {
		insert_ImgPanel.setImage(path);
	}
	
	/////////////////////////////// 초기화 메소드 ///////////////////////////////

	private void clearSelectPane() {
		cos_list.clear();
		ingre_list.clear();
		img_list.clear();
		
		cos_names.clear();
		cosmetics.clear();
		cos_coms.clear();
		cos_cates.clear();
		
		clist.clear();
		hlist.clear();
		alist.clear();
		blist.clear();
	}

	private void clearInsertPane() {
		cos_Name.setText("");
		cos_Com.setText("");
		cos_Cate.setText("");
		cos_Ingre.setText("");

		insert_ImgPanel.bi = null;
		insert_ImgPanel.reBi = null;
		insert_ImgPanel.repaint();
	}
}
