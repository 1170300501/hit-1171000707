package P3;

import java.util.ArrayList;

public class Person {

    private String name ; //����
    private int isvisit = 0; //����ʱ�Ƿ���ʸ���
    private int distance = 0; //���˾����һ���˵���̾���
    ArrayList<String> friend = new ArrayList<>(); //���˵���������
    static ArrayList<String> person = new ArrayList<>();
    private int alone = 1; //�����Ƿ��ǹ�����
    public Person(String name){ 
        this.name = name;
        if(person.contains(name)) {
        	System.out.print("��������������!");
        	System.exit(0);
        }
        else {
        	person.add(name);
        }
    }
    public String getName() {
    	return name; //��������
    }
    public int isVisit() {
    	return isvisit; //����ʱ���ظõ��Ƿ񱻷��ʹ�
    }
    public void Visit() {
    	this.isvisit = 1; //��Ǹõ㱻���ʹ�
    }
    public void unVisit() {
    	this.isvisit = 0; //��Ǹõ�û�����ʹ�
    }
    public int getDistance() {
    	return distance; //���ؾ���
    }
    public void setDistance() {
    	this.distance = 0;//��Ǿ���Ϊ0
    }
    public void CalculateDistance() {
    	this.distance++; //��������1
    }
    public void Setdistance(int dis) {
    	this.distance = dis+1; //�����õ���õ��ǰһ������õ���븳ֵ
    }
    public int isAlone() {
    	return alone;//�����Ƿ�Ϊ������
    }
    public void Alone() {
    	this.alone = 0;//��Ǹõ㲻�ǹ�����
    }
}
