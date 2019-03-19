-- Table: tb_param_config

-- DROP TABLE tb_param_config;

CREATE TABLE tb_param_config
(
  param_config_id character varying(48) NOT NULL, -- 主键id
  param_name character varying(64) NOT NULL, -- 参数名称
  param_code character varying(64) NOT NULL, -- 参数编码
  param_value character varying(128), -- 参数值
  remark character varying(128), -- 备注
  CONSTRAINT pk_tb_param_config PRIMARY KEY (param_config_id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tb_param_config
  OWNER TO postgres;
COMMENT ON TABLE tb_param_config
  IS '参数配置表';
COMMENT ON COLUMN tb_param_config.param_config_id IS '主键id';
COMMENT ON COLUMN tb_param_config.param_name IS '参数名称';
COMMENT ON COLUMN tb_param_config.param_code IS '参数编码';
COMMENT ON COLUMN tb_param_config.param_value IS '参数值';
COMMENT ON COLUMN tb_param_config.remark IS '备注';

