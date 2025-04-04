package com.ohgiraffers.jdbc.dao;

import com.ohgiraffers.jdbc.model.User;
import com.ohgiraffers.jdbc.util.QueryUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private final Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;


    }

    // [ 사용자 추가 (CREATE) ] //
    public boolean addUser(User user) {
        // addUser 메소드 - 사용자 추가 기능 수행. 매개변수 User 객체를 전달받아 사용자 정보를 사용

        String query = QueryUtil.getQuery("addUser");
        // query는 SQL 쿼리문을 저장하는 변수
        // QueryUtil 클래스
        // QueryUtil 클래스에서 "addUser"라는 ID를 가진 쿼리문을 가져오는 메소드

        try (PreparedStatement ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            // SQL 쿼리문 준비하고, 자동으로 생성된 키 값을 반환하도록 설정
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());


            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    // [ 모든 사용자 조회 (READ) ] //
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String query = QueryUtil.getQuery("getAllUsers");

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getInt("role_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // [단일 사용자 조회 (READ)] // 사용자 id로 조회
    public User getUserById(int userId) {
        String query = QueryUtil.getQuery("getUserById");
        User user = null;

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password_hash"),
                        rs.getInt("role_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    // [사용자 수정 [UPDATE] //
    public boolean updateUser(User user) { // User타입의 user 파라미터 받으면 updateUser 메서드 true/ false 반환
        String query = QueryUtil.getQuery("updateUser"); // updateUser query 선언
        try (PreparedStatement ps = connection.prepareStatement(query)) { //
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getRoleId());
            ps.setInt(5, user.getUserId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // [사용자 삭제 [DELETE] //
    public boolean deleteUser(int userId) {
        String query = QueryUtil.getQuery("deleteUser");

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}



