/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.List;
import java.util.Set;
import java.util.Stack;

//import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;


public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	try {
    		for(int i = 0;i <4;i++) {
    			turtle.forward(sideLength);
    			turtle.turn(90);
    		}
    		} catch(Exception e) {
    			throw new RuntimeException("implement me!");
    		}	  
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	try {
    		double angle = (double) 180*(sides-2)/sides;
    		return angle;
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}  
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
        try {
        	int sides = (int)Math.ceil(360.0/(180.0-angle));
        	return sides;
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    	
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
        try {
        	
        	turtle.turn(270);
        	double angle = (double) 180 - calculateRegularPolygonAngle(sides);
        	for(int i = 0;i <sides;i++) {
        		turtle.turn(angle);
        		turtle.forward(sideLength);
        	}
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }	
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	try {
    		int x = targetX-currentX;
    		int y = targetY-currentY; //�õ��������γɵ������ĺ�������
    		double angle = 0; //��ʾ������y������˳ʱ�뷽��ļн�
    		if(x ==  0 ) { //�������ĺ�����
    			if(y < 0)
                angle = 180; //������y������н�Ϊ180��
    			if(y > 0)
    				angle = 0;//������y������н�Ϊ0��
    			if(y == 0)
    				angle = currentBearing; //�����غϣ������������ڴ�Ĭ����ԭ����һ��
    		}
    		else {
    			double angle1 = (Math.atan((double)y/x)) * 180 / Math.PI;//���÷����к������������x��ļн�
        	    if(x >0 ) {        //�����޼���������y������ļн�
        	    	angle = 90 - angle1;
        	    }
        	    else {
        	    	angle = 270-angle1;
        	    }
    		}
    	    if(currentBearing<=angle){ //������Ҫ��ת�ĽǶȲ�����
                return angle - currentBearing;
            }else{
                return 360 - currentBearing + angle;
            }
    	}catch(Exception e) {
    		throw new RuntimeException("implement me!");
    	}  
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
        try {
        	double currentBearing = 0; //��ǰ����Ϊ��y�����򣬼��Ƕ�Ϊ0
        	List<Double> angle = new ArrayList<>(); //���ÿ���������Ҫ��ת�ĽǶ�
        	for(int i = 0;i <xCoords.size()-1;i++) {
        		int x0,x,y0,y;
        		x0 = xCoords.get(i);
        		x = xCoords.get(i+1);
        		y0 =yCoords.get(i);
        		y = yCoords.get(i+1);
        		double angle1 = calculateBearingToPoint(currentBearing,x0,y0,x,y); //�õ���ǰ�㵽��һ������Ҫ��ת�ĽǶ�
        		angle.add(i,angle1);
        		currentBearing = (currentBearing +angle1)%360; //���㵽��(x,y)��ʱ����ķ��򣨷���Ϊ��y������ļнǣ���Χ��0~360������Ҫȡ�����㣩
        	}
        	return angle;
        }catch(Exception e) {
        	throw new RuntimeException("implement me!");
        }
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	try {
    		List<Point> arraylist = new ArrayList<>(points); //��setת����list�����ڱ���
    		Set<Point> result = new HashSet<>();//�����洢��󹹳�͹������С����
    		int n = arraylist.size();
    		if(n == 0 || n == 1 || n == 2) {//��������û�е��ֻ��һ�����ֻ��2���㣬ֱ�ӷ��ظõ㼯
    			for(int i = 0;i< n;i++) {
    				result.add(arraylist.get(i));
    			}
    	        return result;
    		}	
    		Point min = arraylist.get(0);
    		for(int  i = 1;i < n;i++) { //�ҵ�yֵ��С�ĵ�p0����yֵ��ͬ������xֵ��С�ĵ�
    			boolean a = arraylist.get(i).y() < min.y() ;
    			boolean b = arraylist.get(i).y() == min.y() && arraylist.get(i).x() < min.x();
    			if(a || b) {
    				min = arraylist.get(i);
    			}
    		}
    		double[] angle = new double[n]; //����ÿ�����p0��x������ļнǴ��������
    		double angleX; 
    		//�������湹��ļ�����ת�Ƕȵĺ���������ÿ�����p0���ɵ��߶���x��н�
            //���ڲ��Եĵ������Ϊdouble�ͣ�������calculateBearingToPoint���β���int�ͣ����Խ�double�͵ĵ�ת��int��
            //��Ϊtest�ļ��и����Ĳ������Ǹ����͵����������Բ�Ӱ��ʵ����������������дһ�����㷽����calculateBearingToPoint��ֻͬ���β����͸�Ϊ���������岻��
    		for(int i = 0;i < n;i++) { 
    			angleX = calculateBearingToPoint(270.0,(int)min.x(),(int)min.y(),(int)arraylist.get(i).x(),(int)arraylist.get(i).y());
    			if(angleX == 0) {
    				angle[i] = 0;
    			}
    			else
    			angle[i] = 180 - angleX;
    		}
    		int k;
    		double temp1;
    		Point temp2;
    		for(int i = 0;i < n-1;i++) { //���㰴�нǴ�С���������������򰴾���Զ�����򣬾����������ǰ��
    			k = i;
    			for(int j = i+1;j< n;j++) {
    				if(angle[j] <= angle[k]) {
    					k = j;
    				}
    			}
    			if(k != i) {
    				if(angle[k] < angle[i]) {
    					temp1 = angle[k];angle[k] = angle[i];angle[i] = temp1;
    					temp2 = arraylist.get(k);arraylist.set(k, arraylist.get(i));arraylist.set(i,temp2);
    				}
    				else if(angle[k] == angle[i]) {
    					double distancek = Math.pow(arraylist.get(k).x() - min.x(),2)+Math.pow(arraylist.get(k).y()-min.y(), 2);
        				double distancei = Math.pow(arraylist.get(i).x() - min.x(),2)+Math.pow(arraylist.get(i).y()-min.y(), 2);
        				if(distancek < distancei) {
        					temp1 = angle[k];angle[k] = angle[i];angle[i] = temp1;
        					temp2 = arraylist.get(k);arraylist.set(k, arraylist.get(i));arraylist.set(i,temp2);
        				}
    				}
    			}		
    		}
    		//���Ƚ��ź����ǰ������p0��p1��ջ��Ȼ���ж�����p0p1��ת��p1p2�������Ƿ�Ϊ��ʱ�뷽����ν��ʱ�뷽��Ϊ����p0p1��ʱ����תС��180���ĳ���ǶȺ���p1p2������ͬ��
    		//������ʱ�뷽����p2��ջ������ջ��Ԫ�س�ջ�������жϵ�ǰ�������򣨵�ǰջ��ǰ��ĵ���ջ�������ķ�����ջ��Ԫ�غ���һ��������������Ƿ�Ϊ˳ʱ�룬ѭ��ֱ���ж������
    		//һ���㣬Ȼ���ʱջ�еĵ㹹����Ҫ���͹������С�㼯
    		Stack<Point> stack = new Stack<>(); 
    		stack.push(arraylist.get(0));
    		stack.push(arraylist.get(1)); //��ǰ��������ջ
    		arraylist.get(0).angle = 0;
    		double currentAngle; //��ǰ�����ķ���
    		if(angle[1] < 90) {
    		   currentAngle = 90 -angle[1]; //��һ������p0p1��y�������˳ʱ�뷽��н�
    		}
    		else {
    		   currentAngle = 450-angle[1];
    		}
    		arraylist.get(1).angle = currentAngle;	//��¼��ǰջ��Ԫ����ջ���·���һ���㹹�ɵ������ķ�����y������нǣ�
    		double tempAngle; //�����ж���������仯�Ƿ�������ʱ�뷽��
    		int x0,y0,x1,y1;
    		for(int i = 2; i< arraylist.size();i++) { 
    			x0 = (int)stack.peek().x();//ÿ��ȡջ��Ԫ��������ĵ㼯����һ��δ�жϵĵ��ж���������仯�Ƿ�������ʱ�뷽��
        		y0 = (int)stack.peek().y();
        		x1 = (int)arraylist.get(i).x();
        		y1 = (int)arraylist.get(i).y();
        		tempAngle = calculateBearingToPoint(currentAngle,x0,y0,x1,y1);
    			if( tempAngle> 180) {//������ʱ�뷽�򣬸õ��ջ
    				stack.push(arraylist.get(i));
    				currentAngle = (currentAngle + tempAngle)%360;
    				arraylist.get(i).angle = currentAngle;
    			}
    			else {
    				stack.pop();//ջ����ջ�������ж��µ�ջ���뵱ǰ���Ƿ�������ʱ��Ҫ��
    				i--;
    				currentAngle = stack.peek().angle;
    			}	
    		}
    		int size = stack.size();
    		Point[] output = new Point[size];
    		for(int i = 0;i<size;i++) {
    			output[i] = stack.pop();
    		}
    		Set<Point> set = new HashSet<>(Arrays.asList(output)); //��ջ�����еĵ㱣�浽set�в�����
    		return set;
    	}catch(Exception e) {
    		 throw new RuntimeException("implement me!");
    	}
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	for (int i = 0 ; i < 1000; i++) {
            turtle.forward(i/2);
            switch (i % 8) {
                case 0:turtle.color(PenColor.ORANGE);break;
                case 1:turtle.color(PenColor.PINK);break;
                case 2:turtle.color(PenColor.RED);break;
                case 3:turtle.color(PenColor.BLACK);break;
                case 4:turtle.color(PenColor.GREEN);break;
                case 5:turtle.color(PenColor.YELLOW);break;
                case 6:turtle.color(PenColor.GRAY);break;
                case 7:turtle.color(PenColor.BLUE);break;
            }
            if(i%2 == 0)
            turtle.turn(91);
            else
            	turtle.turn(100);
    	}
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
        drawSquare(turtle, 40);
        // draw the window
        turtle.draw();
       
    }

}
