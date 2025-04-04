package com.ohgiraffers.jdbc.service;


import com.ohgiraffers.jdbc.dao.UserDao;
import com.ohgiraffers.jdbc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserService {
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private UserDao userDao;
    private final Connection connection;


    /* 생성자 주임 */
    public UserService(Connection connection) {
        // 객체 생성자 정의
        this.connection = connection;
        // 외부에서 전달받은 Connection 객체를 멤버 변수에 할당
        // 이유: 데이터베이스 연결을 재사용하고 관리하기 위함
        this.userDao = new UserDao(connection);
    }

    //userDao 객체 생성하고 변수에 할당


    // [사용자 등록 (CREATE] //
    public boolean registerUser(User user) throws SQLException {
        // 사용자 등록 메서드
        // 중복 이메일 검사
        List<User> existingUsers = getAllUsers();
        // 모든 사용자 정보를 가져옴 (이메일 중복 검사를 위해)
        for (User u : existingUsers) {
            // 모든 사용자 정보 순회
            if (u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword())) {
                // 이미 존재하는 이메일인 경우
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
                // illegalArgumentException 예외 발생
            }
        }
        return userDao.addUser(user);
    }

    // [사용자 전체 조회 (READ)] //
    public List<User> getAllUsers() throws SQLException {
        List<User> users = userDao.getAllUsers();

        if (users == null) {
            log.error("조회한 사용자의 정보가 없거나 DB와 연결하는 과정에서 오류가 발생했습니다.");
            return null;
        }
        return users;
    }


    // [단일 사용자 조회] //
    public User getUserById(int userId) throws SQLException {
        User user = userDao.getUserById(userId);

        if (user == null) {
            throw new IllegalArgumentException("해당 ID의 사용자를 찾을 수 없습니다.");
        }
        return user;
    }

    // [사용자 정보 수정 (UPDATE)] //

    public boolean updateUser(User user) throws SQLException {
        //1. 기존 사용자 존재 확인 TRUE/FALSE
        User existingUser = getUserById(user.getUserId());
        if (existingUser == null) {
            throw new IllegalArgumentException("수정할 사용자를 찾을 수 없습니다.");
        }
        //2. 이메일 존재 확인
        List<User> existingUsers = getAllUsers();
        for (User u : existingUsers) {
            if (u.getEmail().equals(user.getEmail())) {
                throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
            }
        }
        boolean result = userDao.updateUser(user);
        if (!result) {
            throw new SQLException("수정하는 과정에서 오류가 발생되었습니다.");
        }
        //3, 업데이트 수행
        return result;
    }

    // [사용자 삭제 (DELETE)] //
    public boolean deleteUser(int userId) throws SQLException {
            User existingUser = getUserById(userId);
            if (existingUser == null) {
                throw new IllegalArgumentException("삭제할 사용자를 찾을 수 없습니다.");
            }
            return userDao.deleteUser(userId);
        }
    }



