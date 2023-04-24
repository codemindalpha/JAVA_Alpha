package testcases.C0001_SQLI__CWE89_SQL_Injection.s01;
/*

https://www.tutorialspoint.com/mybatis/mybatis_delete_operation.htm
by juho
*/

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface C0001_CWE89_SQL_Injection__MyBatis_mapper_01 {
   //bad
   //FLAW:
   final String getById = "SELECT * FROM STUDENT WHERE ID = ${id}";
   //FLAW:
   final String deleteById = "DELETE from STUDENT WHERE ID = ${id}";
   //FLAW:
   final String insert = "INSERT INTO STUDENT (NAME, BRANCH, PERCENTAGE, PHONE, EMAIL ) VALUES (${name}, ${branch}, ${percentage}, ${phone}, ${email})";
   //FLAW:
   final String update = "UPDATE STUDENT SET EMAIL = ${email}, NAME = ${name}, BRANCH = ${branch}, PERCENTAGE = ${percentage}, PHONE = ${phone} WHERE ID = ${id}";

   //good
   final String getAll = "SELECT * FROM STUDENT";
   final String getById2 = "SELECT * FROM STUDENT WHERE ID = #{id}";
   final String deleteById2 = "DELETE from STUDENT WHERE ID = #{id}";
   final String insert2 = "INSERT INTO STUDENT (NAME, BRANCH, PERCENTAGE, PHONE, EMAIL ) VALUES (#{name}, #{branch}, #{percentage}, #{phone}, #{email})";
   final String update2 = "UPDATE STUDENT SET EMAIL = #{email}, NAME = #{name}, BRANCH = #{branch}, PERCENTAGE = #{percentage}, PHONE = #{phone} WHERE ID = #{id}";

   @Select(getAll)
   @Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
         @Result(property = "branch", column = "BRANCH"), @Result(property = "percentage", column = "PERCENTAGE"),
         @Result(property = "phone", column = "PHONE"), @Result(property = "email", column = "EMAIL") })

   List getAll();

   @Select(getById)
   @Results(value = { @Result(property = "id", column = "ID"), @Result(property = "name", column = "NAME"),
         @Result(property = "branch", column = "BRANCH"), @Result(property = "percentage", column = "PERCENTAGE"),
         @Result(property = "phone", column = "PHONE"), @Result(property = "email", column = "EMAIL") })

   Student getById(int id);

   @Update(update)
   void update(Student student);

   @Delete(deleteById)
   void delete(int id);

   @Insert(insert)
   @Options(useGeneratedKeys = true, keyProperty = "id")
   void insert(Student student);

   //good annotations
   @Select(getById2)
   Student getById2(int id);
   @Update(update2)
   void update2(Student student);
   @Delete(deleteById2)
   void delete2(int id);
   @Insert(insert2)
   void insert2(Student student);

   class Student {
   }
}
