function getDimensionsInJson() {
    let jsonObj = [];
    $(".fieldsPanel").each(function(element, index) {

        const width = $(this).width();
        const height = $(this).height();
        const positionTop = $(this).offset().top;
        const positionLeft = $(this).offset().left;

        let item = {}
        item ["width"] = width;
        item ["height"] = height;
        item ["positionTop"] = positionTop;
        item ["positionLeft"] = positionLeft;

        jsonObj.push(item);
    });

    runRemoteCommand(JSON.stringify(jsonObj));
}
function runRemoteCommand(param) {
    save([{name: 'param', value: param}]);
}