saveState = [];
var saveI = 0;

$('#txtEditor').on("keydown", function (event) {
    if (event.which === " ".charCodeAt(0)) {
        saveState.push($(this).val());
        saveI = saveState.length - 1;
        debug();
    }
});

function triggerUndo() {
    if (saveI >= 0) {
        saveI--;
        $("#txtEditor").val(saveState[saveI]);
    }
    debug();
}

function triggerRedo() {
    if (saveI < saveState.length) {
        saveI++;
        $("#txtEditor").val(saveState[saveI]);
    }
    debug();
}

$(document).ready(function () {
    var ctrlDown = false,
        ctrlKey = 17,
        cmdKey = 91,
        zKey = 90,
        yKey = 89;

    $(document).keydown(function (e) {
        if (e.keyCode == ctrlKey || e.keyCode == cmdKey) ctrlDown = true;
    }).keyup(function (e) {
        if (e.keyCode == ctrlKey || e.keyCode == cmdKey) ctrlDown = false;
    });

    $("#txtEditor").keydown(function (e) {
        if (ctrlDown && (e.keyCode == zKey || e.keyCode == yKey)) event.preventDefault();
    });

    // Document Ctrl + Z/Y
    $(document).keydown(function (e) {
        if ($("input,textarea").is(":focus")) {
            if (ctrlDown && (e.keyCode == zKey)) triggerUndo();
            if (ctrlDown && (e.keyCode == yKey)) triggerRedo();
        }
    });
});


function debug() {
    return;
    //removed, used for debugging.
    console.log(saveI);
    console.log(saveState);
}