function getDimensionsInJson() {
    let jsonObj = [];
    $(".fieldsPanel").each(function() {

        const width = $(this).width();
        const height = $(this).height();
        const positionTop = $(this).offset().top;
        const positionLeft = $(this).offset().left;

        let item = {}
        item ["width"] = width;
        item ["height"] = height;
        item ["positionTopForCollision"] = positionTop;
        item ["positionLeftForCollision"] = positionLeft;

        jsonObj.push(item);
    });

    runRemoteCommand(JSON.stringify(jsonObj));
}
function runRemoteCommand(param) {
    save([{name: 'param', value: param}]);
}