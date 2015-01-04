/**
 * 
 */
package com.xory.lib.utility;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.OnObbStateChangeListener;
import android.os.storage.StorageManager;


/**
 * @author 朱起伟
 * 
 */
public class StorageManagerEx {

	private StorageManager mStorageMgr;

	/**
	 * 
	 */
	public StorageManagerEx(Context context) {
		mStorageMgr = (StorageManager) context
				.getSystemService(Context.STORAGE_SERVICE);

	}
	
	/**
	 * 
	 * @param strPath, the directory, like this: Environment.getDataDirectory(), or /mnt/sdcard eg.
	 * @return MB
	 */
	public static final float GetFreeStorageSize( String strPath ){
		 StatFs stat = new StatFs( strPath );
	     long sizeBytes  = stat.getAvailableBlocks() * stat.getBlockSize();
	     float sizeMB = (float)( sizeBytes / 1024 /1024 );
	     return sizeMB;
	}

	public boolean mountObb(String filename, String key,
			OnObbStateChangeListener listener) {
		return mStorageMgr.mountObb(filename, key, listener);
	}

	public boolean unmountObb(String filename, boolean force,
			OnObbStateChangeListener listener) {
		return mStorageMgr.unmountObb(filename, force, listener);
	}

	public boolean isObbMounted(String filename) {
		return mStorageMgr.isObbMounted(filename);
	}

	public String getMountedObbPath(String filename) {
		return mStorageMgr.getMountedObbPath(filename);
	}

	/**
	 * Enables USB Mass Storage (UMS) on the device.
	 * 
	 * @hide
	 */
	public void enableUsbMassStorage() {
		try {
			Method method = mStorageMgr.getClass().getMethod(
					"enableUsbMassStorage");
			method.invoke(mStorageMgr);
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::enableUsbMassStorage err, info: "
					+ e.toString());
		}
	}

	/**
	 * Disables USB Mass Storage (UMS) on the device.
	 * 
	 * @hide
	 */
	public void disableUsbMassStorage() {
		try {
			Method method = mStorageMgr.getClass().getMethod(
					"disableUsbMassStorage");
			method.invoke(mStorageMgr);
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::disableUsbMassStorage err, info: "
					+ e.toString());
		}
	}

	/**
	 * Query if a USB Mass Storage (UMS) host is connected.
	 * 
	 * @return true if UMS host is connected.
	 * 
	 * @hide
	 */
	public boolean isUsbMassStorageConnected() {
		try {
			Method method = mStorageMgr.getClass().getMethod(
					"isUsbMassStorageConnected");
			return ((Boolean) method.invoke(mStorageMgr)).booleanValue();
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::isUsbMassStorageConnected err, info: "
					+ e.toString());
		}
		return false;
	}

	/**
	 * Query if a USB Mass Storage (UMS) is enabled on the device.
	 * 
	 * @return true if UMS host is enabled.
	 * 
	 * @hide
	 */
	public boolean isUsbMassStorageEnabled() {
		try {
			Method method = mStorageMgr.getClass().getMethod(
					"isUsbMassStorageEnabled");
			return ((Boolean) method.invoke(mStorageMgr)).booleanValue();
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::isUsbMassStorageEnabled err, info: "
					+ e.toString());
		}
		return false;
	}

	public String[] getVolumePaths() {
		try {
			Method method = mStorageMgr.getClass().getMethod("getVolumePaths");
			return (String[]) method.invoke(mStorageMgr);
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::getVolumePaths err, info: "
					+ e.toString());
		}
		return null;
	}

	public String getVolumeState(String mountPoint) {
		try {
			Method method = mStorageMgr.getClass().getMethod("getVolumeState",
					String.class);
			return (String) method.invoke(mStorageMgr, mountPoint);
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::getVolumeState err, info: "
					+ e.toString());
		}
		return null;
	}

	public StorageVolume[] getVolumeList() {
		try {

			String strPath;
			String strDescription;
			boolean bRemovable;
			boolean bEmulated;
			int nMtpReserveSpace;
			boolean bAllowMassStorage;
			int nStorageId;
			long lMaxFileSize;

			Method method = mStorageMgr.getClass().getMethod("getVolumeList");
			Object[] objs = (Object[]) method.invoke(mStorageMgr);
			StorageVolume[] arrStorageVolumes = new StorageVolume[objs.length];
			Field field;
			for (int i = 0; i < objs.length; i++) {
				field = objs[i].getClass().getDeclaredField("mPath");
				field.setAccessible(true);
				strPath = (String) field.get(objs[i]);

				field = objs[i].getClass().getDeclaredField("mDescription");
				field.setAccessible(true);
				strDescription = (String) field.get(objs[i]);

				field = objs[i].getClass().getDeclaredField("mRemovable");
				field.setAccessible(true);
				bRemovable = field.getBoolean(objs[i]);

				field = objs[i].getClass().getDeclaredField("mEmulated");
				field.setAccessible(true);
				bEmulated = field.getBoolean(objs[i]);

				field = objs[i].getClass().getDeclaredField("mMtpReserveSpace");
				field.setAccessible(true);
				nMtpReserveSpace = field.getInt(objs[i]);

				field = objs[i].getClass()
						.getDeclaredField("mAllowMassStorage");
				field.setAccessible(true);
				bAllowMassStorage = field.getBoolean(objs[i]);

				field = objs[i].getClass().getDeclaredField("mStorageId");
				field.setAccessible(true);
				nStorageId = field.getInt(objs[i]);

				field = objs[i].getClass().getDeclaredField("mMaxFileSize");
				field.setAccessible(true);
				lMaxFileSize = field.getLong(objs[i]);

				StorageVolume storageVolume = new StorageVolume(strPath,
						strDescription, bRemovable, bEmulated,
						nMtpReserveSpace, bAllowMassStorage, lMaxFileSize);
				storageVolume.setStorageId(nStorageId);

				arrStorageVolumes[i] = storageVolume;
			}
			return arrStorageVolumes;
		} catch (Exception e) {
			LogInfo.e("StorageManagerEx::getVolumeList err, info: "
					+ e.toString());
		}

		return null;
	}

}
