-- SYS_GROUP
create table SYS_GROUP
(
  GROUP_ID   VARCHAR2(32) default sys_guid() not null,
  GROUP_NAME VARCHAR2(100) not null,
  GROUP_NO   VARCHAR2(10),
  REMARK     VARCHAR2(500),
  ADD_TIME   DATE default sysdate
);

comment on table SYS_GROUP
  is '系统用户组表';
comment on column SYS_GROUP.GROUP_ID
  is '组ID';
comment on column SYS_GROUP.GROUP_NAME
  is '组名';
comment on column SYS_GROUP.REMARK
  is '备注';
comment on column SYS_GROUP.ADD_TIME
  is '添加时间';
comment on column SYS_GROUP.GROUP_NO
  is '组编号';
  
alter table SYS_GROUP
  add constraint PK_SYS_GROUP primary key (GROUP_ID);
  
  
-- SYS_MENU
create table SYS_MENU
(
  MENU_ID        VARCHAR2(32) default sys_guid() not null,
  MENU_NAME      VARCHAR2(100) not null,
  MENU_URL       VARCHAR2(100),
  PARENT_MENU_ID VARCHAR2(32),
  UPDATE_TIME    DATE default sysdate
);

comment on table SYS_MENU
  is '系统菜单表';
comment on column SYS_MENU.MENU_ID
  is 'ID、主键';
comment on column SYS_MENU.MENU_NAME
  is '菜单名';
comment on column SYS_MENU.MENU_URL
  is '菜单对应的URL';
comment on column SYS_MENU.PARENT_MENU_ID
  is '父菜单';
  
alter table SYS_MENU
  add constraint PK_SYS_MENU primary key (MENU_ID);
  
  
-- SYS_ROLE
create table SYS_ROLE
(
  ROLE_ID     VARCHAR2(32) default sys_guid() not null,
  ROLE_NAME   VARCHAR2(50),
  REMARK      VARCHAR2(500),
  UPDATE_TIME DATE default sysdate
);

comment on table SYS_ROLE
  is '系统用户角色表';
comment on column SYS_ROLE.ROLE_ID
  is 'ID、主键';
comment on column SYS_ROLE.ROLE_NAME
  is '角色名';
comment on column SYS_ROLE.REMARK
  is '备注';
  
alter table SYS_ROLE
  add constraint PK_SYS_ROLE primary key (ROLE_ID);
  
-- SYS_ROLE_MENU
create table SYS_ROLE_MENU
(
  ID      VARCHAR2(32) default sys_guid() not null,
  ROLE_ID VARCHAR2(32),
  MENU_ID VARCHAR2(32)
);

comment on table SYS_ROLE_MENU
  is '角色菜单表';
comment on column SYS_ROLE_MENU.ID
  is '主键';
comment on column SYS_ROLE_MENU.ROLE_ID
  is '角色ID，对应角色表';
comment on column SYS_ROLE_MENU.MENU_ID
  is '菜单ID，对应菜单表';

alter table SYS_ROLE_MENU
  add constraint PK_SYS_ROLE_MENU primary key (ID);

-- SYS_USER
create table SYS_USER
(
  USER_ID  VARCHAR2(32) default sys_guid() not null,
  USERNAME VARCHAR2(50),
  PASSWORD VARCHAR2(40),
  NAME     VARCHAR2(50),
  ROLE_ID  VARCHAR2(32),
  GROUP_ID VARCHAR2(32),
  ADD_TIME DATE default sysdate not null
);

comment on table SYS_USER
  is '系统用户表';
comment on column SYS_USER.USER_ID
  is 'ID、主键';
comment on column SYS_USER.USERNAME
  is '用户名、登录名';
comment on column SYS_USER.PASSWORD
  is '密码（MD5存储）';
comment on column SYS_USER.NAME
  is '姓名';
comment on column SYS_USER.ROLE_ID
  is '角色ID';
comment on column SYS_USER.GROUP_ID
  is '组ID（可作为部门标识）';
comment on column SYS_USER.ADD_TIME
  is '添加时间';

alter table SYS_USER
  add constraint PK_SYS_USER primary key (USER_ID);
alter table SYS_USER
  add constraint IDX_SYS_USER_UNI_USERNAME unique (USERNAME);
