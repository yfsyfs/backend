postgresql-jpa 是使用springboot集成jpa拉取pg数据库数据的时候因为处理pg的二进制(bytea)字段发生**不良的类型 long**问题

postgresql-jdbc 使用原生的jdbc 进行拉取, 没问题

t_face_capture.sql 是建库脚本

不过真心吐槽，一家公司竟然用关系型数据库存储二进制数据