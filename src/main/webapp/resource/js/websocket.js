var ws;

function connect() {
    var username = document.getElementById("username").value;
    ws = new WebSocket("ws://localhost:8183/websocket/chat/" + username);
    //ws = new WebSocket("ws://" + document.location.host + "http://localhost:8183//java-websocket/chat/" + username);


    ws.onmessage = function(event) {
        var log = document.getElementById("log");
        console.log(event.data);
        var message = JSON.parse(event.data);
        log.innerHTML += message.from + " : " + message.content + "\n";
    };

    ws.onclose = function(event){
        log.innerHTML += " Connection Closed \n";
    };

}

function send() {
    var content = document.getElementById("msg").value;
    var json = JSON.stringify({
        "content":content
    });

    ws.send(json);
}

function closeSocket(){
    ws.close();
}