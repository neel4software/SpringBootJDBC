package com.tb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.tb.model.Student;

@Repository
public class DataDaoImpl implements DataDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int add(final Student student) {
		String query = "INSERT INTO STUDENT(FIRST_NAME,LAST_NAME) VALUES(?,?)";

		KeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, student.getFirstName());
				ps.setString(2, student.getLastName());

				return ps;
			}
		}, holder);

		int newUserId = holder.getKey().intValue();
		return newUserId;
	}

	@Override
	public int update(Student student) {
		String query = "UPDATE STUDENT SET FIRST_NAME = ?, LAST_NAME = ? WHERE ID = ?";
		int row = jdbcTemplate.update(query, student.getFirstName(), student.getLastName(), student.getId());

		return row;
	}

	@Override
	public Student get(int id) {
		String query = "SELECT * FROM STUDENT WHERE ID = ?";
		return jdbcTemplate.queryForObject(query, new Object[] { id }, new StudentMapper());
	}

	@Override
	public List<Student> list() {
		String query = "SELECT * FROM STUDENT";
		return jdbcTemplate.query(query, new StudentMapper());

	}

	class StudentMapper implements RowMapper<Student> {

		@Override
		public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
			Student student = new Student();
			student.setId(rs.getInt("ID"));
			student.setFirstName(rs.getString("FIRST_NAME"));
			student.setLastName(rs.getString("LAST_NAME"));

			return student;
		}

	}

	@Override
	public int delete(int id) {
		String query = "DELETE FROM STUDENT WHERE ID = ?";
		int row = jdbcTemplate.update(query, id);

		return row;
	}

}
