package tk.mybatis.simple.mapper;

import java.util.ArrayList;

import tk.mybatis.simple.model.Country;

public interface CountryMapper {

	ArrayList<Country> selectAll();
}
