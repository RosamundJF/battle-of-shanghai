package people;

import java.awt.*;
/*
 * ��Ϸ����ʵ������ĸ���
 */
public class BodyObject {
	
	//����Ա���ɹ��������
	 protected Image img;
	 public int x;
	 public int y;
	 protected int speed;
	 protected int width,height;
	
	 //���Լ���������̳�
	 public void draw_ObjectSelf(Graphics g)
	 {
		 g.drawImage(img,x,y, null);
	 }

	//�޲ι�����
	
	public BodyObject() {}
	
	//�����������ڵľ��Σ�������ײ���
	
	public Rectangle getRect() {return new Rectangle(x, y, width, height);}


}
