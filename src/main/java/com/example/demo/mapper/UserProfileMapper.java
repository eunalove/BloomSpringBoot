package com.example.demo.mapper;
import com.example.demo.model.UserProfile;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
//스프링부트가 Mapper라고 인식
public interface UserProfileMapper {

    @Select("Select * FROM UserProfile WHERE id= #{id}")
    //API를 매핑할 SQL문 작성
    //이렇게 매핑하면 아래에서 파라메타로 전달된 id가 위의 sql문으로 전달이 됨
    //이때 id를 연결하기 위해 @Param을 사용함
    UserProfile getUserProfile(@Param("id") String id);
    //전달된 id를 가지고 데이터베이스 테이블에서 조회해서 UserProfile객체를 리턴하는 API임

    @Select("SELECT * FROM UserProfile")
    List<UserProfile> getUserProfileList();

    @Insert("INSERT INTO UserProfile VALUES(#{id}, #{name}, #{phone}, #{address})")
    int insertUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
    //영향을 받은 레코드의 수가 반환됨

    @Update("UPDATE UserProfile SET name=#{name}, phone=#{phone}, address= #{address} WHERE id = #{id}")
    int updateUserProfile(@Param("id") String id, @Param("name") String name, @Param("phone") String phone, @Param("address") String address);
    //영향을 받은 레코드의 수가 반환됨

    @Delete("DELETE FROM UserProfile WHERE id=#{id}")
    int deleteUserProfile(@Param("id") String id);
    //영향을 받은 레코드의 수가 반환됨

}
