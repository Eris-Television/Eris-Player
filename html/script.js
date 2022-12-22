
/* --- GLOBAL-VARs --- */

var videoPlayer 
var videoSource

/* --- WebSocket --- */

var webSocket = new WebSocket("ws://127.0.0.1:8080/");

webSocket.onopen = (event) => {
    console.log("WS_> Open cnnection ...");
    webSocket.send("CONNECT");
};

webSocket.onclose = function() {
    console.log("WS_> Close connection ..."); 
    webSocket.send("DISCONNECT");
};

webSocket.onmessage = (event) => {
    let message = event.data;
    
    if(message.startsWith("NEXT_VIDEO")) {
        let nextVideo = message.substring(13, message.length)
        console.log("NV_> " + nextVideo)
        
        videoSource.src = nextVideo
        videoPlayer.load()
        videoPlayer.play()

    }else {
        switch(message) {
            case "CONNECTED":
            case "DISCONNECTED":
            case "ERROR":
                console.log(" R_> " + message)
                break;

            default:
                console.log("ER_> " + message)
                webSocket.send("ERROR")
                break;
        }
    }
};

/* --- Methods --- */

function onLoad() {
    videoPlayer = document.getElementById("videoPlayer")
    videoSource = document.getElementById("videoSource")
    videoPlayer.play()
}

function playNextVideo() {
    videoPlayer.pause()
    webSocket.send("NEXT_VIDEO")
}