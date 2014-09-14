/**
 * 
 */
package com.xory.syntax;

import android.util.Log;



/**
 * @author xory
 * Grammar: 语法,包括词法,句法,语义等
 * Syntax: 句法
 * 基本类型不可以作为泛型参数<boolean byte char short  int  long float double >
 * java 不能重载操作符
 * 泛型定义解决方案: http://www.cnblogs.com/xltcjylove/p/3671943.html
 * 1. 主要用接口,泛型类型从接口继承
 * 2. 用 Class,比如 class BaseClass,那么 Class kind = BaseClass.class;
 *          然后可以用 kind.newInstance()来构造 BaseClass实例
 */

public class GenericProgramming {
	private final static String TAG = "GenericProgramming";
	
	//泛型接口解决方案一:
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
		     public  <T extends Performs> void perform(T performer) {  //泛型方法声明
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
		 
		 //泛型接口解决方案二:

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
