<%--
  Created by IntelliJ IDEA.
  User: Dattebayo
  Date: 16/04/2019
  Time: 9:53 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="editUserUrl" value="/ajax-user-edit.html">
    <c:param name="urlType" value="url_edit"/>
</c:url>
<c:url var="listUserUrl" value="/manager-user-list.html">
    <c:param name="urlType" value="url_list"/>
</c:url>
<html>
<head>
    <title>Thông tin hệ thống</title>
</head>
<body>

<!-- Bread crumb -->
<div class="row page-titles">
    <div class="col-md-5 align-self-center">
        <h3 class="text-primary">Dashboard</h3></div>
    <div class="col-md-7 align-self-center">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="home.html">Trang chủ</a></li>
            <li class="breadcrumb-item">Quản lý người dùng</li>
            <li class="breadcrumb-item active">Danh sách</li>
        </ol>
    </div>
</div>
<!-- End Bread crumb -->

<!-- Container fluid  -->
<div class="container-fluid">
    <!-- Start Page Content -->
    <div class="row">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <!-- Credit Card -->
                    <div class="card-body">
                        <div class="card-title">
                            <h3 class="text-center">Cài đặt hệ thống</h3>
                        </div>
                        <hr>
                        <div id="noti">

                        </div>
                        <ul class="nav nav-pills m-t-30 m-b-30">
                            <li class="nav-item"> <a href="#navpills-custom" class="nav-link" id="nav-custom" data-toggle="tab" aria-expanded="false">Cá nhân</a> </li>
                            <li class="nav-item"> <a href="#navpills-audio" class="nav-link" id="nav-audio" data-toggle="tab" aria-expanded="false">Âm thanh</a> </li>
                            <li class="nav-item"> <a href="#navpills-steno" class="nav-link" id="nav-steno" data-toggle="tab" aria-expanded="true">Tốc ký</a> </li>
                            <li class="nav-item"> <a href="#navpills-report" class="nav-link" id="nav-report" data-toggle="tab" aria-expanded="true">Biên bản</a> </li>
                            <li class="nav-item"> <a href="#navpills-member" class="nav-link" id="nav-member" data-toggle="tab" aria-expanded="true">Người họp</a> </li>
                            <li class="nav-item"> <a href="#navpills-connect" class="nav-link" id="nav-connect" data-toggle="tab" aria-expanded="true">Kết nối</a> </li>
                        </ul>
                        <div class="tab-content br-n pn">
                            <div id="navpills-custom" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-6">
                                    </div>
                                    <div class="col-lg-6">
                                    </div>

                                </div>
                            </div>
                            <div id="navpills-audio" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h3>Thông số ghi âm</h3>
                                        <hr>
                                        <form action="" method="POST">
                                            <div class="form-group">
                                                <label for="direction" class=" control-label">Hướng thu âm ưu tiên</label>

                                                <select name="direction" id="direction" class="form-control">
                                                    <option value="0">Đa hướng</option>
                                                    <option value="1">Hướng tập trung chủ tọa</option>
                                                    <option value="2">Hướng ưu tiên số 1</option>
                                                    <option value="3">Hướng ưu tiên số 2</option>
                                                    <option value="4">Hướng ưu tiên số 3</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="numberOfMic" class=" control-label">Số lượng micro</label>
                                                <input type="number" id="numberOfMic" name="numberOfMic" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="samplingFrequency" class=" control-label">Tần số lấy mẫu</label>
                                                <input type="number" id="samplingFrequency" name="samplingFrequency" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="channel" class=" control-label">Kênh âm thanh</label>
                                                <input type="number" id="channel" name="channel" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="bitrate" class=" control-label">Số bit mã hóa</label>
                                                <input type="number" id="bitrate" name="bitrate" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="maxTimeRecord" class=" control-label">Thời lượng ghi âm tối đa</label>
                                                <input type="number" id="maxTimeRecord" name="maxTimeRecord" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="maxSizeRecord" class=" control-label">Kích thước file tối đa</label>
                                                <input type="number" id="maxSizeRecord" name="maxSizeRecord" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="pathRecord" class=" control-label">Vị trí lưu file ghi âm</label>
                                                <div class="form-control" onclick="chooseFolderAudioRecord()">
                                                    <span id="pathRecord">Nhấn để chọn folder</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="fileNameRecordStructure" class=" control-label">Cấu trúc tên file ghi âm</label>
                                                <select name="fileNameRecordStructure" id="fileNameRecordStructure" class="form-control">
                                                    <option value="1">ID cuộc họp + ID phiên họp + ID file ghi</option>
                                                    <option value="2">ID cuộc họp + Tên phiên họp + Tên file ghi</option>
                                                    <option value="3">ID phiên họp + Tên file ghi</option>
                                                    <option value="4">ID phiên họp + ID file ghi</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="maxDurationStorageRecord" class=" control-label">Thời gian lưu trữ ghi âm tối đa</label>
                                                <select name="maxDurationStorageRecord" id="maxDurationStorageRecord" class="form-control">
                                                    <option value="0">Không giới hạn</option>
                                                    <option value="1">6 tháng</option>
                                                    <option value="2">1 năm</option>
                                                    <option value="3">2 năm</option>
                                                    <option value="4">3 năm</option>
                                                </select>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-lg-6">
                                        <h3>Thông số ghi file sample và xử lý</h3>
                                        <hr>
                                        <form action="" method="POST" enctype="multipart/form-data">
                                            <div class="form-group">
                                                <label for="pathTranscript" class=" control-label">Vị trí lưu file bóc tách</label>
                                                <div class="form-control" onclick="chooseFolderAudioTranscript()">
                                                    <span id="pathTranscript">Nhấn để chọn folder</span>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="fileNameStructureResult" class=" control-label">Cấu trúc tên file xử lý</label>
                                                <select name="fileNameStructureResult" id="fileNameStructureResult" class="form-control">
                                                    <option value="1">ID cuộc họp + ID phiên họp + ID người dùng</option>
                                                    <option value="2">ID cuộc họp + Tên phiên họp + ID người dùng</option>
                                                    <option value="3">ID phiên họp + ID người dùng</option>
                                                    <option value="4">Tên cuộc họp + ID phiên họp + ID người dùng</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="maxDurationStorageAudio" class=" control-label">Thời lượng lưu trữ tối đa</label>
                                                <select name="maxDurationStorageAudio" id="maxDurationStorageAudio" class="form-control">
                                                    <option value="0">Không giới hạn</option>
                                                    <option value="1">6 tháng</option>
                                                    <option value="2">1 năm</option>
                                                    <option value="3">2 năm</option>
                                                    <option value="4">3 năm</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="encryptionMechanism" class=" control-label">Cơ chế mã hóa file</label>
                                                <select name="encryptionMechanism" id="encryptionMechanism" class="form-control">
                                                    <option value="0">Không mã hóa</option>
                                                    <option value="1">Loại 1</option>
                                                    <option value="2">Loại 2</option>
                                                    <option value="3">Loại 3</option>
                                                    <option value="4">Loại 4</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="numberOfMicSample" class=" control-label">Số lượng micro thu âm lấy mẫu</label>
                                                <input type="number" id="numberOfMicSample" name="numberOfMicSample" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="samplingFrequencySample" class=" control-label">Tần số lấy mẫu file mẫu</label>
                                                <input type="number" id="samplingFrequencySample" name="samplingFrequencySample" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="bitrateSample" class=" control-label">Số bit mã hóa file mẫu</label>
                                                <input type="number" id="bitrateSample" name="bitrateSample" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="maxTimeRecordSample" class=" control-label">Thời lượng ghi âm mẫu tối đa</label>
                                                <input type="number" id="maxTimeRecordSample" name="maxTimeRecordSample" class="form-control">
                                            </div>
                                            <div class="form-group">
                                                <label for="maxSizeRecordSample" class=" control-label">Kích thước file ghi mẫu tối đa</label>
                                                <input type="number" id="maxSizeRecordSample" name="maxSizeRecordSample" class="form-control">
                                            </div>

                                            <div class="row">
                                                <div class="col-lg-8">
                                                    <h5>Tự động lọc nhiễu </h5>
                                                </div>
                                                <div class="col-lg-4">
                                                    <label class="switch control-label" for="autoNoiseCancelling">
                                                        <input type="checkbox" name="autoNoiseCancelling" id="autoNoiseCancelling" checked>
                                                        <span class="slider round"></span>
                                                    </label>
                                                </div>
                                                <div class="col-lg-8">
                                                    <h5>Tự động lọc vang </h5>
                                                </div>
                                                <div class="col-lg-4">
                                                    <label class="switch" for="autoEchoCancelling">
                                                        <input type="checkbox" name="autoEchoCancelling" id="autoEchoCancelling" checked>
                                                        <span class="slider round"></span>
                                                    </label>
                                                </div>
                                            </div>

                                            <button type="button" onclick="configAudio()"
                                                    class="btn btn-primary btn-rounded m-b-10 m-l-5">
                                                <i class="fa fa-save"></i> Lưu lại
                                            </button>
                                        </form>
                                    </div>

                                </div>
                            </div>
                            <div id="navpills-steno" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-3 col-md-1">
                                    </div>
                                    <div class="col-lg-6 col-md-10">
                                        <h3>Cấu hình tốc ký</h3>
                                        <hr>
                                        <form>
                                            <div class="row m-t-10">
                                                <div class="col-lg-8">
                                                    <label class="vertical-center">Cho phép dùng bộ gõ cá nhân </label>
                                                </div>
                                                <div class="col-lg-4">
                                                    <label class="switch control-label float-right" for="checkDictCustom">
                                                        <input type="checkbox" name="checkDictCustom" id="checkDictCustom" onchange="checkBoxDictPriority()">
                                                        <span class="slider round"></span>
                                                    </label>
                                                </div>
                                            </div>
                                            <div class="form-group m-t-10">
                                                <label for="dictPriority" class=" control-label">Ưu tiên bộ gõ</label>
                                                <select name="dictPriority" id="dictPriority" class="form-control">
                                                    <option value="1">Ưu tiên bộ gõ mặc định</option>
                                                    <option value="2">Ưu tiên bộ gõ cá nhân</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="fileNameDictDefaultStructure" class=" control-label">Cấu trúc tên bộ gõ mặc định</label>
                                                <select name="fileNameDictDefaultStructure" id="fileNameDictDefaultStructure" class="form-control">
                                                    <option value="1">Tạo tự do</option>
                                                    <option value="2">Tên người dùng _ Mốc thời gian</option>
                                                    <option value="3">ID người dùng _ Mốc thời gian</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="fileNameStenoStructure" class=" control-label">Cấu trúc tên file tốc ký</label>
                                                <select name="fileNameStenoStructure" id="fileNameStenoStructure" class="form-control">
                                                    <option value="1">Tạo tự do</option>
                                                    <option value="2">Tên người dùng _ Mốc thời gian</option>
                                                    <option value="3">ID người dùng _ Mốc thời gian</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="pathSteno" class=" control-label">Vị trí lưu file tốc ký</label>
                                                <div class="form-control" style="height: auto !important; padding-bottom: 9.5px; padding-top: 9.5px; padding-left: 15px" onclick="chooseFolderSteno()">
                                                    <span id="pathSteno">Nhấn để chọn folder</span>
                                                </div>
                                            </div>
                                            <button type="button" onclick="configSteno()"
                                                    class="btn btn-primary btn-rounded m-b-10 m-l-5">
                                                <i class="fa fa-save"></i> Lưu lại
                                            </button>
                                        </form>

                                    </div>
                                    <div class="col-lg-3 col-md-1">
                                    </div>
                                </div>
                            </div>
                            <div id="navpills-report" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-6 col-md-12">
                                        <h3>Cấu hình tạo biên bản</h3>
                                        <hr>
                                        <form>
                                            <div class="form-group">
                                                <label for="modeCreateReport" class=" control-label">Chế độ tạo biên bản mặc đinh</label>
                                                <select name="modeCreateReport" id="modeCreateReport" class="form-control">
                                                    <option value="0">Tạo biên bản kết hợp</option>
                                                    <option value="1">Sử dụng dữ liệu tốc ký</option>
                                                    <option value="2">Sử dụng dữ liệu bóc tách âm thanh</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="templateDefault" class=" control-label">Template mặc đinh</label>
                                                <select name="templateDefault" id="templateDefault" class="form-control">
                                                    <option value="0">aaaaaa</option>
                                                    <option value="1">Saaaaaa</option>
                                                    <option value="2">aaaaaaaa</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="fileNameReportStructure" class=" control-label">Cấu trúc tên file biên bản</label>
                                                <select name="fileNameReportStructure" id="fileNameReportStructure" class="form-control">
                                                    <option value="1">ID cuộc họp + ID phiên họp + ID người dùng</option>
                                                    <option value="2">ID cuộc họp + Tên phiên họp + ID người dùng</option>
                                                    <option value="3">ID phiên họp + ID người dùng</option>
                                                    <option value="4">Tên cuộc họp + ID phiên họp + ID người dùng</option>
                                                </select>
                                            </div>
                                            <div class="form-group">
                                                <label for="numberOfSecretary" class=" control-label">Số thư ký tối đa trong phiên họp</label>
                                                <input type="number" id="numberOfSecretary" name="numberOfSecretary" class="form-control">
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-lg-6 col-md-12">
                                        <h3>Cấu hình template và tag</h3>
                                        <hr>
                                        <div class="form-group">
                                            <label for="fileNameTemplateStructure" class=" control-label">Cấu trúc tên template khi tạo mới</label>
                                            <select name="fileNameTemplateStructure" id="fileNameTemplateStructure" class="form-control">
                                                <option value="0">Tạo tự do</option>
                                                <option value="1">Mốc thời gian + STT</option>
                                                <option value="2">ID người tải lên + Mốc thời gian + STT</option>
                                                <option value="3">Tên người tải lên + Mốc thời gian + STT</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label for="tag" class="control-label">Thẻ phân loại (Tag)</label>
                                            <div class="row">
                                                <div class="col-10">
                                                    <input type="text" id="tag" name="tag" value="${tag}"
                                                           placeholder="Nhập vào thẻ phân loại">
                                                </div>
                                                <div class="col-2">
                                                    <button type="button" name="submit" class="btn btn-danger tags--removeAllBtn vertical-center"
                                                            data-tooltip="Xóa hết tag">
                                                        <i class="fa fa-times"></i>&nbsp;
                                                    </button>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="colorGroup" class=" control-label">Tùy chỉnh màu sắc thể hiện độ chính xác</label>
                                            <div id="colorGroup" class="row">
                                                <div class="col-lg-4">
                                                    <h5>Đúng > 80%</h5>
                                                    <select id="colorselector80">
                                                        <option value="106" data-color="#fe9292">sienna</option>
                                                        <option value="47" data-color="#fe92eb">indianred</option>
                                                        <option value="87" data-color="#e892fe">orangered</option>
                                                        <option value="15" data-color="#ae92fe">crimson</option>
                                                        <option value="24" data-color="#92a7fe">darkorange</option>
                                                        <option value="78" data-color="#92e3fe">mediumvioletred</option>
                                                        <option value="74" data-color="#92fed4">mediumvioletred</option>
                                                        <option value="77" data-color="#92feae">mediumvioletred</option>
                                                        <option value="73" data-color="#c0fe92">mediumvioletred</option>
                                                        <option value="72" data-color="#fef892">mediumvioletred</option>
                                                        <option value="52" data-color="#fed992">mediumvioletred</option>
                                                        <option value="42" data-color="#92e1fe">mediumvioletred</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4">
                                                    <h5>Đúng > 50%</h5>
                                                    <select id="colorselector50">
                                                        <option value="106" data-color="#fe9292">sienna</option>
                                                        <option value="47" data-color="#fe92eb">indianred</option>
                                                        <option value="87" data-color="#e892fe">orangered</option>
                                                        <option value="15" data-color="#ae92fe">crimson</option>
                                                        <option value="24" data-color="#92a7fe">darkorange</option>
                                                        <option value="78" data-color="#92e3fe">mediumvioletred</option>
                                                        <option value="74" data-color="#92fed4">mediumvioletred</option>
                                                        <option value="77" data-color="#92feae">mediumvioletred</option>
                                                        <option value="73" data-color="#c0fe92">mediumvioletred</option>
                                                        <option value="72" data-color="#fef892">mediumvioletred</option>
                                                        <option value="52" data-color="#fed992">mediumvioletred</option>
                                                        <option value="42" data-color="#92e1fe">mediumvioletred</option>
                                                    </select>
                                                </div>
                                                <div class="col-lg-4">
                                                    <h5>Đúng < 20%</h5>
                                                    <select id="colorselector20">
                                                        <option value="106" data-color="#fe9292">sienna</option>
                                                        <option value="47" data-color="#fe92eb">indianred</option>
                                                        <option value="87" data-color="#e892fe">orangered</option>
                                                        <option value="15" data-color="#ae92fe">crimson</option>
                                                        <option value="24" data-color="#92a7fe">darkorange</option>
                                                        <option value="78" data-color="#92e3fe">mediumvioletred</option>
                                                        <option value="74" data-color="#92fed4">mediumvioletred</option>
                                                        <option value="77" data-color="#92feae">mediumvioletred</option>
                                                        <option value="73" data-color="#c0fe92">mediumvioletred</option>
                                                        <option value="72" data-color="#fef892">mediumvioletred</option>
                                                        <option value="52" data-color="#fed992">mediumvioletred</option>
                                                        <option value="42" data-color="#92e1fe">mediumvioletred</option>
                                                    </select>
                                                </div>
                                            </div>
                                        </div>
                                        <button type="button" onclick="configReport()"
                                                class="btn btn-primary btn-rounded m-b-10 m-l-5">
                                            <i class="fa fa-save"></i> Lưu lại
                                        </button>
                                    </div>
                                </div>
                            </div>
                            <div id="navpills-member" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-2 col-md-1">
                                    </div>
                                    <div class="col-lg-8 col-md-10">
                                        <form>
                                            <div class="row">
                                                <div class="col-lg-8">
                                                    <h5>Cho phép tự động nhập thông tin thành viên mặc định khi tạo phiên họp</h5>
                                                </div>
                                                <div class="col-lg-4">
                                                    <label class="switch control-label" for="checkMemberDefault">
                                                        <input type="checkbox" name="checkMemberDefault" id="checkMemberDefault" checked>
                                                        <span class="slider round"></span>
                                                    </label>
                                                </div>
                                            </div>

                                            <div class="table-responsive">
                                                <table class="table">
                                                    <thead>
                                                    <tr>
                                                        <th>ID</th>
                                                        <th>Họ tên</th>
                                                        <th>Tên khác</th>
                                                        <th>Chức vụ</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody id="line-member">
                                                    <tr>
                                                        <td>1</td>
                                                        <td>Kolor Tea Shirt For Man</td>
                                                        <td>January 22</td>
                                                        <td><span class="badge badge-primary">Sale</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>2</td>
                                                        <td>Kolor Tea Shirt For Women</td>
                                                        <td>January 30</td>
                                                        <td><span class="badge badge-success">Tax</span></td>
                                                    </tr>
                                                    <tr>
                                                        <td>3</td>
                                                        <td>Blue Backpack For Baby</td>
                                                        <td>January 25</td>
                                                        <td><span class="badge badge-danger">Extended</span></td>
                                                    </tr>
                                                    </tbody>
                                                </table>
                                            </div>

                                            <button type="button" onclick="addNewLine()"
                                                    class="btn btn-info btn-rounded m-b-10 m-l-5">
                                                <i class="fa fa-plus"></i> Thêm người tham gia mặc định
                                            </button>

                                            <button type="submit"
                                                    class="btn btn-primary btn-rounded m-b-10 m-l-5">
                                                <i class="fa fa-save"></i> Lưu lại
                                            </button>
                                        </form>
                                    </div>
                                    <div class="col-lg-2 col-md-1">
                                    </div>
                                </div>
                            </div>
                            <div id="navpills-connect" class="tab-pane">
                                <div class="row">
                                    <div class="col-lg-6">
                                        <h3>Hệ thống nhận dạng tiếng nói</h3>
                                        <hr>
                                        <form action="" method="POST">
                                            <div class="form-group">
                                                <label for="api24-logopt" class=" control-label">API đăng nhập</label>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input type="text" id="api24-logopt" name="api24-logopt" class="form-control"
                                                               placeholder="Nhập vào url API đăng nhập" >
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-outline-inverse" data-tooltip="Kiểm tra">
                                                            <i class="fa fa-check-circle"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="api24-token" class=" control-label">API lấy token</label>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input type="text" id="api24-token" name="api24-token" class="form-control"
                                                               placeholder="Nhập vào url API lấy token" >
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-outline-inverse" data-tooltip="Kiểm tra">
                                                            <i class="fa fa-check-circle "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="api24-upload" class=" control-label">API upload</label>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input type="text" id="api24-upload" name="api24-upload" class="form-control"
                                                               placeholder="Nhập vào url API upload âm thanh" >
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-outline-inverse" data-tooltip="Kiểm tra">
                                                            <i class="fa fa-check-circle "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="api24-meeting" class=" control-label">API xem thông tin</label>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input type="text" id="api24-meeting" name="api24-meeting" class="form-control"
                                                               placeholder="Nhập vào url API xem thông tin xử lý" >
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-outline-inverse" data-tooltip="Kiểm tra">
                                                            <i class="fa fa-check-circle "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="api24-fulltranscription" class=" control-label">API lấy dữ liệu bóc băng</label>
                                                <div class="row">
                                                    <div class="col-10">
                                                        <input type="text" id="api24-fulltranscription" name="api24-fulltranscription" class="form-control"
                                                               placeholder="Nhập vào url API lấy dữ liệu bóc băng" >
                                                    </div>
                                                    <div class="col-2">
                                                        <button type="button" class="btn btn-outline-inverse" data-tooltip="Kiểm tra">
                                                            <i class="fa fa-check-circle "></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </form>
                                    </div>
                                    <div class="col-lg-6">
                                        <h3>Hệ thống thu thập âm thanh</h3>
                                        <hr>
                                        <h3>Hệ thống xử lý tín hiệu tiếng nói</h3>
                                        <hr>
                                        <h3>Hệ thống định danh người nói</h3>
                                        <hr>
                                        <button type="button" onclick="configConnect()"
                                                class="btn btn-primary btn-rounded m-b-10 m-l-5">
                                            <i class="fa fa-save"></i> Lưu lại
                                        </button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>

        </div>
    </div>
