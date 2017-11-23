package com.atguigu.common.mapper.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.atguigu.common.mapper.entities.Employee;
import com.atguigu.common.mapper.mappers.EmployeeMapper;
import com.github.abel533.entity.Example;
import com.github.abel533.entity.Example.Criteria;

public class EmployeeMapperTest {

	private SqlSession session;

	@Before
	public void init() {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		SqlSessionFactory factory = builder.build(this.getClass()
				.getClassLoader().getResourceAsStream("mybatis-config.xml"));
		session = factory.openSession();
	}

	@After
	public void commit() {
		session.commit();
		session.close();
	}

	@Test
	public void testSelect() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(null, "James", 25, null);

		List<Employee> list = mapper.select(employee);
		for (Employee employee2 : list) {
			System.out.println(employee2);
		}
	}

	@Test
	public void testSelectOne() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(null, "James", null, null);

		Employee selectOne = mapper.selectOne(employee);
		System.out.println(selectOne);

	}

	@Test
	public void testSelectCount() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(null, "James", null, null);
		int selectCount = mapper.selectCount(employee);
		System.out.println(selectCount);
	}

	@Test
	public void testSelectByPrimaryKey() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = mapper.selectByPrimaryKey(4);
		System.out.println(employee);

	}

	@Test
	public void testInsert() {
		// 1.创建要保存的实体类对象
		Employee employee = new Employee(null, "empName03", 30, 3000.0);
		// 2.执行保存
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		mapper.insert(employee);

		// 3.获取自增的主键值
		Integer empId = employee.getEmpId();
		System.out.println(empId);

	}

	@Test
	public void testInsertSelective() {
		// 1.创建要保存的实体类对象
		Employee employee = new Employee(null, "empName04", null, 3000.0);
		// 2.执行保存
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		mapper.insertSelective(employee);

		// 3.获取自增的主键值
		Integer empId = employee.getEmpId();
		System.out.println(empId);
	}

	@Test
	public void testDelete() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(null, "empName04", null, 3000.0);
		int i = mapper.delete(employee);
		System.out.println(i);
	}

	@Test
	public void testDeleteByPrimaryKey() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		mapper.deleteByPrimaryKey(13);
	}

	@Test
	public void testUpdateByPrimaryKey() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(5, "newName",23 , 30000.0);
		
		mapper.updateByPrimaryKey(employee);
	}

	@Test
	public void testUpdateByPrimaryKeySelective() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Employee employee = new Employee(5, "newName1",null , null);
		
		mapper.updateByPrimaryKey(employee);
	}

	@Test
	public void testSelectCountByExample() {

	}

	@Test
	public void testDeleteByExample() {

	}

	@Test
	public void testSelectByExample() {
		EmployeeMapper mapper = session.getMapper(EmployeeMapper.class);
		Example example = new Example(Employee.class);
		example.createCriteria().andGreaterThan("empAge", 50).andBetween("empSalary", 1000, 5000);
		Example example2 = new Example(Employee.class);
		example2.createCriteria().andLike("empName", "%a%").andGreaterThan("empSalary", 4000);
		Criteria criteria = example2.createCriteria().andLike("empName", "%e%").andGreaterThan("empSalary", 3000);
		example2.or(criteria);
		
		List<Employee> list = mapper.selectByExample(example2);
		for (Employee employee : list) {
			System.out.println(employee);
		}
	}

	@Test
	public void testUpdateByExampleSelective() {

	}

	@Test
	public void testUpdateByExample() {

	}

}
