/**
 * 
 */
package com.xory.syntax;

/**
 * @author xory
 * @author 访问控制修饰
 * public: 所有都能访问, 能修饰类,方法,变量,接口
 * protected: 子类,当前包能访问,只能修饰方法,变量
 * default: 等于friendly, 即默认值, 当前包能访问, 能修饰类,变量,方法
 * private: 当前类能访问, 修饰方法,变量
 * abstract: 可以修饰类,方法, 如果有abstract方法,则类必须使用abstract
 * interface: 必须声明成 public static final, 可以不写, 则编译器会默认
 *                接口的方法必须 public 和 abstract,也可以不写
 * 详细描述见: http://www.w3cschool.cc/java/java-modifier-types.html               
 */
public class AccessCtrl {
	
	private int mA;
	static {
		int mB;
		int mC;
	};
	
	//接口默认(且必须)为public static final,可不写; 而接口的方法默认(且必须)public  abstract,可以不写
	//接口的变量默认(且必须) public abstract final, 可以不写
	interface IBaseCalculator{
		Object add( Object obj );
		public  Object sub( Object obj );
		abstract  Object mul( Object obj );
		public abstract  Object div( Object obj );
	}
	
	//三角函数
	interface ITrigonometric{ 
		public static final int i =10; //接口变量
		int j = 20;                           //可以不写 public abstract final
		Object sin( Object obj );
		public  Object cos( Object obj );
		abstract  Object tag( Object obj );
		public abstract  Object ctag( Object obj );
	}
	
	interface ISimpleCalculator extends IBaseCalculator, ITrigonometric{ //接口多继承接口
		public abstract < T >  T Loge( T obj ); //申明泛型方法的语法
		public abstract < T > void Log10( T obj );
				
	}
	
	class BaseCalculator implements IBaseCalculator{ //类继承接口,必须实现接口的方法
		public  Object add( Object obj ) { return  obj; }
		public  Object sub( Object obj ) { return  obj; }
		public  Object mul( Object obj ) { return  obj; }
		public  Object div( Object obj ) { return  obj; }
	}
	
	class BaseCalculatorEx extends BaseCalculator{  //类继承类

	} 
	
	abstract class AbsBaseCalculator implements IBaseCalculator{ //抽象类继承接口
		public  abstract Object add( Object obj );  //可以把接口的函数重写下
		//public  Object sub( Object obj ) { return  obj; } //也可以不用声明
		//public  Object mul( Object obj ) { return  obj; } 
		//public  Object div( Object obj ) { return  obj; }
	}
	
	class Calculator implements IBaseCalculator, ITrigonometric{ //类继承多接口
		public  Object add( Object obj ) { return  obj; }
		public  Object sub( Object obj ) { return  obj; }
		public  Object mul( Object obj ) { return  obj; }
		public  Object div( Object obj ) { return  obj; }
		
		public  Object sin( Object obj ) { return  obj; }
		public  Object cos( Object obj ) { return  obj; }
		public  Object tag( Object obj ) { return  obj; }
		public  Object ctag( Object obj ) { return  obj; }
	}

}
