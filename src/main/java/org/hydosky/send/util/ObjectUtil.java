package org.hydosky.send.util;

import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;

/**
 * 对象工具类
 */
public class ObjectUtil extends ObjectUtils {

	/**
	 * 判断元素不为空
	 * @param obj object
	 * @return boolean
	 */
	public static boolean isNotEmpty(@Nullable Object obj) {
		return !ObjectUtil.isEmpty(obj);
	}

}
