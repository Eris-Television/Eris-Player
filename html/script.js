
// --- GLOBAL-Vars : ---

var videoPlayer 
var videoSource

// const webSocket = new WebSocket("127.0.01")

function onLoad() {
    videoPlayer = document.getElementById("videoPlayer")
    videoSource = document.getElementById("videoSource")
    //openFullscreen();

}

function playNextVideo() {
    videoSource.src = getNextVideo()
    videoPlayer.load()
}

function getNextVideo() {
    return "t.mp4"
}

function openFullscreen() {

    var isInFullScreen = (document.fullScreenElement && document.fullScreenElement !==     null) ||    //alternative standard method  
    (document.mozFullScreen || document.webkitIsFullScreen);

    var docElm = document.documentElement;
    if (!isInFullScreen) {
        
        if (docElm.requestFullscreen) {
            docElm.requestFullscreen();
        }
        else if (docElm.mozRequestFullScreen) {
            docElm.mozRequestFullScreen();
            alert("Mozilla entering fullscreen!");
        }
        else if (docElm.webkitRequestFullScreen) {
            docElm.webkitRequestFullScreen();
            alert("Webkit entering fullscreen!");
        }

    }

    //var docElm = document.documentElement;
    //docElm.requestFullscreen();
    
}