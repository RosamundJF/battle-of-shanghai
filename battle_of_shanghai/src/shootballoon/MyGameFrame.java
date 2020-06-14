package shootballoon;

import java.awt.*;
import javax.swing.*;
import people.Boss;
import people.Ninja;
import people.Woman;
import weapon.*;
import java.awt.event.*;
import java.util.ArrayList;

/*
 * 
 * �������ڡ�
 * 
 */
public class MyGameFrame extends JFrame {

	// �زļ���
	Image gamescreen = (new ImageIcon(Constant.IMAGEPATH + "gamescreen.jpg")).getImage(); // ����
	Image gun = (new ImageIcon(Constant.IMAGEPATH + "gun.png")).getImage(); // ǹ
	Image icon = (new ImageIcon(Constant.IMAGEPATH + "icon.png")).getImage(); // ����ǿ
	Image gameover = (new ImageIcon(Constant.IMAGEPATH + "over.jpg")).getImage();

	JPanel launchpanel = new MyGamePanel(); // ʵ�������
	Gun gun_obj = new Gun(); // ʵ����ǹ
	Explode explode = new Explode(); // ʵ������ը����
	Grenade grenade = new Grenade(); // ʵ��������
	Bullet bullet = new Bullet(); // �����ӵ�����
	PaintThread paintthread = new PaintThread(); // �����̶߳���

	public static ArrayList<Bullet> Bullets = new ArrayList<Bullet>(); // �ӵ�����
	private ArrayList<Ninja> Ninjas = new ArrayList<Ninja>(); // ��������
	private ArrayList<Boss> Bosses = new ArrayList<Boss>(); // boss����
	private ArrayList<Woman> Women = new ArrayList<Woman>(); // Ů�ˣ����ʣ�����
	
	public static int ms = 0; // ����
	protected static int s; // ��
	private static int Score = 0; // �÷�
	public  static boolean run = true; // �ж�ִ���߳�
	public boolean pause =false;
	protected boolean over=false;
	
	/*
	 * 
	 * �����������ڳ�ʼ��
	 * 
	 */
	public MyGameFrame() {
		
		this.setTitle("Ѫս�Ϻ�̲"); // ����
		this.setIconImage(icon); // ͼ��
		this.setVisible(true); // ���ڿɼ�
		this.setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT); // ��С
		this.setLocation(200, 10); // ����
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �رմ���
		this.setLayout(null); // ȡ������
		this.setResizable(false);
		this.add(launchpanel);
		addKeyListener(new Key_monitor(this)); // ��Ӱ�������,(��������)

