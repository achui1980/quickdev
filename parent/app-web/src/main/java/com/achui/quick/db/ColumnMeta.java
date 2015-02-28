package com.achui.quick.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.StringTokenizer;


public class ColumnMeta{
	
	private final String name;
	private final String typeName;
	private final int columnSize;
	private final int decimalDigits;
	private final String isNullable;
	private final int typeCode;
	private final String comment;
	private final String tableName;
	private boolean display;
	private boolean searchable;
	private String op;
	
	public ColumnMeta(ResultSet rs) throws SQLException {
		name = rs.getString("COLUMN_NAME");
		columnSize = rs.getInt("COLUMN_SIZE");
		decimalDigits = rs.getInt("DECIMAL_DIGITS");
		isNullable = rs.getInt("NULLABLE") == 1?"Y":"N";
		typeCode = rs.getInt("DATA_TYPE");
		comment = rs.getString("REMARKS");
		tableName = rs.getString("TABLE_NAME");
		typeName = new StringTokenizer( rs.getString("TYPE_NAME"), "() " ).nextToken();
	}
	
	public String getName() {
		return name;
	}

	public String getTypeName() {
		return typeName;
	}

	public int getColumnSize() {
		return columnSize;
	}

	public int getDecimalDigits() {
		return decimalDigits;
	}

	public String getIsNullable() {
		return isNullable;
	}

	public int getTypeCode() {
		return typeCode;
	}

	public String getComment() {
		return comment;
	}

	public String getTableName() {
		return tableName;
	}

	public boolean isDisplay() {
		return display;
	}

	public void setDisplay(boolean display) {
		this.display = display;
	}

	public boolean isSearchable() {
		return searchable;
	}

	public void setSearchable(boolean searchable) {
		this.searchable = searchable;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}
}
