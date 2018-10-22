#### 在线WebSocket测试工具
 http://coolaf.com/tool/chattest
 
#### 打开两个浏览器 分别打开测试工具并输入 
浏览器1：ws://localhost:8080/websocket/1  
浏览器2：ws://localhost:8080/websocket/2

#### 打开postman 使用post请求
```
http://localhost:8080/chat/sendOne
{
	"senderId":"1",
	"receiverId":"2",
	"content":"aaadfasasdfas fasfaaa"
}
```


