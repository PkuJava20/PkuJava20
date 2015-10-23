import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.EnumMap;
import java.util.EnumSet;

 
public class GobangGame {
	
	
	// ����ﵽӮ������������Ŀ
	private final int WIN_COUNT = 5;
	// �����û������X����
	private int posX = 0;
	// �����û������X����
	private int posY = 0;
	// ��������
	private Chessboard chessboard;

	/**
	 * �չ�����
	 */
	public GobangGame() {
	}

	/**
	 * ����������ʼ�����̺���������
	 * 
	 * @param chessboard
	 *            ������
	 */
	public GobangGame(Chessboard chessboard) {
		this.chessboard = chessboard;
	}

	/**
	 * ��������Ƿ�Ϸ���
	 * 
	 * @param inputStr
	 *            �ɿ���̨������ַ�����
	 * @return �ַ����Ϸ�����true,���򷵻�false��
	 */
	public boolean isValid(String inputStr) {
		// ���û�������ַ����Զ���(,)��Ϊ�ָ����ָ��������ַ���
		String[] posStrArr = inputStr.split(",");
		try {
			posX = Integer.parseInt(posStrArr[0]) - 1;
			posY = Integer.parseInt(posStrArr[1]) - 1;
		} catch (NumberFormatException e) {
			chessboard.printBoard();
			System.out.println("����(����,����)�ĸ�ʽ���룺");
			return false;
		}
		// ���������ֵ�Ƿ��ڷ�Χ֮��
		if (posX < 0 || posX >= Chessboard.BOARD_SIZE || posY < 0
				|| posY >= Chessboard.BOARD_SIZE) {
			chessboard.printBoard();
			System.out.println("X��Y����ֻ�ܴ��ڵ���1,��С�ڵ���" + Chessboard.BOARD_SIZE
					+ ",���������룺");
			return false;
		}
		// ��������λ���Ƿ��Ѿ�������
		String[][] board = chessboard.getBoard();
		if (board[posX][posY] != "+") {
			chessboard.printBoard();
			System.out.println("��λ���Ѿ������ӣ����������룺");
			return false;
		}
		return true;
	}

	/**
	 * ��ʼ����
	 */
	public void start() throws Exception {
		// trueΪ��Ϸ����
		boolean isOver = false;
		chessboard.initBoard();
		chessboard.printBoard();
		// ��ȡ���̵�����
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String inputStr = null;
		// br.readLine:ÿ����������һ�����ݰ��س���������������ݱ�br��ȡ��
		while ((inputStr = br.readLine()) != null) {
			isOver = false;
			if (!isValid(inputStr)) {
				// ������Ϸ���Ҫ���������룬�ټ���
				continue;
			}
			// �Ѷ�Ӧ������Ԫ�ظ�Ϊ"��"
			String chessman = Chessman.BLACK.getChessman();
			chessboard.setBoard(posX, posY, chessman);
			// �ж��û��Ƿ�Ӯ��
			if (isWon(posX, posY, chessman)) {
				isOver = true;

			} else {
				// ��������ѡ��λ������
				int[] computerPosArr = computerDo();
				chessman = Chessman.WHITE.getChessman();
				chessboard.setBoard(computerPosArr[0], computerPosArr[1],
						chessman);
				// �жϼ�����Ƿ�Ӯ��
				if (isWon(computerPosArr[0], computerPosArr[1], chessman)) {
					isOver = true;
				}
			}
			// �������ʤ�ߣ�ѯ���û��Ƿ������Ϸ
			if (isOver) {
				// ������������³�ʼ�����̣�������Ϸ
				if (isReplay(chessman)) {
					chessboard.initBoard();
					chessboard.printBoard();
					first=true;
					continue;
				}
				// ������������˳�����
				break;
			}
			chessboard.printBoard();
			System.out.println("����������������꣬Ӧ��x,y�ĸ�ʽ���룺");
		}
	}

