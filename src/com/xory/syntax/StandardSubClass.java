/**
 * 
 */
package com.xory.syntax;

import java.util.Arrays;

/**
 * @author 朱起伟
 * @category 标准子类的写法
 * @tag 数组初始化,数组操作类,重载equals,hashCode,toString
 * @how2do
 * @1 重载equals
 * @2 如果重载了equals,必须重载hashCode
 * @3 重载toString
 * @extra 1 如果从Object继承,则无需处理super.equals,super.hashCode,super.toString
 * @extra 2 否则equals要先判断super.equals, hashCode也需要计算super.hashCode,
 *        toString也需加上super.toString
 * @see 参考: 如何写equals, http://www.cnblogs.com/chenssy/p/3416195.html
 * @see 参考: google Android 例子:
 *      http://developer.android.com/reference/java/lang/Object.html
 */
public class StandardSubClass extends Object {
	private int mnData;
	private StringBuilder[] mstrData = new StringBuilder[9]; // 对象数组初始化的时候,里面的元素都为null,必须显式赋值
																// 关于数组的操作在
																// Arrays类里面
	private String mstrName;
	private Object mObj = new Object();

	public StandardSubClass(String strName, String strData) {
		mstrName = strName;
		boolean bSet = false;
		for (int i = 1; i < mstrData.length; i++) {// 从1开始
			if (null == mstrData[i]) {
				mstrData[i] = new StringBuilder(strData);
				bSet = true;
				break;
			}
		}
		if (!bSet) {
			mstrData[0].setLength(0); // 据说速度比mstrData[0]. delete( 0,
										// mstrData[0.length() )快
			mstrData[0].append(strData);
		}
	}

	@Override
	public boolean equals(Object o) {
		// Return true if the objects are identical.
		// (This is just an optimization, not required for correctness.)
		if (this == o) {
			return true;
		}

		// Return false if the other object has the wrong type.
		// This type may be an interface depending on the interface's
		// specification.
		if (!(o instanceof StandardSubClass)) {
			return false;
		}

		// Cast to the appropriate type.
		// This will succeed because of the instanceof, and lets us access
		// private fields.
		StandardSubClass lhs = (StandardSubClass) o;

		// Check each field. Primitive fields, reference fields, and nullable
		// reference
		// fields are all treated differently.
		return super.equals(lhs) // 要比较父类
				&& mnData == lhs.mnData
				&& mObj.equals(lhs.mObj)
				&& Arrays.equals(mstrData, lhs.mstrData)
				&& (mstrName == null ? lhs.mstrName == null : mstrName
						.equals(lhs.mstrName));
	}

	// 如果重载了equals,必须重载hashCode
	// 如果不打算提供hashCode,则不能返回0,而是抛出异常
	// @Override public int hashCode() {
	// throw new UnsupportedOperationException();
	// }
	/*
	 * hashCode 的常规协定是：
	 * 
	 * @1. 在 Java 应用程序执行期间，在对同一对象多次调用 hashCode 方法时，必须一致地返回相同的整数，前提是将对象进行 equals
	 * 比较时所用的信息没有被修改。从某一应用程序的一次执行到同一应用程序的另一次执行，该整数无需保持一致。
	 * 
	 * @2. 如果根据 equals(Object) 方法，两个对象是相等的，那么对这两个对象中的每个对象调用 hashCode
	 * 方法都必须生成相同的整数结果。
	 * 
	 * @3. 如果根据 equals(java.lang.Object) 方法，两个对象不相等，那么对这两个对象中的任一对象上调用 hashCode
	 * 方法不 要求一定生成不同的整数结果。但是，程序员应该意识到，为不相等的对象生成不同整数结果可以提高哈希表的性能。
	 */
	@Override
	public int hashCode() {
		// Start with a non-zero constant.
		int result = 17;
		result = 31 * result + super.hashCode(); // 父类
		// Include a hash for each field.
		// result = 31 * result + (booleanField ? 1 : 0);
		//
		// result = 31 * result + byteField;
		// result = 31 * result + charField;
		// result = 31 * result + shortField;
		// result = 31 * result + intField;
		//
		// result = 31 * result + (int) (longField ^ (longField >>> 32));
		//
		// result = 31 * result + Float.floatToIntBits(floatField);
		// //特别注意,浮点型,得转换成bit
		//
		// long doubleFieldBits = Double.doubleToLongBits(doubleField);
		// //特别注意,浮点型,得转换成bit
		// result = 31 * result + (int) (doubleFieldBits ^ (doubleFieldBits >>>
		// 32));

		// result = 31 * result + Arrays.hashCode(arrayField);
		//
		// result = 31 * result + referenceField.hashCode();
		// result = 31 * result +
		// (nullableReferenceField == null ? 0
		// : nullableReferenceField.hashCode());
		result = 31 * result + mnData;
		result = 31 * result + mObj.hashCode();
		result = 31 * result + Arrays.hashCode(mstrData);
		result = 31 * result + (mstrName == null ? 0 : mstrName.hashCode());

		return result;
	}

	@Override
	public String toString() {
		return getClass().getName() + "[" + "mnData=" + mnData + ", " + "mObj="
				+ mObj + ", " + ", mstrData=" + Arrays.toString(mstrData)
				+ ", mstrName=" + mstrName + ", super=" + super.toString()
				+ "]"; // 字符串相加,本质是生成临时的StringBuilder对象来实现
		// 如果mstrName对象为null,则StringBuilder会生成"null"字符串来代替
	}
}
