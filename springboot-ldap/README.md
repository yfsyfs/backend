这里提供了springboot整合ldap的demo
这里初始化数据为

dn: o=myorg,dc=yfs,dc=com
objectClass: organization
o: myorg
description: Container fordeveloper entries

dn: cn=Michael,o=myorg,dc=yfs,dc=com
objectClass: inetOrgPerson
cn: Michael
sn: Sun
labeledURI: http://www.yfs.com
mail: sjsky_007@gmail.com
userPassword:: NjY2

dn: cn=Minu,o=myorg,dc=yfs,dc=com
objectClass: inetOrgPerson
cn: Minu
sn: Xin
labeledURI: http://www.github.com
mail: scibird@126.com
userPassword:: OTk5

dn: ou=study,o=myorg,dc=yfs,dc=com
objectClass: top
objectClass: person
objectClass: organizationalPerson
objectClass: inetOrgPerson
cn: gao
sn: xiao
ou: study

dn: cn=xie,ou=study,o=myorg,dc=yfs,dc=com
objectClass: top
objectClass: person
objectClass: organizationalPerson
objectClass: inetOrgPerson
cn: xie
sn: xin

dn: sn=wen,ou=study,o=myorg,dc=yfs,dc=com
objectClass: top
objectClass: person
objectClass: organizationalPerson
objectClass: inetOrgPerson
cn: zhang
sn: wen