	/**
	 * �Ƿ����¿�ʼ���塣
	 * 
	 * @param chessman
	 *            "��"Ϊ�û���"��"Ϊ�������
	 * @return ��ʼ����true�����򷵻�false��
	 */
	public boolean isReplay(String chessman) throws Exception {
		chessboard.printBoard();
		String message = chessman.equals(Chessman.BLACK.getChessman()) ? "��ϲ������Ӯ�ˣ�"
				: "���ź��������ˣ�";
		System.out.println(message + "����һ�֣�(y/n)");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		if (br.readLine().equals("y")) {
			// ��ʼ��һ��
			return true;
		}
		return false;

	}
    /** 
     * �ж�������,����Ϊ�û���������λ�ã����Ϊ���û����������ӵķ��λ��,���򷵻ؿ�
     */
    public int[] judgeThree(int posX,int posY){ //�������п��ܳ��ֵ��������ӵ�������ǣ�ÿһ��if elseΪһ�����
    	String ico="��";
    	int i=posX-1,j=posX+1; //�����ӵ���������λ�ã����㿼�Ǹ������������
		int p=posY-1,v=posY+1;
		int i1=posX-2,j1=posX+2;
		int p1=posY-2,v1=posY+2;
		String[][] ch=chessboard.getBoard();;
		if(i1>-1){  //�Ƿ�Խ��
			if(ch[i1][posY]==ico&&ch[i1+1][posY]==ico){   //һ�����������
				if(i1-1>-1&&ch[i1-1][posY]=="+"){  //һ�˿��Է��
					int[] result={i1-1,posY};
					return result;
				}	
				else if(ch[posX+1][posY]=="+"){   //����ǰһ���޷�����(Խ�������)��ѡ����һ��
					int[] result={posX+1,posY};
					return result;
				}
				else 
					return null;  //�޷����
			}	
	    }
		if(j1<22){
			if(ch[j1][posY]==ico&&ch[j1-1][posY]==ico){
				if(j1+1<22&&ch[j1+1][posY]=="+"){
					int[] result={j1+1,posY};
					return result;
				}	
				else if(ch[posX-1][posY]=="+"){
					int[] result={posX-1,posY};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(p1>-1){
			if(ch[posX][p1]==ico&&ch[posX][p1+1]==ico){
				if(p1-1>-1&&ch[posX][p1-1]=="+"){
					int[] result={posX,p1-1};
					return result;
				}	
				else if(ch[posX][posY+1]=="+"){
					int[] result={posX,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(v1<22){
			if(ch[posX][v1-1]==ico&&ch[posX][v1]==ico){
				if(v1+1<22&&ch[posX][v1+1]=="+"){
					int[] result={posX,v1+1};
					return result;
				}	
				else if(ch[posX][posY-1]=="+"){
					int[] result={posX,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(i1>-1&&p1>-1){
			if(ch[i1][p1]==ico&&ch[i1+1][p1+1]==ico){
				if(i1-1>-1&&p1-1>-1&&ch[i1-1][p1-1]=="+"){
					int[] result={i1-1,p1-1};
					return result;
				}	
				else if(ch[posX+1][posY+1]=="+"){
					int[] result={posX+1,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(j1<22&&p1>-1){
			if(ch[j1][p1]==ico&&ch[j1-1][p1+1]==ico){
				if(j1+1<22&&p1-1>-1&&ch[j1+1][p1-1]=="+"){
					int[] result={j1+1,p1-1};
					return result;
				}	
				else if(ch[posX-1][posY+1]=="+"){
					int[] result={posX-1,posY+1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(i1>-1&&v1<22){
			if(ch[i1][v1]==ico&&ch[i1+1][v1-1]==ico){
				if(i1-1>-1&&v1+1<22&&ch[i1-1][v1+1]=="+"){
					int[] result={i1-1,v1+1};
					return result;
				}	
				else if(ch[posX+1][posY-1]=="+"){
					int[] result={posX+1,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		if(j1<22&&v1<22){
			if(ch[j1][v1]==ico&&ch[j1-1][v1-1]==ico){
				if(j1+1<22&&v1+1<22&&ch[j1+1][v1+1]=="+"){
					int[] result={j1+1,v1+1};
					return result;
				}	
				else if(ch[posX-1][posY-1]=="+"){
					int[] result={posX-1,posY-1};
					return result;
				}
				else 
					return null;
			}	
	    }
		 if(i>-1&&j<22){
			 //System.out.println("33333");
		if(ch[i][posY]==ico&&ch[j][posY]==ico){
			if(i-1>-1&&ch[i-1][posY]=="+"){
				int[] result={i-1,posY};
				return result;
			}	
			else if(ch[j+1][posY]=="+"){
				int[] result={j+1,posY};
				return result;
			}
			else
			return null;
		}	
    }
		if(p>-1&&v<22){
			if(ch[posX][p]==ico&&ch[posX][v]==ico){
				if(p-1>-1&&ch[posX][p-1]=="+"){
					int[] result={posX,p-1};
					return result;
				}	
				else if(ch[posX][v+1]=="+"){
					int[] result={posX,v+1};
					return result;
				}
				else
					return null;
			}	
	    }
		if(p>-1&&i>-1&&v<22&&j<22){
			if(ch[i][p]==ico&&ch[j][v]==ico){
				if(p-1>-1&&i-1>-1&&ch[i-1][p-1]=="+"){
					int[] result={i-1,p-1};
					return result;
				}	
				else if(ch[j+1][v+1]=="+"){
					int[] result={j+1,v+1};
					return result;
				}
				else
					return null;
			}	
	    }
		if(p>-1&&i>-1&&v<22&&j<22){
			if(ch[i][v]==ico&&ch[j][p]==ico){
				if(i-1>-1&&v+1<22&&ch[i-1][v+1]=="+"){
					int[] result={i-1,v+1};
					return result;
				}	
				else if(ch[j+1][p-1]=="+"){
					int[] result={j+1,p-1};
					return result;
				}
				else 
					return null;
			}	
	    }
			return null;
    }
    /**
	 * ��ȡ��posX��posY�������Ӻ�����ӳ���,���ж���Ӯ�㷨���ƣ������¼���
	 */
    public int getLong(int posX,int posY,String ico){
    	int length;  //�洢����ӳ��� 
    	int i=posX-1,j=posX+1;
		int p=posY-1,v=posY+1;
		int i1=posX-1,j1=posX+1;
		int p1=posY-1,v1=posY+1;
		int i2=posX-1,j2=posX+1;
		int p2=posY-1,v2=posY+1;
		String[][] ch=new String[22][22];
		ch=chessboard.getBoard();
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j==22)    //�߽��ж�
				break;
			if(ch[j][posY]==ico)
				j++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i==-1)    //�߽��ж�
				break;
			if(ch[i][posY]==ico)
				i--;
			else
				break;
		}
		j--;
		i++;
		length=j-i+1;   //�����Ӹ���
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(v==22)   //�߽��ж�
				break;
			if(ch[posX][v]==ico)
				v++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(p==-1)    //�߽��ж�
				break;
			if(ch[posX][p]==ico)
				p--;
			else
				break;
		}
		v--;
		p++;
		if((v-p+1)>length)     
			length=v-p+1;     //���������
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j1==22||v1==22)  //�߽��ж�
				break;
			if(ch[j1][v1]==ico){
				j1++;
				v1++;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i1==-1||p1==-1)  //�߽��ж�
				break;
			if(ch[i1][p1]==ico&&p1>-1&&i1>-1){
				i1--;
				p1--;
			}
			else
				break;
		}
		j1--;
		i1++;
		if((j1-i1+1)>length)   
			length=j1-i1+1;    //���������
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j2==22||p2==-1)   //�߽��ж�
				break;
			if(ch[j2][p2]==ico){
				j2++;
				p2--;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i2==-1||v2==22)  //�߽��ж�
				break;
			if(ch[i2][v2]==ico){
				i2--;
				v2++;
			}
			else
				break;
		}
		j2--;
		i2++;
		if((j2-i2+1)>length)   //���������
			length=j2-i2+1;
		return length;
    }
	/**
	 * ������������
	 */
    int oldLong1,oldLong2;  //��¼�û��͵��Ե�����ӳ���
    int maxPsx,maxPsy;   //��¼�û��õ�������ӳ��ȵ���ʷλ��
    int oldPosX,oldPosY;//��¼������һ������λ��
    int maxPosX,maxPosY;//��¼���Եõ�������ӳ��ȵ���ʷλ��
    boolean first=true;  //�ж��Ƿ��һ������
	public int[] computerDo() {
		String[][] board = chessboard.getBoard();
		if(first){  //��һ�����壬��ʼ��
			if(board[11][11]=="+"){
				//System.out.print("111");
				int[] result = {11, 11};
				first=false;
				oldPosX=11;
				oldPosY=11;
				maxPosX=11;
				maxPosY=11;
				oldLong1=1;
				oldLong2=1;
				return result;
			}
			else{
				int[] result = {10, 10};
				first=false;
				oldPosX=10;
				oldPosY=10;
				maxPosX=10;
				maxPosY=10;
				oldLong1=1;
				oldLong2=1;
				return result;
			}		
		}
		int L1=getLong(posX,posY,"��");  //��ȡ�û�����һ��������������ӳ���
		if(L1>oldLong1){   //������ʷ���ȳ���������û���ʷ����
			oldLong1=L1;  
			maxPsx=posX;
			maxPsy=posY;
		}
		int L2=getLong(oldPosX,oldPosY,"��");  //��ȡ�����ϴ����Ӻ���������ӳ���
		if(L2>oldLong2) {   //������ʷ���ȳ�������µ�����ʷ���Ⱥ���ʷ������ӳ���λ��
			oldLong2=L2; 
			maxPosX=oldPosX;
			maxPosY=oldPosY;
		}
		if(oldLong2==4){    //���������ӳ���Ϊ4��һ�˱��û����,����һ�˻�ʤ������if��俼�Ǹ��ֿ������
			if(maxPosX-1>-1&&maxPosX+4<22){
				if(board[maxPosX-1][maxPosY]=="��"&&board[maxPosX+1][maxPosY]=="��"&&board[maxPosX+2][maxPosY]=="��"&&board[maxPosX+3][maxPosY]=="��"&&board[maxPosX+4][maxPosY]=="+")
					return new int[]{maxPosX+4,maxPosY};
			}
			if(maxPosX+1<22&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY]=="��"&&board[maxPosX-1][maxPosY]=="��"&&board[maxPosX-2][maxPosY]=="��"&&board[maxPosX-3][maxPosY]=="��"&&board[maxPosX-4][maxPosY]=="+")
					return new int[]{maxPosX-4,maxPosY};
			}
			if(maxPosY-1>-1&&maxPosY+4<22){
				if(board[maxPosX][maxPosY-1]=="��"&&board[maxPosX][maxPosY+1]=="��"&&board[maxPosX][maxPosY+2]=="��"&&board[maxPosX][maxPosY+3]=="��"&&board[maxPosX][maxPosY+4]=="+")
					return new int[]{maxPosX,maxPosY+4};
			}
			if(maxPosY+1<22&&maxPosY-4>-1){
				if(board[maxPosX][maxPosY+1]=="��"&&board[maxPosX][maxPosY-1]=="��"&&board[maxPosX][maxPosY-2]=="��"&&board[maxPosX][maxPosY-3]=="��"&&board[maxPosX][maxPosY-4]=="+")
					return new int[]{maxPosX,maxPosY-4};
			}
			if(maxPosY+1<22&&maxPosX+1<22&&maxPosY-4>-1&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY+1]=="��"&&board[maxPosX-1][maxPosY-1]=="��"&&board[maxPosX-2][maxPosY-2]=="��"&&board[maxPosX-3][maxPosY-3]=="��"&&board[maxPosX-4][maxPosY-4]=="+")
					return new int[]{maxPosX-4,maxPosY-4};
			}
			if(maxPosY-1>-1&&maxPosX-1>-1&&maxPosY+4<22&&maxPosX+4<22){
				if(board[maxPosX-1][maxPosY-1]=="��"&&board[maxPosX+1][maxPosY+1]=="��"&&board[maxPosX+2][maxPosY+2]=="��"&&board[maxPosX+3][maxPosY+3]=="��"&&board[maxPosX+4][maxPosY+4]=="+")
					return new int[]{maxPosX+4,maxPosY+4};
			}
			if(maxPosY-1>-1&&maxPosX+1<22&&maxPosY+4<22&&maxPosX-4>-1){
				if(board[maxPosX+1][maxPosY-1]=="��"&&board[maxPosX-1][maxPosY+1]=="��"&&board[maxPosX-2][maxPosY+2]=="��"&&board[maxPosX-3][maxPosY+3]=="��"&&board[maxPosX-4][maxPosY+4]=="+")
					return new int[]{maxPosX-4,maxPosY+4};
			}
			if(maxPosX-1>-1&&maxPosY+1<22&&maxPosX+4<22&&maxPosY-4>-1){
				if(board[maxPosX-1][maxPosY+1]=="��"&&board[maxPosX+1][maxPosY-1]=="��"&&board[maxPosX+2][maxPosY-2]=="��"&&board[maxPosX+3][maxPosY-3]=="��"&&board[maxPosX+4][maxPosY-4]=="+")
					return new int[]{maxPosX+4,maxPosY-4};
			}
		}
		if(oldLong2==3||oldLong2==4){    //�������������ӱ��û���ϵ����,��������ȣ�����if��俼�Ǹ��ֿ������
			if(maxPosX-1>-1&&maxPosX+3<22){
				if(board[maxPosX-1][maxPosY]=="��"&&board[maxPosX+1][maxPosY]=="��"&&board[maxPosX+2][maxPosY]=="��")
					oldLong2=1;
			}
			if(maxPosX-1>-1&&maxPosX+3<22&&maxPosY-3>-1&&maxPosY+1<22){
				if(board[maxPosX-1][maxPosY+1]=="��"&&board[maxPosX+1][maxPosY-1]=="��"&&board[maxPosX+2][maxPosY-2]=="��")
					oldLong2=1;
			}
			if(maxPosX-1>-1&&maxPosX+3<22&&maxPosY-3>-1&&maxPosY-1>-1){
				if(board[maxPosX-1][maxPosY-1]=="��"&&board[maxPosX+1][maxPosY+1]=="��"&&board[maxPosX+2][maxPosY+2]=="��")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1&&maxPosY+3<22&&maxPosY-1>-1){
				if(board[maxPosX+1][maxPosY-1]=="��"&&board[maxPosX-1][maxPosY+1]=="��"&&board[maxPosX-2][maxPosY+2]=="��")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1&&maxPosY-3>-1&&maxPosY+1<22){
				if(board[maxPosX+1][maxPosY+1]=="��"&&board[maxPosX-1][maxPosY-1]=="��"&&board[maxPosX-2][maxPosY-2]=="��")
					oldLong2=1;
			}
			if(maxPosX+1<22&&maxPosX-3>-1){
				if(board[maxPosX+1][maxPosY]=="��"&&board[maxPosX-1][maxPosY]=="��"&&board[maxPosX-2][maxPosY]=="��")
					oldLong2=1;
			}
			if(maxPosY-1>-1&&maxPosY+3<22){
				if(board[maxPosX][maxPosY-1]=="��"&&board[maxPosX][maxPosY+1]=="��"&&board[maxPosX][maxPosY+2]=="��")
					oldLong2=1;
			}
			if(maxPosY+1<22&&maxPosY-3>-1){
				if(board[maxPosX][maxPosY+1]=="��"&&board[maxPosX][maxPosY-1]=="��"&&board[maxPosX][maxPosY-2]=="��")
					oldLong2=1;
			}
			if(maxPosX+3<22){
				if(board[maxPosX+3][maxPosY]=="��"&&board[maxPosX+1][maxPosY]=="��"&&board[maxPosX+2][maxPosY]=="��")
					oldLong2=1;
			}
			if(maxPosX-3>-1){
				if(board[maxPosX-3][maxPosY]=="��"&&board[maxPosX-1][maxPosY]=="��"&&board[maxPosX-2][maxPosY]=="��")
					oldLong2=1;
			}
			if(maxPosX+3<22&&maxPosY+3<22){
				if(board[maxPosX+3][maxPosY+3]=="��"&&board[maxPosX+1][maxPosY+1]=="��"&&board[maxPosX+2][maxPosY+2]=="��")
					oldLong2=1;
			}
			if(maxPosX-3>-1&&maxPosY-3>-1){
				if(board[maxPosX-3][maxPosY-3]=="��"&&board[maxPosX-2][maxPosY-2]=="��"&&board[maxPosX-1][maxPosY-1]=="��")
					oldLong2=1;
			}
			if(maxPosX-3>-1&&maxPosY+3<22){
				if(board[maxPosX-3][maxPosY+3]=="��"&&board[maxPosX-1][maxPosY+1]=="��"&&board[maxPosX-2][maxPosY+2]=="��")
					oldLong2=1;
			}
			if(maxPosX+3<22&&maxPosY-3>-1){
				if(board[maxPosX+3][maxPosY-3]=="��"&&board[maxPosX+2][maxPosY-2]=="��"&&board[maxPosX+1][maxPosY-1]=="��")
					oldLong2=1;
			}
			if(maxPosY-3>-1){
				if(board[maxPosX][maxPosY-3]=="��"&&board[maxPosX][maxPosY-2]=="��"&&board[maxPosX][maxPosY-1]=="��")
					oldLong2=1;
			}
			if(maxPosY+3<22){
				if(board[maxPosX][maxPosY+3]=="��"&&board[maxPosX][maxPosY+2]=="��"&&board[maxPosX][maxPosY+1]=="��")
					oldLong2=1;
			}
			//System.out.print(oldLong2);
		}
		
		if(oldLong2<4){//����8��if else���Ϊ��������һ������Ӯ�������"���+���"�����
		if(posX+3<22&&posY-3>-1&&posX-1>-1&&posY+1<22){  //Խ���ж�
			if(board[posX+3][posY-3]=="��"&&board[posX+2][posY-2]=="��"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="��"){  
				return new int[]{posX+1,posY-1};
			}
			}
			if(posX-3>-1&&posY+3<22&&posX+1<22&&posY-1>-1){  //Խ���ж�
				if(board[posX-3][posY+3]=="��"&&board[posX-2][posY+2]=="��"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="��"){  
					return new int[]{posX-1,posY+1};
				}
				}
			if(posX+3<22&&posY+3<22&&posX-1>-1&&posY-1>-1){  //Խ���ж�
				if(board[posX+3][posY+3]=="��"&&board[posX+2][posY+2]=="��"&&board[posX+1][posY+1]=="+"&&board[posX-1][posY-1]=="��"){  
					return new int[]{posX+1,posY+1};
				}
				}
			if(posX+1<22&&posY+1<22&&posX-3>-1&&posY-3>-1){  //Խ���ж�
				if(board[posX-3][posY-3]=="��"&&board[posX-2][posY-2]=="��"&&board[posX-1][posY-1]=="+"&&board[posX+1][posY+1]=="��"){  
					return new int[]{posX-1,posY-1};
				}
				}
			if(posX+1<22&&posX-3>-1){  //Խ���ж�
				if(board[posX-3][posY]=="��"&&board[posX-2][posY]=="��"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="��"){  
					return new int[]{posX-1,posY};
				}
				}
			if(posX+3<22&&posX-1>-1){  //Խ���ж�
				if(board[posX+3][posY]=="��"&&board[posX+2][posY]=="��"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="��"){  
					return new int[]{posX+1,posY};
				}
				}
			if(posY+3<22&&posY-1>-1){  //Խ���ж�
				if(board[posX][posY+3]=="��"&&board[posX][posY+2]=="��"&&board[posX][posY+1]=="+"&&board[posX][posY-1]=="��"){  
					return new int[]{posX,posY+1};
				}
				}
			if(posY+1<22&&posY-3>-1){  //Խ���ж�
				if(board[posX][posY-3]=="��"&&board[posX][posY-2]=="��"&&board[posX][posY-1]=="+"&&board[posX][posY+1]=="��"){  
					return new int[]{posX,posY-1};
				}
				}
		}
		
		if(oldLong2<3){  //�ڵ���������ӳ���С��3��ʱ����Ҫ����"���+��"�������������if else��������24������ж�
			if(posX+4<22&&posY-4>-1&&posX-1>-1&&posY+1<22){  //Խ���ж�
			if(board[posX+4][posY-4]=="+"&&board[posX+3][posY-3]=="��"&&board[posX+2][posY-2]=="��"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="+"){  
				return new int[]{posX+1,posY-1};
			}
			}
			if(posX-4>-1&&posY+4<22&&posX+1<22&&posY-1>-1){  //Խ���ж�
				if(board[posX-4][posY+4]=="+"&&board[posX-3][posY+3]=="��"&&board[posX-2][posY+2]=="��"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="+"){  
					return new int[]{posX-1,posY+1};
				}
				}
			if(posX+4<22&&posY+4<22&&posX-1>-1&&posY-1>-1){  //Խ���ж�
				if(board[posX+4][posY+4]=="+"&&board[posX+3][posY+3]=="��"&&board[posX+2][posY+2]=="��"&&board[posX+1][posY+1]=="+"&&board[posX-1][posY-1]=="+"){  
					return new int[]{posX+1,posY+1};
				}
				}
			if(posX+1<22&&posY+1<22&&posX-4>-1&&posY-4>-1){  //Խ���ж�
				if(board[posX-4][posY-4]=="+"&&board[posX-3][posY-3]=="��"&&board[posX-2][posY-2]=="��"&&board[posX-1][posY-1]=="+"&&board[posX+1][posY+1]=="+"){  
					return new int[]{posX-1,posY-1};
				}
				}
			if(posX+1<22&&posX-4>-1){  //Խ���ж�
				if(board[posX-4][posY]=="+"&&board[posX-3][posY]=="��"&&board[posX-2][posY]=="��"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="+"){  
					return new int[]{posX-1,posY};
				}
				}
			if(posX+4<22&&posX-1>-1){  //Խ���ж�
				if(board[posX+4][posY]=="+"&&board[posX+3][posY]=="��"&&board[posX+2][posY]=="��"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="+"){  
					return new int[]{posX+1,posY};
				}
				}
			if(posY+4<22&&posY-1>-1){  //Խ���ж�
				if(board[posX][posY+4]=="+"&&board[posX][posY+3]=="��"&&board[posX][posY+2]=="��"&&board[posX][posY+1]=="+"&&board[posX][posY-1]=="+"){  
					return new int[]{posX,posY+1};
				}
				}
			if(posY+1<22&&posY-4>-1){  //Խ���ж�
				if(board[posX][posY-4]=="+"&&board[posX][posY-3]=="��"&&board[posX][posY-2]=="��"&&board[posX][posY-1]=="+"&&board[posX][posY+1]=="+"){  
					return new int[]{posX,posY-1};
				}
				}
			
			if(posX+3<22&&posY-3>-1&&posX-2>-1&&posY+2<22){  //Խ���ж�
				if(board[posX+3][posY-3]=="+"&&board[posX+2][posY-2]=="��"&&board[posX+1][posY-1]=="+"&&board[posX-1][posY+1]=="��"&&board[posX-2][posY+2]=="+"){  
					return new int[]{posX+1,posY-1};
				}
				}
				if(posX-3>-1&&posY+3<22&&posX+2<22&&posY-2>-1){  //Խ���ж�
					if(board[posX-3][posY+3]=="+"&&board[posX-2][posY+2]=="��"&&board[posX-1][posY+1]=="+"&&board[posX+1][posY-1]=="��"&&board[posX+2][posY-2]=="+"){  
						return new int[]{posX-1,posY+1};
					}
					}
				if(posX+2<22&&posY+2<22&&posX-3>-1&&posY-3>-1){  //Խ���ж�
					if(board[posX+2][posY+2]=="+"&&board[posX+1][posY+1]=="��"&&board[posX-3][posY-3]=="+"&&board[posX-1][posY-1]=="+"&&board[posX-2][posY-2]=="��"){  
						return new int[]{posX-1,posY-1};
					}
					}
				if(posX+3<22&&posY+3<22&&posX-2>-1&&posY-2>-1){  //Խ���ж�
					if(board[posX-2][posY-2]=="+"&&board[posX+2][posY+2]=="��"&&board[posX-1][posY-1]=="��"&&board[posX+1][posY+1]=="+"&&board[posX+3][posY+3]=="+"){  
						return new int[]{posX+1,posY+1};
					}
					}
				if(posX+2<22&&posX-3>-1){  //Խ���ж�
					if(board[posX-3][posY]=="+"&&board[posX-2][posY]=="��"&&board[posX-1][posY]=="+"&&board[posX+1][posY]=="��"&&board[posX+2][posY]=="+"){  
						return new int[]{posX-1,posY};
					}
					}
				if(posX+3<22&&posX-2>-1){  //Խ���ж�
					if(board[posX+3][posY]=="+"&&board[posX+2][posY]=="��"&&board[posX+1][posY]=="+"&&board[posX-1][posY]=="��"&&board[posX-2][posY]=="+"){  
						return new int[]{posX+1,posY};
					}
					}
				if(posY+3<22&&posY-2>-1){  //Խ���ж�
					if(board[posX][posY+3]=="+"&&board[posX][posY+2]=="��"&&board[posX][posY-1]=="��"&&board[posX][posY+1]=="+"&&board[posX][posY-2]=="+"){  
						return new int[]{posX,posY+1};
					}
					}
				if(posY+2<22&&posY-3>-1){  //Խ���ж�
					if(board[posX][posY-3]=="+"&&board[posX][posY-2]=="��"&&board[posX][posY+1]=="��"&&board[posX][posY-1]=="+"&&board[posX][posY+2]=="+"){  
						return new int[]{posX,posY-1};
					}
					}
				
				if(posX+4<22&&posY-4>-1&&posX-1>-1&&posY+1<22){  //Խ���ж�
					if(board[posX+4][posY-4]=="+"&&board[posX+3][posY-3]=="��"&&board[posX+2][posY-2]=="+"&&board[posX+1][posY-1]=="��"&&board[posX-1][posY+1]=="+"){  
						return new int[]{posX+2,posY-2};
					}
					}
					if(posX-4>-1&&posY+4<22&&posX+1<22&&posY-1>-1){  //Խ���ж�
						if(board[posX-4][posY+4]=="+"&&board[posX-3][posY+3]=="��"&&board[posX-2][posY+2]=="+"&&board[posX-1][posY+1]=="��"&&board[posX+1][posY-1]=="+"){  
							return new int[]{posX-2,posY+2};
						}
						}
					if(posX+4<22&&posY+4<22&&posX-1>-1&&posY-1>-1){  //Խ���ж�
						if(board[posX+4][posY+4]=="+"&&board[posX+3][posY+3]=="��"&&board[posX+2][posY+2]=="+"&&board[posX+1][posY+1]=="��"&&board[posX-1][posY-1]=="+"){  
							return new int[]{posX+2,posY+2};
						}
						}
					if(posX+1<22&&posY+1<22&&posX-4>-1&&posY-4>-1){  //Խ���ж�
						if(board[posX-4][posY-4]=="+"&&board[posX-3][posY-3]=="��"&&board[posX-2][posY-2]=="+"&&board[posX-1][posY-1]=="��"&&board[posX+1][posY+1]=="+"){  
							return new int[]{posX-2,posY-2};
						}
						}
					if(posX+1<22&&posX-4>-1){  //Խ���ж�
						if(board[posX-4][posY]=="+"&&board[posX-3][posY]=="��"&&board[posX-2][posY]=="+"&&board[posX-1][posY]=="��"&&board[posX+1][posY]=="+"){  
							return new int[]{posX-2,posY};
						}
						}
					if(posX+4<22&&posX-1>-1){  //Խ���ж�
						if(board[posX+4][posY]=="+"&&board[posX+3][posY]=="��"&&board[posX+2][posY]=="+"&&board[posX+1][posY]=="��"&&board[posX-1][posY]=="+"){  
							return new int[]{posX+2,posY};
						}
						}
					if(posY+4<22&&posY-1>-1){  //Խ���ж�
						if(board[posX][posY+4]=="+"&&board[posX][posY+3]=="��"&&board[posX][posY+2]=="+"&&board[posX][posY+1]=="��"&&board[posX][posY-1]=="+"){  
							return new int[]{posX,posY+2};
						}
						}
					if(posY+1<22&&posY-4>-1){  //Խ���ж�
						if(board[posX][posY-4]=="+"&&board[posX][posY-3]=="��"&&board[posX][posY-2]=="+"&&board[posX][posY-1]=="��"&&board[posX][posY+1]=="+"){  
							return new int[]{posX,posY-2};
						}
						}
		}
		
		if(oldLong2>=oldLong1||(oldLong2<oldLong1&&oldLong1<3)||judgeThree(posX,posY)==null){ //���������ӳ��Ȳ�С���û�����С���û������û�������ӳ���û��3�����Ҳ������λ�õ�ʱ�򣬿���ֱ���ҵõ�������ӳ��ȵ�λ������
			//System.out.print("333");
			int q1 = -1,q2=-1,q3=-1,q4=-1,q5=-1,q6=-1,q7=-1,q8=-1;   
		    int max=0;  //����if else��������������Χ8��λ���ж��ĸ�λ�����ӵõ�����ӳ��ȣ�ֱ�ӷ������λ��
			if(board[maxPosX-1][maxPosY] == "+"&&(maxPosX-1)>-1){  //��Խ���ҿ�����
				 q1=getLong(maxPosX-1,maxPosY,"��");  //��ȡ���ӳ���
				 if(q1>max)
					 max=q1;
				 //System.out.print(q1);
			}
			if(board[maxPosX-1][maxPosY-1] == "+"&&(maxPosX-1)>-1&&(maxPosY-1)>-1){
				 q2=getLong(maxPosX-1,maxPosY-1,"��");
				 if(q2>max)
					 max=q2;
			}
			if(board[maxPosX][maxPosY-1] == "+"&&(maxPosY-1)>-1){
				 q3=getLong(maxPosX,maxPosY-1,"��");
				 if(q3>max)
					 max=q3;
			}
			if(board[maxPosX+1][maxPosY-1] == "+"&&(maxPosX+1)<22&&(maxPosY-1)>-1){
				 q4=getLong(maxPosX+1,maxPosY-1,"��");
				 if(q4>max)
					 max=q4;
			}
			if(board[maxPosX+1][maxPosY] == "+"&&(maxPosX+1)<22){
				 q5=getLong(maxPosX+1,maxPosY,"��");
				 if(q5>max)
					 max=q5;
			}
			if(board[maxPosX+1][maxPosY+1] == "+"&&(maxPosX+1)<22&&(maxPosY+1)<22){
				 q6=getLong(maxPosX+1,maxPosY+1,"��");
				 if(q6>max)
					 max=q6;
			}
			if(board[maxPosX][maxPosY+1] == "+"&&(maxPosY+1)<22){
				 q7=getLong(maxPosX,maxPosY+1,"��");
				 if(q7>max)
					 max=q7;
			}
			if(board[maxPosX-1][maxPosY+1] == "+"&&(maxPosY-1)>-1&&(maxPosY+1)<22){
				 q8=getLong(maxPosX-1,maxPosY+1,"��");
				 if(q8>max)
					 max=q8;
			}
			if(max==q1){
				maxPosX--;
				int[] result={maxPosX,maxPosY};  //���»�ȡ��������ӳ��ȵ�λ��
				//System.out.print(maxPosX+"omg"+maxPosY);
				oldPosX=maxPosX;   //��¼��һ������λ��
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q2){
				maxPosX--;
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q3){
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q4){
				maxPosX++;
				maxPosY--;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q5){
				maxPosX++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q6){
				maxPosX++;
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
			if(max==q7){
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;	
				return result;
			}	
			if(max==q8){
				maxPosX--;
				maxPosY++;
				int[] result={maxPosX,maxPosY};
				oldPosX=maxPosX;
				oldPosY=maxPosY;
				return result;
			}	
		}	
		//System.out.print("222");
			return judgeThree(posX,posY);   //��������������Ļ���Ȼ����Ҫ�����û�
				
	}

	/**
	 * �ж���Ӯ
	 * 
	 * @param posX
	 *            ���ӵ�X���ꡣ
	 * @param posY
	 *            ���ӵ�Y����
	 * @param ico
	 *            ��������
	 * @return ��������������������һ��ֱ�ӣ������棬�����෴��
	 */
	public boolean isWon(int posX, int posY, String ico) {
		int i=posX-1,j=posX+1;
		int p=posY-1,v=posY+1;
		int i1=posX-1,j1=posX+1;
		int p1=posY-1,v1=posY+1;
		int i2=posX-1,j2=posX+1;
		int p2=posY-1,v2=posY+1;
		String[][] ch=new String[22][22];
		ch=chessboard.getBoard();
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j==22)    //�߽��ж�
				break;
			if(ch[j][posY]==ico)  //λ���ƶ�
				j++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i==-1)    //�߽��ж�
				break;
			if(ch[i][posY]==ico)
				i--;
			else
				break;
		}
		j--;
		i++;
		if(j-i+1>=5)   //�����ж��Ƿ�5����
			return true;
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(v==22)   //�߽��ж�
				break;
			if(ch[posX][v]==ico)
				v++;
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(p==-1)    //�߽��ж�
				break;
			if(ch[posX][p]==ico)
				p--;
			else
				break;
		}
		v--;
		p++;
		if(v-p+1>=5)   //�����ж��Ƿ�5����
			return true;
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j1==22||v1==22)  //�߽��ж�
				break;
			if(ch[j1][v1]==ico){
				j1++;
				v1++;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i1==-1||p1==-1)  //�߽��ж�
				break;
			if(ch[i1][p1]==ico&&p1>-1&&i1>-1){
				i1--;
				p1--;
			}
			else
				break;
		}
		j1--;
		i1++;
		if(j1-i1+1>=5)   //�����ϵ����µĶԽ����ж��Ƿ�5����
			return true;
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(j2==22||p2==-1)   //�߽��ж�
				break;
			if(ch[j2][p2]==ico){
				j2++;
				p2--;
			}
			else
				break;
		}
		for(int u=0;u<4;u++){   //�Ҳ��ϵ���Զλ��
			if(i2==-1||v2==22)  //�߽��ж�
				break;
			if(ch[i2][v2]==ico){
				i2--;
				v2++;
			}
			else
				break;
		}
		j2--;
		i2++;
		if(j2-i2+1>=5)   //�����µ����ϵĶԽ����ж��Ƿ�5����
			return true;
		return false;
	}

	public static void main(String[] args) throws Exception {

		GobangGame gb = new GobangGame(new Chessboard());
		gb.start();
	}
}

