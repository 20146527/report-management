var choseFile = () =;
>
{
    var fileToLoad = document.getElementById('inputFile').files[0];
    var name = fileToLoad.name;
    document.getElementById('lable-name2').innerHTML = name;
    var fileReader = new FileReader();
    fileReader.onload = function (fileLoadedEvent) {
        var textFromFileLoaded = fileLoadedEvent.target.result;
        document.getElementById('stenograph').value = textFromFileLoaded;
        document.getElementById('stenograph').style.fontFamily = 'Wingdings Regular';
        changeData('stenograph', 'doc', '');
    };
    fileReader.readAsText(fileToLoad, "UTF-8");


}
var changeData = (id1, id2, fontFamily) =;
>
{
    var content = document.getElementById(id1).value;

    document.getElementById(id2).value = content;

    document.getElementById(id2).style.fontFamily = fontFamily;
}