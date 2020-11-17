package io.finarkein.flux.util;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataTypesUtils {

	private static final Pattern INTEGER_PATTERN = Pattern.compile("^[0-9]{1," + Integer.MAX_VALUE + "}$");
	private static final Pattern LONG_PATTERN = Pattern.compile("^[0-9]{" + Integer.MAX_VALUE + ",}$");
	private static final Pattern DECIMAL_PATTERN = Pattern.compile("^[0-9]+\\.[0-9]+$");

	public static Integer resolveToInteger(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof String) {
			Double var = Double.parseDouble((String) o);
			return var.intValue();
		} else if (o instanceof Integer) {
			return (Integer) o;
		} else if (o instanceof Double) {
			return ((Double) o).intValue();
		} else if (o instanceof Long) {
			return ((Long) o).intValue();
		} else if (o instanceof Float) {
			return ((Float) o).intValue();
		}
		throw new UnsupportedOperationException("Datatype can not be resolved to Integer");
	}

	public static Double resolveToDouble(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof String) {
			return Double.parseDouble((String) o);
		} else if (o instanceof Double) {
			return (Double) o;
		} else if (o instanceof Integer) {
			return (double) (Integer) o;
		} else if (o instanceof Long) {
			return ((Long) o).doubleValue();
		} else if (o instanceof Float) {
			return ((Float) o).doubleValue();
		}
		throw new UnsupportedOperationException("Datatype can not be resolved to Double");
	}

	public static String resolveToString(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof String) {
			return (String) o;
		} else {
			return o.toString();
		}
	}

	public static Timestamp resolveToTimestamp(Object o) {
		if (o == null) {
			return null;
		} else if (o instanceof Timestamp) {
			return (Timestamp) o;
		} else if (o instanceof Date) {
			return new Timestamp(((Date) o).getTime());
		} else if (o instanceof java.sql.Date) {
			return new Timestamp(((java.sql.Date) o).getTime());
		} else if (o instanceof Calendar) {
			return new Timestamp(((Calendar) o).getTimeInMillis());
		}
		throw new UnsupportedOperationException("Datatype can not be resolved to Timestamp");
	}

	public static Number resolveNumericDataType(String value) {

		Matcher integerMatcher = INTEGER_PATTERN.matcher(value);
		Matcher longMatcher = LONG_PATTERN.matcher(value);
		Matcher decimalMatcher = DECIMAL_PATTERN.matcher(value);

		if (integerMatcher.matches())
			return Integer.parseInt(value);
		if (longMatcher.matches())
			return new BigInteger(value);
		if (decimalMatcher.matches()) {
			// TODO : bigdecimal ?
			return Double.parseDouble(value);
		}

		throw new IllegalArgumentException(String.format("Unsupported datatype. Can not determine datatype of input : '%s'", value.toString()));
	}
}