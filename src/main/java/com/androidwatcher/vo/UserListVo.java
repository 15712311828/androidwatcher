package com.androidwatcher.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserListVo {

    Integer total;

    List<User> users;

    @Data
    public static class User{

        private Integer id;

        private String name;

        public static User fromUser(com.androidwatcher.model.User user){
            User userVo=new User();
            userVo.setId(user.getId());
            userVo.setName(user.getName());
            return userVo;
        }
    }
}