</div>
<!-- Modal -->
<div class="modal fade" id="myModal">
    <div class="modal-dialog modal-xl">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modal-title-folder">Quản lý thư mục</h4>
            </div>

            <!-- Modal body -->
            <div class="modal-body" id="myModal-body">
                <div class="filemanager">

                    <div class="search">
                        <input type="search" placeholder="Nhập tên thư mục..."/>
                    </div>

                    <div class="breadcrumbs"></div>

                    <ul class="data"></ul>

                    <div class="nothingfound">
                        <div class="nofiles">
                        </div>
                        <div class="row text-center">
                            <span>Không còn thư mục con!</span>
                        </div>


                    </div>

                </div>
            </div>

            <!-- Modal footer -->
            <div class="modal-footer">
                <button class="btn btn-success" onclick="fnSelect()">Chọn</button>
                <button class="btn btn-info" onclick="fnAddNewFolder()">Thêm mới</button>
                <button type="button" class="btn btn-secondary" onclick="fnCloseModalFolder()">Close</button>
            </div>

        </div>
    </div>
</div>

<div class="modal fade" id="creatFolder">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Nhập tên muốn lưu</h4>
            </div>

            <form id="form-name-file" class="form-valide" method="post">
                <!-- Modal body -->
                <div class="modal-body">
                    <div class="form-validation">
                        <div class="form-group">
                            <label for="name-folder">Nhập tên thư mục</label>
                            <div>
                                <input type="text" class="form-control" id="name-folder" name="name-folder"
                                       placeholder="Nhập tên thư mục muốn tạo">
                            </div>

                            <input type="text" hidden name="data-path" id="data-path">
                            <input type="hidden" id="configType" name="configType" value="">
                        </div>


                    </div>
                </div>

                <!-- Modal footer -->
                <div class="modal-footer">
                    <button type="submit" class="btn btn-info">Lưu lại</button>
                    <button type="button" class="btn btn-danger" onclick="fnCloseModal()">Close</button>
                </div>
            </form>

        </div>
    </div>
