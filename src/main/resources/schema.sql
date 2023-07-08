declare
    l_exists number;
begin
    select
        count(1) into l_exists
    from
        user_tables uo
    where
        uo.table_name = 'TEST';

    if l_exists > 0 then
        execute immediate 'drop table test';
    end if;
end;
/

create table test(
    id number not null,
    is_enabled char(1) not null,
    constraint test_pk primary key (id)
)
/