package io.finarkein.flux.expr;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static io.finarkein.flux.expr.Expr.*;
import static io.finarkein.flux.expr.ExprType.*;

public class Functions {
	public static final Function<java.util.Date, java.sql.Date> toSqlDate = utilDate -> new Date(utilDate.getTime());

	public static Expr expr(String expressionString) {
		return unary(null, STR_EXPR, expressionString);
	}

	public static Expr arrayIntersect(Expr expr1, Expr expr2) { return binary(expr1, expr2, ARRAY_INTERSECT);}

	public static Expr arrayUnion(Expr expr1, Expr expr2) { return binary(expr1, expr2, ARRAY_UNION);}

	public static Expr when(Expr condition, Object value) {
		return new CasesExpr(condition, value);
	}

	public static Expr var(String name) {
		return Expr.variable(name);
	}

	public static Expr lit(Object literal) {
		if (literal instanceof Expr) {
			return (Expr) literal;
		}
		return literal(literal);
	}

	public static Expr num(Number literal) {
		return literal(literal);
	}

	public static Expr concat(Expr... exprs) {
		return multi(exprs, STRING_CONCAT);
	}

	public static Expr count(String column) {
		return unary(Expr.variable(column), COUNT);
	}

	public static Expr count(Expr expr) {
		return unary(expr, COUNT);
	}

	public static Expr max(String column) {
		return unary(Expr.variable(column), MAX);
	}

	public static Expr max(Expr expr) {
		return unary(expr, MAX);
	}

	public static Expr min(String column) {
		return unary(Expr.variable(column), MIN);
	}

	public static Expr min(Expr expr) {
		return unary(expr, MIN);
	}

	public static Expr collectValues(String column) {
		return unary(Expr.variable(column), COLLECT_VALUES);
	}

	public static Expr collectValues(Expr expr) {
		return unary(expr, COLLECT_VALUES);
	}

	public static Expr sum(String column) {
		return unary(Expr.variable(column), SUM);
	}

	public static Expr sum(Expr expr) {
		return unary(expr, SUM);
	}

	public static Expr avg(String column) {
		return unary(Expr.variable(column), AVG);
	}

	public static Expr avg(Expr expr) {
		return unary(expr, AVG);
	}

	public static Expr first(String column) {
		return unary(Expr.variable(column), FIRST);
	}

	public static Expr first(Expr expr) {
		return unary(expr, FIRST);
	}

	public static Expr last(String column) {
		return unary(Expr.variable(column), LAST);
	}

	public static Expr last(Expr expr) {
		return unary(expr, LAST);
	}

	public static Expr factorial(Expr expr) {
		return unary(expr, FACTORIAL);
	}

	public static Function<List<Expr>, Expr[]> toExprArray = p -> p.stream().map(Functions::lit).toArray(Expr[]::new);

	public static Expr not(Expr expr) {
		return unary(expr, NOT);
	}

	public static Expr rand() {
		return unary(null, RANDOM);
	}

	public static Expr radians(Expr expr) {
		return unary(expr, RADIANS);
	}

	public static Expr abs(Expr expr) {
		return unary(expr, ABS);
	}

	public static Expr acos(Expr expr) {
		return unary(expr, ACOS);
	}

	public static Expr asin(Expr expr) {
		return unary(expr, ASIN);
	}

	public static Expr atan(Expr expr) {
		return unary(expr, ATAN);
	}

	public static Expr atan2(Expr x, Expr y) {
		return binary(x, y, ATAN2);
	}

	public static Expr ceil(Expr expr) {
		return unary(expr, CEIL);
	}

	public static Expr collectList(Expr expr) {
		return unary(expr, COLLECT_LIST);
	}

	public static Expr cos(Expr expr) {
		return unary(expr, COS);
	}

	public static Expr cosh(Expr expr) {
		return unary(expr, COSH);
	}

	public static Expr currentDate() {
		return unary(null, CURRENT_TIMESTAMP);
	}

	public static Expr dateFormat(Expr expr, Expr dateFormat) {
		return unary(expr, DATE_FORMAT, dateFormat);
	}

	public static Expr degrees(Expr expr) {
		return unary(expr, DEGREES);
	}

	public static Expr floor(Expr expr) {
		return unary(expr, FLOOR);
	}

	public static Expr length(Expr expr) {
		return unary(expr, LENGTH);
	}

	public static Expr log(Expr expr) {
		return unary(expr, LOG);
	}

