const host = "ws://localhost:8080/responsesWS";
const wSocket = new WebSocket(host);
const browserSupport = ("WebSocket" in window);

// called  body onLoad()
function initializeReception()
{
    if (browserSupport)
    {
        wSocket.onopen = function()
        {
            alert(" Web Socket is connected, sending data");
            wSocket.send("ping");
        };
    }
    else
    {
        // The browser doesn't support WebSocket
        alert("WebSocket is NOT supported by your Browser!");
    }
}
wSocket.onmessage = function(event)
{
    const received_msg = event.data;
    //alert('server msg:' + received_msg);
    console.log(received_msg);
};

// called when socket closes
wSocket.onclose = function()
{
    // websocket is closed.
    //alert("Connection is closed...");
};