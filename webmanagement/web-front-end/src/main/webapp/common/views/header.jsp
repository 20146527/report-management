<!-- header header -->
<div class="header">
    <nav class="navbar top-navbar navbar-expand-md navbar-light">
        <!-- Logo -->
        <div class="navbar-header">
            <a class="navbar-brand" href="/home.html">
                <!-- Logo icon -->
                <b><img src="<c:url value='/template/images/logo.png'/>" alt="homepage" class="dark-logo"/></b>
                <!--End Logo icon -->
                <!-- Logo text -->
                <span><img src="<c:url value='/template/images/logo-text.png'/>" alt="homepage"
                           class="dark-logo"/></span>
            </a>
        </div>
        <!-- End Logo -->
        <div class="navbar-collapse">
            <!-- toggle and nav items -->
            <ul class="navbar-nav mr-auto mt-md-0">
                <!-- This is  -->
                <li class="nav-item"><a class="nav-link nav-toggler hidden-md-up text-muted  "
                                        href="javascript:void(0)"><i class="mdi mdi-menu"></i></a></li>
                <li class="nav-item m-l-10"><a class="nav-link sidebartoggler hidden-sm-down text-muted  "
                                               href="javascript:void(0)"><i class="ti-menu"></i></a></li>
            </ul>

            <ul class="navbar-nav my-lg-0">
                <!-- Notification -->
                <li class="nav-item dropdown" id="notify">
                    <a class="nav-link dropdown-toggle text-muted text-muted  " href="#" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false"> <i class="fa fa-bell"></i>
                        <div class="notify"><span class="heartbit"></span> <span class="point"></span></div>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right mailbox animated zoomIn">
                        <ul>
                            <li>
                                <div class="drop-title">Thông báo</div>
                            </li>
                            <li>
                                <div class="message-center" id="list-noti">
                                    <!-- Message -->

                                    <!-- Message -->
                                </div>
                            </li>
                            <li>
                                <a class="nav-link text-center" onclick="clearAllNoti()"> <strong>Xóa hết thông báo</strong> <i class="fa fa-angle-right"></i> </a>
                            </li>
                        </ul>
                    </div>
                </li>
                <!-- End Notification -->
                <!-- Profile -->
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle text-muted" href="#" data-toggle="dropdown"
                       aria-haspopup="true" aria-expanded="false">
                        <img src="<c:url value='/template/images/avatar/'/>${login.getAvaPath()}" alt="user"
                             class="profile-pic"/>
                    </a>
                    <div class="dropdown-menu dropdown-menu-right animated zoomIn">
                        <ul class="dropdown-user">
                            <li><a href=""><i class="ti-user"></i> ${login.getFullName()}</a></li>
                            <hr>
                            <li><a href="/personal-information.html"><i class="ti-palette"></i> Thông tin cá nhân</a></li>
                            <li><a href="/logout.html"><i class="fa fa-power-off"></i> Đăng Xuất</a></li>
                        </ul>
                    </div>
                </li>
                <!-- End Profile -->
            </ul>
        </div>
    </nav>
</div>
<!-- End header header -->

<script>
    $(document).ready(function () {
        $('#notify').hide();
        initNotification();
    });


    function initNotification() {
        getNotification();
        var myProgress = setInterval(getNotification, 10000);
        function getNotification() {
            $.ajax({
                url: "/ajax-notification.html",
                type: "get",
                data: {
                    urlType: "getNotification"
                },
                success: function (data) {
                    let listNotify = typeof data != 'object' ? jQuery.parseJSON(data) : data;
                    if(listNotify.length !== 0){
                        setNotification(listNotify);
                    }else {
                        $('#notify').hide();
                    }
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    clearInterval(myProgress);
                    console.log(textStatus, errorThrown);
                }
            });
        }
    }

    function setNotification(list) {
        $('#notify').show();
        let html = '';
        $.each(list, function (i, item) {
            html += '<a class="click-noti" id="'+item.notificationId+'" path="'+item.link+'" title="'+item.title+'" content="'+item.content+'">\n';
            if(item.type === 51){
                html +='<div class="btn btn-info btn-circle m-r-10"><i class="fa fa-user-plus"></i></div>\n';
            }else if(item.type === 52){
                html +='<div class="btn btn-success btn-circle m-r-10"><i class="fa fa-check"></i></div>\n';
            }else if(item.type === 53){
                html +='<div class="btn btn-danger btn-circle m-r-10"><i class="fa fa-times"></i></div>\n';
            }else {
                html +='<div class="btn btn-warning btn-circle m-r-10"><i class="fa fa-bell"></i></div>\n';
            }
            html +=    '    <div class="mail-contnet">\n' +
                '        <h5>'+item.title+'</h5> <span\n' +
                '            class="mail-desc">'+item.content+'</span> <span class="time">9:30 AM</span>\n' +
                '    </div>\n' +
                '</a>';
        });
        html += '<script src="/template/js/manager/clear-notification.js">';

        $('#list-noti').empty();
        $('#list-noti').html(html);
    }


    function clearAllNoti() {
        let listNotiID = '';
        let listNoti = $('#list-noti').find('.click-noti');
        $.each(listNoti, function (i, item) {
            if(listNotiID === ''){
                listNotiID += $(item).attr("id");
            }else {
                listNotiID += ';' + $(item).attr("id");
            }
        });
        $.ajax({
            url: "/ajax-notification.html",
            type: "get",
            data: {
                type: "clearAll",
                ids: listNotiID
            },
            success: function (data) {
                initNotification();
                console.log("Clear All Notification: "+data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                console.log(textStatus, errorThrown);
            }
        });
    }
</script>