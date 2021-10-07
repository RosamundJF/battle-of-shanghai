package shootballoon;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class StartFrame extends JFrame{

	Image startscreen=(new ImageIcon(Constant.IMAGEPATH+"startscreen.jpg")).getImage();    //����
	Image icon=(new ImageIcon(Constant.IMAGEPATH+"icon.png")).getImage();					//����ǿ
	ImageIcon marchsoft=(new ImageIcon(Constant.IMAGEPATH+"marchsoft.png"));						//����

//	StartFrame startframe_obj;																//���崰�ڶ���
	JButton btn_start =new JButton("��ʼ��Ϸ");												//��ʼ��Ϸ  ��ť
	JButton btn_rule =new JButton("����");
	JButton btn_about =new JButton("����",marchsoft);										
	JButton btn_exit =new JButton ("�˳���Ϸ");
	
	JPanel start_panel_obj =new Start_panel(this);											//�½��������Խ�������࣬����Ϊ��ܶ���

	JTextArea about = new JTextArea("����-����-������",1,1);									//����
	JTextArea rules = new JTextArea("���Ҽ��ƶ����ո����ӵ���A��������"
			+"\n"+"�������߼�10�����������ձ����ټ�50�������������ʼ�30������"											//����˵��
			+"\n"	+ "ÿ��20���ӵ���1�����ף���35���ڻ�ȡ���������"+"\n"
			+"��û���ӵ��󰴿ո������Ϸ�����ͣ�",5,15);

	/*
	 * 
	 * �����췽�������ڳ�ʼ��
	 * 
	 */
	public	StartFrame() {                                                        		  //��ܳ�ʼ����

		this.setTitle("Ѫս�Ϻ�̲");															//����
		this.setIconImage(icon);															//ͼ��
		this.setVisible(true);																//���ڿɼ�
		this.setSize(Constant.SCREEN_WIDTH,Constant.SCREEN_HEIGHT);							//��С
		this.setLocation(200, 10);															//����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);								//�رմ���
		this.setLayout(null);																//ȡ������	
		this.setResizable(false);
		this.add(start_panel_obj);															//�������		


	}
	/*
	 * 
	 * ������
	 * 
	 */

	public static void main(String[] args)						
	{
		new StartFrame();     																//������ܶ���
	}



	/*
	 * ���ڲ��ࡿ�Զ����Լ��������
	 * 
	 */
	public class Start_panel extends JPanel implements ActionListener
	{
		JFrame jf;																			//Ϊ�˵õ���ǰ�Ŀ�ܶ���
		//����
		public Start_panel(JFrame jf)
		{

			this.jf=jf;																			//�������õ�ǰ���
			this.setBounds(0, 0, 1020, 768);
			this.setVisible(true);
			this.setLayout(null);

			btn_start.setBounds(620, 430, 150, 50);
			btn_rule.setBounds(620, 510, 150, 50);
			btn_about.setBounds(620, 590, 150, 50);
			btn_exit.setBounds(620, 680, 150, 50);

			this.add(btn_rule);
			this.add(btn_start); 
			this.add(btn_about);
			this.add(btn_exit);

			btn_start.addActionListener(this);
			btn_about.addActionListener(this);
			btn_rule.addActionListener(this);
			btn_exit.addActionListener(this);
		}
		/*
		 * 
		 * 		ʵ��ActionListener�ӿڵķ���
		 */
		public void actionPerformed(ActionEvent e)					
		{		
			if (e.getSource()==btn_start) 														//��������ʼ��Ϸ
			{			
				new MyGameFrame();														//ת����һ������
				jf.dispose();                                                   				//�رյ�ǰ����
			}
			if(e.getSource()==btn_rule) 

			{	
				JOptionPane.showConfirmDialog(jf, rules,"����",JOptionPane.DEFAULT_OPTION);    	//�Ի���
			}

			if	(e.getSource()==btn_about) 
			{
				JOptionPane.showConfirmDialog(jf, about,"����",JOptionPane.DEFAULT_OPTION);		//�Ի���
			}
			if	(e.getSource()==btn_exit) 
			{
				int n=JOptionPane.showConfirmDialog(jf,"��ȷ��Ҫ�˳���Ϸ��","�˳���Ϸ",JOptionPane.YES_NO_OPTION);	//�Ի���
				if(n==JOptionPane.YES_OPTION) jf.dispose();
				if(n==JOptionPane.NO_OPTION) JOptionPane.showConfirmDialog(jf, "�������^_^","��ϲ��ϲ",JOptionPane.DEFAULT_OPTION);	
			}
		}


		/*
		 * 
		 * Ĭ��paint����
		 */	
		public void paintComponent(Graphics g)
		{
			g.drawImage(startscreen,0,0, 1050, 768,null);

		}
	}
}



