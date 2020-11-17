package io.finarkein.flux.expr;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static io.finarkein.flux.expr.Functions.toSqlDate;
import static io.finarkein.flux.expr.InstanceType.BINARY;
import static io.finarkein.flux.expr.InstanceType.LITERAL;
import static io.finarkein.flux.expr.InstanceType.MULTI;
import static io.finarkein.flux.expr.InstanceType.TERNARY;
import static io.finarkein.flux.expr.InstanceType.UNARY;
import static io.finarkein.flux.expr.InstanceType.VARIABLE;

/**
 *
 */
public class Expr {
	@Getter
	protected Expr[] expressions;
	@Getter
	protected InstanceType instanceType;
	@Getter
	protected ExprType type;
	@Getter
	@Setter(AccessLevel.PRIVATE)
	private Object parameters;

	protected Expr() {
	}

	private Expr(Expr[] expressions, ExprType type, InstanceType instanceType) {
		this.expressions = expressions;
		this.type = type;
		this.instanceType = instanceType;
	}

	public static Expr unary(Expr operand, ExprType type, Object parameters) {
		return new Expr(new Expr[]{operand}, type, UNARY).parameters(parameters);
	}

	public static Expr unary(Expr operand, ExprType type) {
		return unary(operand, type, null);
	}

	public static Expr binary(Expr first, Expr second, ExprType type) {
		return new Expr(new Expr[]{first, second}, type, BINARY);
	}

	public static Expr ternary(Expr first, Expr second, Expr third, ExprType type) {
		return new Expr(new Expr[]{first, second, third}, type, TERNARY);
	}

	public static Expr multi(Expr[] expressions, ExprType type) {
		return new Expr(expressions, type, MULTI);
	}

	public static Expr literal(Object literal) {
		return new Expr(null, null, LITERAL).parameters(literal);
	}

	public static Expr variable(String name) {
		return new Expr(null, null, VARIABLE).parameters(name);
	}

	public Expr operand() {
		return first();
	}

	public Expr[] cols() {
		return expressions;
	}

	public Expr first() {
		return expressions[0];
	}

	public Expr second() {
		return expressions[1];
	}

	public Expr third() {
		return expressions[2];
	}

	public Object literal() {
		return parameters;
	}

	public String name() {
		if (parameters == null)
			return null;
		if (parameters instanceof String)
			return (String) parameters;
		throw new IllegalStateException("name not instance of String");
	}

	public String toString() {
		switch (instanceType) {
			case LITERAL:
				return Objects.isNull(parameters) ? "NULL" : parameters.toString();
			case VARIABLE:
				return name();
		}
		return null;
	}

	protected static Expr toExpr(Object inputObj) {
		if (inputObj == null)
			return literal(null);
		if (isLiteralInstance(inputObj))
			return literal(inputObj);
		else if (inputObj instanceof Expr)
			return (Expr) inputObj;
		else if (inputObj instanceof java.util.Date)
			return literal(toSqlDate.apply((java.util.Date) inputObj));

		throw new IllegalArgumentException("Unresolved type, either the parameter should be a literal or Expr");
	}

	private static boolean isLiteralInstance(Object inputObj) {
		return inputObj instanceof Integer || inputObj instanceof Double || inputObj instanceof String
				|| inputObj instanceof Short || inputObj instanceof Float || inputObj instanceof Character
				|| inputObj instanceof Byte || inputObj instanceof java.sql.Date || inputObj instanceof BigDecimal
				|| inputObj instanceof Long || inputObj instanceof Boolean;
	}

	public Expr as(String name) {
		return unary(this, ExprType.ALIAS, toExpr(name));
	}

	public Expr ascNullsLast() {
		return unary(this, ExprType.SORT_ASC_NULLS_LAST);
	}

	public Expr and(Object other) {
		return binary(this, toExpr(other), ExprType.AND);
	}

	public Expr ascNullsFirst() {
		return unary(this, ExprType.SORT_ASC_NULLS_FIRST);
	}

	public Expr bitwiseOR(Object other) {
		return binary(this, toExpr(other), ExprType.BITWISE_OR);
	}

	public Expr between(Object lowerBound, Object upperBound) {
		List<Object> bounds = new ArrayList<>(2);
		bounds.add(!(lowerBound instanceof Expr) ? toExpr(lowerBound) : lowerBound);
		bounds.add(!(upperBound instanceof Expr) ? toExpr(upperBound) : upperBound);
		return unary(this, ExprType.BETWEEN, bounds);
	}

