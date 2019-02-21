package tk.mybatis.simple.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class EnabledTypeHandler implements TypeHandler<Enabled> {
	
	private final Map<Integer, Enabled> enabledMap = new HashMap<Integer, Enabled>();
	
	public EnabledTypeHandler() {
		// TODO Auto-generated constructor stub
		//这里将Enabled枚举类中的枚举类型放入Map中，以它们的枚举值作为key
		for(Enabled enabled : Enabled.values()) {
			enabledMap.put(enabled.getValue(), enabled);
		}
	}

	/*
	 * 定义当前数据如何保存到数据库中
	 * @see org.apache.ibatis.type.TypeHandler#setParameter(java.sql.PreparedStatement, int, java.lang.Object, org.apache.ibatis.type.JdbcType)
	 */
	@Override
	public void setParameter(PreparedStatement ps, int i, Enabled parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		ps.setInt(i, parameter.getValue());
	}

	/*
	 * 从数据库中获取value
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.ResultSet, java.lang.String)
	 */
	@Override
	public Enabled getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		Integer value = rs.getInt(columnName);
		return enabledMap.get(value);
	}

	@Override
	public Enabled getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		Integer value = rs.getInt(columnIndex);
		return enabledMap.get(value);
	}

	/*
	 * 处理存储过程结果集
	 * @see org.apache.ibatis.type.TypeHandler#getResult(java.sql.CallableStatement, int)
	 */
	@Override
	public Enabled getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		Integer value = cs.getInt(columnIndex);
		return enabledMap.get(value);
	}

}
