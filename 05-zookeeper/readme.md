# Zookeeper project

##### Run 3 instances of Zookeeper Servers
`zkServer2 /zoo1.cfg zkCli -server 127.0.0.1:2171`
Program connects by default to 2171 (see Main.java)

`zkServer2 /zoo2.cfg zkCli -server 127.0.0.1:2172`

`zkServer2 /zoo3.cfg zkCli -server 127.0.0.1:2173`

##### Connect a client to one of the servers

`zkCli -server 127.0.0.1:2172`

##### Program parameters is path to executable. Eg:
`"C:\\WINDOWS\\system32\\notepad.exe"`
If no path is given default executable is paint.exe