var stompClient = null;

function connect() {
//        var socket = new SockJS('/v1/order');
    var socket = new SockJS("/v1/order?jsonrpc=2.0&id=1&method=getAll");
    // var socket = new SockJS("/v1/order?jsonrpc=2.0&id=1&method=get&params=%7B%22id%22%3A2%7D");
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

connect();
// stompClient.send(JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
   stompClient.send("test");
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
disconnect();