

jdbcutil-工具类抽取
Example01 - 工具类的应用
db.properties - 属性配置文件
    开发中获取连接的4个参数（驱动、url、用户名、密码）通常都存在配置文件中，方便后期维护，程序如果需要更换数据库，只需要修改配置文件即可。
    1.文件位置：任意，最好在src根目录下
    2.文件名称任意，扩展名为properties
    3.文件内容：一行一组数据，格式为"key=value"
        (1)key的命名自定义，如果是多个单词，习惯使用点分隔。例如:jdbc.driver
        (2)value值不支持中文，如果需要使用非英文字符，将进行unicode转换

Example02 - sql攻击演示

pool - 连接池

Example03 - 连接池测试

Example04 - DBUtils工具类的应用（优化了JDBC操作数据库的）