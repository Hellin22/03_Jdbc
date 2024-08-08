package com.ohgiraffers.section01.statement;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Application2 {

    public static void main(String[] args) {
        Connection con = null;

        Statement stmt = null;
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);
        System.out.println("사번 입력: ");
        int empId = sc.nextInt();

        /* 설명. 입력받은 사번을 활용한 쿼리 작성 */
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = '"+ empId + "'";
        System.out.println("query = " + query);

        try {
            rset = stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            if(rset.next()){
                try {
                    System.out.println(rset.getString("EMP_ID") + ", "
                    + rset.getString("EMP_NAME"));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }else{

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
