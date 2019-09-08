package com.mt.architect.placeholder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.mt.common.utils.ConfigProperty;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.TypedStringValue;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

public class EncryptablePropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	private static final String encryptKey = "ddky.encryptKey";
	private static final String encryptPropNamesName = "ddky.encryptPropNames";
	private static final String configFilterPrefixKey = "CONFIG_FILTER_PREFIX";
	
	@Override
	protected void loadProperties(Properties props) throws IOException {
		super.loadProperties(props);
	}

	@Override
	protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties props) throws BeansException {
		try {
			String encryptPropNames = props.getProperty(encryptPropNamesName);
			String configFilterPrefix = props.getProperty(configFilterPrefixKey);
			if (encryptPropNames != null && !encryptPropNames.isEmpty()) {
				String key = props.getProperty(encryptKey);
				if (key == null) {
					throw new Exception("encrypt key not found. add ddky.encryptKey in your *.properties files.");
				}
				String[] encryptPropNamesArray = encryptPropNames.split(",");
				for (String encryptPropName : encryptPropNamesArray) {
					String encryptValue = props.getProperty(encryptPropName);
					if (encryptValue != null) {
						String decryptValue = Des.Decrypt(encryptValue, Des.hex2byte(key));
						props.setProperty(encryptPropName, decryptValue);
					}
				}
			}

			//鍒濆鍖栭厤缃�
			ConfigProperty.initConfigFilter(configFilterPrefix, props);
			List<String> changeBeanNames = postProcessProperties(beanFactory);
			super.processProperties(beanFactory, props);

			DefaultListableBeanFactory beanFactoryPost = (DefaultListableBeanFactory) beanFactory;
			for (String curName : changeBeanNames) {
				BeanDefinition bd = beanFactoryPost.getBeanDefinition(curName);
				beanFactoryPost.registerBeanDefinition(curName, bd);
			}
		} catch (Exception e) {
			throw new BeanInitializationException(e.getMessage(), e);
		}
	}

	protected List<String> postProcessProperties(ConfigurableListableBeanFactory beanFactoryToProcess) {
		List<String> bnList = new ArrayList<String>();
		String[] beanNames = beanFactoryToProcess.getBeanDefinitionNames();
		for (String curName : beanNames) {
			BeanDefinition bd = beanFactoryToProcess.getBeanDefinition(curName);
			boolean isChanged = false;
			if (!this.getClass().getName().equals(bd.getBeanClassName())) {
				List<PropertyValue> pvl = bd.getPropertyValues().getPropertyValueList();
				for (PropertyValue pv : pvl) {
					if (pv.getValue() instanceof TypedStringValue) {
						String val = ( (TypedStringValue) pv.getValue() ).getValue();
						if (val.startsWith("${")) {
							isChanged = true;
							break;
						}
					}
				}
				if (isChanged) {
					bnList.add(curName);
				}
			}
		}
		return bnList;
	}
}
