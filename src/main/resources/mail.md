配置邮箱
        1.邮箱表设计
        2.添加邮箱
        3.配置日志发送邮箱(可配置多个)
        4.
create table km_mail.mail_info(
id serial primary key,
mail_url varchar(100),--邮箱地址
mail_user_id int , --配置邮箱归属人可以为空
status int  ,--状态 0无效 1 有效 2 删除
create_time timestamp
)