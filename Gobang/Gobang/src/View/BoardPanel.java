package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controller.GameManager;
import Module.ChessBoard;
import Module.Config;
import Module.ChessType;

public class BoardPanel extends JPanel implements MouseListener{

	private static int side = 15;
	public BoardPanel()
	{
		addMouseListener(this);
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		g.setColor(Color.BLACK);
		// 画15行
		for (int i = 0; i < Config.Row; i++) {
			g.drawLine(side, side + i * Config.Board_JianJu, side
					+ (Config.Board_Size - 1) * Config.Board_JianJu,
					side + i * Config.Board_JianJu);
		}
		// 画15列
		for (int i = 0; i < Config.Board_Size; i++) {
			g.drawLine(side + i * Config.Board_JianJu, side, side + i
					* Config.Board_JianJu, side
					+ (Config.Board_Size - 1) * Config.Board_JianJu);
		}
		paintBoard(g);
	}
	private void paintBoard(Graphics g)
	{
		for (int i = 0; i < Config.Board_Size; i++) {
			for (int j = 0; j < Config.Board_Size; j++) 
			{
				if(ChessBoard.getInstance().getBoard()[i][j]==ChessType.BLACK)
				{
					g.setColor(Color.BLACK);
					g.fillOval(i*Config.Board_JianJu+5, j*Config.Board_JianJu+5, Config.Chess_size,
							Config.Chess_size);
				}else if(ChessBoard.getInstance().getBoard()[i][j]==ChessType.WHITE)
				{
					g.setColor(Color.WHITE);
					g.fillOval(i*Config.Board_JianJu+5, j*Config.Board_JianJu+5, Config.Chess_size,
							Config.Chess_size);
				}
			}
		}
	}
	@Override
	public void mouseClicked(MouseEvent event) {
		// TODO Auto-generated method stub
		if(GameManager.getInstance().turn==ChessType.BLACK){
			int column = event.getY()/Config.Board_JianJu;
			int row = event.getX()/Config.Board_JianJu;
			if(ChessBoard.getInstance().isValid(row, column)){
				if(GameManager.getInstance().turn == ChessType.BLACK ){
					ChessBoard.getInstance().setBoard(row, column, ChessType.BLACK);
				}else if(GameManager.getInstance().turn == ChessType.WHITE)
				{
					ChessBoard.getInstance().setBoard(row, column, ChessType.WHITE);
				}
				this.repaint();
				GameManager.getInstance().goNext(row, column);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "此处已有旗子");
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

}
