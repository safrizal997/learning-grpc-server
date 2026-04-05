package com.myproject.learning.grpc.server.model;

public class UserDetail {
    private String userId;
    private String name;
    private String email;
    private int age;
    private String phone;

    public UserDetail(String userId, String name, String email, int age, String phone) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
    public String getPhone() { return phone; }
}
