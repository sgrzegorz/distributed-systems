zkServer.cmd
zkCli.cmd -server 127.0.0.1:2191


ls /
create /zk-demo Pierwszy
create /zkdemo/my-node Moje_dane
ls /zk-demo
get –s /zk-demo/mynode
set /zkdemo/my-node dane
delete /zk-demo/mynode
deleteall /zk-demo Z POTOMKAMI

create -s /zkdemo/sequential dane1
create -e /zk-demo/ephemeral dane   JAK WYŁĄCZYMY zkServer.cmd TO ZNIKA :(


create /zk-demo/watchthis data
get –s –w /zkdemo/watch-this
stat –w /zkdemo/watch-this


