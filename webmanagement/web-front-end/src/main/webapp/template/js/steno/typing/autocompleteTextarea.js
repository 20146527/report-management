var tag = [];
$(document).ready(function () {
    let pathPersonDict = $.base64.decode($("#input-person").attr("data-path"));
    $.ajax({
        url: pathPersonDict,
        async: true,
        success: function (data) {
            $.each(data, function (key, value) {
                if (value.length > 3) {
                    tag.push(value);
                }
            });
        }
    })
});

$(document).ready(function () {

    $("#txtEditor").autocomplete({
        minLength: 0,
        autoFocus: true,
        source: function (request, response) {
            let lastEntry = extractLast(request.term);
            let filteredArray = $.map(tag, function (item) {
                if (item.toLowerCase().includes(lastEntry.toLowerCase())) {
                    return item;
                } else {
                    return null;
                }
            });

            // delegate back to autocomplete, but extract the last term
            if (lastEntry.trim() === "" || lastEntry.trim() === null) {
                response($.ui.autocomplete.filter(filteredArray.slice(0, 10), lastEntry));
            } else {
                response($.ui.autocomplete.filter(filteredArray, lastEntry));
            }

        },
        focus: function () {
            // prevent value inserted on focus
            return false;
        },
        select: function (event, ui) {
            let terms = split(this.value);
            // remove the current input
            terms.pop();
            // add the selected item
            terms.push(ui.item.value);
            // add placeholder to get the comma-and-space at the end
            terms.push("");
            this.value = terms.join(" ");
            return false;
        }
    }).on("keydown", function (event) {
        // don't navigate away from the field on tab when selecting an item
        if (event.keyCode === $.ui.keyCode.TAB /** && $(this).data("ui-autocomplete").menu.active **/) {
            event.preventDefault();
            return null;
        }
    });

    function split(val) {
        return val.split(/ \s*/);
    }

    function extractLast(term) {
        return split(term).pop();
    }
});