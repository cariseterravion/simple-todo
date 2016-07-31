
alter table todo add column due_date timestamp;
alter table todo add column create_date timestamp current_timestamp;

alter table todo add column remind_time timestamp;