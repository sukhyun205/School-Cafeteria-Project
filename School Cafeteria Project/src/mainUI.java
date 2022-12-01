import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.colorchooser.ColorChooserComponentFactory;
// 재고 관리표 만들기 위해
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;



//융프2 프로젝트
//AWT, Swing패키지를 활용한 학식주문 키오스크 프로그램
//utf-8


//class Kitchen extends JFrame implements ActionListener{  //재고관리를 위한 Kitchen클래스
//	
//    public Kitchen(String [] menu_name, int [] menu_rest_num){
//        super("주방");
//        setSize(1000, 1000);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        Container contentPane = getContentPane();
//		contentPane.setLayout(new BorderLayout(10, 20));
//		
//		JLabel la1 = new JLabel("재고상황");
//		la1.setHorizontalAlignment(JLabel.CENTER);
//		la1.setFont(new Font("고딕체", Font.BOLD, 30));
//		la1.setOpaque(true);
//		la1.setBackground(Color.GRAY);
//		contentPane.add(la1, BorderLayout.NORTH);
//		
//		String menu_rest = "";
//		for (int i=0; i<menu_name.length; i++) {
//			menu_rest = menu_rest+"<br>"+menu_name[i]+"의 재고: "+menu_rest_num[i];
//		}
//		
//		JLabel la2 = new JLabel("<html><body><center>"
//				+ menu_rest
//				+ "</center></body></html>");
//		la2.setFont(new Font("고딕체", Font.ITALIC, 20));
//		contentPane.add(la2, BorderLayout.CENTER);
//		
//        JButton bt = new JButton("닫기");
//        contentPane.add(bt, BorderLayout.SOUTH);
//        bt.addActionListener(this);
//        bt.setFont(new Font("고딕체", Font.BOLD, 30));
//        setLocation(200, 0);
//        setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        // TODO Auto-generated method stub
//        dispose();
//    }
//}

class Kitchen extends JFrame implements ActionListener{  //재고관리를 위한 Kitchen클래스
	
    public Kitchen(String [] menu_name, int [] menu_rest_num){
        JFrame f = new JFrame("재고 사항");
        Object data[][] = {
                {menu_name[0], menu_rest_num[0]},
                {menu_name[1], menu_rest_num[1]},
                {menu_name[2], menu_rest_num[2]},
                {menu_name[3], menu_rest_num[3]},
                {menu_name[4], menu_rest_num[4]},
                {menu_name[5], menu_rest_num[5]},
                {menu_name[6], menu_rest_num[6]},
                {menu_name[7], menu_rest_num[7]},
                {menu_name[8], menu_rest_num[8]},
                {menu_name[9], menu_rest_num[9]},
                {menu_name[10], menu_rest_num[10]},
                {menu_name[11], menu_rest_num[11]}
                
         };
        

         Object column[]= {"메뉴","재고량"};

         JTable jt=new JTable(data,column);
         jt.setCellSelectionEnabled(true);
         ListSelectionModel select= jt.getSelectionModel();
         select.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
         select.addListSelectionListener(new ListSelectionListener() {
             public void valueChanged(ListSelectionEvent e) {
                   String Data = null;
                   int[] row = jt.getSelectedRows();

                   for(int i = 0; i < row.length; i++) {
                	   Data = (String)jt.getValueAt(row[i], i);

           }
           System.out.println("Table element selected is: " + Data);
           JOptionPane.showMessageDialog(f,"선택된 테이블은 " + Data + " 입니다");
           }
        });
        JScrollPane sp=new JScrollPane(jt);
        f.add(sp);
        f.setSize(400, 300);
        f.setVisible(true);
        
        JButton bt = new JButton("닫기");
        f.add(bt, BorderLayout.SOUTH);
        bt.addActionListener(this);
        bt.setFont(new Font("고딕체", Font.BOLD, 30));
        setLocation(200, 0);
//        setVisible(true);
		
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
    }
}

