package cache;

import redis.clients.jedis.Jedis;
import tr.common.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BootStrap {
    public static void main(String[] args) throws SQLException {

        // 读取Mysql中的数据
        Map<String, Integer> userMap = new HashMap<String, Integer>();
        Map<String, Integer> dateMap = new HashMap<String, Integer>();

        // 读取用户，时间数据

        Connection connection = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;

        try {

            connection = JDBCUtil.getConnection();

            String queryUserSql = "select id, tel from ct_user";
            pstat = connection.prepareStatement(queryUserSql);
            rs = pstat.executeQuery();
            while ( rs.next() ) {
                Integer id = rs.getInt(1);
                String tel = rs.getString(2);
                userMap.put(tel, id);
            }

            rs.close();

            String queryDateSql = "select id, year, month, day from ct_date";
            pstat = connection.prepareStatement(queryDateSql);
            rs = pstat.executeQuery();
            while ( rs.next() ) {
                Integer id = rs.getInt(1);
                String year = rs.getString(2);
                String month = rs.getString(3);
                if ( month.length() == 1 ) {
                    month = "0" + month;
                }
                String day = rs.getString(4);
                if ( day.length() == 1 ) {
                    day = "0" + day;
                }
                dateMap.put(year + month + day, id);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if ( rs != null ) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( pstat != null ) {
                try {
                    pstat.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if ( connection != null ) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        // 向redis中存储数据
        Jedis jedis = new Jedis("master", 6379);

        Iterator<String> keyIterator = userMap.keySet().iterator();
        while ( keyIterator.hasNext() ) {
            String key = keyIterator.next();
            Integer value = userMap.get(key);
            jedis.hset("ct_user", key, "" + value);
        }

        keyIterator = dateMap.keySet().iterator();
        while ( keyIterator.hasNext() ) {
            String key = keyIterator.next();
            Integer value = dateMap.get(key);
            jedis.hset("ct_date", key, "" + value);
        }

    }
}