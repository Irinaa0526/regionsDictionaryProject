create table regions_dictionary (
    id int primary key,
    name varchar (120) not null,
    shortName varchar (10) not null
);

insert into regions_dictionary values (5, 'Республика Дагестан',  'DA');
insert into regions_dictionary values (41, 'Камчатский край',  'KAM');
insert into regions_dictionary values (26, 'Ставропольский край',  'STA');
insert into regions_dictionary values (62, 'Рязанская область',  'RYA');
insert into regions_dictionary values (47, 'Ленинградская область',  'LEN');