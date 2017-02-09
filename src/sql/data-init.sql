-- init sys_menu
insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('6E62020D770346F08A4287FF205EEB8F', '机构管理', '/app/page/sysgroup', '65E16E3E1EFC484A8CBD2AD77D8D3F92');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('8C95B11FF4C042DDB3AF2C467B593295', '修改密码', '/app/page/syspasswd', '65E16E3E1EFC484A8CBD2AD77D8D3F92');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('65E16E3E1EFC484A8CBD2AD77D8D3F92', '系统管理', '', '');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('A57EF00DD8934819AE2311DC88B622C1', '菜单管理', '/app/page/sysmenu', '65E16E3E1EFC484A8CBD2AD77D8D3F92');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('3D7C1106526F457BBB8B15E72478E983', '角色管理', '/app/page/sysrole', '65E16E3E1EFC484A8CBD2AD77D8D3F92');

insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('21E4F72B9D274510AB219C33D792A4C4', '用户管理', '/app/page/sysuser', '65E16E3E1EFC484A8CBD2AD77D8D3F92');


insert into sys_menu (MENU_ID, MENU_NAME, MENU_URL, PARENT_MENU_ID)
values ('22E4F72B9D274510AB219C33D792A4C4', '街道管理', '/app/page/street', '65E16E3E1EFC484A8CBD2AD77D8D3F92');

-- sys_role
insert into sys_role (ROLE_ID, ROLE_NAME, REMARK)
values ('root', '超级管理员', '具有全部权限');

-- sys_user
insert into sys_user (USERNAME, PASSWORD, NAME, ROLE_ID, GROUP_ID)
values ('admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', 'root', null);

-- sys_role_menu
insert into sys_role_menu (ROLE_ID, MENU_ID)
values ('root', 'A57EF00DD8934819AE2311DC88B622C1');

insert into sys_role_menu (ROLE_ID, MENU_ID)
values ('root', '3D7C1106526F457BBB8B15E72478E983');

insert into sys_role_menu (ROLE_ID, MENU_ID)
values ('root', '21E4F72B9D274510AB219C33D792A4C4');

insert into sys_role_menu (ROLE_ID, MENU_ID)
values ('root', '6E62020D770346F08A4287FF205EEB8F');

insert into sys_role_menu (ROLE_ID, MENU_ID)
values ('root', '8C95B11FF4C042DDB3AF2C467B593295');