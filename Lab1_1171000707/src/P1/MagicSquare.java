package P1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class MagicSquare {
      public static boolean isNumber(String str) { //�ж�ÿ���ָ���ַ����Ƿ�Ϊ����
    	  Pattern pattern = Pattern.compile("[0-9]*");
    	  Matcher isnumber = pattern.matcher(str);
    	  if(!isnumber.matches()) {
    		  return false;
    	  }
    	  return true;
      }
	 static boolean isLegalMagicSquare(String fileName)  throws IOException {
		try {
			FileReader file = new FileReader(fileName);
		    BufferedReader buffer = new BufferedReader(file); //���ļ��ж�ȡ����
		    ArrayList<String> str = new ArrayList<String>(); //������̬�ַ�������洢��ȡ��ÿһ�е�����
		    String line = null;
		    while((line = buffer.readLine()) != null){
		    	str.add(new String(line)); //���ж�ȡ�������ַ�������
		    }
		    int n = str.size();	 //��þ��������
		    file.close();
		    buffer.close();	
		    String [][]da = new String[n][n];//��ŷָ����ÿһ���ַ���
			int [][]data = new int[n][n]; //���ַ���ת����int�ͺ�����ݴ����int������
			int count = 0;
			int row = n;
			int column = 0;
			while(count < row) {
			    String s = str.get(count);
				String[] sl =  s.split("\t"); //�ָ����ÿһ�е�����
				column = sl.length; //���������
				if(row != column) { //���в���Ȼ���ÿ������������\t�ָ�ģ�ֱ�ӷ���
					System.out.println("���Ǿ���");
					return false;
				}
				for(int i = 0;i < column;i++) {
				
					da[count][i] = sl[i];
				}
				count++;
			}
			for(int i = 0;i < row;i++) {
				for(int j = 0;j< column;j++) {
					if(!isNumber(da[i][j])) { //�жϸ��ַ�����ʾ���Ƿ�Ϊ�������������ǣ��򷵻�
						System.out.println("������������");
						return false;
					}	
					data[i][j] = Integer.valueOf(da[i][j]); //����ȡ������ת��Ϊint��
				}
			}
			int sum = 0;
			int judge = 0;
			for(count = 0 ;count < column ;count++) {
				sum += data[0][count] ; //��������һ�еĺ�
			}
			for(int i = 1;i<row;i++) {   //�жϾ��������еĺ��Ƿ����
				for(int j = 0;j < column;j++) {
					 judge += data[i][j];
				}
				if(sum != judge) {
					return false;
				}
				else {
					judge = 0;
				}
			}
			for(int j = 0;j<column;j++) {   //�жϾ���ÿһ�еĺ��Ƿ�͵�һ����ͬ
				for(int i = 0;i < row;i++) {
					 judge += data[i][j];
				}
				if(sum != judge) {
					return false;
				}
				else {
					judge = 0;
				}
			}
			int judge1 = 0;
			//System.out.println(judge);
			for(int i = 0;i<row;i++) {  //�ж������Խ��ߵĺ��Ƿ�͵�һ����ͬ
				for(int j = 0;j < column;j++) {
					if((i + j) ==( column-1)) {
						judge1 += data[i][j];
					}	
				}
				judge += data[i][i];
			}
			if(judge != sum || judge1 != sum) {
				return false;
			}
				
		    
	}catch (IOException e) {
		e.printStackTrace();
    }
		return true;	
	}
	 public static boolean generateMagicSquare(int n) throws IOException{
		if((n%2 == 0) || (n < 0)) { //���nΪż���������޷����ɾ��󣬻����Խ�����⣬ֱ�ӷ���
			System.out.println(false);
			return false;
		 }
		 int magic[][] = new int[n][n];
		 int row = 0,col = n / 2,i,j,square = n*n;
		 for(i = 1;i <= square;i++) {
			 magic[row][col] = i; //��1���ڵ�һ�����м��λ��
			 if(i%n == 0)//��Ҫ�ŵ�λ���Ѿ����������������һ�������ڻ÷������Ͻǣ���ǰҪ�ŵ�����Ӧ���ڸ�λ�õ����·�
				 row++;
			 else {
				 if(row == 0) //�������Ѿ���Ϊ0�������´�����п�ʼ
					 row = n-1;
				 else
					 row--;//�������������ϵķ���仯
				 if(col == (n-1)) //�������Ѿ����ӵ����һ�У���ӵ�һ�����¿�ʼ
					 col = 0;
				 else
				 col++;//�������������ҵķ���仯
			 }
		 }
		 File file = new File("src/P1/txt/6.txt");//�����ɵľ���д���ļ�
		 file.createNewFile();
		 FileWriter output = new FileWriter(file);
		 
		 for(i = 0 ;i< n;i++) {
			for(j = 0;j < n;j++) {
				System.out.print(magic[i][j] + "\t");
				output.write(magic[i][j]+"\t");
			}	
			System.out.println();
			output.write("\r\n");
		 }
		 output.close();
		 return true;
	 }
	public static void main(String[] args) throws IOException {
		
		System.out.println("��һ����");
		boolean a = isLegalMagicSquare("src/P1/txt/1.txt");
		System.out.println(a);
		System.out.println("�ڶ�����");
		boolean b = isLegalMagicSquare("src/P1/txt/2.txt");
		System.out.println(b);
		System.out.println("��������");
		boolean c = isLegalMagicSquare("src/P1/txt/3.txt");
		System.out.println(c);
		System.out.println("���ĸ���");
		boolean d = isLegalMagicSquare("src/P1/txt/4.txt");
		System.out.println(d);
		System.out.println("�������");
		boolean e = isLegalMagicSquare("src/P1/txt/5.txt");
		System.out.println(e);
		generateMagicSquare(7);
		boolean f = isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println("����ľ���"+f);
	}
}
