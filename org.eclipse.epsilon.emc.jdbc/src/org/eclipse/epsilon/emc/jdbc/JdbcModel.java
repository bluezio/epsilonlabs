package org.eclipse.epsilon.emc.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.epsilon.common.parse.AST;
import org.eclipse.epsilon.eol.exceptions.EolInternalException;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;
import org.eclipse.epsilon.eol.exceptions.models.EolEnumerationValueNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelElementTypeNotFoundException;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.execute.context.IEolContext;
import org.eclipse.epsilon.eol.execute.context.Variable;
import org.eclipse.epsilon.eol.execute.introspection.IPropertyGetter;
import org.eclipse.epsilon.eol.models.ISearchableModel;
import org.eclipse.epsilon.eol.parse.EolParser;

public abstract class JdbcModel extends ImmutableModel implements ISearchableModel {
	
	protected String server;
	protected int port;
	protected String database;
	protected String username;
	protected String password;
	protected Connection connection;
	protected List<String> tables = new ArrayList<String>();
	protected ResultSetPropertyGetter propertyGetter = new ResultSetPropertyGetter();
	
	protected abstract String getDriver();
	
	protected abstract String getJdbcUrl();
	
	public void print(ResultSet rs) throws Exception {
		while (rs.next()) {
			System.err.println(rs.getString(3));
		}
	}
	
	@Override
	public void load() throws EolModelLoadingException {
		try {
		Class.forName(getDriver());
	        connection = DriverManager.getConnection(getJdbcUrl() , username, password);
	        
	        // Cache table names
	        ResultSet rs = connection.getMetaData().getTables(null, null, null, new String[]{});
			while (rs.next()) {
				tables.add(rs.getString(3));
			}
			
		}
		catch (Exception ex) {
			throw new EolModelLoadingException(ex, this);
		}
	}
	
	@Override
	public boolean hasType(String type) {
		return tables.contains(type);
	}
	
	@Override
	public Collection<?> getAllOfType(String type)
			throws EolModelElementTypeNotFoundException {
		try {
			PreparedStatement statement = connection.prepareStatement("select * from " + type);
			return new ResultSetCollection(statement.executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	@Override
	public Object getEnumerationValue(String enumeration, String label)
			throws EolEnumerationValueNotFoundException {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<?> allContents() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isOfType(Object instance, String metaClass)
			throws EolModelElementTypeNotFoundException {
		return metaClass.equals(getTypeNameOf(instance));
	}
	
	@Override
	public boolean isOfKind(Object instance, String metaClass)
			throws EolModelElementTypeNotFoundException {
		return isOfType(instance, metaClass);
	}
	
	@Override
	public String getTypeNameOf(Object instance) {
		if (instance instanceof ResultSet) {
			ResultSet rs = (ResultSet) instance;
			try {
				if (rs.getMetaData().getColumnCount() > 0) {
					return rs.getMetaData().getTableName(1);
				}
			}
			catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}
	
	@Override
	public Collection<?> find(Variable iterator, AST ast, IEolContext context)
			throws EolRuntimeException {
		
		String sql = "select * from " + iterator.getType().getName() + 
				" where " + ast2sql(iterator, ast, context);
		
		System.err.println(sql);
		
		try {
			return new ResultSetCollection(connection.prepareStatement(sql).executeQuery());
		} catch (SQLException e) {
			throw new EolInternalException(e);
		}
	}
	
	public String ast2sql(Variable iterator, AST ast, IEolContext context) throws EolRuntimeException {
		if (ast.getType() == EolParser.OPERATOR) {
			return "(" + ast2sql(iterator, ast.getFirstChild(), context)
					+ ast.getText() + 
					ast2sql(iterator, ast.getFirstChild().getNextSibling(), context) + ")";
		}
		else if (ast.getType() == EolParser.POINT && ast.getFirstChild().getText().equals(iterator.getName())) {
			return ast.getFirstChild().getNextSibling().getText();
		}
		else {
			Object result = context.getExecutorFactory().executeAST(ast, context);
			if (result instanceof String) {
				return "\"" + result + "\"";
			}
			else {
				return result + "";
			}
		}
	}
	
	@Override
	public Object getElementById(String id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String getElementId(Object instance) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public IPropertyGetter getPropertyGetter() {
		return propertyGetter;
	}
	
	@Override
	public Collection<?> getAllOfKind(String type)
			throws EolModelElementTypeNotFoundException {
		return getAllOfType(type);
	}
	
	@Override
	public boolean owns(Object instance) {
		return instance instanceof ResultSet;
	}
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getDatabase() {
		return database;
	}

	public void setDatabase(String database) {
		this.database = database;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
