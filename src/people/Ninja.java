package people;

import java.awt.Graphics;


import javax.swing.ImageIcon;
import shootballoon.Constant;
public class Ninja extends BodyObject{
	
	public boolean get_shoot=false;				//�Ƿ񱻻���
	
	public boolean draw=false;						//�Ƿ��ѻ�
	
	//��ʼ��
	public Ninja()
	{
		img=(new ImageIcon(Constant.IMAGEPATH+"\\ninja.png")).getImage();	
		x=(int)(950*Math.random());
		y=130;
		this.speed=0;
		this.width=img.getWidth(null);
		this.height=img.getHeight(null);
	}
	
	//���Լ�
	public void draw_ObjectSelf(Graphics g) 
	{
		if(!get_shoot )
		{
		g.drawImage(img, x, y, null);
		}
	}
}
