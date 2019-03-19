-- Table: "TB_DEVICE"

-- DROP TABLE "TB_DEVICE";

CREATE TABLE "TB_DEVICE"
(
  "ID" character varying(255) NOT NULL, -- 数据库物理主键
  "RESOURCE_ID" character varying(255), -- 设备资源ID
  "IS_BLACKLIST_ON" character varying(10), -- 是否开启黑名单功能
  "INDEXCODE" character varying(255), -- 设备编号
  cn character varying(255),
  device_type character varying(255),
  device_model character varying(255),
  ip character varying(255),
  port character varying(255),
  password_strength integer,
  CONSTRAINT "T_DEVICE_pkey" PRIMARY KEY ("ID"),
  CONSTRAINT device_uniq_key UNIQUE ("RESOURCE_ID"),
  CONSTRAINT device_uniq_key1 UNIQUE ("INDEXCODE"),
  CONSTRAINT "IS_BLACKLIST_ON" CHECK ("IS_BLACKLIST_ON"::text = 'on'::text OR "IS_BLACKLIST_ON"::text = 'off'::text)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE "TB_DEVICE"
  OWNER TO postgres;
COMMENT ON TABLE "TB_DEVICE"
  IS '设备表';
COMMENT ON COLUMN "TB_DEVICE"."ID" IS '数据库物理主键';
COMMENT ON COLUMN "TB_DEVICE"."RESOURCE_ID" IS '设备资源ID';
COMMENT ON COLUMN "TB_DEVICE"."IS_BLACKLIST_ON" IS '是否开启黑名单功能';
COMMENT ON COLUMN "TB_DEVICE"."INDEXCODE" IS '设备编号';

