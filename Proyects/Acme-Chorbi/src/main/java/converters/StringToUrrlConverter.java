/*
 * StringToAuthorityConverter.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Urrl;

@Component
@Transactional
public class StringToUrrlConverter implements Converter<String, Urrl> {

	@Override
	public Urrl convert(final String text) {
		Urrl result;

		try {
			result = new Urrl();
			result.setLink(text);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
