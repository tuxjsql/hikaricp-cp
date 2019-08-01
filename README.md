[![Maven Central](https://maven-badges.herokuapp.com/maven-central/dev.tuxjsql/hikaricp-cp/badge.svg)](https://mvnrepository.com/artifact/dev.tuxjsql/hikaricp-cp)

# HikariCP-CP the HikariCP connection provider

# How to use
1. Add HikariCP into your classpath
2. That's it!


# Optional Options. 
If you want to configure it a bit more you can. That magical properties file seen [here](https://tuxjsql.dev/creating-your-first-tuxjsql.html)
You can add the following values in there

| key | Description | 
|--|--|
| idle.timeout | the maximum amount of time a connection may sit in a pool |  
| leak.detection | HikariCP leak detection setup |  
| pool.size | The maximum pool size for this pool |  
| pool.name | Your pools name |  
