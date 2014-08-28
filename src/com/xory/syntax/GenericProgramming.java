/**
 * 
 */
package com.xory.syntax;

import android.util.Log;



/**
 * @author xory
 * Grammar: �﷨,�����ʷ�,�䷨,�����
 * Syntax: �䷨
 * �������Ͳ�������Ϊ���Ͳ���<boolean byte char short  int  long float double >
 * java �������ز�����
 * ���Ͷ���������: http://www.cnblogs.com/xltcjylove/p/3671943.html
 * 1. ��Ҫ�ýӿ�,�������ʹӽӿڼ̳�
 * 2. �� Class,���� class BaseClass,��ô Class kind = BaseClass.class;
 *          Ȼ������� kind.newInstance()������ BaseClassʵ��
 */

public class GenericProgramming {
	private final static String TAG = "GenericProgramming";
	
	//���ͽӿڽ������һ:
	 public interface Performs { 
		     void speak(); 
		     void sit(); 
		 } 
	 class Dog{
		 
	 }
		 class PerformingDog extends Dog implements Performs { 
		     public void speak() { Log.i( TAG, "Woof!"); } 
		     public void sit() {  Log.i( TAG, "Sitting"); } 
		     public void reproduce() {} 
		 } 
		 class Robot implements Performs { 
		     public void speak() {  Log.i( TAG, "Click!"); } 
		     public void sit() {  Log.i( TAG, "Clank!"); } 
		     public void oilChange() {} 
		 } 
		 class Communicate { 
		     public  <T extends Performs> void perform(T performer) { 
		         performer.speak(); 
		         performer.sit(); 
		     } 
		 } 
		 public class DogsAndRobots { 
			 
			 public DogsAndRobots(){
		         PerformingDog d = new PerformingDog(); 
		         Robot r = new Robot(); 
		         Communicate cnn = new Communicate();
		         cnn.perform( d ); 
		         cnn.perform( r ); 
			 }
		 }
		 
		 //���ͽӿڽ��������:

		 class Building {} 
		 class House extends Building {} 
		 public class ClassTypeCapture<T> { 
		     Class<T> kind; 
		     T t;
		     
		     public ClassTypeCapture(Class<T> kind) { 
		         this.kind = kind; 
		     } 
		     public boolean f(Object arg) { 
		         return kind.isInstance(arg); 
		     }
		     public void newT(){
		         try {
					t = kind.newInstance();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
		     }
	   
		     public  void doSomething(String[] args) { 
		         ClassTypeCapture<Building> ctt1 = 
		         new ClassTypeCapture<Building>(Building.class); 
		         System.out.println(ctt1.f(new Building())); 
		         System.out.println(ctt1.f(new House())); 
		         ClassTypeCapture<House> ctt2 = 
		         new ClassTypeCapture<House>(House.class); 
		         System.out.println(ctt2.f(new Building())); 
		         System.out.println(ctt2.f(new House())); 
		     } 
		 }
}
