/**
 * 
 */
package com.xory.syntax;

/**
 * @author xory
 * public: ���ж��ܷ���, ��������,����,����,�ӿ�
 * protected: ����,��ǰ���ܷ���,ֻ�����η���,����
 * default: ����friendly, ��Ĭ��ֵ, ��ǰ���ܷ���, ��������,����,����
 * private: ��ǰ���ܷ���, ���η���,����
 * abstract: ����������,����, �����abstract����,�������ʹ��abstract
 * interface: ���������� public static final, ���Բ�д, ���������Ĭ��
 *                �ӿڵķ������� public �� abstract,Ҳ���Բ�д
 * ��ϸ������: http://www.w3cschool.cc/java/java-modifier-types.html               
 */
public class Syntax {
	
	//�ӿ�Ĭ��(�ұ���)Ϊpublic static final,�ɲ�д; ���ӿڵķ���Ĭ��(�ұ���)public  abstract,���Բ�д
	//�ӿڵı���Ĭ��(�ұ���) public abstract final, ���Բ�д
	interface IBaseCalculator{
		Object add( Object obj );
		public  Object sub( Object obj );
		abstract  Object mul( Object obj );
		public abstract  Object div( Object obj );
	}
	
	//���Ǻ���
	interface ITrigonometric{ 
		public static final int i =10; //�ӿڱ���
		int j = 20;                           //���Բ�д public abstract final
		Object sin( Object obj );
		public  Object cos( Object obj );
		abstract  Object tag( Object obj );
		public abstract  Object ctag( Object obj );
	}
	
	interface ISimpleCalculator extends IBaseCalculator, ITrigonometric{ //�ӿڶ�̳нӿ�
		public abstract < T >  T Loge( T obj ); //�������ͷ������﷨
		public abstract < T > void Log10( T obj );
				
	}
	
	class BaseCalculator implements IBaseCalculator{ //��̳нӿ�,����ʵ�ֽӿڵķ���
		public  Object add( Object obj ) { return  obj; }
		public  Object sub( Object obj ) { return  obj; }
		public  Object mul( Object obj ) { return  obj; }
		public  Object div( Object obj ) { return  obj; }
	}
	
	class BaseCalculatorEx extends BaseCalculator{  //��̳���

	} 
	
	abstract class AbsBaseCalculator implements IBaseCalculator{ //������̳нӿ�
		public  abstract Object add( Object obj );  //���԰ѽӿڵĺ�����д��
		//public  Object sub( Object obj ) { return  obj; } //Ҳ���Բ�������
		//public  Object mul( Object obj ) { return  obj; } 
		//public  Object div( Object obj ) { return  obj; }
	}
	
	class Calculator implements IBaseCalculator, ITrigonometric{ //��̳ж�ӿ�
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
