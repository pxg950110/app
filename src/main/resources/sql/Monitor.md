sql   服务端内存监控
{"date":"2019-09-29",
"usedMemory":6185.65,
"totalMemory":8032.18,
"time":"18:53:48",
"freeMemory":1846.53}

create schema monitor;
create table monitor.tb_memory_record(
id serial primary key,
date_info varchar not null ,--日期
time_info varchar not null,--时间
total_memory double,--总内存
used_memory double,--已用内存
free_memory double  --空闲内存
)
