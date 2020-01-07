<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Trang Chủ</title>
</head>
<body>

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-md-3">
            <div class="card p-30">
                <div class="media">
                    <div class="media-left meida media-middle">
                        <span><i class="fa fa-file-code-o f-s-40 color-primary"></i></span>
                    </div>
                    <div class="media-body media-text-right">
                        <h2>568120</h2>
                        <p class="m-b-0">Số biên bản</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card p-30">
                <div class="media">
                    <div class="media-left meida media-middle">
                        <span><i class="fa fa-home f-s-40 color-success"></i></span>
                    </div>
                    <div class="media-body media-text-right">
                        <h2>1178</h2>
                        <p class="m-b-0">Số cuộc họp</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card p-30">
                <div class="media">
                    <div class="media-left meida media-middle">
                        <span><i class="fa fa-file-text-o f-s-40 color-warning"></i></span>
                    </div>
                    <div class="media-body media-text-right">
                        <h2>25</h2>
                        <p class="m-b-0">Template</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-md-3">
            <div class="card p-30">
                <div class="media">
                    <div class="media-left meida media-middle">
                        <span><i class="fa fa-user-o f-s-40 color-danger"></i></span>
                    </div>
                    <div class="media-body media-text-right">
                        <h2>847</h2>
                        <p class="m-b-0">Người dùng</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row bg-white m-l-0 m-r-0 box-shadow ">

        <!-- column -->
        <div class="col-lg-12">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Biểu đồ tổng quan</h4>
                    <div id="extra-area-chart"></div>
                </div>
            </div>
        </div>
        12
        <!-- column -->

    </div>


    <div class="row">

        <div class="col-lg-3">
            <div class="card bg-dark match-height">
                <div class="testimonial-widget-one p-17">
                    <div class="testimonial-widget-one owl-carousel owl-theme">
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/4.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i> Trẻ em như búp trên cành; biết ăn ngủ, biết học
                                    hành là ngoan.
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/3.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i> Lợi ích của cá nhân gắn liền với lợi ích của tập
                                    thể. Nếu lợi ích cá nhân mâu thuẫn với lợi ích tập thể, thì đạo đức cách mạng đòi
                                    hỏi lợi ích riêng của cá nhân phải phục tùng lợi ích chung của tập thể.
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/2.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i>Nhiệm vụ của thanh niên không phải là đòi hỏi nước
                                    nhà đã cho mình những gì, mà phải tự hỏi mình đã làm gì cho nước nhà? Mình phải làm
                                    thế nào cho ích lợi nước nhà nhiều hơn ? Mình đã vì lợi ích nước nhà mà hy sinh phấn
                                    đấu đến chừng nào?
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/1.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i> Cái gì cũ mà xấu thì phải bỏ. Cái gì cũ mà không
                                    xấu, nhưng phiền phức thì phải sửa đổi cho hợp lý. Cái gì cũ mà tốt thì phải phát
                                    triển thêm. Cái gì mới mà hay thì phải làm
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/2.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i> Việc gì, dù lợi cho mình, phải xét nó có lợi cho
                                    nước không? Nếu không có lợi, mà có hại cho nước thì quyết không làm. Mỗi ngày cố
                                    làm một việc có lợi cho nước (lợi cho nước tức là lợi cho mình), dù là việc nhỏ, thì
                                    một năm ta làm được 365 việc. Nhiều lợi nhỏ cộng lại thành lợi to.
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                        <div class="item">
                            <div class="testimonial-content">
                                <img class="testimonial-author-img" src="<c:url value='/template/images/avatar/3.jpg'/>"
                                     alt=""/>
                                <div class="testimonial-author">Chủ tịch</div>
                                <div class="testimonial-author-position">Hồ Chí Minh</div>

                                <div class="testimonial-text">
                                    <i class="fa fa-quote-left"></i> Chúng ta không sợ sai lầm, chỉ sợ phạm sai lầm mà
                                    không quyết tâm sửa chữa. Muốn sửa chữa cho tốt thì phải sẵn sàng nghe quần chúng
                                    phê bình và thật thà tự phê bình. Không chịu nghe phê bình và không tự phê bình thì
                                    nhất định lạc hậu, thoái bộ. Lạc hậu và thoái bộ thì sẽ bị quần chúng bỏ rơi. Đó là
                                    kết quả tất nhiên của chủ nghĩa cá nhân.
                                    <i class="fa fa-quote-right"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-lg-9">

            <div class="card match-height">
                <div class="card-title">
                    <h4>Hoạt động gần đây</h4>
                </div>
                <div class="card-body">
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>#</th>
                                <th>Name</th>
                                <th>Product</th>
                                <th>quantity</th>
                                <th>Status</th>
                            </tr>
                            </thead>
                            <tbody>

                            <tr>
                                <td>
                                    <div class="round-img">
                                        <a href=""><img src="<c:url value='/template/images/avatar/5.jpg'/>" alt=""></a>
                                    </div>
                                </td>
                                <td>John Abraham</td>
                                <td><span>iBook</span></td>
                                <td><span>456 pcs</span></td>
                                <td><span class="badge badge-success">Done</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="round-img">
                                        <a href=""><img src="<c:url value='/template/images/avatar/6.jpg'/>" alt=""></a>
                                    </div>
                                </td>
                                <td>John Abraham</td>
                                <td><span>iPhone</span></td>
                                <td><span>456 pcs</span></td>
                                <td><span class="badge badge-success">Done</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="round-img">
                                        <a href=""><img src="<c:url value='/template/images/avatar/7.jpg'/>" alt=""></a>
                                    </div>
                                </td>
                                <td>John Abraham</td>
                                <td><span>iMac</span></td>
                                <td><span>456 pcs</span></td>
                                <td><span class="badge badge-warning">Pending</span></td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="round-img">
                                        <a href=""><img src="<c:url value='/template/images/avatar/5.jpg'/>" alt=""></a>
                                    </div>
                                </td>
                                <td>John Abraham</td>
                                <td><span>iBook</span></td>
                                <td><span>456 pcs</span></td>
                                <td><span class="badge badge-success">Done</span></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>

        </div>
    </div>


    <div class="row">
        <div class="col-lg-6">
            <div class="card row-2">
                <div class="card-body">
                    <h4 class="card-title">Ghi chú công việc</h4>
                    <div class="card-content">
                        <div class="todo-list">
                            <div class="tdl-holder">
                                <div class="tdl-content">
                                    <ul>
                                        <li>
                                            <label>
                                                <input type="checkbox"><i class="bg-primary"></i><span>Build an angular app</span>
                                                <a href='#' class="ti-close"></a>
                                            </label>
                                        </li>
                                        <li>
                                            <label>
                                                <input type="checkbox" checked><i class="bg-success"></i><span>Creating component page</span>
                                                <a href='#' class="ti-close"></a>
                                            </label>
                                        </li>
                                        <li>
                                            <label>
                                                <input type="checkbox" checked><i class="bg-warning"></i><span>Follow back those who follow you</span>
                                                <a href='#' class="ti-close"></a>
                                            </label>
                                        </li>
                                        <li>
                                            <label>
                                                <input type="checkbox" checked><i class="bg-danger"></i><span>Design One page theme</span>
                                                <a href='#' class="ti-close"></a>
                                            </label>
                                        </li>

                                        <li>
                                            <label>
                                                <input type="checkbox" checked><i class="bg-success"></i><span>Creating component page</span>
                                                <a href='#' class="ti-close"></a>
                                            </label>
                                        </li>
                                    </ul>
                                </div>
                                <input type="text" class="tdl-new form-control" placeholder="Type here">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="card row-2">
                <div class="card-body">
                    <div class="year-calendar"></div>
                </div>
            </div>
        </div>


    </div>
    <!-- End PAge Content -->
</div>
<!-- End Container fluid  -->

<script>
    $(document).ready(function () {

        $(".match-height").matchHeight();
        $(".row-2").matchHeight()

    })
</script>

</body>
</html>
