package com.qing.right.tree;

import java.util.Comparator;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * 汉字比较器
 * 
 * @author luoqinglong
 * @date 2013-6-27
 */
public class PinyinComparator implements Comparator<String> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(String o1, String o2) {

		for (int i = 0; i < o1.length() && i < o2.length(); i++) {

			int codePoint1 = o1.charAt(i);
			int codePoint2 = o2.charAt(i);

			if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
				i++;
			}

			if (codePoint1 != codePoint2) {
				if (Character.isSupplementaryCodePoint(codePoint1) || Character.isSupplementaryCodePoint(codePoint2)) {
					return codePoint1 - codePoint2;
				}

				String pinyin1 = this.pinyin((char) codePoint1);
				String pinyin2 = this.pinyin((char) codePoint2);

				if (pinyin1 != null && pinyin2 != null) { // 两个字符都是汉字
					if (!pinyin1.equals(pinyin2)) {
						return pinyin1.compareTo(pinyin2);
					}
				} else {
					return codePoint1 - codePoint2;
				}
			}
		}
		return o1.length() - o2.length();
	}

	/**
	 * 字符的拼音，多音字就得到第一个拼音。不是汉字，就return null。
	 */
	private String pinyin(char c) {
		String[] pinyins = PinyinHelper.toHanyuPinyinStringArray(c);
		if (pinyins == null) {
			return null;
		}
		return pinyins[0];
	}

}
