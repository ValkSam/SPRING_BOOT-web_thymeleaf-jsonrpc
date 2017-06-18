var stompClient = null;

function connect() {
    var socket = new SockJS('/color');
    socket.onmessage = function (message) {
        console.log("***********&&&*************"+message);
        // socket.send(JSON.stringify({ 'colorString': 'RED!!!!!' }));
        var str = '{"id":2}';
        var estr = btoa(str);
        socket.send('{"id":"1","jsonrpc":"2.0","method":"getOne","params":{"id":2}}');
        // socket.send('{"id":"1","jsonrpc":"2.0","method":"getAll"}');
        // socket.send("/v1/order?jsonrpc=2.0&id=1&method=getAll");
        // socket.send("/v1/order?jsonrpc=2.0&id=1&method=get&params="+estr);
    };
    /*stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('!Connected: ' + frame);
        stompClient.subscribe('/topic/color', function(message){
            console.log("************************"+message);
            stompClient.send("/color", {}, JSON.stringify({ 'colorString': 'RED!!!!!' }));
            var str = btoa('{"id":2}');
            // stompClient.send("/v1/order?jsonrpc=2.0&id=1&method=get&params="+str);
            // stompClient.send("/color", {}, JSON.stringify({"id":2}));
        });
        stompClient.onmessage(function (message) {
            console.log("***********!!!*************"+message);
        });
    });*/
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
//    stompClient.send("test");
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
// disconnect();