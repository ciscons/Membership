package com.example.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVo;

@Repository("userDao1") // 변수는 소문자로 시작하기 때문에 소문자로 시작한다.
public class UserDaoJdbcTemplateImpl implements UserDao {

	@Autowired
//	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int create(UserVo user) {	
		String sql = "INSERT INTO Users VALUES(?,?,?,?)";
		return this.jdbcTemplate.update(sql, user.getUserid(), user.getName(), user.getGender(), user.getCity());
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		int rowCount = -1;
//		try {
//			conn = this.dataSource.getConnection(); // 1,2
//			conn.setAutoCommit(false);
//			String sql = "INSERT INTO Users VALUES(?,?,?,?)";
//			pstmt = conn.prepareStatement(sql); // 3
//			pstmt.setString(1, user.getUserid());
//			pstmt.setString(2, user.getName());
//			pstmt.setString(3, user.getGender());
//			pstmt.setString(4, user.getCity());
//			rowCount = pstmt.executeUpdate(); // 4 sql문 실행
//			conn.commit();
//		}catch(Exception e) {
//			e.printStackTrace();
//			try {
//				conn.rollback();  // 실패하면 rollback
//			} catch (SQLException ex) {
//				ex.printStackTrace();
//			}
//		}finally {
//			try {
//				if(conn != null) conn.close(); // 5. close
//				if(pstmt != null) pstmt.close();
//			} catch (Exception ex) {
//				ex.printStackTrace();
//			}
//		}
//		return rowCount;
	}

	@Override
	public UserVo read(String userid) {
		String sql = "SELECT * FROM Users WHERE userid = ?";
		UserVo user = this.jdbcTemplate.queryForObject(sql, new MyMapper(), userid);
		return user;
	}
		private class MyMapper implements RowMapper<UserVo>{
			@Override
			public UserVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				UserVo user = new UserVo();
				user.setUserid("userid");
				user.setName(rs.getString("name"));
				user.setGender(rs.getString("gender"));
				user.setCity(rs.getString("city"));
				return user;
			}
			
		}
		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		UserVo user = null;
//		//1. JDBC 방법
//		try {
//			conn = this.dataSource.getConnection();  //1), 2)
//			String sql ="SELECT * FROM Users WHERE userid = ?";
//			pstmt = conn.prepareStatement(sql);   //3)
//			pstmt.setString(1, userid);
//			rs = pstmt.executeQuery();                //4)
//			rs.next();                                         //5)
//			user = new UserVo(rs.getString("userid"),
//					rs.getString("name"), rs.getString("gender"),
//					rs.getString("city"));
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}finally {
//			try {
//				if(rs != null) rs.close();
//				if(pstmt != null) pstmt.close();
//				if(conn != null) conn.close();           //6)
//			}catch(Exception ex) {
//				ex.printStackTrace();
//			}
//		}
		
	@Override
	public List<UserVo> readAll() {
		return this.jdbcTemplate.query("SELECT * FROM Users ORDER BY userid DESC",	new MyMapper());
	}

	@Override
	public int update(UserVo user) {
		String sql = "UPDATE Users SET name = ?, city = ? WHERE userid = ?";
		return this.jdbcTemplate.update(sql, user.getName(), user.getCity(), user.getUserid());
	}

	@Override
	public int delete(String userid) {
		return this.jdbcTemplate.update("DELETE FROM Users WHERE userid = ?", userid);
	}

}
