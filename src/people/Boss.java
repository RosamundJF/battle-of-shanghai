package people;
import shootballoon.Constant;
import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Boss extends BodyObject
{

	//�ƶ�����
	private int direction;

	//�Ƿ񱻻���
	public boolean get_shoot=false;

	//�Ƿ��ѻ�

	public boolean draw= false;

	//��ʼ��
	public Boss() 
	{

		if((int)(10*Math.random())<5) 
		{
			x=1;
			direction=1;
		}
		else
		{
			x=1099;
			direction=2;
		}

		y=50;

		speed=20;

		img=(new ImageIcon(Constant.IMAGEPATH+"boss.png")).getImage();	

		this.width=img.getWidth(null);

		this.height=img.getHeight(null);
	}

	//���Լ�
	public void draw_ObjectSelf(Graphics g) 

	{
		if(!get_shoot)
		{
			if(x> (-50) && x<1100)
			{
				if(direction==1) 	x+= speed;
				if(direction==2) 	x-= speed;
				g.drawImage(img, x, y, null);
			}
		}
	}
}

