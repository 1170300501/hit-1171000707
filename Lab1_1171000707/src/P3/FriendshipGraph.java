package P3;
import java.util.*;
public class FriendshipGraph {
	public ArrayList<Person> fgraph = new ArrayList<>();//�洢��ϵͼ
	public void addVertex(Person name) {  //��ӵ�
		if(fgraph.contains(name)) {
			System.out.println("�����ѳ����ڹ�ϵ���У�");
			System.exit(0);
		}
		 fgraph.add(name);	 
	}
    public void addEdge(Person name1,Person name2) {  //��ӱ�
    	
    	if(name1.friend.contains(name2.getName())) {
    		System.out.println("��ϵ��������֮���ѽ��������ߣ�");
    		System.exit(0);
    	}
    	if(name1.equals(name2)) {
    		System.out.println("������ͬһ���ˣ��������");
    		System.exit(0);
    	}		
    	name1.friend.add(name2.getName());
    	name2.Alone(); //��ʾ�ڶ�������ͼ�в��ǹ�����
    }
    public int getDistance(Person name1,Person name2) { //�������˾���
    	for(int i =0 ;i <fgraph.size();i++) {
    		fgraph.get(i).unVisit();
    		fgraph.get(i).setDistance();
    	}
    	Queue<Person> queue = new LinkedList<>();
    	//System.out.println("����"+name1.name+name2.name);
    	if(name2.isAlone() == 1) { //name2�ǹ����㣬û������
    		return -1;
    	}
    	else if(name1.friend.contains(name2.getName())) { //name1��name2��ֱ������
    		return 1; 
    	}
    	else if(name1.getName().equals(name2.getName()) == true) {  //name1��name2��ͬһ����
    		return 0;
    	}
    	else { //�����������ù���������ߵ���̾���
    		name1.Visit();
    		for(int i =0;i<fgraph.size();i++) {
    			if(name1.friend.contains(fgraph.get(i).getName())) { //���˲���ֱ�����ѣ�name1���������
    				queue.add(fgraph.get(i));
    				fgraph.get(i).Visit();
    				fgraph.get(i).CalculateDistance(); //name1��name2�ľ�������1
    			}
    		}
    		while(queue.size() > 0 ) {
    			Person head = queue.poll(); //���׳���
    			if(head.friend.contains(name2.getName())) { //��ԭ���׵����Ѱ���name2����name1��name2�ľ�����ԭ�������ϼ�һ�����ؽ��
					head.CalculateDistance();
    				return head.getDistance();
				}
    			else {
    				for(int i = 0;i<fgraph.size();i++) { //��ԭ���׵����Ѳ�����name2�������������δ��ӵ�����ӣ�name1��name2�ľ����1
        				if(head.friend.contains(fgraph.get(i).getName()) && fgraph.get(i).isVisit() == 0) {
        					queue.add(fgraph.get(i));
        					fgraph.get(i).Visit();
        					fgraph.get(i).Setdistance(head.getDistance());
        				}	
        			}
    			}
    		}	
    	}
		return -1;//��name2���ǹ����㣬����name1��name2û��·���������Ӷ���
    	
    }
	public static void main(String[] args) {

		FriendshipGraph graph = new FriendshipGraph();
	    Person rachel = new Person("Rachel"); 
	    Person ross = new Person("Rachel"); 
	    Person ben = new Person("Ben");
	    Person kramer = new Person("Kramer");
	    graph.addVertex(rachel);
	    graph.addVertex(ross);
	    graph.addVertex(ben);
	    graph.addVertex(kramer);
	    graph.addEdge(rachel, ross);
	    graph.addEdge(ross, rachel);
	    graph.addEdge(ross, ben);
	    graph.addEdge(ben, ross);
	    System.out.println(graph.getDistance(rachel, ross));
	    System.out.println(graph.getDistance(rachel, ben)); 
	    System.out.println(graph.getDistance(rachel, rachel));
	    System.out.println(graph.getDistance(rachel, kramer)); 
		
	   	}
}