		for (int i = 0; i < 15; i++) // ��ʼ��ʮ������
		{
			Ninja ninja = new Ninja();
			Ninjas.add(ninja);
		}
		for (int i = 0; i < 5; i++) // ��ʼ�����boss
		{
			Boss boss = new Boss();
			Bosses.add(boss);
		}
		for (int i = 0; i < 5; i++) // ��ʼ�����Ů�ˣ����ʣ�
		{
			Woman woman = new Woman();
			Women.add(woman);
		}
	}

	/*
	 * 
	 * Start ��������
	 * 
	 */
	//	public void Start()
	//	{
	//		new MyGameFrame();     										//������ܶ���
	//																
	//	}

	/*
	 * 
	 * ���ڲ��ࡿ�Զ������
	 * 
	 */
	public class MyGamePanel extends JPanel {
		// ����
		public MyGamePanel() {
			this.setBounds(0, 0, 1020, 768);
			this.setVisible(true);
			this.setLayout(null);

			new PaintThread().start(); // ��������������ͼ�߳�

		}

		/*
		 * 
		 * �� �� ͼ ( �� paint �� �� )
		 *
		 *
		 */
		public void paint(Graphics g) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Dialog", 1, 20));

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawImage(gamescreen, 0, 0, 1020, 768, null); // ��ʼ����ʼ����

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawString("ʣ���ӵ�", 900, 700); // ʣ���ӵ���ʾ

			if (Bullet.b <= Constant.BULLET_QUANTITY && (s <= Constant.GAME_TIME))
				g.drawString("" + (Constant.BULLET_QUANTITY - Bullet.b), 930, 740); // �ӵ�����ʾ
			else
				g.drawString("0", 930, 740);
			// �ӵ�����ʾ

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawString("ʱ��:", 25, 730); // time��ʱ

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawString("" + s, 110, 730);

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawString("����", 20, 30); // �÷�

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				g.drawString("" + Score, 30, 50);

			
				grenade.draw_ObjectSelf(g); // ��������

			// ��ը�����ͼ��
if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME)) // ʣ���ӵ�
				bullet.drawreloading(g);

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				gun_obj.draw_ObjectSelf(g); // ����Gun�Ļ��Լ�����,��ǹ

			if (Grenade.throwed)
			if (Explode.bomb && Explode.number < 16) {
				explode.drawbomb(g);

				// ȡ����������
				for (int j = 0; j < Ninjas.size(); j++) // ��������������Ѿ���ʼ������
				{
					Ninja ninja = Ninjas.get(j); // Ҳ����˵��Ȼû�����������������Ѿ����ڱ�������
					// ��ײ���
					if (ninja.draw && !ninja.get_shoot) // ����Ҫ�ж��Ƿ񻭳���������ײ���
					{
						if (explode.getRect().intersects(ninja.getRect())) // ���
						{
							explode.drawexplode(g, ninja.x, ninja.y); // ��ը����
							Score = Score + 10; // ������10
							ninja.get_shoot = true; // �����У����ٻ����
						}
					}
				}

				// ȡ������boss
				for (int k = 0; k < Bosses.size(); k++) {
					Boss boss = Bosses.get(k);

					// ��ײ���
					if (boss.draw && !boss.get_shoot) {
						if (explode.getRect().intersects(boss.getRect())) {
							explode.drawexplode(g, boss.x, boss.y);
							Score = Score + 50;
							boss.get_shoot = true;
						}
					}
				}

				// ȡ������Ů��
				for (int l = 0; l < Women.size(); l++) {
					Woman woman = Women.get(l);

					// ��ײ���
					if (woman.draw && !woman.get_shoot) {
						if (explode.getRect().intersects(woman.getRect())) {
							explode.drawexplode(g, woman.x, woman.y);
							if (Score >= 20)
								Score -= 30;
							if (Score < 20)
								Score = 0;
							woman.get_shoot = true;
						}
					}
				}

			}

			// �����ӵ���

			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME))
				if (Bullet.fire) // ����ȷ���Ƿ��ѿ���Ȼ���ٿ�ʼ���������ӵ�����������
				{
					for (int i = 0; i < Bullets.size(); i++) {
						Bullet b = Bullets.get(i);
						b.draw_ObjectSelf(g);

						// ȡ����������
						for (int j = 0; j < Ninjas.size(); j++) // ��������������Ѿ���ʼ������
						{
							Ninja ninja = Ninjas.get(j); // Ҳ����˵��Ȼû�����������������Ѿ����ڱ�������
							// ��ײ���
							if (ninja.draw && !ninja.get_shoot) // ����Ҫ�ж��Ƿ񻭳���������ײ���
							{
								if (b.getRect().intersects(ninja.getRect())) // ���
								{
									explode.drawexplode(g, ninja.x, ninja.y); // ��ը����
									Score = Score + 10; // ������10
									ninja.get_shoot = true; // �����У����ٻ����
								}
							}
						}

						// ȡ������boss
						for (int k = 0; k < Bosses.size(); k++) {
							Boss boss = Bosses.get(k);

							// ��ײ���
							if (boss.draw && !boss.get_shoot) {
								if (b.getRect().intersects(boss.getRect())) {
									explode.drawexplode(g, boss.x, boss.y);
									Score = Score + 50;
									boss.get_shoot = true;
								}
							}
						}

						// ȡ������Ů��
						for (int l = 0; l < Women.size(); l++) {
							Woman woman = Women.get(l);

							// ��ײ���
							if (woman.draw && !woman.get_shoot) {
								if (b.getRect().intersects(woman.getRect())) {
									explode.drawexplode(g, woman.x, woman.y);
									if (Score >= 20)
										Score -= 30;
									if (Score < 20)
										Score = 0;
									woman.get_shoot = true;
								}
							}
						}

					}
				}

			// ����ʱ�仭15�����ߣ����Ƴ�ʱ�䵽�����ߣ�ÿ�����˶��Ƕ����ģ����������и��Եĳ���ʱ��ʹ��ʱ��!!!
			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME)) {

				if (s >= 2 && s <= 4) {
					Ninjas.get(0).draw_ObjectSelf(g);
					Ninjas.get(0).draw = true;
				}
				if (s >= 4) {
					Ninjas.get(0).x = -300;
					Ninjas.get(0).y = -300;
				}
				if (s >= 5 && s <= 7) {
					Ninjas.get(1).draw_ObjectSelf(g);
					Ninjas.get(1).draw = true;
				}
				if (s >= 7) {
					Ninjas.get(1).x = -300;
					Ninjas.get(1).y = -300;
				}
				if (s >= 8 && s <= 10) {
					Ninjas.get(2).draw_ObjectSelf(g);
					Ninjas.get(2).draw = true;
				}
				if (s >= 10) {
					Ninjas.get(2).x = -300;
					Ninjas.get(2).y = -300;
				}
				if (s >= 11 && s <= 13) {
					Ninjas.get(3).draw_ObjectSelf(g);
					Ninjas.get(3).draw = true;
				}
				if (s >= 13) {
					Ninjas.get(3).x = -300;
					Ninjas.get(3).y = -300;
				}
				if (s >= 14 && s <= 16) {
					Ninjas.get(4).draw_ObjectSelf(g);
					Ninjas.get(4).draw = true;
				}
				if (s >= 16) {
					Ninjas.get(4).x = -300;
					Ninjas.get(4).y = -300;
				}

				if (s >= 17 && s <= 19) {
					Ninjas.get(5).draw_ObjectSelf(g);
					Ninjas.get(5).draw = true;
				}
				if (s >= 19) {
					Ninjas.get(5).x = -300;
					Ninjas.get(5).y = -300;
				}
				if (s >= 20 && s <= 22) {
					Ninjas.get(6).draw_ObjectSelf(g);
					Ninjas.get(6).draw = true;
				}
				if (s >= 22) {
					Ninjas.get(6).x = -300;
					Ninjas.get(6).y = -300;
				}

				if (s >= 21 && s <= 23) {
					Ninjas.get(7).draw_ObjectSelf(g);
					Ninjas.get(7).draw = true;
				}
				if (s >= 23) {
					Ninjas.get(7).x = -300;
					Ninjas.get(7).y = -300;
				}
				if (s >= 22 && s <= 24) {
					Ninjas.get(8).draw_ObjectSelf(g);
					Ninjas.get(8).draw = true;
				}
				if (s >= 24) {
					Ninjas.get(8).x = -300;
					Ninjas.get(8).y = -300;
				}
				if (s >= 22 && s <= 24) {
					Ninjas.get(9).draw_ObjectSelf(g);
					Ninjas.get(9).draw = true;
				}
				if (s >= 24) {
					Ninjas.get(9).x = -300;
					Ninjas.get(9).y = -300;
				}
				if (s >= 23 && s <= 25) {
					Ninjas.get(10).draw_ObjectSelf(g);
					Ninjas.get(10).draw = true;
				}
				if (s >= 25) {
					Ninjas.get(10).x = -300;
					Ninjas.get(10).y = -300;
				}
				if (s >= 25 && s <= 27) {
					Ninjas.get(11).draw_ObjectSelf(g);
					Ninjas.get(11).draw = true;
				}
				if (s >= 27) {
					Ninjas.get(11).x = -300;
					Ninjas.get(11).y = -300;
				}
				if (s >= 28 && s <= 30) {
					Ninjas.get(12).draw_ObjectSelf(g);
					Ninjas.get(12).draw = true;
				}
				if (s >= 30) {
					Ninjas.get(12).x = -300;
					Ninjas.get(12).y = -300;
				}
				if (s >= 29 && s <= 31) {
					Ninjas.get(13).draw_ObjectSelf(g);
					Ninjas.get(13).draw = true;
				}
				if (s >= 31) {
					Ninjas.get(13).x = -300;
					Ninjas.get(13).y = -300;
				}
				if (s >= 30 && s <= 32) {
					Ninjas.get(14).draw_ObjectSelf(g);
					Ninjas.get(14).draw = true;
				}
				if (s >= 32) {
					Ninjas.get(14).x = -300;
					Ninjas.get(14).y = -300;
				}

			}

			// ����ʱ�仭5��Boss
			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME)) {
				if (s >= 5 && s <= 8) {
					Bosses.get(0).draw_ObjectSelf(g);
					Bosses.get(0).draw = true;
				} else if (s >= 12 && s <= 15) {
					Bosses.get(1).draw_ObjectSelf(g);
					Bosses.get(1).draw = true;
				} else if (s >= 19 && s <= 22) {
					Bosses.get(2).draw_ObjectSelf(g);
					Bosses.get(2).draw = true;
				} else if (s >= 26 && s <= 29) {
					Bosses.get(3).draw_ObjectSelf(g);
					Bosses.get(3).draw = true;
				} else if (s >= 31 && s <= 34) {
					Bosses.get(4).draw_ObjectSelf(g);
					Bosses.get(4).draw = true;
				}
			}

			// ����ʱ�仭5������
			/**
			 * 
			 * 
			 * 
			 */
			if ((Bullet.b <= Constant.BULLET_QUANTITY) && (s <= Constant.GAME_TIME)) {
				if (s >= 6 && s <= 8) {
					Women.get(0).draw_ObjectSelf(g);
					Women.get(0).draw = true;
				}
				if (s >= 8) {
					Women.get(0).x = -500;
					Women.get(0).y = -500;

				}
				if (s >= 11 && s <= 13) {
					Women.get(1).draw_ObjectSelf(g);
					Women.get(1).draw = true;
				}
				if (s >= 13) {
					Women.get(1).x = -500;
					Women.get(1).y = -500;
				}
				if (s >= 17 && s <= 19) {
					Women.get(2).draw_ObjectSelf(g);
					Women.get(2).draw = true;
				}
				if (s >= 19) {
					Women.get(2).x = -500;
					Women.get(2).y = -500;

				}
				if (s >= 25 && s <= 27) {
					Women.get(3).draw_ObjectSelf(g);
					Women.get(3).draw = true;
				}
				if (s >= 27) {
					Women.get(3).x = -500;
					Women.get(3).y = -500;
				}
				if (s >= 30 && s <= 32) {
					Women.get(4).draw_ObjectSelf(g);
					Women.get(4).draw = true;
				}
				if (s >= 32) {
					Women.get(4).x = -500;
					Women.get(4).y = -500;
				}
				// ����ʱ���Ƴ�δ���е�����
			}


			g.setFont(new Font("Dialog", 1, 100)); // ����Ϸ����

			if(Bullet.b==20) {
				g.drawString("�ӵ��ѿ�" , 100, 400);
				g.drawString("�밴�ո������Ϸ" , 100, 500);
			}

			if (( Bullet.fire && (Bullet.b > Constant.BULLET_QUANTITY)) | s == Constant.GAME_TIME) // �ѿ���&&�Ѿ��ﵽ�ӵ���Ϊ0

			{
				g.drawImage(gameover, 0, 0, 1020, 768, null);
				g.drawString("" + Score, 100, 720);
				MyGameFrame.run = false; // �����߳�
				over=true;               //��Ϸ�ѽ���

			}

		}

	}

	/*
	 *
	 * ���ڲ��ࡿ��ͼ�̣߳��û��涯����
	 */
	public class PaintThread extends Thread {
		public void run() {
			while (run) {
				
				repaint(); // �ֶ��ػ�
				ms += 42;
				s = (int) (ms / 1000);
				System.out.println(ms+"---"+s);
				try {
					Thread.sleep(Constant.SCREEN_REFRESH_RATE); // �ӳ�
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * 
	 * ���ڲ��ࡿ������̼����� �ƶ�ǹ֧
	 * 
	 */
	
	public class Key_monitor extends KeyAdapter {
		
		JFrame jf;													
		public Key_monitor(JFrame jf) {													//�����
			this.jf=jf;
		}
		public void keyPressed(KeyEvent e) {
			if(!pause) {															//���û����ͣ
				gun_obj.gun_move(e);
				new Bullet().shoot(e);
				grenade.throwed(e); 
			}
			
			if( e.getKeyCode()==KeyEvent.VK_E && over) {    						 //�˳���Ϸ
				jf.dispose();
			}
			
			if(e.getKeyCode()==KeyEvent.VK_P  ){									//��ͣ��Ϸ
				if(pause==false) {
					pause=true;
				}
				else {
					pause=false;

				}
			}

			
			if(e.getKeyCode()==KeyEvent.VK_R) {										//���¿�ʼ��Ϸ
				if(over) {
					ms=0;
					s=0;
					Score=0;
					Bullet.b=0;
					Bullet.fire=false;
					Grenade.throwed=false;
					Explode.bomb=false;
					Explode.number=0;
					Bosses.removeAll(Bosses);
					Ninjas.removeAll(Ninjas);
					Women.removeAll(Women);
					Bullets.removeAll(Bullets);
					jf.dispose();
					MyGameFrame.run=true;                                          
					new MyGameFrame();
				}
			}
		}
		public void keyReleased(KeyEvent e) {
			gun_obj.gun_stop(e);

		} 

	}

}

