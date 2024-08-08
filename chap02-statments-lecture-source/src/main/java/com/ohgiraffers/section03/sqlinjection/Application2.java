package com.ohgiraffers.section03.sqlinjection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application2 {

    private static String empId = "200";
    private static String empName = "'' OR 1=1 AND EMP_ID = '200";

    /* 설명.
    *   Statement와 달리 palceholder(?)를 통한 sql injection 공격을 막을 수 있게 작성되어 있어
    *   보안적 측면에서도 PreparedStatement가 우수
    * */
    public static void main(String[] args) {
        Connection con = getConnection();
        PreparedStatement pstmt = null;
        ResultSet rset = null;

        String query = "SELECT * FROM EMPLOYEE WHERE EMP_ID = ? AND EMP_NAME = ?";
        /* 설명. placeholder(?)에 (')가 들어가면 추가적으로 (')를 넣어 sql injection 공격을 막는다.*/
//        SELECT * FROM EMPLOYEE WHERE EMP_ID = '200' AND EMP_NAME = ''' OR 1=1 AND EMP_ID = ''200'

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, empId);
            pstmt.setString(2, empName);

            rset = pstmt.executeQuery();

            if(rset.next()) {
                System.out.println(rset.getString("emp_name") + "환영해요");
            }else{
                System.out.println("회원이 없다");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstmt);
            close(con);
        }

    }
}
