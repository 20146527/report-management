var changeFile = (id, id2, id3) =;
>
{
    var fileToLoad = document.getElementById(id).files[0];
    var name = fileToLoad.name;
    document.getElementById(id2).innerHTML = name;
    var fileReader = new FileReader();
    fileReader.onload = function (fileLoadedEvent) {
        var textFromFileLoaded = fileLoadedEvent.target.result;
        document.getElementById(id3).innerHTML = textFromFileLoaded;
    };
    fileReader.readAsText(fileToLoad, "UTF-8");
}