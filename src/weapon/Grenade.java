package weapon;
import shootballoon.Constant;
import shootballoon.Explode;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import people.BodyObject;

public class Grenade extends BodyObject{
		
		public static boolean throwed=false;							//�Ƿ��ӳ�
		
		public static int x_grenade;									//���׺�����
		
		//��ʼ����
		public Grenade() 
		{
			img=(new ImageIcon(Constant.IMAGEPATH+"grenade.png")).getImage();	
			y=640;
			width=img.getWidth(null);
			height=img.getHeight(null);
			speed=40;
		}
		
		//���Լ�
		public void draw_ObjectSelf(Graphics g) 
		{	
			
			if(y>160)
			{
			y-= speed;	
			g.drawImage(img, x_grenade, y, width, height,null);
			}
			else Explode.bomb=true;
		}
		
		//�ж��ӳ�����
		public void throwed(KeyEvent e)
		{
			if(e.getKeyCode()==KeyEvent.VK_A) 
			{
				x_grenade=(Gun.x_gun+25);
				throwed=true;
			}
		}

}
