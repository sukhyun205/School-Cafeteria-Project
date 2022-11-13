import java.awt.*;
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

//융프2 프로젝트
//AWT, Swing패키지를 활용한 학식주문 키오스크 프로그램

public class SchoolCafeteria extends JFrame {
    Font font1 = new Font(Font.MONOSPACED, Font.BOLD, 13);
	
    int count; int total=0; int col=0; int row=0; String contents = "";
	
	public SchoolCafeteria(){
		JFrame frame = new JFrame("학식주문 키오스크");
		
        Panel NorthPanel = new Panel();
        NorthPanel.setBackground(Color.orange);
        NorthPanel.setLayout(new FlowLayout());
        
        Label highbar = new Label();
        highbar.setText("<학식주문 프로그램>");
		NorthPanel.add(highbar);
		
		Panel CenterPanel = new Panel();
		CenterPanel.setLayout(null);
		CenterPanel.setBackground(Color.LIGHT_GRAY);
		String menu[] = {" "};
		
		String menu_txt[] = {"", "", "", "", 
				"","", " 사이드메뉴 "," "," ", " ",
				"","","","+ "," ", " "," ",
				" ", " "," ", ""};
		
		int price[] = {4000, 4500, 4500, 4700, 4700,5700, 500, 2500, 
				2500, 2500, 2700,3300,3500,700,2300, 2300, 2500,
				3500,3500,3500,300};
		JButton bt_menu[] = new JButton[menu.length];
        TextField num[] = new TextField[menu.length];
        Button minus[] = new Button[menu.length];
        Button plus[] = new Button[menu.length];
        JButton ok[] = new JButton[menu.length];
        Label won[] = new Label[menu.length];
        Label name[] = new Label[menu.length];
        Color[] color = {new Color(190,170,50), new Color(150, 100, 0), new Color(255,255,0), new Color(173, 52, 125)};
        
        bt_menu[0] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[1] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[2] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[3] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[4] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[5] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[6] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[7] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[8] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[9] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[10] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[11] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[12] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[13] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[14] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[15] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[16] = new JButton(new ImageIcon("./img/.png"));
        bt_menu[17] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[18] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[19] = new JButton(new ImageIcon("./img/.jpg"));
        bt_menu[20] = new JButton(new ImageIcon("./img/.jpg"));
        
        for (int i = 0; i < menu.length; i++) {
        	if(i<7) {
        		bt_menu[i].setBounds(25 + i * 150, 40, 100, 100);
        	}
        	else if(i<14) {
        		bt_menu[i].setBounds(25 + (i - 7) * 150, 250, 100, 100);
        		
        	}
        	else {
        		bt_menu[i].setBounds(25 + (i - 14) * 150, 470, 100, 100);
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
        }
		
        Panel SouthPanel = new Panel();
        TextArea txt = new TextArea("");
        
	    Panel SelectPanel = new Panel();
	    SelectPanel.setLayout(new GridLayout(3,1,50,0));
	    JButton order[] = new JButton[3];

		
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(NorthPanel, BorderLayout.NORTH);
        frame.add(CenterPanel, BorderLayout.CENTER);
        frame.add(SouthPanel, BorderLayout.SOUTH);
        frame.add(SelectPanel, BorderLayout.EAST);
		frame.setSize(1170, 958);
		frame.setVisible(true);
	}
	
	

	public static void main(String[] args) {
		new SchoolCafeteria();

	}

}
