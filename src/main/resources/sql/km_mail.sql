create table km_mail.mail_send_log(
			mail_id serial primary key ,
			mail_uuid varchar(50),----记录消息体的id
			mail_content text,----记录消息体具体发送的内容
			mail_time  timestamp,--记录时间信息
			mail_log_info text --返回具体消息信息
			);