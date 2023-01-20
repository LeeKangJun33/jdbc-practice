package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

//이건 JdbcTemplate 라이브러리
public class JdbcTemplate {

    public void excuteUpdate(User user, String sql, PreparedStatementSetter pss) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);
            pstmt.executeUpdate();
        }finally {
            if (pstmt != null){
                pstmt.close();
            }
            if (con !=null){
                con.close();
            }
        }
    }

    public Object excuteQuery(String userId,String sql,PreparedStatementSetter pss,RomMapper romMapper) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt= null;
        ResultSet rs = null;

        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setter(pstmt);

            rs = pstmt.executeQuery();

            Object obj = null;
            if (rs.next()){
               return romMapper.map(rs);
            }

            return obj;
        }finally {
            if (rs != null){
                rs.close();
            }
            if (pstmt !=null){
                pstmt.close();
            }
            if (con !=null){
                con.close();
            }
        }
    }
}
