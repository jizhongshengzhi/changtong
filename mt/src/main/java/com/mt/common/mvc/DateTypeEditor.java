package com.mt.common.mvc;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;



/**
 * 日期编辑器
 * 
 * 根据日期字符串长度判断是长日期还是短日期。只支持yyyy-MM-dd，yyyy-MM-dd HH:mm:ss两种格式。
 * 
 * @author aijava
 * 
 */
public class DateTypeEditor extends PropertyEditorSupport {
	
	/**
	 * 短类型日期长度
	 */
	public static final int SHORT_DATE = 10;

	public void setAsText(String text) throws IllegalArgumentException {
		text = text.trim();
		if (!StringUtils.hasText(text)) {
			setValue(null);
			return;
		}
		try {
			if (text.length() <= SHORT_DATE) {
				setValue(new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(text).getTime()));
			} else {
				setValue(new java.sql.Timestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(text).getTime()));
			}
		} catch (ParseException ex) {
			IllegalArgumentException iae = new IllegalArgumentException(
					"Could not parse date: " + ex.getMessage());
			iae.initCause(ex);
			throw iae;
		}
	}

	/**
	 * Format the Date as String, using the specified DateFormat.
	 */
	public String getAsText() {
		Date value = (Date) getValue();
		return (value != null ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value) : "");
	}
}
