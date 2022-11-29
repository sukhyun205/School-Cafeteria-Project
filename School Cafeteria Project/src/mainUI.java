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

//융프2 프로젝트
//AWT, Swing패키지를 활용한 학식주문 키오스크 프로그램
//utf-8

class Kitchen extends JFrame implements ActionListener{  //재고관리를 위한 Kitchen클래스

    public Kitchen(String [] menu_name, int [] menu_rest_num){
        super("주방");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout(10, 20));
		
		JLabel la1 = new JLabel("재고상황");
		la1.setHorizontalAlignment(JLabel.CENTER);
		la1.setFont(new Font("고딕체", Font.BOLD, 30));
		la1.setOpaque(true);
		la1.setBackground(Color.GRAY);
		contentPane.add(la1, BorderLayout.NORTH);
		
		String menu_rest = "";
		for (int i=0; i<menu_name.length; i++) {
			menu_rest = menu_rest+"<br>"+menu_name[i]+"의 재고: "+menu_rest_num[i];
		}
		
		JLabel la2 = new JLabel("<html><body><center>"
				+ menu_rest
				+ "</center></body></html>");
		la2.setFont(new Font("고딕체", Font.ITALIC, 20));
		contentPane.add(la2, BorderLayout.CENTER);
		
        JButton bt = new JButton("닫기");
        contentPane.add(bt, BorderLayout.SOUTH);
        bt.addActionListener(this);
        setLocation(200, 0);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        dispose();
    }
}

