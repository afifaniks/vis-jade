package vis.constants;

import vis.entity.DBEntity;

import java.io.Serializable;

public class DBOperation implements Serializable {

	public enum OperationType {

		READ, WRITE, UPDATE, DELETE

	}

	String tableName;

	OperationType operationType;

	String query;

	DBEntity entity;

	public DBOperation(String tableName, OperationType operationType, DBEntity entity) {
		this.tableName = tableName;
		this.operationType = operationType;
		this.entity = entity;
	}

	public DBOperation(String tableName, OperationType operationType, String query) {
		this.tableName = tableName;
		this.operationType = operationType;
		this.query = query;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public DBEntity getEntity() {
		return entity;
	}

	public void setEntity(DBEntity entity) {
		this.entity = entity;
	}

}
