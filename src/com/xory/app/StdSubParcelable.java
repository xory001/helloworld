/**
 * 
 */
package com.xory.app;

import java.util.Arrays;

import android.os.Parcel;
import android.os.Parcelable;

import com.xory.lib.utility.LogInfo;

/**
 * @author xory
 * @category 标准的Parcelable子类写法
 * @tag 数组转换 类型擦除
 * 
 * @see 关于describeContents()的一个说法:
 *      http://blog.csdn.net/l_yqing/article/details/7522183
 */
public class StdSubParcelable implements Parcelable {
	private int mnData = 1;
	private String mstrData = "data";
	private String[] marrayStringData;
	
	public void init(){
		if( null == marrayStringData )
		{
			String[] arr = {"1", "2", "3"};
			marrayStringData = arr;
		}
	}


	//必须定义此成员变量CREATOR,并实现赋值
	public static final Parcelable.Creator<StdSubParcelable> CREATOR = new Parcelable.Creator<StdSubParcelable>() {

		@Override
		public StdSubParcelable createFromParcel(Parcel source) { //此source与writeToParcel中的dest不是同一个对象
			StdSubParcelable obj = new StdSubParcelable();
			obj.mnData = source.readInt();
			obj.mstrData = source.readString();
			Object objs[] = source.readArray(null);
			if ( objs instanceof String[] ){ //对于parcel来讲,虽然write的是String数组,但是经过传输中的对象不断复制,类型被擦除了,所以这里永远不会进来
				obj.marrayStringData = (String[])objs;
			}else{
				//obj.marrayStringData = (String[])Arrays.asList( objs ).toArray(); //会出错,同样因为类型擦除掉了
				obj.marrayStringData = Arrays.asList( objs ).toArray( new String[0] ); //toArray必须传入非空数组,内部会resize,然后返回
			}
			return obj;
		}

		@Override
		public StdSubParcelable[] newArray(int size) {
			return new StdSubParcelable[size];
		}
	};

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt( mnData );
		dest.writeString( mstrData );
		dest.writeArray(marrayStringData);
	}

}
