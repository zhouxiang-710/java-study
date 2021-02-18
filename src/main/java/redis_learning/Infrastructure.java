package redis_learning;

import redis.clients.jedis.Jedis;

public class Infrastructure {

	public static void main(String[] args) {
		/*
		 * 连接VM下Ubuntu的 Redis 服务，单纯的简单连接测试
		 */
		Jedis jedis = new Jedis("redis.infrastructure.svc.cluster.local", 6379);// 此ip为Ubuntu的ip地址
		jedis.connect();
		//jedis.auth("redis");// redis-cli的访问密码
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		// 进行数据库操作测试
		jedis.set("test", "redis test");
		//jedis.set("key7", "张瑞瑞");//中文在Ubuntu下没正常显示
		String string = jedis.get("test");
		//String string1 = jedis.get("key6");
		System.out.println(string + " " );
		new RedisClient().show();
	}
	// 然后去Ubuntu下，输入命令：get key6
	// 在终端界面输出：jedis test6
	// 说明远程连接、操作数据库成功。
}
