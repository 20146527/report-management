<%@include file="/common/taglib.jsp" %>
<c:url value="/manager-meeting-list.html" var="meetingListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-session-list.html" var="session ListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-user-list.html" var="userListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-speaker-list.html" var="speakerListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/report-list.html" var="reportListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-equipment.html" var="equipmentListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/report-transcript.html" var="transcriptUrl">
    <c:param name="step" value="1"/>
</c:url>
<c:url value="/report-create.html" var="createReportUrl">
    <c:param name="step" value="1"/>
</c:url>
<c:url value="/manager-template.html" var="templateListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-session-list.html" var="sessionListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<c:url value="/manager-statistic-file.html" var="statisticFileListUrl">
    <c:param name="urlType" value="url_list"/>
</c:url>
<!-- Left Sidebar -->
<div class="left-sidebar">
    <!-- Sidebar scroll-->
    <div class="scroll-sidebar">
        <!-- Sidebar navigation-->
        <nav class="sidebar-nav">
            <ul id="sidebarnav">
                <li class="nav-devider"></li>

                <li class="nav-label"><strong>Dashboard</strong></li>

                <c:if test="${fn:checkPermission(urlAllowedList, '/home.html')}">
                    <li><a href="/home.html" aria-expanded="false"><i class="fa fa-home"></i><span
                            class="hide-menu"> Trang chủ</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 0)}">
                    <li class="nav-label"><strong>Âm thanh</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-record.html')}">
                    <li><a href="/manager-record.html" aria-expanded="false"><i class="fa fa-microphone"></i><span
                            class="hide-menu"> Ghi âm cuộc họp</span></a>
                    </li>
                </c:if>


                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-audio.html')}">
                    <li><a href="/manager-audio.html" aria-expanded="false"><i
                            class="fa fa-folder-open-o"></i><span
                            class="hide-menu"> Quản lý file ghi âm</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/report-transcript.html')}">
                    <li><a href="${transcriptUrl}" aria-expanded="false"><i class="fa fa-play-circle-o"></i><span
                            class="hide-menu"> Xử lý âm thanh</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 1)}">
                    <li class="nav-label"><strong>Cuộc họp</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-meeting-list.html')}">
                    <li><a href="${meetingListUrl}" aria-expanded="false"><i class="fa fa-list-ul"></i><span
                            class="hide-menu"> Quản lý cuộc họp</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-session-list.html')}">
                    <li><a href="${sessionListUrl}" aria-expanded="false"><i class="fa fa-list-alt"></i><span
                            class="hide-menu"> Quản lý phiên họp</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 2)}">
                    <li class="nav-label"><strong>Biên bản</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/report-create.html')}">
                    <li><a href="${createReportUrl}" aria-expanded="false"><i class="fa fa-clipboard"></i><span
                            class="hide-menu"> Tạo biên bản</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/report-list.html')}">
                    <li><a href="${reportListUrl}" aria-expanded="false"><i class="fa fa-list-ul"></i><span
                            class="hide-menu"> Quản lý biên bản</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 3)}">
                    <li class="nav-label"><strong>Thống kê</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, statisticFileListUrl)}">

                    <li>
                        <a href="${statisticFileListUrl}" aria-expanded="false"><i class="fa fa-line-chart"></i><span
                                class="hide-menu"> Thống kê biên bản</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, statisticFileListUrl)}">
                    <li>
                        <a href="${statisticFileListUrl}" aria-expanded="false"><i class="fa fa-files-o"></i><span
                                class="hide-menu"> Thống kê files</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 4)}">
                    <li class="nav-label"><strong>Quản lý</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/history.html')}">

                    <li>
                        <a href="/history.html" aria-expanded="false"><i class="fa fa-history"></i><span
                                class="hide-menu"> Lịch sử</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-equipment.html')}">

                    <li>
                        <a href="${equipmentListUrl}" aria-expanded="false"><i class="fa fa-microchip"></i><span
                                class="hide-menu"> Quản lý thiết bị</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-speaker-list.html')}">

                    <li>
                        <a href="${speakerListUrl}" aria-expanded="false"><i class="fa fa-male"></i><span
                                class="hide-menu"> Quản lý người họp</span></a>
                    </li>
                </c:if>


                <c:if test="${fn:checkPermission(urlAllowedList, '/list-data.html')}">
                    <li>
                        <a href="list-data.html" aria-expanded="false"><i class="fa fa-database"></i><span
                                class="hide-menu"> Quản lý dữ liệu</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-template.html')}">
                    <li>
                        <a href="${templateListUrl}" aria-expanded="false"><i class="fa fa-book"></i><span
                                class="hide-menu"> Quản lý template</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/setting.html')}">
                    <li>
                        <a href="/setting.html" aria-expanded="false"><i class="fa fa-cogs"></i><span
                                class="hide-menu"> Cấu hình</span></a>
                    </li>
                </c:if>


                <c:if test="${fn:checkTitleMenu(urlAllowedList, 5)}">
                    <li class="nav-label"><strong>Tốc ký</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/typing-shorthand.html')}">
                    <li>
                        <a href="/typing-shorthand.html" aria-expanded="false"><i
                                class="fa fa-american-sign-language-interpreting" aria-hidden="true"></i><span
                                class="hide-menu"> Gõ tốc ký</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/steno-convert.html')}">
                    <li>
                        <a href="/steno-convert.html" aria-expanded="false"><i class="fa fa-exchange"
                                                                               aria-hidden="true"></i><span
                                class="hide-menu"> Chuyển đổi tốc ký</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/steno-file-manager.html')}">
                    <li>
                        <a href="/steno-file-manager.html" aria-expanded="false"><i class="fa fa-folder-open-o"
                                                                                    aria-hidden="true"></i></i><span
                                class="hide-menu"> Quản lý file tốc ký</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/word-manager.html')}">
                    <li>
                        <a href="/word-manager.html" aria-expanded="false"><i class="fa fa-file-word-o"></i><span
                                class="hide-menu"> Quản lý âm tiết</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/typing-rules.html')}">
                    <li>
                        <a href="/typing-rules.html" aria-expanded="false"><i class="fa fa-hand-lizard-o"
                                                                              aria-hidden="true"></i><span
                                class="hide-menu"> Quản lý quy tắc gõ</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-dict-steno-list.html')}">
                    <li>
                        <a href="/manager-dict-steno-list.html" aria-expanded="false"><i
                                class="fa fa-keyboard-o"></i><span
                                class="hide-menu"> Quản lý bộ từ điển</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-layout.html')}">
                    <li>
                        <a href="/manager-layout.html" aria-expanded="false"><i class="fa fa-file-word-o"></i><span
                                class="hide-menu"> Quản lý layout bàn phím</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 6)}">
                    <li class="nav-label"><strong>Cá nhân</strong></li>
                </c:if>
                <c:if test="${fn:checkPermission(urlAllowedList, '/personal-information.html')}">

                    <li>
                        <a href="/personal-information.html" aria-expanded="false"><i class="ti-user"></i><span
                                class="hide-menu"> Thông tin cá nhân</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-person-dict-steno.html')}">

                    <li>
                        <a href="/manager-person-dict-steno.html" aria-expanded="false"><i
                                class="fa fa-keyboard-o"></i><span
                                class="hide-menu"> Bộ từ điển cá nhân</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/steno-setting-oder.html')}">
                    <li>
                        <a href="/steno-setting-oder.html" aria-expanded="false"><i class="fa fa-anchor"
                                                                                    aria-hidden="true"></i>
                            <span
                                    class="hide-menu"> Thiết lập thứ tự ưu tiên</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 7)}">
                    <li class="nav-label"><strong>Quản trị</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-extend-permission.html')}">

                    <li>
                        <a href="/manager-extend-permission.html" aria-expanded="false"><i
                                class="fa fa-user-plus"></i><span
                                class="hide-menu"> Cấp quyền</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-user-list.html')}">
                    <li>
                        <a href="${userListUrl}" aria-expanded="false"><i class="fa fa-user"></i><span
                                class="hide-menu"> Quản trị người dùng</span></a>
                    </li>
                </c:if>


                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-permission.html')}">
                    <li>
                        <a href="/manager-permission.html" aria-expanded="false"><i class="fa fa-key"></i><span
                                class="hide-menu"> Quản trị vai trò</span></a>
                    </li>

                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/manager-module.html')}">

                    <li>
                        <a href="/manager-module.html" aria-expanded="false"><i class="fa fa-modx"
                                                                                aria-hidden="true"></i><span
                                class="hide-menu"> Quản trị module</span></a>
                    </li>
                </c:if>

                <c:if test="${fn:checkTitleMenu(urlAllowedList, 8)}">
                    <li class="nav-label"><strong>Thông tin</strong></li>
                </c:if>

                <c:if test="${fn:checkPermission(urlAllowedList, '/info.html')}">
                    <li>
                        <a href="/info.html" aria-expanded="false"><i class="fa fa-map-marker"></i><span
                                class="hide-menu"> Thông tin hệ thống</span></a>
                    </li>
                </c:if>


                <c:if test="${fn:checkPermission(urlAllowedList, '/qna.html')}">
                    <li>
                        <a href="/qna.html" aria-expanded="false"><i class="fa fa-map-marker"></i><span
                                class="hide-menu"> Câu hỏi thường gặp</span></a>
                    </li>
                </c:if>

            </ul>
        </nav>
        <!-- End Sidebar navigation -->
    </div>
    <!-- End Sidebar scroll-->
</div>
<!-- End Left Sidebar -->