//학식주문의 mainUI클래스
public class mainUI extends JFrame implements ActionListener {  
	Kitchen kitchen;
    //메뉴판배열 menu_name[12] 6가지 메인메뉴 + 3가지 사이드메뉴 + 3가지 음료수메뉴 
    String menu_name[] = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6", "사이드1", "사이드2", "사이드3","음료수1", "음료수2", "음료수3"};
	//재고수량배열 menu_rest_num[12]
    int menu_rest_num[]= {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
    int count[]; int total=0; int col=0; int row=0; String contents = "";

	public mainUI(){
		//전체 frame 생성
		JFrame frame = new JFrame("학식주문 키오스크");

		//상단North패널 프로젝트 제목 추가
		Panel NorthPanel = new Panel();            //상단North패널 생성
        NorthPanel.setBackground(Color.ORANGE);    //상단North패널 배경색 설정
        NorthPanel.setLayout(new FlowLayout());
		JLabel highbar = new JLabel();
		highbar = new JLabel("<상록원 키오스크>");
		highbar.setForeground(Color.white);
		highbar.setFont(new Font("Serif", Font.BOLD, 35));
		NorthPanel.add(highbar);                   

		Panel CenterPanel = new Panel();           //중앙Center패널 생성
		CenterPanel.setLayout(null);          //중앙Center패널 배경색 설정
		CenterPanel.setBackground(Color.white);

		//중앙패널 메뉴배열, 메뉴텍스트배열
		String menu[] = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6", "사이드1", "사이드2", "사이드3","음료수1", "음료수2"};
		String menu_txt[] = {"main: 제육덮밥", "main: 비빔밥 ", "main: 비빔국수 ", "main: 닭볶음탕", "main: 치킨덮밥", "main: 치즈불닭", "side: 감자튀김", "side: 소떡소떡", "side: 물만두","drink: 콜라", "drink: 사이다"};
		int price[] = 		   //가격배열
			{6000, 5000, 4500, //메인메뉴 1,2,3 가격
			 6000, 5000, 6000, //메인메뉴 4,5,6 가격 
			 2500, 2500, 2500, //사이드메뉴 1,2,3 가격 
			 1500, 1500};      //음료 1,2 가격
		
		JButton bt_menu[] = new JButton[menu.length]; //메뉴 이미지를 띄우기 위한 bt_menu[]생성
		TextField num[] = new TextField[menu.length]; //주문 수량[]
		Button minus[] = new Button[menu.length];     //수량 '-'[]
		Button plus[] = new Button[menu.length];      //수량 '+'[]
		JButton ok[] = new JButton[menu.length];      //주문담기 버튼[]
		Label won[] = new Label[menu.length];		  //메뉴가격[]
		Label name[] = new Label[menu.length];		  //메뉴이름[]
		this.count = new int [menu.length];
		Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 14); //메뉴 이름 폰트, 볼드, 사이즈설정

		//bt_menu[]에 메뉴 이미지 삽입
		bt_menu[0] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\제육덮밥.jpg"));
		bt_menu[1] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\비빔밥.jpg"));
		bt_menu[2] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\비빔국수.jpg"));
		bt_menu[3] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\닭볶음탕.jpg"));
		bt_menu[4] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\치킨덮밥.jpg"));
		bt_menu[5] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\치즈불닭.jpg"));
		bt_menu[6] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\감자튀김.png"));
		bt_menu[7] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\소떡소떡.jpg"));
		bt_menu[8] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\물만두.png"));
		bt_menu[9] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\콜라.jpg"));
		bt_menu[10] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\사이다.png"));

		//반복문을 통해 JButton 학식메뉴를 주문화면에 뿌려준다.
		for (int i = 0; i < menu.length; i++) {

			if(i<3) {       //메뉴1,2,3을 setBounds()에 따라 레이아웃 구성
				bt_menu[i].setBounds(75 + i * 220, 40, 150, 105); 
			}
			else if(i<6) {  //메뉴4,5,6을 setBounds()에 따라 레이아웃 구성
				bt_menu[i].setBounds(75 + (i-3) * 220, 260, 150, 105);  
			}
			else {			//사이드메뉴1,2,3 및 음료메뉴1,2를 setBounds()에 따라 레이아웃 구성
				bt_menu[i].setBounds(90 + (i - 6) * 119, 480, 100, 100);
			}
			
			name[i] = new Label(menu_txt[i]);    //메뉴이름 구성
			name[i].setFont(font1);              //메뉴이름 폰트설정
			name[i].setBounds(bt_menu[i].getX()-3, bt_menu[i].getY() - 20, 115, 20);  //메뉴이름 레이아웃
			
			num[i] = new TextField("0");    //수량버튼 0초기화
			num[i].setBackground(Color.white);   //수량버튼 배경: white
			num[i].setEditable(false);        //수량버튼 활성화
			num[i].setBounds(bt_menu[i].getX() + 30, bt_menu[i].getY() + 130, 40, 20); //수량버튼 레이아웃
			
			minus[i] = new Button("-");   //수량 감소버튼 '-'
			minus[i].setBounds(bt_menu[i].getX(), num[i].getY(), 20, 20);              //수량 감소버튼 '-' 레이아웃
			minus[i].setEnabled(true);		 //수량 감소버튼 '-' 활성화
			
			plus[i] = new Button("+");	 //수량 증가버튼 '+'
			plus[i].setBounds(bt_menu[i].getX() + (100 - 20), num[i].getY(), 20, 20);  //수량 증가버튼 '+' 레이아웃
			plus[i].setEnabled(true);		 //수량 증가버튼 '+' 활성화
			
			won[i] = new Label(price[i] + "원"); //메뉴 가격:price + "원"
			won[i].setBounds(bt_menu[i].getX() + 30, num[i].getY() - 25, 100, 20);     //메뉴 가격:price + "원" 레이아웃
			
			ok[i] = new JButton("주문담기");   //주문담기 (주문확인창에 추가)
			ok[i].setBounds(bt_menu[i].getX(), num[i].getY() + 30, 100, 20);           //주문담기버튼 레이아웃
			ok[i].setEnabled(false);              //주문담기버튼을 비활성화
			
			//중앙 패널에 메뉴이름, 수량조절버튼, 가격+"원", "확인", 주문담기버튼 추가
			CenterPanel.add(name[i]);
			CenterPanel.add(bt_menu[i]);
			CenterPanel.add(num[i]);
			CenterPanel.add(minus[i]);
			CenterPanel.add(plus[i]);
			CenterPanel.add(won[i]);
			CenterPanel.add(ok[i]);
			
			JButton bt = new JButton("재고상황");
			bt.setBounds(275, 680, 200, 40);  //재고상황버튼 위치
			CenterPanel.add(bt);
			bt.addActionListener(this);
		}

			//주문담기창 EastPanel 생성
			Panel EastPanel = new Panel();
			String[] [] data = new String[0][0];						 		  //주문담기창 2차원배열[][] 생성
			String[] title = {"상품명","단가","수량","합계", "총 금액"}; 		   //주문담기창 태그 5가지
			TextArea txt = new TextArea("");
			DefaultTableModel model = new DefaultTableModel(data, title);		  //주문담기 데이터, 태그5가지로 model 구성
			JTable table = new JTable(model); 									  //model을 통해 table 구성
			JScrollPane scrollPane = new JScrollPane(table);			  		  //주문담기 창이 부족할것을 대비하여 스크롤 구성
			scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(420,730));    //주문담기창 사이즈
			EastPanel.add(scrollPane);    										  //주문담기창 추가

