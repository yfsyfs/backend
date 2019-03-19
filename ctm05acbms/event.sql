--参数配置表
create table tb_param_config
(
    param_config_id varchar(48) not null,
    param_name varchar(64) not null,
    param_code varchar(64) not null,
    param_value varchar(128),
    remark varchar(128),
    constraint pk_tb_param_config primary key (param_config_id)
);
comment on table tb_param_config is '参数配置表';
comment on column tb_param_config.param_config_id is '主键id';
comment on column tb_param_config.param_name is '参数名称';
comment on column tb_param_config.param_code is '参数编码';
comment on column tb_param_config.param_value is '参数值';
comment on column tb_param_config.remark is '备注';


--事件表
DROP TABLE IF EXISTS TB_EVENT;
CREATE TABLE TB_EVENT (
ID text NOT NULL PRIMARY KEY,
IDNUM text,
NAME text,
HAPPEN_TIME timestamp,
HAPPEN_ADDRESS text,
EVENT_TYPE integer,
EVENT_NAME TEXT,
EVENT_PICTURE_URL text
);

comment on table tb_event is '事件表';
comment on column tb_event.ID is '主键';
comment on column tb_event.IDNUM is '证件号码';
comment on column tb_event.NAME is '姓名';
comment on column tb_event.HAPPEN_TIME is '事件时间';
comment on column tb_event.HAPPEN_ADDRESS is '门禁点';
comment on column tb_event.EVENT_TYPE is '事件类型编号';
comment on column tb_event.EVENT_NAME is '事件类型描述';
comment on column tb_event.EVENT_PICTURE_URL is '抓拍图片';

drop trigger if exists tri_ins_tb_event on tb_event;
drop function if exists func_tri_tb_event();
create or replace function func_tri_tb_event() returns trigger as
$$
declare my_tbname  varchar(64);
declare my_start_time  varchar(64);
declare my_end_time  varchar(64);
declare sql_str  text;
begin
    my_tbname = TG_TABLE_NAME || '_' || to_char(NEW.HAPPEN_TIME,'YYYYMM');
    sql_str = 'INSERT INTO '||my_tbname ||' SELECT $1.* ';
    EXECUTE sql_str USING NEW;
    return null;

    exception  when  undefined_table then
        begin
            my_start_time=to_char(NEW.HAPPEN_TIME,'YYYY-MM')||'-01 00:00:00';
            my_end_time=(my_start_time::timestamp(0)  + interval '1 month')::text;
            execute 'create table ' ||my_tbname || '(constraint ck_' || my_tbname || '_evtm' || ' check (HAPPEN_TIME >= ' || '''' ||my_start_time || '''' || ' and  HAPPEN_TIME < '||''''|| my_end_time || '''' || ')) INHERITS ('|| TG_TABLE_NAME || ')';
            execute 'alter  table ' ||my_tbname || ' add constraint pk_'||my_tbname||' primary key (id)';
            execute 'create index idx_'||my_tbname ||'_evtm on ' || my_tbname || ' (HAPPEN_TIME)';
            execute 'create index idx_'||my_tbname ||'_evty on ' || my_tbname || ' (event_type)';
            execute 'create index idx_'||my_tbname ||'_idnum on ' || my_tbname || ' (IDNUM)';
            execute 'create index idx_'||my_tbname ||'_name on ' || my_tbname || ' (name)';
						execute 'create index idx_'||my_tbname ||'_HAPPEN_ADDRESS on ' || my_tbname || ' (HAPPEN_ADDRESS)';
            EXECUTE sql_str USING NEW;
            return null;

            exception  when  others then
            EXECUTE sql_str USING NEW;
            return null;

        end;
end;
$$ language plpgsql;
create trigger tri_ins_tb_event BEFORE  insert on tb_event for each row EXECUTE PROCEDURE func_tri_tb_event();