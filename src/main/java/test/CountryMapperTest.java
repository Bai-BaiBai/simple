package test;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import tk.mybatis.simple.mapper.CountryMapper;
import tk.mybatis.simple.model.Country;

public class CountryMapperTest extends BaseMapperTest {

	@Test
	public void testSelectAll() {
		SqlSession sqlSession = getSqlSession();
		CountryMapper countryMapper = sqlSession.getMapper(CountryMapper.class);
		ArrayList<Country> countries = countryMapper.selectAll();
		printList(countries);
	}

	private void printList(ArrayList<Country> countries) {
		// TODO Auto-generated method stub
		for(Country c : countries) {
			System.out.println(c.getId() + c.getCountryname() + c.getCountrycode());
		}
	}
}