</div>
<!-- End Modal -->

<script src="<c:url value='/template/js/lib/bootstrap/js/bootstrap-colorselector.js'/>"></script>
<script>
    document.title = "Cấu hình hệ thống";
    $(document).ready(function () {
        insertValue();
        checkBoxDictPriority();
        //$("#colorselector20").val("78").change();
        //$('#myModal').modal({backdrop: 'static', keyboard: false});
    });

    $('#colorselector80').colorselector();
    $('#colorselector50').colorselector();
    $('#colorselector20').colorselector();
    
    function configAudio() {
        let array = [];
        array.push(getObjConfig('direction', $('#direction').val()));
        array.push(getObjConfig('numberOfMic', $('#numberOfMic').val()));
        array.push(getObjConfig('samplingFrequency', $('#samplingFrequency').val()));
        array.push(getObjConfig('channel', $('#channel').val()));
        array.push(getObjConfig('bitrate', $('#bitrate').val()));
        array.push(getObjConfig('maxTimeRecord', $('#maxTimeRecord').val()));
        array.push(getObjConfig('maxSizeRecord', $('#maxSizeRecord').val()));
        array.push(getObjConfig('pathRecord', $('#pathRecord').text()));
        array.push(getObjConfig('fileNameRecordStructure', $('#fileNameRecordStructure').val()));
        array.push(getObjConfig('maxDurationStorageRecord', $('#maxDurationStorageRecord').val()));

        array.push(getObjConfig('pathTranscript', $('#pathTranscript').text()));
        array.push(getObjConfig('fileNameStructureResult', $('#fileNameStructureResult').val()));
        array.push(getObjConfig('maxDurationStorageAudio', $('#maxDurationStorageAudio').val()));
        array.push(getObjConfig('encryptionMechanism', $('#encryptionMechanism').val()));
        array.push(getObjConfig('numberOfMicSample', $('#numberOfMicSample').val()));
        array.push(getObjConfig('samplingFrequencySample', $('#samplingFrequencySample').val()));
        array.push(getObjConfig('bitrateSample', $('#bitrateSample').val()));
        array.push(getObjConfig('maxTimeRecordSample', $('#maxTimeRecordSample').val()));
        array.push(getObjConfig('maxSizeRecordSample', $('#maxSizeRecordSample').val()));
        array.push(getObjConfig('autoNoiseCancelling', $('#autoNoiseCancelling').val()));
        array.push(getObjConfig('autoEchoCancelling', $('#autoEchoCancelling').val()));
        let data = JSON.stringify(array);
        let url = '/ajax-setting.html';
        //console.log(data);

        $.ajax({
            url: url,
            type: 'post',
            data: {
                config: 'audio',
                data: data,
            },
            success: function (res) {
                console.log(res);
                showNoti('Cấu hình âm thanh', res);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    
    function configSteno() {
        let array = [];
        if($('#checkDictCustom').is(":checked")){
            array.push(getObjConfig('checkDictCustom', 'on'));
        }else {
            array.push(getObjConfig('checkDictCustom', 'off'));
        }
        array.push(getObjConfig('dictPriority', $('#dictPriority').val()));
        array.push(getObjConfig('fileNameDictDefaultStructure', $('#fileNameDictDefaultStructure').val()));
        array.push(getObjConfig('fileNameStenoStructure', $('#fileNameStenoStructure').val()));
        array.push(getObjConfig('pathSteno', $('#pathSteno').text()));

        //window.location.href = "/setting.html";
        let data = JSON.stringify(array);
        let url = '/ajax-setting.html';
        console.log(data);

        $.ajax({
            url: url,
            type: 'post',
            data: {
                config: 'steno',
                data: data,
            },
            success: function (res) {
                //console.log(res);
                showNoti('Cấu hình tốc ký', res);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }
    
    function configReport() {
        let array = [];
        array.push(getObjConfig('modeCreateReport', $('#modeCreateReport').val()));
        array.push(getObjConfig('templateDefault', $('#templateDefault').val()));
        array.push(getObjConfig('fileNameReportStructure', $('#fileNameReportStructure').val()));
        array.push(getObjConfig('numberOfSecretary', $('#numberOfSecretary').val()));
        array.push(getObjConfig('fileNameTemplateStructure', $('#fileNameTemplateStructure').val()));
        array.push(getObjConfig('tag', $('#tag').val()));
        array.push(getObjConfig('colorselector80', $('#colorselector80').val()));
        array.push(getObjConfig('colorselector50', $('#colorselector50').val()));
        array.push(getObjConfig('colorselector20', $('#colorselector20').val()));
        let data = JSON.stringify(array);
        let url = '/ajax-setting.html';
        //console.log(data);

        $.ajax({
            url: url,
            type: 'post',
            data: {
                config: 'report',
                data: data,
            },
            success: function (res) {
                console.log(res);
                showNoti('Cấu hình biên bản', res);
            },
            error: function (res) {
                console.log(res);
            }
        })

    }
    
    function configAttendees() {
        
    }

    function configConnect() {
        let array = [];
        array.push(getObjConfig('api24-logopt', $('#api24-logopt').val()));
        array.push(getObjConfig('api24-token', $('#api24-token').val()));
        array.push(getObjConfig('api24-upload', $('#api24-upload').val()));
        array.push(getObjConfig('api24-meeting', $('#api24-meeting').val()));
        array.push(getObjConfig('api24-fulltranscription', $('#api24-fulltranscription').val()));
        let data = JSON.stringify(array);
        let url = '/ajax-setting.html';
        //console.log(data);

        $.ajax({
            url: url,
            type: 'post',
            data: {
                config: 'connect',
                data: data,
            },
            success: function (res) {
                console.log(res);
                showNoti('Cấu hình kết nối', res);
            },
            error: function (res) {
                console.log(res);
            }
        })
    }

    function getObjConfig(title, value) {
        var data = {
            title: title,
            value: value
        }
        return data;
    }

    function insertValue() {
        var data = '${config}';
        //console.log("DATAAA: "+data);
        var listConfig = jQuery.parseJSON(data);
        $.each(listConfig, function (i, item) {
            var config = listConfig[i];
            var title = config.title;
            if(title === 'colorselector20' || title === 'colorselector50' || title === 'colorselector80'){
                let idElem = '#'+config.title;
                $(idElem).val(config.value).change();
            } else if(title === 'checkDictCustom'){
                showCheckbox(title, config.value);
            } else if(title === 'pathSteno' || title === 'pathRecord' || title === 'pathTranscript'){
                let idElem = '#'+config.title;
                $(idElem).text(config.value);
            } else {
                let idElem = '#'+config.title;
                $(idElem).val(config.value);
            }
        });
    }

    function showCheckbox(title, value) {
        if(value === 'on'){
            var idElem = '#'+title;
            $(idElem).attr('checked', true);
        }else {
            $(idElem).attr('checked', false);
        }
    }

    function showNoti(typeConfig, time) {
        $('#noti').append('<div class="alert alert-warning alert-dismissible fade show">\n' +
            '                   <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>\n' +
            '                   <span class="badge badge-pill badge-warning"><b>'+typeConfig+'</b></span>\n' +
            '                   <strong>Đã cập nhật cài đặt hệ thống ( '+time+' )</strong>\n' +
            '              </div>');
        $("html, body").animate({ scrollTop: 0 }, "slow");
    }
    
    function checkBoxDictPriority() {
        if($('#checkDictCustom').is(":checked")){
            $('#dictPriority').prop('disabled', false);
        }else {
            $('#dictPriority').prop('disabled', true);
        }
    }
</script>

<script>
    function addNewLine() {
        var tmp = "<tr>"
            +"<td>4</td>"
            +"<td>Kolor Tea Shirt For Man</td>"
            +"<td>January 30</td>"
            +"<td><span class='badge badge-danger'>Extended</span></td>"
            +"</tr>";
        $('#line-member').append(tmp);
    }
</script>
<script>
    //Auto complete Tag
    (function () {
        var input = document.querySelector('input[name=tag]'),

            // init Tagify script on the above inputs
            tagify = new Tagify(input, {
                // after 2 characters typed, all matching values from this list will be suggested in a dropdown
                whitelist: ["Môi trường", "Giáo dục", "Y tế"]
            })
        // "remove all tags" button event listener
        document.querySelector('.tags--removeAllBtn')
            .addEventListener('click', tagify.removeAllTags.bind(tagify))
        // Chainable event listeners
        tagify.on('add', onAddTag)
            .on('remove', onRemoveTag)
            .on('input', onInput)
            .on('edit', onTagEdit)
            .on('invalid', onInvalidTag)
            .on('click', onTagClick)
            .on('dropdown:show', onDropdownShow)
            .on('dropdown:hide', onDropdownHide)
            .on('dropdown:select', onDropdownSelect)

        // tag added callback
        function onAddTag(e) {
            tagify.off('add', onAddTag) // exmaple of removing a custom Tagify event
        }
        // tag remvoed callback
        function onRemoveTag(e) {
            //console.log(e.detail);
            //console.log("tagify instance value:", tagify.value)
        }

        // on character(s) added/removed (user is typing/deleting)
        function onInput(e) {
            //console.log(e.detail);
            //console.log("onInput: ", e.detail);
        }

        function onTagEdit(e) {
            //console.log("onTagEdit: ", e.detail);
        }

        // invalid tag added callback
        function onInvalidTag(e) {
            //console.log("onInvalidTag: ", e.detail);
        }

        // invalid tag added callback
        function onTagClick(e) {
            //console.log(e.detail);
            //console.log("onTagClick: ", e.detail);
        }

        function onDropdownShow(e) {
            //console.log("onDropdownShow: ", e.detail)
        }

        function onDropdownHide(e) {
            //console.log("onDropdownHide: ", e.detail)
        }

        function onDropdownSelect(e) {
            //console.log("onDropdownSelect: ", e.detail)
        }
    })()
</script>
<script>
    var output;
    var configType = '${type}';
    var dataFolder = '${folder_tree}';

    $(document).ready(function () {
        activeTab();
        if (window.location.href.indexOf("#file") > -1) {
            var dataFolder = '${folder_tree}';
            $("#myModal").modal("show");
            initFileBrower(dataFolder);
        }
    });

    function activeTab() {
        if(configType === 'custom'){
            $('#nav-custom').addClass("active");
            $('#navpills-custom').addClass("active");
        }else if(configType === 'audio'){
            $('#nav-audio').addClass("active");
            $('#navpills-audio').addClass("active");
        }
        else if(configType === 'audioRecord'){
            $('#nav-audio').addClass("active");
            $('#navpills-audio').addClass("active");
            output = $('#pathRecord');
        }else if(configType === 'audioTranscript'){
            $('#nav-audio').addClass("active");
            $('#navpills-audio').addClass("active");
            output = $('#pathTranscript');
        }
        else if(configType === 'steno'){
            $('#nav-steno').addClass("active");
            $('#navpills-steno').addClass("active");
            output = $('#pathSteno');
        }else if(configType === 'report'){
            $('#nav-report').addClass("active");
            $('#navpills-report').addClass("active");
        }else {
            $('#nav-member').addClass("active");
            $('#navpills-member').addClass("active");
        }
    }

    function chooseFolderSteno() {
        //var dataFolder = '${folder_tree}';
        output = $('#pathSteno');
        $('#configType').val('steno');
        //window.location.href = "/setting.html#file";
        $("#myModal").modal("show");
        initFileBrower(dataFolder);
    }
    
    function chooseFolderAudioRecord() {
        output = $('#pathRecord');
        $('#configType').val('audioRecord');
        $("#myModal").modal("show");
        initFileBrower(dataFolder);
    }
    
    function chooseFolderAudioTranscript() {
        output = $('#pathTranscript');
        $('#configType').val('audioTranscript');
        $("#myModal").modal("show");
        initFileBrower(dataFolder);
    }

    function fnSelect() {
        $(output).text(getPath());
        fnCloseModalFolder();
    }
    function getPath() {
        let obj = $(".breadcrumbs").find('.folderName');
        let name = '';
        for (let i = 1; i < obj.length; i++) {
            if (i === 1) {
                name = obj[i].innerText;
            } else {
                name = name + "\\" + obj[i].innerText;
            }
        }
        return name;
    }
    function fnAddNewFolder() {
        let myModal = $("#myModal");
        myModal.modal("toggle");

        let creatFolder = $("#creatFolder");
        creatFolder.modal('show');

        $("#data-path").val(getPath());
    }

    function fnCloseModal() {
        $("#creatFolder").modal('toggle');
        if (window.location.href.indexOf("#file") > -1) {
            $("#myModal").modal("show");
        }
    }

    function fnOpenModal() {
        window.location.href = "/setting.html#file";
        $("#myModal").modal("show");
    }

    function fnCloseModalFolder() {
        $("#myModal").modal('toggle');
        window.location.href = "/setting.html#";
    }
</script>
<script src="<c:url  value="/template/js/file-browser/init.js"/>"></script>
<script>

    var form_validation = function () {
        var e = function () {
            $.validator.addMethod("regexp", function (value, element) {
                return this.optional(element) || !/[\\/"?*<>|:]/.test(value);
            }, 'Kiểm tra lại tên');


            $.validator.addMethod("checkname", function (value, element) {
                let bool = true;
                let obj = $("li.folders").find('.name');
                for (let i = 0; i < obj.length; i++) {
                    let name = obj[i].innerText;
                    if (name === value) {
                        bool = false;
                        break;
                    }
                }
                return this.optional(element) || bool;

            }, 'Kiểm tra lại tên');

            $(".form-valide").validate({
                ignore: [],
                errorClass: "invalid-feedback animated fadeInDown",
                errorElement: "div",
                errorPlacement: function (e, a) {
                    $(a).parents(".form-group > div").append(e)
                },
                highlight: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid").addClass("is-invalid")
                },
                success: function (e) {
                    $(e).closest(".form-group").removeClass("is-invalid"), $(e).remove()
                },
                rules: {
                    "name-folder": {
                        required: true,
                        regexp: true,
                        checkname: true
                    }
                },
                messages: {
                    "name-folder": "Tên nhập vào không hợp lệ"
                }
            })
        };
        return {
            init: function () {
                e(), $(".js-select2").on("change", function () {
                    $(this).valid()
                })
            }
        }
    }();
    $(function () {
        form_validation.init();
    });

</script>
</body>
</html>
