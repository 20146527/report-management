var x;

function cB(stat, where) {
    let stat2 = stat;
    let insMode = $("#insMode");
    let bool = insMode.prop("checked");
    if (stat === 'key' && bool) {
        insMode.prop("checked", false);
        stat = false;
    } else if (stat === 'key' && !bool) {
        insMode.prop("checked", true);
        stat = true;
    }

    if (stat2 !== 'key') {
        where.focus();
    }
}

function checkArrows(x, where) {
    if (x !== 37 && x !== 38 && x !== 40 && x !== 8 && x !== 45) {
        overwrite(where);
    }
}

function overwrite(where) {
    if ($("#insMode").prop("checked")) {
        if (document.selection) {
            where.focus();
            sel = document.selection.createRange();
            if (sel.text === "") {
                sel.text = 'µµµ';
                dummy = where.createTextRange();
                dummy.findText('µµµ');
                dummy.select();
                pos = where.value.indexOf('µµµ');
                begin = pos;
                document.selection.clear();
                dummy.moveEnd('character', 1);
                dummy.select();
                where.focus();
            }
        } else if (where.selectionStart || where.selectionStart === '0') {
            begin = where.selectionStart;
            end = where.selectionEnd;
            if (end === begin) {
                where.selectionEnd = end + 1;
                where.focus();
            }
        }
    }//comment this if you want overwrite mode by default
}

function fnActiveOverwriteByKey() {
    if (event.keyCode === 45) {
        x = event.keyCode;
        cB('key', $("#txtEditor"));
        return false
    }
}

