package com.example.repository;

import com.example.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private List<User> userList = new ArrayList<>();

    private SendUserIdRepository sendUserIdRepository;

    public List<User> getUserList() {
        return userList;
    }

    public UserRepository(File file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            this.userList = objectMapper.readValue(bufferedReader, new TypeReference<>() {});
        }

        this.sendUserIdRepository = new SendUserIdRepository();
        for(User user : this.userList){
            if(this.sendUserIdRepository.getUserIdList().contains(user.getId())){
                user.setSent(true);
            }
        }
    }

    public SendUserIdRepository getSendUserIdRepository() {
        return sendUserIdRepository;
    }

}
