$(document).ready(function () {
    $('#inputFile').change(function () {
        let input = $('#inputFile');
        let file = input[0].files[0].name;
        $(".custom-file-label").text(file);

        let fileReader = new FileReader();
        fileReader.onload = function (fileLoadedEvent) {
            let textFromFileLoaded = fileLoadedEvent.target.result;
            $("#contentFile").text(textFromFileLoaded);
        };
        fileReader.readAsText(input[0].files[0], "UTF-8");
    });
});

$(document).ready(function () {
    if ($(".checkNameDisk").prop("checked") === true) {
        $("#dictName").prop("disabled", true);
    } else {
        $("#dictName").prop("disabled", false);
    }
});

$(document).ready(function () {
    $(".checkNameDisk").click(function () {
        let dictName = $("#dictName");
        if ($(".checkNameDisk").prop("checked") === true) {
            dictName.prop("disabled", true);
            dictName.val("");
        } else {
            dictName.prop("disabled", false);
        }
    });
});


$(document).ready(function () {
    $("#table-person-dict").DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });
});

$(document).ready(function () {
    $('#steno-content').DataTable({
        "lengthMenu": [[5, 10, 25, 50, -1], [5, 10, 25, 50, "All"]]
    });

});

$(document).ready(function () {
    $('#modalCart').on('hidden', function () {
        clear();
    });
});

