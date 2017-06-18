var stompClient = null;

function connect1() {
    var socket = new SockJS('/color');

    /*socket.onmessage = function (message) {
        console.log("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        console.dir(message);
    };
    setTimeout(function () {
        console.log("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        var str = '{"id":2}';
        var estr = btoa(str);
        socket.send('{"id":"1","jsonrpc":"2.0","method":"getOne","params":{"id":2}}');
    }, 2000);*/

    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('!Connected: ' + frame);
        stompClient.send("/color", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"getOne","params":[2]}));
        // stompClient.send("/color", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"getOne","params":{"id":2}}));
        // stompClient.send("/color", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"getAll"}));
        stompClient.subscribe('/user/topic/color', function(message){
            console.log("1 ************************");
            console.dir(message);
        });
        stompClient.subscribe('/topic/light', function(message){
            console.log("2 ************************");
            console.dir(message);
        });
    });
}

function connect2() {
    var socket = new SockJS('/color');

    var stompClient1 = Stomp.over(socket);
    stompClient1.connect({}, function(frame) {
        console.log('!Connected: ' + frame);
        stompClient1.subscribe('/user/topic/color', function(message){
            console.log("2 ************************");
            console.dir(message);
        });
        stompClient1.subscribe('/topic/light', function(message){
            console.log("4 ************************");
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

connect1();
connect2();
// stompClient.send(JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
//    stompClient.send("test");
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
//    stompClient.send("/", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"get","params":{"id":2}}));
// disconnect();