			//주요기능 3가지 버튼(종료, 리셋, 주문하기)이 있는 SelectPanel생성		
			Panel SelectPanel = new Panel();									   
			SelectPanel.setLayout(new GridLayout(3,1,50,0)); //메뉴선택 SelectPanel 레이아웃
			JButton order[] = new JButton[3];

			//레이아웃 위치 설정
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(NorthPanel, BorderLayout.NORTH);       //프로젝트제목 NorthPanel
			frame.add(CenterPanel, BorderLayout.CENTER);	 //메뉴판 CenterPanel
			frame.add(EastPanel, BorderLayout.EAST);		 //주문담기창 EastPanel
			EastPanel.setBackground(Color.white);
			frame.add(SelectPanel, BorderLayout.WEST);		 //주요기능 선택창 SelectPanel
			frame.setSize(1250, 850);          //학식주문 전체창 사이즈
			frame.setVisible(true);
			
			//주요기능 3가지 버튼
			order[0] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\close.png"));  //close이미지 삽입
			order[1] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\reset.png"));  //reset이미지 삽입
			order[2] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\order.png"));  //order이미지 삽입
			order[0].setBackground(Color.WHITE);	//close버튼 배경: white
			order[1].setBackground(Color.WHITE);	//reset버튼 배경: white
			order[2].setBackground(Color.WHITE);	//order버튼 배경: white
			SelectPanel.add(order[0]);	//close버튼 추가
			SelectPanel.add(order[1]);	//reset버튼 추가
			SelectPanel.add(order[2]);	//order버튼 추가

