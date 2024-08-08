package com.ohgiraffers.section02.prepared;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;
import static com.ohgiraffers.common.JDBCTemplate.close;

/* 설명.
*   PreparedStatement는 Statement와 달리 최초 한번 쿼리를 파싱하고 컴파일 캐싱하여
*   다시 재해석 하는 과정(비용)을 생략함으로써 불완전하게 작성된 쿼리 실행의 경우 Statement보다 빠르다.
* */

public class Application2 {
    public static void main(String[] args) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;
        ResultSet rset = null;

        Scanner sc = new Scanner(System.in);

        System.out.print("사번 입력: ");
        String empId = sc.nextLine();

        String entYn = "N";

        /* 설명. PreparedStatement는 Statement와 달리 palceholder(?)를 활용한 하나의 문자열 형태로 쿼리를 작성 */
        String query = "SELECT EMP_ID, EMP_NAME FROM EMPLOYEE WHERE EMP_ID = ? AND ENT_YN = ?";
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId); // 이부분이 추가됏네 -> 삭제돼도 컴파일 오류는 안남
            pstmt.setString(2, entYn);
            rset = pstmt.executeQuery();

            if(rset.next()) {
                System.out.println(rset.getString("EMP_ID")
                + ", " + rset.getString("EMP_NAME"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally{
            close(rset);
            close(pstmt);
            close(con);
        }
    }
}
