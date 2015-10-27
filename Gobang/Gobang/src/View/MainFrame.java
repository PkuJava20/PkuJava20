package View;



import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public Graphics g;
	public static BoardPanel boardPanel;

	/*
	 * 重载构造方法
	 */
	public MainFrame() {
		boardPanel = new BoardPanel();
		this.initUI();
	}
	public void initUI() 
	{
		this.setTitle("五子棋");
		this.setSize(new Dimension(700, 700));
		this.setResizable(false);
		this.setDefaultCloseOperation(3);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		

		boardPanel.setBackground(new Color(209, 167, 78));
		boardPanel.setBounds(10, 10, 670, 670);
		boardPanel.repaint();
		this.add(boardPanel);

		this.setVisible(true);
		
	}
}
