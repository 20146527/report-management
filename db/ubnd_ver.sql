
CREATE TABLE duty (
	Duty_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Duty_Name varchar(25) NOT NULL,
	Description varchar(100)
);


CREATE TABLE room (
	Room_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Room_Name varchar(200) NOT NULL,
    Description varchar(500),
    Status int NOT NULL
);

CREATE TABLE user (
	User_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	User_Name varchar(100) NOT NULL,
	Full_Name varchar(250) NOT NULL,
    Gender int NOT NULL,
	Password varchar(200) NOT NULL,
    Email varchar(200),
    Phone varchar(20),
    Address varchar(250),
    ava_path Text,
	Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE speaker (
	Speaker_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Full_Name varchar(250) NOT NULL,
	Other_Name  varchar(250),
	Gender int,
    Email varchar(200),
    Phone varchar(15),
    Birthday varchar(10),
    Regency varchar(500),
	Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE audio_sample (
	Audio_Sample_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name varchar(250) NOT NULL,
	Path_Audio text NOT NULL,
	Time_Index text,
    Size float,
	Sys int(1),
	Speaker_ID int NOT NULL,
	Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL,
	FOREIGN KEY (Speaker_ID) REFERENCES speaker(Speaker_ID)
);


CREATE TABLE meeting (
	Meeting_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name text NOT NULL,
	Address text,
	Time_Start Timestamp,
	Time_End Timestamp,
	Description text,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE session (
	Session_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Meeting_ID int NOT NULL,
	Name text NOT NULL,
	Room_ID int NOT NULL,
	Time_Start Timestamp,
	Time_End Timestamp,
	Description text,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL,
    FOREIGN KEY (Meeting_ID) REFERENCES meeting(Meeting_ID),
    FOREIGN KEY (Room_ID) REFERENCES room(Room_ID)
);

CREATE TABLE attendees_list (
	Attendees_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Session_ID int NOT NULL,
	Speaker_ID int NOT NULL,
    Duty_ID int NOT NULL,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL,
    FOREIGN KEY (Duty_ID) REFERENCES duty(Duty_ID),
    FOREIGN KEY (Session_ID) REFERENCES session(Session_ID),
    FOREIGN KEY (Speaker_ID) REFERENCES speaker(Speaker_ID)
);

CREATE TABLE stenography (
	Steno_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Session_ID int NOT NULL,
    Steno_Name varchar(250) NOT NULL,
    Content text,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL,
    FOREIGN KEY (Session_ID) REFERENCES session(Session_ID)
);

CREATE TABLE dict_steno (
	Dict_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Dict_Name text NOT NULL,
	Length int NOT NULL,
    Content text NOT NULL,
    Dict_Default int(1) NOT NULL,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE record (
	Record_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Session_ID int NOT NULL,
	Name varchar(500) NOT NULL,
    Length float,
    Path text NOT NULL,
    Status varchar(10) NOT NULL,
    Processing_Info text,
    FOREIGN KEY (Session_ID) REFERENCES session(Session_ID)
);

CREATE TABLE transcript (
	Transcript_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Record_ID int NOT NULL,
    Speaker_ID varchar(100),
    Audio_Path text,
    Content text,
    Json_Path text,
    XML_Path text,
    FOREIGN KEY (Record_ID) REFERENCES record(Record_ID)
);

CREATE TABLE template (
	Template_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	Name varchar(250) NOT NULL,
	Font_Size int(5),
	Font varchar(50),
    Content text,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE report (
	Report_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Name varchar(500) NOT NULL,
    Session_ID int NOT NULL,
	Template_ID int,
    Record_ID int,
    Steno_ID int,
    Tag text,
	Content text,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL,
    FOREIGN KEY (Session_ID) REFERENCES session(Session_ID),
    FOREIGN KEY (Template_ID) REFERENCES template(Template_ID),
    FOREIGN KEY (Record_ID) REFERENCES record(Record_ID),
    FOREIGN KEY (Steno_ID) REFERENCES stenography(Steno_ID)
);

CREATE TABLE config (
	Config_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Type varchar(30) NOT NULL,
    Title varchar(100) NOT NULL,
    Description varchar(250) NOT NULL,
    Value text NOT NULL,
    Status int NOT NULL,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
	Mod_UID int NOT NULL,
	Mod_Date Timestamp NOT NULL
);

CREATE TABLE equipment (
	Equipment_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Room_ID int NOT NULL,
    Name varchar(250),
    Brand varchar(250),
    Description varchar(500),
    Status int,
    Day_Start varchar(10),
    FOREIGN KEY (Room_ID) REFERENCES room(Room_ID)
);


CREATE TABLE roles(
	Role_ID int auto_increment primary key,
    Role_Name varchar(100) not null,
    Description  text null
);


CREATE TABLE modules(
	Module_ID int auto_increment primary key,
    Name varchar(250) not null,
    Description  text null
);

CREATE TABLE object(
	Object_ID int auto_increment primary key,
    Module_ID int not null,
    Name_Object varchar(250) not null,
    Description  text null,
    Locked int not null,
    Type_Object int not null,
    Link_ID int,
    foreign key (Module_ID) references modules(Module_ID)
);

CREATE TABLE operator(
	Operator_ID int auto_increment primary key,
    Name varchar(250) not null,
    _create int,
    _edit int,
    _view int,
    _delete int,
    Locked int not null
);


CREATE TABLE user_module(
	User_Module_ID int auto_increment primary key,
	Module_ID int not null,
	User_ID int not null,
    foreign key (User_ID) references user(User_ID),
	foreign key (Module_ID) references modules(Module_ID)
);


CREATE TABLE role_module(
	Role_Module_ID int auto_increment primary key,
	Module_ID int not null,
	Role_ID int not null,
	foreign key (Role_ID) references roles(Role_ID),
    foreign key (Module_ID) references modules(Module_ID)
);

CREATE TABLE user_role(
	User_Role_ID int auto_increment primary key,
	User_ID int not null,
    Role_ID int not null,
	foreign key (User_ID) references user(User_ID),
	foreign key (Role_ID) references roles(Role_ID)
);

CREATE TABLE role_permission(
	Role_Permission_ID int auto_increment primary key,
    Role_ID int not null,
    Object_ID int not null,
    Operator_ID int not null,
	foreign key (Role_ID) references roles(Role_ID),
    foreign key (Object_ID) references object(Object_ID),
	foreign key (Operator_ID) references operator(Operator_ID)  
);

CREATE TABLE extend_permission_user (
	Extend_Permission_User_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
	User_ID int NOT NULL,
	Object_ID int not null,
    Operator_ID int not null,
    Count int NOT NULL,
    Request_Tag int,
	Cre_UID int NOT NULL,
	Cre_Date Timestamp NOT NULL,
    FOREIGN KEY (User_ID) REFERENCES user(User_ID),
    foreign key (Object_ID) references object(Object_ID),
    foreign key (Operator_ID) references operator(Operator_ID)  
);

CREATE TABLE notification (
	Notification_ID int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    User_ID int NOT NULL,
    Title varchar(100) NOT NULL,
    Content varchar (250) NOT NULL,
    Link text,
    Type_Noti int,
    Link_ID int,
    Cre_Date Timestamp
);
