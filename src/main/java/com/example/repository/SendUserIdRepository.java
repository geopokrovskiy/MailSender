package com.example.repository;

import com.example.util.Constants;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendUserIdRepository {

    private List<Integer> userIdList = new ArrayList<>();

    public SendUserIdRepository() throws IOException {
        String fileName = Constants.fileName;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            ObjectMapper objectMapper = new ObjectMapper();
            this.userIdList = objectMapper.readValue(bufferedReader, new TypeReference<List<Integer>>() {
            });
        }
    }

    public List<Integer> getUserIdList() {
        return userIdList;
    }
    public void saveToFile(List<Integer> list) throws IOException {
        String fileName = Constants.fileName;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(fileName), list);
    }

    public void setUserIdList(List<Integer> userIdList) {
        this.userIdList = userIdList;
    }
}
