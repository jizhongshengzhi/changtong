package com.mt.common.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class StringUtil extends StringUtils {
	public static String nullConvertToEmptyString(String string) {
		String lastusername = string == null ? "" : string;
		return lastusername;
	}

	public static String nullConvertToEmptyString(Object string) {
		String lastusername = string == null ? "" : (String) string;
		return lastusername;
	}

	public static BigDecimal nullConvertToBigDecimal(Object bd) {
		BigDecimal lastusername = bd == null ? BigDecimal.ZERO : (BigDecimal) bd;
		return lastusername;
	}

	/**
	 * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
	 *
	 * @param c
	 *            需要判断的字符
	 * @return 返回true,Ascill字符
	 */
	public static boolean isLetter(char c) {
		int k = 0x80;
		return c / k == 0 ? true : false;
	}

	/**
	 * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
	 *
	 * @param s
	 *            需要得到长度的字符串
	 * @return i得到的字符串长度
	 */
	public static int length(String s) {
		if (s == null)
			return 0;
		char[] c = s.toCharArray();
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			len++;
			if (!isLetter(c[i])) {
				len++;
			}
		}
		return len;
	}

	/**
	 * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
	 *
	 *
	 * @param origin
	 *            原始字符串
	 * @param len
	 *            截取长度(一个汉字长度按2算的)
	 * @param c
	 *            后缀
	 * @return 返回的字符串
	 */
	public static String substring(String origin, int len, String c) {
		if (origin == null || origin.equals("") || len < 1)
			return "";
		byte[] strByte = new byte[len];
		if (len > length(origin)) {
			return origin + c;
		}
		try {
			System.arraycopy(origin.getBytes("GBK"), 0, strByte, 0, len);
			int count = 0;
			for (int i = 0; i < len; i++) {
				int value = strByte[i];
				if (value < 0) {
					count++;
				}
			}
			if (count % 2 != 0) {
				len = (len == 1) ? ++len : --len;
			}
			return new String(strByte, 0, len, "GBK") + c;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按字节数获取子字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param beginindex
	 *            开始位置
	 * @param endindex
	 *            结束位置
	 * @return 子字符串
	 */

	public static String subStrByByte(String str, int beginindex, int endindex, String charset) {
		String result = "";
		int charLength = 0;
		int tempIndex1 = 0;
		int tempIndex2 = 0;
		int charBeginIndex = -1;
		int charEndIndex = -1;

		if (endindex > beginindex && beginindex >= 0) {
			for (int i = 0; i < str.length(); i++) {
				try {

					if (charset == null || charset.isEmpty()) {
						charLength = str.substring(i, i + 1).getBytes().length;
					} else {
						charLength = str.substring(i, i + 1).getBytes(charset).length;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				tempIndex1 = tempIndex2;
				tempIndex2 += charLength;

				if (beginindex >= tempIndex1 && beginindex < tempIndex2) {
					charBeginIndex = i;
				}

				if (endindex >= tempIndex1 && endindex < tempIndex2) {
					charEndIndex = endindex == tempIndex1 ? i : i + 1;
					break;
				}
			}

			charEndIndex = charEndIndex == -1 ? (charBeginIndex == -1 ? 0 : str.length()) : charEndIndex;
			charBeginIndex = charBeginIndex == -1 ? 0 : charBeginIndex;
			result = str.substring(charBeginIndex, charEndIndex);
		}

		return result.trim();
	}

	/**
	 * 按字节数获取子字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param beginindex
	 *            开始位置
	 * @param endindex
	 *            结束位置
	 * @return 子字符串
	 */

	public static String subStrByByteNoEncode(String str, int beginindex, int endindex, String charset) {
		String result = "";
		int charLength = 0;
		int tempIndex1 = 0;
		int tempIndex2 = 0;
		int charBeginIndex = -1;
		int charEndIndex = -1;

		if (endindex > beginindex && beginindex >= 0) {
			for (int i = 0; i < str.length(); i++) {
				charLength = str.substring(i, i + 1).getBytes().length;

				tempIndex1 = tempIndex2;
				tempIndex2 += charLength;

				if (beginindex >= tempIndex1 && beginindex < tempIndex2) {
					charBeginIndex = i;
				}

				if (endindex >= tempIndex1 && endindex < tempIndex2) {
					charEndIndex = endindex == tempIndex1 ? i : i + 1;
					break;
				}
			}

			charEndIndex = charEndIndex == -1 ? (charBeginIndex == -1 ? 0 : str.length()) : charEndIndex;
			charBeginIndex = charBeginIndex == -1 ? 0 : charBeginIndex;
			result = str.substring(charBeginIndex, charEndIndex);
		}

		return result.trim();
	}

	/**
	 * 返回String 数组 的toString
	 * 
	 * @param strS
	 *            数组
	 * @return toString
	 */
	public static String getStringsToString(String[] strS) {
		StringBuffer reStr = new StringBuffer();
		String s = ",";
		for (String str : strS) {
			reStr = reStr.append(str).append(s);
		}
		if (reStr.length() > 0) {
			return reStr.substring(0, reStr.length() - 1);
		} else {
			return reStr.toString();
		}
	}

	/**
	 * 按字节数获取子字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param beginindex
	 *            开始位置
	 * @param endindex
	 *            结束位置
	 * @return 子字符串
	 * @throws UnsupportedEncodingException
	 */
	public static String subStrByByteEncoding(String str, int beginindex, int endindex, String encoding) {

		try {
			if (endindex <= beginindex) {
				return "";
			}
			if (beginindex < 0) {
				return "";
			}
			byte[] bytes;

			bytes = str.getBytes(encoding);

			byte[] resultbytes = new byte[endindex - beginindex];
			for (int i = beginindex; i < endindex; i++) {
				resultbytes[i - beginindex] = bytes[i - 1];
			}

			return new String(resultbytes, encoding);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 按字节数获取子字符串
	 * 
	 * @param str
	 *            原字符串
	 * @param beginindex
	 *            开始位置
	 * @param endindex
	 *            结束位置
	 * @return 子字符串
	 */

	public static String subString(String str, int beginindex, int endindex) {

		return str.substring(beginindex, endindex);
	}

	public static String getStringsByLongList(List<Long> strArr) {
		String strs = "";
		if (strArr.size() > 0) {
			for (Long str : strArr) {
				strs += str + ",";
			}
		}

		if (strs.length() > 0) {
			strs = strs.substring(0, strs.length() - 1);
		}
		return strs;
	}

	public static String getStringsByStringList(List<String> strArr) {
		String strs = "";
		if (strArr.size() > 0) {
			for (String str : strArr) {
				strs += "'" + str + "',";
			}
		}

		if (strs.length() > 0) {
			strs = strs.substring(0, strs.length() - 1);
		}
		return strs;
	}

	/**
	 * @author Zhangjianbao 把时间格式字符串转换成Quartz 日期格式(精确到时分秒) <<10:15:00>> to << 0
	 *         15 10 * * ? * >>
	 * @param time
	 * @return
	 */
	public static String getTimeToQuartzHHMMss(String time) {
		return reverseSort(time).replaceAll(":", " ") + " * * ? *";
	}

	/**
	 * @author Zhangjianbao 字符串倒序输出 <<abc>> to <<cba>>
	 * @param str
	 * @return
	 */
	public static String reverseSort(String str) {
		String str2 = "";
		for (int i = str.length() - 1; i > -1; i--) {
			str2 += String.valueOf(str.charAt(i));
		}

		return str2;
	}

    /**
     * 得到list的String串
     * @param list
     * @return
     */
    public static String getStringByList(List<Date> list){
		String str = null;
		for (Date s : list) {
			String stoString = s.toString();
			if (StringUtils.isNotBlank(stoString)) {
				stoString = stoString + ",";
				str += stoString;
			}
		}
		if (str.lastIndexOf(",") != -1)
			str = str.substring(0, str.length());
		return str;
    }

    /**
     * 判断输入参数是否为空
     * @param string
     * @return
     */
	public static boolean isEmpty(String string) {
		return string == null || string.trim().isEmpty();
	}
	
	/**
	 * 判断个对象是否为空
	 * 
	 * @param source 要判断对象
	 * @return 如果为空返回真，反之为假
	 */
	public static boolean isEmpty(Object source) {
		return (source == null || source.toString().trim().equals(""));
	}
	
	/**
	 * 手机号码验证
	 * @param mobile
	 * @return
	 */
	public static boolean checkMobile(String mobile){
		Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|18[0-9])\\d{8}$");  
		Matcher m = p.matcher(mobile);  
		return m.matches();
	}
	
	/**
	 * 将字符整型转换成整形
	 * 
	 * @param value
	 *            字符整型
	 * @return 整型
	 */
	public static Integer getIntValue(String value) {
		return getIntValue(value, 0);
	}
	
	/**
	 * 将整型对象转换成整形
	 * 
	 * @param value
	 *            整型对象
	 * @return 整型
	 */
	public static Integer getIntValue(Object value) {
		if (value == null)
			return 0;
		return getIntValue(value.toString());
	}
	
	public static Integer getIntValue(String value, int defaultValue) {
		try {
			value = value.concat(".");
			return Integer.parseInt(value.substring(0, value.indexOf(".")));
		} catch (Throwable e) {
			return defaultValue;
		}
	}

    /**
     * 根据名称Map获取集合总称（逗号分隔）
     * @param ids ID集合
     * @param nameMap 名称Map 
     * @return
     */
    public static String getNameByMap(List<Long> ids, Map<Long, String> nameMap) {
    	String name = "";
    	if (ids != null) {
	    	for (int j = 0; j < ids.size(); j++) {
				if (j > 0)
					name += ",";
				name += nameMap.get(ids.get(j));
			}
    	}
    	return name;
    }

	public static String getStringValue(Object val) {
		return getStringValue(val, "");
	}

	public static String getStringValue(Object val, String defaultValue) {
		if (val == null) {
			return defaultValue;
		}
		return val.toString();
	}

	/**
	 * 按分隔符分割字符
	 * 
	 * @param str
	 *            原始字符
	 * @param spec
	 *            分隔
	 * @return 分割后的字符数组
	 */
	public static String[] str2array(String str, String spec) {
		return str2array(str, spec, false);
	}

	/**
	 * 按分隔符分割字符
	 * 
	 * @param str
	 *            原始字符
	 * @param spec
	 *            分隔
	 * @param withNull
	 *            是否统计空字符串
	 * @return 分割后的字符数组
	 */
	public static String[] str2array(String str, String spec, boolean withNull) {
		if (isEmpty(str) && !withNull)
			return new String[0];
		if (isEmpty(str) && withNull)
			return new String[] { "" };
		List<String> vt = str2List(str, spec, withNull);
		String[] ar = vt.toArray(new String[0]);
		return ar;
	}

	/**
	 * 按分隔符分割字符
	 * 
	 * @param str
	 *            原始字符
	 * @param spec
	 *            分隔
	 * @param withNull
	 *            是否统计空字符串
	 * @return 分割后的字符链表
	 */
	public static List<String> str2List(String str, String spec,
			boolean withNull) {
		if (str == null)
			str = "";
		Vector<String> vt = new Vector<String>();
		while (str.indexOf(spec) != -1) {
			String tmp = str.substring(0, str.indexOf(spec));
			if (withNull || !tmp.equals(""))
				vt.addElement(tmp.trim());
			str = str.substring(str.indexOf(spec) + spec.length());
		}
		if (withNull || !isEmpty(str.trim()))
			vt.addElement(str.trim());
		return vt;
	}

	public static List<Long> stringSplitToLong(String str, String split) {
		List<Long> resutls = new ArrayList<Long>();
		if (str == null || split == null) {
			return resutls;
		}
		String[] tmps = str.split(split);
		Long[] longs = new Long[tmps.length];
		for (int i = 0; i < longs.length; i++) {
			resutls.add(Long.valueOf(tmps[i]));
		}
		return resutls;
	}

	public static String escape(String string) {
		return string == null ? "" : string;
	}
	
	/**
	 * 四位数字验证码
	 * @return
	 */
	public static String genRandomCode() {
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			sRand += rand;
		}
		return sRand;
	}
	/**
	 * 必须由特殊字符、英文字母、数字，长度为6位到20位之间
	 * @param pass
	 * @return
	 */
	public static boolean checkPassword(String pass) {
		boolean l1 = false, n1 = false, a1 = false, s1 = false;
		if (pass.length() >= 6 && pass.length() <= 20) {
			l1 = true;
		}
		int leng = 0;
		for (int i = 0; i < pass.length(); i++) {
			if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9') {
				n1 = true;
				leng++;
			} else if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z' || pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') {
				a1 = true;
				leng++;
			} else if (pass.charAt(i) == '~' || pass.charAt(i) == '!' || pass.charAt(i) == '@' || pass.charAt(i) == '#' || 
					pass.charAt(i) == '$' || pass.charAt(i) == '%' || pass.charAt(i) == '^' || pass.charAt(i) == '&' ||
					pass.charAt(i) == '*' || pass.charAt(i) == '(' || pass.charAt(i) == ')' || pass.charAt(i) == '-' ||
					pass.charAt(i) == '_' || pass.charAt(i) == '+' || pass.charAt(i) == '=' || pass.charAt(i) == '?' ||
					pass.charAt(i) == '|' || pass.charAt(i) == '[' || pass.charAt(i) == ']' || pass.charAt(i) == '{' ||
					pass.charAt(i) == '}' || pass.charAt(i) == '.' || pass.charAt(i) == ',' || pass.charAt(i) == ';') {
				s1 = true;
				leng++;
			}
		}
		return l1 && n1 && a1 && s1 && leng == pass.length();
	}
	/**
	 * 必须由特殊字符、英文字母、数字，长度为6位到20位之间
	 * @param pass
	 * @return
	 */
	public static boolean checkPasswordSimple(String pass) {
		boolean l1 = false, n1 = false, a1 = false;
		if (pass.length() >= 6 && pass.length() <= 20) {
			l1 = true;
		}
		int leng = 0;
		for (int i = 0; i < pass.length(); i++) {
			if (pass.charAt(i) >= '0' && pass.charAt(i) <= '9') {
				n1 = true;
				leng++;
			} else if (pass.charAt(i) >= 'A' && pass.charAt(i) <= 'Z' || pass.charAt(i) >= 'a' && pass.charAt(i) <= 'z') {
				a1 = true;
				leng++;
			}
		}
		return l1 && n1 && a1 && leng == pass.length();
	}

	/**
	 * 替换字符为编辑状态
	 * @param src
	 * @return
	 */
	public static String replaceStringForEdit(String src) {
		src = replace(src, "\n", "\\n");
		src = replace(src, "\r", "\\r");
		src = replace(src, "'", "\\'");
		//src = replace(src, "<", "&lt;");
		//src = replace(src, ">", "&gt;");
		return src;
	}

    /**
     * 按char型分隔符分割字符,将所给分隔符变成拆分成多个char,然后分别分割
     * @param str 原始字符
     * @param spec 分隔�\uFFFD
     * @return 分割后的字符数组
     */
    public static String[] strSplit(String str, String spec) {
        return strSplit(str, spec, false);
    }

    /**
     * 按char型分隔符分割字符,将所给分隔符变成拆分成多个char,然后分别分割
     * @param str 原始字符
     * @param spec 分隔�\uFFFD
     * @param withNull 是否统计空字符串
     * @return 分割后的字符数组
     */
    public static String[] strSplit(String str, String spec, boolean withNull) {
        if (isEmpty(str))
            return new String[0];
        StringTokenizer token = new StringTokenizer(str, spec);
        int count = token.countTokens();
        List<String> vt = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            String tmp = token.nextToken();
            if (withNull || !isEmpty(tmp))
                vt.add(tmp.trim());
        }
        return (String[]) vt.toArray(new String[0]);
    }
	/**
	 * 替换url中的+为%2B
	 * @param url
	 * @return
	 */
	public static String replaceUrl(String url) {
		return replace(url, "+", "%2B");
	}
}
