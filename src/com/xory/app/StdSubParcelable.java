/**
 * 
 */
package com.xory.app;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author xory
 * @category 标准的Parcelable子类写法
 * 
 * @see 关于describeContents()的一个说法:
 *      http://blog.csdn.net/l_yqing/article/details/7522183
 */
public class StdSubParcelable implements Parcelable {
	private int mnData = 1;
	private String mstrData = "data";
	private String[] marrayStringData = {"1", "2", "3"};
	{

	}

	//必须定义此成员变量并实现它
	public static final Parcelable.Creator<StdSubParcelable> CREATOR = new Parcelable.Creator<StdSubParcelable>() {

		@Override
		public StdSubParcelable createFromParcel(Parcel source) {
			StdSubParcelable obj = new StdSubParcelable();
			obj.mnData = source.readInt();
			obj.mstrData = source.readString();
			Object objs[] = source.readArray(null);
		//	obj.marrayStringData = (String[])source.readArray(null);
			
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