			// 1.종료 버튼
			order[0].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					System.exit(0); 	   //시스템 종료 메소드() 실행으로 종료
				}
			});
			
			// 2.주문담기 리셋 버튼
			order[1].addActionListener(new ActionListener() {
		
				@Override
				public void actionPerformed(ActionEvent e) {
					model.setNumRows(0); //주문담기 내역 model을 초기화하여 주문담기 리셋
					txt.setText("");            //{상품명, 단가, 수량, 합계, 총금액}의 text 또한 리셋
					total=0;					   //total: 총금액 또한 리셋
				}
			});
		
			// 3.주문하기 버튼
			order[2].addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					//3-1) "주문하시겠습니까?" 메세지에 "YES"라고 답할때
					int answer = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "Order",JOptionPane.YES_NO_OPTION);
					if(answer == JOptionPane.YES_OPTION)
					{	
						//total: 총 금액이 0일경우 주문불가 및 "선택 항목이 존재하지 않습니다." 메세지 출력
						if(total==0){JOptionPane.showMessageDialog(null, "선택 항목이 존재하지 않습니다.");}
	
						//total: 총 금액이 0이 아닐경우, 주문담기 table의 해당 단가, 수량을 가져와서 메세지 출력
						else {for(int i=0; i<table.getRowCount(); i++) {txt.append(table.getValueAt(i, 0)+" "+table.getValueAt(i, 1)+" X "+table.getValueAt(i, 2)+"개\n");}
							
							//예외상황처리
							contents = txt.toString(); //contents: 주문담기의 메뉴이름 ex) main:제육덮밥, side:감자튀김

							//main메뉴를 선택하지 않고, side메뉴만 선택했을시 안내 메세지 출력
							if(!contents.contains("main:") && contents.contains("side:"))
							{JOptionPane.showMessageDialog(null, "메인메뉴가 선택되지 않았습니다.\n사이드메뉴는 메인메뉴 선택 시 추가할 수 있습니다.\n음료수는 메인메뉴와 상관없이 주문가능합니다."); 
							txt.setText("");}

							//정상적으로 main메뉴와 side메뉴를 함께 선택했다면 "총 OO원 결제 메세지" 출력
							else {JOptionPane.showMessageDialog(null, txt.getText()+"총 "+total+"원 결제되었습니다. \n감사합니다.");
							total=0; 							//total: 총금액 초기화
							txt.setText(""); 				//주문담기창 텍스트, 값 초기화
							model.setNumRows(0);}
						}
					}
					//3-2) "주문하시겠습니까?" 메세지에 "NO"라고 답할때, 다시 mainUI로 복귀
					else
						JOptionPane.showMessageDialog(null, "메뉴 선택 단계로 돌아갑니다.\n");
	
					for (int i = 0; i < menu.length; i++) {
						num[i].setText("0");  //초기화
					}
				}
			});

			//수량+/-버튼, 확인버튼
			for (int i = 0; i < menu.length; i++) {
				int j = i;
		
				bt_menu[i].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						for (int k=0; k<menu.length; k++) {
							count[k] = 0;
						}
					}
				});
		
				minus[i].addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if (menu_rest_num[j]==0){
							minus[j].setEnabled(false);
						} 
						if (count[j] > 0) {
							count[j] = count[j] - 1;
							num[j].setText(count[j] + "");
							ok[j].setEnabled(true);
						} else {
							minus[j].setEnabled(false);
						}         
					}
				});
				
				plus[i].addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						if (count[j]>menu_rest_num[j]-1) {
							JOptionPane.showMessageDialog(null, "재고가 부족합니다. 수량을 줄여주세요.");
							count[j]=count[j]-1;
						}
						if (menu_rest_num[j]==0){
							plus[j].setEnabled(false);
						} 
						count[j] = count[j] + 1;
						num[j].setText(count[j] + "");
						ok[j].setEnabled(true);
						if (count[j] > 0) {
							minus[j].setEnabled(true);
						}
					}
					});
				
				ok[i].addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {

						menu_rest_num[j] = menu_rest_num[j]-count[j];
						
						num[j].setText("0");
						

						if ((menu_rest_num[j]>=0)&& (count[j]!=0)){
								String inputStr[] = new String [5];
								
								inputStr[0] = menu_txt[j];
								inputStr[1] = price[j]+"원";
								inputStr[2] = ""+count[j];
								inputStr[3] = price[j] * count[j]+"원";
								total= total+price[j]*count[j];
								inputStr[4] = total+"원";
								count[j]=0;
								model.addRow(inputStr);
					
								ok[j].setEnabled(false);
							
						}
						else {
							count[j]=0;
							ok[j].setEnabled(false);
						} 
						
					}
				});
			}

	}

    @Override
	public void actionPerformed(ActionEvent e) { //재고 갱신을 위한 메소드?
		// TODO Auto-generated method stub
		if(kitchen == null){
	        kitchen = new Kitchen(menu_name, menu_rest_num);
	    }else{
	    	kitchen.dispose();
	        kitchen = new Kitchen(menu_name, menu_rest_num);
	    }
	}
    
    public static void main(String[] args) {
		new mainUI();
	}
}
