package shootballoon;
import javax.swing.*;

import weapon.Grenade;

import java.awt.*;
public class Explode {
	private Image[] explodes =new Image[16];					//��ͨ���˱�ը

	private Image[] bombs =new Image[16];						//���񵯴�ը

	public static boolean bomb =false;							//�����Ƿ�ը

	protected static int number=0;								//��ըͼƬ�ֲ�����

	public Explode() 
	{
		for(int i=0;i<16;i++)							//��ͨС��ը
		{
			explodes[i] =(new ImageIcon(Constant.IMAGEPATH+"explode\\e"+i+".gif")).getImage();

		}
		for(int i=0;i<16;i++)							//���񵯴�ը
		{
			bombs[i] =(new ImageIcon(Constant.IMAGEPATH+"bomb\\e"+i+".gif")).getImage();

		}
	}


	public void drawexplode(Graphics g,int x,int y) 	//��С��ը
	{								

		for(int i=0;i <= 15;i++)
		{
			g.drawImage(explodes[i],  x,  y, null);
		}

	}

	public void drawbomb(Graphics g)					//����ը
	{
		if(number<16) 
		{
			g.drawImage(bombs[number],(Grenade.x_grenade-150) ,  10, null);
		}
		number++;
	}
	public Rectangle getRect()															//��ײ
	{
		return new Rectangle((Grenade.x_grenade-150),10, 360, 500);
	}
}




