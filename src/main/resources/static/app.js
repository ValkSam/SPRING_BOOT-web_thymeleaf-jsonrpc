var stompClient = null;

function connect1() {
    var socket = new SockJS('/socket');
    /*
    without Stomp
    socket.onmessage = function (message) {
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
        stompClient.subscribe('/user/topic/base', function(message){
            console.log("We get the base data and ready to receive delta-parts");
            console.dir(message);
            console.log("==========================");
            /**/
            stompClient.subscribe('/topic/part', function(message){
                console.log("it's delta-part: ");
                console.dir(message);
            });
        });
        stompClient.send("/order", {}, JSON.stringify({"id":"1","jsonrpc":"2.0","method":"getOne","params":[2]}));

    });
}

function connect2() {
    var socket = new SockJS('/part1');

    var stompClient1 = Stomp.over(socket);
    stompClient1.connect({}, function(frame) {
        console.log('!Connected: ' + frame);
        stompClient1.subscribe('/user/topic/part1', function(message){
            console.log("3 ************************");
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
// connect2();
// disconnect();


