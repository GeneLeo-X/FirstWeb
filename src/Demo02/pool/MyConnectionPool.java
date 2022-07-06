package Demo02.pool;

import Demo02.JdbcUtil;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * 自定义连接池
 */
public class MyConnectionPool   implements DataSource {
    private static final int dilatationCount = 5;
    private List<Connection> connectionList = new ArrayList<>(dilatationCount);
    private int count;

    @Override
    /**
     * 获取连接对象
     */
    public Connection getConnection() throws SQLException {
        if(count == connectionList.size())//连接池的连接数量去完了之后，再加入连接
            for(int i = 0;i < dilatationCount ;i++)
                connectionList.add(JdbcUtil.getConnection());

        return connectionList.remove(count ++);
    }

    /**
     * 归还连接对象
     * @param conn
     */
    public void releaseConnection(Connection conn){
        connectionList.add(conn);
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> iface) throws SQLException {
        return null;
    }

    @Override
    public boolean isWrapperFor(Class<?> iface) throws SQLException {
        return false;
    }

    @Override
    public PrintWriter getLogWriter() throws SQLException {
        return null;
    }

    @Override
    public void setLogWriter(PrintWriter out) throws SQLException {

    }

    @Override
    public void setLoginTimeout(int seconds) throws SQLException {

    }

    @Override
    public int getLoginTimeout() throws SQLException {
        return 0;
    }

    @Override
    public Logger getParentLogger() throws SQLFeatureNotSupportedException {
        return null;
    }
}