public class mainUI extends JFrame implements ActionListener {  //학식주문의 mainUI클래스
	Kitchen kitchen;
    //메뉴판배열 menu_name[12] 6가지 메인메뉴 + 3가지 사이드메뉴 + 3가지 음료수메뉴 
    String menu_name[] = {"메뉴1", "메뉴2", "메뉴3", "메뉴4", "메뉴5", "메뉴6", "사이드1", "사이드2", "사이드3","음료수1", "음료수2", "음료수3"};
	//재고수량배열 menu_rest_num[12]
    int menu_rest_num[]= {10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};

    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 13);
    int count[]; int total=0; int col=0; int row=0; String contents = "";  //민경님이 count를 배열[]로 수정

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
		String menu_txt[] = {"main: 제육덮밥", "main: 제육덮밥 ", "main: 제육덮밥 ", "main: 치즈불닭", "main: 치즈불닭", "main: 치즈불닭", "side: 감자튀김", "side: 감자튀김", "side: 감자튀김","drink: 콜라", "drink: 콜라"};
		//가격배열
		int price[] = {4000, 4500, 4500, 4700,
			2500, 2500, 2700, 3300,
			3500, 3500, 3500, 3000};
		
			JButton bt_menu[] = new JButton[menu.length];
			TextField num[] = new TextField[menu.length];
			Button minus[] = new Button[menu.length];
			Button plus[] = new Button[menu.length];
			JButton ok[] = new JButton[menu.length];
			Label won[] = new Label[menu.length];
			Label name[] = new Label[menu.length];
			this.count = new int [menu.length];

			//메뉴 이미지 설정
			bt_menu[0] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\제육덮밥.jpg"));
			bt_menu[1] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\제육덮밥.jpg"));
			bt_menu[2] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\제육덮밥.jpg"));
			bt_menu[3] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\치즈불닭.jpg"));
			bt_menu[4] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\치즈불닭.jpg"));
			bt_menu[5] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\치즈불닭.jpg"));
			bt_menu[6] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\감자튀김.png"));
			bt_menu[7] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\감자튀김.png"));
			bt_menu[8] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\감자튀김.png"));
			bt_menu[9] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\콜라.jpg"));
			bt_menu[10] = new JButton(new ImageIcon("School Cafeteria Project\\img\\menu\\콜라.jpg"));

			//반복문을 통해 JButton 학식메뉴를 주문화면에 뿌려준다.
			for (int i = 0; i < menu.length; i++) {
				if(i<3) {
					bt_menu[i].setBounds(75 + i * 220, 40, 150, 105);
				}
				else if(i<6) {
					bt_menu[i].setBounds(75 + (i-3) * 220, 260, 150, 105);
				}
				else {
					bt_menu[i].setBounds(90 + (i - 6) * 119, 480, 100, 100);
				}
				
				name[i] = new Label(menu_txt[i]);
				name[i].setFont(font1);
				name[i].setBounds(bt_menu[i].getX()-3, bt_menu[i].getY() - 20, 115, 20);
				
				num[i] = new TextField("0");
				num[i].setBackground(Color.white);
				num[i].setEditable(false);
				num[i].setBounds(bt_menu[i].getX() + 30, bt_menu[i].getY() + 130, 40, 20);
				
				minus[i] = new Button("-");
				minus[i].setBounds(bt_menu[i].getX(), num[i].getY(), 20, 20);
				minus[i].setEnabled(true);
				
				plus[i] = new Button("+");
				plus[i].setBounds(bt_menu[i].getX() + (100 - 20), num[i].getY(), 20, 20);
				plus[i].setEnabled(true);
				
				won[i] = new Label(price[i] + "원");
				won[i].setBounds(bt_menu[i].getX() + 30, num[i].getY() - 25, 100, 20);
				
				ok[i] = new JButton("확인");
				ok[i].setBounds(bt_menu[i].getX(), num[i].getY() + 30, 100, 20);
				ok[i].setEnabled(false);
				
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

			//주문확인 메뉴바 관련
			Panel SouthPanel = new Panel();
			TextArea txt = new TextArea("");
			String[] [] data = new String[0][0];
			String[] title = {"상품명","단가","수량","합계", "총 금액"};
			DefaultTableModel model = new DefaultTableModel(data, title);
			JTable table = new JTable(model);
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane = new JScrollPane(table);
			scrollPane.setPreferredSize(new Dimension(420,730)); //주문확인 메뉴바 관련사이즈
			SouthPanel.add(scrollPane);

      		//메뉴선택 버튼3가지: 종료, 리셋, 주문하기		
			Panel SelectPanel = new Panel();
			SelectPanel.setLayout(new GridLayout(3,1,50,0));
			JButton order[] = new JButton[3];
			
			order[0] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\close.png"));  //close이미지변경
			order[1] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\reset.png"));  //reset이미지변경
			order[2] = new JButton(new ImageIcon("School Cafeteria Project\\img\\icon\\order.png"));  //order이미지변경
			order[0].setBackground(Color.WHITE);
			order[1].setBackground(Color.WHITE);
			order[2].setBackground(Color.WHITE);
			SelectPanel.add(order[0]);
			SelectPanel.add(order[1]);
			SelectPanel.add(order[2]);

				    // 닫기 버튼
					order[0].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					
					// 초기화 버튼
					order[1].addActionListener(new ActionListener() {
			 
						@Override
						public void actionPerformed(ActionEvent e) {
							model.setNumRows(0); //주문 내역 초기화
							txt.setText("");
							total=0;
						}
					});
			 
					// 주문버튼
					order[2].addActionListener(new ActionListener() {
			 
						@Override
						public void actionPerformed(ActionEvent e) {
							
							int answer = JOptionPane.showConfirmDialog(null, "주문하시겠습니까?", "Order",JOptionPane.YES_NO_OPTION);
							if(answer == JOptionPane.YES_OPTION)
							{
								if(total==0)
								{
									JOptionPane.showMessageDialog(null, "선택 항목이 존재하지 않습니다.");
								}
			
								else {
									for(int i=0; i<table.getRowCount(); i++) {
										txt.append(table.getValueAt(i, 0)+" "+table.getValueAt(i, 1)+" X "+table.getValueAt(i, 2)+"개\n");
									}
									
									contents = txt.toString();
									if(!contents.contains("main:") && contents.contains("side:"))
									{
										JOptionPane.showMessageDialog(null, "메인메뉴가 선택되지 않았습니다.\n사이드메뉴는 메인메뉴 선택 시 추가할 수 있습니다.\n음료수는 메인메뉴와 상관없이 주문가능합니다.");
										txt.setText("");
									}
									else {
										JOptionPane.showMessageDialog(null, txt.getText()+"총 금액 : "+total+"원\n"+"주문되었습니다. \n이용해주셔서 감사합니다.");
										total=0; txt.setText(""); model.setNumRows(0);
									}
								}
							}
							else
								JOptionPane.showMessageDialog(null, "메뉴 선택 단계로 돌아갑니다.\n");
			
							for (int i = 0; i < menu.length; i++) {
								num[i].setText("0");
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







		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(NorthPanel, BorderLayout.NORTH);
        frame.add(CenterPanel, BorderLayout.CENTER);
        frame.add(SouthPanel, BorderLayout.EAST);
		SouthPanel.setBackground(Color.white);
		// SouthPanel.setPreferredSize(new Dimension(500,500));
        frame.add(SelectPanel, BorderLayout.WEST);
		frame.setSize(1250, 850); //학식주문 전체창 사이즈
		frame.setVisible(true);
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
