springboot整合ldap ，没有使用jpa

使用postman 访问 http://localhost:8080/findOne
application/x-www-form-urlencoded 作为 content-type 提交
cn=Minu
的参数
postman返回
{
  "dn": {
    "rdns": [
      {
        "value": "myorg",
        "type": "o"
      },
      {
        "value": "Minu",
        "type": "cn"
      }
    ],
    "empty": false,
    "all": {}
  },
  "cn": "Minu",
  "sn": "Xin",
  "userPassword": "57,57,57"
}

POSTMAN POST访问
http://localhost:8080/create
application/x-www-form-urlencoded 作为 content-type 提交
cn=Minu1
sn=Numi
userPassword=777
参数就可以创建对象了

将新增的操作方法中的地址改成 http://localhost:8080/update
则就可以实现改

最后 POST访问
http://localhost:8080/delete
以
application/x-www-form-urlencoded 作为 content-type 提交
cn=Minu1
则可以删除我们创建的对象



数据准备是

```tex
dn:o=myorg,dc=yfs,dc=com
objectclass:organization
description:Container fordeveloper entries
o:myorg

dn:cn=Michael,o=myorg,dc=yfs,dc=com
cn:Michael
objectClass:inetOrgPerson
mail:sjsky_007@gmail.com
userPassword:666
labeledURI:http://www.yfs.com
sn:Sun

dn:cn=Minu,o=myorg,dc=yfs,dc=com
cn:Minu
objectClass:inetOrgPerson
mail:scibird@126.com
userPassword:999
labeledURI:http://www.github.com
sn:Xin
```

事先导入openldap即可