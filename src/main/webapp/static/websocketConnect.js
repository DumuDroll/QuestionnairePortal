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
        };
    }
    else
    {
        alert("WebSocket is NOT supported by your Browser!");
    }
}
wSocket.onmessage = function(event)
{
    const received_msg = event.data;
    runRemoteCommand(received_msg);
};

wSocket.onclose = function()
{
};
function runRemoteCommand(param) {
    const promise = rc([{name: 'param', value: param}]);
    promise.then(function () {
        console.log("Request successful");
    }).catch(function (error) {
        console.error("Request failed", error);
    });
}