	public static Expr log10(Expr expr) {
		return unary(expr, LOG10);
	}

	public static Expr lower(Expr expr) {
		return unary(expr, LOWER);
	}

	public static Expr pow(Expr l, Expr r) {
		return unary(l, POW, r);
	}

	public static Expr regexpReplace(Expr expr, Expr pattern, Expr replacement) {
		return unary(expr, REGEX_REPLACE, Arrays.asList(pattern, replacement));
	}

	public static Expr reverse(Expr expr) {
		return unary(expr, REVERSE);
	}

	public static Expr sin(Expr expr) {
		return unary(expr, SIN);
	}

	public static Expr sinh(Expr expr) {
		return unary(expr, SINH);
	}

	public static Expr sqrt(Expr expr) {
		return unary(expr, SQRT);
	}

	public static Expr substring(Expr expr, Expr pos, Expr len) {
		return unary(expr, SUBSTRING, Arrays.asList(pos, len));
	}

	public static Expr tan(Expr expr) {
		return unary(expr, TAN);
	}

	public static Expr tanh(Expr expr) {
		return unary(expr, TANH);
	}

	public static Expr toDate(Expr expr, String dateFormat) {
		return unary(expr, TO_DATE, dateFormat);
	}

	public static Expr upper(Expr expr) {
		return unary(expr, UPPER);
	}

	public static Expr dayofweek(Expr expr) {
		return unary(expr, DAYOFWEEK);
	}

	public static Expr unixTimestamp(Expr expr) {
		return unary(expr, UNIX_TIMESTAMP);
	}

	public static Expr fromUnixtime(Expr expr) {
		return unary(expr, FROM_UNIXTIME);
	}

	public static Expr dayOfWeek(Expr expr) {
		return unary(expr, DAYOFWEEK);
	}

	public static Expr dayOfMonth(Expr expr) {
		return unary(expr, DAYOFMONTH);
	}

	public static Expr dayOfYear(Expr expr) {
		return unary(expr, DAYOFYEAR);
	}

	public static Expr hourOfDay(Expr expr) {
		return unary(expr, HOUROFDAY);
	}

	public static Expr milliSecond(Expr expr, String dateFormat) {
		return unary(expr, MILLISECOND, dateFormat);
	}

	public static Expr minute(Expr expr) {
		return unary(expr, MINUTE);
	}

	public static Expr second(Expr expr) {
		return unary(expr, SECOND);
	}

	public static Expr monthOfYear(Expr expr) {
		return unary(expr, MONTHOFYEAR);
	}

	public static Expr weekOfYear(Expr expr) {
		return unary(expr, WEEKOFYEAR);
	}

	public static Expr weekOfMonth(Expr expr, String dateFormat) {
		return unary(expr, WEEKOFMONTH, dateFormat);
	}

	public static Expr elapsedTime(Expr first, Expr second) {
		return binary(first, second, ELAPSEDTIME);
	}

	public static Expr dateAdd(Expr first, Integer value) {
		return unary(first, DATEADD, value);
	}

	public static Expr split(Expr operand, String pattern) {
		return unary(operand, SPLIT, pattern);
	}

	public static Expr trim(Expr operand) {
		return unary(operand, TRIM);
	}

	public static Expr arrayContains(Expr arrayOperand, Object value) {
		return unary(arrayOperand, ARRAYCONTAINS, value);
	}

	public static Expr size(Expr arrayOrMap) {
		return unary(arrayOrMap, SIZE);
	}

	public static Expr size(String arrayCol) {
		return unary(var(arrayCol), SIZE);
	}

	public static Expr arrayRemove(Expr array, Object toBeRemoved) {
		return unary(array, ARRAYREMOVE, toBeRemoved);
	}

	public static Expr arrayDistinct(Expr array) {
		return unary(array, ARRAYDISTINCT);
	}

	public static Expr addMonths(Expr first, Integer value) {
		return unary(first, ADDMONTHS, value);
	}

	public static Expr array(Expr... cols) {
		return multi(cols, COLUMN_ARRAY);
	}

	public static Expr arrayMax(Expr expr) {
		return unary(expr, ARRAYMAX);
	}

	public static Expr arrayMin(Expr expr) {
		return unary(expr, ARRAYMIN);
	}

	public static Expr sortArray(Expr expr) {
		return unary(expr, SORT_ARRAY);
	}
}