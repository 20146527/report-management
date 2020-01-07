INSERT INTO roles VALUE
	(1,'Admin','Quản trị viên'),
	(2,'Secrtary','Thư ký'),
    (3,'KTV ghi âm', 'Kỹ thuật viên ghi âm'),
    (4,'TKV','Tốc ký viên'),
    (5,'QLAT','Quản lý âm thanh'),
    (6,'QLTK','Quản lý tốc ký'),
    (7,'QLBB','Quản lý biên bản');
	
INSERT INTO user VALUE
	(null,'system','Account Default', 1,'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','abc@gmail.com','123456789','Default', '',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null,'nhungpham','Pham Thi Nhung', 0,'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','abc@gmail.com','0986680360','Hoang Văn Thai', '',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null,'datdom','Đạt Đom', 1,'8D969EEF6ECAD3C29A3A629280E686CF0C3F5D5A86AFF3CA12020C923ADC6C92','xyz@gmail.com','0986680360','Cu Chinh Lan', '',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');

INSERT INTO meeting VALUE
	(null, 'Meeting Default','Default','2019-05-27 07:30:00','2019-05-28 17:30:00','Default',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');

INSERT INTO dict_steno VALUE
	(null, 'dict',6608,'dict.json',1,0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');
	
INSERT INTO room VALUE
	(null, 'Phòng 301', 'Phòng tầng 3', 0),
	(null, 'Phòng 402', 'Phòng tầng 4', 0),
	(null, 'Phòng 503', 'Phòng tầng 5', 0);
    
INSERT INTO session VALUE
	(null, 1, 'Session Default',1 ,'2019-05-27 07:30:00','2019-05-28 17:30:00','Default', -1 , 1, '2018-06-28 07:30:00', 1, '2018-06-28 07:30:00'),
	(null, 1, 'Phiên thử nghiệm số 1',1 ,'2019-05-27 07:30:00','2019-05-28 17:30:00','Default', 0, 1, '2018-06-28 07:30:00', 1, '2018-06-28 07:30:00');

INSERT INTO speaker VALUE
	(null,'Le Duc Trung','chu tich ubnd',1,'trungld@gmail.com','0965648634','1965-05-23','chu tich',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null,'Le Minh Nguyet','giam doc vp thcb',0,'trungld@gmail.com','0965648634','1965-05-23','gd',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null,'Nguyen Van Thuan','thu ky',1,'trungld@gmail.com','0965648634','1965-05-23','tk',0,1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');
    
INSERT INTO audio_sample VALUE
	(null, 'Nghia 1', 'httpdkjfdlnofv', '10-20; 30-40', 0, 0, 1, 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'Trung 2', 'httpdkjfdlnofv', '10-20; 30-40', 0, 0, 1, 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');

INSERT INTO duty VALUE
	(null, 'chuToa', 'Chủ Tọa'),
	(null, 'thuKy', 'Thư Ký'),
	(null, 'thanhVien', 'Thành Viên');
    
INSERT INTO attendees_list VALUE
	(null, 1, 1, 1, 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 1, 2, 3, 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');

    
INSERT INTO record VALUE 
	(null, 1, 'Ban ghi demo 1', '23012', 'audiorecord/vnist.mp3', '5', '');
    
INSERT INTO stenography value
	(null, 1, 'Steno thu nghiem 1', 'steno', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 1, 'Steno demo 2', 'steno', 0, 1,'2018-06-28 07:31:00',1,'2018-06-28 07:31:00');
    
INSERT INTO template VALUE
	(null, 'Default', 14, 'time new roman', 'Content', 0, 1,'2018-06-28 07:31:00',1,'2018-06-28 07:31:00');
    
INSERT INTO report VALUE
	(null, 'Name', 1, 1, 1, 1, 1, 'content', 0, 1,'2018-06-28 07:31:00',1,'2018-06-28 07:31:00');


INSERT INTO equipment VALUE
	(null, 1, 'Máy ghi âm 01', 'Sony', 'Mô tả',1 , '2018-06-28');
    
INSERT INTO config VALUE
	(null, 'report', 'modeCreateReport', '', '0', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'templateDefault', '', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'fileNameReportStructure', '', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'numberOfSecretary', '', '2', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'fileNameTemplateStructure', '', '0', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'tag', '', 'Môi trường, Giao thông, Giáo dục', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'colorselector80', '', '73', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'colorselector50', '', '72', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'report', 'colorselector20', '', '47', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');
    
INSERT INTO config VALUE
	(null, 'audio', 'direction', 'Hướng thu âm ưu tiên', '0', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'numberOfMic', 'Số lượng micro', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'samplingFrequency', 'Tần số lấy mẫu', '1200', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'channel', 'Kênh âm thanh', '2', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'bitrate', 'Số bit mã hóa', '256', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxTimeRecord', 'Thời lượng ghi âm tối đa', '180', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxSizeRecord', 'Kích thước file tối đa', '2048', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'pathRecord', 'Đường dẫn lưu file', '/file/audiorecord', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'fileNameRecordStructure', 'Cấu trúc tên file', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxDurationStorageRecord', 'Thời hạn lưu trữ tối đa', '4', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
    
	(null, 'audio', 'pathTranscript', 'vị trí lưu file bóc tách', '/file/result24', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'fileNameStructureResult', 'Cấu trúc tên file xử lý', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxDurationStorageAudio', 'Thời lượng lưu trữ file audo xử lý', '4', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'encryptionMechanism', 'Cơ chế mã hóa file', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'numberOfMicSample', 'Số lượng micro thu âm lấy mẫu', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'samplingFrequencySample', 'Tần số lấy mẫu file sample', '1200', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'bitrateSample', 'Số bit mã hóa file mẫu', '256', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxTimeRecordSample', 'Thời lượng ghi âm file mẫu tối đa', '10', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'maxSizeRecordSample', 'Kích thước file mẫu tối đa', '1024', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'autoNoiseCancelling', 'Tự động lọc nhiễu', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'audio', 'autoEchoCancelling', 'Tự động lọc vang', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');
    
INSERT INTO config VALUE
	(null, 'steno', 'checkDictCustom', 'Cho phép dùng bộ gõ cá nhân', 'off', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'steno', 'dictPriority', 'Ưu tiên bộ gõ', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'steno', 'fileNameDictDefaultStructure', 'Cấu trúc tên dict default', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'steno', 'fileNameStenoStructure', 'Cấu trúc tên file tốc ký', '1', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),    
	(null, 'steno', 'pathSteno', 'Vị trí lưu file tốc ký', 'steno', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');    
    
INSERT INTO config VALUE
	(null, 'connect', 'api24-logopt', 'Đăng nhập 24', 'abc', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'connect', 'api24-token', 'Lấy access token 24', 'def', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'connect', 'api24-upload', 'Upload âm thanh', 'ghi', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),
	(null, 'connect', 'api24-meeting', 'Lấy tình trạng xử lý 24', 'jkl', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00'),    
	(null, 'connect', 'api24-fulltranscription', 'Lấy file bóc băng 24', 'mno', 0, 1,'2018-06-28 07:30:00',1,'2018-06-28 07:30:00');

INSERT INTO transcript VALUE 
	(NULL, '1', 'all', 'audiorecord/vnist.mp3', '', 'result24/Session1/Record1/All.json', 'result24/Session1/Record1/All.xml');    
    
INSERT INTO modules VALUE 
	(null,'Nhận dạng người nói nói','Modul nhận dạng người nói nói'),
	(null,'Nhận dạng lời nói','Modul nhận dạng lời nói'),
	(null,'Tốc Ký','Modul quản trị hệ thống tốc ký bao gồm chức năng và các file tốc ký'),
	(null,'Quản lý biên bản','Modul quản lý biên bản');
    
INSERT INTO operator VALUE 
	(1,'view',0,0,1,0,0),
	(2,'view create',1,0,1,0,0),
	(3,'view create edit',1,1,1,0,0),
	(4,'full',1,1,1,1,0);
    
INSERT INTO object VALUE 
	(1,1,'Trang chủ','/home.html',0,'1',0),
	(2,1,'Ghi âm cuộc hop','/manager-record.html,/ajax-record.html',0,'1',0),
	(3,1,'Quản lý file ghi âm','/manager-audio.html',0,'1',0),
	(4,1,'Xử lý âm thanh','/report-transcript.html,/ajax-transcript.html',0,'1',0),
	(5,4,'Quản lý cuộc họp','/manager-meeting-list.html,/ajax-meeting-edit.html',0,'1',0),
	(6,4,'Quản lý phiên họp','/manager-session-list.html,/manager-session-detail.html,/manager-session-detail.html,/ajax-session-edit.html,/manager-session-import.html',0,'1',0),
	
    (7,4,'Tạo biên bản','/report-create.html,/ajax-create-report.html',0,'1',0),
	(8,4,'Quản lý biên bản','/report-list.html,/report-edit.html,/ajax-report-edit.html',0,'1',0),
	(9,4,'Thống kê','',0,'1',0),
	(10,4,'Lịch sử','',0,'1',0),
    
	(11,4,'Quản lý thiết bị','/manager-equipment.html,/ajax-equipment-edit.html',0,'1',0),
	(12,4,'Quản lý người họp','/manager-speaker-list.html,/ajax-speaker-edit.html,/ajax-audio-sample.html',0,'1',0),
	(13,4,'Quản lý dữ liệu','',0,'1',0),
	(14,4,'Quản lý template','/manager-template.html,/ajax-template-edit.html',0,'1',0),
	(18,4,'Cấu hình','/setting.html,/ajax-setting.html',0,'1',0),
	

	(19,3,'Gõ tốc ký','/typing-shorthand.html',0,'1',0),
	(20,3,'Chuyển đổi tốc ký','/steno-convert.html,/steno-display-convert.html',0,'1',0),
	(21,3,'Quản lý file tốc ký','/steno-file-manager.html,/steno-session-list-file.html',0,'1',0),
	(22,3,'Quản lý âm tiết','/word-manager.html',0,'1',0),
	(23,3,'Quản lý quy tắc gõ','/typing-rules.html,/typing-detail.html',0,'1',0),
	(24,3,'Quản lý bộ từ điển','/manager-dict-steno-list.html',0,'1',0),
	(25,3,'Quản lý layout bàn phím','/manager-layout.html',0,'1',0),
    
	(26,4,'Thông tin cá nhân','',0,'1',0),
	(27,3,'Bộ từ điển cá nhân','/manager-person-dict-steno.html',0,'1',0),
	(28,3,'Thiết lập thứ tự ưu tiên bộ luật cá nhân','/steno-setting-oder.html',0,'1',0),
    
	(29,4,'Cấp quyền','/manager-extend-permission.html',0,'1',0),
	(30,4,'Quản trị người dùng','/manager-user-list.html,/ajax-user-edit.html',0,'1',0),
	(31,4,'Quản trị vai trò','/manager-permission.html,/ajax-permission.html',0,'1',0),
	(32,4,'Quản trị module','/manager-module.html',0,'1',0);
    
INSERT INTO role_module VALUE
	(null,4,1),
	(null,4,2),
	(null,4,7);
    
INSERT INTO role_module VALUE
	(null,1,3),
	(null,3,4),
	(null,1,5),
	(null,3,6);
    
INSERT INTO role_permission VALUE
	(null,1,1,4),
	(null,1,2,4),
	(null,1,3,4),
	(null,1,4,4),
	(null,1,5,4),
	(null,1,6,4),
	(null,1,7,4),
	(null,1,8,4),
	(null,1,9,4),
	(null,1,10,4),
	(null,1,11,4),
	(null,1,12,4),
	(null,1,13,4),
	(null,1,14,4),
	(null,1,18,4),
	(null,1,19,4),
	(null,1,20,4),
	(null,1,21,4),
	(null,1,22,4),
	(null,1,23,4),
	(null,1,24,4),
	(null,1,25,4),
	(null,1,26,4),
	(null,1,27,4),
	(null,1,28,4),
	(null,1,29,4),
	(null,1,30,4),
	(null,1,31,4),
	(null,1,32,4),
	(null,2,1,4),
	(null,2,5,4),
	(null,2,6,4),
	(null,2,8,4);
    
INSERT INTO user_role VALUE
	(null,1,1),
	(null,2,2),
	(null,3,1);
	
INSERT INTO user_module VALUE
	(null,1,1),
	(null,4,1),
	(null,4,2),
	(null,1,3),
	(null,4,3);
    
INSERT INTO notification value
	(null,3,'Từ chối quyền', 'Cấp quyền cho file âm thanh: cskdjowefdf.mp3','/manager-extend-permission.html',51,2,'2019-12-17 07:30:00'),
	(null,3,'Đồng ý quyền', 'Cấp quyền cho file âm thanh: cskdjowefdf.mp3','/manager-extend-permission.html',51,2,'2019-12-17 07:30:00'),
	(null,3,'Yêu cầu cấp quyền', 'Cấp quyền cho file âm thanh: cskdjowefdf.mp3','/manager-extend-permission.html',51,2,'2019-12-17 07:30:00'),
	(null,3,'Thay đổi quyền', 'Bạn đã được cấp quyền truy cập file: cskdjowefdf.mp3','/manager-extend-permission.html',51,2,'2019-12-17 09:30:00');