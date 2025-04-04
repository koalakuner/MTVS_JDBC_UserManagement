package com.ohgiraffers.jdbc.model;

import java.time.LocalDateTime;

/* 모델 만드는 이유
 사용자에 댜한 속성 정의
 주요 기능 구현 */

public class User { // User 클래스 정의
    // 필드 선언: 사용자 정보를 저장하기 위한 변수들
    private int userId;
    private String userName;
    private String email;
    private String password;
    private int roleId;
    private LocalDateTime createdAt; // 생성일: 사용자가 생성된 날짜와 시간을 정의하는 LocalDateTime 변수

    // 생성자: User 객체 생성할 때 매개변수 받아서 필드 초기화
public User(int userId, String userName, String email, String password, int roleId) {
    this.userId = userId;
    this.userName = userName;
    this.email = email;
    this.password = password;
    this.roleId = roleId;
    this.createdAt = LocalDateTime.now();
}
// User getter
    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRoleId() {
        return roleId;
    }

    public LocalDateTime getCreateTime() {
        return createdAt;
    }

    // User setter
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createdAt = createdAt;
    }

    @Override // 오버라이드; 객체 지향 프로그래밍에서 상속받은 메서드를 자식 클래스에서 재정의하는 것을 의미.
    // 부모 클래스에서 정의된 메서드를 구현을 자식 클래스에서 새롭게 작성.
    //자식 클래스에서 객체가 해당 메서드 호출할 때 부모 클래스의 구현이 아닌 자식 클래스 구현이 실행되도록 할 때
    public String toString() {
    return "User{" + "userId=" + userId +
            ", userName='" + userName + '\'' +
                        ", email='" + email + '\'' +
                        ", password='" + password + '\'' +
                        ", roleId=" + roleId +
                        ", createdAt=" + createdAt +
                        '}';

    }
}
