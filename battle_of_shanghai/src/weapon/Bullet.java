package weapon;
import javax.swing.*;
import shootballoon.*;
import people.BodyObject;

import java.awt.*;
import java.awt.event.*;
public class Bullet extends BodyObject{

	Image reloading =(new ImageIcon(Constant.IMAGEPATH+"bullet2.png")).getImage();

	public static int b=0;														//��¼���¿ո���Ĵ���(������ӵ�����)
	public static boolean fire=false;											//�Ƿ��Ѿ�����
	private int sec=0;													//����ӳٶ���
	//��ʼ����
	public Bullet() 
	{
		img=(new ImageIcon(Constant.IMAGEPATH+"bullet.png")).getImage();	
		y=640;
		width=img.getWidth(null);
		height=img.getHeight(null);
		speed=70;
	}

	// ����Paint�� ���ӵ�
	public void draw_ObjectSelf(Graphics g) 
	{	
		y-= speed;	
		g.drawImage(img, x, y, width, height,null);

		for(int i=0;i<MyGameFrame.Bullets.size();i++)					//�����ӵ���ɾ���Ѿ�Խ��ģ������Ի��е�
		{	
			Bullet b=MyGameFrame.Bullets.get(i);
			if(b.y<0)
			MyGameFrame.Bullets.remove(i);
		}
	}
	
	public void drawreloading(Graphics g)								//��ʣ�൯ҩ
	{											
		int x=820,y=730;
		for(int i=0;i<(Constant.BULLET_QUANTITY-Bullet.b);i++) 
		{
		g.drawImage(reloading, x, y, null);
		y-=6;
		}
}
	
	public void shoot(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_SPACE) 
		{
			if(MyGameFrame.ms>sec)    							//  ���Ʒ���Ƶ��    
			{
									//�����¶��󣬼ӵ�Bullets����
				MyGameFrame.Bullets.add(new Bullet());

				MyGameFrame.Bullets.get(MyGameFrame.Bullets.size()-1).x=(Gun.x_gun+25);					//��ʼ������
				fire=true;               						 //������ȷ��
				b++;
				sec=MyGameFrame.ms+300;
			}
		}


	}
}
