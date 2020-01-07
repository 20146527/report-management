CKEDITOR.replace('editor1', {
    toolbar: [
        {name: 'document', items: ['Print']},
        {name: 'clipboard', items: ['Undo', 'Redo']},
        {name: 'styles', items: ['Format', 'Font', 'FontSize']},
        {name: 'basicstyles', items: ['Bold', 'Italic', 'Underline', 'Strike', 'RemoveFormat', 'CopyFormatting']},
        {name: 'colors', items: ['TextColor', 'BGColor']},
        {name: 'align', items: ['JustifyLeft', 'JustifyCenter', 'JustifyRight', 'JustifyBlock']},
        {name: 'paragraph'},
        {name: 'tools', items: ['Maximize']}
    ],

    customConfig: '',
    disallowedContent: 'img{width,height,float}',
    extraAllowedContent: 'img[width,height,align]',
    extraPlugins: 'tableresize,uploadimage,uploadfile',
    height: 800,
    contentsCss: ['https://cdn.ckeditor.com/4.8.0/full-all/contents.css', 'assets/css/mystyles.css'],
    bodyClass: 'document-editor',
    format_tags: 'p;h1;h2;h3;pre',
    removeDialogTabs: 'image:advanced;link:advanced',
    stylesSet: [
        /* Inline Styles */
        {name: 'Marker', element: 'span', attributes: {'class': 'marker'}},
        {name: 'Cited Work', element: 'cite'},
        {name: 'Inline Quotation', element: 'q'},

        /* Object Styles */
        {
            name: 'Special Container',
            element: 'div',
            styles: {
                padding: '5px 10px',
                background: '#eee',
                border: '1px solid #ccc'
            }
        },
        {
            name: 'Compact table',
            element: 'table',
            attributes: {
                cellpadding: '5',
                cellspacing: '0',
                border: '1',
                bordercolor: '#ccc'
            },
            styles: {
                'border-collapse': 'collapse'
            }
        },
        {
            name: 'Borderless Table',
            element: 'table',
            styles: {'border-style': 'hidden', 'background-color': '#E6E6FA'}
        },
        {name: 'Square Bulleted List', element: 'ul', styles: {'list-style-type': 'square'}}
    ]
});