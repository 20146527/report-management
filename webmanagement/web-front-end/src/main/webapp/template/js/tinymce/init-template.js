var mentionsFetchFunction = function (query, success) {
    var users = [
        "Terry Green"
    ];

    users = users.map(function (fullName) {
        var userName = fullName.replace(/ /g, '').toLowerCase();

        return {
            id: userName,
            name: userName,
            fullName: fullName
        }
    });

    users = users.filter(function (user) {
        return user.name.indexOf(query.term) === 0
    });

    success(users)
};


tinymce.init({
    selector: 'textarea#full-featured',
    content_style: "#tinymce {width: 21cm; margin: auto; border: 5px solid red}",
    plugins: 'print preview searchreplace autolink directionality visualblocks visualchars fullscreen image link media template codesample table charmap hr pagebreak nonbreaking anchor toc insertdatetime advlist lists wordcount imagetools textpattern help',
    toolbar: 'formatselect | bold italic strikethrough forecolor backcolor permanentpen formatpainter | link image media pageembed | alignleft aligncenter alignright alignjustify  | numlist bullist outdent indent | removeformat | addcomment',
    image_advtab: true,
    body_class: "A4",
    content_css: "template/css/tiny-style.css",
    link_list: [
        { title: 'My page 1', value: 'http://www.tinymce.com' },
        { title: 'My page 2', value: 'http://www.moxiecode.com' }
    ],
    image_list: [
        { title: 'My page 1', value: 'http://www.tinymce.com' },
        { title: 'My page 2', value: 'http://www.moxiecode.com' }
    ],
    image_class_list: [
        { title: 'None', value: '' },
        { title: 'Some class', value: 'class-name' }
    ],
    importcss_append: true,
    height: 1000,
    file_picker_callback: function (callback, value, meta) {
        /* Provide file and text for the link dialog */
        if (meta.filetype === 'file') {
            callback('https://www.google.com/logos/google.jpg', { text: 'My text' });
        }

        /* Provide image and alt text for the image dialog */
        if (meta.filetype === 'image') {
            callback('https://www.google.com/logos/google.jpg', { alt: 'My alt text' });
        }

        /* Provide alternative source and posted for the media dialog */
        if (meta.filetype === 'media') {
            callback('movie.mp4', { source2: 'alt.ogg', poster: 'https://www.google.com/logos/google.jpg' });
        }
    },
    templates: [
        { title: 'Some title 1', description: 'Some desc 1', content: 'My content' },
        { title: 'Some title 2', description: 'Some desc 2', content: '<div class="mceTmpl"><span class="cdate">cdate</span><span class="mdate">mdate</span>My content2</div>' }
    ],
    template_cdate_format: '[CDATE: %m/%d/%Y : %H:%M:%S]',
    template_mdate_format: '[MDATE: %m/%d/%Y : %H:%M:%S]',
    image_caption: true,
    spellchecker_dialog: true,
    spellchecker_whitelist: ['Ephox', 'Moxiecode'],
    tinycomments_mode: 'embedded',
    mentions_fetch: mentionsFetchFunction,
    content_style: '.mce-annotation { background: #fff0b7; } .tc-active-annotation {background: #ffe168; color: black; }',
    // setup : function(ed)
    // {
    //     ed.onInit.add(function(ed)
    //     {
    //         // ed.getBody().style.fontSize = '80px';
    //         ed.execCommand("fontName", false, "Times New Roman");
    //         ed.execCommand("fontSize", false, "14");
    //     });
    // }
});