package weapon;
import javax.swing.*;

import people.BodyObject;

import java.awt.*;
import java.awt.event.*;
import shootballoon.Constant;
import shootballoon.MyGameFrame;
/*
 * 
 * ��Gun�̳�BodyObject
 * 
 */
public class Gun extends BodyObject{
	
	//���췽������Ҫ���ܲ���
	public Gun()
	{
		img=(new ImageIcon(Constant.IMAGEPATH+"gun.png")).getImage();	
		x=495;
		y=620;
	}
	
	//ȫ�ֱ�������
	
	boolean left,right;

	
	static int x_gun;
	
	//���Լ����������paint����
	
	public void draw_ObjectSelf(Graphics g)
	{
		int speed=20 ;
		
		if(left&&x>0)
		{
			x-=speed;
		}								//�жϣ������ٶ�
		
		if(right&&x<950) 
		{
			x+=speed;
		}							//�жϣ������ٶ�
		
		x_gun=x;
		
		g.drawImage(img,x, y, null);				//��
	}

	//�����ƶ������жϣ��������ܵİ�����������
	public void gun_move(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
		case KeyEvent.VK_LEFT:
			left=true;break;
		case KeyEvent.VK_RIGHT:
			right=true;break;
		}
	}
	//����ֹͣ�����жϣ��������ܵİ�����������
	public void gun_stop(KeyEvent e)
	{
		switch (e.getKeyCode()) 
		{
		case KeyEvent.VK_LEFT:
			left=false;break;
		case KeyEvent.VK_RIGHT:
			right=false;break;
		}
	}
}
