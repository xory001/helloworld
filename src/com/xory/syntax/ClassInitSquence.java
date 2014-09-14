/**
 * 
 */
package com.xory.syntax;

import java.lang.reflect.Field;

import com.xory.lib.utility.LogInfo;

/**
 * @author 朱起伟
 * @data 2014.09.14 22:19
 * @category java类初始化顺序说明
 * @tag 初始化顺序, 静态初始化块, 非静态初始化块, 构造函数
 * @1 先执行"静态初始化块",如果有多个,则按照定义顺序依次初调用(在类的初始化时执行)
 * @2 再执行"非静态初始化块",如果有多个,则安装定义顺序依次调用(在new一个类对象的时候执行)
 * @3 最后执行构造函数
 * @extra 所谓依次调用,其实是在.java编译成.class的时候,会把多个块按照定义顺序合并成一个大块
 * @result: 本例子的执行顺序是: 1 3 2 4 构造函数
 * @result.1 如果如下调用:
 * @result.1 int n = ClassInitSquence.n3; //依次执行 init 1, init 3
 * @result.1 ClassInitSquence cis = new ClassInitSquence(); //依次执行init 2, init 4
 * @result.1 如果调用: ClassInitSquence cis = new ClassInitSquence(); //依次执行1 3 2 4
 * 
 * @result.2 如果调用Class.forName("com.xory.syntax.ClassInitSquence"),
 *           (内部其实调用的ClassLoader是VMStack.getCallingClassLoader(), 但该方法已经在Android
 *           2.3中被丢弃), 类似调用Class.forName("com.xory.syntax.ClassInitSquence",
 *           true, getClassLoader() ), 会初始化1 3
 * 
 * @result.3 如果调用: Class Class_ClassInitSquence = Class.forName(
 *           "com.xory.syntax.ClassInitSquence", false, getClassLoader() );
 *           //不执行任何初始化
 * @result.3 Field fld = Class_ClassInitSquence.getDeclaredField( "n3" );
 *           //不执行初始化
 * @result.3 int n3 = fld.getInt( null ); ); //调用1,3, 即当第一次访问类成员变量的的时候,初始化静态块
 * @result.3 注: n3是静态成员变量,所以fld,getInt的时候可以传入null,非静态成员变量必须指定对象
 * @result.3 注: 可以通过Field.getModifiers,返回一个int值,表示修饰符(eg. static,final ), 然后通过
 *           Modifier的诸多静态方法判断是否具有某一修饰符
 * 
 * 
 * @see 详细参考:http://blog.chinaunix.net/uid-29253311-id-3953220.html
 * @see http://blog.csdn.net/darxin/article/details/5293427
 * @see 类加载器深入解析(非常好)
 *      http://www.ibm.com/developerworks/cn/java/j-lo-classloader/index.html
 * @see 加载器相关: http://www.infoq.com/cn/articles/cf-Java-class-loader/
 * @see 深入分析Java ClassLoader原理:
 *      http://blog.csdn.net/xyang81/article/details/7292380
 * @see Class.forName和ClassLoader.loadClass的比较:
 *      http://blog.csdn.net/wikijava/article/details/5576043
 * 
 * @see Android加载器相关:
 * @see Android类动态加载技术
 *      :http://www.blogjava.net/zh-weir/archive/2011/10/29/362294.html
 * @see 关于Android的ClassLoader探索
 *      :http://blog.csdn.net/czh0766/article/details/6736826
 * @see 注 
 *      :PathClassLoader的类加载路径必须在/data/app路径下,DexClassLoader可以加载sdcard目录下的apk或jar文件
 *      ，编译好的类不能立即使用，要通过dx工具转换为.dex格式的
 * 
 *      Dalvik虚拟机识别的是dex文件，而不是class文件。因此，我们供类加载的文件也只能是dex文件
 *      ，或者包含有dex文件的.apk或.jar文件。 有一个细节，可能大家不容易注意到。PathClassLoader是通过构造函数new
 *      DexFile(path)来产生DexFile对象的；而DexClassLoader则是通过其静态方法loadDex（path,
 *      outpath,
 *      0）得到DexFile对象。这两者的区别在于DexClassLoader需要提供一个可写的outpath路径，用来释放.apk包或者
 *      .jar包中的dex文件
 *      。换个说法来说，就是PathClassLoader不能主动从zip包中释放出dex，因此只支持直接操作dex格式文件，或者已经安装的apk
 *      （因为已经安装的apk在cache中存在缓存的dex文件
 *      ）。而DexClassLoader可以支持.apk、.jar和.dex文件，并且会在指定的outpath路径释放出dex文件。
 * 
 *      另外，PathClassLoader在加载类时调用的是DexFile的loadClassBinaryName，
 *      而DexClassLoader调用的是loadClass。因此，在使用PathClassLoader时类全名需要用”/”替换”.”
 * 
 * @see Android程序安装详解: http://blog.csdn.net/hdhd588/article/details/6739281
 * 
 * @extra 类的加载,有3中方法: new claasA;
 *        Class.forName("classA").newInstance();ClassLoader.loadClass("classA")
 * @extra new加载是强绑定,在编译时就已经绑定了,后面2种是弱绑定,在运行时才绑定
 * @extra new其实类似 Class.forName("classA", true, getClassLoader )
 * @extra 第二个参数true表示调用静态初始化块,false则不调用
 * @extra 第三个参数不能传入null,可用父类的加载器,
 *        如果使用null,内部即使用ClassLoader.getSystemClassLoader(
 *        )代替,但路径不是当前app.apk,变成系统的路径,会导致加载失败
 * @extra 如果2个类的实例要相等,不仅对象本身要相等,类的加载器也要是同一个,所以默认所有Object子类都是systemClassLoader加载,
 *        线程上下文加载器Thread.currentThread().getContextClassLoader(),如果没有显式指定,
 *        默认也是systemClassLoader
 * @extra 注: 有的文字把systemClassLoader成为appClassLoader,本质是同一个东西
 * 
 * 
 * 
 */
public class ClassInitSquence {
	static int n1 = 1;
	static { // 静态初始化块
		n1 = 11;
		LogInfo.i("ClassInitSquence init 1");
	}

	int n2 = 2; // 非静态初始化块
	{
		n2 = 22;
		LogInfo.i("ClassInitSquence init 2");
	}

	static {
		n3 = 333;
		n1 = 111;
		LogInfo.i("ClassInitSquence init 3");
	}

	// 以下语句public static int n3 = 3,
	// 本质等于
	// public staitc int n3;
	// {
	// n3 = 3;
	// }
	// 所以n3最终等于3,即使上面已经赋值位333
	public static int n3 = 3;

	{
		n4 = 44;
		LogInfo.i("ClassInitSquence init 4");
	}
	int n4;

	/**
	 * 
	 */
	public ClassInitSquence() { // 构造函数
		LogInfo.i("ClassInitSquence::ClassInitSquence");
	}

}
