package com.example.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.vo.UserVo;

@Repository("userDao") // 변수는 소문자로 시작하기 때문에 소문자로 시작한다.
public class UserDaoImpl implements UserDao {

	@Autowired
	private DataSource dataSource;
	
	@Override
	public int create(UserVo user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int rowCount = -1;
		try {
			conn = this.dataSource.getConnection(); // 1,2
			conn.setAutoCommit(false);
			String sql = "INSERT INTO Users VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql); // 3
			pstmt.setString(1, user.getUserid());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getGender());
			pstmt.setString(4, user.getCity());
			rowCount = pstmt.executeUpdate(); // 4 sql문 실행
			conn.commit();
		}catch(Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();  // 실패하면 rollback
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}finally {
			try {
				if(conn != null) conn.close(); // 5. close
				if(pstmt != null) pstmt.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return rowCount;
	}

	@Override
	public UserVo read(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVo user = null;
		//1. JDBC 방법
		try {
			conn = this.dataSource.getConnection();  //1), 2)
			String sql ="SELECT * FROM Users WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);   //3)
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();                //4)
			rs.next();                                         //5)
			user = new UserVo(rs.getString("userid"),
					rs.getString("name"), rs.getString("gender"),
					rs.getString("city"));
		}catch(Exception ex) {
			ex.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();           //6)
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		return user;
	}

	@Override
	public List<UserVo> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(UserVo user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(String userid) {
		// TODO Auto-generated method stub
		return 0;
	}

}
