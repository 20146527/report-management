function redirectFunction(type, elem) {
    let name = $(elem).closest("tr").find(".name-dict").text();
    let data = $.base64.encode(name);
    window.location.href = "/dict-history.html?type=" + type + "&name=" + data;
}

function download(name) {
    window.location.href = "/dict-history.html?type=download&nameFile=" + name + ".json";
}