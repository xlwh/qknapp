package com.qing.right.tree;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Description: 中文排序
 * 
 * @author luoqinglong
 * @date 2013-6-28 上午9:22:16
 * @since 1.0
 **/
public class EntitySortProvider {
    private static final Log log = LogFactory.getLog(EntitySortProvider.class);

    /**
     * Description:
     * 
     * @param compareList
     *            需排序的数据集合
     * @param name
     *            获取排序字段的方法名，如getXXX
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static List sort(List compareList, final String methodName) {

	// 按组织中文名称排序
	Collections.sort(compareList, new Comparator<Object>() {
	    @Override
	    public int compare(Object o1, Object o2) {
		PinyinComparator p = new PinyinComparator();
		int flag = 0;// 默认不排序
		try {
		    String name1 = o1.getClass().getDeclaredMethod(methodName).invoke(o1).toString();
		    String name2 = o2.getClass().getDeclaredMethod(methodName).invoke(o2).toString();
		    flag = p.compare(name1, name2);
		} catch (Exception e) {
		    log.error("排序异常:" + e);
		}
		return flag;
	    }
	});
	return compareList;
    }

}
