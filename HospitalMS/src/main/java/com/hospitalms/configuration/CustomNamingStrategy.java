package com.hospitalms.configuration;

import java.util.Locale;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

public class CustomNamingStrategy extends PhysicalNamingStrategyStandardImpl {

	private static final long serialVersionUID = 1L;

	/**
	 * Identifies table name & adds _ if finds a camelCase string.
	 */
	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		Identifier iden = new Identifier(addUnderscores(name.getText()), name.isQuoted());
		return iden;
	}

	/**
	 * Identifies column name & adds _ if finds a camelCase string.
	 */
	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		Identifier iden = new Identifier(addUnderscores(name.getText()), name.isQuoted());
		return iden;
	}

	/**
	 * Method to add underscore to the string if finds a camelCase string.
	 */
	protected static String addUnderscores(String name) {
		final StringBuilder buf = new StringBuilder(name.replace('.', '_'));
		for (int i = 1; i < buf.length() - 1; i++) {
			if (Character.isLowerCase(buf.charAt(i - 1)) && Character.isUpperCase(buf.charAt(i))
					&& Character.isLowerCase(buf.charAt(i + 1))) {
				buf.insert(i++, '_');
			}
		}
		return buf.toString().toLowerCase(Locale.ROOT);
	}
}
