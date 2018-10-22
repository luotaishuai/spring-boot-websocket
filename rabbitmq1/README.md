#### 在自己的ubuntu虚拟机上快速部署rabbitmq
```
echo 'deb http://www.rabbitmq.com/debian/ testing main' | sudo tee /etc/apt/sources.list.d/rabbitmq.list
wget -O- https://www.rabbitmq.com/rabbitmq-release-signing-key.asc | sudo apt-key add -
sudo apt-get update
sudo apt-get install rabbitmq-server
```
打开管理页面
```
sudo rabbitmq-plugins enable rabbitmq_management
```
打开浏览器输入
```
localhost:15672
```

rabbitmq 默认是无法远程连接，只识别localhost，如果要远程控制管理页面，还需要一些配置

websocket 在线测试工具 http://coolaf.com/tool/chattest

接口测试工具 postman

压力测试工具 jmeter