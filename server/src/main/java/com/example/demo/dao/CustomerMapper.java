package com.example.demo.dao;

import com.example.demo.domain.CustomerDTO;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface CustomerMapper {

	@Insert("insert into customer (customer_id, customer_password, "
								+ "customer_name, nickname, email, image) "
								+ "values (#{customerId}, #{customerPassword}, #{customerName}, #{nickname}, #{email}, #{image})")
	public boolean createCustomer(CustomerDTO dto);

	@Select("select customer_id, customer_password, customer_name, nickname, email, image from customer")
	public List<CustomerDTO> readCustomer();

	@Update("update customer "
		+ "set customer_id = #{customerId}, customer_password = #{customerPassword},"
		+ "customer_name = #{customerName}, nickname = #{nickname}, email = #{email}, image = #{image}"
		+ "where customer_id = #{customerId}")
	public boolean updateCustomer(CustomerDTO dto);

	@Delete("delete from customer where customer_id = #{customerId}")
	public boolean deleteCustomer(int customerId);

	@Select("select customer_id, customer_password, customer_name, nickname, email, image from customer where ${cn} = #{v}")
	public List<CustomerDTO> readCustomerBy(@Param("cn") String columnName, @Param("v") String value);
}
