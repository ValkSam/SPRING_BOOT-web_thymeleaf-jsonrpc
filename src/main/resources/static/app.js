var stompClient = null;

function connect() {
    var socket = new SockJS('/color');

    /*socket.onmessage = function (message) {
        console.dir(message);
    };
    setTimeout(function () {
        var str = '{"id":2}';
        var estr = btoa(str);
        socket.send('{"id":"1","jsonrpc":"2.0","method":"getOne","params":{"id":2}}');
    }, 2000);*/

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('!Connected: ' + frame);
        stompClient.send("/color", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"getOne","params":{"id":2}}));
        stompClient.subscribe('/topic/color', function(message){
            console.log("************************");
            console.dir(message);
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
//    stompClient.send("test");
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
// disconnect();


