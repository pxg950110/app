create table km_mail.mail_send_log(
			mail_id serial primary key ,
			mail_uuid varchar(50),----记录消息体的id
			mail_content text,----记录消息体具体发送的内容
			mail_time  timestamp,--记录时间信息
			mail_log_info text --返回具体消息信息
			);


--日志建表语句
create table applog.server_log_info
(
    log_id            serial primary key,
    log_message_id    varchar(100),
    log_level         varchar(100),
    log_location      varchar(500),
    log_thread        varchar(100),
    log_category_name varchar(500),
    log_time          timestamp,
    log_info          text,
    log_content_type  varchar(100),
    create_time       timestamp
)