/*
 * AuthorityToStringConverter.java
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
public class UrrlToStringConverter implements Converter<Urrl, String> {

	@Override
	public String convert(final Urrl urrl) {
		String result;

		if (urrl == null)
			result = null;
		else
			result = urrl.getLink();

		return result;
	}

}
