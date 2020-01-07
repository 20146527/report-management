'use strict';

// Create an instance
var wavesurfer;

// Init & load
var pathXML = '<c:url value="/file/"/>' + 'result24/Session1/Record1/All.xml';
var pathAudio = '<c:url value="/file/"/>' + 'audiorecord/vnist.mp3'
document.addEventListener('DOMContentLoaded', function () {
    var options = {
        container: '#waveform',
        waveColor: '#c1e7f4',
        progressColor: '#03a9f4',
        loaderColor: '#03a9f4',
        cursorColor: 'navy',
        selectionColor: '#d0e9c2',
        loopSelection: false,
        plugins: [
            WaveSurfer.elan.create({
                url: pathXML,
                container: '#annotations',
                tiers: {
                    Text: true,
                    Comments: true
                }
            }),
            WaveSurfer.regions.create()
        ]
    };

    // if (location.search.match('scroll')) {
    //     options.minPxPerSec = 100;
    //     options.scrollParent = true;
    // }
    //
    // if (location.search.match('normalize')) {
    //     // options.normalize = true;
    //     options.minPxPerSec = 100;
    //     options.scrollParent = true;
    // }
    options.minPxPerSec = 100;
    options.scrollParent = true;

    // Init wavesurfer
    wavesurfer = WaveSurfer.create(options);

    /* Progress bar */
    (function () {
        var progressDiv = document.querySelector('#progress-bar');
        var progressBar = progressDiv.querySelector('.progress-bar');

        var showProgress = function (percent) {
            progressDiv.style.display = 'block';
            progressBar.style.width = percent + '%';
        };

        var hideProgress = function () {
            progressDiv.style.display = 'none';
        };

        wavesurfer.on('loading', showProgress);
        wavesurfer.on('ready', hideProgress);
        wavesurfer.on('destroy', hideProgress);
        wavesurfer.on('error', hideProgress);
    })();

    wavesurfer.elan.on('ready', function (data) {
        wavesurfer.load(pathAudio);
    });

    wavesurfer.elan.on('select', function (start, end) {
        wavesurfer.backend.play(start, end);
    });

    wavesurfer.elan.on('ready', function () {
        var classList = wavesurfer.elan.container.querySelector('table')
            .classList;
        ['table', 'table-striped', 'table-hover'].forEach(function (cl) {
            classList.add(cl);
        });
    });

    var prevAnnotation, prevRow, region;
    var onProgress = function (time) {
        var annotation = wavesurfer.elan.getRenderedAnnotation(time);
        //console.log("Kich thuoc roww:  "+annotation.id);
        if (prevAnnotation != annotation) {
            prevAnnotation = annotation;

            region && region.remove();
            region = null;

            if (annotation) {
                // console.log("Nghia ne!!");
                // Highlight annotation table row
                var row = wavesurfer.elan.getAnnotationNode(annotation);
                // console.log("Rowwwwwwwwww!!");
                // console.log(row);

                prevRow && prevRow.classList.remove('table-success');
                prevRow = row;
                row.classList.add('table-success');
                var before = row.previousSibling;
                if (before) {
                    wavesurfer.elan.container.scrollTop = before.offsetTop;
                }

                // Region
                region = wavesurfer.addRegion({
                    start: annotation.start,
                    end: annotation.end,
                    resize: false,
                    color: 'rgba(223, 240, 216, 0.7)'
                });
            }
        }
    };


    wavesurfer.on('audioprocess', onProgress);
});