	public Expr bitwiseAND(Object other) {
		return binary(this, toExpr(other), ExprType.BITWISE_AND);
	}

	public Expr bitwiseXOR(Object other) {
		return binary(this, toExpr(other), ExprType.BITWISE_XOR);
	}

	public Expr asc() {
		return unary(this, ExprType.SORT_ASC);
	}

	public Expr descNullsFirst() {
		return unary(this, ExprType.SORT_DSC_NULLS_FIRST);
	}

	public Expr desc() {
		return unary(this, ExprType.SORT_DSC);
	}

	public Expr endsWith(Expr other) {
		return binary(this, other, ExprType.ENDS_WITH);
	}

	public Expr endsWith(String literal) {
		return unary(this, ExprType.ENDS_WITH, toExpr(literal));
	}

	public Expr equalTo(Object other) {
		return binary(this, toExpr(other), ExprType.EQUALS);
	}

	public Expr equalsNullSafe(Object other) {
		return binary(this, toExpr(other), ExprType.EQUALS_NULL_SAFE);
	}

	public Expr isin(List<Expr> list) {
		return unary(this, ExprType.IS_IN, list);
	}

	public Expr descNullsLast() {
		return unary(this, ExprType.SORT_DSC_NULLS_LAST);
	}

	public Expr gt(Object other) {
		return binary(this, toExpr(other), ExprType.GREATER_THAN);
	}

	public Expr geq(Object other) {
		return binary(this, toExpr(other), ExprType.GREATER_THAN_EQUAL_TO);
	}

	public Expr isin(Object... vals) {
		return unary(this, ExprType.IS_IN,
				Arrays.stream(vals).map(Expr::toExpr).collect(Collectors.toList()));
	}

	public Expr divide(Object other) {
		return binary(this, toExpr(other), ExprType.DIVIDE);
	}

	public Expr isNaN() {
		return unary(this, ExprType.IS_NAN);
	}

	public Expr isNotNull() {
		return unary(this, ExprType.IS_NOT_NULL);
	}

	public Expr mod(Object other) {
		return binary(this, toExpr(other), ExprType.MOD);
	}

	public Expr like(Expr literal) {
		return unary(this, ExprType.LIKE, toExpr(literal));
	}

	public Expr leq(Object other) {
		return binary(this, toExpr(other), ExprType.LESS_THAN_EQUAL_TO);
	}

	public Expr lt(Object other) {
		return binary(this, toExpr(other), ExprType.LESS_THAN);
	}

	public Expr minus(Object other) {
		return binary(this, toExpr(other), ExprType.MINUS);
	}

	public Expr isNull() {
		return unary(this, ExprType.IS_NULL);
	}

	public Expr multiply(Object other) {
		return binary(this, toExpr(other), ExprType.MULTIPLY);
	}

	public Expr plus(Object other) {
		return binary(this, toExpr(other), ExprType.PLUS);
	}

	public Expr pow(Object other) {
		return binary(this, toExpr(other), ExprType.POW);
	}

	public Expr notEqual(Object other) {
		return binary(this, toExpr(other), ExprType.NOT_EQUAL);
	}

	public Expr or(Expr other) {
		return binary(this, toExpr(other), ExprType.OR);
	}

	public Expr otherwise(Object value) {
		throw new IllegalArgumentException(
				"otherwise() can only be applied on a Expr previously generated by when()");
	}

	public Expr rlike(String literal) {
		return unary(this, ExprType.R_LIKE, toExpr(literal));
	}

	public Expr when(Expr condition, Object value) {
		throw new IllegalArgumentException(
				"when() can only be applied on a Expr previously generated by when() function");
	}

	public Expr startsWith(String literal) {
		return unary(this, ExprType.STARTS_WITH, toExpr(literal));
	}

	public Expr substr(Expr startPos, Expr len) {
		return ternary(this, startPos, len, ExprType.SUBSTRING);
	}

	public Expr startsWith(Expr other) {
		return binary(this, other, ExprType.STARTS_WITH);
	}

	public Expr unaryMinus() {
		return unary(this, ExprType.UNARY_MINUS, null);
	}

	public Expr cast(Expr expr) {
		return unary(this, ExprType.CAST, expr);
	}

	public Expr getItem(Object key) {
		return unary(this, ExprType.GET_ITEM, key);
	}

	public Expr getField(String fieldName) {
		return unary(this, ExprType.GET_ITEM, fieldName);
	}
}


