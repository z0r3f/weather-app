create table public.chat (id bigint not null, title varchar(255), username varchar(255), primary key (id));
create table public.alert (id bigint generated by default as identity, hour_of_day integer, chat_id bigint, primary key (id));
create table public.location_favorite (id bigint generated by default as identity, country varchar(255), latitude double, longitude double, name varchar(255), chat_id bigint, primary key (id));

alter table public.alert add constraint alert_fk_chat foreign key (chat_id) references chat;
alter table public.location_favorite add constraint location_favorite_fk_chat foreign key (chat_id) references chat;