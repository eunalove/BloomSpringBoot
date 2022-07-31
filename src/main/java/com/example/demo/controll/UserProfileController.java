package com.example.demo.controll;
import com.example.demo.mapper.UserProfileMapper;
import com.example.demo.model.UserProfile;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
//이걸 달아야 스프링 프레임워크가 컨트롤러임을 인식할 수 있음

public class UserProfileController {

    private UserProfileMapper mapper;
    //컨트롤러에서 매퍼를 사용하기 위한 객체생성

    //private Map<String, UserProfile> userMap;
    //클래스 내부에 UserProfile을 담는 Map을 선언함
    //Map이란

    public UserProfileController(UserProfileMapper mapper){
        //생성자를 통해 mapper를 받음
        this.mapper = mapper;
    }
    /*
    @PostConstruct
    //스프링 프레임워크가 UserProfileController라는 인스턴스를 만들고
    //그 직후에 호출하게 되므로 초기화해야할 데이터들을 초기화할 수 있음
    public void init(){
        userMap = new HashMap<String, UserProfile>();
        userMap.put("1", new UserProfile("1", "홍길동", "111-1111", "서울시 강남구 대치1동"));
        userMap.put("2", new UserProfile("2", "홍길자", "222-2222", "서울시 강남구 대치2동"));
        userMap.put("3", new UserProfile("3", "홍길순", "333-3333", "서울시 강남구 대치3동"));

    }
    */

    @GetMapping("/user/{id}")
    //id를 인자로 받아서 해당 UserProfile의 정보를 JSON형태로 전달하는 API생성
    public UserProfile getUserProfile(@PathVariable("id") String id){
        //API는 UserProfile을 리턴하는 함수, 전달받은 파라메타 id를 사용함
        //id는 API의 {}안에 있는 변수를 이용할 것이므로 @PathVariable("id")를 지정함
        //그러면 {id}를 받아서 String id를 파라메타로 입력하여  getUserProfile API를 호출함

        //return userMap.get(id);
        //UserProfile객체를 리턴하면 JSON형태로 자동으로 매핑해서 클라이언트에게 전달함

        return mapper.getUserProfile(id);
        //mapper에 정의된 getUserProfile를 호출하고
        //{}로 전달된 id가 전달됨
        //그럼 Mapper에서 getUserProfile과 매핑된 sql문이 실행되서 해당 id를 갖는 UserProfile객체로 반환됨
        //이를 JASON형태(UserProfile)로 다시 전달함




    }

    @GetMapping("/user/all")
    public List<UserProfile> getUserProfileList(){

        //return new ArrayList<UserProfile>(userMap.values());
        //userMap이 가진 항목 중 3개의 values를 ArrayList로 변환시켜서 리턴함

        return new ArrayList<UserProfile>(mapper.getUserProfileList());
    }

    @PutMapping("/user/{id}")
    public void putUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("address") String address){
        //{}는 한가지나 두가지 정도의 정보를 전달할 때만 쓰고
        //일반적인 경우에는 파라메타를 전달할 땐 RequestParam을 이용하는 것이 일반적이다.

        UserProfile userProfile =new UserProfile(id,name,phone,address);
        //userMap.put(id, userProfile);
        mapper.insertUserProfile(id,name, phone, address);

    }

    @PostMapping("/user/{id}")
    //PUT이 생성이고 POST가 수정이지
    public void postUserProfile(@PathVariable("id") String id, @RequestParam("name") String name,@RequestParam("phone") String phone,@RequestParam("address") String address) {
       /* UserProfile userProfile = userMap.get(id);
        userProfile.setName(name);
        userProfile.setPhone(phone);
        userProfile.setAddress(address);*/
        mapper.updatetUserProfile(id,name,phone,address);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserProfile(@PathVariable("id") String id){
        //userMap.remove(id);
        mapper.deleteUserProfile(id);
    }


}