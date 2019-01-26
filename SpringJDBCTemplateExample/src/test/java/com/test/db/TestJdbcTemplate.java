package com.test.db;

import com.test.db.config.DbConfig;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DbConfig.class}, loader = AnnotationConfigContextLoader.class)
@ActiveProfiles("dev")
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TestJdbcTemplate {

	@Autowired
	DataSource dataSource;

	private static final String QUERY_COUNT = "SELECT COUNT(*) from SAMPLE_TABLE";
	private static final String QUERY_INSERT = "INSERT INTO SAMPLE_TABLE VALUES (?, ?)";
	private static final String QUERY_INSERT_NAMED = "INSERT INTO SAMPLE_TABLE VALUES (:id, :value)";
	private static final String QUERY_GET_ALL = "SELECT * from SAMPLE_TABLE";

	@Test
	public void TestRowCount() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		int count = template.queryForObject(QUERY_COUNT, Integer.class);
		assertEquals(3, count);
	}

	@Test
	public void TestRowInsert() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		template.update(QUERY_INSERT, 4, "Four");
		int count = template.queryForObject(QUERY_COUNT, Integer.class);
		assertEquals(4, count);
	}

	@Test
	public void TestRowInsertNamedQuery() {
		NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("id", 4);
		paramMap.put("value", "FOUR");
		template.update(QUERY_INSERT_NAMED, paramMap);
		int count = template.queryForObject(QUERY_COUNT, paramMap, Integer.class);
		assertEquals(4, count);
	}

	@Test
	public void TestRowMapper() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<String> list = template.query(QUERY_GET_ALL, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int i) throws SQLException {
				return rs.getString("ID") + ":" + rs.getString("NAME");
			}
		});
		System.out.println(list);
		assertEquals(3, list.size());
	}

	@Test
	public void TestRowMapperLambda() {
		JdbcTemplate template = new JdbcTemplate(dataSource);
		List<String> list = template.query(QUERY_GET_ALL, (ResultSet rs, int i) -> rs.getString("ID") + ":" + rs.getString("NAME"));
		System.out.println(list);
		assertEquals(3, list.size());
	}
}
