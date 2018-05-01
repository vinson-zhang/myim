// 创建ProtoBuf
var ProtoBuf = dcodeIO.ProtoBuf;
// 加载ProtoBuf文件
var BaseMessage = ProtoBuf.loadProtoFile("/Message.proto").build("BaseMessage");
var baseMessage = new BaseMessage();
var TextMessage = ProtoBuf.loadProtoFile("/Message.proto").build("TextMessage");
var textMessage = new TextMessage;
textMessage.setmsgId("123").setcontent("123");
baseMessage.bytesData = textMessage.encode().toBuffer();

// 请求websocket
var websocket = null;
websocket = new WebSocket('ws://localhost:8889/ws');
// websocket.binaryType = "arraybuffer";

// 连接成功建立的回调方法
websocket.onopen = function () {
    console.log("连接成功");
    websocket.send(baseMessage.encode().toBuffer());
}
// 接收到消息的回调方法
websocket.onmessage = function (res) {
	  console.log("接收到消息");
    console.log(res.data);
    console.log(CommonProtocol.decode(res.data));
}
// 连接关闭的回调方法
websocket.onclose = function () {
	  console.log("连接关闭");
}
